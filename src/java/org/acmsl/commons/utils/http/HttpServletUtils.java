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
 * Description: Provides some useful methods when dealing with HTTP-related
 *              issues.
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
package org.acmsl.commons.utils.http;

/*
 * Importing project classes.
 */
import org.acmsl.commons.regexpplugin.Matcher;
import org.acmsl.commons.regexpplugin.MatchResult;
import org.acmsl.commons.regexpplugin.Pattern;
import org.acmsl.commons.regexpplugin.RegexpEngine;
import org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException;
import org.acmsl.commons.regexpplugin.RegexpManager;
import org.acmsl.commons.regexpplugin.RegexpPluginMisconfiguredException;
import org.acmsl.commons.utils.regexp.RegexpUtils;

/*
 * Importing some Servlet-related classes.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

/*
 * Importing some JDK classes.
 */
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.StringTokenizer;

/*
 * Importing some Jakarta Commons Logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Provides some useful methods when dealing with HttpServlet-related issues.
 * @author <a href="mailto:jsanleandro@yahoo.es">Jose San Leandro</a>
 * @version $Revision$ $Date$
 */
public abstract class HttpServletUtils
{
    /**
     * The locale parameter.
     */
    public static final String LOCALE_PARAMETER = "lang";

    /**
     * The name-value pair regexp.
     */
    public static final String NAME_VALUE_PAIR_REGEXP = "(.*)?=(.*)";

    /**
     * A cached name-value pair compiled regexp.
     */
    private static Pattern c__NameValuePairRegexp;

    /**
     * Singleton implemented as a weak reference.
     */
    private static WeakReference singleton;

    /**
     * Default protected constructor to avoid accidental instantiation.
     */
    protected HttpServletUtils() {};

    /**
     * Specifies a new weak reference.
     * @param utils the utils instance to use.
     * @precondition utils != null
     */
    protected static void setReference(final HttpServletUtils utils)
    {
        singleton = new WeakReference(utils);
    }

    /**
     * Retrieves the weak reference.
     * @return such reference.
     */
    protected static WeakReference getReference()
    {
        return singleton;
    }

    /**
     * Retrieves a HttpServletUtils instance.
     * @return such instance.
     */
    public static HttpServletUtils getInstance()
    {
        HttpServletUtils result = null;

        WeakReference reference = getReference();

        if  (reference != null) 
        {
            result = (HttpServletUtils) reference.get();
        }

        if  (result == null) 
        {
            result = new HttpServletUtils() {};

            setReference(result);
        }

        return result;
    }

    /**
     * Specifies the name-value pair regexp.
     * @param regexp such regexp.
     */
    protected void setNameValuePairRegexp(final Pattern regexp)
    {
        c__NameValuePairRegexp = regexp;
    }

    /**
     * Retrieves the name-value pair regexp.
     * @return such regexp.
     */
    protected Pattern getNameValuePairRegexp()
    {
        return c__NameValuePairRegexp;
    }

    /**
     * Internationalizes given URL using a configurable convention.
     * @param url the url to internationalize.
     * @param locale the language.
     * @return the translated url.
     * @precondition url != null
     * @precondition locale != null
     */
    public String i14e(final String url, final Locale locale)
    {
        return i14e(url, locale, LOCALE_PARAMETER);
    }

    /**
     * Internationalizes given URL using a configurable convention.
     * @param url the url to internationalize.
     * @param locale the language.
     * @param localeParameter the locale parameter.
     * @return the translated url.
     * @precondition url != null
     * @precondition locale != null
     * @precondition localeParameter != null
     */
    public String i14e(
        final String url,
        final Locale locale,
        final String localeParameter)
    {
        return
            addParameter(
                url,
                localeParameter,
                locale.getLanguage());
    }

    /**
     * Adds a concrete parameter to given URL.
     * @param url the url to be updated.
     * @param paramName the parameter name.
     * @param paramValue the parameter value.
     * @return the modified url.
     * @precondition url != null
     * @precondition paramName != null
     * @precondition paramValue != null
     */
    public String addParameter(
        final String url,
        final String paramName,
        final String paramValue)
    {
        String result = url;

        try 
        {
            result =
                append(
                    url,
                      URLEncoder.encode(paramName, "UTF-8")
                    + "="
                    + URLEncoder.encode(paramValue, "UTF-8"));
        }
        catch (final UnsupportedEncodingException unsupportedEncodingException)
        {
            LogFactory.getLog(HttpServletUtils.class).fatal(
                "cannot manage query-string parameters",
                unsupportedEncodingException);
        }

        return result;
    }

    /**
     * Safely appends extra information to given url.
     * @param url the first part of the url.
     * @param extraInfo the new information to add.
     * @return the updated url.
     * @precondition url != null
     * @precondition extraInfo != null
     */
    public String append(final String url, final String extraInfo)
    {
        String result = "";

        int questionPosition = url.indexOf("?");

        result =
            (questionPosition == -1)
            ?  "?"
            :  "&";

        questionPosition = extraInfo.indexOf("?");

        String params =
            (questionPosition == -1)
            ?  extraInfo
            :  (questionPosition == extraInfo.length() - 1)
               ?  extraInfo.substring(0, questionPosition)
               :    extraInfo.substring(0, questionPosition)
                  + "&"
                  + extraInfo.substring(questionPosition + 1);

        result = url + result + params;

        return result;
    }

