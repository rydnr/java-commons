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
*              unittests.org.acmsl.commons.regexpplugin.jakartaregexp.
*
* Last modified by: $Author: chous $ at $Date: 2006-04-02 12:15:23 +0200 (Sun, 02 Apr 2006) $
*
* File version: $Revision: 548 $
*
* Project version: $Name$
*
* $Id: MatchResultRegexpAdapterTest.java 548 2006-04-02 10:15:23Z chous $
*/
package org.acmsl.commons.regexpplugin.jakartaregexp;

/*
* Importing project classes.
*/
// JUnitDoclet begin import
import org.acmsl.commons.regexpplugin.jakartaregexp.MatchResultRegexpAdapter;
import org.apache.regexp.RE;
import org.apache.regexp.RECompiler;
import org.apache.regexp.REProgram;
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
* Tests MatchResultRegexpAdapterTest class.
* @version $Revision: 548 $
* @see org.acmsl.commons.regexpplugin.jakartaregexp.MatchResultRegexpAdapter
*/
public class MatchResultRegexpAdapterTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  org.acmsl.commons.regexpplugin.jakartaregexp.MatchResultRegexpAdapter matchResultRegexpAdapter = null;

    /**
     * Specifies the adapter.
     * @param adapter the adapter.
     */
    protected void setMatchResultRegexpAdapter(MatchResultRegexpAdapter adapter)
    {
        matchResultRegexpAdapter = adapter;
    }

    /**
     * Retrieves the adapter.
     * @return such adapter.
     */
    public MatchResultRegexpAdapter getMatchResultRegexpAdapter()
    {
        return matchResultRegexpAdapter;
    }
  // JUnitDoclet end class
  
  /**
  * Creates a MatchResultRegexpAdapterTest with given name.
  * @param name such name.
  */
  public MatchResultRegexpAdapterTest(String name)
  {
    // JUnitDoclet begin method MatchResultRegexpAdapterTest
    super(name);
    // JUnitDoclet end method MatchResultRegexpAdapterTest
  }
  
  /**
  * Creates an instance of the tested class.
  * @return such instance.
  
  */
  public org.acmsl.commons.regexpplugin.jakartaregexp.MatchResultRegexpAdapter createInstance()
  throws Exception
  {
    // JUnitDoclet begin method testcase.createInstance
      RECompiler t_Compiler = new RECompiler();

      REProgram t_REProgram = t_Compiler.compile("(.*?):(.*)");

      RE t_RE = new RE(t_REProgram);

      return
          new org.acmsl.commons.regexpplugin.jakartaregexp.MatchResultRegexpAdapter(
              t_RE);
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
    setMatchResultRegexpAdapter(createInstance());
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
    setMatchResultRegexpAdapter(null);
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  /**
  * Tests MatchResultRegexpAdapterTestgetRE()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.regexpplugin.jakartaregexp.MatchResultRegexpAdapter#getRE()
  */
  public void testGetRE()
  throws Exception
  {
    // JUnitDoclet begin method getRE
    // JUnitDoclet end method getRE
  }
  
  /**
  * Tests MatchResultRegexpAdapterTestgroup()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.regexpplugin.jakartaregexp.MatchResultRegexpAdapter#group(int)
  */
  public void testGroup()
  throws Exception
  {
    // JUnitDoclet begin method group
    // JUnitDoclet end method group
  }
  
  /**
  * Tests MatchResultRegexpAdapterTestgroups()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.regexpplugin.jakartaregexp.MatchResultRegexpAdapter#groups()
  */
  public void testGroups()
  throws Exception
  {
    // JUnitDoclet begin method groups
    // JUnitDoclet end method groups
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
    junit.textui.TestRunner.run(MatchResultRegexpAdapterTest.class);
    // JUnitDoclet end method testcase.main
  }
}
