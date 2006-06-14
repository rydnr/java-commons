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
 * Description: Simple way to annotate timestamps.
 *
 * Last modified by: $Author: chous $ at $Date: 2006-04-02 12:07:24 +0200 (Sun, 02 Apr 2006) $
 *
 * File version: $Revision: 151 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: CharUtils.java 151 2006-04-02 10:07:24Z chous $
 *
 */
package org.acmsl.commons.utils;

/*
 * Importing JDK classes.
 */
import java.lang.System;

/**
 * Provides a simple way to annotate timestamps.
 * @author <a href="mailto:jose.sanleandro@ventura24.es">Jose San Leandro</a>
 */
public class Chronometer
{
    /**
     * An empty cached String array.
     */
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    /**
     * The milliseconds per second.
     */
    public static final long MILLISECONDS_PER_SECOND = 1000;
    
    /**
     * The milliseconds per minute.
     */
    public static final long MILLISECONDS_PER_MINUTE =
        MILLISECONDS_PER_SECOND * 60;
    
    /**
     * The milliseconds per hour.
     */
    public static final long MILLISECONDS_PER_HOUR =
        MILLISECONDS_PER_MINUTE * 60;
    
    /**
     * The milliseconds per day.
     */
    public static final long MILLISECONDS_PER_DAY =
        MILLISECONDS_PER_HOUR * 24;
    
    /**
     * The milliseconds per year.
     */
    public static final long MILLISECONDS_PER_YEAR =
        MILLISECONDS_PER_DAY * 365;
    
    /**
     * The starting point.
     */
    private long start;

    /**
     * Creates a new <code>Chronometer</code>.
     */
    public Chronometer()
    {
        immutableSetStart(System.currentTimeMillis());
    }

    /**
     * Specifies the starting point.
     * @param start such point.
     */
    private void immutableSetStart(final long start)
    {
        this.start = start;
    }

    /**
     * Specifies the starting point.
     * @param start such point.
     */
    protected void setStart(final long start)
    {
        immutableSetStart(start);
    }

    /**
     * Retrieves the starting point.
     * @return such point.
     */
    public long getStart()
    {
        return start;
    }

    /**
     * Displays the time since this instance was created,
     * in MM:ss:ms format.
     * @return such time interval.
     */
    public String now()
    {
        return between(getStart(), System.currentTimeMillis());
    }

    /**
     * Displays the time between given timestamps,
     * in MM:ss:ms format.
     * @param start the start.
     * @param end the end.
     * @return such time interval.
     */
    public String between(final long start, final long end)
    {
        return between(start, end, EMPTY_STRING_ARRAY);
    }

    /**
     * Displays the time between given timestamps,
     * in MM:ss:ms format.
     * @param start the start.
     * @param end the end.
     * @param separators the separators, which will be used as follows:
     * <ol>
     * <li>seconds (separators[0] ? separators[0] : ".") milliseconds</li>
     * <li>minutes (separators[1] ? separators[1] : separators[0]) seconds</li>
     * <li>hours (separators[2] ? separators[2] : separators[1]) minutes</li>
     * <li>days (separators[3] ? separators[3] : separators[2]) hours</li>
     * <li>years (separators[4] ? separators[4] : separators[3]) days</li>
     * </ol>
     * @return such time interval.
     * @precondition separators != null
     */
    public String between(
        final long start, final long end, final String[] separators)
    {
        StringBuffer result = new StringBuffer();
        
        long delta = end - start;

        long years = intervalInYears(delta);
        long days = intervalInDays(delta, years);
        long hours = intervalInHours(delta, days, years);
        long minutes =
            intervalInMinutes(
                delta, hours, days, years);
        long seconds =
            intervalInSeconds(
                delta, minutes, hours, days, years);
        long milliseconds =
            intervalInMilliseconds(
                delta, seconds, minutes, hours, days, years);

        long[] timeMetrics =
            new long[] 
            {
                years, days, hours, minutes, seconds, milliseconds
            };

        long separatorCount = (separators != null) ? separators.length : 0;

        String[] actualSeparators =
            new String[] { "y", "d", "h", "m", "s", "ms"};

        int separatorIndex = 0;

        int actualSeparatorCount = actualSeparators.length;
        
        for  (int index = 0; index < actualSeparatorCount; index++)
        {
            if  (   (separatorCount > separatorIndex)
                 && (separators[separatorIndex] != null))
            {
                actualSeparators[index] = separators[separatorIndex++];
            }

                    // there's something to print
            if  (   (timeMetrics[index] > 0)
                    // there's already something printed
                 || (result.length() > 0)
                    // print even 0ms
                 || (index == actualSeparatorCount - 1))
            {
                if  (   (timeMetrics[index] == 0)
                     && (result.length() == 0)
                     && (index == actualSeparatorCount - 1))
                {
                    result.append("<1");
                }
                else
                {
                    result.append(timeMetrics[index]);
                }

                result.append(actualSeparators[index]);
            }
        }
        
        return result.toString();
    }

