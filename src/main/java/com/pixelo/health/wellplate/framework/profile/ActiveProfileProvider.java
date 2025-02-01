package com.pixelo.health.wellplate.framework.profile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ActiveProfileProvider {

    private static final String LOCAL = "local";
    private final String activeProfile;

    public ActiveProfileProvider(@Value("default") String activeProfile) {
        this.activeProfile = activeProfile;
    }

    public boolean isLocal() {
        return activeProfile.equals(LOCAL);
    }
}
