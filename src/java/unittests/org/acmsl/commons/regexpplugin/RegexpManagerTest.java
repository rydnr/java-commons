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
*              unittests.org.acmsl.commons.regexpplugin.
*
* Last modified by: $Author$ at $Date$
*
* File version: $Revision$
*
* Project version: $Name$
*
* $Id$
*/
package unittests.org.acmsl.commons.regexpplugin;

/*
* Importing project classes.
*/
// JUnitDoclet begin import
import org.acmsl.commons.regexpplugin.RegexpManager;
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
* Tests RegexpManagerTest class.
* @version $Revision$
* @see org.acmsl.commons.regexpplugin.RegexpManager
*/
public class RegexpManagerTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  org.acmsl.commons.regexpplugin.RegexpManager regexpmanager = null;
  // JUnitDoclet end class
  
  /**
  * Creates a RegexpManagerTest with given name.
  * @param name such name.
  */
  public RegexpManagerTest(String name)
  {
    // JUnitDoclet begin method RegexpManagerTest
    super(name);
    // JUnitDoclet end method RegexpManagerTest
  }
  
  /**
  * Creates an instance of the tested class.
  * @return such instance.
  
  */
  public org.acmsl.commons.regexpplugin.RegexpManager createInstance()
  throws Exception
  {
    // JUnitDoclet begin method testcase.createInstance
      return null;
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
    regexpmanager = createInstance();
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
    regexpmanager = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  /**
  * Tests RegexpManagerTestuseJakartaOroPerl5()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.regexpplugin.RegexpManager#useJakartaOroPerl5()
  */
  public void testUseJakartaOroPerl5()
  throws Exception
  {
    // JUnitDoclet begin method useJakartaOroPerl5
        try
        {
            RegexpManager.useJakartaOroPerl5();

            org.acmsl.commons.regexpplugin.Compiler t_Compiler = RegexpManager.createCompiler();

            assertTrue(t_Compiler != null);

            assertTrue(t_Compiler instanceof org.acmsl.commons.regexpplugin.jakartaoro.Perl5CompilerOROAdapter);
        }
        catch  (org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            assertTrue("Regexp engine not found.", false);
        }
    // JUnitDoclet end method useJakartaOroPerl5
  }
  
  /**
  * Tests RegexpManagerTestuseJakartaOroAwk()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.regexpplugin.RegexpManager#useJakartaOroAwk()
  */
  public void testUseJakartaOroAwk()
  throws Exception
  {
    // JUnitDoclet begin method useJakartaOroAwk
    // JUnitDoclet end method useJakartaOroAwk
  }
  
  /**
  * Tests RegexpManagerTestuseJakartaRegexp()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.regexpplugin.RegexpManager#useJakartaRegexp()
  */
  public void testUseJakartaRegexp()
  throws Exception
  {
    // JUnitDoclet begin method useJakartaRegexp
        try
        {
            RegexpManager.useJakartaRegexp();

            org.acmsl.commons.regexpplugin.Compiler t_Compiler = RegexpManager.createCompiler();

            assertTrue(t_Compiler != null);

            assertTrue(t_Compiler instanceof org.acmsl.commons.regexpplugin.jakartaregexp.CompilerRegexpAdapter);
        }
        catch  (org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            assertTrue("Regexp engine not found.", false);
        }
    // JUnitDoclet end method useJakartaRegexp
  }
  
  /**
  * Tests RegexpManagerTestuseJDK14Regexp()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.regexpplugin.RegexpManager#useJDK14Regexp()
  */
  public void testUseJDK14Regexp()
  throws Exception
  {
    // JUnitDoclet begin method useJDK14Regexp
        try
        {
            RegexpManager.useJDK14Regexp();

            org.acmsl.commons.regexpplugin.Compiler t_Compiler = RegexpManager.createCompiler();

            assertTrue(t_Compiler != null);

            assertTrue(t_Compiler instanceof org.acmsl.commons.regexpplugin.jdk14regexp.CompilerJDKAdapter);
        }
        catch  (org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            assertTrue("Regexp engine not found.", false);
        }
    // JUnitDoclet end method useJDK14Regexp
  }
  
  /**
  * Tests RegexpManagerTestuseGNURegexp()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.regexpplugin.RegexpManager#useGNURegexp()
  */
  public void testUseGNURegexp()
  throws Exception
  {
    // JUnitDoclet begin method useGNURegexp
        try
        {
            RegexpManager.useGNURegexp();

            org.acmsl.commons.regexpplugin.Compiler t_Compiler = RegexpManager.createCompiler();

            assertTrue(t_Compiler != null);

            assertTrue(t_Compiler instanceof org.acmsl.commons.regexpplugin.gnuregexp.CompilerGNUAdapter);
        }
        catch  (org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            assertTrue("Regexp engine not found.", false);
        }
    // JUnitDoclet end method useGNURegexp
  }
  
  /**
  * Tests RegexpManagerTestcreateCompiler()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.regexpplugin.RegexpManager#createCompiler()
  */
  public void testCreateCompiler()
  throws Exception
  {
    // JUnitDoclet begin method createCompiler
        try
        {
            RegexpManager.useJakartaOroPerl5();

            org.acmsl.commons.regexpplugin.Compiler t_Compiler = RegexpManager.createCompiler();

            assertTrue(t_Compiler != null);

            assertTrue(t_Compiler instanceof org.acmsl.commons.regexpplugin.jakartaoro.Perl5CompilerOROAdapter);
        }
        catch  (org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            assertTrue("Regexp engine not found.", false);
        }
    // JUnitDoclet end method createCompiler
  }
  
  /**
  * Tests RegexpManagerTestcreateHelper()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.regexpplugin.RegexpManager#createHelper()
  */
  public void testCreateHelper()
  throws Exception
  {
    // JUnitDoclet begin method createHelper
        try
        {
            RegexpManager.useJakartaOroPerl5();

            org.acmsl.commons.regexpplugin.Helper t_Helper = RegexpManager.createHelper();

            assertTrue(t_Helper != null);

            assertTrue(t_Helper instanceof org.acmsl.commons.regexpplugin.jakartaoro.HelperOROAdapter);
        }
        catch  (org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            assertTrue("Regexp engine not found.", false);
        }
    // JUnitDoclet end method createHelper
  }
  
  /**
  * Tests RegexpManagerTestcreateMatcher()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.regexpplugin.RegexpManager#createMatcher()
  */
  public void testCreateMatcher()
  throws Exception
  {
    // JUnitDoclet begin method createMatcher
    // JUnitDoclet end method createMatcher
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
    junit.textui.TestRunner.run(RegexpManagerTest.class);
    // JUnitDoclet end method testcase.main
  }
}
