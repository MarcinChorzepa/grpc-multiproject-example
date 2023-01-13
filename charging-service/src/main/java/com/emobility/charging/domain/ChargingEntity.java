package com.emobility.charging.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="charging")
public class ChargingEntity {
  @Id
  private String id;
  private String userId;
  private String chargingType;
  @Column(name = "kwh")
  private Long kWh;

}
