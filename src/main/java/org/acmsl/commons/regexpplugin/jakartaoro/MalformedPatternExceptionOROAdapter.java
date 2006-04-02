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
package org.acmsl.commons.regexpplugin.jakartaoro;

/*
 * Importing Jakarta-ORO classes.
 */
import org.apache.oro.text.regex.MalformedPatternException;

/**
 * Adapts jakarta ORO malformed pattern exceptions to follow this API.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class MalformedPatternExceptionOROAdapter
    extends  org.acmsl.commons.regexpplugin.MalformedPatternException
{
    /**
     * Private reference to the actual exception.
     */
    private MalformedPatternException m__Adaptee;

    /**
     * Constructs an adapter for given ORO exception.
     * @param exception concrete exception instance to adapt.
     */
    public MalformedPatternExceptionOROAdapter(
        final MalformedPatternException exception)
    {
        super(((exception == null) ? "" : exception.getMessage()));

        immutableSetMalformedPatternException(exception);
    }

    /**
     * Specifies the adaptee.
     * @param adaptee the exception to adapt.
     */
    private void immutableSetMalformedPatternException(
        final MalformedPatternException adaptee)
    {
        m__Adaptee = adaptee;
    }

    /**
     * Specifies the adaptee.
     * @param adaptee the exception to adapt.
     */
    protected void setMalformedPatternException(
        final MalformedPatternException adaptee)
    {
        immutableSetMalformedPatternException(adaptee);
    }

    /**
     * Retrieves the adapted exception.
     * @return such exception.
     */
    public MalformedPatternException getMalformedPatternException()
    {
        return m__Adaptee;
    }
}
