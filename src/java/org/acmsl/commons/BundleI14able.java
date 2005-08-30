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
 * Description: Represents Throwable instances with support for messages
 *              in different languages.
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
import org.acmsl.commons.patterns.I14able;
import org.acmsl.commons.utils.StringValidator;

/*
 * Importing some JDK classes.
 */
import java.io.Serializable;
import java.lang.Throwable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/*
 * Importing Commons Logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Represents Throwable instances with support for messages in different
 * languages.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armendariz</a>
 * @version $Revision$
 */
public class BundleI14able
    implements  I14able
{
    /**
     * An empty object array.
     */
    protected static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];

    /**
     * The message key.
     */
    private String m__strMessageKey;

    /**
     * The message params.
     */
    private Object[] m__aParams;

    /**
     * The bundle name.
     */
    private String m__strBundleName;

    /**
     * Creates a BundleI14able with given message.
     * @param messageKey the key to build the exception message.
     * @param params the parameters to build the exception message.
     * @param bundleName the name of the bundle.
     * @precondition messageKey != null
     * @precondition params != null
     * @precondition bundleName != null
     */
    protected BundleI14able(
        final String messageKey,
        final Object[] params,
        final String bundleName)
    {
        immutableSetMessageKey(messageKey);
        immutableSetParams(params);
        immutableSetBundleName(bundleName);
    }

    /**
     * Creates a BundleI14able with given message.
     * @param messageKey the key to build the exception message.
     * @param bundleName the name of the bundle.
     * @precondition messageKey != null
     * @precondition bundleName != null
     */
    protected BundleI14able(
        final String messageKey,
        final String bundleName)
    {
        this(messageKey, EMPTY_OBJECT_ARRAY, bundleName);
    }

    /**
     * Specifies the message key.
     * @param key the message key.
     * @precondition key != null
     */
    private void immutableSetMessageKey(final String key)
    {
        m__strMessageKey = key;
    }

    /**
     * Specifies the message key.
     * @param key the message key.
     * @precondition key != null
     */
    protected void setMessageKey(final String key)
    {
        immutableSetMessageKey(key);
    }

    /**
     * Retrieves the message key.
     * @return such key.
     */
    public String getMessageKey()
    {
        return m__strMessageKey;
    }

    /**
     * Specifies the parameters to build the internationalized message.
     * @param params the parameters.
     * @precondition params != null
     */
     private void immutableSetParams(final Object[] params)
     {
         m__aParams = params;
     }

    /**
     * Specifies the parameters to build the internationalized message.
     * @param params the parameters.
     * @precondition params != null
     */
     protected void setParams(final Object[] params)
     {
         immutableSetParams(params);
     }

    /**
     * Retrieves the parameters needed to build the internationalized message.
     * @return such parameters.
     */
    public Object[] getParams()
    {
        return m__aParams;
    }

    /**
     * Specifies the bundle name.
     * @param name the bundle name.
     * @precondition name != null
     */
    private void immutableSetBundleName(final String name)
    {
        m__strBundleName = name;
    }

    /**
     * Specifies the bundle name.
     * @param name the bundle name.
     * @precondition name != null
     */
    protected void setBundleName(final String name)
    {
        immutableSetBundleName(name);
    }

    /**
     * Retrieves the bundle name.
     * @return such name.
     */
    public String getBundleName()
    {
        return m__strBundleName;
    }

    /**
     * Retrieves the internationalized message.
     * @return such message.
     */
    public String toString()
    {
        return toString(Locale.getDefault());
    }

    /**
     * Retrieves the internationalized message for given locale.
     * @param locale the desired locale.
     * @return such message.
     * @precondition locale != null
     */
    public String toString(final Locale locale)
    {
        return
            buildMessage(
                getMessageKey(), getParams(), locale, getBundleName());
    }

    /**
     * Builds an internationalized message for given key, parameters
     * and locale.
     * @param key the message key.
     * @param params the message parameters.
     * @param locale the locale.
     * @param bundleName the name of the bundle.
     * @return the customized message.
     * @precondition key != null
     * @precondition params != null
     * @precondition locale != null
     * @precondition bundleName != null
     */
    protected String buildMessage(
        final String key,
        final Object[] params,
        final Locale locale,
        final String bundleName)
    {
        return
            buildMessage(
                key, params, ResourceBundle.getBundle(bundleName, locale));
    }

    /**
     * Builds an internationalized message for given key, parameters
     * and locale.
     * @param key the message key.
     * @param params the message parameters.
     * @param bundle the resource bundle.
     * @return the customized message.
     * @precondition key != null
     * @precondition params != null
     * @precondition bundle != null
     */
    protected String buildMessage(
        final String key,
        final Object[] params,
        final ResourceBundle bundle)
    {
        return
            buildMessage(
                bundle.getString(key),
                translateParams(
                    params, bundle, StringValidator.getInstance()));
    }

    /**
     * Translates any elements of given array for which translation is
     * defined in the bundle.
     * @param params the parameters.
     * @param bundle the bundle.
     * @param stringValidator the StringValidator instance.
     * @return the translated parameters.
     * @precondition params != null
     * @precondition bundle != null
     * @precondition stringValidator != null
     */
    protected Object[] translateParams(
        final Object[] params,
        final ResourceBundle bundle,
        final StringValidator stringValidator)
    {
        Collection t_cResult = new ArrayList();

        for  (int t_iIndex = 0; t_iIndex < params.length; t_iIndex++)
        {
            String t_strTranslation = null;

            try
            {
                t_strTranslation = bundle.getString("" + params[t_iIndex]);
            }
            catch  (final MissingResourceException exception)
            {
                LogFactory.getLog(getClass()).debug(
                    "No parameter translation found for: " + params[t_iIndex]);
            }

            if  (stringValidator.isEmpty(t_strTranslation))
            {
                t_cResult.add(params[t_iIndex]);
            }
            else
            {
                t_cResult.add(t_strTranslation);
            }
        }

        return t_cResult.toArray(params);
    }

    /**
     * Builds an internationalized message for given key, parameters and
     * locale.
     * @param message the possibly parameterized message.
     * @param params the message parameters.
     * @return the customized message.
     * @precondition message != null
     * @precondition params != null
     */
    protected String buildMessage(final String message, final Object[] params)
    {
        return new MessageFormat(message).format(params);
    }
}
