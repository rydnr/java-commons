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
 * Filename: FileUtils.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Provides some useful methods when working with files.
 *
 */
package org.acmsl.commons.utils.io;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Singleton;
import org.acmsl.commons.patterns.Utils;
import org.acmsl.commons.regexpplugin.Helper;
import org.acmsl.commons.regexpplugin.MalformedPatternException;
import org.acmsl.commons.regexpplugin.RegexpEngine;
import org.acmsl.commons.regexpplugin.RegexpEngineNotFoundException;
import org.acmsl.commons.regexpplugin.RegexpManager;
import org.acmsl.commons.regexpplugin.RegexpPluginMisconfiguredException;

/*
 * Importing some JDK1.3 classes.
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;

/*
 * Importing Apache Commons-Logging classes.
 */
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Provides some useful methods when working with files.
 * @author <a href="mailto:chous@acm-sl.org"
           >Jose San Leandro Armendariz</a>
 */
public class FileUtils
    implements  Utils,
                Singleton
{
    /**
     * Singleton implemented to avoid the double-checked locking.
     */
    private static class FileUtilsSingletonContainer
    {
        /**
         * The actual singleton.
         */
        @NotNull
        public static final FileUtils SINGLETON = new FileUtils();
    }

    /**
     * Retrieves a FileUtils instance.
     * @return such instance.
     */
    @NotNull
    public static FileUtils getInstance()
    {
        return FileUtilsSingletonContainer.SINGLETON;
    }

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected FileUtils() {}

    /**
     * Reads a file pointed by given path, and returns its contents.
     * @param filePath the path to the file to be read.
     * @return the contents of the file.
     * @throws FileNotFoundException if the file is not found.
     * @throws SecurityException if the operation is forbidden because of
     * security manager settings.
     * @throws IOException if some I/O exception occurs.
     */
    @NotNull
    public String readFile(@NotNull final String filePath)
        throws  SecurityException,
                IOException
    {
        return readFile(new File(filePath));
    }

    /**
     * Reads a file and returns its contents.
     * @param file the file to be read.
     * @return the contents of the file.
     * @throws FileNotFoundException if the file is not found.
     * @throws SecurityException if the operation is forbidden because of
     * security manager settings.
     * @throws IOException if some I/O exception occurs.
     */
    @NotNull
    public char[] readFileContents(@NotNull final File file)
        throws  SecurityException,
                IOException
    {
        char[] result;

        /*
         * Instantiate a FileReader object to read file's contents.
         */
        @NotNull final FileReader t_frPageReader = new FileReader(file);

        /*
         * To read file's contents it's better to use BufferedReader class.
         */
        @NotNull final BufferedReader t_frPageBufferedReader =
            new BufferedReader(t_frPageReader);

        if  (file.length() > Integer.MAX_VALUE)
        {
            throw
                new IOException(
                    "File too large (" + file.length() + " bytes)");
        }

        /*
         * Next, I find out the necessary size of the array where file's
         * contents will be copied into.
         */
        result = new char[(int) file.length()];

        /*
         * Now I actually read the file, and fill the array.
         */
        t_frPageBufferedReader.read(
            result,
            0,
            result.length);

        return result;
    }

    /**
     * Reads a file and returns its contents.
     * @param file the file to be read.
     * @return the contents of the file.
     * @throws FileNotFoundException if the file is not found.
     * @throws SecurityException if the operation is forbidden because of
     * security manager settings.
     * @throws IOException if some I/O exception occurs.
     */
    @NotNull
    public String readFile(@NotNull final File file)
        throws  SecurityException,
                IOException
    {
        /*
         * we can use a String constructor that exactly
         * fits our needs.
         */
        return new String(readFileContents(file));
    }

    /**
     * Reads a file by its path and returns its contents, if possible. If some
     * exception occurs, it's ignored, and returns an empty String. This method
     * is used to avoid declaring try/catch blocks in client code.
     * @param filePath the path to the file to be read.
     * @return the contents of the file, or empty if reading cannot be
     * accomplished.
     */
    @SuppressWarnings("unused")
    @NotNull
    public String readFileIfPossible(@NotNull final String filePath)
    {
        String result = null;

        try
        {
            result = readFile(filePath);
        }
        catch  (final FileNotFoundException fileNotFoundException)
        {
            /*
            * We have chosen not to notify of exceptions, so this
            * block of code is only descriptive.
            */
            LogFactory.getLog(FileUtils.class).trace(
                "Cannot read file " + filePath,
                fileNotFoundException);
        }
        catch  (final SecurityException securityException)
        {
            /*
            * We have chosen not to notify of exceptions, so this
            * block of code is only descriptive.
            */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot read file " + filePath,
                securityException);
        }
        catch  (final IOException ioException)
        {
            /*
            * We have chosen not to notify of exceptions, so this
            * block of code is only descriptive.
            */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot read file " + filePath,
                ioException);
        }

        if (result == null)
        {
            result = "";
        }

        return result;
    }

    /**
     * Reads a file and returns its contents, if possible. If some exception
     * occurs, it's ignored, and returns an empty String. This method is used
     * to avoid declaring try/catch blocks in client code.
     * @param file the file to be read.
     * @return the contents of the file, or empty if reading cannot be
     * accomplished.
     */
    @SuppressWarnings("unused")
    @NotNull
    public String readFileIfPossible(@NotNull final File file)
    {
        @NotNull String result = "";

        try
        {
            result = readFile(file);
        }
        catch  (final FileNotFoundException fileNotFoundException)
        {
            /*
             * We have chosen not to notify of exceptions, so this
             * block of code is only descriptive.
             */
            LogFactory.getLog(FileUtils.class).trace(
                "Cannot read file " + file,
                fileNotFoundException);
        }
        catch  (final SecurityException securityException)
        {
            /*
             * We have chosen not to notify of exceptions, so this
             * block of code is only descriptive.
             */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot read file " + file,
                securityException);
        }
        catch  (final IOException ioException)
        {
            /*
             * We have chosen not to notify of exceptions, so this
             * block of code is only descriptive.
             */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot read file " + file,
                ioException);
        }

        return result;
    }

    /**
     * Tries to read from given stream.
     * @param inputStream the stream to read from.
     * @return the contents read.
     * @throws IOException if the input stream could not be read or closed.
     */
    @NotNull
    public String read(@NotNull final InputStream inputStream)
        throws  IOException
    {
        @NotNull final BufferedInputStream bufferedInputStream;

        if  (inputStream instanceof BufferedInputStream)
        {
            bufferedInputStream = (BufferedInputStream) inputStream;
        }
        else
        {
            bufferedInputStream = new BufferedInputStream(inputStream);
        }

        return read(bufferedInputStream);
    }

    /**
     * Tries to read from given stream.
     * @param bufferedInputStream the stream to read from.
     * @return the contents read.
     * @throws IOException if the input stream could not be read or closed.
     */
    @NotNull
    public String read(@NotNull final BufferedInputStream bufferedInputStream)
        throws  IOException
    {
        @NotNull final StringWriter t_swResult = new StringWriter();

        @NotNull final PrintWriter t_Writer = new PrintWriter(t_swResult);

        int t_iReadChar = bufferedInputStream.read();

        while  (t_iReadChar != -1)
        {
            t_Writer.print((char) t_iReadChar);

            t_iReadChar = bufferedInputStream.read();
        }

        bufferedInputStream.close();

        t_Writer.close();

        return t_swResult.toString();
    }

    /**
     * Saves the contents to a file.
     * @param filePath the path of the file.
     * @param contents the text to save.
     * @return <code>true</code> if the process is successfully accomplished.
     * @deprecated Specify the charset instead.
     */
    @Deprecated
    @SuppressWarnings("unused")
    public boolean writeFileIfPossible(
        @NotNull final String filePath, @NotNull final String contents)
    {
        return writeFileIfPossible(new File(filePath), contents);
    }

    /**
     * Saves the contents to a file.
     * @param filePath the path of the file.
     * @param contents the text to save.
     * @param charset the file charset to use.
     * @return <code>true</code> if the process is successfully accomplished.
     */
    @SuppressWarnings("unused")
    public boolean writeFileIfPossible(
        @NotNull final String filePath, @NotNull final String contents, @NotNull final Charset charset)
    {
        return writeFileIfPossible(new File(filePath), contents, charset);
    }

    /**
     * Saves the contents to a file.
     * @param file the file to be overwritten.
     * @param contents the text to save.
     * @return <code>true</code> if the process is successfully accomplished.
     * @deprecated Specify the charset instead.
     */
    @Deprecated
    public boolean writeFileIfPossible(@NotNull final File file, @NotNull final String contents)
    {
        boolean result = false;

        try
        {
            writeFile(file, contents);
        }
        catch  (final FileNotFoundException fileNotFoundException)
        {
            /*
             * We have chosen not to notify of exceptions, so this
             * block of code is only descriptive.
             */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot write file " + file,
                fileNotFoundException);
        }
        catch  (final SecurityException securityException)
        {
            /*
             * We have chosen not to notify of exceptions, so this
             * block of code is only descriptive.
             */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot write file " + file,
                securityException);
        }
        catch  (final IOException ioException)
        {
            /*
             * We have chosen not to notify of exceptions, so this
             * block of code is only descriptive.
             */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot write file " + file,
                ioException);
        }

        return result;
    }

    /**
     * Saves the contents to a file.
     * @param file the file to be overwritten.
     * @param contents the text to save.
     * @param charset the file charset to use.
     * @return <code>true</code> if the process is successfully accomplished.
     */
    public boolean writeFileIfPossible(
        @NotNull final File file, @NotNull final String contents, @NotNull final Charset charset)
    {
        boolean result = false;

        try
        {
            writeFile(file, contents, charset);
        }
        catch  (final FileNotFoundException fileNotFoundException)
        {
            /*
             * We have chosen not to notify of exceptions, so this
             * block of code is only descriptive.
             */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot write file " + file,
                fileNotFoundException);
        }
        catch  (final SecurityException securityException)
        {
            /*
             * We have chosen not to notify of exceptions, so this
             * block of code is only descriptive.
             */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot write file " + file,
                securityException);
        }
        catch  (final IOException ioException)
        {
            /*
             * We have chosen not to notify of exceptions, so this
             * block of code is only descriptive.
             */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot write file " + file,
                ioException);
        }

        return result;
    }

    /**
     * Writes a file referred by given path, with given contents.
     * @param filePath the path of the file.
     * @param contents the text to write.
     * @throws FileNotFoundException if the file is not found.
     * @throws SecurityException if the security manager forbids this
     * operation.
     * @throws IOException if any other I/O error occurs.
     * @deprecated Specify the charset instead.
     */
    @Deprecated
    @SuppressWarnings("unused")
    public void writeFile(@NotNull final String filePath, @NotNull final String contents)
        throws  SecurityException,
                IOException
    {
        writeFile(new File(filePath), contents);
    }

    /**
     * Writes a file referred by given path, with given contents.
     * @param filePath the path of the file.
     * @param contents the text to write.
     * @param charset the file charset to use.
     * @throws FileNotFoundException if the file is not found.
     * @throws SecurityException if the security manager forbids this
     * operation.
     * @throws IOException if any other I/O error occurs.
     */
    @SuppressWarnings("unused")
    public void writeFile(
        @NotNull final String filePath, @NotNull final String contents, @NotNull final Charset charset)
        throws  SecurityException,
                IOException
    {
        writeFile(new File(filePath), contents, charset);
    }

    /**
     * Writes a file with given contents.
     * @param file the file to write.
     * @param contents the text to write.
     * @throws FileNotFoundException if the file is not found.
     * @throws SecurityException if the security manager forbids this
     * operation.
     * @throws IOException if any other I/O error occurs.
     * @deprecated Specify the charset instead.
     */
    public void writeFile(@NotNull final File file, @NotNull final String contents)
        throws  SecurityException,
                IOException
    {
        @NotNull final FileWriter t_fwWriter = new FileWriter(file);

        @NotNull final PrintWriter t_pwWriter = new PrintWriter(t_fwWriter);

        t_pwWriter.println(contents);

        t_pwWriter.close();

        t_fwWriter.close();
    }

    /**
     * Writes a file with given contents.
     * @param file the file to write.
     * @param contents the text to write.
     * @param charset the charset to use.
     * @throws SecurityException if the security manager forbids this
     * operation.
     * @throws IOException if any other I/O error occurs.
     */
    public void writeFile(
        @NotNull final File file, @NotNull final String contents, @NotNull final Charset charset)
        throws  SecurityException,
                IOException
    {
        @NotNull final FileOutputStream t_fosFileStream = new FileOutputStream(file);

        @NotNull final OutputStreamWriter t_osFileWriter = new OutputStreamWriter(t_fosFileStream, charset);

        @NotNull final PrintWriter t_pwWriter = new PrintWriter(t_osFileWriter);

        t_pwWriter.println(contents);

        t_pwWriter.close();

        t_osFileWriter.close();

        t_fosFileStream.close();
    }

    /**
     * Copies one file from its current path to another.
     * @param filePath file's path.
     * @param destinationPath the new path of the file.
     * @throws SecurityException if the security manager forbids this
     * operation.
     * @throws IOException if any other I/O error occurs.
     */
    public void copy(@NotNull final String filePath, @NotNull final String destinationPath)
        throws  SecurityException,
                IOException
    {
        copy(new File(filePath), new File(destinationPath));
    }

    /**
     * Copies the contents of a file to another.
     * @param original the content to copy.
     * @param destination the file to be overwritten.
     * @throws SecurityException if the security manager forbids this
     * operation.
     * @throws IOException if any other I/O error occurs.
     */
    public void copy(@NotNull final File original, @NotNull final File destination)
        throws  SecurityException,
                IOException
    {
        @NotNull final FileWriter t_FileWriter = new FileWriter(destination);

        t_FileWriter.write(readFileContents(original));

        t_FileWriter.close();
    }

    /**
     * Copies the contents of a file (referred by given path) to another.
     * @param originalPath the path of the file to copy.
     * @param destinationPath the path of the file to be overwritten.
     * @return <code>true</code> if the operation ends up successfully.
     */
    @SuppressWarnings("unused")
    public boolean copyIfPossible(
        @NotNull final String originalPath, @NotNull final String destinationPath)
    {
        return
            copyIfPossible(new File(originalPath), new File(destinationPath));
    }

    /**
     * Copies the contents of a file to another.
     * @param original the content to copy.
     * @param destination the file to be overwritten.
     * @return <code>true</code> if the operation ends up successfully.
     */
    public boolean copyIfPossible(@NotNull final File original, @NotNull final File destination)
    {
        boolean result = false;

        try
        {
            copy(original, destination);

            result = true;
        }
        catch  (final FileNotFoundException fileNotFoundException)
        {
            /*
             * We have chosen not to notify of exceptions, so this
             * block of code is only descriptive.
             */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot copy file " + original + " to " + destination,
                fileNotFoundException);
        }
        catch  (final SecurityException securityException)
        {
            /*
             * We have chosen not to notify of exceptions, so this
             * block of code is only descriptive.
             */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot copy file " + original + " to " + destination,
                securityException);
        }
        catch  (final IOException ioException)
        {
            /*
             * We have chosen not to notify of exceptions, so this
             * block of code is only descriptive.
             */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot copy file " + original + " to " + destination,
                ioException);
        }

        return result;
    }

    /**
     * Moves a file.
     * @param originalFile the file to move.
     * @param destinationFile the new file.
     * @throws FileNotFoundException if the file is not found.
     * @throws SecurityException if the security manager forbids this
     * operation.
     * @throws IOException if any other I/O error occurs.
     */
    public void move(@NotNull final File originalFile, @NotNull final File destinationFile)
        throws  SecurityException,
                IOException
    {
        copy(originalFile, destinationFile);

        originalFile.delete();
    }

    /**
     * Moves a file from one path to another, if possible.
     * @param filePath the path of the file to move.
     * @param destinationPath the new file's path.
     * @throws FileNotFoundException if the file is not found.
     * @throws SecurityException if the security manager forbids this 
     + operation.
     * @throws IOException if any other I/O error occurs.
     */
    @SuppressWarnings("unused")
    public void move(@NotNull final String filePath, @NotNull final String destinationPath)
        throws  SecurityException,
                IOException
    {
        move(new File(filePath), new File(destinationPath));
    }

    /**
     * Moves a file, if possible.
     * @param originalFile the file to move.
     * @param destinationFile the new file.
     * @return <code>true</code> if the operation ends up successfully.
     */
    public boolean moveIfPossible(
        @NotNull final File originalFile, @NotNull final File destinationFile)
    {
        boolean result = false;

        try
        {
            move(originalFile, destinationFile);
            result = true;
        }
        catch  (final FileNotFoundException fileNotFoundException)
        {
            /*
             * We have chosen not to notify of exceptions, so this
             * block of code is only descriptive.
             */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot move file " + originalFile + " to " + destinationFile,
                fileNotFoundException);
        }
        catch  (final SecurityException securityException)
        {
            /*
             * We have chosen not to notify of exceptions, so this
             * block of code is only descriptive.
             */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot move file " + originalFile + " to " + destinationFile,
                securityException);
        }
        catch  (final IOException ioException)
        {
            /*
             * We have chosen not to notify of exceptions, so this
             * block of code is only descriptive.
             */
            LogFactory.getLog(FileUtils.class).info(
                "Cannot move file " + originalFile + " to " + destinationFile,
                ioException);
        }

        return result;
    }

    /**
     * Moves a file from one path to another, if possible.
     * @param filePath the path of the file to move.
     * @param destinationPath the new file's path.
     * @return <code>true</code> if the operation ends up successfully.
     */
    @SuppressWarnings("unused")
    public boolean moveIfPossible(
        @NotNull final String filePath, @NotNull final String destinationPath)
    {
        return
            moveIfPossible(
                new File(filePath),
                new File(destinationPath));
    }

    /**
     * Translates given package name to a relative path.
     * @param packageName the package name.
     * @return the path.
     */
    @Nullable
    public String packageToPath(@NotNull final String packageName)
    {
        String result = null;

        try
        {
            @NotNull final Helper t_Helper = createHelper(RegexpManager.getInstance());

            result =
                t_Helper.replaceAll(packageName, "\\.", File.separator);
        }
        catch  (final MalformedPatternException malformedPatternException)
        {
            /*
             * This exception is about pattern mismatch. In my opinion,
             * it's an error that should be detected at compile time,
             * but regexp API design cannot provide this functionality,
             * since all patterns are defined with strings, and therefore
             * they escape all compiler checks.
             */
            LogFactory.getLog(FileUtils.class).warn(
                "Malformed static patterns are fatal coding errors.",
                malformedPatternException);
        }
        catch  (final RegexpEngineNotFoundException regengNotFoundException)
        {
            /*
             * This exception is thrown only if no regexp library is available
             * at runtime. Not only this one, but any method provided by this
             * class that use regexps will not work.
             */
            LogFactory.getLog(FileUtils.class).fatal(
                "Cannot find any regexp engine.",
                regengNotFoundException);
        }
        catch  (final RegexpPluginMisconfiguredException
                      regexpPluginMisconfiguredException)
        {
            /*
             * This exception is thrown if RegexpPlugin cannot be configured
             * properly at runtime. Not only this one, but any method provided
             * by thisclass that use regexps will not work.
             */
            LogFactory.getLog(FileUtils.class).fatal(
                "Cannot configure RegexpPlugin.",
                regexpPluginMisconfiguredException);
        }

        return result;
    }

    /**
     * Creates the helper.
     * @return the regexp helper.
     * @throws RegexpEngineNotFoundException if a suitable instance
     * cannot be created.
     * @throws RegexpPluginMisconfiguredException if RegexpPlugin is
     * misconfigured.
     */
    @NotNull
    protected static synchronized Helper createHelper()
      throws RegexpEngineNotFoundException,
             RegexpPluginMisconfiguredException
    {
        return createHelper(RegexpManager.getInstance());
    }

    /**
     * Creates the helper.
     * @param regexpManager the RegexpManager instance.
     * @return the regexp helper.
     * @throws RegexpEngineNotFoundException if a suitable instance
     * cannot be created.
     * @throws RegexpPluginMisconfiguredException if RegexpPlugin is
     * misconfigured.
     */
    @NotNull
    protected static synchronized Helper createHelper(
        @NotNull final RegexpManager regexpManager)
      throws RegexpEngineNotFoundException,
             RegexpPluginMisconfiguredException
    {
        return createHelper(regexpManager.getEngine());
    }

    /**
     * Creates the helper.
     * @param regexpEngine the RegexpEngine instance.
     * @return the regexp helper.
     */
    @NotNull
    protected static synchronized Helper createHelper(
        @NotNull final RegexpEngine regexpEngine)
    {
        return regexpEngine.createHelper();
    }
}
