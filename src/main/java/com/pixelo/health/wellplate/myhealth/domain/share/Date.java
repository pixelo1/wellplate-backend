package com.pixelo.health.wellplate.myhealth.domain.share;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Date {
    private LocalDate date;

    @Builder
    public Date(LocalDate date) {
        this.date = date;
    }
}
