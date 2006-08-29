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
 * Description: Jakarta ORO-specific regexp helper adapter.
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
import org.acmsl.commons.regexpplugin.Helper;
import org.acmsl.commons.utils.ClassLoaderUtils;

/*
 * Importing ORO classes.
 */
import org.apache.oro.text.regex.MalformedPatternException;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;
import org.apache.oro.text.regex.StringSubstitution;
import org.apache.oro.text.regex.Util;

/*
 * Importing some Apache Commons Logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Jakarta ORO-specific regexp helper adapter.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class HelperOROAdapter
    implements  Helper
{
    /**
     * Finds all occurrences of a specified pattern in given input contents,
     * and replaces them with passed String.
     * @param input the input text to process.
     * @param pattern the pattern to replace.
     * @param replacement the replacement text.
     * @return the updated input.
     * @throws MalformedPatternException if given regexp is malformed.
     */
    public String replaceAll(
        final String input, final String pattern, final String replacement)
      throws  org.acmsl.commons.regexpplugin.MalformedPatternException
    {
        String result = null;

        if  (   (input       != null)
             && (pattern     != null)
             && (replacement != null))
        {
            String t_strSafePattern = null;

            try
            {
                result =
                    Util.substitute(
                        new Perl5Matcher(),
                        new Perl5Compiler().compile(pattern),
                        new StringSubstitution(
//                            Perl5Compiler.quotemeta(replacement)),
                            replacement),
                        input,
                        Util.SUBSTITUTE_ALL);
            }
            catch  (final MalformedPatternException malformedPatternException)
            {
                throw
                    new
                        org.acmsl.commons.regexpplugin
                            .MalformedPatternException(
                                "Invalid pattern: " + pattern,
                                malformedPatternException);
            }
            catch  (final NoSuchMethodError incompatibleVersionError)
            {
                String location = findLocation(Util.class);

                // This happens on Oro 2.0.8 if another Oro version
                // is loaded first.
                LogFactory.getLog(HelperOROAdapter.class).fatal(
                      "Incompatible Oro version, loaded from: "
                    + ((location != null) ? "[" + location + "]" : ""),
                    incompatibleVersionError);
            }
        }

        if  (result == null) 
        {
            result = "";
        }

        if  (result.length() == 0) 
        {
            result = input;
        }
        
        return result;
    }

    /**
     * Finds the location of given instance.
     * @param classInstance the class to locate.
     * @return such information.
     * @precondition classInstance != null
     */
    protected String findLocation(final Class classInstance)
    {
        return findLocation(classInstance, ClassLoaderUtils.getInstance());
    }

    /**
     * Finds the location of given instance.
     * @param classInstance the class to locate.
     * @return such information.
     * @precondition classInstance != null
     * @precondition classLoaderUtils != null
     */
    protected String findLocation(
        final Class classInstance, final ClassLoaderUtils classLoaderUtils)
    {
        return classLoaderUtils.findLocation(classInstance, true);
    }
}
