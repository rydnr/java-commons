/*
                        ACM-SL Commons

    Copyright (C) 2002-2003  Jose San Leandro Armend�riz
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
 * Description: JDK1.4-specific regexp compiler adapter.
 *              This class makes possible the use of JDK1.4
 *              compilers inside this API.
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
package org.acmsl.commons.regexpplugin.jdk14regexp;

/*
 * Importing some ACM classes.
 */
import org.acmsl.commons.regexpplugin.Compiler;
import org.acmsl.commons.regexpplugin.jdk14regexp.MalformedPatternExceptionJDKAdapter;
import org.acmsl.commons.regexpplugin.MalformedPatternException;

/*
 * Importing JDK1.4 regexp classes.
 */
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/*
 * Importing Commons-Logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * JDK1.4-specific regexp compiler adapter. This class makes
 * possible the use of JDK1.4 compilers within this API.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armend�riz</a>
 * @version $Revision$
 */
public class CompilerJDKAdapter
    implements  Compiler
{
    /**
     * Case sensitiveness.
     */
    private boolean m__bCaseSensitive;

    /**
     * Multiline parsing.
     */
    private boolean m__bMultiline;

    /**
     * Compiles given regular expression and creates a Pattern object to
     * apply such rule on concrete text contents.
     * @param regexp the regular expression to compile.
     * @return the Pattern associated to such regular expression.
     * @throws MalformedPatternException if the regexp is not valid.
     */
    public org.acmsl.commons.regexpplugin.Pattern compile(final String regexp)
        throws  MalformedPatternException
    {
        return compile(regexp, isCaseSensitive(), isMultiline());
    }

    /**
     * Compiles given regular expression and creates a Pattern object to
     * apply such rule on concrete text contents.
     * @param regexp the regular expression to compile.
     * @param caseSensitive such condition.
     * @param multiline such condition.
     * @return the Pattern associated to such regular expression.
     * @throws MalformedPatternException if the regexp is not valid.
     */
    protected org.acmsl.commons.regexpplugin.Pattern compile(
        final String regexp,
        final boolean caseSensitive,
        final boolean multiline)
      throws  MalformedPatternException
    {
        org.acmsl.commons.regexpplugin.Pattern result = null;

        try
        {
            int t_iOptions = 0;

            t_iOptions |=
                (caseSensitive)
                ?  0
                :  Pattern.CASE_INSENSITIVE;

            t_iOptions |=
                (multiline)
                ?  Pattern.MULTILINE
                :  0;

            Pattern t_Pattern;

            if  (t_iOptions == 0)
            {
                t_Pattern = Pattern.compile(regexp);
            }
            else
            {
                t_Pattern = Pattern.compile(regexp, t_iOptions);
            }

            if  (t_Pattern != null)
            {
                result = new PatternJDKAdapter(t_Pattern);
            }
            else 
            {
                throw new MalformedPatternException("pattern compilation error");
            }
        }
        catch  (final PatternSyntaxException exception)
        {
            throw new MalformedPatternExceptionJDKAdapter(exception);
        }
        catch  (final IllegalArgumentException illegalArgumentException)
        {
            if  (resetOptions())
            {
                result = compile(regexp);
            }
        }

        return result;
    }

    /**
     * Resets the compiler options.
     * @return true if the options actually changed.
     */
    private boolean resetOptions()
    {
        boolean result = false;

        result =
            (   (isCaseSensitive())
             || (isMultiline()));

        setCaseSensitive(false);

        setMultiline(false);

        return result;
    }

    /**
     * Sets whether the compiler should care about case sensitiveness
     * or not.
     * @param caseSensitive true for differentiate upper from lower case.
     */
    protected final void immutableSetCaseSensitive(final boolean caseSensitive)
    {
        m__bCaseSensitive = caseSensitive;
    }

    /**
     * Sets whether the compiler should care about case sensitiveness
     * or not.
     * @param caseSensitive true for differentiate upper from lower case.
     */
    public void setCaseSensitive(final boolean caseSensitive)
    {
        immutableSetCaseSensitive(caseSensitive);
    }

    /**
     * Retrieves whether the compiler should care about case sensitiveness
     * or not.
     * @return true if upper from lower cases are processed differently.
     */
    public boolean isCaseSensitive()
    {
        return m__bCaseSensitive;
    }

    /**
     * Sets whether the compiler should care about new line delimiters
     * or not.
     * @param multiline false for parsing each line at a time.
     */
    protected final void immutableSetMultiline(final boolean multiline)
    {
        m__bMultiline = multiline;
    }

    /**
     * Sets whether the compiler should care about new line delimiters
     * or not.
     * @param multiline false for parsing each line at a time.
     */
    public void setMultiline(final boolean multiline)
    {
        immutableSetMultiline(multiline);
    }

    /**
     * Sets whether the compiler should care about new line delimiters
     * or not.
     * @return false if the engine parses each line one at a time.
     */
    public boolean isMultiline()
    {
        return m__bMultiline;
    }
}