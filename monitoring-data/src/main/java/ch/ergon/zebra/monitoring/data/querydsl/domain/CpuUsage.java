package ch.ergon.zebra.monitoring.data.querydsl.domain;

import javax.annotation.Generated;
import ch.ergon.zebra.monitoring.data.measurement.Measurement;

/**
 * CpuUsage is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class CpuUsage implements Measurement {

    private String client;

    private java.math.BigDecimal cpuUsage;

    private String environment;

    private String hash;

    private java.math.BigInteger id;

    private java.sql.Timestamp sysDatetime;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public java.math.BigDecimal getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(java.math.BigDecimal cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
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

}

