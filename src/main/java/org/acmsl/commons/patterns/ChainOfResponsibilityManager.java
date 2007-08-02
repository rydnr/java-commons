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
 * Last modified by: $Author: chous $ at $Date: 2005-05-06 19:18:36 +0200 (Fri, 06 May 2005) $
 *
 * File version: $Revision: 491 $
 *
 * Project version: $Name$
 *                  ("Name" means no concrete version has been checked out)
 *
 * $Id: ChainOfResponsibilityManager.java 491 2005-05-06 17:18:36Z chous $
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
 * @version $Revision: 491 $ $Date: 2005-05-06 19:18:36 +0200 (Fri, 06 May 2005) $
 */
public abstract class ChainOfResponsibilityManager
    implements  ChainOfResponsibility,
                CommandSender,
                Manager
{
    /**
     * Flag inidicating whether the chain has been initialized or not.
     */
    private boolean m__bChainInitialized;

    /**
     * Constructs a manager object by calling initialization code.
     */
    protected ChainOfResponsibilityManager()
    {
        immutableSetChainInitialized(false);
    }

    /**
     * Specifies whether the chain has been initialized.
     * @param flag such flag.
     */
    protected final void immutableSetChainInitialized(final boolean flag)
    {
        m__bChainInitialized = flag;
    }

    /**
     * Retrieves whether the chain has been initialized.
     * @return such flag.
     */
    protected final boolean immutableGetChainInitialized()
    {
        return m__bChainInitialized;
    }

    /**
     * Retrieves the chain.
     * @return the chain.
     */
    protected final Chain immutableGetChain()
    {
        return immutableGetChain(immutableGetChainInitialized());
    }

    /**
     * Retrieves the chain.
     * @param chainInitialized whether the chain was initialized already.
     * @return the chain.
     */
    protected Chain immutableGetChain(final boolean chainInitialized)
    {
        Chain result = null;

        if  (!chainInitialized)
        {
            buildChain();
        }

        result = getChain();

        if  (   (result != null)
             && (!chainInitialized))
        {
            immutableSetChainInitialized(true);
        }

        return result;
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
        add(commandHandler, immutableGetChainInitialized());
    }

    /**
     * Adds given command handler to the chain.
     * @param commandHandler the command handler to add.
     * @param chainInitialized whether the chain is initialized.
     * @precondition commandHandler != null
     */
    protected void add(
        final CommandHandler commandHandler, final boolean chainInitialized)
    {
        Chain t_Chain = null;

        if  (chainInitialized)
        {
            t_Chain = getChain();
        }
        else
        {
            t_Chain = immutableGetChain(false);
        }

        add(commandHandler, t_Chain);
    }

    /**
     * Adds given command handler to the chain.
     * @param commandHandler the command handler to add.
     * @param chain the chain.
     * @precondition commandHandler != null
     */
    protected void add(
        final CommandHandler commandHandler, final Chain chain)
    {
        Chain t_Chain = chain;

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
        addFirst(commandHandler, immutableGetChainInitialized());
    }

    /**
     * Adds given command handler to the first position in the chain.
     * @param commandHandler the command handler to add.
     * @param chainInitialized whether the chain is initialized.
     * @precondition commandHandler != null
     */
    protected void addFirst(
        final CommandHandler commandHandler, final boolean chainInitialized)
    {
        Chain t_Chain = null;

        if  (chainInitialized)
        {
            t_Chain = getChain();
        }
        else
        {
            t_Chain = immutableGetChain(false);
        }

        addFirst(commandHandler, t_Chain);
    }

    /**
     * Adds given command handler to the first position in the chain.
     * @param commandHandler the command handler to add.
     * @param chain the chain.
     * @precondition commandHandler != null
     */
    protected void addFirst(
        final CommandHandler commandHandler, final Chain chain)
    {
        Chain t_Chain = chain;

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
     * @return the next handler in the chain.
     * @precondition commandHandler != null
     */
    public CommandHandler getNextChainLink(
        final CommandHandler commandHandler)
    {
        return
            getNextChainLink(
                commandHandler,
                immutableGetChainInitialized());
    }

    /**
     * Retrieves the link of the chain just after the one given commandHandler
     * takes.
     * @param commandHandler the handler just before the desired link.
     * @param chainInitialized whether the chain is already initialized.
     * @return the next handler in the chain.
     * @precondition commandHandler != null
     */
    protected CommandHandler getNextChainLink(
        final CommandHandler commandHandler, final boolean chainInitialized)
    {
        Chain t_Chain = null;

        if  (chainInitialized)
        {
            t_Chain = getChain();
        }
        else
        {
            t_Chain = immutableGetChain(false);
        }

        return
            getNextChainLink(
                commandHandler,
                t_Chain);
    }

    /**
     * Retrieves the link of the chain just after the one given commandHandler
     * takes.
     * @param commandHandler the handler just before the desired link.
     * @param chain the chain.
     * @return the next handler in the chain.
     * @precondition commandHandler != null
p     */
    protected CommandHandler getNextChainLink(
        final CommandHandler commandHandler, final Chain chain)
    {
        CommandHandler result = null;

        Chain t_Chain = chain;

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
