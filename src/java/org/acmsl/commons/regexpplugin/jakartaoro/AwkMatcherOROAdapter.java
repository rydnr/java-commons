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
package org.acmsl.commons.regexpplugin.jakartaoro;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.regexpplugin.jakartaoro.PatternOROAdapter;
import org.acmsl.commons.regexpplugin.Matcher;
import org.acmsl.commons.regexpplugin.MatchResult;
import org.acmsl.commons.regexpplugin.Pattern;
import org.acmsl.commons.version.Version;
import org.acmsl.commons.version.VersionFactory;

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
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
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
