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
 * Description: Performs some unit tests on ArrayListChainAdapter class.
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
package unittests.org.acmsl.commons.patterns;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.ArrayListChainAdapter;
import org.acmsl.commons.patterns.CommandHandler;
import org.acmsl.commons.patterns.Command;

/**
 * Importing JUnit classes.
 */
import junit.framework.TestCase;

/**
 * Performs some unit tests on NumericUtils class.
 * @testfamily JUnit
 * @testkind testcase
 * @testsetup Default TestCase
 * @testedclass org.acmsl.commons.patterns.ArrayListChainAdapter
 * @see org.acmsl.commons.patterns.ArrayListChainAdapter
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class ArrayListChainAdapterTest
    extends     TestCase
    implements  org.acmsl.commons.patterns.Test
{
    /**
     * Concrete tested instance.
     */
    private ArrayListChainAdapter m__TestedInstance;
    
    /**
     * Constructs a test case with the given name.
     * @param name the test case name.
     */
    public ArrayListChainAdapterTest(String name)
    {
        super(name);
    }

    /**
     * Sets the tested instance.
     * @param testedInstance the instance to test.
     */
    protected void setTestedInstance(ArrayListChainAdapter testedInstance)
    {
        m__TestedInstance = testedInstance;
    }

    /**
     * Retrieves the tested instance.
     * @return the tested instance.
     */
    public ArrayListChainAdapter getTestedInstance()
    {
        return m__TestedInstance;
    }

    /**
     * Sets up the fixture, for example, open a network connection.
     * This method is called before a test is executed.
     */
    protected void setUp()
    {
        setTestedInstance(new ArrayListChainAdapter());
    }

    /**
     * Executes the tests.
     */
    protected void runTest()
    {
        testAdd();
    }

    /**
     * Tests the ArrayListChainAdapter.add(CommandHandler) method.
     * @see org.acmsl.patterns.ArrayListChainAdapter#add(CommandHandler)
     */
    public void testAdd()
    {
        CommandHandler t_CommandHandler = new DummyCommandHandler();

        ArrayListChainAdapter t_TestedInstance = getTestedInstance();

        assertTrue(t_TestedInstance != null);

        t_TestedInstance.add(t_CommandHandler);

        assertTrue(t_TestedInstance.contains(t_CommandHandler));
    }

    /**
     * Dummy command handler implementation.
     * @author <a href="mailto:jsanleandro@yahoo.es"
               >Jose San Leandro Armendáriz</a>
     * @version $Revision$
     */
    public class DummyCommandHandler
        implements  CommandHandler
    {
        /**
         * Asks the handler to process the command. The idea is that each
         * command handler decides if such command is suitable of being
         * processed, and if so perform the concrete actions the command
         * represents.
         * @param command the command to process (or not).
         * @return <code>true</code> if the handler actually process the
         * command, or maybe because it's not desirable to continue the chain.
         */
        public boolean handle(final Command command)
        {
            return true;
        }
    }
}