    /**
     * Retrieves an integer parameter.
     * @param request the request.
     * @param paramName the parameter name
     * @param defaultValue the default value.
     * @return the int value.
     * @precondition request != null
     * @precondition paramName != null
     */
    public int getIntParam(
        final ServletRequest request,
        final String paramName,
        final int defaultValue)
    {
        int result = defaultValue;

        String value = request.getParameter(paramName);

        if  (value != null)
        {
            result = getIntParam(paramName, value, defaultValue);
        }

        return result;
    }

    /**
     * Retrieves an integer parameter.
     * @param paramName the parameter name.
     * @param paramValue the parameter value.
     * @param defaultValue the default value.
     * @return the int value.
     * @precondition paramName != null
     * @precondition paramValue != null
     */
    protected int getIntParam(
        final String paramName,
        final String paramValue,
        final int defaultValue)
    {
        int result = defaultValue;

        try
        {
            result = Integer.parseInt(paramValue);
        }
        catch  (final NumberFormatException numberFormatException)
        {
            LogFactory.getLog(getClass()).debug(
                "Cannot retrieve the " + paramName + " value");
        }

        return result;
    }

    /**
     * Retrieves the real context path (even if it's managed by Struts)
     * of the application.
     * @param request the request.
     * @return the real context path.
     * @precondition request != null
     */
    public String getContextPath(final HttpServletRequest request)
    {
        return
            getContextPath(
                request, removeServletInfo(request.getServletPath()));
    }

    /**
     * Retrieves the real context path (even if it's managed by Struts)
     * of the application.
     * @param request the request.
     * @param subContext the subcontext.
     * @return the real context path.
     * @precondition request != null
     * @precondition subContext != null
     */
    protected String getContextPath(
        final HttpServletRequest request, final String subContext)
    {
        return request.getContextPath() + subContext;
    }

    /**
     * Retrieves the real context path (even if it's managed by Struts)
     * of the application.
     * @param request the request.
     * @return the real context path.
     * @precondition request != null
     */
    public String getServletPath(final HttpServletRequest request)
    {
        return
            removeServletInfo(request.getServletPath());
    }

    /**
     * Removes the trailing servlet info from given servlet path.
     * @param servletPath the servlet path.
     * @return the actual relative path of the servlet, without the
     * information about the servlet itself.
     * @precondition servletPath != null
     */
    protected String removeServletInfo(final String servletPath)
    {
        StringBuffer result = new StringBuffer();

        StringTokenizer tokenizer = new StringTokenizer(servletPath, "/");

        int totalTokens = tokenizer.countTokens();

        int tokenIndex = 1;

        while  (tokenizer.hasMoreTokens())
        {
            if (tokenIndex < totalTokens)
            {
                result.append("/");
                result.append(tokenizer.nextToken());
                tokenIndex++;
            }
            else
            {
                break;
            }
        }

        return result.toString();
    }

    /**
     * Retrieves an object from the repository.
     * @param key the key.
     * @param request the request.
     * @return the object if it's found.
     * @precondition key != null
     * @precondition request != null
     */
    public Object retrieve(final String key, final HttpServletRequest request)
    {
        return retrieve(key, request, request.getSession(true), null);
    }

    /**
     * Retrieves an object from the repository.
     * @param key the key.
     * @param session the session.
     * @return the object if it's found.
     * @precondition key != null
     * @precondition session != null
     */
    public Object retrieve(final String key, final HttpSession session)
    {
        return retrieve(key, null, session, null);
    }

    /**
     * Retrieves an object from the repository.
     * @param key the key.
     * @param request the request.
     * @param session the session.
     * @return the object if it's found.
     * @precondition key != null
     * @precondition ((request != null) || (session != null))
     */
    public Object retrieve(
        final String key,
        final ServletRequest request,
        final HttpSession session)
    {
        return retrieve(key, request, session, null);
    }

    /**
     * Retrieves an object from the repository.
     * @param key the key.
     * @param request the request.
     * @param session the session.
     * @param context the context.
     * @return the object if it's found.
     * @precondition key != null
     * @precondition ((request != null) || (session != null) || (context != null))
     */
    public Object retrieve(
        final String key,
        final ServletRequest request,
        final HttpSession session,
        final ServletContext context)
    {
        Object result = null;

        // Thanks Sun for had missed an interface for
        // all classes that export setAttribute().
        if  (request != null)
        {
            result = request.getAttribute(key);
        }

        if  (   (result  == null)
             && (session != null))
        {
            result = session.getAttribute(key);
        }

        if  (   (result  == null)
             && (context != null))
        {
            result = context.getAttribute(key);
        }

        return result;
    }

