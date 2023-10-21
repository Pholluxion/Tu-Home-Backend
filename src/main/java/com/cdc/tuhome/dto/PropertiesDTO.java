package com.cdc.tuhome.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class PropertiesDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 8436618758694784430L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("address")
    private String address;

    @JsonProperty("propertyType")
    private String propertyType;

    @JsonProperty("description")
    private String description;

    @JsonProperty("availabilityDate")
    private Date availabilityDate;

    @JsonProperty("availabilityStatus")
    private Boolean availabilityStatus;
}
