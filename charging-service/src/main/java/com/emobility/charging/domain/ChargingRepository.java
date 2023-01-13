package com.emobility.charging.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargingRepository extends JpaRepository<ChargingEntity,String> {

  List<ChargingEntity> findByUserId(String userId);
}
