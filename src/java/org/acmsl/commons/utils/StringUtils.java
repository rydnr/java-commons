/*
                        ACM-SL Commons

    Copyright (C) 2002-2003  Jose San Leandro Armendáriz
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
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-307  USA


    Thanks to ACM S.L. for distributing this library under the LGPL license.
    Contact info: jsr000@terra.es
    Postal Address: c/Playa de Lagoa, 1
                    Urb. Valdecabañas
                    Boadilla del monte

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armendáriz
 *
 * Description:	Provides some useful methods when working with Strings.
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
package org.acmsl.commons.utils;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Utils;
import org.acmsl.commons.regexpplugin.Compiler;
import org.acmsl.commons.regexpplugin.Helper;
import org.acmsl.commons.regexpplugin.MalformedPatternException;
import org.acmsl.commons.regexpplugin.Matcher;
import org.acmsl.commons.regexpplugin.MatchResult;
import org.acmsl.commons.regexpplugin.Pattern;
import org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException;
import org.acmsl.commons.regexpplugin.RegexpManager;
import org.acmsl.commons.utils.StringValidator;
import org.acmsl.commons.version.Version;
import org.acmsl.commons.version.VersionFactory;

/*
 * Importing some JDK classes.
 */
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringTokenizer;

/*
 * Importing commons-logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Provides some useful methods when working with Strings.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @stereotype tested
 * @testcase unittests.org.acmsl.commons.utils.TestStringUtils
 * @version $Revision$
 */
