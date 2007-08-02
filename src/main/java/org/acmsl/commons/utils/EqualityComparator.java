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
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-307  USA


    Thanks to ACM S.L. for distributing this library under the GPL license.
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
 * Description: Responsible of checking the equality of values or objects.
 *
 * Last modified by: $Author: chous $ at $Date: 2006-06-14 21:01:54 +0200 (Wed, 14 Jun 2006) $
 *
 * File version: $Revision: 550 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: EqualityComparator.java 550 2006-06-14 19:01:54Z chous $
 *
 */
package org.acmsl.commons.utils;

/*
 * Importing some ACM-SL classes
 */
import org.acmsl.commons.patterns.Comparator;
import org.acmsl.commons.patterns.Singleton;

/**
 * Responsible of checking the equality of values or objects.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @stereotype validator
 * @version $Revision: 550 $
 */
public class EqualityComparator
    implements  Comparator,
                Singleton
{
    /**
     * Singleton implemented to avoid the double-checked locking.
     */
    private static class EqualityComparatorSingletonContainer
    {
        /**
         * The actual singleton.
         */
        public static final EqualityComparator SINGLETON =
            new EqualityComparator();
    }

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected EqualityComparator() {};

    /**
     * Retrieves an <code>EqualityComparator</code> instance.
     * @return such instance.
     */
    public static EqualityComparator getInstance()
    {
        return EqualityComparatorSingletonContainer.SINGLETON;
    }

    /**
     * Checks if given values are equal.
     * @param first the first value to compare.
     * @param second the second value to compare.
     * @return <code>true</code> if both values are equal.
     */
    public boolean areEqual(final int first, final int second)
    {
        return (first == second);
    }

    /**
     * Checks if given values are equal.
     * @param first the first value to compare.
     * @param second the second value to compare.
     * @return <code>true</code> if both values are equal.
     */
    public boolean areEqual(final int[] first, final int[] second)
    {
        boolean result = false;

        if  (   (first != null)
             && (second != null))
        {
            result = (first.length == second.length);
        }

        if  (result)
        {
            for  (int t_iIndex = 0; t_iIndex < first.length; t_iIndex++)
            {
                result = areEqual(first[t_iIndex], second[t_iIndex]);

                if  (!result)
                {
                    break;
                }
            }
        }
        
        return result;
    }

    /**
     * Checks if given values are equal.
     * @param first the first value to compare.
     * @param second the second value to compare.
     * @return <code>true</code> if both values are equal.
     */
    public boolean areEqual(final long first, final long second)
    {
        return (first == second);
    }

    /**
     * Checks if given values are equal.
     * @param first the first value to compare.
     * @param second the second value to compare.
     * @return <code>true</code> if both values are equal.
     */
    public boolean areEqual(final long[] first, final long[] second)
    {
        boolean result = false;

        if  (   (first != null)
             && (second != null))
        {
            result = (first.length == second.length);
        }

        if  (result)
        {
            for  (int t_iIndex = 0; t_iIndex < first.length; t_iIndex++)
            {
                result = areEqual(first[t_iIndex], second[t_iIndex]);

                if  (!result)
                {
                    break;
                }
            }
        }
        
        return result;
    }

    /**
     * Checks if given values are equal.
     * @param first the first value to compare.
     * @param second the second value to compare.
     * @return <code>true</code> if both values are equal.
     */
    public boolean areEqual(final float first, final float second)
    {
        return (first == second);
    }

    /**
     * Checks if given values are equal.
     * @param first the first value to compare.
     * @param second the second value to compare.
     * @return <code>true</code> if both values are equal.
     */
    public boolean areEqual(final float[] first, final float[] second)
    {
        boolean result = false;

        if  (   (first != null)
             && (second != null))
        {
            result = (first.length == second.length);
        }

        if  (result)
        {
            for  (int t_iIndex = 0; t_iIndex < first.length; t_iIndex++)
            {
                result = areEqual(first[t_iIndex], second[t_iIndex]);

                if  (!result)
                {
                    break;
                }
            }
        }
        
        return result;
    }

    /**
     * Checks if given values are equal.
     * @param first the first value to compare.
     * @param second the second value to compare.
     * @return <code>true</code> if both values are equal.
     */
    public boolean areEqual(final double first, final double second)
    {
        return (first == second);
    }

    /**
     * Checks if given values are equal.
     * @param first the first value to compare.
     * @param second the second value to compare.
     * @return <code>true</code> if both values are equal.
     */
    public boolean areEqual(final double[] first, final double[] second)
    {
        boolean result = false;

        if  (   (first != null)
             && (second != null))
        {
            result = (first.length == second.length);
        }

        if  (result)
        {
            for  (int t_iIndex = 0; t_iIndex < first.length; t_iIndex++)
            {
                result = areEqual(first[t_iIndex], second[t_iIndex]);

                if  (!result)
                {
                    break;
                }
            }
        }
        
        return result;
    }

    /**
     * Checks if given objects are equal.
     * @param first the first value to compare.
     * @param second the second value to compare.
     * @return <code>true</code> if both values are equal.
     */
    public boolean areEqual(final Object first, final Object second)
    {
        return
            (   (first == second)
             || (   (first  != null)
                 && (second != null)
                 && (first.equals(second))
                 && (second.equals(first))));
    }

    /**
     * Checks if given values are equal.
     * @param first the first value to compare.
     * @param second the second value to compare.
     * @return <code>true</code> if both values are equal.
     */
    public boolean areEqual(final Object[] first, final Object[] second)
    {
        boolean result = false;

        if  (   (first != null)
             && (second != null))
        {
            result = (first.length == second.length);
        }

        if  (result)
        {
            for  (int t_iIndex = 0; t_iIndex < first.length; t_iIndex++)
            {
                result = areEqual(first[t_iIndex], second[t_iIndex]);

                if  (!result)
                {
                    break;
                }
            }
        }
        
        return result;
    }

}
