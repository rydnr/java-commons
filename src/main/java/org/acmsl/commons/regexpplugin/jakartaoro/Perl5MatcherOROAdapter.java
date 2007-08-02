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
 * Description: Jakarta ORO-specific regexp compiler adapter. This class makes
 *              possible the use of ORO compilers inside this API. A delegation
 *              is used because Perl5Compiler is a final class.
 *
 * Last modified by: $Author: chous $ at $Date: 2004-12-01 09:50:27 +0100 (Wed, 01 Dec 2004) $
 *
 * File version: $Revision: 473 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: Perl5MatcherOROAdapter.java 473 2004-12-01 08:50:27Z chous $
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
 * Importing Jakarta ORO classes.
 */
import org.apache.oro.text.regex.Perl5Matcher;

/**
 * Jakarta ORO-specific regexp compiler adapter. This class makes possible the
 * use of ORO compilers inside this API. A delegation is used because
 * Perl5Compiler is a final class.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision: 473 $
 */
public class Perl5MatcherOROAdapter
    implements  Matcher
{
    /**
     * Delegated instance.
     */
    private Perl5Matcher m__Instance;

    /**
     * Constructs a Perl5MatcherOROAdapter for given object.
     * @param adaptee the instance to be adapted.
     */
    public Perl5MatcherOROAdapter()
    {
        immutableSetMatcher(new Perl5Matcher());
    }

    /**
     * Specifies the adaptee.
     * @param adaptee the matcher to adapt.
     */
    protected final void immutableSetMatcher(final Perl5Matcher adaptee)
    {
        m__Instance = adaptee;
    }

    /**
     * Specifies the adaptee.
     * @param adaptee the matcher to adapt.
     */
    protected void setMatcher(final Perl5Matcher adaptee)
    {
        immutableSetMatcher(adaptee);
    }

    /**
     * Retrieves an instance of Perl5Matcher class.
     * @return a the adapted matcher.
     */
    protected Perl5Matcher getMatcher()
    {
        return m__Instance;
    }

    /**
     * Checks if given text contains specified pattern.
     * @param text the text to analyze.
     * @param pattern the regular expression to apply.
     * @return <code>true</code> if the pattern is found.
     * @precondition text != null
     * @precondition pattern != null
     * @precondition pattern instanceof PatternOROAdapter
     */
    public boolean contains(final String text, final Pattern pattern)
    {
        return contains(text, pattern, getMatcher());
    }

    /**
     * Checks if given text contains specified pattern.
     * @param text the text to analyze.
     * @param pattern the regular expression to apply.
     * @param matcher the matcher.
     * @return <code>true</code> if the pattern is found.
     * @precondition text != null
     * @precondition pattern != null
     * @precondition pattern instanceof PatternOROAdapter
     * @precondition matcher != null
     */
    protected boolean contains(
        final String text, final Pattern pattern, final Perl5Matcher matcher)
    {
        return
            matcher.contains(
                text, ((PatternOROAdapter) pattern).getPattern());
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
    protected MatchResult getMatch(final Perl5Matcher matcher)
    {
        return new MatchResultOROAdapter(matcher.getMatch());
    }
}
