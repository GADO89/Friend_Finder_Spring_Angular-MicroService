package com.user.management.model.bundle;

import lombok.AllArgsConstructor;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
public class BundleErrorMessage {

    @JsonProperty("arabicMessage")
    private String arabicMessage;

    @JsonProperty("englishMessage")
    private String englishMessage;

}
