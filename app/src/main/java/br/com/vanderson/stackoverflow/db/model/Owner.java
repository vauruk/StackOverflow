package br.com.vanderson.stackoverflow.db.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vauruk on 24/03/16.
 */
public class Owner {

    @SerializedName("reputation")
    private Integer reputation;
    @SerializedName("user_id")
    private Integer user_id;
    @SerializedName("user_type")
    private String user_type;
    @SerializedName("profile_image")
    private String profile_image;
    @SerializedName("display_name")
    private String display_name;
    @SerializedName("link")
    private String link;


    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
