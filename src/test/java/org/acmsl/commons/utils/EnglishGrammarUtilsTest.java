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
*              org.acmsl.commons.utils.
*
* Last modified by: $Author: chous $ at $Date: 2006-04-02 12:15:23 +0200 (Sun, 02 Apr 2006) $
*
* File version: $Revision: 548 $
*
* Project version: $Name$
*
* $Id: EnglishGrammarUtilsTest.java 548 2006-04-02 10:15:23Z chous $
*/
package org.acmsl.commons.utils;

/*
* Importing project classes.
*/
// JUnitDoclet begin import
import junit.framework.Assert;
import org.acmsl.commons.utils.EnglishGrammarUtils;
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
 * Tests EnglishGrammarUtilsTest class.
 * @version $Revision: 548 $
 * @see org.acmsl.commons.utils.EnglishGrammarUtils
 */
public class EnglishGrammarUtilsTest
// JUnitDoclet begin extends_implements
    extends TestCase
// JUnitDoclet end extends_implements
{
    // JUnitDoclet begin class
    org.acmsl.commons.utils.EnglishGrammarUtils englishgrammarutils = null;
    // JUnitDoclet end class

    /**
     * Creates a EnglishGrammarUtilsTest with given name.
     * @param name such name.
     */
    public EnglishGrammarUtilsTest(String name)
    {
        // JUnitDoclet begin method EnglishGrammarUtilsTest
        super(name);
        // JUnitDoclet end method EnglishGrammarUtilsTest
    }

    public static void main(String[] args)
    {
        // JUnitDoclet begin method testcase.main
        junit.textui.TestRunner.run(EnglishGrammarUtilsTest.class);
        // JUnitDoclet end method testcase.main
    }

    /**
     * Creates an instance of the tested class.
     * @return such instance.

     */
    public org.acmsl.commons.utils.EnglishGrammarUtils createInstance()
        throws Exception
    {
        // JUnitDoclet begin method testcase.createInstance
        return org.acmsl.commons.utils.EnglishGrammarUtils.getInstance();
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
        englishgrammarutils = createInstance();
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
        englishgrammarutils = null;
        super.tearDown();
        // JUnitDoclet end method testcase.tearDown
    }

    /**
     * Tests EnglishGrammarUtilsTest#getInstance()
     * @throws Exception if an unexpected situation occurs.
     * @see org.acmsl.commons.utils.EnglishGrammarUtils#getInstance()
     */
    public void testGetInstance()
        throws Exception
    {
        // JUnitDoclet begin method getInstance
        EnglishGrammarUtils t_EnglishGrammarUtils = EnglishGrammarUtils.getInstance();

        assertNotNull(t_EnglishGrammarUtils);
        // JUnitDoclet end method getInstance
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

    /**
     * Tests {EnglishGrammarUtils#getRegularSingularForm(String)}.
     * @see EnglishGrammarUtils#getRegularSingularForm(String)
     */
    public void testGetRegularSingularForm()
    {
        EnglishGrammarUtils t_EnglishGrammarUtils = EnglishGrammarUtils.getInstance();

        Assert.assertNotNull(t_EnglishGrammarUtils);

        Assert.assertEquals(
            "Invalid singular for cars",
            "car",
            t_EnglishGrammarUtils.getRegularSingularForm("cars"));
        Assert.assertEquals(
            "Invalid singular for fast_cars",
            "fast_car",
            t_EnglishGrammarUtils.getRegularSingularForm("fast_cars"));
        Assert.assertEquals(
            "Invalid singular for trees",
            "tree",
            t_EnglishGrammarUtils.getRegularSingularForm("trees"));
        Assert.assertEquals(
            "Invalid singular for tall_trees",
            "tall_tree",
            t_EnglishGrammarUtils.getRegularSingularForm("tall_trees"));
        Assert.assertEquals(
            "Invalid singular for monkeys",
            "monkey",
            t_EnglishGrammarUtils.getRegularSingularForm("monkeys"));
        Assert.assertEquals(
            "Invalid singular for old_monkeys",
            "old_monkey",
            t_EnglishGrammarUtils.getRegularSingularForm("old_monkeys"));
        Assert.assertEquals(
            "Invalid singular for addresses",
            "address",
            t_EnglishGrammarUtils.getRegularSingularForm("addresses"));
        Assert.assertEquals(
            "Invalid singular for postal_addresses",
            "postal_address",
            t_EnglishGrammarUtils.getRegularSingularForm("postal_addresses"));
        Assert.assertEquals(
            "Invalid singular for categories",
            "category",
            t_EnglishGrammarUtils.getRegularSingularForm("categories"));
        Assert.assertEquals(
            "Invalid singular for food_categories",
            "food_category",
            t_EnglishGrammarUtils.getRegularSingularForm("food_categories"));
    }

    /**
     * Tests {EnglishGrammarUtils#getRegularPluralForm(String)}.
     * @see EnglishGrammarUtils#getRegularPluralForm(String)
     */
    public void testGetRegularPluralForm()
    {
        EnglishGrammarUtils t_EnglishGrammarUtils = EnglishGrammarUtils.getInstance();

        Assert.assertNotNull(t_EnglishGrammarUtils);

        Assert.assertEquals(
            "Invalid plural for car",
            "cars",
            t_EnglishGrammarUtils.getRegularPluralForm("car"));
        Assert.assertEquals(
            "Invalid plural for fast_car",
            "fast_cars",
            t_EnglishGrammarUtils.getRegularPluralForm("fast_car"));
        Assert.assertEquals(
            "Invalid plural for tree",
            "trees",
            t_EnglishGrammarUtils.getRegularPluralForm("tree"));
        Assert.assertEquals(
            "Invalid plural for tall_tree",
            "tall_trees",
            t_EnglishGrammarUtils.getRegularPluralForm("tall_tree"));
        Assert.assertEquals(
            "Invalid plural for monkey",
            "monkeys",
            t_EnglishGrammarUtils.getRegularPluralForm("monkey"));
        Assert.assertEquals(
            "Invalid plural for old_monkey",
            "old_monkeys",
            t_EnglishGrammarUtils.getRegularPluralForm("old_monkey"));
        Assert.assertEquals(
            "Invalid plural for category",
            "categories",
            t_EnglishGrammarUtils.getRegularPluralForm("category"));
        Assert.assertEquals(
            "Invalid plural for food_category",
            "food_categories",
            t_EnglishGrammarUtils.getRegularPluralForm("food_category"));
    }

    /**
     * Tests {EnglishGrammarUtils#getSingular(String)}.
     * @see EnglishGrammarUtils#getSingular(String)
     */
    public void testGetSingular()
    {
        EnglishGrammarUtils t_EnglishGrammarUtils = EnglishGrammarUtils.getInstance();

        Assert.assertNotNull(t_EnglishGrammarUtils);

        Assert.assertEquals(
            "Invalid singular for cars",
            "car",
            t_EnglishGrammarUtils.getSingular("cars"));
        Assert.assertEquals(
            "Invalid singular for fast_cars",
            "fast_car",
            t_EnglishGrammarUtils.getSingular("fast_cars"));
        Assert.assertEquals(
            "Invalid singular for trees",
            "tree",
            t_EnglishGrammarUtils.getSingular("trees"));
        Assert.assertEquals(
            "Invalid singular for tall_trees",
            "tall_tree",
            t_EnglishGrammarUtils.getSingular("tall_trees"));
        Assert.assertEquals(
            "Invalid singular for monkeys",
            "monkey",
            t_EnglishGrammarUtils.getSingular("monkeys"));
        Assert.assertEquals(
            "Invalid singular for old_monkeys",
            "old_monkey",
            t_EnglishGrammarUtils.getSingular("old_monkeys"));
        Assert.assertEquals(
            "Invalid singular for addresses",
            "address",
            t_EnglishGrammarUtils.getSingular("addresses"));
        Assert.assertEquals(
            "Invalid singular for postal_addresses",
            "postal_address",
            t_EnglishGrammarUtils.getSingular("postal_addresses"));
        Assert.assertEquals(
            "Invalid singular for categories",
            "category",
            t_EnglishGrammarUtils.getSingular("categories"));
        Assert.assertEquals(
            "Invalid singular for food_categories",
            "food_category",
            t_EnglishGrammarUtils.getSingular("food_categories"));
    }

    /**
     * Tests {EnglishGrammarUtils#getPlural(String)}.
     * @see EnglishGrammarUtils#getPlural(String)
     */
    public void testGetPlural()
    {
        EnglishGrammarUtils t_EnglishGrammarUtils = EnglishGrammarUtils.getInstance();

        Assert.assertNotNull(t_EnglishGrammarUtils);

        Assert.assertEquals(
            "Invalid plural for car",
            "cars",
            t_EnglishGrammarUtils.getPlural("car"));
        Assert.assertEquals(
            "Invalid plural for fast_car",
            "fast_cars",
            t_EnglishGrammarUtils.getPlural("fast_car"));
        Assert.assertEquals(
            "Invalid plural for tree",
            "trees",
            t_EnglishGrammarUtils.getPlural("tree"));
        Assert.assertEquals(
            "Invalid plural for tall_tree",
            "tall_trees",
            t_EnglishGrammarUtils.getPlural("tall_tree"));
        Assert.assertEquals(
            "Invalid plural for monkey",
            "monkeys",
            t_EnglishGrammarUtils.getPlural("monkey"));
        Assert.assertEquals(
            "Invalid plural for old_monkey",
            "old_monkeys",
            t_EnglishGrammarUtils.getPlural("old_monkey"));
        Assert.assertEquals(
            "Invalid plural for category",
            "categories",
            t_EnglishGrammarUtils.getPlural("category"));
        Assert.assertEquals(
            "Invalid plural for food_category",
            "food_categories",
            t_EnglishGrammarUtils.getPlural("food_category"));
    }

}
