package com.cdc.tuhome.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class InventoryDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5177846822445552790L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("property")
    private PropertiesDTO property;
}
