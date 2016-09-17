package com.topv1.cyz.colorbook;

/**
 * Created by Administrator on 2016/9/10.
 */
public class UserInfo {
    private static boolean mHasLogin = false;

    public static boolean HasLogin() {
        return mHasLogin;
    }

    public static void setmHasLogin(boolean mHasLogin) {
        UserInfo.mHasLogin = mHasLogin;
    }

}
