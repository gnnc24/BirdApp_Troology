
package com.troology.birdapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetStates {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<DatumGetStates> datumGetStates = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DatumGetStates> getDatumGetStates() {
        return datumGetStates;
    }

    public void setDatumGetStates(List<DatumGetStates> datumGetStates) {
        this.datumGetStates = datumGetStates;
    }
}
