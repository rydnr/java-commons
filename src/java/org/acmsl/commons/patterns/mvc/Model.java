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
 * Description: Represents an information model, so it can be displayed and
 *              modified following the Model-View-Controller architecture.
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
package org.acmsl.commons.patterns.mvc;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Subject;
import org.acmsl.commons.version.Version;
import org.acmsl.commons.version.VersionFactory;

/**
 * Represents an information model, so it can be displayed and modified
 * following the Model-View-Controller architecture.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public abstract class Model
    extends  Subject
{
    /**
     * To know if it's new or contains information already present in the
     * persistence layer.
     */
    private boolean m__bIsNew = true;

    /**
     * Builds a Model object managed by given controller.
     * @param controller the controller entity.
     */
    protected Model(Controller controller)
    {
        attach(controller);
    }

    /**
     * Sets if this model represents new information or not.
     * @param isNew true if so.
     */
    public void isNew(boolean isNew)
    {
        m__bIsNew = isNew;
    }

    /**
     * Retrieves true if the model contains new information.
     * @return true if so.
     */
    public boolean isNew()
    {
        return m__bIsNew;
    }

    /**
     * Concrete version object updated everytime it's checked-in in a
     * CVS repository.
     */
    public static final Version VERSION =
        VersionFactory.createVersion("$Revision$");

    /**
     * Retrieves the version.
     * @return the current version.
     */
    public Version getVersion()
    {
        return VERSION;
    }

    /**
     * Retrieves the class version.
     * @return the current version of the class.
     */
    public static Version getClassVersion()
    {
        return VERSION;
    }
}
