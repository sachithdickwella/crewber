package com.earcs.grabm.entity;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * @author Sachith Dickwella
 */
public class User implements Serializable {

    private static final long serialVersionUID = -2998663753049290235L;
    private static final String TAG = "User";

    private int id;
    private String name = "";
    private String email = "";
    private String imageUrl = "";
    private char status;
    private String loggedBy = "";
    private String token = "";

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public String getLoggedBy() {
        return loggedBy;
    }

    public void setLoggedBy(String loggedBy) {
        this.loggedBy = loggedBy;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        try {
            return new JSONObject()
                    .put("id", id)
                    .put("name", name)
                    .put("email", email)
                    .put("status", String.valueOf(status))
                    .put("loggedBy", loggedBy)
                    .put("token", token)
                    .toString();
        } catch (JSONException ex) {
            Log.e(TAG, ex.toString());
            return "";
        }
    }
}
