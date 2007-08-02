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
 * Description: Adapts GNU Regexp 1.1.4 RE objects to follow the
 *              generic Pattern interface defined in this API.
 *
 * Last modified by: $Author: chous $ at $Date: 2004-12-01 09:50:27 +0100 (Wed, 01 Dec 2004) $
 *
 * File version: $Revision: 473 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: PatternGNUAdapter.java 473 2004-12-01 08:50:27Z chous $
 *
 */
package org.acmsl.commons.regexpplugin.gnuregexp;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.regexpplugin.Pattern;

/*
 * Importing some GNU Regexp classes.
 */
import gnu.regexp.RE;

/**
 * Adapts GNU Regexp 1.1.4 RE objects to follow the generic
 * Pattern interface defined in this API.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision: 473 $
 */
public class PatternGNUAdapter
    implements  Pattern
{
    /**
     * Delegated instance.
     */
    private RE m__Instance;

    /**
     * Constructs a PatternGNUAdapter for given object.
     * @param adaptee the instance to be adapted.
     */
    public PatternGNUAdapter(RE adaptee)
    {
        inmutableSetRE(adaptee);
    }

    /**
     * Specifies the adaptee.
     * @param adaptee the instance to adapt.
     */
    private void inmutableSetRE(RE adaptee)
    {
        m__Instance = adaptee;
    }

    /**
     * Specifies the adaptee.
     * @param adaptee the instance to adapt.
     */
    protected void setRE(RE adaptee)
    {
        inmutableSetRE(adaptee);
    }

    /**
     * Retrieves the adaptee.
     * @return such adapted instance.
     */
    protected RE getRE()
    {
        return m__Instance;
    }

    /**
     * Retrieves the adapted instance of the RE class.
     * Note: This method has package private access rights.
     * @return such instance.
     */
    RE getDelegatedInstance()
    {
        return m__Instance;
    }
}
