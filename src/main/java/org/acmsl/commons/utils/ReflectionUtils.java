//;-*- mode: java -*-
/*
                        ACM-SL Commons

    Copyright (C) 2002-today  Jose San Leandro Armend&aacute;riz
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
    Contact info: jose.sanleandro@acm-sl.com

 ******************************************************************************
 *
 * Filename: ReflectionUtils.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Provides some useful methods when working with reflection.
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Importing some JDK classes.
 */
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Provides some useful methods when working with reflection.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class ReflectionUtils
    implements  Utils,
                Singleton
{
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
        @NotNull public static final ReflectionUtils SINGLETON =
            new ReflectionUtils();
    }

    /**
     * Default protected constructor to avoid accidental instantiation.
     */
    protected ReflectionUtils() {}

    /**
     * Retrieves a ReflectionUtils instance.
     * @return such instance.
     */
    @NotNull
    public static ReflectionUtils getInstance()
    {
        return ReflectionUtilsSingletonContainer.SINGLETON;
    }

    /**
     * Retrieves the parent classes of given object.
     * @param object the object to analyze.
     * @return the ordered collection of superclasses.
     */
    @NotNull
    public Class[] retrieveSuperClasses(@NotNull final Object object)
    {
        return retrieveSuperClasses(object.getClass());
    }
    
    /**
     * Retrieves the parent classes of given class.
     * @param classInstance the class to analyze.
     * @return the ordered collection of superclasses.
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public Class[] retrieveSuperClasses(@NotNull final Class classInstance)
    {
        @NotNull final Class[] result;

        @NotNull final Collection<Class> t_cSuperClasses = new ArrayList<Class>();

        t_cSuperClasses.add(classInstance);

        t_cSuperClasses.addAll(retrieveParentClasses(classInstance));

        result = t_cSuperClasses.toArray(new Class[t_cSuperClasses.size()]);

        return result;
    }

    /**
     * Recursively retrieves the parent classes.
     * @param objectClass the object class.
     */
    @NotNull
    protected Collection<Class> retrieveParentClasses(@NotNull final Class objectClass)
    {
        @NotNull final Collection<Class> result;

        @Nullable final Class parent = objectClass.getSuperclass();

        if  (parent != null)
        {
            result = new ArrayList<Class>();

            result.add(parent);

            result.addAll(retrieveParentClasses(parent));
        }
        else
        {
            result = new ArrayList<Class>(0);
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
     * @return such member instance.
     */
    @NotNull
    public Field[] getMember(@NotNull final Class classInstance, @NotNull final Class type)
    {
        @NotNull final Collection<Field> t_cResult = new ArrayList<Field>();

        @NotNull final Class[] t_aClasses = retrieveSuperClasses(classInstance);
        
        for  (@NotNull final Class t_Class : t_aClasses)
        {
            t_cResult.addAll(getClassMembersAsCollection(t_Class, type));
        }
        
        return t_cResult.toArray(new Field[t_cResult.size()]);
    }
    
    /**
     * Retrieves the members (not only public ones)
     * matching a concrete type, declared in given class.
     * This method will likely fail under a security manager
     * without explicit permissions.
     * @param classInstance the class instance.
     * @param type the type to match.
     * @return such member instance.
     */
    @NotNull
    public Field[] getClassMembers(@NotNull final Class classInstance, @NotNull final Class type)
    {
        @NotNull final Field[] result;
        
        @NotNull final Collection<Field> t_cMembers =
            getClassMembersAsCollection(classInstance, type);
        
        if  (t_cMembers.size() == 0)
        {
            result = t_cMembers.toArray(new Field[t_cMembers.size()]);
        }
        else
        {
            result = EMPTY_FIELD_ARRAY;
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
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public Collection<Field> getClassMembersAsCollection(
        @NotNull final Class classInstance, @NotNull final Class type)
    {
        @NotNull final Collection<Field> result = new ArrayList<Field>();

        @Nullable Field[] t_Aux = null;
        
        try
        {
            t_Aux = classInstance.getDeclaredFields();
        }
        catch  (@NotNull final Throwable throwable)
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

        if (t_Aux != null)
        {
            for (@Nullable final Field t_CurrentField : t_Aux)
            {
                if  (t_CurrentField != null)
                {
                    @Nullable final Class t_CurrentMemberClass = t_CurrentField.getType();

                    if  (   (t_CurrentMemberClass != null)
                         && (   (t_CurrentMemberClass.equals(type)
                             || (type.isAssignableFrom(t_CurrentMemberClass))
                             || (t_CurrentMemberClass.isAssignableFrom(type)))))
                    {
                        result.add(t_CurrentField);
                    }
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
     */
    @Nullable
    public Object getFieldValue(@NotNull final Object instance, @NotNull final Field field)
    {
        @Nullable Object result = null;
        
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
    @NotNull
    public ClassLoader getContextClassLoader()
        throws IllegalAccessException,
               InvocationTargetException
    {
        @Nullable ClassLoader result = null;
        
        try
        {
            // Are we running on a JDK 1.2 or later system?
            @NotNull final Method t_Method =
                Thread.class.getMethod("getContextClassLoader", (Class<?>) null);

            // Get the thread context class loader (if there is one)
            try
            {
                result =
                    (ClassLoader) t_Method.invoke(Thread.currentThread(), (Class<?>) null);
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
        if (result == null)
        {
            result = ReflectionUtils.class.getClassLoader();
        }

        // Return the selected class loader
        return result;
    }
}
