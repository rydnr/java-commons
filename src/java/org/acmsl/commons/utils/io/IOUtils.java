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
 * Description: Provides some commonly used static methods related to
 *              java.io classes.
 *
 * File version: $Revision$
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id$
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
import java.lang.ref.WeakReference;
import java.lang.StringBuffer;

/*
 * Importing Commons-Logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Provides some commonly used static methods related to java.io classes.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public abstract class IOUtils
    implements  Utils,
                Singleton
{
    /**
     * Block size used when reading the input stream.
     */
    private static final int BLOCK_SIZE = 1024;

    /**
     * Singleton implemented as a weak reference.
     */
    private static WeakReference singleton;

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected IOUtils() {};

    /**
     * Specifies a new weak reference.
     * @param utils the utils instance to use.
     * @precondition utils != null
     */
    protected static void setReference(final IOUtils utils)
    {
        singleton = new WeakReference(utils);
    }

    /**
     * Retrieves the weak reference.
     * @return such reference.
     */
    protected static WeakReference getReference()
    {
        return singleton;
    }

    /**
     * Retrieves an IOUtils instance.
     * @return such instance.
     */
    public static IOUtils getInstance()
    {
        IOUtils result = null;

        WeakReference reference = getReference();

        if  (reference != null) 
        {
            result = (IOUtils) reference.get();
        }

        if  (result == null) 
        {
            result = new IOUtils() {};

            setReference(result);
        }

        return result;
    }

    /**
     * Reads given input stream into a String.
     * @param inputStream the input stream to read.
     * @param contentLength the length of the content.
     * @return the input stream contents, or an empty string if the operation
     * fails.
     * @exception IOException if the input stream cannot be read.
     */
    public String read(final InputStream inputStream, final int contentLength)
        throws IOException
    {
        StringBuffer t_sbResult = new StringBuffer();

        CharUtils t_CharUtils = CharUtils.getInstance();

        if  (t_CharUtils != null)
        {
            if  (inputStream != null)
            {
                if  (contentLength > 0)
                {
                    InputStreamReader t_isrReader =
                        new InputStreamReader(inputStream);

                    char[] t_acContents = new char[contentLength];

                    if  (t_isrReader.ready())
                    {
                        t_isrReader.read(t_acContents);
                    }

                    t_sbResult.append(t_acContents);
                }
                else
                {
                    InputStreamReader t_isrReader =
                        new InputStreamReader(inputStream);

                    char[] t_acContents = new char[BLOCK_SIZE];

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
            }
        }
        else 
        {
            LogFactory.getLog(getClass()).error(
                "Cannot retrieve CharUtils instance");
        }

        return t_sbResult.toString();
    }

    /**
     * Reads an input stream and returns its contents.
     * @param input the input stream to be read.
     * @return the contents of the stream.
     * @throws IOException whenever the operation cannot be accomplished.
     * @precondition input != null
     */
    public String read(final InputStream input)
        throws  IOException
    {
        StringBuffer t_sbResult = new StringBuffer();

        /*
         * Instantiating an InputStreamReader object to read the contents.
         */
        InputStreamReader t_isrReader = new InputStreamReader(input);

        /*
         * It's faster to use BufferedReader class.
         */
        BufferedReader t_brBufferedReader =
            new BufferedReader(t_isrReader);

        String t_strLine = t_brBufferedReader.readLine();

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
    public String readIfPossible(final InputStream input)
    {
        String result = "";

        try
        {
            result = read(input);
        }
        catch  (final IOException ioException)
        {
            LogFactory.getLog(getClass()).error(
                "Cannot read file",
                ioException);
        }

        return result;
    }
}
