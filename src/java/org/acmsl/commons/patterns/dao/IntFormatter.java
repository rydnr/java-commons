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
 * Description: Is able to format ValueObjectField.Int objects.
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
import org.acmsl.commons.patterns.dao.ValueObjectFieldFormatter;
import org.acmsl.commons.version.Version;
import org.acmsl.commons.version.VersionFactory;

/**
 * Is able to format ValueObjectField.Int objects.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class IntFormatter
    implements  ValueObjectFieldFormatter
{
    /**
     * Singleton instance.
     */
    private static IntFormatter m__Singleton;

    /**
     * Retrieves an IntFormatter instance.
     * @return a int formatter.
     */
    public static IntFormatter getInstance()
    {
        IntFormatter result = m__Singleton;

        if  (m__Singleton == null)
        {
            m__Singleton = new IntFormatter();

            result = m__Singleton;
        }

        return result;
    }

    /**
     * Formats the field in a correct way.
     * @param valueObjectField the field to format.
     * @return the String format.
     */
    public String format(ValueObjectField valueObjectField)
    {
        String result = "";

        if  (valueObjectField instanceof ValueObjectField.Int)
        {
            result = format((ValueObjectField.Int) valueObjectField);
        }

        return result;
    }

    /**
     * Formats the int field in a correct way.
     * @param intField the field to format.
     * @return the String format.
     */
    public String format(ValueObjectField.Int intField)
    {
        return intField.getValue() + "";
    }

    /**
     * Concrete version object updated everytime it's checked-in in a CVS
     * repository.
     */
    public static final Version VERSION =
        VersionFactory.createVersion("$Revision$");

    /**
     * Retrieves the current version of this object.
     * @return the version object with such information.
     */
    public Version getVersion()
    {
        return VERSION;
    }

    /**
     * Retrieves the current version of this class. It's defined because
     * this is a utility class that cannot be instantiated.
     * @return the object with class version information.
     */
    public static Version getClassVersion()
    {
        return VERSION;
    }
}
