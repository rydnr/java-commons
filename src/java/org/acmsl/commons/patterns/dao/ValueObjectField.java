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
 * Description: Represents any information stored inside a value object.
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
package org.acmsl.commons.patterns.dao;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.dao.ValueObjectFieldFormatter;
import org.acmsl.commons.patterns.dao.IntFormatter;
import org.acmsl.commons.patterns.dao.LongFormatter;
import org.acmsl.commons.patterns.dao.StringFormatter;
import org.acmsl.commons.version.Version;
import org.acmsl.commons.version.Versionable;
import org.acmsl.commons.version.VersionFactory;

/**
 * Represents any information stored inside a value object.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class ValueObjectField
    implements  Versionable
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
        java.lang.String name,
        ValueObjectFieldFormatter formatter)
    {
        inmutableSetName(name);

        inmutableSetFormatter(formatter);
    }

    /**
     * Sets field's name.
     * @param name the name of the field.
     */
    private void inmutableSetName(java.lang.String name)
    {
        m__strName = name;
    }

    /**
     * Sets field's name.
     * @param name the name of the field.
     */
    protected void setName(java.lang.String name)
    {
        inmutableSetName(name);
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
    private void inmutableSetFormatter(ValueObjectFieldFormatter formatter)
    {
        m__Formatter = formatter;
    }

    /**
     * Sets the formatter.
     * @param formatter the formatter itself.
     */
    protected void setFormatter(ValueObjectFieldFormatter formatter)
    {
        inmutableSetFormatter(formatter);
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
        return m__Formatter.format(this);
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
        public Int(java.lang.String name, int value)
        {
            super(name, IntFormatter.getInstance());
            setValue(value);
        }

        /**
         * Sets the value.
         * @param value the value
         */
        private void setValue(int value)
        {
            m__iValue = value;
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
               >Jose San Leandro Armendáriz</a>
     * @version $Revision$
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
        public String(java.lang.String name, java.lang.String value)
        {
            super(name, StringFormatter.getInstance());

            setValue(value);
        }

        /**
         * Sets the value.
         * @param value the value
         */
        private void setValue(java.lang.String value)
        {
            m__strValue = value;
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
               >Jose San Leandro Armendáriz</a>
     * @version $Revision$
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
        public Long(java.lang.String name, long value)
        {
            super(name, LongFormatter.getInstance());
            setValue(value);
        }

        /**
         * Sets the value.
         * @param value the value
         */
        private void setValue(long value)
        {
            m__lValue = value;
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

    /**
     * Concrete version object updated everytime it's checked-in in a
     * CVS repository.
     */
    public static final Version VERSION =
        VersionFactory.createVersion("$Revision$");

    /**
     * Retrieves the version.
     * @return the current version.
     */
    public Version getVersion()
    {
        return VERSION;
    }

    /**
     * Retrieves the class version.
     * @return the current version of the class.
     */
    public static Version getClassVersion()
    {
        return VERSION;
    }
}
