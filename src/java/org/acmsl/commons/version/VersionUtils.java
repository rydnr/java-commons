/*
                        ACM-SL Commons

    Copyright (C) 2002-2005  Jose San Leandro Armendáriz
                             chous@acm-sl.org

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU General Public
    License as published by the Free Software Foundation; either
    version 2 of the License, or any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    General Public License for more details.

    You should have received a copy of the GNU General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-307  USA

    Thanks to ACM S.L. for distributing this library under the GPL license.
    Contact info: jose.sanleandro@acm-sl.com
    Postal Address: c/Playa de Lagoa, 1
                    Urb. Valdecabañas
                    Boadilla del monte

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armendáriz
 *
 * Description: Provides some useful methods when working with Strings.
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
package org.acmsl.commons.version;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Utils;
import org.acmsl.commons.regexpplugin.Compiler;
import org.acmsl.commons.regexpplugin.Helper;
import org.acmsl.commons.regexpplugin.MalformedPatternException;
import org.acmsl.commons.regexpplugin.Matcher;
import org.acmsl.commons.regexpplugin.MatchResult;
import org.acmsl.commons.regexpplugin.Pattern;
import org.acmsl.commons.regexpplugin.RegexpEngine;
import org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException;
import org.acmsl.commons.regexpplugin.RegexpManager;
import org.acmsl.commons.regexpplugin.RegexpPluginMisconfiguredException;
import org.acmsl.commons.utils.ConversionUtils;
import org.acmsl.commons.utils.StringValidator;
import org.acmsl.commons.utils.StringUtils;

/*
 * Importing some JDK classes.
 */
import java.lang.ref.WeakReference;
import java.text.MessageFormat;

