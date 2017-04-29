package com.sdaacademy.kudas.tomasz.communicationhttptestapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by RENT on 2017-04-29.
 */

public class Task {

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("completed")
    @Expose
    private boolean completed;

    @SerializedName("value")
    @Expose
    private String value;

    public Task() {

    }



    public Task(long id, boolean completed, String value) {
        this.id = id;
        this.completed = completed;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
