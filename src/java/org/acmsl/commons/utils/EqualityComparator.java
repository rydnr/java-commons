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
 * Importing some ACM-SL classes
 */
import org.acmsl.commons.patterns.Comparator;

/*
 * Importing some JDK classes.
 */
import java.lang.ref.WeakReference;

/**
 * Responsible of checking the equality of values or objects.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @stereotype validator
 * @version $Revision$
 */
public abstract class EqualityComparator
    implements  Comparator
{
    /**
     * Singleton implemented as a weak reference.
     */
    private static WeakReference singleton;

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected EqualityComparator() {};

    /**
     * Specifies a new weak reference.
     * @param comparator the comparator instance to use.
     */
    protected static void setReference(EqualityComparator comparator)
    {
        singleton = new WeakReference(comparator);
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
     * Retrieves a EqualityComparator instance.
     * @return such instance.
     */
    public static EqualityComparator getInstance()
    {
        EqualityComparator result = null;

        WeakReference reference = getReference();

        if  (reference != null) 
        {
            result = (EqualityComparator) reference.get();
        }

        if  (result == null) 
        {
            result = new EqualityComparator() {};

            setReference(result);
        }

        return result;
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
    public boolean areEqual(final double first, final double second)
    {
        return (first == second);
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
            (    (first == second)
              || (   (first  != null)
                  && (second != null)
                  && (first.equals(second))
                  && (second.equals(first))));
    }
}
