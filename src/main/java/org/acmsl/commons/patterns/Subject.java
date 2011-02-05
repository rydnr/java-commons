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
 * Filename: Subject.java
 *
 * Author: Jose San Leandro Armendariz
 *
 * Description: Generic implementation of the Observable pattern.
 *
 */
package org.acmsl.commons.patterns;

/*
 * Importing some ACM-SL classes.
 */
import org.acmsl.commons.patterns.Observable;
import org.acmsl.commons.patterns.Observer;

/*
 * Importing some JDK classes.
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/**
 * Generic implementation of the Observable pattern.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro Armendariz</a>
 */
public class Subject
    implements  Observable
{
    /**
     * Concrete reference to the observers' collection.
     * @associates <{Observer}>
     */
    private Collection m__cObservers;

    /**
     * Specifies the observer collection.
     * @param observers the observers.
     */
    protected void setObservers(final Collection observers)
    {
        m__cObservers = observers;
    }

    /**
     * Retrieves the observer collection.
     * @return the observers.
     */
    protected Collection getObservers()
    {
        return m__cObservers;
    }

    /**
     * Retrieves the observers. This includes creating the collection the first
     * time, so this method never returns a <code>null</code> value.
     * @return a not <code>null</code> collection.
     */
    private Collection getObserverCollection()
    {
        Collection result = getObservers();

        if  (result == null)
        {
            result = new ArrayList();
            setObservers(result);
        }

        return result;
    }

    /**
     * Attaches given observer in order to be notified of state changes.
     * @param observer the new observer to attach.
     */
    public void attach(final Observer observer)
    {
        if  (observer != null)
        {
            getObserverCollection().add(observer);
        }
    }

    /**
     * Attaches given observer in order to be notified of state changes.
     * @param observer the new observer to attach.
     * @param observerCollection the observer collection.
     * @precondition observer != null
     * @precondition observerCollection != null
     */
    protected void attach(
        final Observer observer, final Collection observerCollection)
    {
        observerCollection.add(observer);
    }

    /**
     * Detaches given observer in order to stop being notified of state
     * changes.
     * @param observer the new observer to attach.
     */
    public void detach(final Observer observer)
    {
        if  (observer != null)
        {
            getObserverCollection().remove(observer);
        }
    }

    /**
     * Detaches given observer in order to stop being notified of state
     * changes.
     * @param observer the new observer to attach.
     * @param observerCollection the observer collection.
     * @precondition observer != null
     * @precondition observerCollection != null
     */
    protected void detach(
        final Observer observer, final Collection observerCollection)
    {
        observerCollection.remove(observer);
    }

    /**
     * Notifies all attached observers of state changes.
     */
    public void inform()
    {
        Iterator t_itObservers = observers();

        while  (   (t_itObservers != null)
                && (t_itObservers.hasNext()))
        {
            ((Observer) t_itObservers.next()).update();
        }
    }

    /**
     * Retrieves all observers attached to this subject.
     * @return the iterator to browse all observers.
     */
    public Iterator observers()
    {
        Collection t_cResult = getObservers();

        if  (t_cResult != null)
        {
            Vector t_vClone = new Vector(t_cResult);

            t_cResult = t_vClone.subList(0, t_vClone.size());
        }
        else 
        {
            t_cResult = new ArrayList();
        }
        
        return t_cResult.iterator();
    }
}
