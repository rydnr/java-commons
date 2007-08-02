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
 * Description: Adapts jakarta ORO malformed pattern exceptions to follow this
 *              API.
 *
 * Last modified by: $Author: chous $ at $Date: 2004-12-01 09:50:27 +0100 (Wed, 01 Dec 2004) $
 *
 * File version: $Revision: 473 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * Version: $Revision: 473 $
 *
 * $Id: MalformedPatternExceptionRegexpAdapter.java 473 2004-12-01 08:50:27Z chous $
 *
 */
package org.acmsl.commons.regexpplugin.jakartaregexp;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.regexpplugin.MalformedPatternException;

/*
 * Importing some Jakarta Regexp classes.
 */
import org.apache.regexp.RESyntaxException;

/**
 * Adapts jakarta ORO malformed pattern exceptions to follow this API.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision: 473 $
 */
public class MalformedPatternExceptionRegexpAdapter
    extends  MalformedPatternException
{
    /**
     * Private reference to the actual exception.
     */
    private RESyntaxException m__Adaptee;

    /**
     * Constructs an adapter for given Jakarta Regexp exception.
     * @param exception concrete exception instance to adapt.
     */
    public MalformedPatternExceptionRegexpAdapter(
        final RESyntaxException exception)
    {
        super(((exception == null) ? "" : exception.getMessage()));

        immutableSetRESyntaxException(exception);
    }

    /**
     * Specifies the adaptee.
     * @param exception the exception to adapt.
     */
    private void immutableSetRESyntaxException(
        final RESyntaxException exception)
    {
        m__Adaptee = exception;
    }

    /**
     * Specifies the adaptee.
     * @param exception the exception to adapt.
     */
    protected void setRESyntaxException(final RESyntaxException exception)
    {
        immutableSetRESyntaxException(exception);
    }

    /**
     * Retrieves the adapted exception.
     * @return such exception.
     */
    protected RESyntaxException getRESyntaxException()
    {
        return m__Adaptee;
    }
}
