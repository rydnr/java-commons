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
 * Description: Adapts JDK 1.4 Regexp package to be used as a
 *              RegexpPlugin implementation.
 *
 * Last modified by: $Author: chous $ at $Date: 2004-12-01 08:23:23 +0100 (Wed, 01 Dec 2004) $
 *
 * File version: $Revision: 461 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: JDKRegexpEngine.java 461 2004-12-01 07:23:23Z chous $
 *
 */
package org.acmsl.commons.regexpplugin.jdk14regexp;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.regexpplugin.Compiler;
import org.acmsl.commons.regexpplugin.Helper;
import org.acmsl.commons.regexpplugin.jdk14regexp.CompilerJDKAdapter;
import org.acmsl.commons.regexpplugin.jdk14regexp.HelperJDKAdapter;
import org.acmsl.commons.regexpplugin.jdk14regexp.MatcherJDKAdapter;
import org.acmsl.commons.regexpplugin.Matcher;
import org.acmsl.commons.regexpplugin.RegexpEngine;

/**
 * Adapts JDK 1.4 Regexp package to be used as a RegexpPlugin implementation.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armendáriz</a>
 * @version $Revision: 461 $
 */
public class JDKRegexpEngine
    implements RegexpEngine
{
    /**
     * Creates a compiler instance.
     * @return such instance.
     */
    public Compiler createCompiler()
    {
        return new CompilerJDKAdapter();
    }

    /**
     * Creates a matcher instance.
     * @return such instance.
     */
    public Matcher createMatcher()
    {
        return new MatcherJDKAdapter();
    }

    /**
     * Creates a helper instance.
     * @return such instance.
     */
    public Helper createHelper()
    {
        return new HelperJDKAdapter();
    }
}
