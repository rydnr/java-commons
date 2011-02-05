//;-*- mode: java -*-
/*
                        ACM-SL Commons

    Copyright (C) 2002-today  Jose San Leandro Armendariz
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
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-307  USA


    Thanks to ACM S.L. for distributing this library under the LGPL license.
    Contact info: jose.sanleandro@acm-sl.com

 ******************************************************************************
 *
 * Filename: URLUtils.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Provides some useful methods when working with URLs.
 *
 */
package org.acmsl.commons.utils.net;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Singleton;
import org.acmsl.commons.patterns.Utils;
import org.acmsl.commons.regexpplugin.Compiler;
import org.acmsl.commons.regexpplugin.Matcher;
import org.acmsl.commons.regexpplugin.MatchResult;
import org.acmsl.commons.regexpplugin.MalformedPatternException;
import org.acmsl.commons.regexpplugin.Pattern;
import org.acmsl.commons.regexpplugin.RegexpEngine;
import org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException;
import org.acmsl.commons.regexpplugin.RegexpManager;
import org.acmsl.commons.regexpplugin.RegexpPluginMisconfiguredException;
import org.acmsl.commons.utils.regexp.RegexpUtils;

/*
 * Importing Commons-Logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Provides some useful methods when working with URLs.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class URLUtils
    implements  Utils,
                Singleton
{
    /**
     * Singleton implemented to avoid the double-checked locking.
     */
    private static class URLUtilsSingletonContainer
    {
        /**
         * The actual singleton.
         */
        public static final URLUtils SINGLETON = new URLUtils();
    }

    /**
     * Question mark pattern.
     */
    private volatile static Pattern m__QuestionMarkPattern;

    /**
     * Block pattern.
     */
    private volatile static Pattern m__BlockPattern;

    /**
     * Parameter pattern.
     */
    private volatile static Pattern m__ParameterPattern;

    /**
     * Retrieves an URLUtils instance.
     * @return such instance.
     */
    public static URLUtils getInstance()
    {
        URLUtils result =  URLUtilsSingletonContainer.SINGLETON;

        synchronized (result)
        {
            Compiler t_Compiler = retrieveCompiler();

            result.initialize(t_Compiler);
        }
        
        return result;
    }

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected URLUtils() {};

    /**
     * Initializes given URLUtils instance.
     * @param compiler the regexp compiler.
     * @precondition compiler != null
     */
    private void initialize(final Compiler compiler)
    {
        immutableSetQuestionMarkPattern(
            compiler.compile("(.*?)\\?(.*)"));

        immutableSetBlockPattern(
            compiler.compile("(.*?)&(.*)"));

        immutableSetParameterPattern(
            compiler.compile("(.*?)=(.*)"));
    }

    /**
     * Specifies the question mark pattern.
     * @param pattern the pattern.
     * @precondition pattern != null
     */
    private void immutableSetQuestionMarkPattern(final Pattern pattern)
    {
        m__QuestionMarkPattern = pattern;
    }

    /**
     * Specifies the question mark pattern.
     * @param pattern the pattern.
     * @precondition pattern != null
     */
    protected void setQuestionMarkPattern(final Pattern pattern)
    {
        immutableSetQuestionMarkPattern(pattern);
    }

    /**
     * Retrieves the question mark pattern.
     * @return such pattern.
     */
    public Pattern getQuestionMarkPattern()
    {
        return m__QuestionMarkPattern;
    }

    /**
     * Specifies the block pattern.
     * @param pattern the pattern.
     * @precondition pattern != null
     */
    private void immutableSetBlockPattern(final Pattern pattern)
    {
        m__BlockPattern = pattern;
    }

    /**
     * Specifies the block pattern.
     * @param pattern the pattern.
     * @precondition pattern != null
     */
    protected void setBlockPattern(final Pattern pattern)
    {
        immutableSetBlockPattern(pattern);
    }

    /**
     * Retrieves the block pattern.
     * @return such pattern.
     */
    public Pattern getBlockPattern()
    {
        return m__BlockPattern;
    }

    /**
     * Specifies the parameter pattern.
     * @param pattern the pattern.
     * @precondition pattern != null
     */
    private void immutableSetParameterPattern(final Pattern pattern)
    {
        m__ParameterPattern = pattern;
    }

    /**
     * Specifies the parameter pattern.
     * @param pattern the pattern.
     * @precondition pattern != null
     */
    protected void setParameterPattern(final Pattern pattern)
    {
        immutableSetParameterPattern(pattern);
    }

    /**
     * Retrieves the parameter pattern.
     * @return such pattern.
     */
    public Pattern getParameterPattern()
    {
        return m__ParameterPattern;
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
        String result;

        int t_iQuestionPosition = url.indexOf("?");

        result =
            (t_iQuestionPosition == -1)
            ?   "?"
            :   "&";

        t_iQuestionPosition = extraInfo.indexOf("?");

        String t_strExtraInfo =
            (t_iQuestionPosition == -1)
            ?   extraInfo
            :   (t_iQuestionPosition == extraInfo.length() - 1)
                ?    extraInfo.substring(0, t_iQuestionPosition)
                :      extraInfo.substring(0, t_iQuestionPosition)
                     + "&"
                     + extraInfo.substring(t_iQuestionPosition + 1);

        result = url + result + t_strExtraInfo;

        return result;
    }

    /**
     * Puts a parameter in the query string. It simulates the query
     * string as a Map, so this method overrides previous values
     * if the parameter name matches.
     * Note: If the parameter has an array of values, then this
     * method adds a new one.
     * One drawback found is when the url has more than one question
     * mark in a row, like in "www.m-centric.com/search??q=wap".
     * @param query the query String to update.
     * @param name the parameter name.
     * @param value the parameter value.
     * @return the updated query.
     * @throws RegexpEngineNotFoundException if the regexp services are
     * not available.
     * @precondition query != null
     * @precondition name != null
     * @precondition value != null
     */
    public String putParamInQueryString(
        final String query,
        final String name,
        final String value)
      throws  RegexpEngineNotFoundException
    {
        return
            putParamInQueryString(
                query,
                name,
                value,
                isMultiple(query, name),
                valuePresent(query, name, value),
                createMatcher(RegexpManager.getInstance()),
                getQuestionMarkPattern(),
                getBlockPattern());
    }

    /**
     * Puts a parameter in the query string. It simulates the query
     * string as a Map, so this method overrides previous values
     * if the parameter name matches.
     * Note: If the parameter has an array of values, then this
     * method adds a new one.
     * One drawback found is when the url has more than one question
     * mark in a row, like in "www.m-centric.com/search??q=wap".
     * @param query the query String to update.
     * @param name the parameter name.
     * @param value the parameter value.
     * @param isMultiple whether given value appears more than once in the
     * query.
     * @param valuePresent whether given value is present in the query.
     * @param matcher the regexp matcher.
     * @param questionMarkPattern the question mark pattern.
     * @param blockPattern the block pattern.
     * @return the updated query.
     * @precondition query != null
     * @precondition name != null
     * @precondition value != null
     * @precondition matcher != null
     * @precondition questionMarkPattern != null
     * @precondition blockPattern != null
     */
    protected String putParamInQueryString(
        final String query,
        final String name,
        final String value,
        final boolean isMultiple,
        final boolean valuePresent,
        final Matcher matcher,
        final Pattern questionMarkPattern,
        final Pattern blockPattern)
    {
        StringBuffer t_sbResult = new StringBuffer();

        if  (matcher.contains(query, questionMarkPattern))
        {
            MatchResult t_MatchResult = matcher.getMatch();

            t_sbResult.append(t_MatchResult.group(1));

            t_sbResult.append("?");

            boolean t_bFirstBlock = true;

            if  (t_MatchResult.groups() > 1)
            {
                String t_strLeft = t_MatchResult.group(2);

                while  (matcher.contains(t_strLeft, blockPattern))
                {
                    t_MatchResult = matcher.getMatch();

                    String t_strBlock = t_MatchResult.group(1);

                    t_sbResult.append(
                        (isMultiple)
                        ?   t_strBlock + "&"
                        :   (t_strBlock.length() == 0)
                            ?   name + "=" + value
                            :   (   (t_bFirstBlock)
                                 || (valuePresent))
                                ?   parseParameter(
                                        t_strBlock,
                                        name,
                                        value,
                                        valuePresent)
                                :     "&"
                                    + parseParameter(
                                          t_strBlock,
                                          name,
                                          value,
                                          valuePresent));

                    if (t_bFirstBlock)
                    {
                        t_bFirstBlock = false;
                    }

                    t_strLeft = t_MatchResult.group(2);
                }

                t_sbResult.append(
                    (   (isMultiple)
                     && (!valuePresent))
                    ?   t_strLeft + "&" + name + "=" + value
                    :   (t_strLeft.length() == 0)
                        ?   name + "=" + value
                        :   (   (t_bFirstBlock)
                             || (valuePresent))
                            ?   parseParameter(
                                    t_strLeft,
                                    name,
                                    value,
                                    valuePresent)
                            :     "&"
                                + parseParameter(
                                      t_strLeft,
                                      name,
                                      value,
                                      valuePresent));

                if (t_bFirstBlock)
                {
                    t_bFirstBlock = false;
                }
            }
            else
            {
                t_sbResult.append("?");
                t_sbResult.append(name);
                t_sbResult.append("=");
                t_sbResult.append(value);
            }
        }
        else
        {
            t_sbResult.append(
                (valuePresent)
                ?     "?"
                    + parseParameter(
                          query,
                          name,
                          value,
                          valuePresent)
                :     "?"
                    + query
                    + "&"
                    + name
                    + "="
                    + value);
        }

        return t_sbResult.toString();
    }

    /**
     * Parses a block analyzing if it should be updated.
     * @param block the text block.
     * @param name the parameter name.
     * @param value the parameter value.
     * @param valuePresent true if the value of the parameter is
     * already present in the query string.
     * @return the updated block.
     * @throws RegexpEngineNotFoundException if the system is not properly
     * configured to provide regexp services.
     * @precondition block != null
     */
    protected String parseParameter(
        final String block,
        final String name,
        final String value,
        final boolean valuePresent)
      throws  RegexpEngineNotFoundException
    {
        return
            parseParameter(
                block,
                name,
                value,
                valuePresent,
                createMatcher(RegexpManager.getInstance()),
                getParameterPattern());
    }

    /**
     * Parses a block analyzing if it should be updated.
     * @param block the text block.
     * @param name the parameter name.
     * @param value the parameter value.
     * @param valuePresent true if the value of the parameter is
     * already present in the query string.
     * @param matcher the regexp matcher.
     * @param parameterPattern the parameter pattern.
     * @return the updated block.
     * @precondition block != null
     * @precondition name != null
     * @precondition value != null
     * @precondition matcher != null
     * @precondition parameterPattern != null
     */
    protected String parseParameter(
        final String block,
        final String name,
        final String value,
        final boolean valuePresent,
        final Matcher matcher,
        final Pattern parameterPattern)
    {
        StringBuffer t_sbResult = new StringBuffer();

        if  (matcher.contains(block, parameterPattern))
        {
            MatchResult t_ParamMatchResult = matcher.getMatch();

            if  (t_ParamMatchResult != null)
            {
                String t_strName = t_ParamMatchResult.group(1);

                String t_strValue = "";

                if  (   (t_strName.equals(name))
                     && (!valuePresent))
                {
                    t_strValue = value;
                }
                else if  (t_ParamMatchResult.groups() > 1)
                {
                    t_strValue = t_ParamMatchResult.group(2);
                }

                t_sbResult.append(t_strName);

                if  (t_strValue.length() > 0)
                {
                    t_sbResult.append("=");
                }

                t_sbResult.append(t_strValue);
            }
        }
        else
        {
            t_sbResult.append(block);

            if  (block.equals(name))
            {
                t_sbResult.append("=");
                t_sbResult.append(value);
            }
        }

        return t_sbResult.toString();
    }

    /**
     * Checks whether the parameter appears more than once in
     * the specified query.
     * @param query the query to parse.
     * @param name the parameter name.
     * @return true if the parameter has more than one value.
     */
    public boolean isMultiple(final String query, final String name)
    {
        boolean result = false;

        try
        {
            if  (   (query != null)
                 && ( name != null))
            {
                Compiler t_Compiler = retrieveCompiler();

                if  (t_Compiler != null)
                {
                    Pattern t_Pattern =
                        t_Compiler.compile(
                            "(.*?)(\\?|&)?"
//                        + Perl5Compiler.quotemeta(name)
                          + name
                          + "(=|&)(.*)");

                    Matcher t_Matcher =
                        createMatcher(RegexpManager.getInstance());

                    if  (   (t_Matcher != null)
                         && (t_Matcher.contains(query, t_Pattern)))
                    {
                        result =
                            t_Matcher.contains(
                                t_Matcher.getMatch().group(4),
                                t_Pattern);
                    }
                }
            }
        }
        catch  (MalformedPatternException malformedPatternException)
        {
            /*
             * Possibly the name contains invalid characters, that
             * conflicts with Perl5 regexp syntax.
             */
            LogFactory.getLog(getClass()).error(
                "Invalid pattern (possibly quote symbol conflict)",
                malformedPatternException);

        }
        catch  (RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            LogFactory.getLog(getClass()).error(
                "no regexp engine found",
                regexpEngineNotFoundException);
        }

        return result;
    }

    /**
     * Checks if the specified parameter already contains given value
     * in the query string.
     * @param query the query to parse.
     * @param name the parameter name.
     * @param value the parameter value.
     * @return <code>true</code> if the parameter value is already present.
     * @throws RegexpEngineNotFoundException if the system is not configured
     * properly in order to provide regexp services.
     * @precondition query != null
     * @precondition name != null
     * @precondition value != null
     */
    public boolean valuePresent(String query, String name, String value)
        throws  RegexpEngineNotFoundException
    {
        boolean result = false;

        Compiler t_Compiler = retrieveCompiler();

        try
        {
            Pattern t_Pattern =
                t_Compiler.compile(
                      "(.*?)(\\?|&)?"
//                  + Perl5Compiler.quotemeta(name)
                    + name
                    + "="
//                  + Perl5Compiler.quotemeta(value)
                    + value
                    + "(&)?(.*)");

            result =
                valuePresent(
                    query,
                    name,
                    value,
                    t_Pattern,
                    createMatcher(RegexpManager.getInstance()));
        }
        catch  (final MalformedPatternException malformedPatternException)
        {
            LogFactory.getLog(getClass()).error(
                "Invalid pattern (possibly quote symbol conflict)",
                malformedPatternException);
        }

        return result;
    }

    /**
     * Checks if the specified parameter already contains given value
     * in the query string.
     * @param query the query to parse.
     * @param name the parameter name.
     * @param value the parameter value.
     * @param pattern the pattern.
     * @param matcher the regexp matcher.
     * @return <code>true</code> if the parameter value is already present.
     * @precondition query != null
     * @precondition name != null
     * @precondition value != null
     * @precondition pattern != null
     * @precondition matcher != null
     */
    public boolean valuePresent(
        final String query,
        final String name,
        final String value,
        final Pattern pattern,
        final Matcher matcher)
    {
        return matcher.contains(query, pattern);
    }

    /**
     * Retrieves the regexp compiler.
     * @return such compiler.
     * @throws RegexpEngineNotFoundException if the system is not configured
     * properly in order to provide regexp services.
     */
    protected static Compiler retrieveCompiler()
        throws  RegexpEngineNotFoundException
    {
        return retrieveCompiler(RegexpUtils.getInstance());
    }

    /**
     * Retrieves the regexp compiler.
     * @param regexpUtils the RegexpUtils instance.
     * @return such compiler.
     * @precondition regexpUtils != null
     */
    protected static Compiler retrieveCompiler(final RegexpUtils regexpUtils)
    {
        return regexpUtils.getRegexpCompiler();
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
}
