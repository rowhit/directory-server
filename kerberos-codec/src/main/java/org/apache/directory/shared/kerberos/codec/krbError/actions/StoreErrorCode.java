/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */
package org.apache.directory.shared.kerberos.codec.krbError.actions;


import org.apache.directory.shared.asn1.ber.Asn1Container;
import org.apache.directory.shared.kerberos.codec.actions.AbstractReadInteger;
import org.apache.directory.shared.kerberos.codec.krbError.KrbErrorContainer;
import org.apache.directory.shared.kerberos.exceptions.ErrorType;


/**
 * The action used to store the error-code
 * 
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class StoreErrorCode extends AbstractReadInteger
{

    /**
     * Instantiates a new StoreErrorCode action.
     */
    public StoreErrorCode()
    {
        super( "KRB-ERROR errorCode" );
    }


    /**
     * {@inheritDoc}
     */
    @Override
    protected void setIntegerValue( int value, Asn1Container container )
    {
        KrbErrorContainer krbErrContainer = ( KrbErrorContainer ) container;
        
        ErrorType errorCode = ErrorType.getTypeByOrdinal( value );
        
        krbErrContainer.getKrbError().setErrorCode( errorCode );
    }
}