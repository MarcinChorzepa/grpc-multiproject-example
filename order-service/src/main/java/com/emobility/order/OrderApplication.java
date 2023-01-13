package com.emobility.order;

import com.emobility.proto.JsonResponse;
import com.emobility.proto.OrderResponse;
import com.emobility.proto.OrderType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderApplication {

  public static void main(String[] args) throws IOException {

    SpringApplication.run(OrderApplication.class, args);
//    String orderId = "orderId";
//    String userId = "userId";
//    long amount = 15L;
//    OrderResponse orderResponse = OrderResponse.newBuilder()
//        .setOrderId(orderId)
//        .setUserId(userId)
//        .setOrderType(OrderType.FREE)
////        .setAmount(amount)
//        .build();
//
//    System.out.println("Proto: " + orderResponse);
//    System.out.println("Proto order type: " + orderResponse.getOrderType());
//    System.out.println("Proto size: " + orderResponse.toByteArray().length);
//
//    JsonResponse jsonResponse = JsonResponse.builder()
//        .orderId(orderId)
//        .userId(userId)
//        .orderType(OrderType.FREE.name())
//        .amount(amount)
//        .build();
//
//    ObjectMapper objectMapper = new ObjectMapper();
//    System.out.println("JSON : " + jsonResponse);
//    System.out.println("JSON size: " + objectMapper.writeValueAsBytes(jsonResponse).length);
//
//    // performance
//
//    // PROTO runnable
//    Runnable proto = ()->{
//      try {
//        byte[] bytes = orderResponse.toByteArray();
//        OrderResponse.parseFrom(bytes);
//      } catch (InvalidProtocolBufferException e) {
//        throw new RuntimeException(e);
//      }
//    };
//    //Json runnable
//    Runnable json = () ->{
//      try {
//        byte[] bytes = objectMapper.writeValueAsBytes(jsonResponse);
//        objectMapper.readValue(bytes, JsonResponse.class);
//      } catch (Exception e) {
//        throw new RuntimeException(e);
//      }
//    };
//
//    // 5 try of conversions
////    for (int i = 0; i < 5; i++) {
////      testPerformance(proto,"PROTO");
////      testPerformance(json,"JSON");
////    }
//
//    Path v1 = Paths.get("v1.proto");
////    Files.write(v1, orderResponse.toByteArray());
//
//    OrderResponse read = OrderResponse.parseFrom(Files.readAllBytes(v1));
//    System.out.println("Readed : " + read);

  }

  private static void testPerformance(Runnable runnable, String name) {
    long start = System.currentTimeMillis();
    for (int i = 0; i < 1_000_000; i++) {
      runnable.run();

    }
    long totalTime = System.currentTimeMillis() - start;
    System.out.println(name + " time " + totalTime);

  }
}
