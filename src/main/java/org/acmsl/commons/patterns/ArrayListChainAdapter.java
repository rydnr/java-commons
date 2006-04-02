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
 * Description: Adapts ArrayList objects to implement the Chain
 *              interface.
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
import org.acmsl.commons.patterns.Chain;
import org.acmsl.commons.patterns.CommandHandler;

/*
 * Importing some JDK1.3 classes
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Adapts ArrayList objects to implement the Chain interface.
 * @author <a href="mailto:jsanleandro@yahoo.es"
           >Jose San Leandro Armendáriz</a>
 * @version $Revision$
 */
public class ArrayListChainAdapter
    implements  Chain
{
    /**
     * Actual storing object.
     */
    private List m__lCore;

    /**
     * Constructs an ArrayListChainAdapter.
     */
    public ArrayListChainAdapter()
    {
        immutableSetCore(new ArrayList());
    }

    /**
     * Specifies a new chain.
     * @param list the new list.
     * @precondition list != null
     */
    private void immutableSetCore(final List list)
    {
        m__lCore = list;
    }

    /**
     * Specifies a new chain.
     * @param list the new list.
     * @precondition list != null
     */
    protected void setCore(final List list)
    {
        immutableSetCore(list);
    }

    /**
     * Retrieves the core of this chain.
     * @return such collection.
     */
    protected List getCore()
    {
        return m__lCore;
    }

    /**
     * Adds a new commandHandler to the chain.
     * @param commandHandler the commandHandler to be added.
     * @precondition commandHandler != null
     */
    public void add(final CommandHandler commandHandler)
    {
        List t_lCore = getCore();

        if  (t_lCore != null)
        {
            t_lCore.add(commandHandler);
        }
    }

    /**
     * Adds a new commandHandler to the first position of the chain.
     * @param commandHandler the commandHandler to be added.
     * @precondition commandHandler != null
     */
    public void addFirst(final CommandHandler commandHandler)
    {
        List t_lCore = getCore();

        if  (t_lCore != null)
        {
            t_lCore.add(0, commandHandler);
        }
    }

    /**
     * Checks whether or not this chain contains given command handler.
     * @param commandHandler the handler to look for inside this chain.
     * @return <code>true</code> if such handler is already present in
     * this chain.
     * @precondition commandHandler != null
     */
    public boolean contains(final CommandHandler commandHandler)
    {
        boolean result = false;

        List t_lCore = getCore();

        if  (t_lCore != null)
        {
            result = t_lCore.contains(commandHandler);
        }

        return result;
    }

    /**
     * Retrieves the position given handler occupies in this chain.
     * @param commandHandler the handler to locate.
     * @return the first position in which given handler is found,
     * or -1 if doesn't participate in this chain.
     * @precondition commandHandler != null
     */
    public int indexOf(final CommandHandler commandHandler)
    {
        int result = -1;

        List t_lCore = getCore();

        if  (t_lCore != null)
        {
            result = t_lCore.indexOf(commandHandler);
        }

        return result;
    }

    /**
     * Retrieves the command handler that is located at given position.
     * @param commandHandlerIndex the position of the chain.
     * @return the command handler referred at such position.
     */
    public CommandHandler get(final int commandHandlerIndex)
    {
        CommandHandler result = null;

        if  (commandHandlerIndex >= 0)
        {
            List t_lCore = getCore();

            if  (t_lCore != null)
            {
                result = (CommandHandler) t_lCore.get(commandHandlerIndex);
            }
        }

        return result;
    }

    /**
     * Checks the emptyness of this chain.
     * @return true if this chain is empty.
     */
    public boolean isEmpty()
    {
        boolean result = false;

        List t_lCore = getCore();

        if  (t_lCore != null)
        {
            result = t_lCore.isEmpty();
        }

        return result;
    }

    /**
     * Retrieves the size of the chain.
     * @return current number of command handlers referred by this chain.
     */
    public int size()
    {
        int result = 0;

        List t_lCore = getCore();

        if  (t_lCore != null)
        {
            result = t_lCore.size();
        }

        return result;
    }
}
