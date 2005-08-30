/*
                        ACM-SL Commons

    Copyright (C) 2002-2004  Jose San Leandro Armendáriz
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
 * Description: Provides the bundles used by ACM-SL Commons.
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
package org.acmsl.commons;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Repository;
import org.acmsl.commons.patterns.Singleton;

/*
 * Importing some JDK classes.
 */
import java.lang.ref.WeakReference;

/**
 * Provides the bundles used by ACM-SL Commons.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$ $Date$
 */
public class CommonsBundleRepository
    implements  Repository,
                Singleton
{
    /**
     * The exceptions system property.
     */
    protected static final String EXCEPTIONS_SYSTEM_PROPERTY = "org.acmsl.commons.exceptions";

    /**
     * The exceptions bundle.
     */
    protected static final String EXCEPTIONS_BUNDLE = "commons-exceptions";

    /**
     * The constants bundle.
     */
    protected static final String CONSTANTS_BUNDLE = "commons-constants";

    /**
     * The grammar system property.
     */
    protected static final String GRAMMAR_SYSTEM_PROPERTY = "org.acmsl.commons.utils.grammar";

    /**
     * The grammar bundle.
     */
    protected static final String GRAMMAR_BUNDLE = "commons-grammar";

    /**
     * Singleton implemented as a weak reference.
     */
    private static WeakReference m__Singleton;

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected CommonsBundleRepository() {};

    /**
     * Specifies a new weak reference.
     * @param repository the repository instance to use.
     * @precondition repository != null
     */
    protected static void setReference(
        final CommonsBundleRepository repository)
    {
        m__Singleton = new WeakReference(repository);
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
     * Retrieves a (the) repository instance.
     * @return such instance.
     */
    public static CommonsBundleRepository getInstance()
    {
        CommonsBundleRepository result = null;

        WeakReference t_Reference = getReference();

        if  (t_Reference != null) 
        {
            result = (CommonsBundleRepository) t_Reference.get();
        }

        if  (result == null) 
        {
            result = new CommonsBundleRepository();

            setReference(result);
        }
        
        return result;
    }

    /**
     * Retrieves the exceptions' system property.
     * @return such property.
     */
    public String getExceptionsBundleProperty()
    {
        return EXCEPTIONS_SYSTEM_PROPERTY;
    }

    /**
     * Retrieves the exceptions bundle name.
     * @return such name.
     */
    public String getExceptionsBundleName()
    {
        return EXCEPTIONS_BUNDLE;
    }

    /**
     * Retrieves the constants bundle name.
     * @return such name.
     */
    public String getConstantsBundleName()
    {
        return CONSTANTS_BUNDLE;
    }

    /**
     * Retrieves the grammar's system property.
     * @return such property.
     */
    public String getGrammarBundleProperty()
    {
        return GRAMMAR_SYSTEM_PROPERTY;
    }

    /**
     * Retrieves the grammar bundle name.
     * @return such name.
     */
    public String getGrammarBundleName()
    {
        return GRAMMAR_BUNDLE;
    }
}
