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
 * Description: Adapts Perl5Pattern objects to follow the generic interface
 *              defined in this API. A delegation is used because Perl5Pattern
 *              is a final class.
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
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.version.Version;
import org.acmsl.commons.version.VersionFactory;

/*
 * Importing some Jakarta Oro classes.
 */
import org.apache.oro.text.regex.Pattern;

/**
 * Adapts jakarta ORO Pattern objects to follow the generic interface defined
 * in this API.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class PatternOROAdapter
    implements  org.acmsl.commons.regexpplugin.Pattern
{
    /**
     * Delegated instance.
     */
    private Pattern m__Instance;

    /**
     * Constructs a PatternOROAdapter for given object.
     * @param adaptee the instance to be adapted.
     */
    public PatternOROAdapter(Pattern adaptee)
    {
        inmutableSetPattern(adaptee);
    }

    /**
     * Specifies the adaptee.
     * @param adaptee the instance to adapt.
     */
    private void inmutableSetPattern(Pattern adaptee)
    {
        m__Instance = adaptee;
    }

    /**
     * Specifies the adaptee.
     * @param adaptee the instance to adapt.
     */
    protected void setPattern(Pattern adaptee)
    {
        inmutableSetPattern(adaptee);
    }

    /**
     * Retrieves an instance of a Perl5Pattern class.
     * Note: Other classes in this package can access this method.
     * @return such instance.
     */
    Pattern getPattern()
    {
        return m__Instance;
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
