package com.pixelo.health.wellplate.myhealth.application.vo.diet;

import lombok.Builder;

import java.util.List;

@Builder
public record DietSummariesDto(
        List<DietVo> dietVos
) {
}
