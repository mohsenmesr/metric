package com.example.metric.model.entity;

import com.example.metric.model.request.ClickReq;
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
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@ToString
@DynamicInsert
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Click {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  String impId;
  Double revenue;
  @CreationTimestamp
  LocalDateTime createdOn;

  public static Click fromRequest(ClickReq req) {
    Click click = new Click();
    click.setImpId(req.getImpId());
    click.setRevenue(req.getRevenue());
    return click;
  }
}
