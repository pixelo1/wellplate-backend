package com.pixelo.health.wellplate.framework.spi;

import java.util.UUID;

public interface UserService {

    JwtUserDetails findUserById(UUID memberId);
}
