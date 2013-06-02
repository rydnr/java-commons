/*
                        ACM-SL Commons

    Copyright (C) 2002-2005  Jose San Leandro Armend�riz
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
                    Urb. Valdecaba�as
                    Boadilla del monte
                    28660 Madrid
                    Spain

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armend�riz
 *
 * Description: Performs some unit tests on StringUtils class.
 *
 * File version: $Revision: 1493 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: StringUtilsTest.java 1493 2006-08-29 06:35:52Z chous $
 *
 */
package org.acmsl.commons.utils;

/*
 * Importing some JDK classes.
 */
import java.io.File;

/*
 * Importing JUnit classes.
 */
import junit.framework.TestCase;
import org.jetbrains.annotations.NotNull;

/**
 * Performs some unit tests on StringUtils class.
 * @author <a href="mailto:chous@acm-sl.org"
 *         >Jose San Leandro Armend�riz</a>
 * @see org.acmsl.commons.utils.StringUtils
 */
public class StringUtilsTest
    extends     TestCase
    implements  org.acmsl.commons.patterns.Test
{
    /**
     * Constructs a test case with the given name.
     * @param name the test case name.
     */
    public StringUtilsTest(@NotNull final String name)
    {
        super(name);
    }

    /**
     * Tests the <code>StringUtils.replace(text, original, replacement)</code>
     * method.
     * @see StringUtils#replace(String,String,String)
     */
    @SuppressWarnings("unused")
    public void testReplace()
    {
        @NotNull final StringUtils t_StringUtils = StringUtils.getInstance();

        assertNotNull(t_StringUtils);

        assertEquals(
            "replace(\"com.foo.bar\", \".\", \"-\") failed.",
            "com-foo-bar",
            t_StringUtils.replace("com.foo.bar", ".", "-"));

        assertEquals(
            "replace(\"com.foo.bar\", \"f\", \"-\") failed.",
            "com.-oo.bar",
            t_StringUtils.replace("com.foo.bar", "f", "-"));
    }

    /**
     * Tests the <code>StringUtils.escapeRegexp(text)</code>
     * method.
     * @see StringUtils#escapeRegexp(String)
     */
    @SuppressWarnings("unused")
    public void testEscapeRegexp()
    {
        @NotNull final StringUtils t_StringUtils = StringUtils.getInstance();

        assertNotNull(t_StringUtils);

        @NotNull final String t_strInput = "c$om.(foo)*.bar^";

        assertEquals(
            "escapeRegexp(\"" + t_strInput + "\") failed.",
            "c\\$om\\.\\(foo\\)\\*\\.bar\\^", // c\$om\.\(foo\)\*\.bar\^
            t_StringUtils.escapeRegexp(t_strInput));
    }

    /**
     * Tests the StringUtils.packageToFilePath(packageName) method.
     * @see StringUtils#packageToFilePath(String)
     */
    @SuppressWarnings("unused")
    public void testPackageToFilePath()
    {
        @NotNull final StringUtils t_StringUtils = StringUtils.getInstance();

        assertNotNull(t_StringUtils);

        assertEquals(
            "packageToFilePath(\"com.foo.bar\") failed.",
            "com" + File.separator + "foo" + File.separator + "bar",
            t_StringUtils.packageToFilePath("com.foo.bar"));

        assertEquals(
            "packageToFilePath(\"com\") failed.",
            "com",
            t_StringUtils.packageToFilePath("com"));

        assertEquals(
            "packageToFilePath(\"com.\") failed.",
            "com",
            t_StringUtils.packageToFilePath("com."));

        assertEquals(
            "packageToFilePath(\"com..\") failed.",
            "com",
            t_StringUtils.packageToFilePath("com.."));

        assertEquals(
            "packageToFilePath(\"..com...\") failed.",
            "com",
            t_StringUtils.packageToFilePath("..com..."));

        assertEquals(
            "packageToFilePath(\"..com...foo..bar....\") failed.",
            "com" + File.separator + "foo" + File.separator + "bar",
            t_StringUtils.packageToFilePath("..com...foo..bar...."));
    }

    /**
     * Tests the StringUtils.justify(text, margin) method.
     * @see StringUtils#justify(String,int)
     */
    @SuppressWarnings("unused")
    public void testJustify()
    {
        @NotNull final StringUtils t_StringUtils = StringUtils.getInstance();

        assertNotNull(t_StringUtils);

        String t_strInput1 = "To be justified just after the \"just\"";

        int t_iInput2 = "To be justified just ".length();

        String t_strJustified =
            t_StringUtils.justify(t_strInput1, t_iInput2);

        assertNotNull(t_strJustified);

        assertEquals(
            "justify(\"" + t_strInput1 + "\", \"" + t_iInput2 + "\") failed.",
            "To be justified just\nafter the \"just\"",
            t_strJustified);

        t_iInput2 = "To be justified just".length();

        t_strJustified = t_StringUtils.justify(t_strInput1, t_iInput2);

        assertNotNull(t_strJustified);

        assertEquals(
            "justify(\"" + t_strInput1 + "\", \"" + t_iInput2 + "\") failed.",
            "To be justified just\nafter the \"just\"",
            t_strJustified);

        t_strInput1 = "To be justified just  after the \"after\"";

        t_iInput2 = "To be justified just  after".length();

        t_strJustified =
            t_StringUtils.justify(t_strInput1, t_iInput2);

        assertNotNull(t_strJustified);

        assertEquals(
            "justify(\"" + t_strInput1 + "\", \"" + t_iInput2 + "\") failed.",
            "To be justified just after\nthe \"after\"",
            t_strJustified);
    }

    /**
     * Tests the StringUtils.uncapitalize(text, separator) method.
     * @see StringUtils#unCapitalize(String,String)
     */
    @SuppressWarnings("unused")
    public void testUncapitalize()
    {
        @NotNull final StringUtils t_StringUtils = StringUtils.getInstance();

        assertNotNull(t_StringUtils);

        String t_strInput = "thisIsATest";

        String t_strUnCapitalized =
            t_StringUtils.unCapitalize(t_strInput, "-");

        assertEquals(
            "unCapitalize(" + t_strInput + ", \"-\") failed.",
            "this-is-a-test",
            t_strUnCapitalized);

        t_strInput = "thisIsAnotherTest";

        t_strUnCapitalized =
            t_StringUtils.unCapitalize(t_strInput, "-");

        assertEquals(
            "unCapitalize(" + t_strInput + ", \"-\") failed.",
            "this-is-another-test",
            t_strUnCapitalized);

        t_strInput = "this Is yetAnotherTest";

        t_strUnCapitalized =
            t_StringUtils.unCapitalize(t_strInput, "-");

        assertEquals(
            "unCapitalize(" + t_strInput + ", \"-\") failed.",
            "this-is-yet-another-test",
            t_strUnCapitalized);
    }

    /**
     * Tests the StringUtils.uncapitalizeStart(text, separator) method.
     * @see StringUtils#unCapitalize(String,String)
     */
    @SuppressWarnings("unused")
    public void testUncapitalizeStart()
    {
        @NotNull final StringUtils t_StringUtils = StringUtils.getInstance();

        assertNotNull(t_StringUtils);

        String t_strInput = "ThisIsATest";

        String t_strUnCapitalized =
            t_StringUtils.unCapitalizeStart(t_strInput);

        assertEquals(
            "unCapitalizeStart(" + t_strInput + ", \"-\") failed.",
            "thisIsATest",
            t_strUnCapitalized);

        t_strInput = "ThisIsAnotherTest";

        t_strUnCapitalized =
            t_StringUtils.unCapitalizeStart(t_strInput);

        assertEquals(
            "unCapitalizeStart(" + t_strInput + ", \"-\") failed.",
            "thisIsAnotherTest",
            t_strUnCapitalized);
    }

    /**
     * Tests the StringUtils.applyToEachLine(text, format) method.
     * @see StringUtils#applyToEachLine(String,String)
     */
    @SuppressWarnings("unused")
    public void testApplyToEachLine()
    {
        @NotNull final StringUtils t_StringUtils = StringUtils.getInstance();

        assertNotNull(t_StringUtils);

        String t_strInput = " line 1   \n    and line 2";

        String t_strOutput =
            t_StringUtils.applyToEachLine(
                t_strInput, "||{0}{1}//");

        assertEquals(
            "applyToEachLine(\"" + t_strInput + "\"||{0}{1}//\") failed.",
            "||line 1//\n||   and line 2//\n",
            t_strOutput);

        t_strInput = "    line 1   \n .   and line 2";

        t_strOutput =
            t_StringUtils.applyToEachLine(
                t_strInput, "||{0}{1}//");

        assertEquals(
            "applyToEachLine(\"" + t_strInput + "\"||{0}{1}//\") failed.",
            "||line 1//\n||.   and line 2//\n",
            t_strOutput);

        t_strInput =
              "      update customers\n" 
            + "      set name = ?\n" 
            + "      where customer_id in\n" 
            + "      (\n" 
            + "        select person_id\n" 
            + "        from people\n" 
            + "        where last_name like '?%'\n" 
            + "      )\n";

        t_strOutput =
            t_StringUtils.applyToEachLine( 
                t_strInput, "{0}|{1}");

        assertEquals(
            "applyToEachLine(\"" + t_strInput + "\", \"{0}{1}\") failed.",
              "|update customers\n" 
            + "|set name = ?\n" 
            + "|where customer_id in\n" 
            + "|(\n" 
            + "  |select person_id\n" 
            + "  |from people\n" 
            + "  |where last_name like '?%'\n" 
            + "|)\n",
            t_strOutput);
    }

    /**
     * Tests the StringUtils.retrieveMinimumIndentInAllLines(text) method.
     * @see StringUtils#retrieveMinimumIndentInAllLines(String)
     */
    @SuppressWarnings("unused")
    public void testRetrieveMinimumIndentInAllLines()
    {
        @NotNull final StringUtils t_StringUtils = StringUtils.getInstance();

        assertNotNull(t_StringUtils);

        String t_strInput = " line 1   \n    and line 2";

        int t_iOutput =
            t_StringUtils.retrieveMinimumIndentInAllLines(t_strInput);

        assertEquals(
            "retrieveMinimumIndentInAllLines(\"" + t_strInput + "\") failed.",
            1,
            t_iOutput);

        t_strInput = "    line 1   \n .   and line 2";

        t_iOutput =
            t_StringUtils.retrieveMinimumIndentInAllLines(t_strInput);

        assertEquals(
            "retrieveMinimumIndentInAllLines(\"" + t_strInput + "\") failed.",
            1,
            t_iOutput);
    }

    /**
     * Tests the StringUtils.removeFirstAndLastBlankLines(text) method.
     * @see StringUtils#removeFirstAndLastBlankLines(String)
     */
    @SuppressWarnings("unused")
    public void testRemoveFirstAndLastBlankLines()
    {
        @NotNull final StringUtils t_StringUtils = StringUtils.getInstance();

        assertNotNull(t_StringUtils);

        String t_strInput = " line 1   \n    and line 2";

        String t_strOutput =
            t_StringUtils.removeFirstAndLastBlankLines(t_strInput);

        assertEquals(
            "removeFirstAndLastBlankLines(\"" + t_strInput + "\") failed.",
            t_strInput,
            t_strOutput);

        t_strInput = " \nline 1   \n    and line 2\n     ";

        t_strOutput =
            t_StringUtils.removeFirstAndLastBlankLines(t_strInput);

        assertEquals(
            "removeFirstAndLastBlankLines(\"" + t_strInput + "\") failed.",
            "line 1   \n    and line 2\n",
            t_strOutput);

        t_strInput =
            "insert into customers (customer_id, name) values (?, \"test\")";

        t_strOutput =
            t_StringUtils.removeFirstAndLastBlankLines(t_strInput);

        assertEquals(
            "removeFirstAndLastBlankLines(\"" + t_strInput + "\") failed.",
            t_strInput,
            t_strOutput);
    }

    /**
     * Tests the <code>StringUtils.capitalize(String)</code> method.
     * @see org.acmsl.commons.utils.StringUtils#capitalize(String)
     */
    @SuppressWarnings("unused")
    public void testCapitalize()
    {
        @NotNull final StringUtils t_StringUtils = StringUtils.getInstance();

        assertNotNull(t_StringUtils);

        @NotNull final String t_strInput = "ab-cd_ef  gh+ijk;;_|l~~@m  2n=o4p";

        @NotNull final String t_strOutput = t_StringUtils.capitalize(t_strInput);

        assertEquals(
            "capitalize(\"" + t_strInput + "\") failed.",
            "AbCdEfGhIjkLM2nO4p",
            t_strOutput);
    }

    /**
     * Tests the <code>StringUtils.escape(String, char)</code> method.
     * @see org.acmsl.commons.utils.StringUtils#escape(String, char)
     */
    @SuppressWarnings("unused")
    public void testEscape()
    {
        @NotNull final StringUtils t_StringUtils = StringUtils.getInstance();

        assertNotNull(t_StringUtils);

        @NotNull final String t_strInput = "ab ()\" .cde  \" f g ^\\\"";

        @NotNull final String t_strOutput = t_StringUtils.escape(t_strInput, '\"');

        assertEquals(
            "escape(\"" + t_strInput + "\") failed.",
            "ab ()\\\" .cde  \\\" f g ^\\\\\"",
            t_strOutput);
    }

    /**
     * Tests the <code>StringUtils.softNormalize()</code>
     * method.
     * @see org.acmsl.commons.utils.StringUtils#softNormalize(String, String)
     */
    @SuppressWarnings("unused")
    public void testSoftNormalize()
    {
        @NotNull final StringUtils t_StringUtils = StringUtils.getInstance();

        assertNotNull(t_StringUtils);

        String t_strInput;
        t_strInput = "number of    \nbets";

        String t_strOutput = t_StringUtils.softNormalize(t_strInput, "_");

        assertEquals(
            "softNormalize(\"" + t_strInput + "\") failed.",
            "number_of_bets",
            t_strOutput);

        t_strInput = "number_of_bets";

        t_strOutput = t_StringUtils.softNormalize(t_strInput, "_");

        assertEquals(
            "softNormalize(\"" + t_strInput + "\") failed.",
            t_strInput,
            t_strOutput);

        t_strInput = "allocate in different clubs";

        t_strOutput = t_StringUtils.softNormalize(t_strInput);

        assertEquals(
            "softNormalize(\"" + t_strInput + "\") failed.",
            "allocate_in_different_clubs",
            t_strOutput);

        t_strInput = "n-1";

        t_strOutput = t_StringUtils.softNormalize(t_strInput);

        assertEquals(
            "softNormalize(\"" + t_strInput + "\") failed.",
            "n_1",
            t_strOutput);
    }

    /**
     * Tests the <code>StringUtils.normalize()</code>
     * method.
     * @see org.acmsl.commons.utils.StringUtils#normalize(String)
     */
    @SuppressWarnings("unused")
    public void testNormalize()
    {
        @NotNull final StringUtils t_StringUtils = StringUtils.getInstance();

        assertNotNull(t_StringUtils);

        @NotNull final String t_strInput = "multiple.bet.result";

        @NotNull final String t_strOutput = t_StringUtils.normalize(t_strInput);

        assertEquals(
            "normalize(\"" + t_strInput + "\") failed.",
            "Multiple_bet_result",
            t_strOutput);
    }
}
