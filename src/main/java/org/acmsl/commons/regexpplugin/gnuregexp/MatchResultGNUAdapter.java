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
 *              process using GNU Regexp 1.1.4 package.
 *
 * Last modified by: $Author: chous $ at $Date: 2004-12-01 09:50:27 +0100 (Wed, 01 Dec 2004) $
 *
 * File version: $Revision: 473 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: MatchResultGNUAdapter.java 473 2004-12-01 08:50:27Z chous $
 *
 */
package org.acmsl.commons.regexpplugin.gnuregexp;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.regexpplugin.MatchResult;

/*
 * Importing some GNU Regexp 1.1.4 classes.
 */
import gnu.regexp.REMatch;

/**
 * Represents the result of match in a regexp parsing process using
 * GNU Regexp 1.1.4 package.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision: 473 $
 */
public class MatchResultGNUAdapter
    implements  MatchResult
{
    /**
     * Adapted object.
     */
    private REMatch m__Adaptee;

    /**
     * Group count.
     */
    private int m__iGroups = 0;

    /**
     * Constructs a MatchResultGNUAdapter from given GNU Regexp 1.1.4
     * REMatch instance.
     * @param adaptee GNU Regexp 1.1.4 REMatch object to adapt.
     * @param groups the group count.
     */
    public MatchResultGNUAdapter(REMatch adaptee, int groups)
    {
        inmutableSetREMatch(adaptee);

        inmutableSetGroups(groups);
    }

    /**
     * Specifies the adaptee.
     * @param adaptee the instance to adapt.
     */
    private void inmutableSetREMatch(REMatch adaptee)
    {
        m__Adaptee = adaptee;
    }

    /**
     * Specifies the adaptee.
     * @param adaptee the instance to adapt.
     */
    protected void setREMatch(REMatch adaptee)
    {
        inmutableSetREMatch(adaptee);
    }

    /**
     * Retrieves the adaptee.
     * @return the instance to adapt.
     */
    protected REMatch getREMatch()
    {
        return m__Adaptee;
    }

    /**
     * Specifies the group count.
     * @param groups the number of groups.
     */
    private void inmutableSetGroups(int groups)
    {
        m__iGroups = groups;
    }

    /**
     * Specifies the group count.
     * @param groups the number of groups.
     */
    protected void setGroups(int groups)
    {
        inmutableSetGroups(groups);
    }

    /**
     * Taken from GNU Regexp 1.4 javadoc:
     * <i>Returns the maximum number of subexpressions in this regular
     * expression</i>.
     * @return such value.
     */
    public int groups()
    {
        return m__iGroups;
    }

    /**
     * Taken from JDK1.4 javadoc:
     * <i>Returns the string matching the given subexpression. The
     * subexpressions are indexed starting with one, not zero.
     * That is, the subexpression identified by the first set of
     * parentheses in a regular expression could be retrieved from an
     * REMatch by calling match.toString(1).
     * @param group Index of the subexpression.
     * @return the group matched at given position.
     */
    public String group(int group)
    {
        String result = "";

        REMatch t_REMatch = getREMatch();

        if  (t_REMatch != null)
        {
            if  (group < 1)
            {
                result = t_REMatch.toString();
            }
            else
            {
                result = t_REMatch.toString(group);
            }
        }

        return result;
    }
}
