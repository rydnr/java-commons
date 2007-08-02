/*
                        ACM-SL Commons

    Copyright (C) 2002-2004  Jose San Leandro Armendáriz
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
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

    Thanks to ACM S.L. for distributing this library under the GPL license.
    Contact info: jose.sanleandro@acm-sl.com
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
 * Last modified by: $Author: chous $ at $Date: 2006-06-14 21:01:54 +0200 (Wed, 14 Jun 2006) $
 *
 * File version: $Revision: 550 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: ConfigurationManager.java 550 2006-06-14 19:01:54Z chous $
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
 * @version $Revision: 550 $ $Date: 2006-06-14 21:01:54 +0200 (Wed, 14 Jun 2006) $
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
    protected ConfigurationManager() {};

    /**
     * Retrieves the properties file name.
     * @return such name.
     */
    protected abstract String getPropertiesFileName();

    /**
     * Specifies the configuration settings.
     * @param properties the properties.
     */
    protected final void immutableSetProperties(final Properties properties)
    {
        m__Properties = properties;
    }
    
    /**
     * Retrieves the configuration settings.
     * @return the properties.
     * @precondition propertiesFileName != null
     */
    protected final Properties immutableGetProperties()
    {
        return m__Properties;
    }
    
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
     * @param propertiesFileName the properties' file name.
     * @return the properties.
     * @precondition propertiesFileName != null
     */
    public synchronized final Properties getProperties(
        final String propertiesFileName)
    {
        return
            getProperties(
                propertiesFileName,
                immutableGetProperties());
    }
    
    /**
     * Retrieves the configuration settings.
     * @param propertiesFileName the properties' file name.
     * @param properties the properties.
     * @return the properties.
     * @precondition propertiesFileName != null
     */
    protected final Properties getProperties(
        final String propertiesFileName,
        final Properties properties)
    {
        Properties auxProperties = properties;
        
        if  (auxProperties == null)
        {
            auxProperties = new Properties();
            immutableSetProperties(auxProperties);
        }

        if  (auxProperties.size() == 0)
        {
            loadProperties(auxProperties, propertiesFileName);
        }

        return auxProperties;
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
     * Retrieves the array property.
     * @param name the name.
     * @return the array of values.
     * @precondition name != null
     */
    public String[] getStringArray(final String name)
    {
        return getStringArray(name, getProperty(name));
    }

    /**
     * Retrieves the array property.
     * @param name the name.
     * @param value the value.
     * @return the array of values.
     * @precondition name != null
     */
    protected String[] getStringArray(final String name, final String value)
    {
        String[] result = null;

        if  (value != null)
        {
            result = value.split(",");
        }

        return result;
    }

    /**
     * Specifies an array property.
     * @param name the name.
     * @param value the value.
     */
    public void setStringArray(final String name, final String[] value)
    {
        StringBuffer propertyValue = new StringBuffer();

        for  (int index = 0; index < value.length; index++)
        {
            propertyValue.append(value[index]);

            if  (index < value.length - 1)
            {
                propertyValue.append(",");
            }
        }
    }

    /**
     * Retrieves the property.
     * @param key the setting name.
     * @param value the value.
     * @precondition key != null
     * @precondition value != null
     */
    public void setProperty(final String key, final String value)
    {
        setProperty(key, value, getProperties());
    }

    /**
     * Specifies the property.
     * @param key the setting name.
     * @param value the value.
     * @param properties the property collection.
     * @precondition key != null
     * @precondition value != null
     * @precondition properties != null
     */
    protected void setProperty(
        final String key, final String value, final Properties properties)
    {
        properties.setProperty(key, value);
    }

    /**
     * Retrieves a concrete integer setting.
     * @param name the name.
     * @return such parameter.
     * @precondition name != null
     */
    public int getIntProperty(final String name)
    {
        return getIntProperty(name, getProperty(name));
    }

    /**
     * Retrieves a concrete integer setting.
     * @param name the name.
     * @param value the value.
     * @return such parameter.
     * @precondition name != null
     * @precondition value != null
     */
    protected int getIntProperty(
        final String name, final String value)
    {
        int result = -1;

        try
        {
            result = Integer.parseInt(value);
        }
        catch  (final NumberFormatException numberFormatException)
        {
            LogFactory.getLog(ConfigurationManager.class + "." + getClass()).fatal(
                "Invalid integer setting (" + name + "): " + value,
                numberFormatException);
        }

        return result;
    }

    /**
     * Specifies a concrete integer setting.
     * @param name the name.
     * @param value the value.
     * @precondition name != null
     */
    public void setIntProperty(final String name, final int value)
    {
        setProperty(name, "" + value);
    }

    /**
     * Retrieves an array of integers associated to given name.
     * @param name the name.
     * @return such value.
     * @precondition name != null
     */
    public int[] getIntArray(final String name)
    {
        return getIntArray(name, getProperty(name));
    }

    /**
     * Retrieves an array of integers associated to given name.
     * @param name the name.
     * @param value the property value.
     * @return such parsed value.
     * @precondition name != null
     */
    protected int[] getIntArray(final String name, final String value)
    {
        int[] result = null;

        if  (value != null)
        {
            try
            {
                String[] splittedValue = value.split(",");

                result = new int[splittedValue.length];

                for  (int index = 0; index < result.length; index++)
                {
                    result[index] = Integer.parseInt(splittedValue[index]);
                }
            }
            catch  (final NumberFormatException numberFormatException)
            {
                LogFactory.getLog(ConfigurationManager.class + "." + getClass()).fatal(
                    "Invalid integer array setting (" + name + "): " + value,
                    numberFormatException);
            }
        }

        return result;
    }

    /**
     * Specifies an integer setting.
     * @param name the name.
     * @param value the value.
     * @precondition name != null
     * @precondition value != null
     */
    public void setIntArray(final String name, final int[] value)
    {
        StringBuffer propertyValue = new StringBuffer();

        for  (int index = 0; index < value.length; index++)
        {
            propertyValue.append("" + value[index]);

            if  (index < value.length - 1)
            {
                propertyValue.append(",");
            }
        }
    }

    /**
     * Retrieves a concrete long setting.
     * @param name the name.
     * @return such parameter.
     * @precondition name != null
     */
    public long getLongProperty(final String name)
    {
        return getLongProperty(name, getProperty(name));
    }

    /**
     * Retrieves a concrete long setting.
     * @param name the name.
     * @param value the value.
     * @return such parameter.
     * @precondition name != null
     * @precondition value != null
     */
    protected long getLongProperty(
        final String name, final String value)
    {
        long result = -1;

        try
        {
            result = Long.parseLong(value);
        }
        catch  (final NumberFormatException numberFormatException)
        {
            LogFactory.getLog(ConfigurationManager.class + "." + getClass()).fatal(
                "Invalid long setting (" + name + "): " + value,
                numberFormatException);
        }

        return result;
    }

    /**
     * Specifies a concrete long setting.
     * @param name the name.
     * @param value the value.
     * @precondition name != null
     */
    public void setLongProperty(final String name, final long value)
    {
        setProperty(name, "" + value);
    }

    /**
     * Retrieves an array of longs associated to given name.
     * @param name the name.
     * @return such value.
     * @precondition name != null
     */
    public long[] getLongArray(final String name)
    {
        return getLongArray(name, getProperty(name));
    }

    /**
     * Retrieves an array of longs associated to given name.
     * @param name the name.
     * @param value the property value.
     * @return such parsed value.
     * @precondition name != null
     */
    protected long[] getLongArray(final String name, final String value)
    {
        long[] result = null;

        if  (value != null)
        {
            try
            {
                String[] splittedValue = value.split(",");

                result = new long[splittedValue.length];

                for  (int index = 0; index < result.length; index++)
                {
                    result[index] = Long.parseLong(splittedValue[index]);
                }
            }
            catch  (final NumberFormatException numberFormatException)
            {
                LogFactory.getLog(ConfigurationManager.class + "." + getClass()).fatal(
                    "Invalid long array setting (" + name + "): " + value,
                    numberFormatException);
            }
        }

        return result;
    }

    /**
     * Specifies an long setting.
     * @param name the name.
     * @param value the value.
     * @precondition name != null
     * @precondition value != null
     */
    public void setLongArray(final String name, final long[] value)
    {
        StringBuffer propertyValue = new StringBuffer();

        for  (int index = 0; index < value.length; index++)
        {
            propertyValue.append("" + value[index]);

            if  (index < value.length - 1)
            {
                propertyValue.append(",");
            }
        }
    }

    /**
     * Retrieves a concrete double setting.
     * @param name the name.
     * @return such parameter.
     * @precondition name != null
     */
    public double getDoubleProperty(final String name)
    {
        return getDoubleProperty(name, getProperty(name));
    }

    /**
     * Retrieves a concrete double setting.
     * @param name the name.
     * @param value the value.
     * @return such parameter.
     * @precondition name != null
     * @precondition value != null
     */
    protected double getDoubleProperty(
        final String name, final String value)
    {
        double result = -1;

        try
        {
            result = Double.parseDouble(value);
        }
        catch  (final NumberFormatException numberFormatException)
        {
            LogFactory.getLog(ConfigurationManager.class + "." + getClass()).fatal(
                "Invalid double setting (" + name + "): " + value,
                numberFormatException);
        }

        return result;
    }

    /**
     * Specifies a concrete double setting.
     * @param name the name.
     * @param value the value.
     * @precondition name != null
     */
    public void setDoubleProperty(final String name, final double value)
    {
        setProperty(name, "" + value);
    }

    /**
     * Retrieves an array of doubles associated to given name.
     * @param name the name.
     * @return such value.
     * @precondition name != null
     */
    public double[] getDoubleArray(final String name)
    {
        return getDoubleArray(name, getProperty(name));
    }

    /**
     * Retrieves an array of doubles associated to given name.
     * @param name the name.
     * @param value the property value.
     * @return such parsed value.
     * @precondition name != null
     */
    protected double[] getDoubleArray(final String name, final String value)
    {
        double[] result = null;

        if  (value != null)
        {
            try
            {
                String[] splittedValue = value.split(",");

                result = new double[splittedValue.length];

                for  (int index = 0; index < result.length; index++)
                {
                    result[index] = Double.parseDouble(splittedValue[index]);
                }
            }
            catch  (final NumberFormatException numberFormatException)
            {
                LogFactory.getLog(ConfigurationManager.class + "." + getClass()).fatal(
                    "Invalid double array setting (" + name + "): " + value,
                    numberFormatException);
            }
        }

        return result;
    }

    /**
     * Specifies an double setting.
     * @param name the name.
     * @param value the value.
     * @precondition name != null
     * @precondition value != null
     */
    public void setDoubleArray(final String name, final double[] value)
    {
        StringBuffer propertyValue = new StringBuffer();

        for  (int index = 0; index < value.length; index++)
        {
            propertyValue.append("" + value[index]);

            if  (index < value.length - 1)
            {
                propertyValue.append(",");
            }
        }
    }

    /**
     * Retrieves a concrete boolean setting.
     * @param name the name.
     * @return such parameter.
     * @precondition name != null
     */
    public boolean getBooleanProperty(final String name)
    {
        return getBooleanProperty(name, getProperty(name));
    }

    /**
     * Retrieves a concrete boolean setting.
     * @param name the name.
     * @param value the value.
     * @return such parameter.
     * @precondition name != null
     * @precondition value != null
     */
    protected boolean getBooleanProperty(
        final String name, final String value)
    {
        return Boolean.valueOf(value).booleanValue();
    }

    /**
     * Specifies a concrete boolean setting.
     * @param name the name.
     * @param value the value.
     * @precondition name != null
     */
    public void setBooleanProperty(final String name, final boolean value)
    {
        setProperty(name, "" + value);
    }

    /**
     * Retrieves an array of booleans associated to given name.
     * @param name the name.
     * @return such value.
     * @precondition name != null
     */
    public boolean[] getBooleanArray(final String name)
    {
        return getBooleanArray(name, getProperty(name));
    }

    /**
     * Retrieves an array of booleans associated to given name.
     * @param name the name.
     * @param value the property value.
     * @return such parsed value.
     * @precondition name != null
     */
    protected boolean[] getBooleanArray(final String name, final String value)
    {
        boolean[] result = null;

        if  (value != null)
        {
            String[] splittedValue = value.split(",");

            result = new boolean[splittedValue.length];

            for  (int index = 0; index < result.length; index++)
            {
                result[index] = Boolean.valueOf(splittedValue[index]).booleanValue();
            }
        }

        return result;
    }

    /**
     * Specifies an boolean setting.
     * @param name the name.
     * @param value the value.
     * @precondition name != null
     * @precondition value != null
     */
    public void setBooleanArray(final String name, final boolean[] value)
    {
        StringBuffer propertyValue = new StringBuffer();

        for  (int index = 0; index < value.length; index++)
        {
            propertyValue.append("" + value[index]);

            if  (index < value.length - 1)
            {
                propertyValue.append(",");
            }
        }
    }

    /**
     * Retrieves a date property.
     * @param name the name.
     * @param format the date format.
     * @return the property.
     * @precondition name != null
     * @precondition format != null
     */
    public Date getDateProperty(final String name, final String format)
    {
        return getDateProperty(name, format, getProperty(name));
    }

    /**
     * Retrieves a date property.
     * @param name the name.
     * @param format the date format.
     * @param value the value.
     * @return the property.
     * @precondition name != null
     * @precondition format != null
     */
    protected Date getDateProperty(
        final String name, final String format, final String value)
    {
        Date result = null;

        if  (value != null)
        {
            try
            {
                result = new SimpleDateFormat(format).parse(value);
            }
            catch  (final ParseException parseException)
            {
                LogFactory.getLog(ConfigurationManager.class + "." + getClass()).error(
                    "Invalid date property (" + name + "):" + value,
                    parseException);
            }
        }

        return result;
    }

    /**
     * Specifies a date property.
     * @param name the name.
     * @param value the value.
     * @param format the format.
     * @precondition name != null
     * @precondition value != null
     * @precondition format != null
     */
    public void setDateProperty(
        final String name, final Date value, final String format)
    {
        setProperty(name, new SimpleDateFormat(format).format(value));
        setProperty(name + ".format", format);
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
        InputStream propertiesFile = null;

        boolean startsWithSlash = propertiesFileName.startsWith("/");

        String alternatePropertiesFileName = null;

        if  (startsWithSlash)
        {
            alternatePropertiesFileName = propertiesFileName.substring(1);
        }
        else
        {
            alternatePropertiesFileName = "/" + propertiesFileName;
        }

        // Loading properties
        try
        {
            propertiesFile =
                getClass().getResourceAsStream(propertiesFileName);

            if  (propertiesFile != null)
            {
                properties.load(propertiesFile);
            }
            else if (retry)
            {
                LogFactory.getLog(getClass()).debug(
                      "Properties file: "
                    + propertiesFileName
                    + " not found. Trying " + alternatePropertiesFileName);

                loadProperties(
                    properties,
                    alternatePropertiesFileName,
                    false);
            }
            else
            {
                LogFactory.getLog(getClass()).warn(
                      "Could not load configuration properties from file: "
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
                    + " not found. Trying " + alternatePropertiesFileName);

                loadProperties(
                    properties,
                    alternatePropertiesFileName,
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
