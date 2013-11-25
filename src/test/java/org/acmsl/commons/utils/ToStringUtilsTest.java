/*
                        queryj

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
 * Filename: ToStringUtilsTest.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Defines some tests on ToStringUtils.
 *
 * Date: 2013/11/24
 * Time: 07:18
 *
 */
package org.acmsl.commons.utils;

/*
 * Importing JetBrains annotations.
 */
import junit.framework.TestCase;
import org.jetbrains.annotations.NotNull;

/*
 * Importing checkthread.org annotations.
 */
import org.checkthread.annotations.ThreadSafe;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines some tests on {@link ToStringUtils}.
 * @author <a href="mailto:queryj@acm-sl.org">Jose San Leandro</a>
 * @since 3.0
 * Created: 2013/11/24 07:18
 */
@ThreadSafe
@SuppressWarnings("unused")
public class ToStringUtilsTest
    extends TestCase
    implements  org.acmsl.commons.patterns.Test
{
    /**
     * Constructs a test case with the given name.
     * @param name the test case name.
     */
    public ToStringUtilsTest(@NotNull final String name)
    {
        super(name);
    }

    protected static class Dummy
    {
        @Nullable private String fancyName;
        private long fancyAge;
        private double fancyValue;
        @Nullable private BigDecimal fancyAmount;
        @Nullable private Date fancyDate;
        @Nullable private Dummy fancyChild;
        @Nullable private String[] otherNames;
        @Nullable private List<String> otherStuff;
        @Nullable private List<Dummy> children;

        @NotNull
        @Override
        public String toString()
        {
            @NotNull final Map<String, Object> args = new HashMap<String, Object>();
            if (fancyName != null)
            {
                args.put("fancyName", fancyName);
            }
            args.put("fancyAge", fancyAge);
            args.put("fancyValue", fancyValue);
            if (fancyAmount != null)
            {
                args.put("fancyAmount", fancyAmount);
            }
            if (fancyDate != null)
            {
                args.put("fancyDate", fancyDate);
            }

            if (fancyChild != null)
            {
                args.put("fancyChild", fancyChild);
            }
            if (otherNames != null)
            {
                args.put("otherNames", otherNames);
            }
            if (otherStuff != null)
            {
                args.put("otherStuff", otherStuff);
            }
            if (children != null)
            {
                args.put("children", children);
            }

            return ToStringUtils.getInstance().toJson(this, Dummy.class, args);
        }
    }

    /**
     * Tests the <code>ToStringUtils.toJson(T, String, Map)</code>
     * method.
     * @see ToStringUtils
     */
    @SuppressWarnings("unused")
    public void testToJson()
    {
        @NotNull final ToStringUtils t_ToStringUtils = ToStringUtils.getInstance();

        assertNotNull(t_ToStringUtils);

        @NotNull final Dummy d = new Dummy();
        d.fancyName = "d1";
        d.fancyAge = 15L;
        d.fancyValue = 38D;
        d.fancyAmount = new BigDecimal("13131");
        @NotNull final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2000);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 33);
        calendar.set(Calendar.SECOND, 44);
        calendar.set(Calendar.MINUTE, 55);
        d.fancyDate = calendar.getTime();

        @NotNull final String expected =
            normalize(
                "{ \"class\": \"" + Dummy.class.getName() + '"'
                + ", \"fancyAmount\": " + d.fancyAmount
                + ", \"fancyDate\": \"" + new SimpleDateFormat("YYYY-MM-ddHH:mm:ss.SSSZ").format(d.fancyDate) + '"'
                + ", \"fancyValue\": " + d.fancyValue
                + ", \"fancyName\": \"" + d.fancyName + '"'
                + ", \"fancyAge\": " + d.fancyAge + " }");

        @NotNull final String actual = normalize(d.toString());

//        assertEquals("Invalid JSON", expected, actual);

        @NotNull final Dummy otherDummy = new Dummy();
        otherDummy.fancyName = "d1_1";
        otherDummy.fancyAge = 155L;
        otherDummy.fancyValue = 39D;
        otherDummy.fancyAmount = new BigDecimal("5");
        calendar.set(Calendar.YEAR, 2001);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 2);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 3);
        calendar.set(Calendar.SECOND, 4);
        calendar.set(Calendar.MINUTE, 5);
        otherDummy.fancyDate = calendar.getTime();
        d.fancyChild = otherDummy;

        @NotNull final String expected2 =
            normalize(
                  "{ \"class\": \"" + Dummy.class.getName() + '"'
                + ", \"fancyAmount\": " + d.fancyAmount
                + ", \"fancyChild\": "
                +   " { \"class\": \"" + Dummy.class.getName() + '"'
                +    ", \"fancyAmount\": " + otherDummy.fancyAmount
                +    ", \"fancyDate\": \"" + new SimpleDateFormat("YYYY-MM-ddHH:mm:ss.SSSZ").format(otherDummy.fancyDate) + '"'
                +    ", \"fancyValue\": " + otherDummy.fancyValue
                +    ", \"fancyName\": \"" + otherDummy.fancyName + '"'
                +    ", \"fancyAge\": " + otherDummy.fancyAge
                +    " } "
                + ", \"fancyDate\": \"" + new SimpleDateFormat("YYYY-MM-ddHH:mm:ss.SSSZ").format(d.fancyDate) + '"'
                + ", \"fancyValue\": " + d.fancyValue
                + ", \"fancyName\": \"" + d.fancyName + '"'
                + ", \"fancyAge\": " + d.fancyAge
                + " }");

        @NotNull final String actual2 = normalize(d.toString());

        @NotNull final String aux = otherDummy.toString();

        assertEquals("Invalid JSON (2)", expected2, actual2);

    }

    @NotNull
    protected String normalize(@NotNull final String text)
    {
        return text.replaceAll("\n", "").replaceAll("\\s*", "");
    }
}