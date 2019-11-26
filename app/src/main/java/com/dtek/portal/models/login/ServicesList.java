package com.dtek.portal.models.login;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ServicesList extends BaseObservable {

    @SerializedName("isValid")
    @Expose
    private boolean valid;

    @SerializedName("services")
    @Expose
    private List<ServicePortal> services;

    public void setServices(List<ServicePortal> services) {
        this.services = services;
    }

    public boolean isValid() {
        return valid;
    }

    public boolean getServiceAccess(String serviceName) {
        for (ServicePortal servicePortal : services) {
            if (servicePortal.getServiceName().equals(serviceName) && servicePortal.isSuccess()) {
                return true;
            }
        }
        return false;
    }
}
