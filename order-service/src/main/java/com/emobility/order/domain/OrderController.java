package com.emobility.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderController {

  private final OrderRepository orderRepository;

  @GetMapping("order/{orderId}")
  public OrderEntity getOrderById(@PathVariable String orderId) {
    return orderRepository.findById(orderId).get();
  }

}
