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
 * Description: Jakarta ORO-specific regexp compiler adapter. This class makes
 *              possible the use of ORO compilers inside this API. A delegation
 *              is used because Perl5Compiler is a final class.
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
package org.acmsl.commons.regexpplugin.jakartaoro;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.regexpplugin.Compiler;
import org.acmsl.commons.regexpplugin.Pattern;

/*
 * Importing Jakarta ORO classes.
 */
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Perl5Compiler;

/*
 * Importing some commons-logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Jakarta ORO-specific regexp compiler adapter. This class makes possible the
 * use of ORO compilers inside this API. A delegation is used because
 * Perl5Compiler is a final class.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class Perl5CompilerOROAdapter
    implements  Compiler
{
    /**
     * Delegated instance.
     */
    private Perl5Compiler m__Instance;

    /**
     * Case sensitiveness.
     */
    private boolean m__bCaseSensitive;

    /**
     * Multiline parsing.
     */
    private boolean m__bMultiline;

    /**
     * Specifies the adaptee.
     * @param adaptee the compiler to adapt.
     */
    protected final void immutableSetCompiler(
        final Perl5Compiler adaptee)
    {
        m__Instance = adaptee;
    }

    /**
     * Specifies the adaptee.
     * @param adaptee the compiler to adapt.
     */
    protected void setCompiler(final Perl5Compiler adaptee)
    {
        immutableSetCompiler(adaptee);
    }

    /**
     * Retrieves an instance of Perl5Compiler class.
     * @return a new (or already existing) compiler.
     */
    protected Perl5Compiler getCompiler()
    {
        Perl5Compiler result = m__Instance;

        if  (result == null)
        {
            result = new Perl5Compiler();

            setCompiler(result);
        }

        return result;
    }

    /**
     * Compiles given regular expression and creates a Pattern object to
     * apply such rule on concrete text contents.
     * @param regexp the regular expression to compile.
     * @return the Pattern associated to such regular expression.
     * @throws org.acmsl.commons.regexpplugin.MalformedPatternException if
     * given regexp is malformed.
     * @precondition regexp != null
     */
    public Pattern compile(final String regexp)
        throws  org.acmsl.commons.regexpplugin.MalformedPatternException
    {
        return
            compile(regexp, getCompiler(), isCaseSensitive(), isMultiline());
    }

    /**
     * Compiles given regular expression and creates a Pattern object to
     * apply such rule on concrete text contents.
     * @param regexp the regular expression to compile.
     * @param compiler the compiler.
     * @param caseSensitive such option.
     * @param multiline such option.
     * @return the Pattern associated to such regular expression.
     * @throws org.acmsl.commons.regexpplugin.MalformedPatternException if
     * given regexp is malformed.
     * @precondition regexp != null
     * @precondition compiler != null
     */
    protected Pattern compile(
        final String regexp,
        final Perl5Compiler compiler,
        final boolean caseSensitive,
        final boolean multiline)
      throws  org.acmsl.commons.regexpplugin.MalformedPatternException
    {
        Pattern result = null;

        try
        {
            int t_iOptions = Perl5Compiler.DEFAULT_MASK;

            t_iOptions |=
                (caseSensitive)
                ?  0
                :  Perl5Compiler.CASE_INSENSITIVE_MASK;

            t_iOptions |=
                (multiline)
                ?  Perl5Compiler.MULTILINE_MASK
                :  Perl5Compiler.SINGLELINE_MASK;

            result =
                new PatternOROAdapter(
                    compiler.compile(regexp, t_iOptions));
        }
        catch  (final MalformedPatternException malformedPatternException)
        {
            throw
                new MalformedPatternExceptionOROAdapter(
                    malformedPatternException);
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

    /*
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
