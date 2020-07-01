
package com.troology.birdapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatumGetStates {

    @SerializedName("sid")
    @Expose
    private String sid;
    @SerializedName("sname")
    @Expose
    private String sname;
    @SerializedName("is_delete")
    @Expose
    private String isDelete;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

}
