CREATE TABLE MEMORY_USAGE
  (
    ID              NUMBER PRIMARY KEY,
    CLIENT          VARCHAR(255) NOT NULL,
    ENVIRONMENT     VARCHAR(255) NOT NULL,
    SOURCE_FILENAME VARCHAR(255) NOT NULL,
    SYS_DATETIME    TIMESTAMP,
    MEMORY_USAGE    NUMBER(*,2)
  );
  
CREATE sequence S_MEMORY_USAGE start with 1 increment BY 1 nomaxvalue;
CREATE TRIGGER T_MEMORY_USAGE before
  INSERT ON memory_usage FOR EACH row BEGIN
  SELECT S_MEMORY_USAGE.nextval INTO :new.id FROM dual;
END;
/