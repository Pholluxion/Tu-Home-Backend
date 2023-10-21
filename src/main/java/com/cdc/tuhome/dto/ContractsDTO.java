package com.cdc.tuhome.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ContractsDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -5297717779048474119L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("userId")
    private UsersDTO userId;

    @JsonProperty("property")
    private PropertiesDTO property;

    @JsonProperty("startdate")
    private Date startdate;

    @JsonProperty("enddate")
    private Date enddate;

    @JsonProperty("rentalamount")
    private Double rentalamount;

    @JsonProperty("includeservices")
    private Boolean includeservices;

    @JsonProperty("includeadmin")
    private Boolean includeadmin;

    @JsonProperty("serviceamount")
    private Double serviceamount;

    @JsonProperty("admineamount")
    private Double admineamount;
}
