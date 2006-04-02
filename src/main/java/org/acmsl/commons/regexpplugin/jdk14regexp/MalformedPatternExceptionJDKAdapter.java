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
 * Description: Adapts JDK1.4 malformed pattern exceptions to follow
 *              this API.
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
package org.acmsl.commons.regexpplugin.jdk14regexp;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.regexpplugin.MalformedPatternException;

/*
 * Importing some JDK1.4 regexp classes.
 */
import java.util.regex.PatternSyntaxException;

/**
 * Adapts JDK1.4 malformed pattern exceptions to follow this API.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class MalformedPatternExceptionJDKAdapter
    extends  MalformedPatternException
{
    /**
     * Private reference to the actual exception.
     */
    private PatternSyntaxException m__Adaptee;

    /**
     * Constructs an adapter for given JDK1.4 exception.
     * @param exception concrete exception instance to adapt.
     */
    public MalformedPatternExceptionJDKAdapter(
        final PatternSyntaxException exception)
    {
        super(((exception == null) ? "" : exception.getMessage()));

        immutableSetPatternSyntaxException(exception);
    }

    /**
     * Specifies the adapted exception.
     * @param exception the PatternSyntaxException to adapt.
     */
    private void immutableSetPatternSyntaxException(
        final PatternSyntaxException exception)
    {
        m__Adaptee = exception;
    }

    /**
     * Specifies the adapted exception.
     * @param exception the PatternSyntaxException to adapt.
     */
    protected void setPatternSyntaxException(
        final PatternSyntaxException exception)
    {
        immutableSetPatternSyntaxException(exception);
    }

    /**
     * Retrieves the adapted exception.
     * @return such exception.
     */
    protected PatternSyntaxException getPatternSyntaxException()
    {
        return m__Adaptee;
    }
}
