/*
                        ACM-SL Commons

    Copyright (C) 2002-2003  Jose San Leandro Armendáriz
                             jsanleandro@yahoo.es
                             chousz@yahoo.com

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
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

    Thanks to ACM S.L. for distributing this library under the GPL license.
    Contact info: jsr000@terra.es
    Postal Address: c/Playa de Lagoa, 1
                    Urb. Valdecaba&ntilde;as
                    Boadilla del monte
                    28660 Madrid
                    Spain

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armend&aacute;riz
 *
 * Description: Provides some stateless helper regexp-related services.
 *
 * Last modified by: $Author$ at $Date$
 *
 * File version: $Revision$
 *
 * Project version: $Name$
 *
 * $Id$
 *
 */
package org.acmsl.commons.utils.regexp;

/*
 * Importing some project classes.
 */
import org.acmsl.commons.patterns.Singleton;
import org.acmsl.commons.patterns.Utils;
import org.acmsl.commons.regexpplugin.Compiler;
import org.acmsl.commons.regexpplugin.MalformedPatternException;
import org.acmsl.commons.regexpplugin.Matcher;
import org.acmsl.commons.regexpplugin.MatchResult;
import org.acmsl.commons.regexpplugin.Pattern;
import org.acmsl.commons.regexpplugin.RegexpEngine;
import org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException;
import org.acmsl.commons.regexpplugin.RegexpManager;
import org.acmsl.commons.regexpplugin.RegexpPluginMisconfiguredException;
import org.acmsl.commons.utils.StringValidator;

/*
 * Importing some JDK classes.
 */
import java.lang.ref.WeakReference;

/**
 * Provides some stateless helper regexp-related services.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision$
 */
public abstract class RegexpUtils
    implements  Utils,
                Singleton
{
    /**
     * Compiler used.
     */
    private static Compiler m__Compiler;

    /**
     * Singleton implemented as a weak reference.
     */
    private static WeakReference m__Singleton;

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected RegexpUtils() {};

    /**
     * Specifies a new weak reference.
     * @param utils the utils instance to use.
     * @precondition utils != null
     */
    protected static void setReference(final RegexpUtils utils)
    {
        m__Singleton = new WeakReference(utils);
    }

    /**
     * Retrieves the weak reference.
     * @return such reference.
     */
    protected static WeakReference getReference()
    {
        return m__Singleton;
    }

    /**
     * Retrieves a RegexpUtils instance.
     * @return such instance.
     * @throws RegexpEngineNotFoundException if the system is not configured
     * properly in order to provide regexp services.
     */
    public static RegexpUtils getInstance()
        throws  RegexpEngineNotFoundException
    {
        RegexpUtils result = null;

        WeakReference t_Reference = getReference();

        if  (t_Reference != null) 
        {
            result = (RegexpUtils) t_Reference.get();
        }

        if  (result == null) 
        {
            result = new RegexpUtils() {};
        }

        Compiler t_Compiler = result.getRegexpCompiler();

        if  (t_Compiler == null)
        {
            synchronized (result)
            {
                t_Compiler = createCompiler(RegexpManager.getInstance());

                t_Compiler.setMultiline(false);
                t_Compiler.setCaseSensitive(false);

                result.immutableSetRegexpCompiler(t_Compiler);

                setReference(result);
            }
        }
        
        return result;
    }

    /**
     * Specifies the regexp compiler.
     * @param compiler the compiler.
     * @precondition compiler != null
     */
    private void immutableSetRegexpCompiler(final Compiler compiler)
    {
        m__Compiler = compiler;
    }

    /**
     * Specifies the regexp compiler.
     * @param compiler the compiler.
     * @precondition compiler != null
     */
    protected void setRegexpCompiler(final Compiler compiler)
    {
        immutableSetRegexpCompiler(compiler);
    }

    /**
     * Retrieves the regexp compiler.
     * @return such compiler.
     */
    public Compiler getRegexpCompiler()
    {
        return m__Compiler;
    }

    /**
     * Retrieves a compiled pattern for given regexp.
     * @param regexp the regexp to compile.
     * @return the regexp pattern.
     * @throws MalformedPatternException if the pattern is invalid.
     * @precondition regexp != null
     */
    public Pattern buildPattern(final String regexp)
      throws  MalformedPatternException
    {
        return buildPattern(regexp, getRegexpCompiler());
    }

    /**
     * Retrieves a compiled pattern for given regexp.
     * @param regexp the regexp to compile.
     * @param compiler the regexp compiler.
     * @return the regexp pattern.
     * @throws MalformedPatternException if the pattern is invalid.
     * @precondition regexp != null
     * @precondition compiler != null
     */
    protected Pattern buildPattern(
        final String regexp, final Compiler compiler)
      throws  MalformedPatternException
    {
        return compile(compiler, regexp);
    }

    /**
     * Compiles given pattern.
     * @param compiler the regexp compiler.
     * @param pattern the regexp pattern to compile.
     * @return the compiled pattern.
     * @throws MalformedPatternException if the pattern is invalid.
     * @precondition compiler != null
     * @precondition pattern != null
     */
    protected static Pattern compile(
        final Compiler compiler, final String pattern)
      throws  MalformedPatternException
    {
        return compiler.compile(pattern);
    }

    /**
     * Creates the compiler.
     * @return the regexp compiler.
     * @throws RegexpEngineNotFoundException if a suitable instance
     * cannot be created.
     * @throws RegexpPluginMisconfiguredException if RegexpPlugin is
     * misconfigured.
     */
    protected static synchronized Compiler createCompiler()
      throws RegexpEngineNotFoundException,
             RegexpPluginMisconfiguredException
    {
        return createCompiler(RegexpManager.getInstance());
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
}
