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
 * Description: Manages which regexp engine to use, acting as a facade
 *              hiding all details of building or retrieving implementations.
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
package org.acmsl.commons.regexpplugin;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Manager;
import org.acmsl.commons.regexpplugin.Compiler;
import org.acmsl.commons.regexpplugin.Matcher;
import org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException;

/*
 * Importing some commons-logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Manages which regexp engine to use, acting as a facade hiding all
 * details of building or retrieving implementations.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public final class RegexpManager
    implements  Manager
{
    /**
     * Engine name position in the array object.
     */
    protected static final int NAME = 0;

    /**
     * Engine version position in the array object.
     */
    protected static final int ENGINE_VERSION = 1;

    /**
     * Engine class package position in the array object.
     */
    protected static final int PACKAGE = 2;

    /**
     * Compiler position in the array object.
     */
    protected static final int COMPILER = 3;

    /**
     * Match result position in the array object.
     */
    protected static final int MATCHER = 4;

    /**
     * Helper result position in the array object.
     */
    protected static final int HELPER = 5;

    /**
     * Jakarta Oro Perl5
     */
    private static final String[] JAKARTA_ORO_PERL5 =
        new String[]
        {
            "Jakarta Oro Perl 5",
            "2.0.6",
            "org.apache.oro.text.perl",
            "org.acmsl.commons.regexpplugin.jakartaoro.Perl5CompilerOROAdapter",
            "org.acmsl.commons.regexpplugin.jakartaoro.Perl5MatcherOROAdapter",
            "org.acmsl.commons.regexpplugin.jakartaoro.HelperOROAdapter"
        };

    /**
     * Jakarta Oro Awk
     * @deprecated Awk engine is not working correctly!
     */
    private static final String[] JAKARTA_ORO_AWK =
        new String[]
        {
            "Jakarta Oro Awk",
            "2.0.6",
            "org.apache.oro.text.awk",
            "org.acmsl.commons.regexpplugin.jakartaoro.AwkCompilerOROAdapter",
            "org.acmsl.commons.regexpplugin.jakartaoro.AwkMatcherOROAdapter",
            "org.acmsl.commons.regexpplugin.jakartaoro.HelperOROAdapter"
        };

    /**
     * Jakarta Regexp
     */
    private static final String[] JAKARTA_REGEXP =
        new String[]
        {
            "Jakarta Regexp",
            "1.2",
            "org.apache.regexp",
            "org.acmsl.commons.regexpplugin.jakartaregexp.CompilerRegexpAdapter",
            "org.acmsl.commons.regexpplugin.jakartaregexp.MatcherRegexpAdapter",
            "org.acmsl.commons.regexpplugin.jakartaregexp.HelperRegexpAdapter"
        };

    /**
     * JDK1.4
     */
    private static final String[] JDK14_REGEXP =
        new String[]
        {
            "JDK1.4 Regular Expresions",
            "1.4",
            "java.util.regex",
            "org.acmsl.commons.regexpplugin.jdk14regexp.CompilerJDKAdapter",
            "org.acmsl.commons.regexpplugin.jdk14regexp.MatcherJDKAdapter",
            "org.acmsl.commons.regexpplugin.jdk14regexp.HelperJDKAdapter"
        };

    /**
     * GNU Regexp
     */
    private static final String[] GNU_REGEXP_114 =
        new String[]
        {
            "GNU Regexp",
            "1.1.4",
            "gnu.regexp",
            "org.acmsl.commons.regexpplugin.gnuregexp.CompilerGNUAdapter",
            "org.acmsl.commons.regexpplugin.gnuregexp.MatcherGNUAdapter",
            "org.acmsl.commons.regexpplugin.gnuregexp.HelperGNUAdapter"
        };

    /**
     * Singleton instance.
     */
    private static RegexpManager m__Singleton;

    /**
     * Private reference to the engine type.
     */
    private String[] m__astrEngine = JAKARTA_ORO_PERL5;

    /**
     * Private constructor to avoid accidental instantiation.
     */
    private RegexpManager()  {};

    /**
     * Common method to retrieve the singleton object.
     * @return the singleton instance.
     */
    private static RegexpManager getInstance()
    {
        RegexpManager result = m__Singleton;

        if  (m__Singleton == null)
        {
            m__Singleton = new RegexpManager();

            result = m__Singleton;
        }

        return result;
    }

    /**
     * Retrieves current engine information.
     * @return the engine information.
     */
    protected String[] getEngine()
    {
        return m__astrEngine;
    }

    /**
     * Sets the current engine.
     * @param engine the engine information.
     */
    protected void setEngine(String[] engine)
    {
        m__astrEngine = engine;
    }

    /**
     * Sets Jakarta ORO Perl5 implementation to be the engine used.
     */
    public static void useJakartaOroPerl5()
    {
        getInstance().useJakartaOroPerl5Engine();
    }

    /**
     * Sets Jakarta ORO Perl5 implementation to be the engine used.
     */
    private void useJakartaOroPerl5Engine()
    {
        setEngine(JAKARTA_ORO_PERL5);
    }

    /**
     * Sets Jakarta ORO Awk implementation to be the engine used.
     * @deprecated Awk engine is not working correctly!
     */
    public static void useJakartaOroAwk()
    {
        getInstance().useJakartaOroAwkEngine();
    }

    /**
     * Sets Jakarta ORO Awk implementation to be the engine used.
     * @deprecated Awk engine is not working correctly!
     */
    private void useJakartaOroAwkEngine()
    {
        setEngine(JAKARTA_ORO_AWK);
    }

    /**
     * Sets Jakarta Regexp implementation to be the engine used.
     */
    public static void useJakartaRegexp()
    {
        getInstance().useJakartaRegexpEngine();
    }

    /**
     * Sets Jakarta Regexp implementation to be the engine used.
     */
    private void useJakartaRegexpEngine()
    {
        setEngine(JAKARTA_REGEXP);
    }

    /**
     * Sets JDK1.4 regexp implementation to be the engine used.
     */
    public static void useJDK14Regexp()
    {
        getInstance().useJDK14RegexpEngine();
    }

    /**
     * Sets JDK1.4 regexp implementation to be the engine used.
     */
    private void useJDK14RegexpEngine()
    {
        setEngine(JDK14_REGEXP);
    }

    /**
     * Sets GNU Regexp implementation to be the engine used.
     */
    public static void useGNURegexp()
    {
        getInstance().useGNURegexpEngine();
    }

    /**
     * Sets GNU Regexp implementation to be the engine used.
     */
    private void useGNURegexpEngine()
    {
        setEngine(GNU_REGEXP_114);
    }

    /**
     * Creates a new compiler.
     * @return the new compiler.
     * @throws RegexpEngineNotFoundException whenever the instantiation
     * of the engine classes fails.
     */
    public static Compiler createCompiler()
        throws  RegexpEngineNotFoundException
    {
        return createSpecificCompiler(getInstance().getEngine());
    }

    /**
     * Creates a new helper instance.
     * @return such kind of object.
     * @throws RegexpEngineNotFoundException whenever the instantiation
     * of the engine classes fails.
     */
    public static Helper createHelper()
        throws  RegexpEngineNotFoundException
    {
        return createSpecificHelper(getInstance().getEngine());
    }

    /**
     * Creates a compiler determined by given class name.
     * @param className the class name of the compiler.
     * @return the compiler object, or null if the class name is incorrect.
     * @throws RegexpEngineNotFoundException whenever the instantiation
     * of the engine classes fails.
     */
    protected static Compiler createSpecificCompiler(String[] engine)
        throws  RegexpEngineNotFoundException
    {
        Compiler result = null;

        try
        {
            result = (Compiler) Class.forName(engine[COMPILER]).newInstance();
        }
        catch  (Exception exception)
        {
            LogFactory.getLog(RegexpManager.class).error(
                "Compiler instantiation error.",
                exception);

            throw
                new RegexpEngineNotFoundException(
                    engine[NAME],
                    engine[ENGINE_VERSION],
                    engine[PACKAGE],
                    engine[COMPILER],
                    engine[MATCHER],
                    engine[HELPER]);
        }

        return result;
    }

    /**
     * Creates a compiler determined by given class name.
     * @param className the class name of the compiler.
     * @return the compiler object, or null if the class name is incorrect.
     * @throws RegexpEngineNotFoundException whenever the instantiation
     * of the engine classes fails.
     */
    protected static Helper createSpecificHelper(String[] engine)
        throws  RegexpEngineNotFoundException
    {
        Helper result = null;

        try
        {
            result = (Helper) Class.forName(engine[HELPER]).newInstance();
        }
        catch  (Exception exception)
        {
            LogFactory.getLog(RegexpManager.class).error(
                "Helper instantiation error.",
                exception);

            exception.printStackTrace(System.err);

            throw
                new RegexpEngineNotFoundException(
                    engine[NAME],
                    engine[ENGINE_VERSION],
                    engine[PACKAGE],
                    engine[COMPILER],
                    engine[MATCHER],
                    engine[HELPER]);
        }

        return result;
    }

    /**
     * Creates a new pattern matcher.
     * @return the new matcher.
     * @throws RegexpEngineNotFoundException whenever the instantiation
     * of the engine classes fails.
     */
    public static Matcher createMatcher()
        throws  RegexpEngineNotFoundException
    {
        return createSpecificMatcher(getInstance().getEngine());
    }

    /**
     * Creates a matcher determined by given class name.
     * @param className the class name of the matcher.
     * @return the matcher object, or null if the class name is incorrect.
     * @throws RegexpEngineNotFoundException whenever the instantiation
     * of the engine classes fails.
     */
    private static Matcher createSpecificMatcher(String[] engine)
        throws  RegexpEngineNotFoundException
    {
        Matcher result = null;

        try
        {
            result = (Matcher) Class.forName(engine[MATCHER]).newInstance();
        }
        catch  (Exception exception)
        {
            LogFactory.getLog(RegexpManager.class).error(
                "Matcher instantiation error.",
                exception);

            throw
                new RegexpEngineNotFoundException(
                    engine[NAME],
                    engine[ENGINE_VERSION],
                    engine[PACKAGE],
                    engine[COMPILER],
                    engine[MATCHER],
                    engine[HELPER]);
        }

        return result;
    }
}
