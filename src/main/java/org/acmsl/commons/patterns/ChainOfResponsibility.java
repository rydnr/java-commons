/*
                        ACM-SL Commons

    Copyright (C) 2002-2003  Jose San Leandro Armend�riz
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
 * Description: Represents classes that know about the ordering in which
 *              commands are handled by CommandHandler objects, under the
 *              model defined in GoF's Chain Of Responsibility pattern.
 *
 * Last modified by: $Author$
 *
 * File version: $Revision$
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id$
 *
 */
package org.acmsl.commons.patterns;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.CommandHandler;

/**
 * Represents classes that know about the ordering in which commands are
 * handled by CommandHandler objects, under the model defined in GoF's Chain Of
 * Responsibility design pattern.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armend�riz</a>
 * @version $Revision$ $Date$
 */
public interface ChainOfResponsibility
{
    /**
     * Retrieves the link of the chain just after the one given commandHandler
     * takes.
     * @param commandHandler the handler just before the desired link.
     * @return the next hanlder in the chain.
     */
    public CommandHandler getNextChainLink(
        final CommandHandler commandHandler);
}