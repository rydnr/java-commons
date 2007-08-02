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
 * Last modified by: $Author: chous $ at $Date: 2004-10-06 08:00:18 +0200 (Wed, 06 Oct 2004) $
 *
 * File version: $Revision: 419 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: ValueObjectPrimaryKey.java 419 2004-10-06 06:00:18Z chous $
 *
 */
package org.acmsl.commons.patterns.dao;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.dao.ValueObjectField;
import org.acmsl.commons.patterns.dao.ValueObjectFieldIterator;

/**
 * Importing some JDK classes.
 */
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents primary keys that uniquely identifies each value object.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision: 419 $
 */
public class ValueObjectPrimaryKey
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
     * @version $Revision: 419 $
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
    }
}
