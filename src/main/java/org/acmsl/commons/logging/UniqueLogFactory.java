//;-*- mode: java -*-
/*
                        ACM-SL Commons

    Copyright (C) 2002-today  Jose San Leandro Armendariz
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

    Thanks to ACM S.L. for distributing this library under the LGPL license.
    Contact info: jose.sanleandro@acm-sl.com

 ******************************************************************************
 *
 * Filename: UniqueLogFactory.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Simplified LogFactory implementation that uses a
 * cached Log instance.
 *
 * Updated: Simplified to avoid Commons Logging API compatibility issues
 *
 */
package org.acmsl.commons.logging;

/*
 * Importing Commons-Logging classes.
 */
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;

/**
 * Simplified {@link LogFactory} implementation that uses a
 * cached {@link Log} instance.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class UniqueLogFactory
    extends LogFactory
{
    /**
     * The cached Log instance.
     */
    private static Log cachedLog;

    /**
     * The singleton factory instance.
     */
    private static UniqueLogFactory singleton;

    /**
     * Creates a new UniqueLogFactory with the given log.
     * @param log the log instance.
     */
    protected UniqueLogFactory(final Log log)
    {
        cachedLog = log;
    }

    /**
     * Retrieves the singleton instance.
     * @return the singleton instance.
     */
    public static UniqueLogFactory getSingleton()
    {
        return singleton;
    }

    /**
     * Sets the singleton instance.
     * @param factory the factory instance.
     */
    protected static void setSingleton(final UniqueLogFactory factory)
    {
        singleton = factory;
    }

    /**
     * Retrieves the cached log instance.
     * @return the log instance.
     */
    public static Log getLog()
    {
        return cachedLog;
    }

    /**
     * Retrieves the log instance for the specified class.
     * @param clazz the class.
     * @return the log instance.
     */
    public static Log getLogForClass(@SuppressWarnings("unused") final Class<?> clazz)
    {
        return getLog();
    }

    /**
     * Retrieves the log instance for the specified class name.
     * @param className the class name.
     * @return the log instance.
     */
    public static Log getLogForName(@SuppressWarnings("unused") final String className)
    {
        return getLog();
    }

    /**
     * Initializes the log factory.
     * @param log the log instance.
     */
    public static void initializeInstance(final Log log)
    {
        setSingleton(new UniqueLogFactory(log));
    }

    /**
     * Release any internal references to previously created Log instances.
     */
    @Override
    public void release()
    {
        // No-op - we maintain a single cached instance
    }

    /**
     * Remove any configuration attribute associated with the specified name.
     * @param name Name of the attribute to remove
     */
    @Override
    public void removeAttribute(final String name)
    {
        // No-op - simplified implementation
    }

    /**
     * Set the configuration attribute with the specified name.
     * @param name Name of the attribute to set
     * @param value Value of the attribute to set, or null to remove any current attribute
     */
    @Override
    public void setAttribute(final String name, final Object value)
    {
        // No-op - simplified implementation
    }

    /**
     * Return the configuration attribute with the specified name (if any).
     * @param name Name of the attribute to return
     * @return the configuration attribute value
     */
    @Override
    public Object getAttribute(final String name)
    {
        return null; // Simplified implementation
    }

    /**
     * Return an array containing the names of all currently defined configuration attributes.
     * @return array of attribute names
     */
    @Override
    public String[] getAttributeNames()
    {
        return new String[0];
    }

    /**
     * Convenience method to derive a name from the specified class and
     * call getInstance(String) with it.
     * @param clazz Class for which a suitable Log name will be derived
     * @return Log instance
     * @throws LogConfigurationException if a suitable Log instance cannot be returned
     */
    @Override
    public Log getInstance(final Class clazz) throws LogConfigurationException
    {
        return getLog();
    }

    /**
     * Construct (if necessary) and return a Log instance.
     * @param name Logical name of the Log instance to be returned
     * @return Log instance
     * @throws LogConfigurationException if a suitable Log instance cannot be returned
     */
    @Override
    public Log getInstance(final String name) throws LogConfigurationException
    {
        return getLog();
    }
}