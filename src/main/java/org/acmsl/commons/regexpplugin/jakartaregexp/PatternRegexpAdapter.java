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
    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

    Thanks to ACM S.L. for distributing this library under the GPL license.
    Contact info: jsr000@terra.es
    Postal Address: c/Playa de Lagoa, 1
                    Urb. Valdecabañas
                    Boadilla del monte
                    28660 Madrid
                    Spain

 ******************************************************************************
 *
 * Filename: $RCSfile$
 *
 * Author: Jose San Leandro Armendáriz
 *
 * Description: Adapts Jakarta Regexp REProgram objects to follow the
 *              generic Pattern interface defined in this API.
 *
 * Last modified by: $Author: chous $ at $Date: 2004-12-01 09:50:27 +0100 (Wed, 01 Dec 2004) $
 *
 * File version: $Revision: 473 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: PatternRegexpAdapter.java 473 2004-12-01 08:50:27Z chous $
 *
 */
package org.acmsl.commons.regexpplugin.jakartaregexp;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.regexpplugin.Pattern;

/*
 * Importing some Jakarta Regexp classes.
 */
import org.apache.regexp.RE;
import org.apache.regexp.REProgram;

/**
 * Adapts Jakarta Regexp REProgram objects to follow the generic
 * Pattern interface defined in this API.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision: 473 $
 */
public class PatternRegexpAdapter
    implements  Pattern
{
    /**
     * Delegated instance.
     */
    private REProgram m__Instance;

    /**
     * RE reference needed to pass compiler configuration to the
     * mather.
     */
    private RE m__RE;

    /**
     * Constructs a PatternRegexpAdapter for given object.
     * @param adaptee the instance to be adapted.
     * @param re the required RE instance.
     */
    public PatternRegexpAdapter(final REProgram adaptee, final RE re)
    {
        immutableSetREProgram(adaptee);
        immutableSetRE(re);
    }

    /**
     * Specifies the instance to adapt.
     * @param adaptee the REProgram to be adapted.
     */
    private void immutableSetREProgram(final REProgram adaptee)
    {
        m__Instance = adaptee;
    }

    /**
     * Specifies the instance to adapt.
     * @param adaptee the REProgram to be adapted.
     */
    protected void setREProgram(final REProgram adaptee)
    {
        immutableSetREProgram(adaptee);
    }

    /**
     * Retrieves the adapted REProgram instance.
     * @return such instance.
     */
    protected REProgram getREProgram()
    {
        return m__Instance;
    }

    /**
     * Sets the RE reference.
     * @param re such reference.
     */
    private void immutableSetRE(final RE re)
    {
        m__RE = re;
    }

    /**
     * Sets the RE reference.
     * @param re such reference.
     */
    void setRE(final RE re)
    {
        immutableSetRE(re);
    }

    /**
     * Retrieves the associated RE reference.
     * Note: Other classes in this package cannot access this method.
     * @return the RE with compiler configuration.
     */
    RE getRE()
    {
        return m__RE;
    }
}
