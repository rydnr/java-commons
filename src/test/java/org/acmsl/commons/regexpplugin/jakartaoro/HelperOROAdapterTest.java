/*
                      Project tests

Copyright (C) 2003  Jose San Leandro Armend?riz
jsanleandro@yahoo.es
chousz@yahoo.com

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public
License as published by the Free Software Foundation; either
version 2 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

Thanks to ACM S.L. for distributing this library under the GPL license.
Contact info: jsr000@terra.es
Postal Address: c/Playa de Lagoa, 1
Urb. Valdecaba?as
Boadilla del monte
28660 Madrid
Spain

******************************************************************************
*
* Filename: $RCSfile$
*
* Author: Jose San Leandro Armend?riz
*
* Description: Executes all tests defined for package
*              unittests.org.acmsl.commons.regexpplugin.jakartaoro.
*
* Last modified by: $Author: chous $ at $Date: 2006-08-29 08:35:52 +0200 (Tue, 29 Aug 2006) $
*
* File version: $Revision: 1493 $
*
* Project version: $Name$
*
* $Id: HelperOROAdapterTest.java 1493 2006-08-29 06:35:52Z chous $
*/
package org.acmsl.commons.regexpplugin.jakartaoro;

/*
* Importing project classes.
*/
// JUnitDoclet begin import
import org.acmsl.commons.regexpplugin.jakartaoro.HelperOROAdapter;
// JUnitDoclet end import

/*
* Importing JUnit classes.
*/
import junit.framework.TestCase;

/*
This file is part of  JUnitDoclet, a project to generate basic
test cases  from source code and  helping to keep them in sync
during refactoring.

Copyright (C) 2002  ObjectFab GmbH  (http://www.objectfab.de/)

This library is  free software; you can redistribute it and/or
modify  it under the  terms of  the  GNU Lesser General Public
License as published  by the  Free Software Foundation; either
version 2.1  of the  License, or  (at your option)  any  later
version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or  FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Lesser General Public License for more details.

You  should  have  received a  copy of the  GNU Lesser General
Public License along with this  library; if not, write  to the
Free  Software  Foundation, Inc.,  59 Temple Place,  Suite 330,
Boston, MA  02111-1307  USA
*/


/**
* Tests HelperOROAdapterTest class.
* @version $Revision: 1493 $
* @see org.acmsl.commons.regexpplugin.jakartaoro.HelperOROAdapter
*/
public class HelperOROAdapterTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  org.acmsl.commons.regexpplugin.jakartaoro.HelperOROAdapter helperoroadapter = null;
  // JUnitDoclet end class
  
  /**
  * Creates a HelperOROAdapterTest with given name.
  * @param name such name.
  */
  public HelperOROAdapterTest(String name)
  {
    // JUnitDoclet begin method HelperOROAdapterTest
    super(name);
    // JUnitDoclet end method HelperOROAdapterTest
  }
  
  /**
  * Creates an instance of the tested class.
  * @return such instance.
  
  */
  public org.acmsl.commons.regexpplugin.jakartaoro.HelperOROAdapter createInstance()
  throws Exception
  {
    // JUnitDoclet begin method testcase.createInstance
    return new org.acmsl.commons.regexpplugin.jakartaoro.HelperOROAdapter();
    // JUnitDoclet end method testcase.createInstance
  }
  
  /**
  * Performs any required steps before each test.
  * @throws Exception if an unexpected situation occurs.
  */
  protected void setUp()
  throws Exception
  {
    // JUnitDoclet begin method testcase.setUp
    super.setUp();
    helperoroadapter = createInstance();
    // JUnitDoclet end method testcase.setUp
  }
  
  /**
  * Performs any required steps after each test.
  * @throws Exception if an unexpected situation occurs.
  */
  protected void tearDown()
  throws Exception
  {
    // JUnitDoclet begin method testcase.tearDown
    helperoroadapter = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  /**
  * Tests HelperOROAdapterTestreplaceAll()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.regexpplugin.jakartaoro.HelperOROAdapter#replaceAll(java.lang.String, java.lang.String, java.lang.String)
  */
  public void testReplaceAll()
  throws Exception
  {
    // JUnitDoclet begin method replaceAll
      assertNotNull(helperoroadapter);
      assertEquals(
          "replaceAll(\"tex\", \"x\", \"sst\") failed.",
          "tesst",
          helperoroadapter.replaceAll("tex", "x", "sst"));
      assertEquals(
          "replaceAll(\"text\", \"e\", \".\") failed.",
          "t.xt",
          helperoroadapter.replaceAll("text", "e", "."));
      assertEquals(
          "replaceAll(\"text.\", \"\\.\", \"\") failed.",
          "text",
          helperoroadapter.replaceAll("text.", "\\.", ""));
      assertEquals(
            "replaceAll(\""
          + "simple.text\", \"simple\\.text\", \"textual\") failed.",
          "textual",
          helperoroadapter.replaceAll(
              "simple.text", "simple\\.text", "textual"));
    // JUnitDoclet end method replaceAll
  }
  
  
  
  /**
  * JUnitDoclet moves marker to this method, if there is not match
  * for them in the regenerated code and if the marker is not empty.
  * This way, no test gets lost when regenerating after renaming.
  * Method testVault is supposed to be empty.
  * @throws Exception if an unexpected situation occurs.
  */
  public void testVault()
  throws Exception
  {
    // JUnitDoclet begin method testcase.testVault
    // JUnitDoclet end method testcase.testVault
  }
  
  public static void main(String[] args)
  {
    // JUnitDoclet begin method testcase.main
    junit.textui.TestRunner.run(HelperOROAdapterTest.class);
    // JUnitDoclet end method testcase.main
  }
}
