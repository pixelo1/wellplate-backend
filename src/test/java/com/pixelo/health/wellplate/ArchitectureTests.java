package com.pixelo.health.wellplate;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.jmolecules.archunit.JMoleculesArchitectureRules;
import org.jmolecules.archunit.JMoleculesDddRules;

@AnalyzeClasses(packagesOf = WellPlateApplication.class)
public class ArchitectureTests {

    @ArchTest
    ArchRule addRule = JMoleculesDddRules.all();

    // 레이어간 의존성 확인 - Simple로 import해야 인식한다
    @ArchTest
    ArchRule archRule = JMoleculesArchitectureRules.ensureOnionSimple();
}
