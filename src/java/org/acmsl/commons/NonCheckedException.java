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
 * Description: Represents any exception which doesn't require to be catched.
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
 * Importing project classes.
 */
import org.acmsl.commons.BundleI14able;
import org.acmsl.commons.patterns.Decorator;
import org.acmsl.commons.patterns.I14able;

/*
 * Importing some JDK classes.
 */
import java.lang.RuntimeException;
import java.util.Locale;

/**
 * Represents any exception which doesn't require to be catched.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armendariz</a>
 * @version $Revision$
 */
public abstract class NonCheckedException
    extends     RuntimeException
    implements  Decorator,
                I14able
{
    /**
     * The wrapped object.
     */
    private BundleI14able m__BundleI14able;

    /**
     * Creates a NonCheckedException with given message.
     * @param messageKey the key to build the exception message.
     * @param params the parameters to build the exception message.
     * @precondition messageKey != null
     * @precondition params != null
     */
    protected NonCheckedException(
        final String messageKey,
        final Object[] params)
    {
        super(messageKey);
        immutableSetBundleI14able(
            new _BundleI14able(
                messageKey,
                params,
                retrieveExceptionsBundleName()) {});
    }

    /**
     * Creates a NonCheckedException with given cause.
     * @param messageKey the key to build the exception message.
     * @param params the parameters to build the exception message.
     * @param cause the error cause.
     * @precondition messageKey != null
     * @precondition params != null
     * @precondition cause != null
     */
    protected NonCheckedException(
        final String messageKey,
        final Object[] params,
        final Throwable cause)
    {
        super(messageKey, cause);
        immutableSetBundleI14able(
            new _BundleI14able(
                messageKey,
                params,
                retrieveExceptionsBundleName()) {});
    }

    /**
     * Specifies the wrapped localized throwable.
     * @param bundleI14able the instance to wrap.
     */
     private void immutableSetBundleI14able(
         final BundleI14able bundleI14able)
     {
         m__BundleI14able = bundleI14able;
     }

    /**
     * Specifies the wrapped localized throwable.
     * @param bundleI14able the instance to wrap.
     */
     protected void setBundleI14able(
         final BundleI14able bundleI14able)
     {
         immutableSetBundleI14able(bundleI14able);
     }

    /**
     * Retrieves the wrapped throwable instance.
     * @return such instance.
     */
    protected BundleI14able getBundleI14able()
    {
        return m__BundleI14able;
    }

    /**
     * Retrieves the parameters needed to build the internationalized message.
     * @return such parameters.
     */
    public Object[] getParams()
    {
        return getParams(getBundleI14able());
    }

    /**
     * Retrieves the parameters needed to build the internationalized message.
     * @param bundleI14able the localized throwable.
     * @return such parameters.
     */
    protected Object[] getParams(final BundleI14able bundleI14able)
    {
        return bundleI14able.getParams();
    }

    /**
     * Retrieves the bundle name.
     * @return such name.
     */
    public String getBundleName()
    {
        return getBundleName(getBundleI14able());
    }

    /**
     * Retrieves the bundle name.
     * @param bundleI14able the localized throwable.
     * @return such name.
     */
    protected String getBundleName(final BundleI14able bundleI14able)
    {
        return bundleI14able.getBundleName();
    }

    /**
     * Retrieves the internationalized message.
     * @return such message.
     */
    public String getMessage()
    {
        return getMessage(getBundleI14able());
    }

    /**
     * Retrieves the internationalized message.
     * @param bundleI14able the localized throwable.
     * @return such message.
     */
    protected String getMessage(final BundleI14able bundleI14able)
    {
        return bundleI14able.toString();
    }

    /**
     * Retrieves the internationalized message for given locale.
     * @param locale the desired locale.
     * @return such message.
     * @precondition locale != null
     */
    public String getMessage(final Locale locale)
    {
        return getMessage(locale, getBundleI14able());
    }

    /**
     * Retrieves the internationalized message for given locale.
     * @param locale the desired locale.
     * @param bundleI14able the localized throwable.
     * @return such message.
     */
    protected String getMessage(
        final Locale locale, final BundleI14able bundleI14able)
    {
        return bundleI14able.toString(locale);
    }

    /**
     * Retrieves the exceptions bundle.
     * @return such bundle name.
     */
    protected String retrieveExceptionsBundleName()
    {
        return
            retrieveExceptionsBundleName(
                CommonsBundleRepository.getInstance());
    }

    /**
     * Retrieves the exceptions bundle.
     * @param bundleRepository the bundle repository.
     * @return such bundle name.
     * @precondition bundleRepository != null
     */
    protected String retrieveExceptionsBundleName(
        final CommonsBundleRepository bundleRepository)
    {
        return bundleRepository.getExceptionsBundleName();
    }

    /**
     * Retrieves the text defined for the exception.
     * @param locale the locale.
     * @return such text, using given locale.
     */
    public String toString(final Locale locale)
    {
        return getMessage(locale);
    }

    /**
     * Retrieves the text defined for the exception.
     * @return such text, using the default locale.
     */
    public String toString()
    {
        return getMessage();
    }

    /**
     * BundleI14able suited for NonCheckedException class.
     * @author <a href="mailto:jsanleandro@yahoo.es"
               >Jose San Leandro Armendariz</a>
     * @version $Revision$ $Date$
     */
    protected class _BundleI14able
        extends  BundleI14able
    {
        /**
         * Creates a _BundleI14able with given information.
         * @param messageKey the key to build the exception message.
         * @param params the parameters to build the exception message.
         * @param bundleName the name of the bundle.
         */
        protected _BundleI14able(
            final String messageKey,
            final Object[] params,
            final String bundleName)
        {
            super(messageKey, params, bundleName);
        }
    }
}
