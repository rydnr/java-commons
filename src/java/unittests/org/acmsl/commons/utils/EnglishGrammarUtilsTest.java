/*
                        ACM-SL Commons

    Copyright (C) 2002-2003  Jose San Leandro Armendáriz
                             jsanleandro@yahoo.es
                             chousz@yahoo.com

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the License, or any later version.

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
                    Urb. Valdecabañas
                    Boadilla del monte
                    28660 Madrid
                    Spain

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armendáriz
 *
 * Description: Performs some unit tests on EnglishGrammarUtils class.
 *
 * File version: $Revision$
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id$
 *
 */
package unittests.org.acmsl.commons.utils;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.utils.EnglishGrammarUtils;
import org.acmsl.commons.version.Version;
import org.acmsl.commons.version.VersionFactory;

/*
 * Importing some JDK classes.
 */
import java.io.File;

/*
 * Importing JUnit classes.
 */
import junit.framework.TestCase;

/**
 * Performs some unit tests on EnglishGrammarUtils class.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 * @testfamily JUnit
 * @testkind testcase
 * @testsetup Default TestCase
 * @testedclass org.acmsl.commons.utils.EnglishGrammarUtils
 * @see org.acmsl.commons.utils.EnglishGrammarUtils
 */
public class EnglishGrammarUtilsTest
    extends     TestCase
    implements  org.acmsl.commons.patterns.Test
{
    /**
     * Constructs a test case with the given name.
     * @param name the test case name.
     */
    public EnglishGrammarUtilsTest(final String name)
    {
        super(name);
    }

    /**
     * Tests the EnglishGrammarUtils.getPlural(word) method.
     * @see EnglishGrammarUtils#getPlural(String)
     */
    public void testGetPlural()
    {
        EnglishGrammarUtils t_EnglishGrammarUtils = EnglishGrammarUtils.getInstance();

        assertNotNull(t_EnglishGrammarUtils);

        assertEquals(t_EnglishGrammarUtils.getPlural("car"), "cars");
        assertEquals(t_EnglishGrammarUtils.getPlural("CAR"), "CARS");
        assertEquals(t_EnglishGrammarUtils.getPlural("man"), "men");
        assertEquals(t_EnglishGrammarUtils.getPlural("MAN"), "MEN");
        assertEquals(t_EnglishGrammarUtils.getPlural("woman"), "women");
        assertEquals(t_EnglishGrammarUtils.getPlural("WOMAN"), "WOMEN");
        assertEquals(t_EnglishGrammarUtils.getPlural("bus"), "buses");
        assertEquals(t_EnglishGrammarUtils.getPlural("BUS"), "BUSES");
        assertEquals(t_EnglishGrammarUtils.getPlural("peach"), "peaches");
        assertEquals(t_EnglishGrammarUtils.getPlural("PEACH"), "PEACHES");
        assertEquals(t_EnglishGrammarUtils.getPlural("dish"), "dishes");
        assertEquals(t_EnglishGrammarUtils.getPlural("DISH"), "DISHES");
        assertEquals(t_EnglishGrammarUtils.getPlural("customer"), "customers");
        assertEquals(t_EnglishGrammarUtils.getPlural("CUSTOMER"), "CUSTOMERS");
    }

    /**
     * Tests the EnglishGrammarUtils.getSingular(word) method.
     * @see EnglishGrammarUtils#getSingular(String)
     */
    public void testGetSingular()
    {
        EnglishGrammarUtils t_EnglishGrammarUtils = EnglishGrammarUtils.getInstance();

        assertNotNull(t_EnglishGrammarUtils);

        assertEquals(t_EnglishGrammarUtils.getSingular("cars"), "car");
        assertEquals(t_EnglishGrammarUtils.getSingular("CARS"), "CAR");
        assertEquals(t_EnglishGrammarUtils.getSingular("men"), "man");
        assertEquals(t_EnglishGrammarUtils.getSingular("MEN"), "MAN");
        assertEquals(t_EnglishGrammarUtils.getSingular("women"), "woman");
        assertEquals(t_EnglishGrammarUtils.getSingular("WOMEN"), "WOMAN");
        assertEquals(t_EnglishGrammarUtils.getSingular("buses"), "bus");
        assertEquals(t_EnglishGrammarUtils.getSingular("BUSES"), "BUS");
        assertEquals(t_EnglishGrammarUtils.getSingular("peaches"), "peach");
        assertEquals(t_EnglishGrammarUtils.getSingular("PEACHES"), "PEACH");
        assertEquals(t_EnglishGrammarUtils.getSingular("dishes"), "dish");
        assertEquals(t_EnglishGrammarUtils.getSingular("DISHES"), "DISH");
        assertEquals(t_EnglishGrammarUtils.getSingular("customers"), "customer");
        assertEquals(t_EnglishGrammarUtils.getSingular("CUSTOMERS"), "CUSTOMER");
    }

    /**
     * Concrete version object updated everytime it's checked-in in a CVS
     * repository.
     */
    public static final Version VERSION =
        VersionFactory.createVersion("$Revision$");

    /**
     * Retrieves the current version of this object.
     * @return the version object with such information.
     */
    public Version getVersion()
    {
        return VERSION;
    }

    /**
     * Retrieves the current version of this class.
     * @return the object with class version information.
     */
    public static Version getClassVersion()
    {
        return VERSION;
    }
}
