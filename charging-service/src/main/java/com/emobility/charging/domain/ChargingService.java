package com.emobility.charging.domain;

import com.emobility.proto.AddChargingRequest;
import com.emobility.proto.ChargingRequest;
import com.emobility.proto.ChargingResponse;
import com.emobility.proto.ChargingResponses;
import com.emobility.proto.ChargingServiceGrpc.ChargingServiceImplBase;
import com.emobility.proto.ChargingType;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class ChargingService extends ChargingServiceImplBase {


  private final ChargingRepository chargingRepository;

  @Override
  public void getChargingByUserId(ChargingRequest request, StreamObserver<ChargingResponses> responseObserver) {
    List<ChargingResponse> chargingResponses = chargingRepository.findByUserId(request.getUserId()).stream().map(chargingEntity -> ChargingResponse.newBuilder()
        .setUserId(chargingEntity.getUserId())
        .setChargingId(chargingEntity.getId())
        .setChargingType(ChargingType.valueOf(chargingEntity.getChargingType()))
        .setKWh(chargingEntity.getKWh())
        .build()
    ).toList();
    responseObserver.onNext(ChargingResponses.newBuilder().addAllChargingList(chargingResponses).build());
    responseObserver.onCompleted();
  }

  @Override
  public void getChargingStreamByUserId(ChargingRequest request, StreamObserver<ChargingResponse> responseObserver) {

    chargingRepository.findByUserId(request.getUserId()).forEach(chargingEntity -> {
      ChargingResponse response = ChargingResponse.newBuilder()
          .setUserId(chargingEntity.getUserId())
          .setChargingId(chargingEntity.getId())
          .setChargingType(ChargingType.valueOf(chargingEntity.getChargingType()))
          .setKWh(chargingEntity.getKWh())
          .build();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      responseObserver.onNext(response);
    });
    super.getChargingStreamByUserId(request, responseObserver);
  }

  @Override
  public StreamObserver<AddChargingRequest> addCharging(StreamObserver<ChargingResponses> responseObserver) {
    StreamObserver<AddChargingRequest> streamObserver = new StreamObserver<AddChargingRequest>() {

      List<ChargingEntity> chargingEntities = new ArrayList<>();
      @Override
      public void onNext(AddChargingRequest value) {
        chargingEntities.add(ChargingEntity.builder()
                .id(UUID.randomUUID().toString())
                .chargingType(value.getChargingType().name())
                .kWh(value.getKWh())
                .userId(value.getUserId())
            .build());

      }

      @Override
      public void onError(Throwable t) {

      }

      @Override
      public void onCompleted() {
        chargingRepository.saveAll(chargingEntities);
        List<ChargingResponse> chargingResponses = chargingRepository.findAll().stream().map(chargingEntity -> ChargingResponse.newBuilder()
            .setUserId(chargingEntity.getUserId())
            .setChargingId(chargingEntity.getId())
            .setChargingType(ChargingType.valueOf(chargingEntity.getChargingType()))
            .setKWh(chargingEntity.getKWh())
            .build()
        ).toList();
        responseObserver.onNext(ChargingResponses.newBuilder().addAllChargingList(chargingResponses).build());
        responseObserver.onCompleted();
      }
    };
    return streamObserver;
  }

}
