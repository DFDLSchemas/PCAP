PCAP
====

DFDL Schemas for PCAP Packet Capture

Based on PCAP 2.4 as described at http://wiki.wireshark.org/Development/LibpcapFileFormat

It works with Daffodil, but not with IBM DFDL as the latter does not yet support 
the dfdl:inputValueCalc and dfdl:hiddenGroupRef properties.

Notes:

You must bind the external variable dfdl:byteOrder (defaults to "bigEndian") if you want to parse a
littleEndian pcap file and write it out littleEndian. It will parse fine, but when writing out will
use bigEndian by default, unless you bind the external variable.

