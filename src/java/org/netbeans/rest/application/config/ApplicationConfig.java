/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author snake
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(REST.AdminResource.class);
        resources.add(REST.BookingsResource.class);
        resources.add(REST.CarResource.class);
        resources.add(REST.Car_MakeResourse.class);
        resources.add(REST.Car_ModelResourse.class);
        resources.add(REST.CcResource.class);
        resources.add(REST.CountiesResource.class);
        resources.add(REST.LoginResourse.class);
        resources.add(REST.LotsResource.class);
        resources.add(REST.NewCrossOriginResourceSharingFilter.class);
        resources.add(REST.PaymentLogsResource.class);
        resources.add(REST.RecoveryPasswordResource.class);
        resources.add(REST.RecoveryResource.class);
        resources.add(REST.SupportResource.class);
        resources.add(REST.UserCarResource.class);
        resources.add(REST.UserDetailsResource.class);
        resources.add(REST.UserResource.class);
        resources.add(REST.VipResource.class);
        resources.add(REST.ZoneResource.class);
    }
    
}
