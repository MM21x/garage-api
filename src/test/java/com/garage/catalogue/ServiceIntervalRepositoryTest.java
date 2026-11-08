package com.garage.catalogue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ServiceIntervalRepositoryTest {

    @Autowired
    private ServiceIntervalRepository repository;

    @Test
    void findsIntervalsByProfileKey() {
        repository.save(new ServiceInterval("TEST", "OIL_CHANGE", "Oil and filter",
                15000, 12, Severity.RELIABILITY, "Service book", Confidence.HIGH));
        repository.save(new ServiceInterval("TEST", "AIR_FILTER", "Air filter",
                90000, null, Severity.COMFORT, "Service book", Confidence.HIGH));
        repository.save(new ServiceInterval("OTHER", "OIL_CHANGE", "Oil and filter",
                10000, 12, Severity.RELIABILITY, "Sample", Confidence.LOW));

        List<ServiceInterval> found = repository.findByProfileKey("TEST");

        assertEquals(2, found.size());
    }

    @Test
    void rejectsIntervalWithNoDistanceAndNoTime() {
        assertThrows(IllegalArgumentException.class, () ->
                new ServiceInterval("TEST", "X", "Nothing", null, null,
                        Severity.COMFORT, "Sample", Confidence.LOW));
    }

    @Test
    void rejectsNegativeDistance() {
        assertThrows(IllegalArgumentException.class, () ->
                new ServiceInterval("TEST", "X", "Bad", -5, null,
                        Severity.COMFORT, "Sample", Confidence.LOW));
    }
}