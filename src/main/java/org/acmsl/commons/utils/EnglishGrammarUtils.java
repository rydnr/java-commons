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
 * Description: Provides some grammar rules for English language.
 *
 * Last modified by: $Author: chous $ at $Date: 2006-06-14 21:01:54 +0200 (Wed, 14 Jun 2006) $
 *
 * File version: $Revision: 550 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: EnglishGrammarUtils.java 550 2006-06-14 19:01:54Z chous $
 *
 */
package org.acmsl.commons.utils;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Utils;
import org.acmsl.commons.patterns.Singleton;

/*
 * Importing some JDK classes.
 */
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Provides some grammar rules for English language.
 * @author <a href="mailto:jsanleandro@yahoo.es"
 *         >Jose San Leandro Armendáriz</a>
 * @version $Revision: 550 $
 */
public class EnglishGrammarUtils
    extends  GrammarUtils
    implements  Singleton
{
    /**
     * Singleton implemented to avoid the double-checked locking.
     */
    private static class EnglishGrammarUtilsSingletonContainer
    {
        /**
         * The actual singleton.
         */
        public static final EnglishGrammarUtils SINGLETON =
            new EnglishGrammarUtils();
    }

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected EnglishGrammarUtils()
    {
        super(new Locale("en"));
    }

    /**
     * Retrieves a EnglishGrammarUtils instance.
     * @return such instance.
     */
    public static EnglishGrammarUtils getInstance()
    {
        return EnglishGrammarUtilsSingletonContainer.SINGLETON;
    }

    /**
     * Manages the regular plural forms.
     * @param word the word.
     * @return the regular plural form.
     * @precondition word != null
     */
    protected String getRegularPluralForm(final String word)
    {
        String result = word.trim().toLowerCase();

        if  (   (result.endsWith("s"))
             || (result.endsWith("x"))
             || (result.endsWith("sh"))
             || (result.endsWith("ch")))
        {
            result += "e";
        }

        result += "s";

        if  (word.trim().toUpperCase().equals(word))
        {
            result = result.toUpperCase();
        }

        return result;
    }

    /**
     * Manages the regular singular forms.
     * @param word the word.
     * @return the regular plural form.
     * @precondition word != null
     */
    protected String getRegularSingularForm(final String word)
    {
        String result = word.trim().toLowerCase();

        if  (result.endsWith("s"))
        {
//            if  (   (result.endsWith("es"))
            if  (   (result.endsWith("xes"))
                 || (result.endsWith("shes"))
                 || (result.endsWith("ches")))
            {
                result = result.substring(0, result.length() - 1);
            }

            result = result.substring(0, result.length() - 1);
        }

        if  (word.trim().toUpperCase().equals(word))
        {
            result = result.toUpperCase();
        }

        return result;
    }
}