    /**
     * Retrieves given milliseconds in years.
     * @param millis such amount.
     * @return the years.
     * @precondition millis > 0
     */
    public long inYears(final long millis)
    {
        return intervalInYears(millis - getStart());
    }

    /**
     * Retrieves given milliseconds in years.
     * @param millis such amount.
     * @return the years.
     * @precondition millis > 0
     */
    public long intervalInYears(final long millis)
    {
        return (long) ((double) millis / MILLISECONDS_PER_YEAR);
    }

    /**
     * Retrieves given milliseconds in days.
     * @param millis such amount.
     * @return the days.
     * @precondition millis > 0
     */
    public long inDays(final long millis)
    {
        return intervalInDays(millis - getStart());
    }

    /**
     * Retrieves given milliseconds in days.
     * @param millis such amount.
     * @return the days.
     * @precondition millis > 0
     */
    public long intervalInDays(final long millis)
    {
        return intervalInDays(millis, 0);
    }
    
    /**
     * Retrieves given milliseconds in days.
     * @param millis such amount.
     * @param years the previously-calculated years.
     * @return the days.
     * @precondition millis > 0
     */
    protected long intervalInDays(final long millis, final long years)
    {
        return
            (long)
                ((double)
                     (  millis
                      - (years * MILLISECONDS_PER_YEAR))
                 / MILLISECONDS_PER_DAY);
    }

    /**
     * Retrieves given milliseconds in hours.
     * @param millis such amount.
     * @return the hours.
     * @precondition millis > 0
     */
    public long inHours(final long millis)
    {
        return intervalInHours(millis - getStart());
    }

    /**
     * Retrieves given milliseconds in hours.
     * @param millis such amount.
     * @return the hours.
     * @precondition millis > 0
     */
    public long intervalInHours(final long millis)
    {
        return intervalInHours(millis, 0);
    }
    
    /**
     * Retrieves given milliseconds in hours.
     * @param millis such amount.
     * @param days the previously-calculated days.
     * @return the hours.
     * @precondition millis > 0
     */
    protected long intervalInHours(final long millis, final long days)
    {
        return intervalInHours(millis, days, 0);
    }

    /**
     * Retrieves given milliseconds in hours.
     * @param millis such amount.
     * @param days the previously-calculated days.
     * @param years the previously-calculated years.
     * @return the hours.
     * @precondition millis > 0
     */
    protected long intervalInHours(
        final long millis, final long days, final long years)
    {
        return
            (long)
                ((double)
                     (  millis
                      - (days * MILLISECONDS_PER_DAY)
                      - (years * MILLISECONDS_PER_YEAR))
                 / MILLISECONDS_PER_HOUR);
    }

    /**
     * Retrieves given milliseconds in minutes.
     * @param millis such amount.
     * @return the minutes.
     * @precondition millis > 0
     */
    public long inMinutes(final long millis)
    {
        return intervalInMinutes(millis - getStart());
    }

    /**
     * Retrieves given milliseconds in minutes.
     * @param millis such amount.
     * @return the minutes.
     * @precondition millis > 0
     */
    public long intervalInMinutes(final long millis)
    {
        return intervalInMinutes(millis, 0);
    }
    
    /**
     * Retrieves given milliseconds in minutes.
     * @param millis such amount.
     * @param hours the previously-calculated hours.
     * @return the minutes.
     * @precondition millis > 0
     */
    protected long intervalInMinutes(final long millis, final long hours)
    {
        return intervalInMinutes(millis, hours, 0);
    }

    /**
     * Retrieves given milliseconds in minutes.
     * @param millis such amount.
     * @param hours the previously-calculated hours.
     * @param days the previously-calculated days.
     * @return the minutes.
     * @precondition millis > 0
     */
    protected long intervalInMinutes(
        final long millis, final long hours, final long days)
    {
        return intervalInMinutes(millis, hours, days, 0);
    }

    /**
     * Retrieves given milliseconds in minutes.
     * @param millis such amount.
     * @param hours the previously-calculated hours.
     * @param days the previously-calculated days.
     * @param years the previously-calculated years.
     * @return the minutes.
     * @precondition millis > 0
     */
    protected long intervalInMinutes(
        final long millis,
        final long hours,
        final long days,
        final long years)
    {
        return
            (long)
                ((double)
                     (  millis
                      - (hours * MILLISECONDS_PER_HOUR)
                      - (days * MILLISECONDS_PER_DAY)
                      - (years * MILLISECONDS_PER_YEAR))
                 / MILLISECONDS_PER_MINUTE);
    }

    /**
     * Retrieves given milliseconds in seconds.
     * @param millis such amount.
     * @return the seconds.
     * @precondition millis > 0
     */
    public long inSeconds(final long millis)
    {
        return intervalInSeconds(millis - getStart());
    }

    /**
     * Retrieves given milliseconds in seconds.
     * @param millis such amount.
     * @return the seconds.
     * @precondition millis > 0
     */
    public long intervalInSeconds(final long millis)
    {
        return intervalInSeconds(millis, 0);
    }

