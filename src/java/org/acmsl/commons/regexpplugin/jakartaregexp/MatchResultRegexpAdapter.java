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
 * Description: Represents the result of match in a regexp parsing
 *              process using Jakarta Regexp package.
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
import org.acmsl.commons.regexpplugin.MatchResult;
import org.acmsl.commons.version.Version;
import org.acmsl.commons.version.VersionFactory;

/*
 * Importing some Jakarta Regexp classes.
 */
import org.apache.regexp.RE;

/**
 * Represents the result of match in a regexp parsing process using
 * Jakarta Regexp package.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class MatchResultRegexpAdapter
    implements  MatchResult
{
    /**
     * Adapted object.
     */
    private RE m__Adaptee;

    /**
     * Constructs a REMatchResultRegexpAdapter from given Jakarta
     * Regexp-specific instance.
     * @param matchResult Jakarta Regexp match result object to adapt.
     */
    public MatchResultRegexpAdapter(RE matchResult)
    {
        inmutableSetRE(matchResult);
    }

    /**
     * Specifies the instance to adapt.
     * @param matchResult the adaptee.
     */
    private void inmutableSetRE(RE matchResult)
    {
        m__Adaptee = matchResult;
    }

    /**
     * Specifies the instance to adapt.
     * @param matchResult the adaptee.
     */
    protected void setRE(RE matchResult)
    {
        inmutableSetRE(matchResult);
    }

    /**
     * Retrieves the adapted instance.
     * @return such instance.
     */
    public RE getRE()
    {
        return m__Adaptee;
    }

    /**
     * Taken from Jakarta Regexp javadoc:
     * <i>Gets the contents of a parenthesized subexpression after a
     * successful match</i>.
     * @param group Nesting level of subexpression.
     * @return A string containing the indicated pattern subgroup.
     */
    public String group(int group)
    {
        String result = "";

        RE t_RE = getRE();

        if  (t_RE != null)
        {
            result = t_RE.getParen(group);
        }

        return result;
    }

    /**
     * Taken from Jakarta Regexp 1.2 javadoc:
     * <i>Returns the number of parenthesized subexpressions available
     * after a successful match.</i>.
     * @return such value.
     */
    public int groups()
    {
        int result = 0;

        RE t_RE = getRE();

        if  (t_RE != null)
        {
            t_RE.getParenCount();
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
     * Retrieves the current version of this class.
     * @return the object with class version information.
     */
    public static Version getClassVersion()
    {
        return VERSION;
    }
}
