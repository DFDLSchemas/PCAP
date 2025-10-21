PCAP
====

DFDL Schemas for PCAP Packet Capture

Based on PCAP 2.4 as described at http://wiki.wireshark.org/Development/LibpcapFileFormat, with support for IPv4 (UDP, TCP, ICMP) and IPv6 (TCP, UDP) packet types and protocols.

It works with Daffodil, but not with IBM DFDL as the latter does not yet support 
the dfdl:inputValueCalc and dfdl:hiddenGroupRef properties.

This schema depends on the `ethernetIP` DFDL schema which describes the various packet formats 
captured in PCAP files. 
This schema provides a useful example of what we call _schema composition_ where an _assembly_ 
_schema_ is built up from other _component schemas_. 

### External Variable `dfdl:byteOrder`

You must bind the external variable `dfdl:byteOrder` (defaults to `"bigEndian"`) if you
want to parse a `littleEndian` PCAP file and write it out (aka _unparse_ it) as `littleEndian`. 
Without this variable binding it will parse fine, but when writing out will 
use `bigEndian` by default. 

## Using PCAP from the Daffodil Command Line

PCAP is often used by beginners learning DFDL with the expectation that it is
a simple format that is well understood.
It is then surprising that it's not possible to make PCAP work only via the daffodil 
command-line interface (CLI).
PCAP has some complexities discussed below that make it a bit difficult to use
as a first example. 
Details and how to overcome this are below.

This issue was first raised as an issue ticket on the PCAP schema project. 
Here's the answer to that issue:
https://github.com/DFDLSchemas/PCAP/issues/25#issuecomment-2904267949 which is also
described here.

To make PCAP work, one must have the [`sbt` build tool](https://www.scala-sbt.org/), 
as ethernetIP has a small bit of Scala code in it that must be compiled. 
This Scala code exists because IP packets have a checksum in them. 
The Scala code is the checksum algorithm.

What `sbt` does for you is take a one line specification of the dependency 
on ethernetIP (in PCAP's `build.sbt` file), and automates extracting all 
the needed files for that dependency. 
It also compiles the scala code that is part of ethernetIP’s 
`IPv4Checksum` calculation (a Daffodil plug-in) and creates a 
Java _jar file_ from it that Daffodil will find on the classpath.

Here’s the steps to get PCAP to run its unit tests. 
Of note, these steps do not use the daffodil CLI. 
They will work without you even having downloaded daffodil at all.

1. `git clone` (or unzip) the release of PCAP
1. `git clone` (or unzip) the release of ethernetIP – you need the version that is in PCAP’s 
      `build.sbt` line (which is 1.4.0 as of this writing).
1. `cd ethernetIP`
1. `sbt test` – Compiles code and runs tests to show that ethernetIP works. 
  This pulls down everything needed from the internet.
1. `sbt package` – compiles and creates jar file 
1. `sbt publishLocal` – makes jar available as a _managed dependency_ for use by PCAP or other 
   DFDL schemas (It stores the jar in a subdirectirt of your homedir named `“~/.ivy2/local”`
   on linux which sbt knows and uses later)
1. `cd PCAP`
1. `sbt test` – shows that PCAP works (and uses ethernetIP when doing so.)

Next, the `DAFFODIL_CLASSPATH` environment variable must be set properly to allow 
Daffodil to find the ethernetIP components in their jar files.

Once again `sbt` helps you do this, but the specific command is complex. 
Hence, you really need the `setDaffodilClasspath` alias. 
For linux bash shell that’s this:

    alias setDaffodilClasspath='export DAFFODIL_CLASSPATH=$(sbt -batch -error  "export fullClasspath")'

(Note the specific kinds of quotation marks in the above. They are important.)
This alias command should be put in your `~/.bash_aliases` file for future use. 

The supposed MS-Windows equivalent (untested) is a batch file 
named `setDaffodilClasspath.bat` that does this:

    for /f "delims=" %i in ('sbt -batch -error "export fullClasspath"') do set DAFFODIL_CLASSPATH=%i

The above command sets `DAFFODIL_CLASSPATH` so that ethernetIP’s jar file can be 
found by PCAP when you issue daffodil command lines.

After the above steps, the daffodil CLI can be used to parse PCAP files. 

## Usage as a Component DFDL Schema

To use PCAP data as a component (for example as the payload of a format that carries files)
you would add this namespace prefix definition:

    xmlns:pcap="urn:owlcyberdefense.com:schema:dfdl:pcap"

then import the `pcap-type.dfdl.xsd` file like this:

    <import namespace="urn:owlcyberdefense.com:schema:dfdl:pcap"
            schemaLocation="/com/tresys/pcap/xsd/pcap-type.dfdl.xsd"/>

then one can define a local element like this:

    <element name="pcap" type="pcap:PCAP"/>

> **TIP**:
> The [envelope-payload DFDL Schema](
> https://github.com/DFDLSchemas/envelope-payload
) uses this PCAP schema as a component. 
