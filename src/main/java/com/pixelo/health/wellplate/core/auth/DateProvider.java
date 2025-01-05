package com.pixelo.health.wellplate.core.auth;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateProvider {

    private Long systemCurrentMillis() {
        return System.currentTimeMillis();
    }
    public Date currentTimeMillis() {
        return new Date(systemCurrentMillis());
    }

    public Date currentTimeMillisPlusMillis(long millis) {
        return new Date(systemCurrentMillis() + millis);
    }
}
