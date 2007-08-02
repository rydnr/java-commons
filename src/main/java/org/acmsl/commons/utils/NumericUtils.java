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
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-307  USA


    Thanks to ACM S.L. for distributing this library under the LGPL license.
    Contact info: jsr000@terra.es
    Postal Address: c/Playa de Lagoa, 1
                    Urb. Valdecabañas
                    Boadilla del monte

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armendáriz
 *
 * Description: Provides some useful methods when working with numbers.
 *
 * Last modified by: $Author: chous $ at $Date: 2006-06-14 21:01:54 +0200 (Wed, 14 Jun 2006) $
 *
 * File version: $Revision: 550 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: NumericUtils.java 550 2006-06-14 19:01:54Z chous $
 *
 */
package org.acmsl.commons.utils;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Singleton;
import org.acmsl.commons.patterns.Utils;

/*
 * Importing some JDK classes.
 */
import java.util.StringTokenizer;

/**
 * Provides some useful methods when working with numbers.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @stereotype tested
 * @testcase unittests.org.acmsl.commons.utils.TestNumericUtils
 * @version $Revision: 550 $
 */
public class NumericUtils
    implements  Utils,
                Singleton
{
    /**
     * Singleton implemented to avoid the double-checked locking.
     */
    private static class NumericUtilsSingletonContainer
    {
        /**
         * The actual singleton.
         */
        public static final NumericUtils SINGLETON = new NumericUtils();
    }

    /**
     * Private constructor to avoid accidental instantiation.
     */
    private NumericUtils()  {};

    /**
     * Retrieves a NumericUtils instance.
     * @return such instance.
     */
    public static NumericUtils getInstance()
    {
        return NumericUtilsSingletonContainer.SINGLETON;
    }

    /**
     * Checks if given object contains numeric information or not.
     * @param object the object to check if it represents a number.
     * @return true if such object actually is a number.
     */
    public boolean isNumeric(final Object object)
    {
        boolean result = false;

        if  (object != null)
        {
            String t_strValue = object.toString();

            StringTokenizer t_strtokFigures =
                new StringTokenizer(t_strValue, "0123456789.,", false);

            result = !t_strtokFigures.hasMoreTokens();
        }

        return result;
    }

    /**
     * Retrieves the maximum between a list of integers.
     * @param numbers the collection of all numbers.
     * @result the maximum number,
     */
    public int getMax(final int[] numbers)
    {
        int result = 0;

        int t_iCount = (numbers != null) ? numbers.length : 0;

        for (int t_iIndex = 0; t_iIndex < t_iCount; t_iIndex++)
        {
            result = Math.max(result, numbers[t_iIndex]);
        }

        return result;
    }

    /**
     * Retrieves the maximum between a list of doubles.
     * @param numbers the collection of all numbers.
     * @result the maximum number,
     */
    public double getMax(final double[] numbers)
    {
        double result = 0.0;

        int t_iCount = (numbers != null) ? numbers.length : 0;

        for (int t_iIndex = 0; t_iIndex < t_iCount; t_iIndex++)
        {
            result = Math.max(result, numbers[t_iIndex]);
        }

        return result;
    }
}
