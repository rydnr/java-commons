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
 * Description: Performs some unit tests on RegexpManager class.
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
package unittests.org.acmsl.commons.regexpplugin;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.regexpplugin.Compiler;
import org.acmsl.commons.regexpplugin.gnuregexp.CompilerGNUAdapter;
import org.acmsl.commons.regexpplugin.gnuregexp.GNURegexpEngine;
import org.acmsl.commons.regexpplugin.gnuregexp.PatternGNUAdapter;
import org.acmsl.commons.regexpplugin.jakartaoro.ORORegexpEngine;
import org.acmsl.commons.regexpplugin.jakartaoro.PatternOROAdapter;
import org.acmsl.commons.regexpplugin.jakartaoro.Perl5CompilerOROAdapter;
import org.acmsl.commons.regexpplugin.jakartaregexp.CompilerRegexpAdapter;
import org.acmsl.commons.regexpplugin.jakartaregexp.JakartaRegexpEngine;
import org.acmsl.commons.regexpplugin.jakartaregexp.PatternRegexpAdapter;
import org.acmsl.commons.regexpplugin.jdk14regexp.CompilerJDKAdapter;
import org.acmsl.commons.regexpplugin.jdk14regexp.JDKRegexpEngine;
import org.acmsl.commons.regexpplugin.jdk14regexp.PatternJDKAdapter;
import org.acmsl.commons.regexpplugin.Pattern;
import org.acmsl.commons.regexpplugin.RegexpEngine;
import org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException;
import org.acmsl.commons.regexpplugin.RegexpManager;

/**
 * Importing JUnit classes.
 */
import junit.framework.TestCase;

/**
 * Performs some unit tests on Compiler class.
 * @testfamily JUnit
 * @testkind testcase
 * @testsetup Default TestCase
 * @testedclass org.acmsl.commons.regexpplugin.Compiler
 * @see org.acmsl.commons.regexpplugin.RegexpManager
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class CompilerTest
    extends     TestCase
    implements  org.acmsl.commons.patterns.Test
{
    /**
     * The test pattern.
     */
    public static final String REGEXP = "A*b";

    /**
     * Constructs a test case with the given name.
     * @param name the test case name.
     */
    public CompilerTest(String name)
    {
        super(name);
    }

    /**
     * Tests the compiler.compile() method.
     * @see org.acmsl.commons.regexpplugin.Compiler#compile(String)
     */
    public void testDefaultCompile()
    {
        try
        {
            RegexpManager t_RegexpManager = RegexpManager.getInstance();

            assertNotNull(t_RegexpManager);

            RegexpEngine t_RegexpEngine = t_RegexpManager.getEngine();

            assertNotNull(t_RegexpEngine);

            Compiler t_Compiler = t_RegexpEngine.createCompiler();

            assertNotNull(t_Compiler);

            Pattern t_Pattern = t_Compiler.compile(REGEXP);

            assertNotNull(t_Pattern);
        }
        catch  (final Throwable throwable)
        {
            fail("" + throwable);
        }
    }

    /**
     * Tests the compiler.compile() method.
     * @see org.acmsl.commons.regexpplugin.Compiler#compile(String)
     */
    public void testJakartaOroCompile()
    {
        try
        {
            RegexpEngine t_RegexpEngine = new ORORegexpEngine();

            assertNotNull(t_RegexpEngine);

            Compiler t_Compiler = t_RegexpEngine.createCompiler();

            assertNotNull(t_Compiler);

            assertTrue(t_Compiler instanceof Perl5CompilerOROAdapter);

            Pattern t_Pattern = t_Compiler.compile(REGEXP);

            assertNotNull(t_Pattern);

            assertTrue(t_Pattern instanceof PatternOROAdapter);
        }
        catch  (final Throwable throwable)
        {
            fail("" + throwable);
        }
    }

    /**
     * Tests the compiler.compile() method.
     * @see org.acmsl.commons.regexpplugin.Compiler#compile(String)
     */
    public void testJakartaRegexpCompile()
    {
        try
        {
            RegexpEngine t_RegexpEngine = new JakartaRegexpEngine();

            assertNotNull(t_RegexpEngine);

            Compiler t_Compiler = t_RegexpEngine.createCompiler();

            assertNotNull(t_Compiler);

            assertTrue(t_Compiler instanceof CompilerRegexpAdapter);

            Pattern t_Pattern = t_Compiler.compile(REGEXP);

            assertNotNull(t_Pattern);

            assertTrue(t_Pattern instanceof PatternRegexpAdapter);
        }
        catch  (final Throwable throwable)
        {
            fail("" + throwable);
        }
    }

    /**
     * Tests the compiler.compile() method.
     * @see org.acmsl.commons.regexpplugin.Compiler#compile(String)
     */
    public void testJDKCompile()
    {
        try
        {
            RegexpEngine t_RegexpEngine = new JDKRegexpEngine();

            assertNotNull(t_RegexpEngine);

            Compiler t_Compiler = t_RegexpEngine.createCompiler();

            assertNotNull(t_Compiler);

            assertTrue(t_Compiler instanceof CompilerJDKAdapter);

            Pattern t_Pattern = t_Compiler.compile(REGEXP);

            assertNotNull(t_Pattern);

            assertTrue(t_Pattern instanceof PatternJDKAdapter);
        }
        catch  (final Throwable throwable)
        {
            fail("" + throwable);
        }
    }

    /**
     * Tests the compiler.compile() method.
     * @see org.acmsl.commons.regexpplugin.Compiler#compile(String)
     */
    public void testGNUCompile()
    {
        try
        {
            RegexpEngine t_RegexpEngine = new GNURegexpEngine();

            assertNotNull(t_RegexpEngine);

            Compiler t_Compiler = t_RegexpEngine.createCompiler();

            assertNotNull(t_Compiler);

            assertTrue(t_Compiler instanceof CompilerGNUAdapter);

            Pattern t_Pattern = t_Compiler.compile(REGEXP);

            assertNotNull(t_Pattern);

            assertTrue(t_Pattern instanceof PatternGNUAdapter);
        }
        catch  (final Throwable throwable)
        {
            fail("" + throwable);
        }
    }
}
