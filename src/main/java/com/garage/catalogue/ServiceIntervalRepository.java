package com.garage.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceIntervalRepository extends JpaRepository<ServiceInterval, Long> {

    List<ServiceInterval> findByProfileKey(String profileKey);
}