package com.bitbucket.util.start;

import com.bitbucket.util.screen.UserInformation;

/**
 *
 * @author Ram Mishra
 */
public class StartIt {

    public static void main(String[] args) {

        UserInformation bitBucketUserInfo = new UserInformation();
        bitBucketUserInfo.build(new String[]{});
    }
}
