package com.pixelo.health.wellplate.myhealth.applidation.service.diet;

import com.pixelo.health.wellplate.myhealth.applidation.in.command.diet.DietCommandInputPort;
import com.pixelo.health.wellplate.myhealth.applidation.out.DietOutputPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class DietCommandService implements DietCommandInputPort {

    private final DietOutputPort dietOutputPort;

    @Override
    public void test() {
        dietOutputPort.test();
    }
}