    /**
     * Retrieves given milliseconds in seconds.
     * @param millis such amount.
     * @param minutes the previously-calculated minutes.
     * @return the seconds.
     * @precondition millis > 0
     */
    protected long intervalInSeconds(final long millis, final long minutes)
    {
        return intervalInSeconds(millis, minutes, 0);
    }
    
    /**
     * Retrieves given milliseconds in seconds.
     * @param millis such amount.
     * @param minutes the previously-calculated minutes.
     * @param hours the previously-calculated hours.
     * @return the seconds.
     * @precondition millis > 0
     */
    protected long intervalInSeconds(
        final long millis, final long minutes, final long hours)
    {
        return intervalInSeconds(millis, minutes, hours, 0);
    }
    
    /**
     * Retrieves given milliseconds in seconds.
     * @param millis such amount.
     * @param minutes the previously-calculated minutes.
     * @param hours the previously-calculated hours.
     * @param days the previously-calculated days.
     * @return the seconds.
     * @precondition millis > 0
     */
    protected long intervalInSeconds(
        final long millis,
        final long minutes,
        final long hours,
        final long days)
    {
        return intervalInSeconds(millis, minutes, hours, days, 0);
    }
    
    /**
     * Retrieves given milliseconds in seconds.
     * @param millis such amount.
     * @param minutes the previously-calculated minutes.
     * @param minutes the previously-calculated minutes.
     * @param hours the previously-calculated hours.
     * @param days the previously-calculated days.
     * @param years the previously-calculated years.
     * @return the seconds.
     * @precondition millis > 0
     */
    protected long intervalInSeconds(
        final long millis,
        final long minutes,
        final long hours,
        final long days,
        final long years)
    {
        return
            (long)
                ((double)
                     (  millis
                      - (minutes * MILLISECONDS_PER_MINUTE)
                      - (hours * MILLISECONDS_PER_HOUR)
                      - (days * MILLISECONDS_PER_DAY)
                      - (years * MILLISECONDS_PER_YEAR))
                 / MILLISECONDS_PER_SECOND);
    }

    /**
     * Retrieves given milliseconds in seconds.
     * @param millis such amount.
     * @return the seconds.
     * @precondition millis > 0
     */
    protected long inMilliseconds(final long millis)
    {
        return intervalInMilliseconds(millis - getStart());
    }

    /**
     * Retrieves given milliseconds in seconds.
     * @param millis such amount.
     * @return the seconds.
     * @precondition millis > 0
     */
    public long intervalInMilliseconds(final long millis)
    {
        return intervalInMilliseconds(millis, 0);
    }

    /**
     * Retrieves given milliseconds in seconds.
     * @param millis such amount.
     * @param seconds the previously-calculated seconds.
     * @return the seconds.
     * @precondition millis > 0
     */
    protected long intervalInMilliseconds(
        final long millis, final long seconds)
    {
        return intervalInMilliseconds(millis, seconds, 0);
    }

    /**
     * Retrieves given milliseconds in seconds.
     * @param millis such amount.
     * @param seconds the previously-calculated seconds.
     * @param minutes the previously-calculated minutes.
     * @return the seconds.
     * @precondition millis > 0
     */
    protected long intervalInMilliseconds(
        final long millis, final long seconds, final long minutes)
    {
        return intervalInMilliseconds(millis, seconds, minutes, 0);
    }

    /**
     * Retrieves given milliseconds in seconds.
     * @param millis such amount.
     * @param seconds the previously-calculated seconds.
     * @param minutes the previously-calculated minutes.
     * @param hours the previously-calculated hours.
     * @return the seconds.
     * @precondition millis > 0
     */
    protected long intervalInMilliseconds(
        final long millis,
        final long seconds,
        final long minutes,
        final long hours)
    {
        return
            intervalInMilliseconds(millis, seconds, minutes, hours, 0);
    }

    /**
     * Retrieves given milliseconds in seconds.
     * @param millis such amount.
     * @param seconds the previously-calculated seconds.
     * @param minutes the previously-calculated minutes.
     * @param hours the previously-calculated hours.
     * @param days the previously-calculated days.
     * @return the seconds.
     * @precondition millis > 0
     */
    protected long intervalInMilliseconds(
        final long millis,
        final long seconds,
        final long minutes,
        final long hours,
        final long days)
    {
        return
            intervalInMilliseconds(
                millis, seconds, minutes, hours, days, 0);
    }

    /**
     * Retrieves given milliseconds in seconds.
     * @param millis such amount.
     * @param seconds the previously-calculated seconds.
     * @param minutes the previously-calculated minutes.
     * @param hours the previously-calculated hours.
     * @param days the previously-calculated days.
     * @param years the previously-calculated years.
     * @return the seconds.
     * @precondition millis > 0
     */
    protected long intervalInMilliseconds(
        final long millis,
        final long seconds,
        final long minutes,
        final long hours,
        final long days,
        final long years)
    {
        return
            (  millis
             - (seconds * MILLISECONDS_PER_SECOND)
             - (minutes * MILLISECONDS_PER_MINUTE)
             - (hours * MILLISECONDS_PER_HOUR)
             - (days * MILLISECONDS_PER_DAY)
             - (years * MILLISECONDS_PER_YEAR));
    }
}
