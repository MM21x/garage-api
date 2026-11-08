package com.garage.vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void savesAndFindsVehicleByVin() {
        Vehicle vehicle = new Vehicle("WAUZZZ8T0GA000001", "Audi", "A5", 2016,
                "CSUA", "PNE", FuelType.DIESEL, TransmissionType.MANUAL,
                178500, DataOrigin.REAL);
        vehicleRepository.save(vehicle);

        Vehicle found = vehicleRepository.findByVin("WAUZZZ8T0GA000001").orElseThrow();

        assertEquals("CSUA", found.getEngineCode());
        assertEquals("PNE", found.getGearboxCode());
        assertEquals(FuelType.DIESEL, found.getFuelType());
        assertEquals(178500, found.getCurrentMileageKm());
    }

    @Test
    void returnsEmptyForUnknownVin() {
        assertTrue(vehicleRepository.findByVin("WAUZZZ8T0GA999999").isEmpty());
    }
}