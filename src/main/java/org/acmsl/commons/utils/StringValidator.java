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
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-307  USA


    Thanks to ACM S.L. for distributing this library under the GPL license.
    Contact info: jsr000@terra.es
    Postal Address: c/Playa de Lagoa, 1
                    Urb. Valdecabañas
                    Boadilla del monte

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armendáriz
 *
 * Description: Responsible of checking the validness of strings.
 *
 * Last modified by: $Author: chous $ at $Date: 2006-06-14 21:01:54 +0200 (Wed, 14 Jun 2006) $
 *
 * File version: $Revision: 550 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: StringValidator.java 550 2006-06-14 19:01:54Z chous $
 *
 */
package org.acmsl.commons.utils;

/*
 * Importing some ACM-SL classes
 */
import org.acmsl.commons.patterns.Singleton;
import org.acmsl.commons.patterns.Validator;

/**
 * Responsible of checking the validness of strings.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @stereotype validator
 * @version $Revision: 550 $
 */
public class StringValidator
    implements  Validator,
                Singleton
{
    /**
     * Singleton implemented to avoid the double-checked locking.
     */
    private static class StringValidatorSingletonContainer
    {
        /**
         * The actual singleton.
         */
        public static final StringValidator SINGLETON = new StringValidator();
    }

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected StringValidator() {};

    /**
     * Retrieves a StringValidator instance.
     * @return such instance.
     */
    public static StringValidator getInstance()
    {
        return StringValidatorSingletonContainer.SINGLETON;
    }

    /**
     * Checks the emptyness of given string.
     * @param value the string to be checked.
     * @return true whenever given value is empty or null.
     */
    public boolean isEmpty(final String value)
    {
        return
            (   (value == null)
             || (value.trim().length() == 0));
    }
}
