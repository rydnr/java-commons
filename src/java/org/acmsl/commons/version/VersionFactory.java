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
 * Description: Is responsible of creating valid Version objects.
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
package org.acmsl.commons.version;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Factory;
import org.acmsl.commons.utils.StringValidator;
import org.acmsl.commons.utils.version.VersionValidator;
import org.acmsl.commons.version.Version;
import org.acmsl.commons.version.Versionable;

/*
 * Importing some JDK classes.
 */
import java.lang.ref.WeakReference;

/*
 * Importing commons-logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Is responsible of creating valid Version objects.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 * @stereotype factory
 * @see org.acmsl.commons.version.Version
 * @see org.acmsl.commons.version.Versionable
 */
public abstract class VersionFactory
    implements  Factory
{
    /**
     * Singleton implemented as a weak reference.
     */
    private static WeakReference singleton;

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected VersionFactory() {};

    /**
     * Specifies a new weak reference.
     * @param utils the utils instance to use.
     */
    protected static void setReference(VersionFactory factory)
    {
        singleton = new WeakReference(factory);
    }

    /**
     * Retrieves the weak reference.
     * @return such reference.
     */
    protected static WeakReference getReference()
    {
        return singleton;
    }

    /**
     * Retrieves a VersionFactory instance.
     * @return such instance.
     */
    public static VersionFactory getInstance()
    {
        VersionFactory result = null;

        WeakReference reference = getReference();

        if  (reference != null) 
        {
            result = (VersionFactory) reference.get();
        }

        if  (result == null) 
        {
            result = new VersionFactory() {};

            setReference(result);
        }

        return result;
    }

    /**
     * Creates a version object with given information.
     * @param versionInfo the concrete version information.
     */
    public static Version createVersion(String versionInfo)
    {
        Version result = Version.INVALID;

        StringValidator t_StringValidator =
            StringValidator.getInstance();

        VersionValidator t_VersionValidator =
            VersionValidator.getInstance();

        if  (   (t_StringValidator  != null)
             && (t_VersionValidator != null))
        {
            if  (   (!t_StringValidator.isEmpty(versionInfo))
                 && (t_VersionValidator.isValid(versionInfo)))
            {
                result = new Version(versionInfo) {};
            }
        }
        else 
        {
            LogFactory.getLog(VersionFactory.class).fatal(
                  "One of the following instances are not found: "
                + "StringValidator (" + t_StringValidator + "), "
                + "VersionValidator (" + t_VersionValidator + ")");
        }
        
        return result;
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
