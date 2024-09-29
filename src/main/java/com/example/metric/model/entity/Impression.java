package com.example.metric.model.entity;

import com.example.metric.model.request.ImpReq;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@ToString
@DynamicInsert
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Impression {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String uid;
  String cntCode;
  Integer advId;
  Integer appId;
  @CreationTimestamp
  LocalDateTime createdOn;

  public static Impression fromRequest(ImpReq impReq) {
    Impression impression = new Impression();
    impression.setUid(impReq.getId());
    impression.setCntCode(impReq.getCntCode());
    impression.setAdvId(impReq.getAdvId());
    impression.setAppId(impReq.getAppId());
    return impression;
  }
}
