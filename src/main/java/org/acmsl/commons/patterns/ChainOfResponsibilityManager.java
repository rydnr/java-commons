//;-*- mode: java -*-
/*
                        ACM-SL Commons

    Copyright (C) 2002-today  Jose San Leandro Armendariz
                              chous@acm-sl.org

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

    Thanks to ACM S.L. for distributing this library under the LGPL license.
    Contact info: jose.sanleandro@acm-sl.com

 ******************************************************************************
 *
 * Filename: ChainOfResponsibilityManager.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Keeps tracks of the order in which objects are invoked to make
 *              them participate in the chain of responsibility pattern.
 *
 */
package org.acmsl.commons.patterns;

/**
 * Keeps track of the order in which objects are invoked to
 * make them participate in the chain of responsibility pattern.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public abstract class ChainOfResponsibilityManager<C extends CommandHandler>
    implements  ChainOfResponsibility<C>,
                CommandSender,
                Manager
{
    /**
     * Flag indicating whether the chain has been initialized or not.
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
    protected final Chain<C> immutableGetChain()
    {
        return immutableGetChain(immutableGetChainInitialized());
    }

    /**
     * Retrieves the chain.
     * @param chainInitialized whether the chain was initialized already.
     * @return the chain.
     */
    protected Chain<C> immutableGetChain(final boolean chainInitialized)
    {
        Chain<C> result;

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
    protected abstract Chain<C> getChain();

    /**
     * Adds given command handler to the chain.
     * @param commandHandler the command handler to add.
     * @precondition commandHandler != null
     */
    public void add(final C commandHandler)
    {
        add(commandHandler, immutableGetChainInitialized());
    }

    /**
     * Adds given command handler to the chain.
     * @param commandHandler the command handler to add.
     * @param chainInitialized whether the chain is initialized.
     * @precondition commandHandler != null
     */
    protected void add(final C commandHandler, final boolean chainInitialized)
    {
        Chain<C> t_Chain;

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
        final C commandHandler, final Chain<C> chain)
    {
        Chain<C> t_Chain = chain;

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
    public void addFirst(final C commandHandler)
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
        final C commandHandler, final boolean chainInitialized)
    {
        Chain<C> t_Chain;

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
        final C commandHandler, final Chain<C> chain)
    {
        Chain<C> t_Chain = chain;

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
    @Override
    public C getNextChainLink(final C commandHandler)
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
    protected C getNextChainLink(
        final C commandHandler, final boolean chainInitialized)
    {
        Chain<C> t_Chain;

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
     */
    protected C getNextChainLink(
        final C commandHandler, final Chain<C> chain)
    {
        C result = null;

        Chain<C> t_Chain = chain;

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
                    result = t_Chain.get(t_iCurrentIndex + 1);
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
    @Override
    public boolean send(final Command command)
    {
        boolean result = false;

        C t_CurrentCommandHandler = null;

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
