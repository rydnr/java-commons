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
 * Description: Is responsible of checking the validness of Version
 *              objects.
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
package org.acmsl.commons.utils.version;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Validator;
import org.acmsl.commons.regexpplugin.Compiler;
import org.acmsl.commons.regexpplugin.MalformedPatternException;
import org.acmsl.commons.regexpplugin.Matcher;
import org.acmsl.commons.regexpplugin.Pattern;
import org.acmsl.commons.regexpplugin.RegexpEngine;
import org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException;
import org.acmsl.commons.regexpplugin.RegexpManager;
import org.acmsl.commons.regexpplugin.RegexpPluginMisconfiguredException;
import org.acmsl.commons.utils.StringValidator;
import org.acmsl.commons.version.Versionable;
import org.acmsl.commons.version.Version;
import org.acmsl.commons.version.VersionFactory;

/*
 * Importing some JDK classes.
 */
import java.lang.ref.WeakReference;

/*
 * Importing commons-logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Is responsible of checking the validness of Version objects.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 * @stereotype Validator
 * @see org.acmsl.commons.version.Version
 * @see org.acmsl.commons.version.Versionable
 */
public abstract class VersionValidator
    implements  Validator
{
    /**
     * Concrete regular expression used to parse CVS revision information.
     */
    protected static final String CVS_REGEXP =
        "(.*)?\\$[\\s*]?Revision[\\s*]?:[\\s*]?(.*)[\\s*]?\\$(.*)?";

    /**
     * Singleton implemented as a weak reference.
     */
    private static WeakReference singleton;

    /**
     * Compiler used.
     */
    private static Compiler m__Compiler;

    /**
     * Compiled CVS pattern.
     */
    private static Pattern m__CVSPattern;

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected VersionValidator() {};

    /**
     * Specifies a new weak reference.
     * @param utils the utils instance to use.
     */
    protected static void setReference(VersionValidator validator)
    {
        singleton = new WeakReference(validator);
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
     * Retrieves a VersionValidator instance.
     * @return such instance.
     */
    public static VersionValidator getInstance()
    {
        VersionValidator result = null;

        WeakReference reference = getReference();

        if  (reference != null) 
        {
            result = (VersionValidator) reference.get();
        }

        if  (result == null) 
        {
            result = new VersionValidator() {};
        }

        Compiler t_Compiler = result.getCompiler();

        if  (t_Compiler == null)
        {
            synchronized (result)
            {
                try
                {
                    t_Compiler = createCompiler(RegexpManager.getInstance());

                    if  (t_Compiler != null)
                    {
                        t_Compiler.setMultiline(false);
                        t_Compiler.setCaseSensitive(false);

                        result.inmutableSetCompiler(t_Compiler);

                        result.inmutableSetCVSPattern(
                            t_Compiler.compile(CVS_REGEXP));

                        setReference(result);
                    }
                }
                catch  (MalformedPatternException malformedPatternException)
                {
                    /*
                     * This should never happen. It's a compile-time
                     * error not detected by the compiler, but it's
                     * nothing dynamic. So, if fails, fix it and forget.
                     */
                    LogFactory.getLog(VersionValidator.class).fatal(
                        "Invalid pattern",
                        malformedPatternException);

                    result = null;
                }
                catch  (RegexpEngineNotFoundException regexpEngineNotFoundException)
                {
                    LogFactory.getLog(VersionValidator.class).error(
                        "no regexp engine found",
                        regexpEngineNotFoundException);

                    result = null;
                }
            }
        }
        
        return result;
    }

    /**
     * Specifies the regexp compiler.
     * @param compiler the compiler.
     */
    private void inmutableSetCompiler(Compiler compiler)
    {
        m__Compiler = compiler;
    }

    /**
     * Specifies the regexp compiler.
     * @param compiler the compiler.
     */
    protected void setCompiler(Compiler compiler)
    {
        inmutableSetCompiler(compiler);
    }

    /**
     * Retrieves the regexp compiler.
     * @return such compiler.
     */
    protected Compiler getCompiler()
    {
        return m__Compiler;
    }

    /**
     * Specifies the CVS pattern.
     * @param pattern the pattern.
     */
    private void inmutableSetCVSPattern(Pattern pattern)
    {
        m__CVSPattern = pattern;
    }

    /**
     * Specifies the CVS pattern.
     * @param pattern the pattern.
     */
    protected void setCVSPattern(Pattern pattern)
    {
        inmutableSetCVSPattern(pattern);
    }

    /**
     * Retrieves the CVS pattern.
     * @return such pattern.
     */
    public Pattern getCVSPattern()
    {
        return m__CVSPattern;
    }

    /**
     * Checks whether given information contains version information or not.
     * @param versionInformation the information to check.
     * @return <code>true</code> if given information contains valid version
     * information, according to CVS syntax.
     */
    public boolean isValid(String versionInformation)
    {
        boolean result = false;

        try
        {
            StringValidator t_StringValidator = StringValidator.getInstance();

            Compiler t_Compiler = getCompiler();
            Pattern t_Pattern = getCVSPattern();

            if  (   (t_StringValidator != null)
                 && (t_Compiler        != null)
                 && (!t_StringValidator.isEmpty(versionInformation)))
            {
                /*
                 * Pattern could be null in the process of initializing
                 * the exact RegexpPlugin pattern adapter choosen (or the
                 * default if none is specified). That's why the pattern
                 * is not checked.
                 */

                //  The first thing is to construct the compiler and
                // pattern-related stuff.
                Matcher t_PatternMatcher =
                    createMatcher(RegexpManager.getInstance());

                if  (t_PatternMatcher != null)
                {
                    if  (t_PatternMatcher.contains(
                             versionInformation, t_Pattern))
                    {
                        result =
                            !t_StringValidator.isEmpty(
                                t_PatternMatcher.getMatch().group(1));
                    }
                }
                else 
                {
                    LogFactory.getLog(getClass()).error(
                        "cannot create regexp matcher");
                }
            }
            else 
            {
                LogFactory.getLog(getClass()).error(
                      "one of the following instances are null: "
                    + "StringValidator (" + t_StringValidator + "), "
                    + "Compiler (" + t_Compiler + ") ");
            }
        }
        catch  (RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            LogFactory.getLog(getClass()).error(
                "no regexp engine found",
                regexpEngineNotFoundException);
        }

        return result;
    }

    /**
     * Creates the compiler.
     * @param regexpManager the RegexpManager instance.
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
        return regexpEngine.createCompiler();
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

    /**
     * Checks whether given object contains valid version information or not.
     * @param version the version object to check.
     * @return true if given object contains valid version information,
               according to CVS syntax.
     */
    public boolean isValid(Version version)
    {
        return
            (   (version != null)
             && (isValid(version.getVersionInformation())));
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
     * Retrieves the current version of this class. It's defined because
     * this is a utility class that cannot be instantiated.
     * @return the object with class version information.
     */
    public static Version getClassVersion()
    {
        return VERSION;
    }
}
