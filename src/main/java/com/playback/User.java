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
    Account account;
    String homeCountry;
    int streamInUse;

    public User(int id, String userName, Account account, String homeCountry) {
        this.id = pid++;
        this.userName = userName;
        this.account = account;
        this.homeCountry = homeCountry;
        this.status = Status.ACTIVE;
        this.streamInUse = 0;
    }

    public void addStream() {

    }
}
