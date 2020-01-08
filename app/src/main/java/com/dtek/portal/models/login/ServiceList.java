package com.dtek.portal.models.login;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ServiceList extends BaseObservable {

    @SerializedName("isValid")
    @Expose
    private boolean valid;

    @SerializedName("services")
    @Expose
    private List<ServicePortal> services;

    public ServiceList(boolean valid, List<ServicePortal> services) {
        this.valid = valid;
        this.services = services;
    }

    public boolean isValid() {
        return !valid;
    }

    public List<ServicePortal> getServices() {
        return services;
    }

    public boolean getServiceAccess(String serviceName) {
        if(!valid){
            return false;
        } else {
            for (ServicePortal servicePortal : services) {
                if (servicePortal.getServiceName().equals(serviceName) && servicePortal.isSuccess()) {
                    return true;
                }
            }
        }
        return false;
    }
}
