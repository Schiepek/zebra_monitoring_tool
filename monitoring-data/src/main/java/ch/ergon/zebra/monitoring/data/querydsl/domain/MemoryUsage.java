package ch.ergon.zebra.monitoring.data.querydsl.domain;

import javax.annotation.Generated;
import ch.ergon.zebra.monitoring.data.measurement.Measurement;

/**
 * MemoryUsage is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class MemoryUsage implements Measurement {

    private String client;

    private String environment;

    private java.math.BigDecimal freeHeap;

    private String hash;

    private java.math.BigInteger id;

    private java.sql.Timestamp sysDatetime;

    private java.math.BigDecimal totalHeap;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public java.math.BigDecimal getFreeHeap() {
        return freeHeap;
    }

    public void setFreeHeap(java.math.BigDecimal freeHeap) {
        this.freeHeap = freeHeap;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public java.math.BigInteger getId() {
        return id;
    }

    public void setId(java.math.BigInteger id) {
        this.id = id;
    }

    public java.sql.Timestamp getSysDatetime() {
        return sysDatetime;
    }

    public void setSysDatetime(java.sql.Timestamp sysDatetime) {
        this.sysDatetime = sysDatetime;
    }

    public java.math.BigDecimal getTotalHeap() {
        return totalHeap;
    }

    public void setTotalHeap(java.math.BigDecimal totalHeap) {
        this.totalHeap = totalHeap;
    }

}

