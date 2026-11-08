package com.garage.catalogue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ServiceInterval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // which cars this applies to, e.g. an engine code like CSUA
    @Column(nullable = false)
    private String profileKey;

    // matches ServiceRecord.componentCode, e.g. OIL_CHANGE
    @Column(nullable = false)
    private String componentCode;

    @Column(nullable = false)
    private String componentName;

    // either can be null, but not both (checked in the constructor)
    private Integer everyKm;
    private Integer everyMonths;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Severity severity;

    // where the number came from, e.g. Audi service book
    @Column(nullable = false)
    private String source;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Confidence confidence;

    protected ServiceInterval() {
    }

    public ServiceInterval(String profileKey, String componentCode, String componentName,
                           Integer everyKm, Integer everyMonths, Severity severity,
                           String source, Confidence confidence) {
        if (everyKm == null && everyMonths == null) {
            throw new IllegalArgumentException("An interval needs a distance, a time, or both");
        }
        if ((everyKm != null && everyKm <= 0) || (everyMonths != null && everyMonths <= 0)) {
            throw new IllegalArgumentException("Intervals must be positive");
        }
        this.profileKey = profileKey;
        this.componentCode = componentCode;
        this.componentName = componentName;
        this.everyKm = everyKm;
        this.everyMonths = everyMonths;
        this.severity = severity;
        this.source = source;
        this.confidence = confidence;
    }

    public Long getId() { return id; }
    public String getProfileKey() { return profileKey; }
    public String getComponentCode() { return componentCode; }
    public String getComponentName() { return componentName; }
    public Integer getEveryKm() { return everyKm; }
    public Integer getEveryMonths() { return everyMonths; }
    public Severity getSeverity() { return severity; }
    public String getSource() { return source; }
    public Confidence getConfidence() { return confidence; }
}