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
 * Description: Provides basic methods to allow specialized classes to
 *              manage the configuration of applications.
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
import org.acmsl.commons.patterns.Manager;

/*
 * Importing some JDK classes.
 */
import java.io.InputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Properties;

/*
 * Importing Apache Commons Logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Provides basic methods to allow specialized classes to manage th
 * configuration of applications.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$ $Date$
 */
public abstract class ConfigurationManager
    implements  Manager
{
    /**
     * In-memory configuration settings.
     */
    private Properties m__Properties = null;

    /**
     * Default protected constructor to avoid accidental instantiation.
     */
    protected ConfigurationManager() {}

    /**
     * Retrieves the properties file name.
     * @return such name.
     */
    protected abstract String getPropertiesFileName();

    /**
     * Retrieves the configuration settings.
     * @return the properties.
     */
    public Properties getProperties()
    {
        return getProperties(getPropertiesFileName());
    }

    /**
     * Retrieves the configuration settings.
     * @param propertiesFileName the name of the properties file.
     * @return the properties.
     * @precondition propertiesFileName != null
     */
    public synchronized final Properties getProperties(
        final String propertiesFileName)
    {
        if  (m__Properties == null)
        {
            m__Properties = new Properties();
        }

        if  (m__Properties.size() == 0)
        {
            loadProperties(m__Properties, propertiesFileName);
        }

        return m__Properties;
    }

    /**
     * Retrieves the property.
     * @param key the setting name.
     * @return the configuration value associated to such setting.
     * @precondition key != null
     */
    public String getProperty(final String key)
    {
        return getProperty(key, getProperties());
    }

    /**
     * Retrieves the property.
     * @param key the setting name.
     * @param properties the property collection.
     * @return the configuration value associated to such setting.
     * @precondition key != null
     * @precondition properties != null
     */
    protected String getProperty(final String key, final Properties properties)
    {
        return properties.getProperty(key);
    }

    /**
     * Loads the configuration from a property file.
     * @param properties where to store the settings.
     * @param propertiesFileName the properties file.
     * @precondition properties != null
     * @precondition propertiesFileName != null
     */
    public final void loadProperties(
        final Properties properties, final String propertiesFileName)
    {
        loadProperties(properties, propertiesFileName, true);
    }

    /**
     * Loads the configuration from a property file.
     * @param properties where to store the settings.
     * @param propertiesFileName the properties file.
     * @param retry whether to retry to load the properties appending an slash
     * at the begining of the file name or not.
     * @precondition properties != null
     * @precondition propertiesFileName != null
     */
    public synchronized void loadProperties(
        final Properties properties,
        final String propertiesFileName,
        final boolean retry)
    {
        InputStream t_isPropertiesFile = null;

        // Loading properties
        try
        {
            t_isPropertiesFile =
                getClass().getResourceAsStream(propertiesFileName);

            if  (t_isPropertiesFile != null)
            {
                properties.load(t_isPropertiesFile);
            }
            else if (retry)
            {
                LogFactory.getLog(getClass()).debug(
                      "Properties file: "
                    + propertiesFileName
                    + " not found. Trying /"
                    + propertiesFileName);

                loadProperties(
                    properties,
                    "/" + propertiesFileName,
                    false);
            }
            else
            {
                LogFactory.getLog(getClass()).warn(
                      "Could not read configuration properties from file: "
                    + propertiesFileName);
            }
        }
        catch  (final IOException ioException)
        {
            if  (retry)
            {
                LogFactory.getLog(getClass()).debug(
                      "Properties file: "
                    + propertiesFileName
                    + " not found. Trying /"
                    + propertiesFileName);

                loadProperties(
                    properties,
                    "/" + propertiesFileName,
                    false);
            }
            else
            {
                LogFactory.getLog(getClass()).warn(
                      "Could not load configuration properties from file: "
                    + propertiesFileName);
            }
        }
    }
}
