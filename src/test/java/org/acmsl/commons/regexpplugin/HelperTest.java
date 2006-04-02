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
 * Description: Performs some unit tests on Helper class.
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
package org.acmsl.commons.regexpplugin;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.regexpplugin.Helper;
import org.acmsl.commons.regexpplugin.gnuregexp.GNURegexpEngine;
import org.acmsl.commons.regexpplugin.gnuregexp.HelperGNUAdapter;
import org.acmsl.commons.regexpplugin.jakartaoro.HelperOROAdapter;
import org.acmsl.commons.regexpplugin.jakartaoro.ORORegexpEngine;
import org.acmsl.commons.regexpplugin.jakartaregexp.HelperRegexpAdapter;
import org.acmsl.commons.regexpplugin.jakartaregexp.JakartaRegexpEngine;
import org.acmsl.commons.regexpplugin.jdk14regexp.HelperJDKAdapter;
import org.acmsl.commons.regexpplugin.jdk14regexp.JDKRegexpEngine;
import org.acmsl.commons.regexpplugin.RegexpEngine;
import org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException;
import org.acmsl.commons.regexpplugin.RegexpManager;

/**
 * Importing JUnit classes.
 */
import junit.framework.TestCase;

/**
 * Performs some unit tests on Helper class.
 * @testfamily JUnit
 * @testkind testcase
 * @testsetup Default TestCase
 * @testedclass org.acmsl.commons.regexpplugin.Helper
 * @see org.acmsl.commons.regexpplugin.RegexpManager
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class HelperTest
    extends     TestCase
    implements  org.acmsl.commons.patterns.Test
{
    /**
     * The input text.
     */
    public static final String INPUT = "This is an untested test.";

    /**
     * The text to replace.
     */
    public static final String TEXT_TO_REPLACE = "n untested";

    /**
     * The replacement text.
     */
    public static final String REPLACEMENT_TEXT = " tested";

    /**
     * The successful replacement result.
     */
    public static final String SUCCESS = "This is a tested test.";

    /**
     * Constructs a test case with the given name.
     * @param name the test case name.
     */
    public HelperTest(String name)
    {
        super(name);
    }

    /**
     * Tests the helper.replaceAll() method.
     * @see Helper#replaceAll(String,String,String)
     */
    public void testDefaultReplaceAll()
    {
        try
        {
            RegexpManager t_RegexpManager = RegexpManager.getInstance();

            assertNotNull(t_RegexpManager);

            RegexpEngine t_RegexpEngine = t_RegexpManager.getEngine();

            assertNotNull(t_RegexpEngine);

            Helper t_Helper = t_RegexpEngine.createHelper();

            assertNotNull(t_Helper);

            String t_strResult =
                t_Helper.replaceAll(INPUT, TEXT_TO_REPLACE, REPLACEMENT_TEXT);

            assertNotNull(t_strResult);

            assertTrue(t_strResult.equals(SUCCESS));
        }
        catch  (final Throwable throwable)
        {
            fail("" + throwable);
        }
    }

    /**
     * Tests the helper.replaceAll() method.
     * @see Helper#replaceAll(String,String,String)
     */
    public void testJakartaOroReplaceAll()
    {
        try
        {
            RegexpEngine t_RegexpEngine = new ORORegexpEngine();

            assertNotNull(t_RegexpEngine);

            Helper t_Helper = t_RegexpEngine.createHelper();

            assertNotNull(t_Helper);

            assertTrue(t_Helper instanceof HelperOROAdapter);

            String t_strResult =
                t_Helper.replaceAll(INPUT, TEXT_TO_REPLACE, REPLACEMENT_TEXT);

            assertNotNull(t_strResult);

            assertTrue(t_strResult.equals(SUCCESS));
        }
        catch  (final Throwable throwable)
        {
            fail("" + throwable);
        }
    }

    /**
     * Tests the helper.replaceAll() method.
     * @see Helper#replaceAll(String,String,String)
     */
    public void testJakartaRegexpReplaceAll()
    {
        try
        {
            RegexpEngine t_RegexpEngine = new JakartaRegexpEngine();

            assertNotNull(t_RegexpEngine);

            Helper t_Helper = t_RegexpEngine.createHelper();

            assertNotNull(t_Helper);

            assertTrue(t_Helper instanceof HelperRegexpAdapter);

            String t_strResult =
                t_Helper.replaceAll(INPUT, TEXT_TO_REPLACE, REPLACEMENT_TEXT);

            assertNotNull(t_strResult);

            assertTrue(t_strResult.equals(SUCCESS));
        }
        catch  (final Throwable throwable)
        {
            fail("" + throwable);
        }
    }

    /**
     * Tests the helper.replaceAll() method.
     * @see Helper#replaceAll(String,String,String)
     */
    public void testJDKReplaceAll()
    {
        try
        {
            RegexpEngine t_RegexpEngine = new JDKRegexpEngine();

            assertNotNull(t_RegexpEngine);

            Helper t_Helper = t_RegexpEngine.createHelper();

            assertNotNull(t_Helper);

            assertTrue(t_Helper instanceof HelperJDKAdapter);

            String t_strResult =
                t_Helper.replaceAll(INPUT, TEXT_TO_REPLACE, REPLACEMENT_TEXT);

            assertNotNull(t_strResult);

            assertTrue(t_strResult.equals(SUCCESS));
        }
        catch  (final Throwable throwable)
        {
            fail("" + throwable);
        }
    }

    /**
     * Tests the helper.replaceAll() method.
     * @see org.acmsl.regexpplugin.Helper
     */
    public void testGNUReplaceAll()
    {
        try
        {
            RegexpEngine t_RegexpEngine = new GNURegexpEngine();

            assertNotNull(t_RegexpEngine);

            Helper t_Helper = t_RegexpEngine.createHelper();

            assertNotNull(t_Helper);

            assertTrue(t_Helper instanceof HelperGNUAdapter);

            String t_strResult =
                t_Helper.replaceAll(INPUT, TEXT_TO_REPLACE, REPLACEMENT_TEXT);

            assertNotNull(t_strResult);

            assertTrue(t_strResult.equals(SUCCESS));
        }
        catch  (final Throwable throwable)
        {
            fail("" + throwable);
        }
    }
}
