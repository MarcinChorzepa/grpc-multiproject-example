package com.emoblility.mobile.domain;

import com.emobility.proto.ChargingResponse;
import com.emobility.proto.ChargingResponses;
import com.emobility.proto.OrderResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MobileApiResponse {

  private String orderId;
  private String userId;
  private String orderType;
  private Long amount;
  private List<Charging> chargingList;

  public static MobileApiResponse from(OrderResponse orderResponse, ChargingResponses chargingListResponse) {
    List<Charging> chargings = chargingListResponse.getChargingListList().stream().map(Charging::from).toList();
    return MobileApiResponse.builder()
        .orderId(orderResponse.getOrderId())
        .userId(orderResponse.getUserId())
        .amount(orderResponse.getAmount())
        .orderType(orderResponse.getOrderType().name())
        .chargingList(chargings)
        .build();
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  @Builder
  private static class Charging {
    private String chargingId;
    private String chargingType;
    private Long kWh;

    private static Charging from(ChargingResponse chargingResponse) {
      return Charging.builder()
          .chargingId(chargingResponse.getChargingId())
          .chargingType(chargingResponse.getChargingType().name())
          .kWh(chargingResponse.getKWh())
          .build();
    }
  }
}
