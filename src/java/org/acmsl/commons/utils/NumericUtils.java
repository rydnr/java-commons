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
 * Description:	Provides some useful methods when working with numbers.
 *
 * Last modified by: $Author$ at $Date$
 *
 * File version: $Revision$
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id$
 *
 */
package org.acmsl.commons.utils;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Utils;
import org.acmsl.commons.version.Version;
import org.acmsl.commons.version.VersionFactory;

/*
 * Importing some JDK classes.
 */
import java.lang.ref.WeakReference;
import java.util.StringTokenizer;

/**
 * Provides some useful methods when working with numbers.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @stereotype tested
 * @testcase unittests.org.acmsl.commons.utils.TestNumericUtils
 * @version $Revision$
 */
public abstract class NumericUtils
    implements  Utils
{
    /**
     * Singleton implemented as a weak reference.
     */
    private static WeakReference singleton;

    /**
     * Private constructor to avoid accidental instantiation.
     */
    private NumericUtils()  {};

    /**
     * Specifies a new weak reference.
     * @param utils the utils instance to use.
     */
    protected static void setReference(CharUtils utils)
    {
        singleton = new WeakReference(utils);
    }

    /**
     * Retrieves the weak reference.
     * @return such reference.
     */
    protected static WeakReference getReference()
    {
        return singleton;
    }

    /**
     * Retrieves a NumericUtils instance.
     * @return such instance.
     */
    public static NumericUtils getInstance()
    {
        NumericUtils result = null;

        WeakReference reference = getReference();

        if  (reference != null) 
        {
            result = (NumericUtils) reference.get();
        }

        if  (result == null) 
        {
            result = new NumericUtils() {};
        }
        
        return result;
    }

    /**
     * Checks if given object contains numeric information or not.
     * @param object the object to check if it represents a number.
     * @return true if such object actually is a number.
     */
    public boolean isNumeric(Object object)
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
    public int getMax(int[] numbers)
    {
        int result = 0;

        if  (numbers != null)
        {
            for (int t_iIndex = 0; t_iIndex < numbers.length; t_iIndex++)
            {
                result = Math.max(result, numbers[t_iIndex]);
            }
        }

        return result;
    }

    /**
     * Retrieves the maximum between a list of doubles.
     * @param numbers the collection of all numbers.
     * @result the maximum number,
     */
    public double getMax(double[] numbers)
    {
        double result = 0.0;

        if  (numbers != null)
        {
            for (int t_iIndex = 0; t_iIndex < numbers.length; t_iIndex++)
            {
                result = Math.max(result, numbers[t_iIndex]);
            }
        }

        return result;
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
     * Retrieves the current version of this class. It's defined because
     * this is a utility class that cannot be instantiated.
     * @return the object with class version information.
     */
    public static Version getClassVersion()
    {
        return VERSION;
    }
}
