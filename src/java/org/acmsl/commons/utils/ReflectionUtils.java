/*
                        ACM-SL Commons

    Copyright (C) 2002-2004  Jose San Leandro Armend&aacute;riz
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
                    Urb. Valdecaba&ntilde;as
                    Boadilla del monte
                    28660 Madrid
                    Spain

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armend&aacute;riz
 *
 * Description: Provides some useful methods when working with reflection.
 *
 * Last modified by: $Author$ at $Date$
 *
 * File version: $Revision$
 *
 * Project version: $Name$
 *
 * $Id$
 *
 */
package org.acmsl.commons.utils;

/*
 * Importing some JDK classes.
 */
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Provides some useful methods when working with reflection.
 * @author <a href="mailto:jose.sanleandro@ventura24.es">Jose San Leandro</a>
 * @version $Revision$
 */
public abstract class ReflectionUtils
{
    /**
     * Singleton implemented as a weak reference.
     */
    private static WeakReference singleton;

    /**
     * Default protected constructor to avoid accidental instantiation.
     */
    protected ReflectionUtils() {};

    /**
     * Specifies a new weak reference.
     * @param utils the utils instance to use.
     * @precondition utils != null
     */
    protected static void setReference(final ReflectionUtils utils)
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
     * Retrieves a ReflectionUtils instance.
     * @return such instance.
     */
    public static ReflectionUtils getInstance()
    {
        ReflectionUtils result = null;

        WeakReference reference = getReference();

        if  (reference != null) 
        {
            result = (ReflectionUtils) reference.get();
        }

        if  (result == null) 
        {
            result = new ReflectionUtils() {};

            setReference(result);
        }

        return result;
    }

    /**
     * Retrieves the parent classes of given object.
     * @param object the object to analyze.
     * @return the ordered collection of superclasses.
     * @precondition object != null
     */
    public Class[] retrieveSuperClasses(final Object object)
    {
        Class[] result = new Class[0];

        Collection t_cSuperClasses = new ArrayList();

        t_cSuperClasses.add(object.getClass());

        retrieveSuperClasses(object.getClass(), t_cSuperClasses);

        result = (Class[]) t_cSuperClasses.toArray(result);

        return result;
    }

    /**
     * Recursively retrieves the parent classes.
     * @param objectClass the object class.
     * @param collection the collection to fill with superclasses.
     * @precondition objectClass != null
     * @precondition collection != null
     */
    protected void retrieveSuperClasses(
        final Class objectClass, final Collection collection)
    {
        Class parent = objectClass.getSuperclass();

        if  (parent != null)
        {
            collection.add(parent);

            retrieveSuperClasses(parent, collection);
        }
    }
}
