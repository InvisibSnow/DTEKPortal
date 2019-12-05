package com.dtek.portal.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServicePortal {

    @SerializedName("ServiceName")
    @Expose
    private String serviceName;
    @SerializedName("Success")
    @Expose
    private boolean success;

    public ServicePortal(String serviceName, boolean success) { //todo Constructor for test
        this.serviceName = serviceName;
        this.success = success;
    }

    String getServiceName() {
        return serviceName;
    }
    boolean isSuccess() {
        return success;
    }

}
