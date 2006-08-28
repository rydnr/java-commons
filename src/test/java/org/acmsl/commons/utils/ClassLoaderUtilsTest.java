/*
                        ACM-SL Commons

    Copyright (C) 2002-2005  Jose San Leandro Armendáriz
                             chous@acm-sl.org

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
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA


    Thanks to ACM S.L. for distributing this library under the GPL license.
    Contact info: jose.sanleandro@acm-sl.com
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
 * Description: Performs some unit tests on ClassLoaderUtils class.
 *
 * File version: $Revision: 548 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: StringUtilsTest.java 548 2006-04-02 10:15:23Z chous $
 *
 */
package org.acmsl.commons.utils;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.utils.ClassLoaderUtils;

/*
 * Importing some JDK classes.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/*
 * Importing JUnit classes.
 */
import junit.framework.TestCase;

/**
 * Performs some unit tests on StringUtils class.
 * @author <a href="mailto:chous@acm-sl.org"
 *         >Jose San Leandro Armendáriz</a>
 * @version $Revision: 548 $
 * @testfamily JUnit
 * @testkind testcase
 * @testsetup Default TestCase
 * @testedclass org.acmsl.commons.utils.ClassLoaderUtils
 * @see org.acmsl.commons.utils.ClassLoaderUtils
 */
public class ClassLoaderUtilsTest
    extends     TestCase
    implements  org.acmsl.commons.patterns.Test
{
    /**
     * Constructs a test case with the given name.
     * @param name the test case name.
     */
    public ClassLoaderUtilsTest(final String name)
    {
        super(name);
    }

    /**
     * Tests the <code>ClassLoaderUtils.findLocation(Class)</code> method.
     * @see org.acmsl.commons.utils.ClassLoaderUtils#findLocation(Class)
     */
    public void testFindLocation()
    {
        ClassLoaderUtils classLoaderUtils = ClassLoaderUtils.getInstance();
        
        assertNotNull(classLoaderUtils);

        String location =
            classLoaderUtils.findLocation(String.class);

        assertNotNull(location);

        assertTrue(location.endsWith("rt.jar"));
    }

    /**
     * Tests the <code>ClassLoaderUtils.pathContainsClass(String,String)</code>
     * method.
     * @see org.acmsl.commons.utils.ClassLoaderUtils#pathContainsClass(String,String)
     */
    public void testPathContainsClass()
    {
        ClassLoaderUtils classLoaderUtils = ClassLoaderUtils.getInstance();
        
        assertNotNull(classLoaderUtils);

        try
        {
            File tempFile =
                File.createTempFile(
                    "acmslcommons", "classloaderutilstest.zip");

            OutputStream fileOutputStream = new FileOutputStream(tempFile);

            ZipOutputStream zipOutputStream =
                new ZipOutputStream(fileOutputStream);

            ZipEntry zipEntry = new ZipEntry("test/package/my.class");

            zipOutputStream.putNextEntry(zipEntry);

            zipOutputStream.close();

            fileOutputStream.close();

            assertTrue(
                classLoaderUtils.pathContainsClass(
                    tempFile.getAbsolutePath(), "test.package.my"));

            tempFile.delete();
        }
        catch  (final Throwable throwable)
        {
            fail(throwable.getMessage());
        }
    }
}
