package com.cdc.tuhome.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class AnnouncementsDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -250931632055621839L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("sendingDate")
    private Date sendingDate;

    @JsonProperty("description")
    private String description;

    @JsonProperty("sender")
    private UsersDTO sender;

    @JsonProperty("receiver")
    private UsersDTO receiver;
}
