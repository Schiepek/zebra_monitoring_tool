package ch.ergon.zebra.monitoring.data.querydsl.domain;

import javax.annotation.Generated;

/**
 * ImportedFile is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class ImportedFile {

    private String client;

    private String environment;

    private String filename;

    private java.math.BigInteger id;

    private java.sql.Timestamp importDatetime;

    private String stacktrace;

    private String status;

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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public java.math.BigInteger getId() {
        return id;
    }

    public void setId(java.math.BigInteger id) {
        this.id = id;
    }

    public java.sql.Timestamp getImportDatetime() {
        return importDatetime;
    }

    public void setImportDatetime(java.sql.Timestamp importDatetime) {
        this.importDatetime = importDatetime;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

