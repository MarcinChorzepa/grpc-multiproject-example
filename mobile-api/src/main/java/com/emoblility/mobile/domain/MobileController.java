package com.emoblility.mobile.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MobileController {

  private final MobileService mobileService;

  @GetMapping("full/{orderId}")
  public MobileApiResponse getOrderAndUserChargings(@PathVariable String orderId) {

    return mobileService.getOrderAndUserChargings(orderId);
  }
}
