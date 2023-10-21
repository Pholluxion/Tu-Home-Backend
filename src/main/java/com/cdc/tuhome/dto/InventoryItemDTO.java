package com.cdc.tuhome.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class InventoryItemDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2171209440909561971L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("inventory")
    private InventoryDTO inventory;

    @JsonProperty("itemname")
    private String itemname;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("value")
    private Double value;
}
