CREATE TABLE EVENTS 
  (
    ID              BIGINT PRIMARY KEY AUTO_INCREMENT,
    CLIENT          VARCHAR2(255) NOT NULL,
    ENVIRONMENT     VARCHAR2(255) NOT NULL,
    DATUM           DATE, 
    "TYPE"          VARCHAR2(255), 
    BESCHREIBUNG    VARCHAR2(255)
  );
  
INSERT INTO EVENTS (CLIENT, ENVIRONMENT, DATUM, TYPE, BESCHREIBUNG) 
  values ('Coop', 'pjazz', parsedatetime('31.01.2015','DD.MM.YYYY'), 'ignore_days', '100% CPU');
INSERT INTO EVENTS (CLIENT, ENVIRONMENT, DATUM, TYPE, BESCHREIBUNG) 
  values ('Coop', 'pjazz', parsedatetime('05.01.2015','DD.MM.YYYY'), 'ignore_days', '100% CPU');
INSERT INTO EVENTS (CLIENT, ENVIRONMENT, DATUM, TYPE, BESCHREIBUNG) 
  values ('Coop', 'pjazz', parsedatetime('16.02.2015','DD.MM.YYYY'), 'ignore_days', '100% CPU');