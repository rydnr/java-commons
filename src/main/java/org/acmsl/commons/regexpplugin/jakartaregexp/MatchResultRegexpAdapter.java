/*
                        ACM-SL Commons

    Copyright (C) 2002-2003  Jose San Leandro Armend�riz
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
                    Urb. Valdecaba�as
                    Boadilla del monte
                    28660 Madrid
                    Spain

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armend�riz
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

/*
 * Importing some Jakarta Regexp classes.
 */
import org.apache.regexp.RE;

/**
 * Represents the result of match in a regexp parsing process using
 * Jakarta Regexp package.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armend�riz</a>
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
    public MatchResultRegexpAdapter(final RE matchResult)
    {
        inmutableSetRE(matchResult);
    }

    /**
     * Specifies the instance to adapt.
     * @param matchResult the adaptee.
     */
    private void inmutableSetRE(final RE matchResult)
    {
        m__Adaptee = matchResult;
    }

    /**
     * Specifies the instance to adapt.
     * @param matchResult the adaptee.
     */
    protected void setRE(final RE matchResult)
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
    public String group(final int group)
    {
        return group(group, getRE());
    }

    /**
     * Taken from Jakarta Regexp javadoc:
     * <i>Gets the contents of a parenthesized subexpression after a
     * successful match</i>.
     * @param group Nesting level of subexpression.
     * @param re the RE instance.
     * @return a string containing the indicated pattern subgroup.
     * @precondition re != null
     */
    protected String group(final int group, final RE re)
    {
        return re.getParen(group);
    }

    /**
     * Taken from Jakarta Regexp 1.2 javadoc:
     * <i>Returns the number of parenthesized subexpressions available
     * after a successful match.</i>.
     * @return such value.
     */
    public int groups()
    {
        return groups(getRE());
    }

    /**
     * Taken from Jakarta Regexp 1.2 javadoc:
     * <i>Returns the number of parenthesized subexpressions available
     * after a successful match.</i>.
     * @param re the RE instance.
     * @return such value.
     * @precondition re != null
     */
    protected int groups(final RE re)
    {
        return re.getParenCount();
    }
}