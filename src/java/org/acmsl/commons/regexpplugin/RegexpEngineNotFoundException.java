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
 * Description: Designed to be thrown at runtime when the
 *              specified regexp engine is not found.
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
import org.acmsl.commons.ConfigurationException;

/**
 * Designed to be thrown at runtime when the specified regexp engine
 * is not found.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class RegexpEngineNotFoundException
    extends  ConfigurationException
{
    /**
     * The key for this exception in the bundle.
     */
    protected static final String MESSAGE_KEY = "regexp.engine.not.found";

    /**
     * Engine name.
     */
    private String m__strEngineName;

    /**
     * Engine version.
     */
    private String m__strEngineVersion;

    /**
     * Engine package name.
     */
    private String m__strPackageName;

    /**
     * Compiler class name.
     */
    private String m__strCompilerClassName;

    /**
     * Matcher class name.
     */
    private String m__strMatcherClassName;

    /**
     * Helper class name.
     */
    private String m__strHelperClassName;

    /**
     * Just constructs the exception with the default logic defined in
     * its super class.
     * @param engineName the name of the Regexp implementation whose
     * instatiation has failed.
     * @param engineVersion the version of the engine.
     * @param packageName the name of the engine class package.
     * @param compilerClassName the compiler class name.
     * @param matcherClassName the matcher class name.
     * @param helperClassName the helper class name.
     * @precondition engineName != null
     */
    public RegexpEngineNotFoundException(
        String engineName,
        String engineVersion,
        String packageName,
        String compilerClassName,
        String matcherClassName,
        String helperClassName)
    {
        super(
            MESSAGE_KEY,
            new Object[]
            {
                engineName,
                engineVersion,
                packageName,
                compilerClassName,
                matcherClassName,
                helperClassName
            });

        inmutableSetEngineName(engineName);
        inmutableSetEngineVersion(engineVersion);
        inmutableSetEnginePackage(packageName);
        inmutableSetCompilerClassName(compilerClassName);
        inmutableSetMatcherClassName(matcherClassName);
        inmutableSetHelperClassName(helperClassName);
    }

    /**
     * Specifies the implementation name.
     * @param engineName the implementation name.
     * @precondition engineName != null
     */
    private void inmutableSetEngineName(final String engineName)
    {
        m__strEngineName = engineName;
    }

    /**
     * Specifies the implementation name.
     * @param engineName the implementation name.
     * @precondition engineName != null
     */
    protected void setEngineName(final String engineName)
    {
        inmutableSetEngineName(engineName);
    }

    /**
     * Retrieves the implementation name.
     * @return implementation name.
     */
    public String getEngineName()
    {
        return m__strEngineName;
    }

    /**
     * Specifies the implementation version.
     * @param engineVersion the version of the implementation.
     */
    private void inmutableSetEngineVersion(final String engineVersion)
    {
        m__strEngineVersion = engineVersion;
    }

    /**
     * Specifies the implementation version.
     * @param engineVersion the version of the implementation.
     */
    protected void setEngineVersion(final String engineVersion)
    {
        inmutableSetEngineVersion(engineVersion);
    }

    /**
     * Retrieves the implementation version. It's not calculated,
     * just based on what the implementation provides.
     * @return the version of the implementation.
     */
    public String getEngineVersion()
    {
        return m__strEngineVersion;
    }

    /**
     * Specifies the Regexp implementation package name.
     * @param enginePackage the engine package.
     */
    private void inmutableSetEnginePackage(final String enginePackage)
    {
        m__strPackageName = enginePackage;
    }

    /**
     * Specifies the Regexp implementation package name.
     * @param enginePackage the engine package.
     */
    protected void setEnginePackage(final String enginePackage)
    {
        inmutableSetEnginePackage(enginePackage);
    }

    /**
     * Retrieves the Regexp implementation package name.
     * @return the engine package.
     */
    public String getEnginePackage()
    {
        return m__strPackageName;
    }

    /**
     * Sets the compiler class name of the Regexp implementation.
     * @param compilerClassName the compiler class name.
     */
    private void inmutableSetCompilerClassName(final String compilerClassName)
    {
        m__strCompilerClassName = compilerClassName;
    }

    /**
     * Specifies the compiler class name of the Regexp implementation.
     * @param compilerClassName the compiler class name.
     */
    protected void setCompilerClassName(final String compilerClassName)
    {
        inmutableSetCompilerClassName(compilerClassName);
    }

    /**
     * Retrieves the compiler class name of the Regexp implementation
     * whose instantiation has thrown the runtime exception.
     * @return the compiler class name.
     */
    public String getCompilerClassName()
    {
        return m__strCompilerClassName;
    }

    /**
     * Specifies the matcher class name of the Regexp implementation.
     * @param matcherClassName the matcher class name.
     */
    private void inmutableSetMatcherClassName(final String matcherClassName)
    {
        m__strMatcherClassName = matcherClassName;
    }

    /**
     * Specifies the matcher class name of the Regexp implementation.
     * @param matcherClassName the matcher class name.
     */
    protected void setMatcherClassName(final String matcherClassName)
    {
        inmutableSetMatcherClassName(matcherClassName);
    }

    /**
     * Retrieves the matcher class name of the Regexp implementation
     * whose instantiation has thrown the runtime exception.
     * @return the matcher class name.
     */
    public String getMatcherClassName()
    {
        return m__strMatcherClassName;
    }

    /**
     * Specifies the helper class name of the Regexp implementation.
     * @param helperClassName the helper class name.
     */
    private void inmutableSetHelperClassName(final String helperClassName)
    {
        m__strHelperClassName = helperClassName;
    }

    /**
     * Specifies the helper class name of the Regexp implementation.
     * @param helperClassName the helper class name.
     */
    protected void setHelperClassName(final String helperClassName)
    {
        inmutableSetHelperClassName(helperClassName);
    }

    /**
     * Retrieves the helper class name of the Regexp implementation
     * whose instantiation has thrown the runtime exception.
     * @return the helper class name.
     */
    public String getHelperClassName()
    {
        return m__strHelperClassName;
    }
}
