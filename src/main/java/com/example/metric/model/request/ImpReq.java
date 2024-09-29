package com.example.metric.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImpReq {

  String id;
  @JsonProperty("app_id")
  Integer appId;
  @JsonProperty("country_code")
  String cntCode;
  @JsonProperty("advertiser_id")
  Integer advId;
}