    /**
     * Stores an object in the repository.
     * @param object the object to store.
     * @param key the key.
     * @param request the request.
     * @param session the session.
     * @param context the context.
     * @return <code>true</code> if the object has been stored successfully.
     * @precondition object != null
     * @precondition key != null
     * @precondition ((request != null) || (session != null))
     */
    public boolean store(
        final Object         object,
        final String         key,
        final ServletRequest request,
        final HttpSession    session)
    {
        boolean result = false;

        // Thanks Sun for had missed an interface for
        // all classes that export setAttribute().
        if  (request != null) 
        {
            request.setAttribute(key, object);
            result = true;
        }
                            
        if  (session != null) 
        {
            session.setAttribute(key, object);
            result = true;
        }

        return result;
    }

    /**
     * Removes an object from the repository.
     * @param key the key.
     * @param request the request.
     * @param session the session.
     * @return <code>true</code> if the object has been removed successfully.
     * @precondition key != null
     * @precondition ((request != null) || (session != null))
     */
    public boolean remove(
        final String         key,
        final ServletRequest request,
        final HttpSession    session,
        final ServletContext context)
    {
        boolean result = false;

        // Thanks Sun for had missed an interface for
        // all classes that export setAttribute().
        if  (request != null) 
        {
            request.removeAttribute(key);
            result = true;
        }
                            
        if  (session != null) 
        {
            session.removeAttribute(key);
            result = true;
        }

        return result;
    }

    /**
     * Creates the matcher.
     * @return the regexp matcher.
     * @throws RegexpEngineNotFoundException if a suitable instance
     * cannot be created.
     * @throws RegexpPluginMisconfiguredException if RegexpPlugin is
     * misconfigured.
     */
    protected static synchronized Matcher createMatcher()
      throws RegexpEngineNotFoundException,
             RegexpPluginMisconfiguredException
    {
        return createMatcher(RegexpManager.getInstance());
    }

    /**
     * Creates the matcher.
     * @param regexpManager the RegexpManager instance.
     * @return the regexp matcher.
     * @throws RegexpEngineNotFoundException if a suitable instance
     * cannot be created.
     * @throws RegexpPluginMisconfiguredException if RegexpPlugin is
     * misconfigured.
     * @precondition regexpManager != null
     */
    protected static synchronized Matcher createMatcher(
        final RegexpManager regexpManager)
      throws RegexpEngineNotFoundException,
             RegexpPluginMisconfiguredException
    {
        return createMatcher(regexpManager.getEngine());
    }

    /**
     * Creates the matcher.
     * @param regexpEngine the RegexpEngine instance.
     * @return the regexp matcher.
     * @precondition regexpEngine != null
     */
    protected static synchronized Matcher createMatcher(
        final RegexpEngine regexpEngine)
    {
        return regexpEngine.createMatcher();
    }

    /**
     * Parses a name=value text, splitting it in two strings.
     * @param unparsed the text to parse.
     * @return a two-element array, first for the left part, second for the
     * right.
     * @throws RegexpEngineNotFoundException if the system is not configured
     * properly in order to provide regexp services.
     * @precondition unparsed != null
     */
    public String[] parseNameValuePair(final String unparsed)
        throws  RegexpEngineNotFoundException
    {
        return
            parseNameValuePair(
                unparsed, createMatcher(RegexpManager.getInstance()));
    }

    /**
     * Parses a name=value text, splitting it in two strings.
     * @param unparsed the text to parse.
     * @param matcher the regexp matcher.
     * @return a two-element array, first for the left part, second for the
     * right.
     * @precondition unparsed != null
     * @precondition matcher != null
     */
    public String[] parseNameValuePair(
        final String unparsed, final Matcher matcher)
    {
        Pattern t_Pattern = getNameValuePairRegexp();

        RegexpUtils regexpUtils = RegexpUtils.getInstance();

        if  (t_Pattern == null)
        {
            t_Pattern = buildNameValuePairRegexp(regexpUtils);

            setNameValuePairRegexp(t_Pattern);
        }

        return parseNameValuePair(unparsed, t_Pattern, matcher);
    }

    /**
     * Builds the regexp for parsing name=value texts.
     * @param regexpUtils the RegexpUtils instance.
     * @return such compiled regexp.
     * @precondition regexpUtils != null
     */
    protected Pattern buildNameValuePairRegexp(
        final RegexpUtils regexpUtils)
    {
        return regexpUtils.buildPattern(NAME_VALUE_PAIR_REGEXP);
    }

    /**
     * Parses a name=value text, splitting it in two strings.
     * @param unparsed the text to parse.
     * @param pattern the regexp pattern.
     * @param matcher the regexp matcher.
     * @return a two-element array, first for the left part, second for the
     * right.
     * @precondition unparsed != null
     * @precondition pattern != null
     * @precondition matcher != null
     */
    protected String[] parseNameValuePair(
        final String unparsed, final Pattern pattern, final Matcher matcher)
    {
        String[] result = new String[2];

        if  (matcher.contains(unparsed, pattern))
        {
            MatchResult t_MatchResult = matcher.getMatch();

            if  (   (t_MatchResult != null)
                 && (t_MatchResult.groups() >= 2))
            {
                result[0] = t_MatchResult.group(1);
                result[1] = t_MatchResult.group(2);
            }
        }
        
        return result;
    }
}
