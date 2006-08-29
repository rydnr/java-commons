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
 * Description: Models runtime-malformed regular expressions.
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
package org.acmsl.commons.regexpplugin;

/*
 * Importing some JDK classes.
 */
import java.io.Serializable;
import java.lang.RuntimeException;

/**
 * Models runtime-malformed regular expressions.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class MalformedPatternException
    extends     RuntimeException
    implements  Serializable
{
    /**
     * Just constructs the exception with the default logic defined in its
     * super class.
     */
    public MalformedPatternException()  {};

    /**
     * Constructs the exception with given message.
     * @param message the error message.
     * @precondition message != null
     */
    public MalformedPatternException(final String message)
    {
        super(message);
    }

    /**
     * Constructs the exception with given message.
     * @param message the error message.
     * @param cause the cause.
     * @precondition message != null
     */
    public MalformedPatternException(
        final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
