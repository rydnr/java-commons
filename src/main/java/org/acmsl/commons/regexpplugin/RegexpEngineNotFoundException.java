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
 * Description: Designed to be thrown at runtime when the
 *              specified regexp engine is not found.
 *
 * Last modified by: $Author: chous $ at $Date: 2004-12-01 08:23:23 +0100 (Wed, 01 Dec 2004) $
 *
 * File version: $Revision: 461 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: RegexpEngineNotFoundException.java 461 2004-12-01 07:23:23Z chous $
 *
 */
package org.acmsl.commons.regexpplugin;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.ConfigurationException;

/**
 * Designed to be thrown at runtime when the specified regexp engine
 * is not found.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision: 461 $
 */
public class RegexpEngineNotFoundException
    extends  ConfigurationException
{
    /**
     * The key for this exception in the bundle.
     */
    protected static final String MESSAGE_KEY = "regexp.engine.not.found";

    /**
     * Engine name.
     */
    private String m__strEngineClass;

    /**
     * Builds an exception to indicate the regexp engine was not found.
     * @param engineClass the class name of the Regexp implementation whose
     * instatiation has failed.
     * @precondition engineClass != null
     */
    public RegexpEngineNotFoundException(final String engineClass)
    {
        super(
            MESSAGE_KEY,
            new Object[]
            {
                engineClass,
            });

        inmutableSetEngineClass(engineClass);
    }

    /**
     * Specifies the implementation class name.
     * @param engineClass the implementation class.
     * @precondition engineClass != null
     */
    private void inmutableSetEngineClass(final String engineClass)
    {
        m__strEngineClass = engineClass;
    }

    /**
     * Specifies the implementation class name.
     * @param engineClass the implementation class.
     * @precondition engineClass != null
     */
    protected void setEngineClass(final String engineClass)
    {
        inmutableSetEngineClass(engineClass);
    }

    /**
     * Retrieves the implementation class name.
     * @return implementation class.
     */
    public String getEngineClass()
    {
        return m__strEngineClass;
    }
}
