package com.pixelo.health.wellplate.fittract.infrastructure.external.callee;

import com.pixelo.health.wellplate.core.spi.ResultResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class testAdapter {

    @GetMapping
    public ResultResponse<?> test() {

        return ResultResponse.ok("test");
    }
}
