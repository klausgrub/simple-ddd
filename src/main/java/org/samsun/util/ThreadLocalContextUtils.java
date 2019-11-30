package org.samsun.util;

/**
 * user's variable in current thread
 *
 * @author sunmin
 * @version 1.0.0
 * @createTime 2019年10月21日
 */
public class ThreadLocalContextUtils {

    private static final InheritableThreadLocal<String> LOCAL_USER = new InheritableThreadLocal<>();

    /**
     * set user into current thread
     *
     * @param user
     */
    public static void setUser(String user) {
        LOCAL_USER.set(user);
    }

    /**
     * remove user from current thread
     */
    public static void removeUser() {
        LOCAL_USER.remove();
    }

    /**
     * get User from current thread
     *
     * @return
     */
    public static String getUser() {
        return LOCAL_USER.get();
    }


}