/*
 * Importing commons-logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Provides some useful methods when working with Strings.
 * @author <a href="mailto:chous@acm-sl.org"
 *         >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class VersionUtils
    implements  Utils
{
    /**
     * The version regexp.
     */
    public static final String VERSION_REGEXP =
        "(.*?)([0-9]+)\\.?([0-9]+|{0})?\\.?([0-9]+|{0})?\\.?(.*)?";
    //     ^      ^        ^      ^        ^      ^       ^    ^
    //  prefix  major   minor wildcard subminor wildcard rest  suffix

    /**
     * The default wildcard.
     */
    public static final String DEFAULT_WILDCARD = "x";

    /**
     * An empty String array.
     */
    protected static final String[] EMPTY_STRING_ARRAY = new String[0];
    
    /**
     * Singleton implemented as a weak reference.
     */
    private static WeakReference singleton;

    /**
     * Compiler instance.
     */
    private static Compiler m__Compiler;

    /**
     * The version pattern.
     */
    private static Pattern m__VersionPattern;

    /**
     * Specifies a new weak reference.
     * @param utils the utils instance to use.
     */
    protected static void setReference(final VersionUtils utils)
    {
        singleton = new WeakReference(utils);
    }

    /**
     * Retrieves the weak reference.
     * @return such reference.
     */
    protected static WeakReference getReference()
    {
        return singleton;
    }

    /**
     * Retrieves a <code>VersionUtils</code> instance.
     * @return such instance.
     */
    public static VersionUtils getInstance()
    {
        VersionUtils result = null;

        WeakReference reference = getReference();

        if  (reference != null) 
        {
            result = (VersionUtils) reference.get();
        }

        if  (result == null) 
        {
            result = new VersionUtils();
        }

        Compiler t_Compiler = result.getCompiler();

        if  (t_Compiler == null)
        {
            try 
            {
                t_Compiler = createCompiler(RegexpManager.getInstance());

                if  (t_Compiler != null)
                {
                    result.immutableSetCompiler(t_Compiler);

                    try 
                    {
                        result.immutableSetVersionPattern(
                            immutableCompileVersionPattern(
                                DEFAULT_WILDCARD,
                                t_Compiler,
                                StringUtils.getInstance()));
                    }
                    catch  (final MalformedPatternException exception)
                    {
                        /*
                         * This should never happen. It's a compile-time
                         * error not detected by the compiler, but it's
                         * nothing dynamic. So, if it fails, fix it once
                         * and forget.
                         */
                        LogFactory.getLog(VersionUtils.class).error(
                            "Invalid version pattern", exception);

                        result = null;
                    }
                }
                else 
                {
                    LogFactory.getLog(VersionUtils.class).error(
                        "compiler unavailable");
                }

                if  (result != null)
                {
                    setReference(result);
                }
            }
            catch  (final RegexpEngineNotFoundException exception)
            {
                LogFactory.getLog(VersionUtils.class).error(
                    "no regexp engine found", exception);

                result = null;
            }
            catch  (final Throwable throwable)
            {
                LogFactory.getLog(VersionUtils.class).fatal(
                    "Unknown error", throwable);
            }
        }
        
        return result;
    }

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected VersionUtils()  {};

    /**
     * Specifies the regexp compiler.
     * @param compiler the compiler.
     */
    private static void immutableSetCompiler(final Compiler compiler)
    {
        m__Compiler = compiler;
    }

    /**
     * Specifies the regexp compiler.
     * @param compiler the compiler.
     */
    protected static void setCompiler(final Compiler compiler)
    {
        immutableSetCompiler(compiler);
    }

    /**
     * Retrieves the regexp compiler.
     * @return such compiler.
     */
    protected static Compiler getCompiler()
    {
        return m__Compiler;
    }

    /**
     * Specifies the version pattern.
     * @param pattern the pattern.
     */
    private static void immutableSetVersionPattern(final Pattern pattern)
    {
        m__VersionPattern = pattern;
    }

    /**
     * Specifies the version pattern.
     * @param pattern the pattern.
     */
    protected static void setVersionPattern(final Pattern pattern)
    {
        immutableSetVersionPattern(pattern);
    }

    /**
     * Retrieves the version pattern.
     * @return such pattern.
     */
    public static Pattern getVersionPattern()
    {
        return m__VersionPattern;
    }

    /**
     * Retrieves the version pattern.
     * @param wildcard the identifier used to identify "anything goes".
     * @return such pattern.
     * @precondition wildcard != null
     */
    public static Pattern getVersionPattern(final String wildcard)
    {
        Pattern result = getVersionPattern();

        if  (!DEFAULT_WILDCARD.equalsIgnoreCase(wildcard))
        {
            result =
                immutableCompileVersionPattern(
                    wildcard, getCompiler(), StringUtils.getInstance());
        }
        
        return result;
    }

    /**
     * Compiles a version pattern using given wildcard.
     * @param wildcard the identifier used to identify "anything goes".
     * @param compiler the regexp compiler.
     * @param stringUtils the <code>StringUtils</code> instance.
     * @return such pattern.
     * @precondition wildcard != null
     * @precondition compiler != null
     * @precondition stringUtils != null
     */
    protected static final Pattern immutableCompileVersionPattern(
        final String wildcard,
        final Compiler compiler,
        final StringUtils stringUtils)
    {
        Pattern result = null;

        MessageFormat t_Formatter = new MessageFormat(VERSION_REGEXP);
        
        try 
        {
            result =
                compiler.compile(
                    t_Formatter.format(
                        new Object[]
                        {
                            stringUtils.escapeRegexp(wildcard)
                        }));
        }
        catch  (final MalformedPatternException exception)
        {
            /*
             * This should never happen. It's a compile-time
             * error not detected by the compiler, but it's
             * nothing dynamic. So, if it fails, fix it once
             * and forget.
             */
            LogFactory.getLog(VersionUtils.class).error(
                "Invalid version pattern", exception);
        }

        return result;
    }
    
    /**
     * Checks whether given version value matches a concrete version family.
     * @param version the version.
     * @param family the family.
     * @return <code>true</code> if the version is compatible.
     * @precondition version != null
     * @precondition family != null
     */
    public boolean matches(final String version, final String family)
    {
        return matches(version, family, DEFAULT_WILDCARD);
    }

    /**
     * Checks whether given version value matches a concrete version family.
     * @param version the version.
     * @param family the family.
     * @param wildcard the identifier used to identify "anything goes".
     * @return <code>true</code> if the version is compatible.
     * @precondition version != null
     * @precondition family != null
     * @precondition wildcard != null
     */
    public boolean matches(
        final String version, final String family, final String wildcard)
    {
        return
            matches(
                version,
                family,
                wildcard,
                getVersionPattern(wildcard),
                createMatcher(RegexpManager.getInstance()),
                StringValidator.getInstance(),
                ConversionUtils.getInstance());
    }

    /**
     * Checks whether given version value matches a concrete version family.
     * @param version the version.
     * @param family the family.
     * @param wildcard the identifier used to identify "anything goes".
     * @param pattern the version pattern.
     * @param matcher the matcher.
     * @param stringValidator the <code>StringValidator</code> instance.
     * @param conversionUtils the <code>ConversionUtils</code> instance.
     * @return <code>true</code> if the version is compatible.
     * @precondition version != null
     * @precondition family != null
     * @precondition wildcard != null
     * @precondition pattern != null
     * @precondition matcher != null
     * @precondition stringValidator != null
     * @precondition conversionUtils != null
     */
    protected boolean matches(
        final String version,
        final String family,
        final String wildcard,
        final Pattern pattern,
        final Matcher matcher,
        final StringValidator stringValidator,
        final ConversionUtils conversionUtils)
    {
        boolean result = false;
        
        String[] t_astrVersion =
            parseVersion(version, pattern, matcher, stringValidator);
        
        String[] t_astrFamily =
            parseVersion(family, pattern, matcher, stringValidator);
        
        if  (   (t_astrVersion != null)
             && (t_astrFamily != null)
             && (t_astrVersion.length >= 1)
             && (t_astrFamily.length >= 1))
        {
            String t_strVersionMajor = t_astrVersion[0];
            String t_strFamilyMajor = t_astrFamily[0];
            String t_strVersionMinor = "";
            String t_strFamilyMinor = "";
            String t_strVersionSubminor = "";
            String t_strFamilySubminor = "";
            
            if  (t_astrVersion.length >= 2)
            {
                t_strVersionMinor = t_astrVersion[1];
            }
            if  (t_astrFamily.length >= 2)
            {
                t_strFamilyMinor = t_astrFamily[1];
            }
            if  (t_astrVersion.length >= 3)
            {
                t_strVersionSubminor = t_astrVersion[2];
            }
            if  (t_astrFamily.length >= 3)
            {
                t_strFamilySubminor = t_astrFamily[2];
            }

            result =
                matches(
                    t_strVersionMajor,
                    t_strVersionMinor,
                    t_strVersionSubminor,
                    t_strFamilyMajor,
                    t_strFamilyMinor,
                    t_strFamilySubminor,
                    wildcard);
        }
        
        return result;
    }
    
    /**
     * Checks whether given version value matches a concrete version family.
     * @param version the version.
     * @param pattern the version pattern.
     * @param matcher the matcher.
     * @param stringValidator the <code>StringValidator</code> instance.
     * @return <code>true</code> if the version is compatible.
     * @precondition version != null
     * @precondition versionPattern != null
     * @precondition matcher != null
     * @precondition stringValidator != null
     */
    protected String[] parseVersion(
        final String version,
        final Pattern pattern,
        final Matcher matcher,
        final StringValidator stringValidator)
    {
        String[] result = EMPTY_STRING_ARRAY;

        try
        {
            MatchResult t_MatchResult = null;

            if  (   (!stringValidator.isEmpty(version))
                 && (matcher.contains(version, pattern)))
            {
                t_MatchResult = matcher.getMatch();

                result =
                    new String[]
                    {
                        t_MatchResult.group(2),
                        t_MatchResult.group(3),
                        t_MatchResult.group(4)
                    };
           }
        }
        catch  (final MalformedPatternException exception)
        {
            LogFactory.getLog(getClass()).error(
                "Malformed pattern (possibly due to quote symbol conflict)",
                exception);
        }
        catch  (final RegexpEngineNotFoundException exception)
        {
            /*
             * This exception is thrown only if no regexp library is available
             * at runtime. Not only this one, but any method provided by this
             * class that use regexps will not work.
             */
            LogFactory.getLog(getClass()).error(
                "Cannot find any regexp engine.", exception);
        }
        
        return result;
    }

    /**
     * Checks whether given version value matches a concrete version family.
     * @param major the major version information.
     * @param minor the minor version information.
     * @param subminor the subminor version information.
     * @param familyMajor the family's major version.
     * @param familyMinor the family's minor version.
     * @param familySubminor the family's subminor version.
     * @precondition major != null
     * @precondition familyMajor != null
     */
    public boolean matches(
        final String major,
        final String minor,
        final String subminor,
        final String familyMajor,
        final String familyMinor,
        final String familySubminor)
    {
        return
            matches(
                major,
                minor,
                subminor,
                familyMajor,
                familyMinor,
                familySubminor,
                DEFAULT_WILDCARD);
    }
    
    /**
     * Checks whether given version value matches a concrete version family.
     * @param major the major version information.
     * @param minor the minor version information.
     * @param subminor the subminor version information.
     * @param familyMajor the family's major version.
     * @param familyMinor the family's minor version.
     * @param familySubminor the family's subminor version.
     * @param wildcard the identifier used to identify "anything goes".
     * @precondition major != null
     * @precondition familyMajor != null
     * @precondition wildcard != null
     */
    public boolean matches(
        final String major,
        final String minor,
        final String subminor,
        final String familyMajor,
        final String familyMinor,
        final String familySubminor,
        final String wildcard)
    {
        return
            matches(
                major,
                minor,
                subminor,
                familyMajor,
                familyMinor,
                familySubminor,
                wildcard,
                ConversionUtils.getInstance());
    }
    
    /**
     * Checks whether given version value matches a concrete version family.
     * @param major the major version information.
     * @param minor the minor version information.
     * @param subminor the subminor version information.
     * @param familyMajor the family's major version.
     * @param familyMinor the family's minor version.
     * @param familySubminor the family's subminor version.
     * @param wildcard the identifier used to identify "anything goes".
     * @param conversionUtils the <code>ConversionUtils</code> instance.
     * @precondition major != null
     * @precondition familyMajor != null
     * @precondition wildcard != null
     * @precondition conversionUtils != null
     */
    protected boolean matches(
        final String major,
        final String minor,
        final String subminor,
        final String familyMajor,
        final String familyMinor,
        final String familySubminor,
        final String wildcard,
        final ConversionUtils conversionUtils)
    {
        boolean result = false;

        if  (versionNumbersMatch(
                 major, familyMajor, wildcard, conversionUtils))
        {
            result = true;

            if  (   (minor != null)
                 && (familyMinor != null))
            {
                result =
                    versionNumbersMatch(
                        minor, familyMinor, wildcard, conversionUtils);
            }

            if  (result)
            {
                if  (   (subminor != null)
                     && (familySubminor != null))
                {
                    result =
                        versionNumbersMatch(
                            subminor,
                            familySubminor,
                            wildcard,
                            conversionUtils);
                }
            }
        }
        
        return result;
    }

    /**
     * Checks whether concrete version numbers match.
     * @param number the version number.
     * @param familyNumber the family number.
     */
    public boolean versionNumbersMatch(
        final String number, final String familyNumber)
    {
        return versionNumbersMatch(number, familyNumber, DEFAULT_WILDCARD);
    }
    
    /**
     * Checks whether concrete version numbers match.
     * @param number the version number.
     * @param familyNumber the family number.
     * @param wildcard the identifier used to identify "anything goes".
     * @precondition wildcard != null
     */
    public boolean versionNumbersMatch(
        final String number, final String familyNumber, final String wildcard)
    {
        return
            versionNumbersMatch(
                number,
                familyNumber,
                wildcard,
                ConversionUtils.getInstance());
    }
    
    /**
     * Checks whether concrete version numbers match.
     * @param number the version number.
     * @param familyNumber the family number.
     * @param wildcard the identifier used to identify "anything goes".
     * @param conversionUtils the <code>ConversionUtils</code> instance.
     * @precondition wildcard != null
     * @precondition conversionUtils != null
     */
    protected boolean versionNumbersMatch(
        final String number,
        final String familyNumber,
        final String wildcard,
        final ConversionUtils conversionUtils)
    {
        return
            (   (wildcard.equalsIgnoreCase(number))
             || (wildcard.equalsIgnoreCase(familyNumber))
             || (   conversionUtils.toInt(number)
                 == conversionUtils.toInt(familyNumber)));
    }
    
    /**
     * Creates the compiler.
     * @param regexpManager the <code>RegexpManager</code> instance.
     * @return the regexp compiler.
     * @throws RegexpEngineNotFoundException if a suitable instance
     * cannot be created.
     * @throws RegexpPluginMisconfiguredException if RegexpPlugin is
     * misconfigured.
     * @precondition regexpManager != null
     */
    protected static synchronized Compiler createCompiler(
        final RegexpManager regexpManager)
      throws RegexpEngineNotFoundException,
             RegexpPluginMisconfiguredException
    {
        return createCompiler(regexpManager.getEngine());
    }

    /**
     * Creates the compiler.
     * @param regexpEngine the RegexpEngine instance.
     * @return the regexp compiler.
     * @precondition regexpEngine != null
     */
    protected static synchronized Compiler createCompiler(
        final RegexpEngine regexpEngine)
    {
        Compiler result = regexpEngine.createCompiler();

        result.setCaseSensitive(false);
        
        return result;
    }

    /**
     * Creates the matcher.
     * @param regexpManager the RegexpManager instance.
     * @return the regexp matcher.
     * @throws RegexpEngineNotFoundException if a suitable instance
     * cannot be created.
     * @throws RegexpPluginMisconfiguredException if RegexpPlugin is
     * misconfigured.
     * @precondition regexpManager != null
     */
    protected static synchronized Matcher createMatcher(
        final RegexpManager regexpManager)
      throws RegexpEngineNotFoundException,
             RegexpPluginMisconfiguredException
    {
        return createMatcher(regexpManager.getEngine());
    }

    /**
     * Creates the matcher.
     * @param regexpEngine the RegexpEngine instance.
     * @return the regexp matcher.
     * @precondition regexpEngine != null
     */
    protected static synchronized Matcher createMatcher(
        final RegexpEngine regexpEngine)
    {
        return regexpEngine.createMatcher();
    }
}
