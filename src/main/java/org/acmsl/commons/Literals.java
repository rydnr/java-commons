/*
                        Java Commons

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
 * Filename: Literals.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Literals for Java Commons.
 *
 * Date: 2013/11/29
 * Time: 07:45
 *
 */
package org.acmsl.commons;

/*
 * Importing checkthread.org annotations.
 */
import org.checkthread.annotations.ThreadSafe;

/**
 * Literals for Java Commons.
 * @author <a href="mailto:queryj@acm-sl.org">Jose San Leandro</a>
 * @since 3.0
 * Created: 2013/11/29 07:45
 */
@ThreadSafe
public interface Literals
{
    String COULD_NOT_LOAD_ENVIRONMENT_PROPERTY = "Could not load environment property ";
    String PROPERTIES = ".properties";
    String M_B_CASE_SENSITIVE = "m__bCaseSensitive=";
    String M_B_MULTILINE = ", m__bMultiline=";
    String FALSE = "false";
    String FALSE_L = FALSE;
    String SOMETHING_ELSE = "something else";
    String DUE_TO_LOG4_J_CLASS_LOADING_ISSUES = "due to Log4J class-loading issues.";
    String ERROR_USING_COMMONS_LOGGING_THIS_CAN_HAPPEN =
        "Error using Commons-Logging. This can happen ";
    String ATTRS = "attrs";
    String CLASS = ".class";
    String GET_CLASSPATH = "getClasspath";
    String GET_UR_LS = "getURLs";
    String GET_BOOTSTRAP_CLASS_PATH = "getBootstrapClassPath";
    String OTHER_STUFF = "otherStuff";
    String TO_JSON = "toJson";
    String TO_STRING = "toString";
    String NO_REGEXP_ENGINE_FOUND = "no regexp engine found";
    String UNKNOWN_ERROR = "Unknown error";
    String COULD_NOT_INITIALIZE_STRING_UTILS = "Could not initialize StringUtils";
    String MALFORMED_STATIC_PATTERNS_ARE_FATAL_CODING_ERRORS = "Malformed static patterns are fatal coding errors.";
    String CANNOT_FIND_ANY_REGEXP_ENGINE = "Cannot find any regexp engine.";
    String MALFORMED_PATTERN = "Malformed pattern";
    String MALFORMED_PATTERN_POSSIBLY_DUE_TO_QUOTE_SYMBOL_CONFLICT =
        "Malformed pattern (possibly due to quote symbol conflict)";
    String INVALID_SUB_PACKAGE_PATTERN = "Invalid sub-package pattern";
}
