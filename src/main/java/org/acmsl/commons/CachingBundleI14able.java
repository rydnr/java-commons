/*
                        ACM-SL Commons

    Copyright (C) 2002-2005  Jose San Leandro Armend�riz
                             chous@acm-sl.org

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
    Contact info: jose.sanleandro@acm-sl.com
    Postal Address: c/Playa de Lagoa, 1
                    Urb. Valdecaba�as
                    Boadilla del monte
                    28660 Madrid
                    Spain

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armend�riz
 *
 * Description: Represents Throwable instances with support for messages
 *              in different languages.
 *
 * Last modified by: $Author: chous $ at $Date: 2005-12-02 19:07:59 +0100 (Fri, 02 Dec 2005) $
 *
 * File version: $Revision: 523 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: BundleI14able.java 523 2005-12-02 18:07:59Z chous $
 *
 */
package org.acmsl.commons;

/*
 * Importing project classes.
 */
import org.acmsl.commons.BundleI14able;

/*
 * Importing some JDK classes.
 */
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/*
 * Importing Commons Logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Represents instances able to display messages in different languages.
 * @author <a href="mailto:chous@acm-sl.org"
 *         >Jose San Leandro Armendariz</a>
 * @version $Revision: 523 $ at $Date: 2005-12-02 19:07:59 +0100 (Fri, 02 Dec 2005) $ by $Author: chous $
 */
