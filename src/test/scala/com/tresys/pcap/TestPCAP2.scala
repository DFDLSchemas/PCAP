/* Copyright (c) 2012-2020 Owl Cyber Defense Solutions, LLC. All rights reserved.
 *
 * Developed by: Owl Cyber Defense Solutions, LLC
 *               http://www.owlcyberdefense.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal with
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 *  1. Redistributions of source code must retain the above copyright notice,
 *     this list of conditions and the following disclaimers.
 *
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimers in the
 *     documentation and/or other materials provided with the distribution.
 *
 *  3. Neither the names of Owl Cyber Defense, nor the names of its contributors
 *     may be used to endorse or promote products derived from this Software
 *     without specific prior written permission.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * CONTRIBUTORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS WITH THE
 * SOFTWARE.
 */

package com.tresys.pcap

import org.junit.Test
import org.apache.daffodil.tdml.Runner
import org.junit.AfterClass

object TestPCAP2 {
  lazy val runner = Runner("/com/tresys/pcap/", "pcap2.tdml")

  @AfterClass def shutDown {
    runner.reset
  }
}

class TestPCAP2 {

  import TestPCAP2._

  // Ticket https://github.com/DFDLSchemas/PCAP/issues/7
  // @Test def test_udp_fragmented() = { runner.runOneTest("udp-fragmented") }

}
