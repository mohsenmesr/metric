package com.example.metric.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ImpRes {

  @JsonProperty("app_id")
  Integer appId;
  @JsonProperty("country_code")
  String cntCode;
  Number impressions;
  Number clicks;
  Number revenue;
}
