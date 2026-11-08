package com.garage.history;

import java.time.LocalDate;

import com.garage.vehicle.DataOrigin;
import com.garage.vehicle.Vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ServiceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // many records belong to one vehicle
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    // matches a row in the interval catalogue, e.g. OIL_CHANGE
    @Column(nullable = false)
    private String componentCode;

    @Column(nullable = false)
    private LocalDate serviceDate;

    @Column(nullable = false)
    private int mileageKm;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DataOrigin dataOrigin;

    protected ServiceRecord() {
    }

    public ServiceRecord(Vehicle vehicle, String componentCode, LocalDate serviceDate,
                         int mileageKm, DataOrigin dataOrigin) {
        this.vehicle = vehicle;
        this.componentCode = componentCode;
        this.serviceDate = serviceDate;
        this.mileageKm = mileageKm;
        this.dataOrigin = dataOrigin;
    }

    public Long getId() { return id; }
    public Vehicle getVehicle() { return vehicle; }
    public String getComponentCode() { return componentCode; }
    public LocalDate getServiceDate() { return serviceDate; }
    public int getMileageKm() { return mileageKm; }
    public DataOrigin getDataOrigin() { return dataOrigin; }
}