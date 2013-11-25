/*
                        ACM-SL Java Commons

    Copyright (C) 2002-today  Jose San Leandro Armendariz
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
 * Filename: ToStringUtils.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Provides logic to generate valid, complete toString()
 *              representations of objects.
 *
 * Date: 2013/11/22
 * Time: 07:00
 *
 */
package org.acmsl.commons.utils;

/*
 * Importing ACM-SL Java Commons classes.
 */
import org.acmsl.commons.patterns.Utils;

/*
 * Importing JetBrains annotations.
 */
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/*
 * Importing checkthread.org annotations.
 */
import org.checkthread.annotations.ThreadSafe;

/*
 * Importing StringTemplate classes.
 */
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/*
 * Importing JDK classes.
 */
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides logic to generate valid, complete toString() representations of objects.
 * @author <a href="mailto:queryj@acm-sl.org">Jose San Leandro</a>
 * @since 0.7.1
 * Created: 2013/11/22 07:00
 */
@ThreadSafe
public class ToStringUtils
    implements Utils
{
    /**
     * The path to the actual ST template.
     */
    public static final String TO_JSON = "org/acmsl/commons/utils/ToStringUtilsJSON.stg";

    /**
     * The ST template.
     */
    protected static final String ST_TEMPLATE = "toString";

    /**
     * Singleton implemented to avoid the double-checked locking.
     */
    private static class ToStringUtilsSingletonContainer
    {
        /**
         * The actual singleton.
         */
        @NotNull public static final ToStringUtils SINGLETON = new ToStringUtils();
    }

    /**
     * Retrieves the singleton instance.
     * @return such instance.
     */
    @NotNull
    public static ToStringUtils getInstance()
    {
        return ToStringUtilsSingletonContainer.SINGLETON;
    }

    /**
     * Generates a JSON-formatted string representation of given instance.
     * @param instance the instance.
     * @param fileClass the file class.
     * @param args the instance fields.
     * @return the JSON representation.
     */
    @NotNull
    public <T> String toJson(
        @NotNull final T instance, @NotNull final Class fileClass, @NotNull final Map<String, Object> args)
    {
        @NotNull final STGroup stGroup = new STGroupFile(TO_JSON);

        @NotNull final ST template = stGroup.getInstanceOf(ST_TEMPLATE);

        @NotNull final Map<String, Object> context = new HashMap<String, Object>(3);

        context.put("obj", instance);
        context.put("fileClass", fileClass);
        context.put("attrs", decorate(args));

        template.add("C", context);

        @NotNull final String result = template.render();

        return result;
    }

    /**
     * Decorates given arguments.
     * @param args the arguments.
     * @return the map of decorations.
     */
    protected Map<String, Object> decorate(@NotNull final Map<String, Object> args)
    {
        @NotNull final Map<String, Object> result = new HashMap<String, Object>(args.size());

        for (@NotNull final Map.Entry<String, Object> arg : args.entrySet())
        {
            @Nullable final Object value = arg.getValue();

            if (value != null)
            {
                result.put(arg.getKey(), decorateElement(arg.getKey(), value));
            }
        }

        return result;
    }

    /**
     * Decorates given element.
     * @param name the element name.
     * @param arg the element value.
     * @return the decorated instance.
     */
    protected Object decorateElement(@NotNull final String name, @NotNull final Object arg)
    {
        @NotNull final Object result;

        if (arg instanceof String)
        {
            result = new Decorator<String>(name, (String) arg);
        }
        else if (arg instanceof Boolean)
        {
            result = new Decorator<Boolean>(name, (Boolean) arg);
        }
        else if (arg instanceof Integer)
        {
            result = new Decorator<Integer>(name, (Integer) arg);
        }
        else if (arg instanceof Long)
        {
            result = new Decorator<Long>(name, (Long) arg);
        }
        else if (arg instanceof Float)
        {
            result = new Decorator<Float>(name, (Float) arg);
        }
        else if (arg instanceof Double)
        {
            result = new Decorator<Double>(name, (Double) arg);
        }
        else if (arg instanceof Date)
        {
            result = new DateDecorator(name, (Date) arg);
        }
        else
        {
            result = new Decorator<Object>(name, arg);
        }

        return result;
    }

    /**
     * Decorates values to properly serialize them as JSON.
     * @param <T> the type of the wrapped value.
     */
    protected static class Decorator<T>
    {
        /**
         * The name.
         */
        @NotNull private final String name;

        /**
         * The wrapped value.
         */
        @NotNull private final T arg;

        /**
         * Creates a decorator.
         * @param name the name.
         * @param arg the value to wrap.
         */
        public Decorator(@NotNull final String name, @NotNull final T arg)
        {
            this.name = name;
            this.arg = arg;
        }

        /**
         * Indicates the value is decorated.
         * @return {@code true}.
         */
        @SuppressWarnings("unused")
        public boolean isRepresentedAsString()
        {
            return !isPrimitive();
        }

        /**
         * Retrieves the name.
         * @return such information.
         */
        @NotNull
        public String getName()
        {
            return this.name;
        }

        /**
         * Retrieves the wrapped value.
         * @return such value.
         */
        @NotNull
        protected T getArg()
        {
            return this.arg;
        }

        /**
         * Retrieves the wrapped value.
         * @return such value.
         */
        @NotNull
        public String getValue()
        {
            return getArg().toString();
        }

        /**
         * Retrieves the file class.
         * @return such class.
         */
        @SuppressWarnings("unused")
        public Class getFileClass()
        {
            return this.arg.getClass();
        }

        /**
         * Checks whether the wrapped value is an array.
         * @return {@code true} in such case.
         */
        public boolean isArray()
        {
            return (this.arg instanceof Object[]);
        }

        /**
         * Checks whether the wrapped instance is compound.
         * @return {@code true} in such case.
         */
        @SuppressWarnings("unused")
        public boolean isCompound()
        {
            return
                (   !(isPrimitive())
                 && !(this.arg instanceof String));
        }

        /**
         * Checks whether the decorator wraps a primitive value.
         * @return {@code true} in such case.
         */
        public boolean isPrimitive()
        {
            final boolean result;

            if (   (this.arg instanceof Boolean)
                || (this.arg instanceof Integer)
                || (this.arg instanceof Long)
                || (this.arg instanceof Float)
                || (this.arg instanceof Double)
                || (this.arg instanceof BigInteger)
                || (this.arg instanceof BigDecimal))
            {
                result = true;
            }
            else
            {
                result = false;
            }

            return result;
        }

        /**
         * Generates a JSON text representing the instance.
         * @return such text.
         */
        @Override
        public String toString()
        {
            @NotNull final String result;

            if (isCompound())
            {
                result = toStringCompound();
            }
            else
            {
                result = toStringSimple();
            }

            return result;
        }

        /**
         * Generates a JSON text representing the instance.
         * @return such text.
         */
        protected String toStringSimple()
        {
            @NotNull final STGroup stGroup = new STGroupFile(TO_JSON);

            @NotNull final ST template = stGroup.getInstanceOf("toJson");

            template.add("name", this.name);
            template.add("attr", this.arg);

            @NotNull final String result = template.render();

            return result;
        }

        /**
         * Generates a JSON text representing the instance.
         * @return such text.
         */
        protected String toStringCompound()
        {
            return getValue();
        }
    }

    /**
     * A date decorator.
     */
    private static class DateDecorator
        extends Decorator<Date>
    {
        /**
         * Creates a date decorator.
         * @param name the name.
         * @param arg the date value.
         */
        public DateDecorator(@NotNull final String name, @NotNull final Date arg)
        {
            super(name, arg);
        }

        /**
         * Checks whether the wrapped instance is compound.
         * @return {@code true} in such case.
         */
        @Override
        @SuppressWarnings("unused")
        public boolean isCompound()
        {
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isRepresentedAsString()
        {
            return true;
        }

        /**
         * Retrieves the date, in String format.
         * @return the date.
         */
        @NotNull
        @Override
        public String getValue()
        {
            return new SimpleDateFormat("YYYY-MM-ddHH:mm:ss.SSSZ").format(getArg());
        }
    }
}