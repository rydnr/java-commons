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
 * Description: JDK1.4-specific regexp matcher adapter. This class
 *              makes possible the use of JDK1.4 matchers inside this
 *              API.
 *
 * Last modified by: $Author: chous $ at $Date: 2004-12-01 09:50:27 +0100 (Wed, 01 Dec 2004) $
 *
 * File version: $Revision: 473 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: MatcherJDKAdapter.java 473 2004-12-01 08:50:27Z chous $
 *
 */
package org.acmsl.commons.regexpplugin.jdk14regexp;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.regexpplugin.MatchResult;
import org.acmsl.commons.regexpplugin.jdk14regexp.PatternJDKAdapter;

/*
 * Importing JDK1.4 regexp classes.
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Importing Commons-Logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * JDK1.4-specific regexp matcher adapter. This class makes possible
 * the use of JDK1.4 matchers inside this API.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision: 473 $
 */
public class MatcherJDKAdapter
    implements  org.acmsl.commons.regexpplugin.Matcher
{
    /**
     * Concrete engine implementation.
     */
    private Matcher m__Matcher;

    /**
     * Constructs a MatcherJDKAdapter.
     */
    public MatcherJDKAdapter()  {};

    /**
     * Checks if given text contains specified pattern.
     * @param text the text to analyze.
     * @param pattern the regular expression to apply.
     * @return <code>true</code> if the pattern is found.
     * @precondition text != null
     * @precondition pattern != null
     * @precondition pattern instanceof PatternJDKAdapter
     */
    public boolean contains(
        final String text,
        final org.acmsl.commons.regexpplugin.Pattern pattern)
    {
        boolean result = false;

        Pattern t_Pattern =
            ((PatternJDKAdapter) pattern).getPattern();

        Matcher t_Matcher = null;

        if  (t_Pattern != null)
        {
            t_Matcher = t_Pattern.matcher(text);
        }
        else 
        {
            LogFactory.getLog(getClass()).error(
                "pattern not accessible");
        }

        if  (t_Matcher != null)
        {
            result = t_Matcher.find();

            setMatcher(t_Matcher);
        }

        return result;
    }

    /**
     * Specifies the matcher.
     * @param matcher the new matcher.
     */
    protected final void immutableSetMatcher(final Matcher matcher)
    {
        m__Matcher = matcher;
    }

    /**
     * Specifies the matcher.
     * @param matcher the new matcher.
     */
    protected void setMatcher(final Matcher matcher)
    {
        immutableSetMatcher(matcher);
    }

    /**
     * Retrieves the adapted Matcher instance.
     * @return a the adapted matcher.
     */
    protected Matcher getMatcher()
    {
        return m__Matcher;
    }

    /**
     * Retrieves the last match found due to a previous call to
     * <i>contains</i> method.
     * @return such match result.
     */
    public MatchResult getMatch()
    {
        return getMatch(getMatcher());
    }

    /**
     * Retrieves the last match found due to a previous call to
     * <i>contains</i> method.
     * @param matcher the matcher.
     * @return such match result.
     * @precondition matcher != null
     */
    protected MatchResult getMatch(final Matcher matcher)
    {
        return new MatchResultJDKAdapter(matcher);
    }
}
