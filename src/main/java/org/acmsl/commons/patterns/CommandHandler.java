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
 * Description: Represents objects responsible of receiving commands,
 *              and depending on the command passed, handle it or
 *              pass it to the next handler in the chain.
 *
 * Last modified by: $Author: chous $ at $Date: 2004-09-06 09:05:48 +0200 (Mon, 06 Sep 2004) $
 *
 * File version: $Revision: 397 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: CommandHandler.java 397 2004-09-06 07:05:48Z chous $
 *
 */
package org.acmsl.commons.patterns;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Command;

/**
 * Represents objects responsible of receiving commands, and
 * depending on the command passed, handle it or pass it to the next
 * handler in the chain.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision: 397 $ $Date: 2004-09-06 09:05:48 +0200 (Mon, 06 Sep 2004) $
 */
public interface CommandHandler
{
    /**
     * Asks the handler to process the command. The idea is that each
     * command handler decides if such command is suitable of being
     * processed, and if so perform the concrete actions the command
     * represents.
     * @param command the command to process (or not).
     * @return <code>true</code> if the handler actually process the command,
     * or maybe because it's not desirable to continue the chain.
     */
    public boolean handle(final Command command);
}
