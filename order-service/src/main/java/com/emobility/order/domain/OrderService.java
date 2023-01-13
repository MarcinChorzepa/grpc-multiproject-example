package com.emobility.order.domain;

import com.emobility.proto.OrderRequest;
import com.emobility.proto.OrderResponse;
import com.emobility.proto.OrderResponse.Builder;
import com.emobility.proto.OrderServiceGrpc.OrderServiceImplBase;
import com.emobility.proto.OrderType;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class OrderService extends OrderServiceImplBase {

  private final OrderRepository orderRepository;

  @Override
  public void getOrderById(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
    Builder builder = OrderResponse.newBuilder();
    orderRepository.findById(request.getOrderId()).ifPresent(orderEntity -> builder
        .setAmount(orderEntity.getAmount())
        .setOrderType(OrderType.valueOf(orderEntity.getType()))
        .setUserId(orderEntity.getUserId())
        .setOrderId(orderEntity.getId())
    );
    responseObserver.onNext(builder.build());
    responseObserver.onCompleted();
  }
}
