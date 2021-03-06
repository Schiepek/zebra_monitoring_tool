CREATE TABLE IMPORTED_FILE
  (
    ID              BIGINT PRIMARY KEY AUTO_INCREMENT,
    CLIENT          VARCHAR(255) NOT NULL,
    ENVIRONMENT     VARCHAR(255) NOT NULL,
    STATUS          VARCHAR(255),
    IMPORT_DATETIME TIMESTAMP,
    FILENAME        VARCHAR(255) NOT NULL,
    STACKTRACE		CLOB,
    CONSTRAINT UNIQUE_FILE UNIQUE (CLIENT, ENVIRONMENT, FILENAME)
  );