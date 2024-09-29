package com.example.metric.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecommendRes {

  @JsonProperty("app_id")
  Integer appId;
  @JsonProperty("country_code")
  String cntCode;
  @JsonProperty("recommended_advertiser_ids")
  Collection<Integer> advIds;

  public static RecommendRes fromRequest(AdvRes req) {
    RecommendRes res = new RecommendRes();
    res.setCntCode(req.getCntCode());
    res.setAppId(req.getAppId());
    res.setAdvIds(Optional.ofNullable(req.getAdvIds())
        .map(adv -> adv.split(","))
        .map(Arrays::asList)
        .map(adv -> adv.stream()
            .filter(Objects::nonNull)
            .map(Integer::parseInt)
            .collect(Collectors.toList()))
        .orElse(Collections.emptyList()));
    return res;
  }
}
