package com.phollux.tuhome.notification.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NotificationDTO {

    private Long id;

    @NotNull
    private String description;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    private Long userId;

}
