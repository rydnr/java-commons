//;-*- mode: java -*-
/*
                        ACM-SL Commons

    Copyright (C) 2002-today  Jose San Leandro Armendariz
                              chous@acm-sl.org

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

    Thanks to ACM S.L. for distributing this library under the LGPL license.
    Contact info: jose.sanleandro@acm-sl.com

 ******************************************************************************
 *
 * Filename: ValueObjectField.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Represents any information stored inside a value object.
 *
 */
package org.acmsl.commons.patterns.dao;

/**
 * Represents any information stored inside a {@link ValueObject}.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class ValueObjectField
{
    /**
     * Field name.
     */
    private java.lang.String m__strName;

    /**
     * Formatter.
     */
    private ValueObjectFieldFormatter m__Formatter;

    /**
     * Constructs a field using given name.
     * @param name the name of the field.
     * @param formatter the entity able to format this field correctly.
     */
    public ValueObjectField(
        final java.lang.String name,
        final ValueObjectFieldFormatter formatter)
    {
        immutableSetName(name);

        immutableSetFormatter(formatter);
    }

    /**
     * Sets field's name.
     * @param name the name of the field.
     */
    private void immutableSetName(java.lang.String name)
    {
        m__strName = name;
    }

    /**
     * Sets field's name.
     * @param name the name of the field.
     */
    protected void setName(java.lang.String name)
    {
        immutableSetName(name);
    }

    /**
     * Retrieves field's name.
     * @return the name of the field.
     */
    public java.lang.String getName()
    {
        return m__strName;
    }

    /**
     * Sets the formatter.
     * @param formatter the formatter itself.
     */
    private void immutableSetFormatter(
        final ValueObjectFieldFormatter formatter)
    {
        m__Formatter = formatter;
    }

    /**
     * Sets the formatter.
     * @param formatter the formatter itself.
     */
    protected void setFormatter(
        final ValueObjectFieldFormatter formatter)
    {
        immutableSetFormatter(formatter);
    }

    /**
     * Retrieves the formatter.
     * @return this field's associated foramtter.
     */
    public ValueObjectFieldFormatter getFormatter()
    {
        return m__Formatter;
    }

    /**
     * Formats the field in a correct way.
     * @return this field in String format.
     */
    public java.lang.String toString()
    {
        return toString(getFormatter());
    }

    /**
     * Formats the field in a correct way.
     * @param formatter the formatter.
     * @return this field in String format.
     * @precondition formatter != null
     */
    protected java.lang.String toString(final ValueObjectFieldFormatter formatter)
    {
        return formatter.format(this);
    }

    /**
     * Represents int fields.
     */
    public static class Int
        extends ValueObjectField
    {
        /**
         * Field value.
         */
        private int m__iValue;

        /**
         * Constructs a field using given name.
         * @param name the name of the field.
         */
        public Int(final java.lang.String name, final int value)
        {
            super(name, IntFormatter.getInstance());
            immutableSetValue(value);
        }

        /**
         * Sets the value.
         * @param value the value
         */
        private void immutableSetValue(final int value)
        {
            m__iValue = value;
        }

        /**
         * Sets the value.
         * @param value the value
         */
        protected void setValue(final int value)
        {
            immutableSetValue(value);
        }

        /**
         * Retrieves the value.
         * @return field's value.
         */
        public int getValue()
        {
            return m__iValue;
        }
    }

    /**
     * Represents String fields.
     * @author <a href="mailto:jsanleandro@yahoo.es"
               >Jose San Leandro Armend�riz</a>
     * @version $Revision: 419 $
     */
    public static class String
        extends  ValueObjectField
    {
        /**
         * Field value.
         */
        private java.lang.String m__strValue;

        /**
         * Constructs a field using given name and value.
         * @param name the name of the field.
         * @param value field's value.
         */
        public String(
            final java.lang.String name, final java.lang.String value)
        {
            super(name, StringFormatter.getInstance());

            immutableSetValue(value);
        }

        /**
         * Sets the value.
         * @param value the value
         */
        private void immutableSetValue(final java.lang.String value)
        {
            m__strValue = value;
        }

        /**
         * Sets the value.
         * @param value the value
         */
        protected void setValue(final java.lang.String value)
        {
            immutableSetValue(value);
        }

        /**
         * Retrieves the value.
         * @return field's value.
         */
        public java.lang.String getValue()
        {
            return m__strValue;
        }
    }

    /**
     * Represents long fields.
     * @author <a href="mailto:jsanleandro@yahoo.es"
               >Jose San Leandro Armend�riz</a>
     * @version $Revision: 419 $
     */
    public static class Long
        extends  ValueObjectField
    {
        /**
         * Field value.
         */
        private long m__lValue;

        /**
         * Constructs a field using given name and value.
         * @param name the name of the field.
         * @param value field's value.
         */
        public Long(final java.lang.String name, final long value)
        {
            super(name, LongFormatter.getInstance());
            immutableSetValue(value);
        }

        /**
         * Sets the value.
         * @param value the value
         */
        private void immutableSetValue(final long value)
        {
            m__lValue = value;
        }

        /**
         * Sets the value.
         * @param value the value
         */
        protected void setValue(final long value)
        {
            immutableSetValue(value);
        }

        /**
         * Retrieves the value.
         * @return field's value.
         */
        public long getValue()
        {
            return m__lValue;
        }
    }
}
