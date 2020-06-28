package com.bitbucket.util.screen;

/**
 *
 * @author Ram Mishra
 */
public class PullRequestInformation {

    private final String userName;

    private final String password;

    private final String toBranch;

    private final String fromBranch;

    private final String reviewer;

    public PullRequestInformation(String userName, String password,
            String toBranch, String fromBranch, String reviewer) {
        this.userName = userName;
        this.password = password;
        this.toBranch = toBranch;
        this.fromBranch = fromBranch;
        this.reviewer = reviewer;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getToBranch() {
        return toBranch;
    }

    public String getFromBranch() {
        return fromBranch;
    }

}
