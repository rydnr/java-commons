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
* Last modified by: $Author$ at $Date$
*
* File version: $Revision$
*
* Project version: $Name$
*
* $Id$
*/
package unittests.org.acmsl.commons.regexpplugin.jakartaoro;

/*
* Importing project classes.
*/
// JUnitDoclet begin import
import org.acmsl.commons.regexpplugin.jakartaoro.MatchResultOROAdapter;
import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
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
* Tests MatchResultOROAdapterTest class.
* @version $Revision$
* @see org.acmsl.commons.regexpplugin.jakartaoro.MatchResultOROAdapter
*/
public class MatchResultOROAdapterTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
    MatchResultOROAdapter matchResultOROAdapter = null;

    /**
     * Specifies the adapter.
     * @param adapter the adapter.
     */
    protected void setMatchResultOROAdapter(MatchResultOROAdapter adapter)
    {
        matchResultOROAdapter = adapter;
    }

    /**
     * Retrieves the adapter.
     * @return such adapter.
     */
    public MatchResultOROAdapter getMatchResultOROAdapter()
    {
        return matchResultOROAdapter;
    }
  // JUnitDoclet end class
  
  /**
  * Creates a MatchResultOROAdapterTest with given name.
  * @param name such name.
  */
  public MatchResultOROAdapterTest(String name)
  {
    // JUnitDoclet begin method MatchResultOROAdapterTest
    super(name);
    // JUnitDoclet end method MatchResultOROAdapterTest
  }
  
  /**
  * Creates an instance of the tested class.
  * @return such instance.
  
  */
  public org.acmsl.commons.regexpplugin.jakartaoro.MatchResultOROAdapter createInstance()
  throws Exception
  {
    // JUnitDoclet begin method testcase.createInstance
      MatchResultOROAdapter result = null;

      Perl5Compiler t_Compiler = new Perl5Compiler();

      Pattern t_Pattern =
          t_Compiler.compile("^(.*)?\\:(.*)$");

      Perl5Matcher t_Perl5Matcher = new Perl5Matcher();

      if  (t_Perl5Matcher.contains("one:two", t_Pattern))
      {
          result = new MatchResultOROAdapter(t_Perl5Matcher.getMatch());
      }

      return result;
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
    setMatchResultOROAdapter(createInstance());
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
    setMatchResultOROAdapter(null);
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  /**
  * Tests MatchResultOROAdapterTestgroup()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.regexpplugin.jakartaoro.MatchResultOROAdapter#group(int)
  */
  public void testGroup()
  throws Exception
  {
    // JUnitDoclet begin method group
      assertNotNull(getMatchResultOROAdapter());
      assertEquals(getMatchResultOROAdapter().group(1), "one");
      assertEquals(getMatchResultOROAdapter().group(2), "two");
    // JUnitDoclet end method group
  }
  
  /**
  * Tests MatchResultOROAdapterTestgroups()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.regexpplugin.jakartaoro.MatchResultOROAdapter#groups()
  */
  public void testGroups()
  throws Exception
  {
    // JUnitDoclet begin method groups
      assertNotNull(getMatchResultOROAdapter());
      assertTrue(
          (   (getMatchResultOROAdapter().groups() == 2)
           || (   (getMatchResultOROAdapter().groups() == 3)
               && (getMatchResultOROAdapter().group(3) == null))));
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
    junit.textui.TestRunner.run(MatchResultOROAdapterTest.class);
    // JUnitDoclet end method testcase.main
  }
}
