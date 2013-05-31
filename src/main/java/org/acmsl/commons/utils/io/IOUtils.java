//;-*- mode: java -*-
/*
                        ACM-SL Commons

    Copyright (C) 2002-today  Jose San Leandro Armendariz
                              chous@acm-sl.org

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
    Contact info: jose.sanleandro@acm-sl.com

 ******************************************************************************
 *
 * Filename: IOUtils.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Provides some commonly used static methods related to
 *              java.io classes.
 *
 */
package org.acmsl.commons.utils.io;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.utils.CharUtils;
import org.acmsl.commons.patterns.Singleton;
import org.acmsl.commons.patterns.Utils;

/*
 * Importing some JDK classes.
 */
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/*
 * Importing Commons-Logging classes.
 */
import org.apache.commons.logging.LogFactory;

/*
 * Importing JetBrains annotations.
 */
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Provides some commonly used static methods related to java.io classes.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
@SuppressWarnings("unused")
public class IOUtils
    implements  Utils,
                Singleton
{
    /**
     * Block size used when reading the input stream.
     */
    private static final int BLOCK_SIZE = 1024;

    /**
     * Singleton implemented to avoid the double-checked locking.
     */
    private static class IOUtilsSingletonContainer
    {
        /**
         * The actual singleton.
         */
        @NotNull
        public static final IOUtils SINGLETON = new IOUtils();
    }

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected IOUtils() {}

    /**
     * Retrieves an IOUtils instance.
     * @return such instance.
     */
    @NotNull
    public static IOUtils getInstance()
    {
        return IOUtilsSingletonContainer.SINGLETON;
    }

    /**
     * Reads given input stream into a String.
     * @param inputStream the input stream to read.
     * @param contentLength the length of the content.
     * @return the input stream contents, or an empty string if the operation
     * fails.
     * @exception IOException if the input stream cannot be read.
     */
    @NotNull
    public String read(@NotNull final InputStream inputStream, final int contentLength)
        throws IOException
    {
        @NotNull final StringBuilder t_sbResult = new StringBuilder();

        @NotNull final CharUtils t_CharUtils = CharUtils.getInstance();

        if  (contentLength > 0)
        {
            @NotNull final InputStreamReader t_isrReader =
                new InputStreamReader(inputStream);

            @NotNull final char[] t_acContents = new char[contentLength];

            if  (t_isrReader.ready())
            {
                t_isrReader.read(t_acContents);
            }

            t_sbResult.append(t_acContents);
        }
        else
        {
            @NotNull final InputStreamReader t_isrReader =
                new InputStreamReader(inputStream);

            @NotNull final char[] t_acContents = new char[BLOCK_SIZE];

            while  (t_isrReader.ready())
            {
                int t_iCharsRead = t_isrReader.read(t_acContents);

                char[] t_acCharsRead =
                    (t_iCharsRead == BLOCK_SIZE)
                    ?   t_acContents
                    :   t_CharUtils.subbuffer(
                        t_acContents, 0, t_iCharsRead);

                t_sbResult.append(t_acCharsRead);
            }
        }

        return t_sbResult.toString();
    }

    /**
     * Reads an input stream and returns its contents.
     * @param input the input stream to be read.
     * @return the contents of the stream.
     * @throws IOException whenever the operation cannot be accomplished.
     */
    @NotNull
    public String read(@NotNull final InputStream input)
        throws  IOException
    {
        @NotNull final StringBuilder t_sbResult = new StringBuilder();

        /*
         * Instantiating an InputStreamReader object to read the contents.
         */
        @NotNull final InputStreamReader t_isrReader = new InputStreamReader(input);

        /*
         * It's faster to use BufferedReader class.
         */
        @NotNull final BufferedReader t_brBufferedReader =
            new BufferedReader(t_isrReader);

        @Nullable String t_strLine = t_brBufferedReader.readLine();

        while  (t_strLine != null)
        {
            t_sbResult.append(t_strLine + "\n");

            t_strLine = t_brBufferedReader.readLine();
        }

        return t_sbResult.toString();
    }

    /**
     * Reads the contents of an input stream and returns its contents, if
     * possible. If some exception occurs, returns an empty String.
     * @param input the input stream to be read.
     * @return the contents of the stream, or empty if reading cannot be
               accomplished.
     */
    @NotNull
    @SuppressWarnings("unused")
    public String readIfPossible(@NotNull final InputStream input)
    {
        String result = "";

        try
        {
            result = read(input);
        }
        catch  (final IOException ioException)
        {
            LogFactory.getLog(IOUtils.class).error(
                "Cannot read file", ioException);
        }

        return result;
    }

    /**
     * Writes given information to the stream.
     * @param content the content to write.
     */
    public void write(@NotNull final String content, @NotNull final OutputStream output)
    {
        @NotNull final PrintStream t_Printer = new PrintStream(output);
        t_Printer.print(content);
        t_Printer.flush();
    }
}
