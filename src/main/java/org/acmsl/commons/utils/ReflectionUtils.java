/*
                        ACM-SL Commons

    Copyright (C) 2002-2005  Jose San Leandro Armend&aacute;riz
                             chous@acm-sl.org

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
    Contact info: jose.sanleandro@acm-sl.org
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
 * Importing project classes.
 */
import org.acmsl.commons.patterns.Singleton;
import org.acmsl.commons.patterns.Utils;

/*
 * Importing Commons-Logging classes.
 */
import org.apache.commons.logging.LogFactory;

/*
 * Importing some JDK classes.
 */
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Provides some useful methods when working with reflection.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro</a>
 * @version $Revision$ at $Date$
 */
public class ReflectionUtils
    implements  Utils,
                Singleton
{
    /**
     * A cached empty class array.
     */
    public static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
    
    /**
     * A cached empty field array.
     */
    public static final Field[] EMPTY_FIELD_ARRAY = new Field[0];
    
    /**
     * Singleton implemented to avoid the double-checked locking.
     */
    private static class ReflectionUtilsSingletonContainer
    {
        /**
         * The actual singleton.
         */
        public static final ReflectionUtils SINGLETON =
            new ReflectionUtils();
    }

    /**
     * Default protected constructor to avoid accidental instantiation.
     */
    protected ReflectionUtils() {};

    /**
     * Retrieves a ReflectionUtils instance.
     * @return such instance.
     */
    public static ReflectionUtils getInstance()
    {
        return ReflectionUtilsSingletonContainer.SINGLETON;
    }

    /**
     * Retrieves the parent classes of given object.
     * @param object the object to analyze.
     * @return the ordered collection of superclasses.
     * @precondition object != null
     */
    public Class[] retrieveSuperClasses(final Object object)
    {
        return retrieveSuperClasses(object.getClass());
    }
    
    /**
     * Retrieves the parent classes of given class.
     * @param classInstance the class to analyze.
     * @return the ordered collection of superclasses.
     * @precondition classInstance != null
     */
    public Class[] retrieveSuperClasses(final Class classInstance)
    {
        Class[] result = EMPTY_CLASS_ARRAY;

        Collection t_cSuperClasses = new ArrayList();

        t_cSuperClasses.add(classInstance);

        retrieveSuperClasses(classInstance, t_cSuperClasses);

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

    /**
     * Retrieves the members (not only public ones)
     * matching a concrete type, declared in given class.
     * This method will likely fail under a security manager
     * without explicit permissions.
     * @param classInstance the class instance.
     * @param type the type to match.
     * @return such member instance.
     * @precondition classInstance != null
     * @precondition type != null
     */
    public Field[] getMember(final Class classInstance, final Class type)
    {
        Collection t_cResult = new ArrayList();

        Class[] t_aClasses = retrieveSuperClasses(classInstance);
        
        int t_iLength = (t_aClasses != null) ? t_aClasses.length : 0;
        
        for  (int t_iIndex = 0; t_iIndex < t_iLength; t_iIndex++)
        {
            t_cResult.addAll(
                getClassMembersAsCollection(t_aClasses[t_iIndex], type));
        }
        
        return (Field[]) t_cResult.toArray(EMPTY_FIELD_ARRAY);
    }
    
    /**
     * Retrieves the members (not only public ones)
     * matching a concrete type, declared in given class.
     * This method will likely fail under a security manager
     * without explicit permissions.
     * @param classInstance the class instance.
     * @param type the type to match.
     * @return such member instance.
     * @precondition classInstance != null
     * @precondition type != null
     */
    public Field[] getClassMembers(final Class classInstance, final Class type)
    {
        Field[] result = EMPTY_FIELD_ARRAY;
        
        Collection t_cMembers =
            getClassMembersAsCollection(classInstance, type);
        
        if  (t_cMembers != null)
        {
            result = (Field[]) t_cMembers.toArray(EMPTY_FIELD_ARRAY);
        }
        
        return result;
    }
    
    /**
     * Retrieves the members (not only public ones)
     * matching a concrete type, declared in given class.
     * This method will likely fail under a security manager
     * without explicit permissions.
     * @param classInstance the class instance.
     * @param type the type to match.
     * @return such fields.
     * @precondition classInstance != null
     * @precondition type != null
     */
    public Collection getClassMembersAsCollection(
        final Class classInstance, final Class type)
    {
        Collection result = new ArrayList();

        Field[] t_Aux = null;
        
        try
        {
            t_Aux = classInstance.getDeclaredFields();
        }
        catch  (final Throwable throwable)
        {
            try
            {
                LogFactory.getLog(ReflectionUtils.class).warn(
                    "Cannot retrieve " + classInstance + " members",
                    throwable);
            }
            catch  (final Throwable classLoadingProblem)
            {
                System.err.println(
                        "Error using Commons-Logging. This can happen "
                      + "due to Log4J class-loading issues.");

                classLoadingProblem.printStackTrace(System.err);
            }
        }

        List t_lMembers = Arrays.asList(t_Aux);

        int t_iLength = (t_Aux != null) ? t_Aux.length : 0;

        Field t_CurrentField = null;
        
        Class t_CurrentMemberClass = null;
        
        for  (int t_iIndex = 0; t_iIndex < t_iLength; t_iIndex++)
        {
            t_CurrentField = t_Aux[t_iIndex];

            if  (t_CurrentField != null)
            {
                t_CurrentMemberClass = t_CurrentField.getType();

                if  (   (t_CurrentMemberClass != null)
                     && (   (t_CurrentMemberClass.equals(type)
                         || (type.isAssignableFrom(t_CurrentMemberClass))
                         || (t_CurrentMemberClass.isAssignableFrom(type)))))
                {
                    result.add(t_CurrentField);
                }
            }
        }

        return result;
    }

    /**
     * Retrieves the value of given field, for a concrete instance.
     * @param instance the instance.
     * @param field the field.
     * @return the field value, on given instance, or <code>null</code>
     * if such field doesn't exist or is not accessible.
     * @precondition instance != null
     * @precondition field != null
     */
    public Object getFieldValue(final Object instance, final Field field)
    {
        Object result = null;
        
        try
        {
            result = field.get(instance);
        }
        catch  (final Throwable throwable)
        {
            try
            {
                LogFactory.getLog(ReflectionUtils.class).info(
                      "Cannot retrieve the value of "
                    + "the field " + field
                    + " on instance " + instance,
                      throwable);
            }
            catch  (final Throwable classLoadingProblem)
            {
                System.err.println(
                        "Error using Commons-Logging. This can happen "
                      + "due to Log4J class-loading issues.");

                classLoadingProblem.printStackTrace(System.err);
            }
        }
        
        return result;
    }

    /**
     * Returns the thread context class loader if available.
     * The thread context class loader is available for JDK 1.2
     * or later, if certain security conditions are met.
     * Note: This logic is adapted from Commons-Logging.
     * @return the class loader.
     * @throws IllegalAccessException when trying to access
     * <code>Thread.getContextClassLoader()</code> via reflection.
     * @throws InvocationTargetException when trying to access
     * <code>Thread.getContextClassLoader()</code> via reflection, and
     * the target exception is not a <code>SecurityException</code>..
     */
    public ClassLoader getContextClassLoader()
        throws IllegalAccessException,
               InvocationTargetException
    {
        ClassLoader result = null;
        
        try
        {
            // Are we running on a JDK 1.2 or later system?
            Method t_Method =
                Thread.class.getMethod("getContextClassLoader", null);

            // Get the thread context class loader (if there is one)
            try
            {
                result =
                    (ClassLoader) t_Method.invoke(Thread.currentThread(), null);
            }
            catch  (final IllegalAccessException illegalAccessException)
            {
                throw illegalAccessException;
            }
            catch  (final InvocationTargetException invocationTargetException)
            {
                /**
                 * InvocationTargetException is thrown by 'invoke' when
                 * the method being invoked (getContextClassLoader) throws
                 * an exception.
                 *
                 * getContextClassLoader() throws SecurityException when
                 * the context class loader isn't an ancestor of the
                 * calling class's class loader, or if security
                 * permissions are restricted.
                 *
                 * In the first case (not related), we want to ignore and
                 * keep going.  We cannot help but also ignore the second
                 * with the logic below, but other calls elsewhere (to
                 * obtain a class loader) will trigger this exception where
                 * we can make a distinction.
                 */
                if  (invocationTargetException.getTargetException()
                     instanceof SecurityException)
                {
                    LogFactory.getLog(ReflectionUtils.class).info(
                        "Could not retrieve context class loader.",
                        invocationTargetException);
                }
                else
                {
                    throw invocationTargetException;
                }
            }
        }
        catch  (final NoSuchMethodException noSuchMethodException)
        {
            // Assume we are running on JDK 1.1
            result = ReflectionUtils.class.getClassLoader();
        }

        // Return the selected class loader
        return result;
    }
}
