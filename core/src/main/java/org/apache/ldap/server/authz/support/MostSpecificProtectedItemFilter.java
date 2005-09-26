/*
 *   @(#) $Id$
 *   
 *   Copyright 2004 The Apache Software Foundation
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */
package org.apache.ldap.server.authz.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.apache.ldap.common.aci.ACITuple;
import org.apache.ldap.common.aci.AuthenticationLevel;
import org.apache.ldap.common.aci.ProtectedItem;
import org.apache.ldap.server.interceptor.NextInterceptor;

public class MostSpecificProtectedItemFilter implements ACITupleFilter
{
    public Collection filter( Collection tuples, OperationScope scope, NextInterceptor next, Name userGroupName, Name userName, Attributes userEntry, AuthenticationLevel authenticationLevel, Name entryName, String attrId, Object attrValue, Attributes entry, Collection microOperations ) throws NamingException
    {
        if( tuples.size() <= 1 )
        {
            return tuples;
        }

        Collection filteredTuples = new ArrayList();
        
        // If the protected item is an attribute and there are tuples that
        // specify the attribute type explicitly, discard all other tuples.
        for( Iterator i = tuples.iterator(); i.hasNext(); )
        {
            ACITuple tuple = ( ACITuple ) i.next();
            for( Iterator j = tuple.getProtectedItems().iterator(); j.hasNext(); )
            {
                ProtectedItem item = ( ProtectedItem ) j.next();
                if( item instanceof ProtectedItem.AttributeType )
                {
                    filteredTuples.add( tuple );
                    break;
                }
                else if( item instanceof ProtectedItem.AllAttributeValues )
                {
                    filteredTuples.add( tuple );
                    break;
                }
                else if( item instanceof ProtectedItem.SelfValue )
                {
                    filteredTuples.add( tuple );
                    break;
                }
                else if( item instanceof ProtectedItem.AttributeValue )
                {
                    filteredTuples.add( tuple );
                    break;
                }
            }
        }
        
        if( filteredTuples.size() > 0 )
        {
            return filteredTuples;
        }

        // If the protected item is an attribute value, and there are tuples
        // that specify the attribute value explicitly, discard all other tuples.
        // A protected item which is a rangeOfValues is to be treated as
        // specifying an attribute value explicitly. 
        for( Iterator i = tuples.iterator(); i.hasNext(); )
        {
            ACITuple tuple = ( ACITuple ) i.next();
            for( Iterator j = tuple.getProtectedItems().iterator(); j.hasNext(); )
            {
                ProtectedItem item = ( ProtectedItem ) j.next();
                if( item instanceof ProtectedItem.RangeOfValues )
                {
                    filteredTuples.add( tuple );
                }
            }
        }

        if( filteredTuples.size() > 0 )
        {
            return filteredTuples;
        }
        
        return tuples;
    }
}
