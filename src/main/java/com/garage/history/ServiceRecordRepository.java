package com.garage.history;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {

    List<ServiceRecord> findByVehicleIdOrderByServiceDateDesc(Long vehicleId);
}