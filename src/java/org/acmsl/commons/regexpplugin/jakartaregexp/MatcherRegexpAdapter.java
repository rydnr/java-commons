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
import org.acmsl.commons.regexpplugin.Matcher;
import org.acmsl.commons.regexpplugin.MatchResult;
import org.acmsl.commons.regexpplugin.Pattern;

/*
 * Importing some Jakarta Regexp classes.
 */
import org.apache.regexp.RE;
import org.apache.regexp.REProgram;

/**
 * Represents the result of match in a regexp parsing process using
 * Jakarta Regexp package.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class MatcherRegexpAdapter
    implements  Matcher
{
    /**
     * Private RE reference.
     */
    private RE m__RE;

    /**
     * Constructs an empty REMatcherRegexpAdapter.
     */
    public MatcherRegexpAdapter()  {};

    /**
     * Sets the instance to adapt.
     * @param adaptee the re object to adapt.
     */
    protected final void immutableSetRE(final RE adaptee)
    {
        m__RE = adaptee;
    }

    /**
     * Sets the instance to adapt.
     * @param adaptee the re object to adapt.
     */
    protected void setRE(final RE adaptee)
    {
        immutableSetRE(adaptee);
    }

    /**
     * Retrieves the adapted instance.
     * @return the RE object.
     */
    public RE getRE()
    {
        return m__RE;
    }

    /**
     * Checks if given text contains specified pattern.
     * @param text the text to analyze.
     * @param pattern the regular expression to apply.
     * @return <code>true</code> if the pattern is found.
     * @precondition pattern != null
     * @precondition pattern instanceof PatternRegexpAdapter
     */
    public boolean contains(
        final String text, final Pattern pattern)
    {
        boolean result = false;

        PatternRegexpAdapter t_PatternAdapter =
            (PatternRegexpAdapter) pattern;

        RE t_RE = t_PatternAdapter.getRE();

        setRE(t_RE);

        result = t_RE.match(text);

        return result;
    }

    /**
     * Retrieves the last match found due to a previous call to
     * <i>contains</i> method.
     * @return such match result.
     */
    public MatchResult getMatch()
    {
        return getMatch(getRE());
    }

    /**
     * Retrieves the last match found due to a previous call to
     * <i>contains</i> method.
     * @param re the RE instance.
     * @return such match result.
     * @precondition re != null
     */
    protected MatchResult getMatch(final RE re)
    {
        return new MatchResultRegexpAdapter(re);
    }
}
