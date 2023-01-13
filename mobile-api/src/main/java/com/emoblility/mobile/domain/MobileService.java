package com.emoblility.mobile.domain;

import com.emobility.proto.ChargingRequest;
import com.emobility.proto.ChargingResponses;
import com.emobility.proto.ChargingServiceGrpc.ChargingServiceBlockingStub;
import com.emobility.proto.OrderRequest;
import com.emobility.proto.OrderResponse;
import com.emobility.proto.OrderServiceGrpc.OrderServiceBlockingStub;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class MobileService {

  @GrpcClient("order-service")
  private OrderServiceBlockingStub orderServiceBlockingStub;

  @GrpcClient("charging-service")
  private ChargingServiceBlockingStub chargingServiceBlockingStub;


  public MobileApiResponse getOrderAndUserChargings(String orderId) {
    OrderResponse orderResponse = orderServiceBlockingStub.getOrderById(OrderRequest.newBuilder().setOrderId(orderId).build());
    ChargingResponses chargingResponses = chargingServiceBlockingStub.getChargingByUserId(
        ChargingRequest.newBuilder().setUserId(orderResponse.getUserId()).build());

    return MobileApiResponse.from(orderResponse, chargingResponses);

  }
}
