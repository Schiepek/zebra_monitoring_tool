package ch.ergon.zebra.monitoring.data.querydsl.metadata;

import static com.mysema.query.types.PathMetadataFactory.*;
import ch.ergon.zebra.monitoring.data.querydsl.domain.Profiling;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QProfiling is a Querydsl query type for Profiling
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QProfiling extends com.mysema.query.sql.RelationalPathBase<Profiling> {

    private static final long serialVersionUID = 990010481;

    public static final QProfiling profiling = new QProfiling("PROFILING");

    public final NumberPath<java.math.BigDecimal> actionDateIntevalDays = createNumber("actionDateIntevalDays", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> activeSessions1 = createNumber("activeSessions1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> activeSessions5 = createNumber("activeSessions5", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> buchungslaufDauerMs = createNumber("buchungslaufDauerMs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> buchungslaufTage = createNumber("buchungslaufTage", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> bwDauerMs = createNumber("bwDauerMs", java.math.BigDecimal.class);

    public final StringPath bwMode = createString("bwMode");

    public final StringPath client = createString("client");

    public final StringPath clientIpAddress = createString("clientIpAddress");

    public final NumberPath<java.math.BigDecimal> connectDuration = createNumber("connectDuration", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> connectEnd = createNumber("connectEnd", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> connectStart = createNumber("connectStart", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> domainLookupDuration = createNumber("domainLookupDuration", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> domainLookupEnd = createNumber("domainLookupEnd", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> domainLookupStart = createNumber("domainLookupStart", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> domCompleteDuration = createNumber("domCompleteDuration", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> domContentLoadedEvent = createNumber("domContentLoadedEvent", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> domContentLoadedEventEnd = createNumber("domContentLoadedEventEnd", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> domContentLoadedEventStart = createNumber("domContentLoadedEventStart", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> domInteractive = createNumber("domInteractive", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> domLoading = createNumber("domLoading", java.math.BigDecimal.class);

    public final StringPath environment = createString("environment");

    public final NumberPath<java.math.BigDecimal> fetchStart = createNumber("fetchStart", java.math.BigDecimal.class);

    public final DateTimePath<java.sql.Timestamp> focusDate = createDateTime("focusDate", java.sql.Timestamp.class);

    public final NumberPath<java.math.BigDecimal> focusDateMinusSystemdate = createNumber("focusDateMinusSystemdate", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> focusKstNrOfMitarbeiter = createNumber("focusKstNrOfMitarbeiter", java.math.BigDecimal.class);

    public final StringPath focusKstNummer = createString("focusKstNummer");

    public final NumberPath<Byte> forcePlanungNeurechnen = createNumber("forcePlanungNeurechnen", Byte.class);

    public final StringPath hash = createString("hash");

    public final StringPath host = createString("host");

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final StringPath javaVersion = createString("javaVersion");

    public final NumberPath<java.math.BigDecimal> kontolaufDauerMs = createNumber("kontolaufDauerMs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> kontolaufIterationen = createNumber("kontolaufIterationen", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> kontolaufTage = createNumber("kontolaufTage", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> latencyDuration = createNumber("latencyDuration", java.math.BigDecimal.class);

    public final StringPath limitPeeNummer = createString("limitPeeNummer");

    public final NumberPath<java.math.BigDecimal> loadEventDuration = createNumber("loadEventDuration", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> loadEventEnd = createNumber("loadEventEnd", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> loadEventStart = createNumber("loadEventStart", java.math.BigDecimal.class);

    public final StringPath loginName = createString("loginName");

    public final StringPath mandant = createString("mandant");

    public final DateTimePath<java.sql.Timestamp> maxActionDate = createDateTime("maxActionDate", java.sql.Timestamp.class);

    public final NumberPath<java.math.BigDecimal> maxActionDateMinusSysdays = createNumber("maxActionDateMinusSysdays", java.math.BigDecimal.class);

    public final DateTimePath<java.sql.Timestamp> minActionDate = createDateTime("minActionDate", java.sql.Timestamp.class);

    public final NumberPath<java.math.BigDecimal> minActionDateMinusSysdays = createNumber("minActionDateMinusSysdays", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> msFirstPaint = createNumber("msFirstPaint", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> msFirstPaintLoadEnddurMs = createNumber("msFirstPaintLoadEnddurMs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> msFirstPaintNavstartdurMs = createNumber("msFirstPaintNavstartdurMs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> navigationStart = createNumber("navigationStart", java.math.BigDecimal.class);

    public final NumberPath<Byte> navigationTimeReceived = createNumber("navigationTimeReceived", Byte.class);

    public final NumberPath<java.math.BigDecimal> nrOfAccKostenstelleToday = createNumber("nrOfAccKostenstelleToday", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pageLoadDuration = createNumber("pageLoadDuration", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> pageLoadsPerSecond = createNumber("pageLoadsPerSecond", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> planungenGenerierenDauerMs = createNumber("planungenGenerierenDauerMs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> planungenGenerierenTage = createNumber("planungenGenerierenTage", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> planungenNeurechnenDauerMs = createNumber("planungenNeurechnenDauerMs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> planungenNeurechnenTage = createNumber("planungenNeurechnenTage", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> processCpuLoad = createNumber("processCpuLoad", java.math.BigDecimal.class);

    public final NumberPath<Byte> realServerTime = createNumber("realServerTime", Byte.class);

    public final NumberPath<java.math.BigDecimal> redirectCount = createNumber("redirectCount", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> redirectDuration = createNumber("redirectDuration", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> redirectEnd = createNumber("redirectEnd", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> redirectStart = createNumber("redirectStart", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> requestId = createNumber("requestId", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> requestStart = createNumber("requestStart", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> requestToResponseDuration = createNumber("requestToResponseDuration", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> responseDuration = createNumber("responseDuration", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> responseEnd = createNumber("responseEnd", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> responseStart = createNumber("responseStart", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> serverExecNoWaitTimeMs = createNumber("serverExecNoWaitTimeMs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> serverExecutionTimeMs = createNumber("serverExecutionTimeMs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> serverOverheadTimeMs = createNumber("serverOverheadTimeMs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> serverWaitTimeMs = createNumber("serverWaitTimeMs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> sessionAgeInMinutes = createNumber("sessionAgeInMinutes", java.math.BigDecimal.class);

    public final StringPath sessionId = createString("sessionId");

    public final NumberPath<java.math.BigDecimal> sollGleichIstDauerMs = createNumber("sollGleichIstDauerMs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> sollGleichIstTage = createNumber("sollGleichIstTage", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> statementsExecuted = createNumber("statementsExecuted", java.math.BigDecimal.class);

    public final DateTimePath<java.sql.Timestamp> sysDatetime = createDateTime("sysDatetime", java.sql.Timestamp.class);

    public final NumberPath<java.math.BigDecimal> systemCpuLoad = createNumber("systemCpuLoad", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> systemLoadAverage = createNumber("systemLoadAverage", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> totalDatabaseTimeMs = createNumber("totalDatabaseTimeMs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> totalExecutionTimeMs = createNumber("totalExecutionTimeMs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> totalFetchTimeMs = createNumber("totalFetchTimeMs", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> totalTimeDuration = createNumber("totalTimeDuration", java.math.BigDecimal.class);

    public final StringPath typeName = createString("typeName");

    public final NumberPath<java.math.BigDecimal> unloadEventDuration = createNumber("unloadEventDuration", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> unloadEventEnd = createNumber("unloadEventEnd", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> unloadEventStart = createNumber("unloadEventStart", java.math.BigDecimal.class);

    public final NumberPath<Byte> usecaseExpectsNavTime = createNumber("usecaseExpectsNavTime", Byte.class);

    public final StringPath usecaseId = createString("usecaseId");

    public final StringPath userAgent = createString("userAgent");

    public final StringPath weekday = createString("weekday");

    public final NumberPath<java.math.BigDecimal> wocheNeuRechnenAnzahl = createNumber("wocheNeuRechnenAnzahl", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> wocheNeuRechnenDauerMs = createNumber("wocheNeuRechnenDauerMs", java.math.BigDecimal.class);

    public final StringPath zebraVersion = createString("zebraVersion");

    public final StringPath zeitartNummer = createString("zeitartNummer");

    public final NumberPath<java.math.BigDecimal> zeitartRegelEnforcmAnzahl = createNumber("zeitartRegelEnforcmAnzahl", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> zeitartRegelEnforcmDauerMs = createNumber("zeitartRegelEnforcmDauerMs", java.math.BigDecimal.class);

    public final com.mysema.query.sql.PrimaryKey<Profiling> sysC00756825 = createPrimaryKey(id);

    public QProfiling(String variable) {
        super(Profiling.class, forVariable(variable), "ZEBRA_M_1", "PROFILING");
        addMetadata();
    }

    public QProfiling(String variable, String schema, String table) {
        super(Profiling.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QProfiling(Path<? extends Profiling> path) {
        super(path.getType(), path.getMetadata(), "ZEBRA_M_1", "PROFILING");
        addMetadata();
    }

    public QProfiling(PathMetadata<?> metadata) {
        super(Profiling.class, metadata, "ZEBRA_M_1", "PROFILING");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(actionDateIntevalDays, ColumnMetadata.named("ACTION_DATE_INTEVAL_DAYS").withIndex(35).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(activeSessions1, ColumnMetadata.named("ACTIVE_SESSIONS_1").withIndex(60).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(activeSessions5, ColumnMetadata.named("ACTIVE_SESSIONS_5").withIndex(61).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(buchungslaufDauerMs, ColumnMetadata.named("BUCHUNGSLAUF_DAUER_MS").withIndex(43).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(buchungslaufTage, ColumnMetadata.named("BUCHUNGSLAUF_TAGE").withIndex(42).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(bwDauerMs, ColumnMetadata.named("BW_DAUER_MS").withIndex(36).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(bwMode, ColumnMetadata.named("BW_MODE").withIndex(37).ofType(Types.VARCHAR).withSize(255));
        addMetadata(client, ColumnMetadata.named("CLIENT").withIndex(2).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(clientIpAddress, ColumnMetadata.named("CLIENT_IP_ADDRESS").withIndex(14).ofType(Types.VARCHAR).withSize(255));
        addMetadata(connectDuration, ColumnMetadata.named("CONNECT_DURATION").withIndex(77).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(connectEnd, ColumnMetadata.named("CONNECT_END").withIndex(76).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(connectStart, ColumnMetadata.named("CONNECT_START").withIndex(75).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(domainLookupDuration, ColumnMetadata.named("DOMAIN_LOOKUP_DURATION").withIndex(74).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(domainLookupEnd, ColumnMetadata.named("DOMAIN_LOOKUP_END").withIndex(73).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(domainLookupStart, ColumnMetadata.named("DOMAIN_LOOKUP_START").withIndex(72).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(domCompleteDuration, ColumnMetadata.named("DOM_COMPLETE_DURATION").withIndex(88).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(domContentLoadedEvent, ColumnMetadata.named("DOM_CONTENT_LOADED_EVENT").withIndex(87).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(domContentLoadedEventEnd, ColumnMetadata.named("DOM_CONTENT_LOADED_EVENT_END").withIndex(86).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(domContentLoadedEventStart, ColumnMetadata.named("DOM_CONTENT_LOADED_EVENT_START").withIndex(85).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(domInteractive, ColumnMetadata.named("DOM_INTERACTIVE").withIndex(84).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(domLoading, ColumnMetadata.named("DOM_LOADING").withIndex(83).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(environment, ColumnMetadata.named("ENVIRONMENT").withIndex(3).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(fetchStart, ColumnMetadata.named("FETCH_START").withIndex(71).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(focusDate, ColumnMetadata.named("FOCUS_DATE").withIndex(22).ofType(Types.TIMESTAMP).withSize(7));
        addMetadata(focusDateMinusSystemdate, ColumnMetadata.named("FOCUS_DATE_MINUS_SYSTEMDATE").withIndex(23).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(focusKstNrOfMitarbeiter, ColumnMetadata.named("FOCUS_KST_NR_OF_MITARBEITER").withIndex(25).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(focusKstNummer, ColumnMetadata.named("FOCUS_KST_NUMMER").withIndex(24).ofType(Types.VARCHAR).withSize(255));
        addMetadata(forcePlanungNeurechnen, ColumnMetadata.named("FORCE_PLANUNG_NEURECHNEN").withIndex(49).ofType(Types.DECIMAL).withSize(1));
        addMetadata(hash, ColumnMetadata.named("HASH").withIndex(100).ofType(Types.CHAR).withSize(64));
        addMetadata(host, ColumnMetadata.named("HOST").withIndex(13).ofType(Types.VARCHAR).withSize(255));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.DECIMAL).withSize(0).notNull());
        addMetadata(javaVersion, ColumnMetadata.named("JAVA_VERSION").withIndex(58).ofType(Types.VARCHAR).withSize(255));
        addMetadata(kontolaufDauerMs, ColumnMetadata.named("KONTOLAUF_DAUER_MS").withIndex(45).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(kontolaufIterationen, ColumnMetadata.named("KONTOLAUF_ITERATIONEN").withIndex(46).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(kontolaufTage, ColumnMetadata.named("KONTOLAUF_TAGE").withIndex(44).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(latencyDuration, ColumnMetadata.named("LATENCY_DURATION").withIndex(92).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(limitPeeNummer, ColumnMetadata.named("LIMIT_PEE_NUMMER").withIndex(27).ofType(Types.VARCHAR).withSize(255));
        addMetadata(loadEventDuration, ColumnMetadata.named("LOAD_EVENT_DURATION").withIndex(91).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(loadEventEnd, ColumnMetadata.named("LOAD_EVENT_END").withIndex(90).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(loadEventStart, ColumnMetadata.named("LOAD_EVENT_START").withIndex(89).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(loginName, ColumnMetadata.named("LOGIN_NAME").withIndex(21).ofType(Types.VARCHAR).withSize(255));
        addMetadata(mandant, ColumnMetadata.named("MANDANT").withIndex(20).ofType(Types.VARCHAR).withSize(255));
        addMetadata(maxActionDate, ColumnMetadata.named("MAX_ACTION_DATE").withIndex(32).ofType(Types.TIMESTAMP).withSize(7));
        addMetadata(maxActionDateMinusSysdays, ColumnMetadata.named("MAX_ACTION_DATE_MINUS_SYSDAYS").withIndex(34).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(minActionDate, ColumnMetadata.named("MIN_ACTION_DATE").withIndex(31).ofType(Types.TIMESTAMP).withSize(7));
        addMetadata(minActionDateMinusSysdays, ColumnMetadata.named("MIN_ACTION_DATE_MINUS_SYSDAYS").withIndex(33).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(msFirstPaint, ColumnMetadata.named("MS_FIRST_PAINT").withIndex(95).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(msFirstPaintLoadEnddurMs, ColumnMetadata.named("MS_FIRST_PAINT_LOAD_ENDDUR_MS").withIndex(97).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(msFirstPaintNavstartdurMs, ColumnMetadata.named("MS_FIRST_PAINT_NAVSTARTDUR_MS").withIndex(96).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(navigationStart, ColumnMetadata.named("NAVIGATION_START").withIndex(64).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(navigationTimeReceived, ColumnMetadata.named("NAVIGATION_TIME_RECEIVED").withIndex(63).ofType(Types.DECIMAL).withSize(1));
        addMetadata(nrOfAccKostenstelleToday, ColumnMetadata.named("NR_OF_ACC_KOSTENSTELLE_TODAY").withIndex(26).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(pageLoadDuration, ColumnMetadata.named("PAGE_LOAD_DURATION").withIndex(93).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(pageLoadsPerSecond, ColumnMetadata.named("PAGE_LOADS_PER_SECOND").withIndex(59).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(planungenGenerierenDauerMs, ColumnMetadata.named("PLANUNGEN_GENERIEREN_DAUER_MS").withIndex(39).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(planungenGenerierenTage, ColumnMetadata.named("PLANUNGEN_GENERIEREN_TAGE").withIndex(38).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(planungenNeurechnenDauerMs, ColumnMetadata.named("PLANUNGEN_NEURECHNEN_DAUER_MS").withIndex(48).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(planungenNeurechnenTage, ColumnMetadata.named("PLANUNGEN_NEURECHNEN_TAGE").withIndex(47).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(processCpuLoad, ColumnMetadata.named("PROCESS_CPU_LOAD").withIndex(55).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(realServerTime, ColumnMetadata.named("REAL_SERVER_TIME").withIndex(62).ofType(Types.DECIMAL).withSize(1));
        addMetadata(redirectCount, ColumnMetadata.named("REDIRECT_COUNT").withIndex(99).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(redirectDuration, ColumnMetadata.named("REDIRECT_DURATION").withIndex(70).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(redirectEnd, ColumnMetadata.named("REDIRECT_END").withIndex(69).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(redirectStart, ColumnMetadata.named("REDIRECT_START").withIndex(68).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(requestId, ColumnMetadata.named("REQUEST_ID").withIndex(9).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(requestStart, ColumnMetadata.named("REQUEST_START").withIndex(78).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(requestToResponseDuration, ColumnMetadata.named("REQUEST_TO_RESPONSE_DURATION").withIndex(82).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(responseDuration, ColumnMetadata.named("RESPONSE_DURATION").withIndex(81).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(responseEnd, ColumnMetadata.named("RESPONSE_END").withIndex(80).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(responseStart, ColumnMetadata.named("RESPONSE_START").withIndex(79).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(serverExecNoWaitTimeMs, ColumnMetadata.named("SERVER_EXEC_NO_WAIT_TIME_MS").withIndex(7).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(serverExecutionTimeMs, ColumnMetadata.named("SERVER_EXECUTION_TIME_MS").withIndex(5).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(serverOverheadTimeMs, ColumnMetadata.named("SERVER_OVERHEAD_TIME_MS").withIndex(8).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(serverWaitTimeMs, ColumnMetadata.named("SERVER_WAIT_TIME_MS").withIndex(6).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(sessionAgeInMinutes, ColumnMetadata.named("SESSION_AGE_IN_MINUTES").withIndex(11).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(sessionId, ColumnMetadata.named("SESSION_ID").withIndex(10).ofType(Types.VARCHAR).withSize(255));
        addMetadata(sollGleichIstDauerMs, ColumnMetadata.named("SOLL_GLEICH_IST_DAUER_MS").withIndex(41).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(sollGleichIstTage, ColumnMetadata.named("SOLL_GLEICH_IST_TAGE").withIndex(40).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(statementsExecuted, ColumnMetadata.named("STATEMENTS_EXECUTED").withIndex(16).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(sysDatetime, ColumnMetadata.named("SYS_DATETIME").withIndex(28).ofType(Types.TIMESTAMP).withSize(11).withDigits(6));
        addMetadata(systemCpuLoad, ColumnMetadata.named("SYSTEM_CPU_LOAD").withIndex(54).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(systemLoadAverage, ColumnMetadata.named("SYSTEM_LOAD_AVERAGE").withIndex(56).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(totalDatabaseTimeMs, ColumnMetadata.named("TOTAL_DATABASE_TIME_MS").withIndex(19).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(totalExecutionTimeMs, ColumnMetadata.named("TOTAL_EXECUTION_TIME_MS").withIndex(17).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(totalFetchTimeMs, ColumnMetadata.named("TOTAL_FETCH_TIME_MS").withIndex(18).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(totalTimeDuration, ColumnMetadata.named("TOTAL_TIME_DURATION").withIndex(94).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(typeName, ColumnMetadata.named("TYPE_NAME").withIndex(98).ofType(Types.VARCHAR).withSize(255));
        addMetadata(unloadEventDuration, ColumnMetadata.named("UNLOAD_EVENT_DURATION").withIndex(67).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(unloadEventEnd, ColumnMetadata.named("UNLOAD_EVENT_END").withIndex(66).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(unloadEventStart, ColumnMetadata.named("UNLOAD_EVENT_START").withIndex(65).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(usecaseExpectsNavTime, ColumnMetadata.named("USECASE_EXPECTS_NAV_TIME").withIndex(15).ofType(Types.DECIMAL).withSize(1));
        addMetadata(usecaseId, ColumnMetadata.named("USECASE_ID").withIndex(4).ofType(Types.VARCHAR).withSize(255));
        addMetadata(userAgent, ColumnMetadata.named("USER_AGENT").withIndex(12).ofType(Types.VARCHAR).withSize(255));
        addMetadata(weekday, ColumnMetadata.named("WEEKDAY").withIndex(29).ofType(Types.VARCHAR).withSize(255));
        addMetadata(wocheNeuRechnenAnzahl, ColumnMetadata.named("WOCHE_NEU_RECHNEN_ANZAHL").withIndex(52).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(wocheNeuRechnenDauerMs, ColumnMetadata.named("WOCHE_NEU_RECHNEN_DAUER_MS").withIndex(53).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(zebraVersion, ColumnMetadata.named("ZEBRA_VERSION").withIndex(57).ofType(Types.VARCHAR).withSize(255));
        addMetadata(zeitartNummer, ColumnMetadata.named("ZEITART_NUMMER").withIndex(30).ofType(Types.VARCHAR).withSize(255));
        addMetadata(zeitartRegelEnforcmAnzahl, ColumnMetadata.named("ZEITART_REGEL_ENFORCM_ANZAHL").withIndex(50).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(zeitartRegelEnforcmDauerMs, ColumnMetadata.named("ZEITART_REGEL_ENFORCM_DAUER_MS").withIndex(51).ofType(Types.DECIMAL).withSize(38).withDigits(2));
    }

}

