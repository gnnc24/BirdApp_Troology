
package com.troology.birdapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PCThemeListModel {

    @SerializedName("image")
    @Expose
    private String image;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
       this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
