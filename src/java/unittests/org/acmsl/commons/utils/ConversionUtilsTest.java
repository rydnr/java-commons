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
 * Description: Performs some unit tests on ConversionUtils class.
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
import org.acmsl.commons.utils.ConversionUtils;

/*
 * Importing some JDK classes.
 */
import java.math.BigDecimal;

/*
 * Importing JUnit classes.
 */
import junit.framework.TestCase;

/**
 * Performs some unit tests on ConversionUtils class.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 * @testfamily JUnit
 * @testkind testcase
 * @testsetup Default TestCase
 * @testedclass org.acmsl.commons.utils.ConversionUtils
 * @see org.acmsl.commons.utils.ConversionUtils
 */
public class ConversionUtilsTest
    extends     TestCase
    implements  org.acmsl.commons.patterns.Test
{
    /**
     * Constructs a test case with the given name.
     * @param name the test case name.
     */
    public ConversionUtilsTest(final String name)
    {
        super(name);
    }

    /**
     * Tests the ConversionUtils.toString(String) method.
     * @see ConversionUtils#toString(String)
     */
    public void testToString()
    {
        ConversionUtils t_ConversionUtils =
            ConversionUtils.getInstance();

        assertNotNull(t_ConversionUtils);

        assertTrue("a".equals(t_ConversionUtils.toString("a")));
        assertTrue(
            Boolean.TRUE.toString().equals(
                t_ConversionUtils.toString(Boolean.TRUE.toString())));
        assertTrue("a".equals(t_ConversionUtils.toString("a")));
        assertTrue("something else".equals(t_ConversionUtils.toString("something else")));
    }

    /**
     * Tests the ConversionUtils.toBoolean(String) method.
     * @see ConversionUtils#toBoolean(String)
     */
    public void testToBoolean()
    {
        ConversionUtils t_ConversionUtils =
            ConversionUtils.getInstance();

        assertNotNull(t_ConversionUtils);

        assertTrue(t_ConversionUtils.toBoolean("true"));
        assertTrue(t_ConversionUtils.toBoolean(Boolean.TRUE.toString()));
        assertTrue(!t_ConversionUtils.toBoolean("false"));
        assertTrue(!t_ConversionUtils.toBoolean(Boolean.FALSE.toString()));
        assertTrue(!t_ConversionUtils.toBoolean("something else"));
    }

    /**
     * Tests the ConversionUtils.toInt(String) method.
     * @see ConversionUtils#toInt(String)
     */
    public void testToInt()
    {
        ConversionUtils t_ConversionUtils =
            ConversionUtils.getInstance();

        assertNotNull(t_ConversionUtils);

        assertTrue(t_ConversionUtils.toInt("2") == 2);
        assertTrue(t_ConversionUtils.toInt("-2") == -2);
        assertTrue(t_ConversionUtils.toInt("1232323") == 1232323);
        assertTrue(t_ConversionUtils.toInt("something else") == 0);
    }

    /**
     * Tests the ConversionUtils.toLong(String) method.
     * @see ConversionUtils#toLong(String)
     */
    public void testToLong()
    {
        ConversionUtils t_ConversionUtils =
            ConversionUtils.getInstance();

        assertNotNull(t_ConversionUtils);

        assertTrue(t_ConversionUtils.toLong("2") == 2);
        assertTrue(t_ConversionUtils.toLong("-2") == -2);
        assertTrue(t_ConversionUtils.toLong("1232323") == 1232323);
        assertTrue(t_ConversionUtils.toLong("something else") == 0);
    }

    /**
     * Tests the ConversionUtils.toFloat(String) method.
     * @see ConversionUtils#toFloat(String)
     */
    public void testToFloat()
    {
        ConversionUtils t_ConversionUtils =
            ConversionUtils.getInstance();

        assertNotNull(t_ConversionUtils);

        assertTrue(t_ConversionUtils.toFloat("2.0") == 2.0);
        assertTrue(t_ConversionUtils.toFloat("-2.0") == -2.0);
        assertTrue(t_ConversionUtils.toFloat("1232323.0") == 1232323.0);
        assertTrue(t_ConversionUtils.toFloat("something else") == 0.0);
    }

    /**
     * Tests the ConversionUtils.toDouble(String) method.
     * @see ConversionUtils#toDouble(String)
     */
    public void testToDouble()
    {
        ConversionUtils t_ConversionUtils =
            ConversionUtils.getInstance();

        assertNotNull(t_ConversionUtils);

        assertTrue(t_ConversionUtils.toDouble("2.0") == 2.0);
        assertTrue(t_ConversionUtils.toDouble("-2.0") == -2.0);
        assertTrue(t_ConversionUtils.toDouble("1232323.0") == 1232323.0);
        assertTrue(t_ConversionUtils.toDouble("something else") == 0.0);
    }

    /**
     * Tests the ConversionUtils.toChar(String) method.
     * @see ConversionUtils#toChar(String)
     */
    public void testToChar()
    {
        ConversionUtils t_ConversionUtils =
            ConversionUtils.getInstance();

        assertNotNull(t_ConversionUtils);

        assertTrue(Character.getNumericValue(t_ConversionUtils.toChar("2")) == 2);
        assertTrue(Character.getNumericValue(t_ConversionUtils.toChar("3")) == 3);
        assertTrue(Character.getNumericValue(t_ConversionUtils.toChar("something else")) == -1);
    }

    /**
     * Tests the ConversionUtils.toShort(String) method.
     * @see ConversionUtils#toShort(String)
     */
    public void testToShort()
    {
        ConversionUtils t_ConversionUtils =
            ConversionUtils.getInstance();

        assertNotNull(t_ConversionUtils);

        assertTrue(t_ConversionUtils.toShort("2") == 2);
        assertTrue(t_ConversionUtils.toShort("-2") == -2);
        assertTrue(t_ConversionUtils.toShort("something else") == 0);
    }

    /**
     * Tests the ConversionUtils.toByte(String) method.
     * @see ConversionUtils#toByte(String)
     */
    public void testToByte()
    {
        ConversionUtils t_ConversionUtils =
            ConversionUtils.getInstance();

        assertNotNull(t_ConversionUtils);

        assertTrue(t_ConversionUtils.toByte("2") == 2);
        assertTrue(t_ConversionUtils.toByte("-2") == -2);
        assertTrue(t_ConversionUtils.toByte("something else") == 0);
    }

    /**
     * Tests the ConversionUtils.toBigDecimal(String) method.
     * @see ConversionUtils#toBigDecimal(String)
     */
    public void testToBigDecimal()
    {
        ConversionUtils t_ConversionUtils =
            ConversionUtils.getInstance();

        assertNotNull(t_ConversionUtils);

        BigDecimal t_BigDecimal = t_ConversionUtils.toBigDecimal("2");
        assertNotNull(t_BigDecimal);
        assertTrue(
            t_BigDecimal.divide(
                new BigDecimal(1.0),
                1,
                BigDecimal.ROUND_HALF_EVEN).doubleValue()
            == 2.0);
    }
}
