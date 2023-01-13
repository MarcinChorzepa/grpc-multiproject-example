package com.emobility.proto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResponse {

  private String orderId;
  private String userId;
  private String orderType;
  private Long amount;

}
