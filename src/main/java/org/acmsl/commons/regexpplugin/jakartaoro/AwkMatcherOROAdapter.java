//;-*- mode: java -*-
/*
                        ACM-SL Commons

    Copyright (C) 2002-today  Jose San Leandro Armendariz
                              chous@acm-sl.org

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

    Thanks to ACM S.L. for distributing this library under the LGPL license.
    Contact info: jose.sanleandro@acm-sl.com

 ******************************************************************************
 *
 * Filename: AwkMatcherOROAdapter.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Jakarta ORO-specific regexp compiler adapter. This class makes
 *              possible the use of ORO compilers inside this API. A delegation
 *              is used because Perl5Compiler is a final class.
 *
 */
package org.acmsl.commons.regexpplugin.jakartaoro;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.regexpplugin.jakartaoro.PatternOROAdapter;
import org.acmsl.commons.regexpplugin.Matcher;
import org.acmsl.commons.regexpplugin.MatchResult;
import org.acmsl.commons.regexpplugin.Pattern;

/*
 * Importing ORO classes.
 */
import org.apache.oro.text.awk.AwkMatcher;

/*
 * Importing some commons-logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Jakarta ORO-specific regexp compiler adapter. This class makes possible the
 * use of ORO compilers inside this API. A delegation is used because
 * Perl5Compiler is a final class.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class AwkMatcherOROAdapter
    implements  Matcher
{
    /**
     * Delegated instance.
     */
    private AwkMatcher m__Instance;

    /**
     * Constructs an AwkMatcherOROAdapter for given object.
     * @param adaptee the instance to be adapted.
     */
    public AwkMatcherOROAdapter()
    {
        inmutableSetAwkMatcher(new AwkMatcher());
    }

    /**
     * Specifies the adaptee.
     * @param adaptee the instance to adapt.
     */
    private void inmutableSetAwkMatcher(AwkMatcher adaptee)
    {
        m__Instance = adaptee;
    }

    /**
     * Specifies the adaptee.
     * @param adaptee the instance to adapt.
     */
    protected void setAwkMatcher(AwkMatcher adaptee)
    {
        inmutableSetAwkMatcher(adaptee);
    }

    /**
     * Retrieves an instance of AwkMatcher class.
     * @return the adapted matcher.
     */
    protected AwkMatcher getAwkMatcher()
    {
        return m__Instance;
    }

    /**
     * Checks if given text contains specified pattern.
     * @param text the text to analyze.
     * @param pattern the regular expression to apply.
     * @return true if the pattern is found.
     */
    public boolean contains(String text, Pattern pattern)
    {
        boolean result = false;

        if  (   (pattern != null)
             && (pattern instanceof PatternOROAdapter))
        {
            AwkMatcher t_AwkMatcher = getAwkMatcher();

            if  (t_AwkMatcher != null)
            {
                result =
                    t_AwkMatcher.contains(
                        text,
                        ((PatternOROAdapter) pattern).getPattern());
            }
            else 
            {
                LogFactory.getLog(getClass()).error(
                    "Awk matcher unavailable.");
            }
        }

        return result;
    }

    /**
     * Retrieves the last match found due to a previous call to
     * <i>contains</i> method.
     * @return such match result.
     */
    public MatchResult getMatch()
    {
        MatchResult result = null;

        AwkMatcher t_AwkMatcher = getAwkMatcher();


        if  (t_AwkMatcher != null)
        {
            result =
                new MatchResultOROAdapter(t_AwkMatcher.getMatch());
        }

        return result;
    }
}
