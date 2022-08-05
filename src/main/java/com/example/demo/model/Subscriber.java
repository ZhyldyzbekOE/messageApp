package com.example.demo.model;

public class Subscriber {
    private int id;
    private boolean isBlocked;
    private String phone;

    public Subscriber() {}

    public Subscriber(int id, boolean isBlocked, String phone) {
        this.id = id;
        this.isBlocked = isBlocked;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return phone ;
    }


}
