/*
                        ACM S.L. Commons

    Copyright (C) 2002-today  Jose San Leandro Armendariz
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

 ******************************************************************************
 *
 * Filename: FileUtilsTest.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Performs some tests of FileUtils class.
 *
 * Date: 2014/03/03
 * Time: 08:41
 *
 */
package org.acmsl.commons.utils.io;

/*
 * Importing JetBrains annotations.
 */
import org.jetbrains.annotations.NotNull;

/*
 * Importing JUnit classes.
 */
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/*
 * Importing JDK classes.
 */
import java.io.File;
import java.io.FileInputStream;

/**
 * Performs some tests of {@link FileUtils} class.
 * @author <a href="mailto:queryj@acm-sl.org">Jose San Leandro</a>
 * @since 3.0
 * Created: 2014/03/03 08:41
 */
@RunWith(JUnit4.class)
public class FileUtilsTest
{
    /**
     * Checks whether the retrieveFile() method works.
     * @throws Exception
     */
    @Test
    public void retrieve_the_File_from_a_FileInputStream_works()
        throws Exception
    {
        @NotNull final FileUtils instance = FileUtils.getInstance();

        @NotNull final File t_File = File.createTempFile("test", "");

        @NotNull final FileInputStream t_InputStream = new FileInputStream(t_File);

        Assert.assertEquals(t_File, instance.retrieveFile(t_InputStream));

        t_InputStream.close();

        t_File.delete();

    }
}
