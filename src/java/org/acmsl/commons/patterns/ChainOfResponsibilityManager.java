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
 * Description: Keeps tracks of the order in which objects are invoked to make
 *              them participate in the chain of responsibility pattern.
 *
 * Last modified by: $Author$ at $Date$
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
import org.acmsl.commons.patterns.ArrayListChainAdapter;
import org.acmsl.commons.patterns.Chain;
import org.acmsl.commons.patterns.ChainOfResponsibility;
import org.acmsl.commons.patterns.CommandSender;
import org.acmsl.commons.patterns.Manager;

/**
 * Keeps track of the order in which objects are invoked to
 * make them participate in the chain of responsibility pattern.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$ $Date$
 */
public abstract class ChainOfResponsibilityManager
    implements  ChainOfResponsibility,
                CommandSender,
                Manager
{
    /**
     * Constructs a manager object by calling initialization code.
     */
    protected ChainOfResponsibilityManager()
    {
        immutableInitChain();
    }

    /**
     * Performs all needed initial actions.
     */
    protected void immutableInitChain()
    {
        buildChain();
    }

    /**
     * Retrieves the concrete chain.
     * @return the chain itself.
     */
    protected abstract Chain getChain();

    /**
     * Adds given command handler to the chain.
     * @param commandHandler the command handler to add.
     * @precondition commandHandler != null
     */
    public void add(final CommandHandler commandHandler)
    {
        Chain t_Chain = getChain();

        if  (   (t_Chain != null)
             && (!t_Chain.contains(commandHandler)))
        {
            t_Chain.add(commandHandler);
        }
    }

    /**
     * Adds given command handler to the first position in the chain.
     * @param commandHandler the command handler to add.
     * @precondition commandHandler != null
     */
    public void addFirst(final CommandHandler commandHandler)
    {
        Chain t_Chain = getChain();

        if  (   (t_Chain != null)
             && (!t_Chain.contains(commandHandler)))
        {
            t_Chain.addFirst(commandHandler);
        }
    }

    /**
     * Retrieves the link of the chain just after the one given commandHandler
     * takes.
     * @param commandHandler the handler just before the desired link.
     * @return the next hanlder in the chain.
     * @precondition commandHandler != null
     */
    public CommandHandler getNextChainLink(
        final CommandHandler commandHandler)
    {
        CommandHandler result = null;

        Chain t_Chain = getChain();

        if  (   (t_Chain != null)
             && (!t_Chain.isEmpty()))
        {
            if  (   (commandHandler == null)
                 || (!t_Chain.contains(commandHandler)))
            {
                result = t_Chain.get(0);
            }
            else
            {
                int t_iCurrentIndex = t_Chain.indexOf(commandHandler);

                if  (   (t_iCurrentIndex >= 0)
                     && (t_iCurrentIndex < t_Chain.size() - 1))
                {
                    result =
                        (CommandHandler) t_Chain.get(t_iCurrentIndex + 1);
                }
            }
        }

        return result;
    }

    /**
     * Sends given command to the chain.
     * @param command the command that represents which actions should be done.
     * @return <code>true</code> if the command is processed by the chain.
     * @precondition command != null
     */
    public boolean send(final Command command)
    {
        boolean result = false;

        CommandHandler t_CurrentCommandHandler = null;

        do
        {
            t_CurrentCommandHandler =
                getNextChainLink(t_CurrentCommandHandler);

            if  (t_CurrentCommandHandler != null)
            {
                result = t_CurrentCommandHandler.handle(command);
            }
        }
        while  (   (!result)
                && (t_CurrentCommandHandler != null));

        return result;
    }

    /**
     * Builds the chain, that is, specifies the concrete order in which all
     * handlers are organized.
     */
    protected abstract void buildChain();
}
