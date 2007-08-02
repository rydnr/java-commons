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
*              unittests.org.acmsl.commons.logging.
*
* Last modified by: $Author: chous $ at $Date: 2006-04-02 12:15:23 +0200 (Sun, 02 Apr 2006) $
*
* File version: $Revision: 548 $
*
* Project version: $Name$
*
* $Id: UniqueLogFactoryTest.java 548 2006-04-02 10:15:23Z chous $
*/
package org.acmsl.commons.logging;

/*
* Importing project classes.
*/
// JUnitDoclet begin import
import org.acmsl.commons.logging.UniqueLogFactory;
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
* Tests UniqueLogFactoryTest class.
* @version $Revision: 548 $
* @see org.acmsl.commons.logging.UniqueLogFactory
*/
public class UniqueLogFactoryTest
// JUnitDoclet begin extends_implements
extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
  org.acmsl.commons.logging.UniqueLogFactory uniquelogfactory = null;
  // JUnitDoclet end class
  
  /**
  * Creates a UniqueLogFactoryTest with given name.
  * @param name such name.
  */
  public UniqueLogFactoryTest(String name)
  {
    // JUnitDoclet begin method UniqueLogFactoryTest
    super(name);
    // JUnitDoclet end method UniqueLogFactoryTest
  }
  
  /**
  * Creates an instance of the tested class.
  * @return such instance.
  
  */
  public org.acmsl.commons.logging.UniqueLogFactory createInstance()
  throws Exception
  {
    // JUnitDoclet begin method testcase.createInstance
      return (UniqueLogFactory) UniqueLogFactory.getInstance();
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
    uniquelogfactory = createInstance();
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
    uniquelogfactory = null;
    super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  /**
  * Tests UniqueLogFactoryTestgetLog()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.logging.UniqueLogFactory#getLog()
  */
  public void testGetLog()
  throws Exception
  {
    // JUnitDoclet begin method getLog
    // JUnitDoclet end method getLog
  }
  
  /**
  * Tests UniqueLogFactoryTestgetInstance()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.logging.UniqueLogFactory#getInstance()
  */
  public void testGetInstance()
  throws Exception
  {
    // JUnitDoclet begin method getInstance
    // JUnitDoclet end method getInstance
  }
  
  /**
  * Tests UniqueLogFactoryTestinitializeInstance()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.logging.UniqueLogFactory#initializeInstance(org.apache.commons.logging.Log)
  */
  public void testInitializeInstance()
  throws Exception
  {
    // JUnitDoclet begin method initializeInstance
    // JUnitDoclet end method initializeInstance
  }
  
  /**
  * Tests UniqueLogFactoryTestgetAttribute()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.logging.UniqueLogFactory#getAttribute(java.lang.String)
  */
  public void testGetAttribute()
  throws Exception
  {
    // JUnitDoclet begin method getAttribute
    // JUnitDoclet end method getAttribute
  }
  
  /**
  * Tests UniqueLogFactoryTestgetAttributeNames()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.logging.UniqueLogFactory#getAttributeNames()
  */
  public void testGetAttributeNames()
  throws Exception
  {
    // JUnitDoclet begin method getAttributeNames
    // JUnitDoclet end method getAttributeNames
  }
  
  /**
  * Tests UniqueLogFactoryTestrelease()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.logging.UniqueLogFactory#release()
  */
  public void testRelease()
  throws Exception
  {
    // JUnitDoclet begin method release
    // JUnitDoclet end method release
  }
  
  /**
  * Tests UniqueLogFactoryTestremoveAttribute()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.logging.UniqueLogFactory#removeAttribute(java.lang.String)
  */
  public void testRemoveAttribute()
  throws Exception
  {
    // JUnitDoclet begin method removeAttribute
    // JUnitDoclet end method removeAttribute
  }
  
  /**
  * Tests UniqueLogFactoryTestsetAttribute()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.logging.UniqueLogFactory#setAttribute(java.lang.String, java.lang.Object)
  */
  public void testSetAttribute()
  throws Exception
  {
    // JUnitDoclet begin method setAttribute
    // JUnitDoclet end method setAttribute
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
    junit.textui.TestRunner.run(UniqueLogFactoryTest.class);
    // JUnitDoclet end method testcase.main
  }
}
