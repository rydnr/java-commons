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
*              unittests.org.acmsl.commons.version.
*
* Last modified by: $Author$ at $Date$
*
* File version: $Revision$
*
* Project version: $Name$
*
* $Id$
*/
package unittests.org.acmsl.commons.version;

/*
* Importing project classes.
*/
// JUnitDoclet begin import
import org.acmsl.commons.version.VersionUtils;
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
* Tests VersionUtilsTest class.
* @version $Revision$
* @see org.acmsl.commons.version.VersionUtils
*/
public class VersionUtilsTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  org.acmsl.commons.version.VersionUtils versionutils = null;
  // JUnitDoclet end class
  
  /**
  * Creates a VersionUtilsTest with given name.
  * @param name such name.
  */
  public VersionUtilsTest(String name)
  {
    // JUnitDoclet begin method VersionUtilsTest
    super(name);
    // JUnitDoclet end method VersionUtilsTest
  }
  
  /**
  * Creates an instance of the tested class.
  * @return such instance.
  
  */
  public org.acmsl.commons.version.VersionUtils createInstance()
  throws Exception
  {
    // JUnitDoclet begin method testcase.createInstance
    return org.acmsl.commons.version.VersionUtils.getInstance();
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
    versionutils = createInstance();
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
    versionutils = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  /**
  * Tests VersionUtilsTestgetInstance()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.version.VersionUtils#getInstance()
  */
  public void testGetInstance()
  throws Exception
  {
    // JUnitDoclet begin method getInstance
      assertNotNull("getInstance() is null", VersionUtils.getInstance());
    // JUnitDoclet end method getInstance
  }
  
  /**
  * Tests VersionUtilsTestgetVersionPattern()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.version.VersionUtils#getVersionPattern()
  */
  public void testGetVersionPattern()
  throws Exception
  {
    // JUnitDoclet begin method getVersionPattern
    // JUnitDoclet end method getVersionPattern
  }
  
  /**
  * Tests VersionUtilsTestmatches()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.version.VersionUtils#matches(java.lang.String, java.lang.String)
  */
  public void testMatches()
  throws Exception
  {
    // JUnitDoclet begin method matches
      VersionUtils t_VersionUtils = VersionUtils.getInstance();
      assertNotNull("getInstance() is null", t_VersionUtils);
      assertTrue(
          "'4.x' should match '4.3.2'",
          t_VersionUtils.matches("4.3.2", "4.x"));
      assertFalse(
          "'5.x' should not match '2.1'",
          t_VersionUtils.matches("2.1", "5.x"));
      assertTrue(
          "'6.1.x' should match '6.1.3-pre'",
          t_VersionUtils.matches("6.1.3-pre", "6.1.x"));
    // JUnitDoclet end method matches
  }
  
  /**
  * Tests VersionUtilsTestversionNumbersMatch()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.version.VersionUtils#versionNumbersMatch(java.lang.String, java.lang.String)
  */
  public void testVersionNumbersMatch()
  throws Exception
  {
    // JUnitDoclet begin method versionNumbersMatch
      VersionUtils t_VersionUtils = VersionUtils.getInstance();
      assertNotNull("getInstance() is null", t_VersionUtils);
      assertTrue(
          "'x' should match '4'",
          t_VersionUtils.versionNumbersMatch("4", "x"));
      assertTrue(
          "'5' should match '5'",
          t_VersionUtils.versionNumbersMatch("5", "5"));
    // JUnitDoclet end method versionNumbersMatch
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
    junit.textui.TestRunner.run(VersionUtilsTest.class);
    // JUnitDoclet end method testcase.main
  }
}
