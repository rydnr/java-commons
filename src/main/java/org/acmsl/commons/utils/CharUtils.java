//;-*- mode: java -*-
/*
                        ACM-SL Commons

    Copyright (C) 2002-2006  Jose San Leandro Armendáriz
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
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-307  USA


    Thanks to ACM S.L. for distributing this library under the LGPL license.
    Contact info: jose.sanleandro@acm-sl.com
    Postal Address: c/Playa de Lagoa, 1
                    Urb. Valdecabañas
                    Boadilla del monte

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armendáriz
 *
 * Description: Provides some useful methods when working with characters and
 *              buffers.
 *
 * Last modified by: $Author: chous $ at $Date: 2006-06-14 21:01:54 +0200 (Wed, 14 Jun 2006) $
 *
 * File version: $Revision: 550 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: CharUtils.java 550 2006-06-14 19:01:54Z chous $
 *
 */
package org.acmsl.commons.utils;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Singleton;
import org.acmsl.commons.patterns.Utils;

/**
 * Provides some useful methods when working with characters and buffers.
 * @author <a href="mailto:chous@acm-sl.org"
 *         >Jose San Leandro Armend&aacute;riz</a>
 * @version $Revision: 550 $ at $Date: 2006-06-14 21:01:54 +0200 (Wed, 14 Jun 2006) $
 */
public class CharUtils
    implements  Utils,
                Singleton
{
    /**
     * An empty char array for building invalid method invocations.
     */
    protected static final char[] EMPTY_CHAR_ARRAY = new char[0];

    /**
     * Singleton implemented to avoid the double-checked locking.
     */
    private static class CharUtilsSingletonContainer
    {
        /**
         * The actual singleton.
         */
        public static final CharUtils SINGLETON = new CharUtils();
    }

    /**
     * Protected constructor to avoid accidental instantiation.
     */
    protected CharUtils()  {};

    /**
     * Retrieves a CharUtils instance.
     * @return such instance.
     */
    public static CharUtils getInstance()
    {
        return CharUtilsSingletonContainer.SINGLETON;
    }

    /**
     * Creates a char array with the specified subcontents of given array.
     * @param buffer the buffer where to extract the information from.
     * @param start the starting position of the desired subcontents.
     * @param offset the subcontents length.
     * @return the sub-buffer, or null if any parameter is invalid.
     */
    public char[] subbuffer(
        final char[] buffer, final int start, final int offset)
    {
        char[] result = EMPTY_CHAR_ARRAY;

        if  (   (buffer != null)
             && (start  > -1)
             && (offset >  0))
        {
            int t_iEnd =
                (start + offset >= buffer.length)
                ?   buffer.length
                :   start + offset;

            result = new char[t_iEnd - start];

            for (
                int t_iCharIndex = start;
                    t_iCharIndex < t_iEnd;
                    t_iCharIndex ++)
            {
                result[t_iCharIndex - start] = buffer[t_iCharIndex];
            }
        }

        return result;
    }

    /**
     * Creates a char array with the specified subcontents of given array.
     * @param buffer the buffer where to extract the information from.
     * @param start the starting position of the desired subcontents.
     * @return the sub-buffer.
     */
    public char[] subbuffer(final char[] buffer, final int start)
    {
        char[] result = EMPTY_CHAR_ARRAY;

        if  (buffer != null)
        {
            result = subbuffer(buffer, start, buffer.length);
        }

        return result;
    }
}
