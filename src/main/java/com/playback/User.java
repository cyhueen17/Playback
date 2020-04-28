package com.playback;

/**
 * User Resourse.
 *
 */

public class User {
    enum Status {
        ACTIVE, INACTIVE;
    }

    static int pid=0;
    int id;
    String userName;
    Status status;
    String accountType;
    String homeCountry;
    int streamInUse;

    public User(int id, String userName, String accountType, String homeCountry) {
        this.id = pid++;
        this.userName = userName;
        this.accountType = accountType;
        this.homeCountry = homeCountry;
        this.status = Status.INACTIVE;
        this.streamInUse = 0;
    }

    public void addStream() {
        streamInUse++;
    }

    public String getUserName() { return userName; }

    public String getHomeCountry() {
        return homeCountry;
    }

    public Status getStatus() {
        return status;
    }

    public int getStreamInUse() {
        return streamInUse;
    }

    public String getAccountType() {
        return accountType;
    }

    public void activateUser() { status = Status.ACTIVE; }

    public boolean isActive() {
        return status == Status.ACTIVE;
    }
}
