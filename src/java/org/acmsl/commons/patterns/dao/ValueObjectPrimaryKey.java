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
 * Description: Represents primary keys that uniquely identifies each
 *              value object.
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
import org.acmsl.commons.patterns.dao.ValueObjectField;
import org.acmsl.commons.patterns.dao.ValueObjectFieldIterator;
import org.acmsl.commons.version.Version;
import org.acmsl.commons.version.Versionable;
import org.acmsl.commons.version.VersionFactory;

/**
 * Importing some JDK classes.
 */
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents primary keys that uniquely identifies each value object.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class ValueObjectPrimaryKey
    implements  Versionable
{
    /**
     * Field collection.
     */
    private ArrayList m__alFields = new ArrayList();

    /**
     * Adds a field to this primary key.
     * @param field the field to add.
     */
    protected void add(final ValueObjectField field)
    {
        m__alFields.add(field);
    }

    /**
     * Retrieves a list of all the fields included in the PK.
     * @return the iterator to browse the list.
     */
    public ValueObjectFieldIterator iterator()
    {
        return new _FieldIterator(m__alFields.iterator());
    }

    /**
     * Generic ValueObjectFieldIterator implementation.
     * @author <a href="mailto:jsanleandro@yahoo.es"
               >Jose San Leandro Armendáriz</a>
     * @version $Revision$
     */
    private static class _FieldIterator
        implements  ValueObjectFieldIterator
    {
        Iterator m__Iterator;

        /**
         * Constructs an iterator using given generic iterator.
         * @param iterator the actual iterator.
         */
        public _FieldIterator(Iterator iterator)
        {
            m__Iterator = iterator;
        }

        /**
         * Checks whether there's more fields to browse or not.
         * @return true if there are more fields to browse.
         */
        public boolean hasNext()
        {
            return m__Iterator.hasNext();
        }

        /**
         * Retrieves the next field to browse.
         * @return the next field.
         */
        public ValueObjectField next()
        {
            return (ValueObjectField) m__Iterator.next();
        }

        /**
         * Retrieves the version.
         * @return the current version.
         */
        public Version getVersion()
        {
            return this.VERSION;
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
