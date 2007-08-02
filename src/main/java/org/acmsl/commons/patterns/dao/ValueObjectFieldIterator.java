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
 * Description: Knows how to iterate over ValueObjectField elements.
 *
 * Last modified by: $Author: chous $ at $Date: 2004-10-06 08:00:18 +0200 (Wed, 06 Oct 2004) $
 *
 * File version: $Revision: 419 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: ValueObjectFieldIterator.java 419 2004-10-06 06:00:18Z chous $
 *
 */
package org.acmsl.commons.patterns.dao;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.dao.ValueObjectField;

/**
 * Knows how to iterate over ValueObjectField elements.
 * @stereotype Iterator
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision: 419 $
 */
public interface ValueObjectFieldIterator
{
    /**
     * Checks whether there's more fields to browse or not.
     * @return true if there are more fields to browse.
     */
    public boolean hasNext();

    /**
     * Retrieves the next field to browse.
     * @return the next field.
     */
    public ValueObjectField next();
}
