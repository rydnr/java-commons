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
*              unittests.org.acmsl.commons.patterns.dao.
*
* Last modified by: $Author$ at $Date$
*
* File version: $Revision$
*
* Project version: $Name$
*
* $Id$
*/
package unittests.org.acmsl.commons.patterns.dao;

/*
* Importing project classes.
*/
// JUnitDoclet begin import
import org.acmsl.commons.patterns.dao.ValueObjectField;
import org.acmsl.commons.patterns.dao.IntFormatter;
import org.acmsl.commons.patterns.dao.LongFormatter;
import org.acmsl.commons.patterns.dao.StringFormatter;
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
* Tests ValueObjectFieldTest class.
* @version $Revision$
* @see org.acmsl.commons.patterns.dao.ValueObjectField
*/
public class ValueObjectFieldTest
// JUnitDoclet begin extends_implements
    extends TestCase
// JUnitDoclet end extends_implements
{
  // JUnitDoclet begin class
    org.acmsl.commons.patterns.dao.ValueObjectField intField = null;
    org.acmsl.commons.patterns.dao.ValueObjectField longField = null;
    org.acmsl.commons.patterns.dao.ValueObjectField stringField = null;

    /**
     * Specifies the int field.
     * @param field the int field.
     */
    protected void setIntField(ValueObjectField field)
    {
        intField = field;
    }

    /**
     * Retrieves the int field.
     * @return such field.
     */
    public ValueObjectField getIntField()
    {
        return intField;
    }

    /**
     * Specifies the long field.
     * @param field the long field.
     */
    protected void setLongField(ValueObjectField field)
    {
        longField = field;
    }

    /**
     * Retrieves the long field.
     * @return such field.
     */
    public ValueObjectField getLongField()
    {
        return longField;
    }

    /**
     * Specifies the string field.
     * @param field the string field.
     */
    protected void setStringField(ValueObjectField field)
    {
        stringField = field;
    }

    /**
     * Retrieves the string field.
     * @return such field.
     */
    public ValueObjectField getStringField()
    {
        return stringField;
    }

    /**
     * Creates an int instance of the tested class.
     * @return such instance.
     */
    public org.acmsl.commons.patterns.dao.ValueObjectField createIntInstance()
        throws Exception
    {
        return
            new org.acmsl.commons.patterns.dao.ValueObjectField(
                "int", IntFormatter.getInstance());
    }
  
    /**
     * Creates a long instance of the tested class.
     * @return such instance.
     */
    public org.acmsl.commons.patterns.dao.ValueObjectField createLongInstance()
        throws Exception
    {
        return
            new org.acmsl.commons.patterns.dao.ValueObjectField(
                "long", LongFormatter.getInstance());
    }
  
    /**
     * Creates a string instance of the tested class.
     * @return such instance.
     */
    public org.acmsl.commons.patterns.dao.ValueObjectField createStringInstance()
        throws Exception
    {
        return
            new org.acmsl.commons.patterns.dao.ValueObjectField(
                "string", StringFormatter.getInstance());
    }
  
  // JUnitDoclet end class
  
  /**
  * Creates a ValueObjectFieldTest with given name.
  * @param name such name.
  */
  public ValueObjectFieldTest(String name)
  {
    // JUnitDoclet begin method ValueObjectFieldTest
        super(name);
    // JUnitDoclet end method ValueObjectFieldTest
  }
  
  /**
  * Creates an instance of the tested class.
  * @return such instance.
  
  */
  public org.acmsl.commons.patterns.dao.ValueObjectField createInstance()
  throws Exception
  {
    // JUnitDoclet begin method testcase.createInstance
    return new org.acmsl.commons.patterns.dao.ValueObjectField("unknown", null);
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
        setIntField(createIntInstance());
        setLongField(createLongInstance());
        setStringField(createStringInstance());
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
        setIntField(null);
        setLongField(null);
        setStringField(null);
        super.tearDown();
    // JUnitDoclet end method testcase.tearDown
  }
  
  /**
  * Tests ValueObjectFieldTestgetName()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.patterns.dao.ValueObjectField#getName()
  */
  public void testGetName()
  throws Exception
  {
    // JUnitDoclet begin method getName
        assertNotNull(getIntField());
        assertEquals(getIntField().getName(), "int");
        assertNotNull(getLongField());
        assertEquals(getLongField().getName(), "long");
        assertNotNull(getStringField());
        assertEquals(getStringField().getName(), "string");
    // JUnitDoclet end method getName
  }
  
  /**
  * Tests ValueObjectFieldTestgetFormatter()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.patterns.dao.ValueObjectField#getFormatter()
  */
  public void testGetFormatter()
  throws Exception
  {
    // JUnitDoclet begin method getFormatter
    // JUnitDoclet end method getFormatter
  }
  
  /**
  * Tests ValueObjectFieldTesttoString()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.patterns.dao.ValueObjectField#toString()
  */
  public void testToString()
  throws Exception
  {
    // JUnitDoclet begin method toString
    // JUnitDoclet end method toString
  }
  
  /**
  * Tests ValueObjectFieldTestgetVersion()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.patterns.dao.ValueObjectField#getVersion()
  */
  public void testGetVersion()
  throws Exception
  {
    // JUnitDoclet begin method getVersion
    // JUnitDoclet end method getVersion
  }
  
  /**
  * Tests ValueObjectFieldTestgetClassVersion()
  * @throws Exception if an unexpected situation occurs.
  * @see org.acmsl.commons.patterns.dao.ValueObjectField#getClassVersion()
  */
  public void testGetClassVersion()
  throws Exception
  {
    // JUnitDoclet begin method getClassVersion
    // JUnitDoclet end method getClassVersion
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
        junit.textui.TestRunner.run(ValueObjectFieldTest.class);
    // JUnitDoclet end method testcase.main
  }
}
