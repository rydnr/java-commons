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
import org.acmsl.commons.regexpplugin.jakartaoro.AwkCompilerOROAdapter;
import org.acmsl.commons.regexpplugin.jakartaoro.HelperOROAdapter;
import org.acmsl.commons.regexpplugin.jakartaoro.Perl5CompilerOROAdapter;
import org.acmsl.commons.regexpplugin.jakartaregexp.CompilerRegexpAdapter;
import org.acmsl.commons.regexpplugin.jdk14regexp.CompilerJDKAdapter;
import org.acmsl.commons.regexpplugin.Helper;
import org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException;
import org.acmsl.commons.regexpplugin.RegexpManager;
import org.acmsl.commons.version.Version;
import org.acmsl.commons.version.VersionFactory;

/**
 * Importing JUnit classes.
 */
import junit.framework.TestCase;

/**
 * Performs some unit tests on RegexpManager class.
 * @testfamily JUnit
 * @testkind testcase
 * @testsetup Default TestCase
 * @testedclass org.acmsl.commons.regexpplugin.RegexpManager
 * @see org.acmsl.commons.regexpplugin.RegexpManager
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class RegexpManagerTest
    extends     TestCase
    implements  org.acmsl.commons.patterns.Test
{
    /**
     * Constructs a test case with the given name.
     * @param name the test case name.
     */
    public RegexpManagerTest(String name)
    {
        super(name);
    }

    /**
     * Tests the RegexpManager.createCompiler() method.
     * @see RegexpManager#createCompiler()
     */
    public void testCreateOroPerlCompiler()
    {
        try
        {
            RegexpManager.useJakartaOroPerl5();

            Compiler t_Compiler = RegexpManager.createCompiler();

            assertTrue(t_Compiler != null);

            assertTrue(t_Compiler instanceof Perl5CompilerOROAdapter);
        }
        catch  (RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            assertTrue("Regexp engine not found.", false);
        }
    }

    /**
     * Tests the RegexpManager.createCompiler() method.
     * @see RegexpManager#createCompiler()
     */
    public void testCreateJakartaRegexpCompiler()
    {
        try
        {
            RegexpManager.useJakartaRegexp();

            Compiler t_Compiler = RegexpManager.createCompiler();

            assertTrue(t_Compiler != null);

            assertTrue(t_Compiler instanceof CompilerRegexpAdapter);
        }
        catch  (RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            assertTrue("Regexp engine not found.", false);
        }
    }

    /**
     * Tests the RegexpManager.createCompiler() method.
     * @see RegexpManager#createCompiler()
     */
    public void testCreateJDK14Compiler()
    {
        try
        {
            RegexpManager.useGNURegexp();

            Compiler t_Compiler = RegexpManager.createCompiler();

            assertTrue(t_Compiler != null);

            assertTrue(t_Compiler instanceof CompilerGNUAdapter);
        }
        catch  (RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            assertTrue("Regexp engine not found.", false);
        }
    }

    /**
     * Tests the RegexpManager.createCompiler() method.
     * @see RegexpManager#createCompiler()
     */
    public void testCreateOroPerlHelper()
    {
        try
        {
            RegexpManager.useJakartaOroPerl5();

            Helper t_Helper = RegexpManager.createHelper();

            assertTrue(t_Helper != null);

            assertTrue(t_Helper instanceof HelperOROAdapter);
        }
        catch  (RegexpEngineNotFoundException regexpEngineNotFoundException)
        {
            assertTrue("Regexp engine not found.", false);
        }
    }

    /**
     * Concrete version object updated everytime it's checked-in in a CVS
     * repository.
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
