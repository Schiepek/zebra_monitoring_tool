CREATE TABLE EVENTS 
  (
    ID              NUMBER PRIMARY KEY,
    CLIENT          VARCHAR2(255) NOT NULL,
    ENVIRONMENT     VARCHAR2(255) NOT NULL,
    DATUM           DATE, 
    "TYPE"          VARCHAR2(255), 
    BESCHREIBUNG    VARCHAR2(255)
  );
  
CREATE sequence S_EVENTS start with 1 increment BY 1 nomaxvalue;
CREATE TRIGGER T_EVENTS before
  INSERT ON EVENTS FOR EACH row BEGIN
  SELECT S_EVENTS.nextval INTO :new.id FROM dual;
END;
/

INSERT INTO EVENTS (CLIENT, ENVIRONMENT, DATUM, TYPE, BESCHREIBUNG) 
  values ('Coop', 'pjazz', to_date('31.01.2015','DD.MM.YYYY'), 'ignore_days', '100% CPU');
INSERT INTO EVENTS (CLIENT, ENVIRONMENT, DATUM, TYPE, BESCHREIBUNG) 
  values ('Coop', 'pjazz', to_date('05.01.2015','DD.MM.YYYY'), 'ignore_days', '100% CPU');
INSERT INTO EVENTS (CLIENT, ENVIRONMENT, DATUM, TYPE, BESCHREIBUNG) 
  values ('Coop', 'pjazz', to_date('16.02.2015','DD.MM.YYYY'), 'ignore_days', '100% CPU');