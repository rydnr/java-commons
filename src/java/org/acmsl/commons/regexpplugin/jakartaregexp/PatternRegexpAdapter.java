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
 * Description: Adapts Jakarta Regexp REProgram objects to follow the
 *              generic Pattern interface defined in this API.
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
package org.acmsl.commons.regexpplugin.jakartaregexp;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.regexpplugin.Pattern;
import org.acmsl.commons.version.Version;
import org.acmsl.commons.version.VersionFactory;

/*
 * Importing some Jakarta Regexp classes.
 */
import org.apache.regexp.RE;
import org.apache.regexp.REProgram;

/**
 * Adapts Jakarta Regexp REProgram objects to follow the generic
 * Pattern interface defined in this API.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class PatternRegexpAdapter
    implements  Pattern
{
    /**
     * Delegated instance.
     */
    private REProgram m__Instance;

    /**
     * RE reference needed to pass compiler configuration to the
     * mather.
     */
    private RE m__RE;

    /**
     * Constructs a PatternRegexpAdapter for given object.
     * @param adaptee the instance to be adapted.
     * @param re the required RE instance.
     */
    public PatternRegexpAdapter(REProgram adaptee, RE re)
    {
        inmutableSetREProgram(adaptee);

        inmutableSetRE(re);
    }

    /**
     * Specifies the instance to adapt.
     * @param adaptee the REProgram to be adapted.
     */
    private void inmutableSetREProgram(REProgram adaptee)
    {
        m__Instance = adaptee;
    }

    /**
     * Specifies the instance to adapt.
     * @param adaptee the REProgram to be adapted.
     */
    protected void setREProgram(REProgram adaptee)
    {
        inmutableSetREProgram(adaptee);
    }

    /**
     * Retrieves the adapted REProgram instance.
     * @return such instance.
     */
    protected REProgram getREProgram()
    {
        return m__Instance;
    }

    /**
     * Sets the RE reference.
     * @param re such reference.
     */
    private void inmutableSetRE(RE re)
    {
        m__RE = re;
    }

    /**
     * Sets the RE reference.
     * @param re such reference.
     */
    void setRE(RE re)
    {
        inmutableSetRE(re);
    }

    /**
     * Retrieves the associated RE reference.
     * Note: Other classes in this package cannot access this method.
     * @return the RE with compiler configuration.
     */
    RE getRE()
    {
        return m__RE;
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
