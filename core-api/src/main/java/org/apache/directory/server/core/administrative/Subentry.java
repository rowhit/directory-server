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
package org.apache.directory.server.core.administrative;


import java.util.Set;

import org.apache.directory.shared.ldap.subtree.SubtreeSpecification;


/**
 * An operational view of a subentry within the system. A Subentry can have
 * many types (Collective, Schema, AccessControl or Trigger) but only one
 * Subtree Specification. This subtreeSpecification will apply to all the
 * subentry's roles.
 *
 * @author <a href="mailto:dev@directory.apache.org">Apache Directory Project</a>
 */
public class Subentry
{
    /** The Subtree Specification associated with this subentry */
    private SubtreeSpecification ss;

    /** The administratives roles */
    private Set<AdministrativeRoleEnum> administrativeRoles;
    
    /** The subentry UUID */
    private String uuid;
    
    /** The subentry CN */
    private String cn;
    
    /**
     * Stores the subtreeSpecification
     *
     * @param ss The subtree specification
     */
    public void setSubtreeSpecification( SubtreeSpecification ss )
    {
        this.ss = ss;
    }


    /**
     * @return The subtree specification
     */
    public SubtreeSpecification getSubtreeSpecification()
    {
        return ss;
    }


    /**
     * Stores the set of roles for this subentry
     *
     * @param administrativeRoles The roles to be added
     */
    public void setAdministrativeRoles( Set<AdministrativeRoleEnum> administrativeRoles )
    {
        this.administrativeRoles = administrativeRoles;
    }


    /**
     * @return The list of roles for this subentry
     */
    public Set<AdministrativeRoleEnum> getAdministrativeRoles()
    {
        return administrativeRoles;
    }


    /**
     * Tells if the type contains the Collective attribute Administrative Role
     */
    public boolean isCollectiveAdminRole()
    {
        return administrativeRoles.contains( AdministrativeRoleEnum.CollectiveAttribute );
    }


    /**
     * Tells if the type contains the SubSchema Administrative Role
     */
    public boolean isSchemaAdminRole()
    {
        return administrativeRoles.contains( AdministrativeRoleEnum.SubSchema );
    }


    /**
     * Tells if the type contains the Access Control Administrative Role
     */
    public boolean isAccessControlAdminRole()
    {
        return administrativeRoles.contains( AdministrativeRoleEnum.AccessControl );
    }


    /**
     * Tells if the type contains the Triggers Administrative Role
     */
    public boolean isTriggersAdminRole()
    {
        return administrativeRoles.contains( AdministrativeRoleEnum.TriggerExecution );
    }


    /**
     * @return the uuid
     */
    public String getUuid()
    {
        return uuid;
    }


    /**
     * @param uuid the uuid to set
     */
    public void setUuid( String uuid )
    {
        this.uuid = uuid;
    }


    /**
     * @return the cn
     */
    public String getCn()
    {
        return cn;
    }


    /**
     * @param cn the cn to set
     */
    public void setCn( String cn )
    {
        this.cn = cn;
    }


    /**
     * @see Object#toString()
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append( "Subentry: " ).append( cn).append( '-').append( uuid ).append( "[" );
        
        boolean isFirst = true;
        
        for ( AdministrativeRoleEnum role : administrativeRoles )
        {
            if ( isFirst )
            {
                isFirst = false;
            }
            else
            {
                sb.append( ", " );
            }
            
            sb.append( role );
        }
        sb.append( "]" );
        
        return sb.toString();
    }
}
