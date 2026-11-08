package com.garage.vehicle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 17)
    private String vin;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int modelYear;

    @Column(nullable = false)
    private String engineCode;

    // can be empty, not every car's gearbox code is known
    private String gearboxCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransmissionType transmissionType;

    @Column(nullable = false)
    private int currentMileageKm;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DataOrigin dataOrigin;

    // JPA needs an empty constructor to build objects from database rows
    protected Vehicle() {
    }

    public Vehicle(String vin, String make, String model, int modelYear,
                   String engineCode, String gearboxCode, FuelType fuelType,
                   TransmissionType transmissionType, int currentMileageKm,
                   DataOrigin dataOrigin) {
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.modelYear = modelYear;
        this.engineCode = engineCode;
        this.gearboxCode = gearboxCode;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        this.currentMileageKm = currentMileageKm;
        this.dataOrigin = dataOrigin;
    }

    public Long getId() { return id; }
    public String getVin() { return vin; }
    public String getMake() { return make; }
    public String getModel() { return model; }
    public int getModelYear() { return modelYear; }
    public String getEngineCode() { return engineCode; }
    public String getGearboxCode() { return gearboxCode; }
    public FuelType getFuelType() { return fuelType; }
    public TransmissionType getTransmissionType() { return transmissionType; }
    public int getCurrentMileageKm() { return currentMileageKm; }
    public DataOrigin getDataOrigin() { return dataOrigin; }

    public void setGearboxCode(String gearboxCode) { this.gearboxCode = gearboxCode; }
    public void setCurrentMileageKm(int currentMileageKm) { this.currentMileageKm = currentMileageKm; }
}