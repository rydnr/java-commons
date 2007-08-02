/*
                        ACM-SL Commons

    Copyright (C) 2002-2005  Jose San Leandro Armendariz
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
                    Urb. Valdecabanas
                    Boadilla del monte
                    28660 Madrid
                    Spain

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: *LogFactory* implementation that uses a
 * cached *Log* instance if pre-cached.
 *
 * Version: $Revision: 506 $ ($Author: chous $ at $Date: 2005-09-05 10:46:31 +0200 (Mon, 05 Sep 2005) $)
 *
 * $Id: UniqueLogFactory.java 506 2005-09-05 08:46:31Z chous $
 *
 * Important Note: This class implements Apache Commons-Logging's Log
 * interface. License details are copied verbatim below.
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
 * <code>LogFactory</code> implementation that uses a
 * cached <code>Log</code> instance.
 * @author <a href="mailto:chous@acm-sl.org"
 *         >Jose San Leandro</a>
 * @version $Revision: 506 $ ($Author: chous $ at $Date: 2005-09-05 10:46:31 +0200 (Mon, 05 Sep 2005) $)
 */
public class UniqueLogFactory
    extends  LogFactory
{
    /**
     * The <code>Log</code> instance.
     */
    private static Log m__Log;

    /**
     * The singleton instance.
     */
    private static LogFactory m__Singleton;
    
    /**
     * Specifies the <code>Log</code> instance.
     * @param log the log instance.
     */
    protected static final void immutableSetLog(
        final Log log)
    {
        m__Log = log;
    }

    /**
     * Specifies the <code>Log</code> instance.
     * @param log the log instance.
     */
    protected static void setLog(final Log log)
    {
        immutableSetLog(log);
    }

    /**
     * Retrieves the <code>Log</code> instance.
     * @return the long instance.
     */
    public static Log getLog()
    {
        return m__Log;
    }

    /**
     * Specifies the singleton instance.
     * @param singleton such instance.
     * @precondition singleton != null
     */
    protected static final void immutableSetSingleton(
        final LogFactory singleton)
    {
        m__Singleton = singleton;
    }
    
    /**
     * Specifies the singleton instance.
     * @param singleton such instance.
     * @precondition singleton != null
     */
    protected static void setSingleton(
        final LogFactory singleton)
    {
        immutableSetSingleton(singleton);
    }
    
    /**
     * Retrieves the singleton instance.
     * @return such instance.
     */
    protected static LogFactory getSingleton()
    {
        return m__Singleton;
    }
    
    /**
     * Creates a <code>UniqueLogFactory</code> instance with
     * given log instance.
     * @param log the log instance.
     * @precondition log != null
     */
    protected UniqueLogFactory(final Log log)
    {
        immutableSetLog(log);
    }

    /**
     * Retrieves the singleton instance.
     * @return such instance.
     */
    public static final LogFactory getInstance()
    {
        return getSingleton();
    }

    /**
     * Retrieves the log instance.
     * @param clazz the class name.
     * @return the log instance.
     */
    public static Log getLog(final Class clazz)
    {
        return getLog();
    }
    
    /**
     * Retrieves the log instance.
     * @param className the class name.
     * @return the log instance.
     */
    public static Log getLog(final String className)
    {
        return getLog();
    }
    
    /**
     * Initializes the log factory.
     * @param log the log instance.
     * @precondition log != null
     */
    public static final void initializeInstance(final Log log)
    {
        setSingleton(new UniqueLogFactory(log));
    }
    
    /**
     * Return the configuration attribute with the specified name (if any),
     * or <code>null</code> if there is no such attribute.
     * @param name Name of the attribute to return.
     */
    public Object getAttribute(final String name)
    {
        return null;
    }

    /**
     * Return an array containing the names of all currently defined
     * configuration attributes.  If there are no such attributes, a zero
     * length array is returned.
     */
    public String[] getAttributeNames()
    {
        return new String[0];
    }

    /**
     * Convenience method to derive a name from the specified class and
     * call <code>getInstance(String)</code> with it.
     * @param clazz Class for which a suitable Log name will be derived
     * @throws LogConfigurationException if a suitable <code>Log</code>
     * instance cannot be returned
     */
    public Log getInstance(final Class clazz)
        throws LogConfigurationException
    {
        return getInstance(clazz, getLog());
    }

    /**
     * Convenience method to derive a name from the specified class and
     * call <code>getInstance(String)</code> with it.
     * @param clazz Class for which a suitable Log name will be derived
     * @param log the cached log instance.
     * @throws LogConfigurationException if a suitable <code>Log</code>
     * instance cannot be returned
     */
    protected Log getInstance(final Class clazz, final Log log)
        throws LogConfigurationException
    {
        Log result = log;
        
        LogFactory t_LogFactory = LogFactory.getFactory();
        
        if  (t_LogFactory != null)
        {
            result = t_LogFactory.getInstance(clazz);
        }
            
        return result;
    }

    /**
     * <p>Construct (if necessary) and return a <code>Log</code> instance,
     * using the factory's current set of configuration attributes.</p>
     * <p><strong>NOTE</strong> - Depending upon the implementation of
     * the <code>LogFactory</code> you are using, the <code>Log</code>
     * instance you are returned may or may not be local to the current
     * application, and may or may not be returned again on a subsequent
     * call with the same name argument.</p>
     * @param name Logical name of the <code>Log</code> instance to be
     * returned (the meaning of this name is only known to the underlying
     * logging implementation that is being wrapped)
     * @throws LogConfigurationException if a suitable <code>Log</code>
     * instance cannot be returned
     */
    public Log getInstance(final String name)
        throws LogConfigurationException
    {
        return getInstance(name, getLog());
    }
    
    /**
     * <p>Construct (if necessary) and return a <code>Log</code> instance,
     * using the factory's current set of configuration attributes.</p>
     * <p><strong>NOTE</strong> - Depending upon the implementation of
     * the <code>LogFactory</code> you are using, the <code>Log</code>
     * instance you are returned may or may not be local to the current
     * application, and may or may not be returned again on a subsequent
     * call with the same name argument.</p>
     * @param name Logical name of the <code>Log</code> instance to be
     * returned (the meaning of this name is only known to the underlying
     * logging implementation that is being wrapped)
     * @param log the possibly cached log instance.
     * @throws LogConfigurationException if a suitable <code>Log</code>
     * instance cannot be returned
     */
    public Log getInstance(final String name, final Log log)
        throws LogConfigurationException
    {
        Log result = log;
        
        if  (result == null)
        {
            LogFactory t_LogFactory = LogFactory.getFactory();
        
            if  (t_LogFactory != null)
            {
                result = t_LogFactory.getInstance(name);
            }
        }
        
        return result;
    }

    /**
     * Releases any internal references to previously created {@link Log}
     * instances returned by this factory.  This is useful in environments
     * like servlet containers, which implement application reloading by
     * throwing away a ClassLoader.  Dangling references to objects in that
     * class loader would prevent garbage collection.
     */
    public void release()
    {
        setLog(null);
    }

    /**
     * Remove any configuration attribute associated with the specified name.
     * If there is no such attribute, no action is taken.
     * @param name Name of the attribute to remove.
     */
    public void removeAttribute(final String name)
    {
    }

    /**
     * Set the configuration attribute with the specified name.  Calling
     * this with a <code>null</code> value is equivalent to calling
     * <code>removeAttribute(name)</code>.
     *
     * @param name Name of the attribute to set
     * @param value Value of the attribute to set, or <code>null</code>
     *  to remove any setting for this attribute
     */
    public void setAttribute(final String name, final Object value)
    {
    }
}
/*
 * Copyright 2001-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
