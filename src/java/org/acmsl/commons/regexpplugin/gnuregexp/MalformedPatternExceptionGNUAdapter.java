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
 * Description: Adapts GNU Regexp 1.1.4 malformed pattern exceptions
 *              to follow this API.
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
package org.acmsl.commons.regexpplugin.gnuregexp;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.regexpplugin.MalformedPatternException;
import org.acmsl.commons.version.Version;
import org.acmsl.commons.version.VersionFactory;

/*
 * Importing some GNU Regexp 1.1.4 classes.
 */
import gnu.regexp.REException;

/**
 * Adapts GNU Regexp 1.1.4 malformed pattern exceptions to follow this
 * API.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class MalformedPatternExceptionGNUAdapter
    extends MalformedPatternException
{
    /**
     * Private reference to the actual exception.
     */
    private REException m__Adaptee;

    /**
     * Constructs an adapter for given GNU Regexp 1.1.4 exception.
     * @param exception concrete exception instance to adapt.
     */
    public MalformedPatternExceptionGNUAdapter(REException exception)
    {
        super(exception.getMessage());

        inmutableSetREException(exception);
    }

    /**
     * Specifies the exception to adapt.
     * @param adaptee the instance to adapt.
     */
    private void inmutableSetREException(REException adaptee)
    {
        m__Adaptee = adaptee;
    }

    /**
     * Specifies the exception to adapt.
     * @param adaptee the instance to adapt.
     */
    protected void setREException(REException adaptee)
    {
        inmutableSetREException(adaptee);
    }

    /**
     * Retrieves the original exception.
     * @return such exception.
     */
    protected REException getREException()
    {    
        return m__Adaptee;
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
     * Retrieves the current version of this class.
     * @return the object with class version information.
     */
    public static Version getClassVersion()
    {
        return VERSION;
    }
}
