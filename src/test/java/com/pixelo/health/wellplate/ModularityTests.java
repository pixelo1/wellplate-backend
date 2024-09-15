package com.pixelo.health.wellplate;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
//bc 간의 의존성 테스트
public class ModularityTests {

    ApplicationModules modules = ApplicationModules.of(WellPlateApplication.class);

    @Test
    void verifyModularity() {
        modules.forEach(System.out::println);
        ApplicationModules.of(WellPlateApplication.class).verify();
    }
    @Test
    void renderDocumentation() {
        var canvasOptions = Documenter.CanvasOptions.defaults();
        var docOptions = Documenter.DiagramOptions.defaults()
                .withStyle(Documenter.DiagramOptions.DiagramStyle.UML);
        new Documenter(modules)
                .writeDocumentation(docOptions, canvasOptions);
    }
}
