package com.phollux.tuhome.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AnnouncementDTO {

    private Integer id;

    @NotNull
    @Size(max = 255)
    private String title;

    @NotNull
    private String description;

    @NotNull
    @Digits(integer = 12, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "75.08")
    private BigDecimal price;

    @NotNull
    @Size(max = 255)
    private String address;

    @NotNull
    private Integer bedrooms;

    @NotNull
    private Integer bathrooms;

    @NotNull
    @Digits(integer = 12, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "35.08")
    private BigDecimal squareFeet;

    @NotNull
    private String photos;

    @NotNull
    private Integer property;

}
