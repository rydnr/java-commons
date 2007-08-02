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
 *              process using JDK1.4 regexp package.
 *
 * Last modified by: $Author: chous $ at $Date: 2004-09-06 09:05:48 +0200 (Mon, 06 Sep 2004) $
 *
 * File version: $Revision: 397 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: MatchResultJDKAdapter.java 397 2004-09-06 07:05:48Z chous $
 *
 */
package org.acmsl.commons.regexpplugin.jdk14regexp;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.regexpplugin.MatchResult;

/*
 * Importing some JDK1.4 regexp classes.
 */
import java.lang.IllegalStateException;
import java.util.regex.Matcher;

/*
 * Importing Commons-Logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Represents the result of match in a regexp parsing process using
 * JDK1.4 regexp package.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision: 397 $
 */
public class MatchResultJDKAdapter
    implements  MatchResult
{
    /**
     * Adapted object.
     */
    private Matcher m__Adaptee;

    /**
     * Constructs a MatchResultJDKAdapter from given JDK1.4
     * Regexp-specific instance.
     * @param matcher JDK1.4 matcher object to adapt.
     */
    public MatchResultJDKAdapter(Matcher matcher)
    {
        inmutableSetMatcher(matcher);
    }

    /**
     * Specifies the instance to adapt.
     * @param matcher the adaptee.
     */
    private void inmutableSetMatcher(Matcher matcher)
    {
        m__Adaptee = matcher;
    }

    /**
     * Specifies the instance to adapt.
     * @param matcher the adaptee.
     */
    protected void setMatcher(Matcher matcher)
    {
        inmutableSetMatcher(matcher);
    }

    /**
     * Retrieves the adapted instance.
     * @return such adaptee.
     */
    public Matcher getMatcher()
    {
        return m__Adaptee;
    }

    /**
     * Taken from JDK1.4 javadoc:
     * <i>Returns the input subsequence captured by the given group
     * during the previous match operation.</i>.
     * @param group The index of a capturing group in this matcher's
     * pattern.
     * @return The (possibly empty) subsequence captured by the group
     * during the previous match, or null if the group failed to match
     * part of the input.
     */
    public String group(final int group)
    {
        return group(group, getMatcher());
    }

    /**
     * Taken from JDK1.4 javadoc:
     * <i>Returns the input subsequence captured by the given group
     * during the previous match operation.</i>.
     * @param group The index of a capturing group in this matcher's
     * pattern.
     * @param matcher the regexp matcher.
     * @return The (possibly empty) subsequence captured by the group
     * during the previous match, or null if the group failed to match
     * part of the input.
     * @precondition matcher != null
     */
    protected String group(final int group, final Matcher matcher)
    {
        String result = null;

        try
        {
            // To ensure the groups are filled in.
            matcher.matches();

            result = matcher.group(group);
        }
        catch  (final IllegalStateException illegalStateException)
        {
            LogFactory.getLog(matcher.getClass()).error(
                "group not found",
                illegalStateException);
        }

        return result;
    }

    /**
     * Taken from JDK 1.4 javadoc:
     * <i>Returns the number of capturing groups in this matcher's
     * pattern.</i>.
     * @return such value.
     */
    public int groups()
    {
        return groups(getMatcher());
    }

    /**
     * Taken from JDK 1.4 javadoc:
     * @param matcher the matcher.
     * <i>Returns the number of capturing groups in this matcher's
     * pattern.</i>.
     * @return such value.
     * @precondition matcher != null
     */
    protected int groups(final Matcher matcher)
    {
        return matcher.groupCount();
    }
}
