CREATE TABLE CPU_USAGE
  (
    ID              BIGINT PRIMARY KEY AUTO_INCREMENT,
    CLIENT          VARCHAR(255) NOT NULL,
    ENVIRONMENT     VARCHAR(255) NOT NULL,
    SYS_DATETIME    TIMESTAMP,
    CPU_USAGE       DECIMAL(65535,2),
    HASH 			CHAR(64)
  );