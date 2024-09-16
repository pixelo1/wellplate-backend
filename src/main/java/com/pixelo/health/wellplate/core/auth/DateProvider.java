package com.pixelo.health.wellplate.core.auth;

import java.util.Date;

public class DateProvider {

    private static Long systemCurrentMillis() {
        return System.currentTimeMillis();
    }
    public static Date currentTimeMillis() {
        return new Date(systemCurrentMillis());
    }

    public static Date currentTimeMillisPlusMillis(long millis) {
        return new Date(systemCurrentMillis() + millis);
    }
}
