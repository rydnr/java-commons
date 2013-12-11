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
*              unittests.org.acmsl.commons.patterns.
*
* Last modified by: $Author: chous $ at $Date: 2006-04-02 12:15:23 +0200 (Sun, 02 Apr 2006) $
*
* File version: $Revision: 548 $
*
* Project version: $Name$
*
* $Id: SubjectTest.java 548 2006-04-02 10:15:23Z chous $
*/
package org.acmsl.commons.patterns;

/*
* Importing project classes.
*/
// JUnitDoclet begin import
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
* Tests SubjectTest class.
* @version $Revision: 548 $
* @see org.acmsl.commons.patterns.Subject
*/
public class SubjectTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  org.acmsl.commons.patterns.Subject subject = null;
  // JUnitDoclet end class
  
  /**
  * Creates a SubjectTest with given name.
  * @param name such name.
  */
  public SubjectTest(final String name)
  {
    // JUnitDoclet begin method SubjectTest
    super(name);
    // JUnitDoclet end method SubjectTest
  }
  
  /**
  * Creates an instance of the tested class.
  * @return such instance.
  
  */
  public org.acmsl.commons.patterns.Subject createInstance()
  throws Exception
  {
    // JUnitDoclet begin method testcase.createInstance
    return new org.acmsl.commons.patterns.Subject();
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
    subject = createInstance();
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
    subject = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  /**
  * Tests SubjectTestattach()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.patterns.Subject#attach(org.acmsl.commons.patterns.Observer)
  */
  @SuppressWarnings("unused")
  public void testAttach()
  throws Exception
  {
    // JUnitDoclet begin method attach
    // JUnitDoclet end method attach
  }
  
  /**
  * Tests SubjectTestdetach()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.patterns.Subject#detach(org.acmsl.commons.patterns.Observer)
  */
  @SuppressWarnings("unused")
  public void testDetach()
  throws Exception
  {
    // JUnitDoclet begin method detach
    // JUnitDoclet end method detach
  }
  
  /**
  * Tests SubjectTestinform()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.patterns.Subject#inform()
  */
  @SuppressWarnings("unused")
  public void testInform()
  throws Exception
  {
    // JUnitDoclet begin method inform
    // JUnitDoclet end method inform
  }
  
  /**
  * Tests SubjectTestobservers()
  * @throws Exception if an unexpected situation occurs.
  */
  @SuppressWarnings("unused")
  public void testObservers()
  throws Exception
  {
    // JUnitDoclet begin method observers
    // JUnitDoclet end method observers
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
  
  public static void main(final String[] args)
  {
    // JUnitDoclet begin method testcase.main
    junit.textui.TestRunner.run(SubjectTest.class);
    // JUnitDoclet end method testcase.main
  }

    @Override
    public String toString()
    {
        return "SubjectTest{" +
               "subject=" + subject +
               '}';
    }
}
