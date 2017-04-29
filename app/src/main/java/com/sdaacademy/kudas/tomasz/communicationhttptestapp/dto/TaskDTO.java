package com.sdaacademy.kudas.tomasz.communicationhttptestapp.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by RENT on 2017-04-29.
 */

public class TaskDTO {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("user")
    @Expose
    private long user;

    @SerializedName("completed")
    @Expose
    private boolean completed;

    @SerializedName("value")
    @Expose
    private String value;

    public TaskDTO(long id, long user, boolean completed, String value) {
        this.id = id;
        this.user = user;
        this.completed = completed;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
