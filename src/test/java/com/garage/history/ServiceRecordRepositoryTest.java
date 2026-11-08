package com.garage.history;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.garage.vehicle.DataOrigin;
import com.garage.vehicle.FuelType;
import com.garage.vehicle.TransmissionType;
import com.garage.vehicle.Vehicle;
import com.garage.vehicle.VehicleRepository;

@SpringBootTest
@Transactional
class ServiceRecordRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ServiceRecordRepository serviceRecordRepository;

    @Test
    void returnsRecordsNewestFirst() {
        Vehicle vehicle = vehicleRepository.save(new Vehicle("WAUZZZ8T0GA000002", "Audi", "A5",
                2016, "CSUA", "PNE", FuelType.DIESEL, TransmissionType.MANUAL,
                172000, DataOrigin.SAMPLE));

        serviceRecordRepository.save(new ServiceRecord(vehicle, "OIL_CHANGE",
                LocalDate.of(2025, 1, 10), 160000, DataOrigin.SAMPLE));
        serviceRecordRepository.save(new ServiceRecord(vehicle, "OIL_CHANGE",
                LocalDate.of(2026, 1, 26), 172000, DataOrigin.SAMPLE));

        List<ServiceRecord> records =
                serviceRecordRepository.findByVehicleIdOrderByServiceDateDesc(vehicle.getId());

        assertEquals(2, records.size());
        assertEquals(172000, records.get(0).getMileageKm());
    }
}