public class CachingBundleI14able
    extends  BundleI14able
{
    /**
     * The caching ttl.
     */
    private long m__lCachingTtl;

    /**
     * Whether to reset the cache.
     */
    private boolean m__bResetCache;

    /**
     * The resource bundle cache.
     */
    private static final Map RESOURCE_BUNDLE_CACHE = new HashMap();
    
    /**
     * Creates a <code>CachingBundleI14able</code> with given message.
     * @param messageKey the key to build the exception message.
     * @param params the parameters to build the exception message.
     * @param systemProperty the name of the system property pointing to the
     * bundle.
     * @param bundleName the name of the bundle.
     * @param useClassLoader whether to use class loaders explicitly.
     * @param ttl the caching TTL.
     * @precondition messageKey != null
     * @precondition params != null
     * @precondition (systemProperty != null) || (bundleName != null)
     */
    protected CachingBundleI14able(
        final String messageKey,
        final Object[] params,
        final String systemProperty,
        final String bundleName,
        final boolean useClassLoader,
        final long ttl)
    {
        super(messageKey, params, systemProperty, bundleName, useClassLoader);
        immutableSetCacheTtl(ttl);
    }

    /**
     * Creates a <code>CachingBundleI14able</code> with given message.
     * @param messageKey the key to build the exception message.
     * @param params the parameters to build the exception message.
     * @param systemProperty the name of the system property pointing to the
     * bundle.
     * @param bundleName the name of the bundle.
     * @param ttl the caching TTL.
     * @precondition messageKey != null
     * @precondition params != null
     * @precondition (systemProperty != null) || (bundleName != null)
     */
    protected CachingBundleI14able(
        final String messageKey,
        final Object[] params,
        final String systemProperty,
        final String bundleName,
        final long ttl)
    {
        super(messageKey, params, systemProperty, bundleName);
        immutableSetCacheTtl(ttl);
    }

    /**
     * Creates a <code>BundleI14able</code> with given message bundle.
     * @param messageKey the key to build the exception message.
     * @param systemProperty the name of the bundle.
     * @param bundleName the bundle name.
     * @precondition messageKey != null
     * @precondition (systemProperty != null) || (bundleName != null)
     */
    protected CachingBundleI14able(
        final String messageKey,
        final String systemProperty,
        final String bundleName,
        final long ttl)
    {
        super(messageKey, systemProperty, bundleName);
        immutableSetCacheTtl(ttl);
    }

    /**
     * Specifies whether to retry the bundle retrieval.
     * @param flag such condition.
     */
    protected final void immutableSetResetCache(final boolean flag)
    {
        m__bResetCache = flag;
    }
     
    /**
     * Specifies whether to reset the cache.
     * @param flag such condition.
     */
    public void setResetCache(final boolean flag)
    {
        immutableSetResetCache(flag);
    }
    
    /**
     * Retrieves whether to reset the cache.
     * @return such condition.
     */
    public boolean getResetCache()
    {
        return m__bResetCache;
    }

    /**
     * Specifies the cache TTL.
     * @param ttl such value.
     */
    protected final void immutableSetCacheTtl(final long ttl)
    {
        m__lCachingTtl = ttl;
    }
    
    /**
     * Specifies the cache TTL.
     * @param ttl such value.
     */
    protected void setCacheTtl(final long ttl)
    {
        immutableSetCacheTtl(ttl);
    }

    /**
     * Retrieves the cache TTL.
     * @return such value.
     */
    public long getCacheTtl()
    {
        return m__lCachingTtl;
    }

    /**
     * Annotates given bundle in the cache.
     * @param bundle the bundle to annotate.
     * @precondition bundle != null
     */
    protected void cacheBundle(final ResourceBundle resourceBundle)
    {
        cacheBundle(resourceBundle, getMessageKey(), RESOURCE_BUNDLE_CACHE);
    }
    
    /**
     * Annotates given bundle in the cache.
     * @param bundle the bundle to annotate.
     * @param messageKey the message key.
     * @param map the caching mechanism.
     * @precondition bundle != null
     * @precondition messageKey != null
     * @precondition map != null
     */
    protected void cacheBundle(
        final ResourceBundle resourceBundle, final String messageKey, final Map map)
    {
        map.put(messageKey, wrapTimestamp(resourceBundle));
    }

    /**
     * Retrieves the cached <code>ResourceBundle</code>.
     * @return such bundle, or <code>null</code> if any.
     */
    protected ResourceBundle getCachedBundle()
    {
        return
            getCachedBundle(
                getMessageKey(), RESOURCE_BUNDLE_CACHE, getResetCache(), getCacheTtl());
    }
    
    /**
     * Retrieves the cached <code>ResourceBundle</code>.
     * @param messageKey the message key.
     * @param map the caching mechanism.
     * @param resetCache whether the cache should be reset.
     * @param cacheTtl the cache TTL.
     * @return such bundle, or <code>null</code> if any.
     * @precondition messageKey != null
     * @precondition map != null
     */
    protected ResourceBundle getCachedBundle(
        final String messageKey,
        final Map map,
        final boolean resetCache,
        final long cacheTtl)
    {
        TimestampResourceBundle result = (TimestampResourceBundle) map.get(messageKey);

        if  (resetCache)
        {
            emptyCache(map);
            result = null;
        }
        else if  (   (result != null)
                  && (hasExpiredFromCache(result, cacheTtl)))
        {
            map.remove(messageKey);
            result = null;
        }
        
        return result;
    }

    /**
     * Empties the cache.
     * @param map the caching mechanism.
     * @precondition map != null
     */
    protected void emptyCache(final Map map)
    {
        map.clear();
    }

    /**
     * Checks whether the bundle has expired from the cache.
     * @param bundle the bundle.
     * @param cacheTtl the cache TTL.
     * @return <code>true</code> in such case.
     * @precondition bundle != null
     */
    protected boolean hasExpiredFromCache(
        final TimestampResourceBundle bundle, final long cacheTtl)
    {
        boolean result = true;

        Date timestamp = bundle.getTimestamp();
        
        if  (timestamp != null)
        {
            Date now = new Date();
            
            if  (now.getTime() < timestamp.getTime() + cacheTtl)
            {
                result = false;
            }
        }
        
        return result;
    }
  
    /**
     * Wraps given bundle to attach a timestamp.
     * @param bundle the bundle.
     * @return a <code>TimestampResourceBundle</code> instance.
     * @precondition bundle != null
     */
    protected TimestampResourceBundle wrapTimestamp(final ResourceBundle bundle)
    {
        return new TimestampResourceBundle(bundle);
    }

    /**
     * A bundle wrapper to annotate the caching timestamp.
     * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro</a>.
     */
    protected static class TimestampResourceBundle
        extends  ResourceBundle
    {
        /**
         * The wrapped bundle.
         */
        private ResourceBundle m__WrappedBundle;
        
        /**
         * The timestamp.
         */
        private Date m__Timestamp;
        
        /**
         * Creates a <code>TimestampResourceBundle</code> to wrap given instance.
         * @param bundle the bundle to wrap.
         * @precondition bundle != null
         */
        protected TimestampResourceBundle(final ResourceBundle bundle)
        {
            immutableSetWrappedBundle(bundle);
            immutableSetTimestamp(new Date());
        }
        
        /**
         * Specifies the wrapped bundle.
         * @param bundle such bundle.
         */
        protected final void immutableSetWrappedBundle(final ResourceBundle bundle)
        {
            m__WrappedBundle = bundle;
        }
        
        /**
         * Specifies the wrapped bundle.
         * @param bundle such bundle.
         */
        protected void setWrappedBundle(final ResourceBundle bundle)
        {
            immutableSetWrappedBundle(bundle);
        }

        /**
         * Retrieves the wrapped bundle.
         * @return such bundle.
         */
        protected ResourceBundle getWrappedBundle()
        {
            return m__WrappedBundle;
        }
        
        /**
         * Specifies the timestamp.
         * @param date such date.
         */
        protected final void immutableSetTimestamp(final Date date)
        {
            m__Timestamp = date;
        }
        
        /**
         * Specifies the timestamp.
         * @param date such date.
         */
        protected void setTimestamp(final Date date)
        {
            immutableSetTimestamp(date);
        }
        
        /**
         * Retrieves the timestamp.
         * @return such information.
         */
        public Date getTimestamp()
        {
            return m__Timestamp;
        }

        /**
         * Retrieves the locale.
         * @return such information.
         */
        public Locale getLocale()
        {
            return getLocale(getWrappedBundle());
        }
        
        /**
         * Retrieves the locale.
         * @param bundle the bundle.
         * @return such information.
         * @precondition bundle != null
         */
        protected Locale getLocale(final ResourceBundle bundle)
        {
            return bundle.getLocale();
        }

        /**
         * Retrieves the bundle keys.
         * @return such keys.
         */
        public Enumeration getKeys()
        {
            return getKeys(getWrappedBundle());
        }

        /**
         * Retrieves the bundle keys.
         * @param bundle the bundle.
         * @return such keys.
         * @precondition bundle != null
         */
        protected Enumeration getKeys(final ResourceBundle bundle)
        {
            return bundle.getKeys();
        }

        /**
         * Gets an object for the given key from this resource bundle.
         * @param key the key.
         */
        protected Object handleGetObject(final String key)
        {
            return handleGetObject(key, getWrappedBundle());
        }
        
        /**
         * Gets an object for the given key from this resource bundle.
         * @param key the key.
         * @param bundle the bundle.
         */
        protected Object handleGetObject(final String key, final ResourceBundle bundle)
        {
            return bundle.getObject(key);
        }
        
        /**
         * Retrieves the hashcode.
         * @return such value.
         */
        public int hashCode()
        {
            return hashCode(getWrappedBundle());
        }

        /**
         * Retrieves the hashcode.
         * @param bundle the bundle.
         * @return such value.
         * @precondition bundle != null
         */
        protected int hashCode(final ResourceBundle bundle)
        {
            return bundle.hashCode();
        }

        /**
         * Checks whether given instance is equal to this one.
         * @param object the object to check.
         * @return <code>true</code> in such case.
         */
        public boolean equals(final Object object)
        {
            boolean result = false;
            
            if  (object instanceof ResourceBundle)
            {
                result = object.equals(getWrappedBundle());
            }
            
            return result;
        }
        
        /**
         * Gives the text representation of the instance.
         * @return such text.
         */
        public String toString()
        {
            return toString(getWrappedBundle());
        }

        /**
         * Gives the text representation of the instance.
         * @return such text.
         * @precondition bundle != null
         */
        protected String toString(final ResourceBundle bundle)
        {
            return bundle.toString();
        }
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
     * Retrieves the bundle from the environment using given information.
     * @param locale the locale.
     * @param systemProperty the system property.
     * @param classLoader the class loader.
     * @return the bundle.
     * @precondition locale != null
     * @precondition systemProperty != null
     */
    protected ResourceBundle retrieveSystemPropertyBundle(
        final String systemProperty,
        final Locale locale,
        final ClassLoader classLoader)
    {
        ResourceBundle result = getCachedBundle();
        
        if  (result == null)
        {
            result =
                super.retrieveSystemPropertyBundle(
                    systemProperty, locale, classLoader);
        }
        
        if  (result != null)
        {
            cacheBundle(result);
        }
        
        return result;
    }
    
    /**
     * Retrieves the bundle using a concrete class loader.
     * @param bundleName the bundle name.
     * @param locale the locale.
     * @param classLoader the class loader.
     * @return the bundle.
     * @precondition locale != null
     * @precondition classLoader != null
     */
    protected ResourceBundle retrieveBundle(
        final String bundleName,
        final Locale locale,
        final ClassLoader classLoader)
    {
        ResourceBundle result = getCachedBundle();
        
        if  (result == null)
        {
            result =
                super.retrieveBundle(
                    bundleName, locale, classLoader);
        }
        
        if  (result != null)
        {
            cacheBundle(result);
        }
        
        return result;
    }
    
}