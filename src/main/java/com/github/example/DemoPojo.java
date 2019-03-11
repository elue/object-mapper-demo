package com.github.example;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@JsonDeserialize
public class DemoPojo implements Serializable {

    private OffsetDateTime offsetDateTime;

}
