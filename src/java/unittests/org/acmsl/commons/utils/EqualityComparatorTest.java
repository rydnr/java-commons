/*
                        ACM-SL Commons

    Copyright (C) 2002-2003  Jose San Leandro Armendáriz
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
 * Description: Performs some unit tests on EqualityComparator class.
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
import org.acmsl.commons.utils.EqualityComparator;
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
 * Performs some unit tests on EqualityComparator class.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 * @testfamily JUnit
 * @testkind testcase
 * @testsetup Default TestCase
 * @testedclass org.acmsl.commons.utils.EqualityComparator
 * @see org.acmsl.commons.utils.EqualityComparator
 */
public class EqualityComparatorTest
    extends     TestCase
    implements  org.acmsl.commons.patterns.Test
{
    /**
     * Constructs a test case with the given name.
     * @param name the test case name.
     */
    public EqualityComparatorTest(String name)
    {
        super(name);
    }

    /**
     * Tests the EqualityComparator.areEqual(int,int) method.
     * @see EqualityComparator#areEqual(int,int)
     */
    public void testAreEqualInt()
    {
        EqualityComparator t_EqualityComparator =
            EqualityComparator.getInstance();

        assertNotNull(t_EqualityComparator);

        assertTrue(!t_EqualityComparator.areEqual(1, 3));

        assertTrue(!t_EqualityComparator.areEqual(-1, 0));

        assertTrue(t_EqualityComparator.areEqual(0, 0));

        assertTrue(t_EqualityComparator.areEqual(400, 400));
    }

    /**
     * Tests the EqualityComparator.areEqual(long,long) method.
     * @see EqualityComparator#areEqual(long,long)
     */
    public void testAreEqualLong()
    {
        EqualityComparator t_EqualityComparator =
            EqualityComparator.getInstance();

        assertNotNull(t_EqualityComparator);

        assertTrue(!t_EqualityComparator.areEqual(1L, 3L));

        assertTrue(!t_EqualityComparator.areEqual(-1L, 0L));

        assertTrue(t_EqualityComparator.areEqual(0L, 0L));

        assertTrue(t_EqualityComparator.areEqual(400L, 400L));
    }

    /**
     * Tests the EqualityComparator.areEqual(float,float) method.
     * @see EqualityComparator#areEqual(float,float)
     */
    public void testAreEqualFloat()
    {
        EqualityComparator t_EqualityComparator =
            EqualityComparator.getInstance();

        assertNotNull(t_EqualityComparator);

        assertTrue(!t_EqualityComparator.areEqual(1.0f, 3.0));

        assertTrue(!t_EqualityComparator.areEqual(-1.0f, 0.0f));

        assertTrue(t_EqualityComparator.areEqual(0.0f, 0.0f));

        assertTrue(t_EqualityComparator.areEqual(400.0f, 400.0f));
    }

    /**
     * Tests the EqualityComparator.areEqual(double,double) method.
     * @see EqualityComparator#areEqual(double,double)
     */
    public void testAreEqualDouble()
    {
        EqualityComparator t_EqualityComparator =
            EqualityComparator.getInstance();

        assertNotNull(t_EqualityComparator);

        assertTrue(!t_EqualityComparator.areEqual(1.0, 3.0));

        assertTrue(!t_EqualityComparator.areEqual(-1.0, 0.0));

        assertTrue(t_EqualityComparator.areEqual(0.0, 0.0));

        assertTrue(t_EqualityComparator.areEqual(400.0, 400.0));
    }

    /**
     * Tests the EqualityComparator.areEqual(Object,Object) method.
     * @see EqualityComparator#areEqual(Object,Object)
     */
    public void testAreEqualObject()
    {
        EqualityComparator t_EqualityComparator =
            EqualityComparator.getInstance();

        assertNotNull(t_EqualityComparator);

        assertTrue(!t_EqualityComparator.areEqual("1.0", "3.0"));

        assertTrue(!t_EqualityComparator.areEqual("-1.0", "0.0"));

        assertTrue(t_EqualityComparator.areEqual("0.0", "0.0"));

        assertTrue(t_EqualityComparator.areEqual("400.0", "400.0"));
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