public abstract class StringUtils
    implements  Utils
{
    /**
     * Singleton implemented as a weak reference.
     */
    private static WeakReference singleton;

    /**
     * Compiler instance.
     */
    private static Compiler m__Compiler;

    /**
     * The subpackage pattern.
     */
    private static Pattern m__SubPackagePattern;

    /**
     * The package pattern.
     */
    private static Pattern m__PackagePattern;

    /**
     * The justify pattern.
     */
    private static Pattern m__JustifyPattern;

    /**
     * Specifies a new weak reference.
     * @param utils the utils instance to use.
     */
    protected static void setReference(StringUtils utils)
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
     * Retrieves a StringUtils instance.
     * @return such instance.
     */
    public static StringUtils getInstance()
    {
        StringUtils result = null;

        WeakReference reference = getReference();

        if  (reference != null) 
        {
            result = (StringUtils) reference.get();
        }

        if  (result == null) 
        {
            result = new StringUtils() {};
        }

        Compiler t_Compiler = result.getCompiler();

        if  (t_Compiler == null)
        {
            try 
            {
                t_Compiler = createCompiler();

                if  (t_Compiler != null)
                {
                    result.inmutableSetCompiler(t_Compiler);

                    result.inmutableSetSubPackagePattern(
                        t_Compiler.compile("(.*)\\.(.*)"));

                    result.inmutableSetPackagePattern(
                        t_Compiler.compile("(.*?)\\.(.*)"));

                    result.inmutableSetJustifyPattern(
                        t_Compiler.compile("(.*?)\\s+(.*)"));
                }
                else 
                {
                    LogFactory.getLog(StringUtils.class).error(
                        "compiler unavailable");
                }

                setReference(result);
            }
            catch  (MalformedPatternException malformedPatternException)
            {
                /*
                 * This should never happen. It's a compile-time
                 * error not detected by the compiler, but it's
                 * nothing dynamic. So, if it fails, fix it once
                 * and forget.
                 */
                LogFactory.getLog(StringUtils.class).error(
                    "Invalid subpackage pattern",
                    malformedPatternException);

                result = null;
            }
            catch  (RegexpEngineNotFoundException regexpEngineNotFoundException)
            {
                LogFactory.getLog(StringUtils.class).error(
                    "no regexp engine found",
                    regexpEngineNotFoundException);

                result = null;
            }
            catch  (Throwable throwable)
            {
                LogFactory.getLog(StringUtils.class).fatal(
                    "Unknown error",
                    throwable);
            }
        }
        
        return result;
    }

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected StringUtils()  {};

    /**
     * Specifies the regexp compiler.
     * @param compiler the compiler.
     */
    private void inmutableSetCompiler(Compiler compiler)
    {
        m__Compiler = compiler;
    }

    /**
     * Specifies the regexp compiler.
     * @param compiler the compiler.
     */
    protected void setCompiler(Compiler compiler)
    {
        inmutableSetCompiler(compiler);
    }

    /**
     * Retrieves the regexp compiler.
     * @return such compiler.
     */
    protected Compiler getCompiler()
    {
        return m__Compiler;
    }

    /**
     * Specifies the subpackage pattern.
     * @param pattern the pattern.
     */
    private void inmutableSetSubPackagePattern(Pattern pattern)
    {
        m__SubPackagePattern = pattern;
    }

    /**
     * Specifies the subpackage pattern.
     * @param pattern the pattern.
     */
    protected void setSubPackagePattern(Pattern pattern)
    {
        inmutableSetSubPackagePattern(pattern);
    }

    /**
     * Retrieves the subpackage pattern.
     * @return such pattern.
     */
    public Pattern getSubPackagePattern()
    {
        return m__SubPackagePattern;
    }

    /**
     * Specifies the package pattern.
     * @param pattern the pattern.
     */
    private void inmutableSetPackagePattern(Pattern pattern)
    {
        m__PackagePattern = pattern;
    }

    /**
     * Specifies the package pattern.
     * @param pattern the pattern.
     */
    protected void setPackagePattern(Pattern pattern)
    {
        inmutableSetPackagePattern(pattern);
    }

    /**
     * Retrieves the package pattern.
     * @return such pattern.
     */
    public Pattern getPackagePattern()
    {
        return m__PackagePattern;
    }

    /**
     * Specifies the justify pattern.
     * @param pattern the pattern.
     */
    private void inmutableSetJustifyPattern(Pattern pattern)
    {
        m__JustifyPattern = pattern;
    }

    /**
     * Specifies the justify pattern.
     * @param pattern the pattern.
     */
    protected void setJustifyPattern(Pattern pattern)
    {
        inmutableSetJustifyPattern(pattern);
    }

    /**
     * Retrieves the justify pattern.
     * @return such pattern.
     */
    public Pattern getJustifyPattern()
    {
        return m__JustifyPattern;
    }

    /**
     * Creates the compiler.
     * @return the regexp compiler.
     */
    protected static synchronized Compiler createCompiler()
        throws  RegexpEngineNotFoundException
    {
        return RegexpManager.createCompiler();

    }

    /**
     * Removes all duplicates of specified char in given text.
     * @param text the text to parse.
     * @param lookfor the char to remove duplicates.
     * @return the updated text.
     */
    public String removeDuplicate(
        String  original,
        char    lookfor)
    {
        String result = original;

        StringValidator t_StringValidator = StringValidator.getInstance();

        if  (t_StringValidator != null)
        {
            if  (!t_StringValidator.isEmpty(original))
            {
                String t_strLookFor = new String(new char[]{ lookfor });

                StringTokenizer t_strTokenizer =
                    new StringTokenizer(original, t_strLookFor, false);

                if  (t_strTokenizer.hasMoreTokens())
                {
                    result = "";
                }

                while  (t_strTokenizer.hasMoreTokens())
                {
                    result += t_strTokenizer.nextToken();

                    if  (t_strTokenizer.hasMoreTokens())
                    {
                        result += t_strLookFor;
                    }
                }
            }
        }
        else 
        {
            LogFactory.getLog(getClass()).fatal(
                "cannot retrieve StringValidator instance");
        }
        
        return result;
    }

    /**
     * Replaces a sequence with another inside a text content.
     * @param content the text content to update.
     * @param original the text to replace.
     * @param replacement the replacement.
     * @return the updated version of the original text.
     */
    public String replace(
        String content,
        String original,
        String replacement)
    {
        StringBuffer t_sbResult = new StringBuffer();

        StringValidator t_StringValidator = StringValidator.getInstance();

        if  (t_StringValidator != null)
        {
            try
            {
                Compiler t_Compiler = getCompiler();

                if  (t_Compiler != null)
                {
                    Matcher t_Matcher = RegexpManager.createMatcher();

                    MatchResult t_MatchResult = null;

                    Pattern t_Pattern =
                        t_Compiler.compile(
                            "(.*?)" + original + "(.*)");

                    if  (   (t_Matcher != null)
                         && (t_Pattern != null))
                    {
                        while   (   (!t_StringValidator.isEmpty(content))
                                 && (t_Matcher.contains(content, t_Pattern)))
                        {
                            t_MatchResult = t_Matcher.getMatch();

                            t_sbResult.append(t_MatchResult.group(1) + replacement);

                            // the rest is parsed next.
                            content = t_MatchResult.group(2);
                        }
                    }
                    else 
                    {
                        LogFactory.getLog(getClass()).error(
                            "regexp matcher unavailable");
                    }
                }

                if  (!t_StringValidator.isEmpty(content))
                {
                    t_sbResult.append(content);
                }
            }
            catch	(MalformedPatternException malformedPatternException)
            {
                LogFactory.getLog(StringUtils.class).warn(
                    "Malformed pattern (possibly a quote symbol conflict",
                    malformedPatternException);
            }
            catch  (RegexpEngineNotFoundException regexpEngineNotFoundException)
            {
                /*
                 * This exception is thrown only if no regexp library is available
                 * at runtime. Not only this one, but any method provided by this
                 * class that use regexps will not work.
                 */
                LogFactory.getLog(getClass()).error(
                    "Cannot find any regexp engine",
                    regexpEngineNotFoundException);
            }
        }
        else 
        {
            LogFactory.getLog(getClass()).fatal(
                "cannot retrieve StringValidator instance");
        }

        return t_sbResult.toString();
    }

    /**
     * Puts a quote before and after given string.
     * @param text the content to quote.
     * @return the quoted version of such content.
     */
    public String quote(String text)
    {
        return quote(text, '\"');
    }

    /**
     * Puts a quote before and after given string.
     * @param text the content to quote.
     * @param quote the quote.
     * @return the quoted version of such content.
     */
    public String quote(String text, char quote)
    {
        String result = text;

        boolean t_bProcessed = true;

        StringValidator t_StringValidator = StringValidator.getInstance();

        if  (t_StringValidator != null)
        {
            if  (!t_StringValidator.isEmpty(text))
            {
                if	(text.charAt(0) != quote)
                {
                    if  (text.charAt(0) != quote)
                    {
                        result = quote + text + quote;
                    }
                    else
                    {
                        if  (text.trim().length() > 0)
                        {
                            result =
                                  quote
                                + text.substring(1, text.length() - 1)
                                + quote;
                        }
                        else
                        {
                            t_bProcessed = false;
                        }
                    }
                }
                else
                {
                    result = text;
                }
            }
        }
        else 
        {
            LogFactory.getLog(getClass()).fatal(
                "cannot retrieve StringValidator instance");
        }

        if  (!t_bProcessed)
        {
            result = "" + quote + quote;
        }

        return result;
    }

    /**
     * Removes all quotes at the beginning and ending of given string.
     * @param text the content to unquote.
     * @return the unquoted version of such content.
     */
    public String unquote(String text)
    {
        return unquote(text, '\'');
    }

    /**
     * Removes all quotes at the beginning and ending of given string.
     * @param text the content to unquote.
     * @param quote the quote.
     * @return the unquoted version of such content.
     */
    public String unquote(String text, char quote)
    {
        String result = "";

        StringValidator t_StringValidator = StringValidator.getInstance();

        if  (t_StringValidator != null)
        {
            if  (!t_StringValidator.isEmpty(text))
            {
                if	(text.charAt(0) != quote)
                {
                    if  (text.charAt(0) != quote)
                    {
                        result = text;
                    }
                    else
                    {
                        if  (text.trim().length() > 0)
                        {
                            result =
                                text.substring(1, text.length() - 1);
                        }
                    }
                }
                else
                {
                    result =
                        text.substring(1, text.length() - 1);
                }
            }
        }
        else 
        {
            LogFactory.getLog(getClass()).fatal(
                "cannot retrieve StringValidator instance");
        }

        return result;
    }

    /**
     * Substitutes most common HTML reserved characters with their escaped
     * version.
     * i.e. it takes all "<" and replaces them with "&amp;lt;".
     * @param htmlContent the html contents to escape.
     * @return the escaped version of such contents.
     */
    public String escapeHTML(String htmlContents)
    {
        String result = htmlContents;

        result = replace(result, "<", "&lt;");
        result = replace(result, ">", "&gt;");
        result = replace(result, "\"", "&quot;");
        result = replace(result, "'", "&apos;");

        return result;
    }

    /**
     * Substitutes most common HTML escape sequences with their unescaped
     * version.
     * i.e. it takes all "&amp;lt;" and replaces them with "<".
     * @param htmlContent the escaped html contents.
     * @return the unescaped version of such contents.
     */
    public String unescapeHTML(String htmlContents)
    {
        String result = htmlContents;

        result = replace(result, "&lt;", "<");
        result = replace(result, "&gt;", ">");
        result = replace(result, "&quot;", "\"");
        result = replace(result, "&apos;", "'");

        return result;
    }

    /**
     * Normalizes given value, whose words are defined by a concrete char
     * separator.
     * @param value the value to normalize.
     * @param separator the word separator.
     * @return the normalized version.
     */
    public String normalize(String value, char separator)
    {
        String result = value;

        if  (result != null) 
        {
            try
            {
                value = capitalize(value, separator);

                Helper t_Helper = RegexpManager.createHelper();

                result = t_Helper.replaceAll(value, "\\W", "");
            }
            catch (MalformedPatternException malformedPatternException)
            {
                LogFactory.getLog(getClass()).fatal(
                    "Malformed pattern",
                    malformedPatternException);
            }
            catch (RegexpEngineNotFoundException regexpEngineNotFoundException)
            {
                LogFactory.getLog(getClass()).fatal(
                    "Cannot find any regexp engine.",
                    regexpEngineNotFoundException);
            }
        }
        
        return result;
    }

    /**
     * Capitalizes the words contained in given string, using a concrete char
     * separator.
     * @param text the text to process.
     * @param separator the word separator.
     * @return the capitalized string.
     */
    public String capitalize(String text, char separator)
    {
        StringBuffer t_sbResult = null;

        if  (text != null) 
        {
            t_sbResult = new StringBuffer(text.length());

            boolean t_bToUpper = true;

            for  (int t_iIndex = 0; t_iIndex < text.length(); t_iIndex++)
            {
                if (text.charAt(t_iIndex) == separator)
                {
                    t_bToUpper = true;
                    continue;
                }

                if (t_bToUpper)
                {
                    t_sbResult.append(
                        Character.toUpperCase(text.charAt(t_iIndex)));

                    t_bToUpper = false;
                    continue;
                }

                t_sbResult.append(text.charAt(t_iIndex));
            }
        }

        return ((t_sbResult == null) ? null : t_sbResult.toString());
    }

    /**
     * Capitalizes the words contained in given string, using a concrete char
     * separator. The first letter is always in lower case.
     * @param text the text to process.
     * @param separator the word separator.
     * @return the processed string.
     */
    public String toJavaMethod(String text, char separator)
    {
        StringBuffer t_sbResult = new StringBuffer();

        text = normalize(text, separator);

        if  (text != null)
        {
            if  (text.length() > 0) 
            {
                t_sbResult.append(text.substring(0,1).toLowerCase());
                t_sbResult.append(text.substring(1));
            }
            else 
            {
                t_sbResult.append(text.toLowerCase());
            }
        }

        return t_sbResult.toString();
    }

    /**
     * Capitalizes the contents, using given separator, and word list.
     * @param text the text.
     * @param separator the separator.
     * @param words the predefined words.
     * @return the token collection.
     */
    public String toJavaMethod(String text, char separator, String[] words)
    {
        String result = text;

        try
        {
            if  (   (text      != null)
                 && (words     != null))
            {
                Helper t_Helper = RegexpManager.createHelper();

                for  (int t_iIndex = 0; t_iIndex < words.length; t_iIndex++) 
                {
                    result =
                        t_Helper.replaceAll(
                            result,
                            words[t_iIndex],
                            capitalize(words[t_iIndex], separator));
                }
            }

            result = toJavaMethod(result, separator);
        }
        catch	(MalformedPatternException malformedPatternException)
        {
            /*
             * This exception is about pattern mismatch. In my opinion,
             * it's an error that should be detected at compile time,
             * but regexp API design cannot provide this functionality,
             * since all patterns are defined with strings, and therefore
             * they escape all compiler checks.
             */
            LogFactory.getLog(StringUtils.class).warn(
                "Malformed static patterns are fatal coding errors.",
                malformedPatternException);
        }
        catch  (RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            /*
             * This exception is thrown only if no regexp library is available
             * at runtime. Not only this one, but any method provided by this
             * class that use regexps will not work.
             */
            LogFactory.getLog(StringUtils.class).fatal(
                "Cannot find any regexp engine.",
                regexpEngineNotFoundException);
        }
        
        return result;
    }

    /**
     * Retrieves all tokens found in given string, using given separator.
     * @param text the text.
     * @param separator the separator.
     * @return the token collection.
     */
    public Collection tokenize(String text, String separator)
    {
        ArrayList result = new ArrayList();

        StringValidator t_StringValidator = StringValidator.getInstance();

        if  (t_StringValidator != null)
        {
            try
            {
                if  (   (text      != null)
                     && (separator != null))
                {
                    Compiler t_Compiler = getCompiler();

                    Matcher t_Matcher = RegexpManager.createMatcher();

                    MatchResult t_MatchResult = null;

                    if  (   (t_Compiler != null)
                         && (t_Matcher  != null))
                    {
                        Pattern t_Pattern =
                            t_Compiler.compile(
                                "(.*?)" + separator + "(.*)");

                        while  (   (!t_StringValidator.isEmpty(text))
                                && (t_Matcher.contains(text, t_Pattern)))
                        {
                            t_MatchResult = t_Matcher.getMatch();

                            result.add(t_MatchResult.group(1));

                            // the rest is parsed next.
                            text = t_MatchResult.group(2);
                        }
                    }
                    else 
                    {
                        LogFactory.getLog(getClass()).error(
                              "regexp compiler (" + t_Compiler + ") "
                            + "or matcher (" + t_Matcher + ")  unavailable");
                    }
                }

                if  (!t_StringValidator.isEmpty(text))
                {
                    result.add(text);
                }
            }
            catch	(MalformedPatternException malformedPatternException)
            {
                LogFactory.getLog(getClass()).error(
                    "Malformed pattern (possibly due to quote symbol conflict)",
                    malformedPatternException);
            }
            catch  (RegexpEngineNotFoundException regexpEngineNotFoundException)
            {
                /*
                 * This exception is thrown only if no regexp library is available
                 * at runtime. Not only this one, but any method provided by this
                 * class that use regexps will not work.
                 */
                LogFactory.getLog(getClass()).error(
                    "Cannot find any regexp engine.",
                    regexpEngineNotFoundException);
            }
        }
        else 
        {
            LogFactory.getLog(getClass()).fatal(
                "cannot retrieve StringValidator instance");
        }
        
        return result;
    }

    /**
     * Extracts the most specific package name from given text.
     * @param text the text.
     * @return the leaf package name.
     */
    public String extractPackageName(String text)
    {
        String result = text;

        try
        {
            if  (result != null)
            {
                Matcher t_Matcher = RegexpManager.createMatcher();

                MatchResult t_MatchResult = null;

                Pattern t_SubPackagePattern = getSubPackagePattern();

                if  (   (result != null)
                     && (result.trim().length() > 0)
                     && (t_SubPackagePattern != null)
                     && (t_Matcher.contains(
                             result, t_SubPackagePattern)))
                {
                    t_MatchResult = t_Matcher.getMatch();

                    // the rest is parsed next.
                    result = t_MatchResult.group(2);
                }
            }

            result = capitalize(result, '_');
        }
        catch  (RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            /*
             * This exception is thrown only if no regexp library is available
             * at runtime. Not only this one, but any method provided by this
             * class that use regexps will not work.
             */
            LogFactory.getLog(getClass()).fatal(
                "Cannot find any regexp engine.",
                regexpEngineNotFoundException);
        }
        
        return result;
    }

    /**
     * Translates given package name to a relative file path.
     * @param packageName the pacjage name.
     * @return such path
     */
    public String packageToFilePath(String packageName)
    {
        StringBuffer t_sbResult = new StringBuffer();

        try
        {
            StringValidator t_StringValidator =
                StringValidator.getInstance();

            if  (   (packageName != null)
                 && (t_StringValidator != null))
            {
                Matcher t_Matcher = RegexpManager.createMatcher();

                MatchResult t_MatchResult = null;

                boolean t_bMatched = false;

                Pattern t_PackagePattern = getPackagePattern();

                while  (   (packageName != null)
                        && (packageName.trim().length() > 0)
                        && (t_PackagePattern != null)
                        && (t_Matcher.contains(
                               packageName, t_PackagePattern)))
                {
                    t_MatchResult = t_Matcher.getMatch();

                    String t_strSubPattern = t_MatchResult.group(1);

                    if  (   (!t_StringValidator.isEmpty(t_strSubPattern))
                         && (!".".equals(t_strSubPattern)))
                    {
                        if  (t_bMatched)
                        {
                            t_sbResult.append(File.separator);
                        }
                        
                        t_sbResult.append(t_strSubPattern);

                        t_bMatched = true;
                    }

                    // the rest is parsed next.
                    packageName = t_MatchResult.group(2);
                }

                if  (   (!t_StringValidator.isEmpty(packageName))
                     && (!".".equals(packageName)))
                {
                    if  (t_bMatched)
                    {
                        t_sbResult.append(File.separator);
                    }

                    t_sbResult.append(packageName);
                }
            }
        }
        catch  (RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            /*
             * This exception is thrown only if no regexp library is available
             * at runtime. Not only this one, but any method provided by this
             * class that use regexps will not work.
             */
            LogFactory.getLog(getClass()).fatal(
                "Cannot find any regexp engine.",
                regexpEngineNotFoundException);
        }

        return t_sbResult.toString();
    }

    /**
     * Justifies given text to not exceed specified margin,
     * if possible.
     * @param text the text to justify.
     * @param linePrefix the prefix to add to all justified lines.
     * @param margin the margin.
     * @return the justified text.
     */
    public String justify(String text, int margin)
    {
        return justify(text, "", margin);
    }

    /**
     * Justifies given text to not exceed specified margin,
     * if possible.
     * @param text the text to justify.
     * @param linePrefix the prefix to add to all justified lines.
     * @param margin the margin.
     * @return the justified text.
     */
    public String justify(String text, String linePrefix, int margin)
    {
        StringBuffer t_sbResult = new StringBuffer();

        try
        {
            StringValidator t_StringValidator =
                StringValidator.getInstance();

            if  (   (text != null)
                 && (t_StringValidator != null))
            {
                if  (linePrefix == null)
                {
                    linePrefix = "";
                }

                Matcher t_Matcher = RegexpManager.createMatcher();

                MatchResult t_MatchResult = null;

                Pattern t_JustifyPattern = getJustifyPattern();

                String t_strCurrentLine = "";

                String t_strCurrentWord = "";

                while  (   (!t_StringValidator.isEmpty(text))
                        && (t_JustifyPattern != null)
                        && (t_Matcher.contains(
                               text, t_JustifyPattern)))
                {
                    t_MatchResult = t_Matcher.getMatch();

                    if  (t_MatchResult != null)
                    {
                        t_strCurrentWord = t_MatchResult.group(1);

                        if  (t_strCurrentWord != null)
                        {
                            if  (  t_strCurrentLine.length()
                                 + t_strCurrentWord.length()
                                 + 1
                                 > margin)
                            {
                                t_sbResult.append(t_strCurrentLine);

                                if  (t_strCurrentLine.length() > 0)
                                {
                                    t_sbResult.append("\n");
                                }

                                t_strCurrentLine = linePrefix;
                            }
                            else 
                            {
                                if  (t_strCurrentLine.length() > 0)
                                {
                                    t_strCurrentLine += " ";
                                }
                            }

                            t_strCurrentLine += t_strCurrentWord;
                        }

                        // the rest is parsed next.
                        text = t_MatchResult.group(2);
                    }
                }

                t_strCurrentWord = text;

                t_sbResult.append(t_strCurrentLine);

                if  (  t_strCurrentLine.length()
                     + t_strCurrentWord.length()
                     + 1
                     > margin)
                {
                    if  (t_strCurrentLine.length() > 0)
                    {
                        t_sbResult.append("\n");

                        t_sbResult.append(linePrefix);
                    }

                    if  (t_strCurrentWord.length() > margin)
                    {
                        t_sbResult.append(t_strCurrentWord);
                        t_sbResult.append("\n");
                        t_sbResult.append(linePrefix);
                    }
                }
                else 
                {
                    t_sbResult.append(" ");
                }

                t_sbResult.append(t_strCurrentWord);
            }
        }
        catch  (RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            /*
             * This exception is thrown only if no regexp library is available
             * at runtime. Not only this one, but any method provided by this
             * class that use regexps will not work.
             */
            LogFactory.getLog(getClass()).fatal(
                "Cannot find any regexp engine.",
                regexpEngineNotFoundException);
        }

        return t_sbResult.toString();
    }

    /**
     * Concrete version object updated everytime it's checked-in in a
     * CVS repository.
     */
    public static final Version VERSION =
        VersionFactory.createVersion("$Revision$");

    /**
     * Retrieves the current version of this object.
     * @return the version object with such information.
     */
    public Version getVersion()
    {
        return VERSION;
    }

    /**
     * Retrieves the current version of this class. It's defined because
     * this is a utility class that cannot be instantiated.
     * @return the object with class version information.
     */
    public static Version getClassVersion()
    {
        return VERSION;
    }
}
