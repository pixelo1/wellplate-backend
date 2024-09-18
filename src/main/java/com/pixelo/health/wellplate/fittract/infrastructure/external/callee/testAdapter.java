package com.pixelo.health.wellplate.fittract.infrastructure.external.callee;

import com.pixelo.health.wellplate.core.spi.AuthUser;
import com.pixelo.health.wellplate.core.spi.ResultResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class testAdapter {

    @GetMapping
    public ResultResponse<?> test(
            @AuthenticationPrincipal
            AuthUser authUser
            ) {

        return ResultResponse.ok("test");
    }
}
