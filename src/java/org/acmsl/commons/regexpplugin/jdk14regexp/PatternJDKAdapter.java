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
 * Description: Adapts JDK1.4 Pattern objects to follow the
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
package org.acmsl.commons.regexpplugin.jdk14regexp;

/*
 * Importing some JDK1.4 classes.
 */
import java.util.regex.Pattern;

/**
 * Adapts JDK1.4 pattern objects to follow the generic
 * Pattern interface defined in this API.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class PatternJDKAdapter
    implements  org.acmsl.commons.regexpplugin.Pattern
{
    /**
     * Delegated instance.
     */
    private Pattern m__Instance;

    /**
     * Constructs a PatternJDKAdapter for given object.
     * @param adaptee the instance to be adapted.
     */
    public PatternJDKAdapter(final Pattern adaptee)
    {
        immutableSetPattern(adaptee);
    }

    /**
     * Specifies the pattern to adapt.
     * Note: This method has package private access rights.
     * @param adaptee the adaptee.
     */
    private void immutableSetPattern(final Pattern adaptee)
    {
        m__Instance = adaptee;
    }

    /**
     * Specifies the pattern to adapt.
     * Note: This method has package private access rights.
     * @param adaptee the adaptee.
     */
    void setPattern(final Pattern adaptee)
    {
        immutableSetPattern(adaptee);
    }

    /**
     * Retrieves the adapted Pattern instance.
     * Note: This method has package private access rights.
     * @return such instance.
     */
    Pattern getPattern()
    {
        return m__Instance;
    }
}
