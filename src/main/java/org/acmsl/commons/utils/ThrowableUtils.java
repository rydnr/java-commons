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
 * Description: Provides some useful methods when working with throwable
 *              instances.
 *
 * Last modified by: $Author: chous $ at $Date: 2006-06-14 21:01:54 +0200 (Wed, 14 Jun 2006) $
 *
 * File version: $Revision: 550 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: ThrowableUtils.java 550 2006-06-14 19:01:54Z chous $
 *
 */
package org.acmsl.commons.utils;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Utils;
import org.acmsl.commons.patterns.Singleton;

/*
 * Importing JDK1.3 classes.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Throwable;

/*
 * Importing commons-logging classes.
 */
import org.apache.commons.logging.LogFactory;

/**
 * Provides some useful methods when working with throwable instances.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @stereotype Utils
 * @version $Revision: 550 $
 */
public class ThrowableUtils
    implements  Utils,
                Singleton
{
    /**
     * Singleton implemented to avoid the double-checked locking.
     */
    private static class ThrowableUtilsSingletonContainer
    {
        /**
         * The actual singleton.
         */
        public static final ThrowableUtils SINGLETON = new ThrowableUtils();
    }

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected ThrowableUtils() {};

    /**
     * Retrieves a ThrowableUtils instance.
     * @return such instance.
     */
    public static ThrowableUtils getInstance()
    {
        return ThrowableUtilsSingletonContainer.SINGLETON;
    }

    /**
     * Retrieves given throwable's stack trace.
     * @param throwable the error to output.
     * @return its stack trace.
     */
    public String getStackTrace(final Throwable throwable)
    {
        String result = "";

        if  (throwable != null)
        {
            StringWriter t_swResult = new StringWriter();

            PrintWriter t_PrintWriter = new PrintWriter(t_swResult);

            throwable.printStackTrace(t_PrintWriter);

            result = t_swResult.toString();

            t_PrintWriter.close();

            try 
            {
                t_swResult.close();
            }
            catch  (final IOException ioException)
            {
                LogFactory.getLog(ThrowableUtils.class).fatal(
                    "Strange and exceptional error",
                    ioException);
            }
        }

        return result;
    }
}
