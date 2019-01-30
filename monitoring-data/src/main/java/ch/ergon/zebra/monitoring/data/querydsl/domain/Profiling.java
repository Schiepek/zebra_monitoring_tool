package ch.ergon.zebra.monitoring.data.querydsl.domain;

import javax.annotation.Generated;
import ch.ergon.zebra.monitoring.data.measurement.Measurement;

/**
 * Profiling is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class Profiling implements Measurement {

    private java.math.BigDecimal actionDateIntevalDays;

    private java.math.BigDecimal activeSessions1;

    private java.math.BigDecimal activeSessions5;

    private java.math.BigDecimal buchungslaufDauerMs;

    private java.math.BigDecimal buchungslaufTage;

    private java.math.BigDecimal bwDauerMs;

    private String bwMode;

    private String client;

    private String clientIpAddress;

    private java.math.BigDecimal connectDuration;

    private java.math.BigDecimal connectEnd;

    private java.math.BigDecimal connectStart;

    private java.math.BigDecimal domainLookupDuration;

    private java.math.BigDecimal domainLookupEnd;

    private java.math.BigDecimal domainLookupStart;

    private java.math.BigDecimal domCompleteDuration;

    private java.math.BigDecimal domContentLoadedEvent;

    private java.math.BigDecimal domContentLoadedEventEnd;

    private java.math.BigDecimal domContentLoadedEventStart;

    private java.math.BigDecimal domInteractive;

    private java.math.BigDecimal domLoading;

    private String environment;

    private java.math.BigDecimal fetchStart;

    private java.sql.Timestamp focusDate;

    private java.math.BigDecimal focusDateMinusSystemdate;

    private java.math.BigDecimal focusKstNrOfMitarbeiter;

    private String focusKstNummer;

    private Byte forcePlanungNeurechnen;

    private String hash;

    private String host;

    private java.math.BigInteger id;

    private String javaVersion;

    private java.math.BigDecimal kontolaufDauerMs;

    private java.math.BigDecimal kontolaufIterationen;

    private java.math.BigDecimal kontolaufTage;

    private java.math.BigDecimal latencyDuration;

    private String limitPeeNummer;

    private java.math.BigDecimal loadEventDuration;

    private java.math.BigDecimal loadEventEnd;

    private java.math.BigDecimal loadEventStart;

    private String loginName;

    private String mandant;

    private java.sql.Timestamp maxActionDate;

    private java.math.BigDecimal maxActionDateMinusSysdays;

    private java.sql.Timestamp minActionDate;

    private java.math.BigDecimal minActionDateMinusSysdays;

    private java.math.BigDecimal msFirstPaint;

    private java.math.BigDecimal msFirstPaintLoadEnddurMs;

    private java.math.BigDecimal msFirstPaintNavstartdurMs;

    private java.math.BigDecimal navigationStart;

    private Byte navigationTimeReceived;

    private java.math.BigDecimal nrOfAccKostenstelleToday;

    private java.math.BigDecimal pageLoadDuration;

    private java.math.BigDecimal pageLoadsPerSecond;

    private java.math.BigDecimal planungenGenerierenDauerMs;

    private java.math.BigDecimal planungenGenerierenTage;

    private java.math.BigDecimal planungenNeurechnenDauerMs;

    private java.math.BigDecimal planungenNeurechnenTage;

    private java.math.BigDecimal processCpuLoad;

    private Byte realServerTime;

    private java.math.BigDecimal redirectCount;

    private java.math.BigDecimal redirectDuration;

    private java.math.BigDecimal redirectEnd;

    private java.math.BigDecimal redirectStart;

    private java.math.BigDecimal requestId;

    private java.math.BigDecimal requestStart;

    private java.math.BigDecimal requestToResponseDuration;

    private java.math.BigDecimal responseDuration;

    private java.math.BigDecimal responseEnd;

    private java.math.BigDecimal responseStart;

    private java.math.BigDecimal serverExecNoWaitTimeMs;

    private java.math.BigDecimal serverExecutionTimeMs;

    private java.math.BigDecimal serverOverheadTimeMs;

    private java.math.BigDecimal serverWaitTimeMs;

    private java.math.BigDecimal sessionAgeInMinutes;

    private String sessionId;

    private java.math.BigDecimal sollGleichIstDauerMs;

    private java.math.BigDecimal sollGleichIstTage;

    private java.math.BigDecimal statementsExecuted;

    private java.sql.Timestamp sysDatetime;

    private java.math.BigDecimal systemCpuLoad;

    private java.math.BigDecimal systemLoadAverage;

    private java.math.BigDecimal totalDatabaseTimeMs;

    private java.math.BigDecimal totalExecutionTimeMs;

    private java.math.BigDecimal totalFetchTimeMs;

    private java.math.BigDecimal totalTimeDuration;

    private String typeName;

    private java.math.BigDecimal unloadEventDuration;

    private java.math.BigDecimal unloadEventEnd;

    private java.math.BigDecimal unloadEventStart;

    private Byte usecaseExpectsNavTime;

    private String usecaseId;

    private String userAgent;

    private String weekday;

    private java.math.BigDecimal wocheNeuRechnenAnzahl;

    private java.math.BigDecimal wocheNeuRechnenDauerMs;

    private String zebraVersion;

    private String zeitartNummer;

    private java.math.BigDecimal zeitartRegelEnforcmAnzahl;

    private java.math.BigDecimal zeitartRegelEnforcmDauerMs;

    public java.math.BigDecimal getActionDateIntevalDays() {
        return actionDateIntevalDays;
    }

    public void setActionDateIntevalDays(java.math.BigDecimal actionDateIntevalDays) {
        this.actionDateIntevalDays = actionDateIntevalDays;
    }

    public java.math.BigDecimal getActiveSessions1() {
        return activeSessions1;
    }

    public void setActiveSessions1(java.math.BigDecimal activeSessions1) {
        this.activeSessions1 = activeSessions1;
    }

    public java.math.BigDecimal getActiveSessions5() {
        return activeSessions5;
    }

    public void setActiveSessions5(java.math.BigDecimal activeSessions5) {
        this.activeSessions5 = activeSessions5;
    }

    public java.math.BigDecimal getBuchungslaufDauerMs() {
        return buchungslaufDauerMs;
    }

    public void setBuchungslaufDauerMs(java.math.BigDecimal buchungslaufDauerMs) {
        this.buchungslaufDauerMs = buchungslaufDauerMs;
    }

    public java.math.BigDecimal getBuchungslaufTage() {
        return buchungslaufTage;
    }

    public void setBuchungslaufTage(java.math.BigDecimal buchungslaufTage) {
        this.buchungslaufTage = buchungslaufTage;
    }

    public java.math.BigDecimal getBwDauerMs() {
        return bwDauerMs;
    }

    public void setBwDauerMs(java.math.BigDecimal bwDauerMs) {
        this.bwDauerMs = bwDauerMs;
    }

    public String getBwMode() {
        return bwMode;
    }

    public void setBwMode(String bwMode) {
        this.bwMode = bwMode;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }

    public java.math.BigDecimal getConnectDuration() {
        return connectDuration;
    }

    public void setConnectDuration(java.math.BigDecimal connectDuration) {
        this.connectDuration = connectDuration;
    }

    public java.math.BigDecimal getConnectEnd() {
        return connectEnd;
    }

    public void setConnectEnd(java.math.BigDecimal connectEnd) {
        this.connectEnd = connectEnd;
    }

    public java.math.BigDecimal getConnectStart() {
        return connectStart;
    }

    public void setConnectStart(java.math.BigDecimal connectStart) {
        this.connectStart = connectStart;
    }

    public java.math.BigDecimal getDomainLookupDuration() {
        return domainLookupDuration;
    }

    public void setDomainLookupDuration(java.math.BigDecimal domainLookupDuration) {
        this.domainLookupDuration = domainLookupDuration;
    }

    public java.math.BigDecimal getDomainLookupEnd() {
        return domainLookupEnd;
    }

    public void setDomainLookupEnd(java.math.BigDecimal domainLookupEnd) {
        this.domainLookupEnd = domainLookupEnd;
    }

    public java.math.BigDecimal getDomainLookupStart() {
        return domainLookupStart;
    }

    public void setDomainLookupStart(java.math.BigDecimal domainLookupStart) {
        this.domainLookupStart = domainLookupStart;
    }

    public java.math.BigDecimal getDomCompleteDuration() {
        return domCompleteDuration;
    }

    public void setDomCompleteDuration(java.math.BigDecimal domCompleteDuration) {
        this.domCompleteDuration = domCompleteDuration;
    }

    public java.math.BigDecimal getDomContentLoadedEvent() {
        return domContentLoadedEvent;
    }

    public void setDomContentLoadedEvent(java.math.BigDecimal domContentLoadedEvent) {
        this.domContentLoadedEvent = domContentLoadedEvent;
    }

    public java.math.BigDecimal getDomContentLoadedEventEnd() {
        return domContentLoadedEventEnd;
    }

    public void setDomContentLoadedEventEnd(java.math.BigDecimal domContentLoadedEventEnd) {
        this.domContentLoadedEventEnd = domContentLoadedEventEnd;
    }

    public java.math.BigDecimal getDomContentLoadedEventStart() {
        return domContentLoadedEventStart;
    }

    public void setDomContentLoadedEventStart(java.math.BigDecimal domContentLoadedEventStart) {
        this.domContentLoadedEventStart = domContentLoadedEventStart;
    }

    public java.math.BigDecimal getDomInteractive() {
        return domInteractive;
    }

    public void setDomInteractive(java.math.BigDecimal domInteractive) {
        this.domInteractive = domInteractive;
    }

    public java.math.BigDecimal getDomLoading() {
        return domLoading;
    }

    public void setDomLoading(java.math.BigDecimal domLoading) {
        this.domLoading = domLoading;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public java.math.BigDecimal getFetchStart() {
        return fetchStart;
    }

    public void setFetchStart(java.math.BigDecimal fetchStart) {
        this.fetchStart = fetchStart;
    }

    public java.sql.Timestamp getFocusDate() {
        return focusDate;
    }

    public void setFocusDate(java.sql.Timestamp focusDate) {
        this.focusDate = focusDate;
    }

    public java.math.BigDecimal getFocusDateMinusSystemdate() {
        return focusDateMinusSystemdate;
    }

    public void setFocusDateMinusSystemdate(java.math.BigDecimal focusDateMinusSystemdate) {
        this.focusDateMinusSystemdate = focusDateMinusSystemdate;
    }

    public java.math.BigDecimal getFocusKstNrOfMitarbeiter() {
        return focusKstNrOfMitarbeiter;
    }

    public void setFocusKstNrOfMitarbeiter(java.math.BigDecimal focusKstNrOfMitarbeiter) {
        this.focusKstNrOfMitarbeiter = focusKstNrOfMitarbeiter;
    }

    public String getFocusKstNummer() {
        return focusKstNummer;
    }

    public void setFocusKstNummer(String focusKstNummer) {
        this.focusKstNummer = focusKstNummer;
    }

    public Byte getForcePlanungNeurechnen() {
        return forcePlanungNeurechnen;
    }

    public void setForcePlanungNeurechnen(Byte forcePlanungNeurechnen) {
        this.forcePlanungNeurechnen = forcePlanungNeurechnen;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public java.math.BigInteger getId() {
        return id;
    }

    public void setId(java.math.BigInteger id) {
        this.id = id;
    }

    public String getJavaVersion() {
        return javaVersion;
    }

    public void setJavaVersion(String javaVersion) {
        this.javaVersion = javaVersion;
    }

    public java.math.BigDecimal getKontolaufDauerMs() {
        return kontolaufDauerMs;
    }

    public void setKontolaufDauerMs(java.math.BigDecimal kontolaufDauerMs) {
        this.kontolaufDauerMs = kontolaufDauerMs;
    }

    public java.math.BigDecimal getKontolaufIterationen() {
        return kontolaufIterationen;
    }

    public void setKontolaufIterationen(java.math.BigDecimal kontolaufIterationen) {
        this.kontolaufIterationen = kontolaufIterationen;
    }

    public java.math.BigDecimal getKontolaufTage() {
        return kontolaufTage;
    }

    public void setKontolaufTage(java.math.BigDecimal kontolaufTage) {
        this.kontolaufTage = kontolaufTage;
    }

    public java.math.BigDecimal getLatencyDuration() {
        return latencyDuration;
    }

    public void setLatencyDuration(java.math.BigDecimal latencyDuration) {
        this.latencyDuration = latencyDuration;
    }

    public String getLimitPeeNummer() {
        return limitPeeNummer;
    }

    public void setLimitPeeNummer(String limitPeeNummer) {
        this.limitPeeNummer = limitPeeNummer;
    }

    public java.math.BigDecimal getLoadEventDuration() {
        return loadEventDuration;
    }

    public void setLoadEventDuration(java.math.BigDecimal loadEventDuration) {
        this.loadEventDuration = loadEventDuration;
    }

    public java.math.BigDecimal getLoadEventEnd() {
        return loadEventEnd;
    }

    public void setLoadEventEnd(java.math.BigDecimal loadEventEnd) {
        this.loadEventEnd = loadEventEnd;
    }

    public java.math.BigDecimal getLoadEventStart() {
        return loadEventStart;
    }

    public void setLoadEventStart(java.math.BigDecimal loadEventStart) {
        this.loadEventStart = loadEventStart;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMandant() {
        return mandant;
    }

    public void setMandant(String mandant) {
        this.mandant = mandant;
    }

    public java.sql.Timestamp getMaxActionDate() {
        return maxActionDate;
    }

    public void setMaxActionDate(java.sql.Timestamp maxActionDate) {
        this.maxActionDate = maxActionDate;
    }

    public java.math.BigDecimal getMaxActionDateMinusSysdays() {
        return maxActionDateMinusSysdays;
    }

    public void setMaxActionDateMinusSysdays(java.math.BigDecimal maxActionDateMinusSysdays) {
        this.maxActionDateMinusSysdays = maxActionDateMinusSysdays;
    }

    public java.sql.Timestamp getMinActionDate() {
        return minActionDate;
    }

    public void setMinActionDate(java.sql.Timestamp minActionDate) {
        this.minActionDate = minActionDate;
    }

    public java.math.BigDecimal getMinActionDateMinusSysdays() {
        return minActionDateMinusSysdays;
    }

    public void setMinActionDateMinusSysdays(java.math.BigDecimal minActionDateMinusSysdays) {
        this.minActionDateMinusSysdays = minActionDateMinusSysdays;
    }

    public java.math.BigDecimal getMsFirstPaint() {
        return msFirstPaint;
    }

    public void setMsFirstPaint(java.math.BigDecimal msFirstPaint) {
        this.msFirstPaint = msFirstPaint;
    }

    public java.math.BigDecimal getMsFirstPaintLoadEnddurMs() {
        return msFirstPaintLoadEnddurMs;
    }

    public void setMsFirstPaintLoadEnddurMs(java.math.BigDecimal msFirstPaintLoadEnddurMs) {
        this.msFirstPaintLoadEnddurMs = msFirstPaintLoadEnddurMs;
    }

    public java.math.BigDecimal getMsFirstPaintNavstartdurMs() {
        return msFirstPaintNavstartdurMs;
    }

    public void setMsFirstPaintNavstartdurMs(java.math.BigDecimal msFirstPaintNavstartdurMs) {
        this.msFirstPaintNavstartdurMs = msFirstPaintNavstartdurMs;
    }

    public java.math.BigDecimal getNavigationStart() {
        return navigationStart;
    }

    public void setNavigationStart(java.math.BigDecimal navigationStart) {
        this.navigationStart = navigationStart;
    }

    public Byte getNavigationTimeReceived() {
        return navigationTimeReceived;
    }

    public void setNavigationTimeReceived(Byte navigationTimeReceived) {
        this.navigationTimeReceived = navigationTimeReceived;
    }

    public java.math.BigDecimal getNrOfAccKostenstelleToday() {
        return nrOfAccKostenstelleToday;
    }

    public void setNrOfAccKostenstelleToday(java.math.BigDecimal nrOfAccKostenstelleToday) {
        this.nrOfAccKostenstelleToday = nrOfAccKostenstelleToday;
    }

    public java.math.BigDecimal getPageLoadDuration() {
        return pageLoadDuration;
    }

    public void setPageLoadDuration(java.math.BigDecimal pageLoadDuration) {
        this.pageLoadDuration = pageLoadDuration;
    }

    public java.math.BigDecimal getPageLoadsPerSecond() {
        return pageLoadsPerSecond;
    }

    public void setPageLoadsPerSecond(java.math.BigDecimal pageLoadsPerSecond) {
        this.pageLoadsPerSecond = pageLoadsPerSecond;
    }

    public java.math.BigDecimal getPlanungenGenerierenDauerMs() {
        return planungenGenerierenDauerMs;
    }

    public void setPlanungenGenerierenDauerMs(java.math.BigDecimal planungenGenerierenDauerMs) {
        this.planungenGenerierenDauerMs = planungenGenerierenDauerMs;
    }

    public java.math.BigDecimal getPlanungenGenerierenTage() {
        return planungenGenerierenTage;
    }

    public void setPlanungenGenerierenTage(java.math.BigDecimal planungenGenerierenTage) {
        this.planungenGenerierenTage = planungenGenerierenTage;
    }

    public java.math.BigDecimal getPlanungenNeurechnenDauerMs() {
        return planungenNeurechnenDauerMs;
    }

    public void setPlanungenNeurechnenDauerMs(java.math.BigDecimal planungenNeurechnenDauerMs) {
        this.planungenNeurechnenDauerMs = planungenNeurechnenDauerMs;
    }

    public java.math.BigDecimal getPlanungenNeurechnenTage() {
        return planungenNeurechnenTage;
    }

    public void setPlanungenNeurechnenTage(java.math.BigDecimal planungenNeurechnenTage) {
        this.planungenNeurechnenTage = planungenNeurechnenTage;
    }

    public java.math.BigDecimal getProcessCpuLoad() {
        return processCpuLoad;
    }

    public void setProcessCpuLoad(java.math.BigDecimal processCpuLoad) {
        this.processCpuLoad = processCpuLoad;
    }

    public Byte getRealServerTime() {
        return realServerTime;
    }

    public void setRealServerTime(Byte realServerTime) {
        this.realServerTime = realServerTime;
    }

    public java.math.BigDecimal getRedirectCount() {
        return redirectCount;
    }

    public void setRedirectCount(java.math.BigDecimal redirectCount) {
        this.redirectCount = redirectCount;
    }

    public java.math.BigDecimal getRedirectDuration() {
        return redirectDuration;
    }

    public void setRedirectDuration(java.math.BigDecimal redirectDuration) {
        this.redirectDuration = redirectDuration;
    }

    public java.math.BigDecimal getRedirectEnd() {
        return redirectEnd;
    }

    public void setRedirectEnd(java.math.BigDecimal redirectEnd) {
        this.redirectEnd = redirectEnd;
    }

    public java.math.BigDecimal getRedirectStart() {
        return redirectStart;
    }

    public void setRedirectStart(java.math.BigDecimal redirectStart) {
        this.redirectStart = redirectStart;
    }

    public java.math.BigDecimal getRequestId() {
        return requestId;
    }

    public void setRequestId(java.math.BigDecimal requestId) {
        this.requestId = requestId;
    }

    public java.math.BigDecimal getRequestStart() {
        return requestStart;
    }

    public void setRequestStart(java.math.BigDecimal requestStart) {
        this.requestStart = requestStart;
    }

    public java.math.BigDecimal getRequestToResponseDuration() {
        return requestToResponseDuration;
    }

    public void setRequestToResponseDuration(java.math.BigDecimal requestToResponseDuration) {
        this.requestToResponseDuration = requestToResponseDuration;
    }

    public java.math.BigDecimal getResponseDuration() {
        return responseDuration;
    }

    public void setResponseDuration(java.math.BigDecimal responseDuration) {
        this.responseDuration = responseDuration;
    }

    public java.math.BigDecimal getResponseEnd() {
        return responseEnd;
    }

    public void setResponseEnd(java.math.BigDecimal responseEnd) {
        this.responseEnd = responseEnd;
    }

    public java.math.BigDecimal getResponseStart() {
        return responseStart;
    }

    public void setResponseStart(java.math.BigDecimal responseStart) {
        this.responseStart = responseStart;
    }

    public java.math.BigDecimal getServerExecNoWaitTimeMs() {
        return serverExecNoWaitTimeMs;
    }

    public void setServerExecNoWaitTimeMs(java.math.BigDecimal serverExecNoWaitTimeMs) {
        this.serverExecNoWaitTimeMs = serverExecNoWaitTimeMs;
    }

    public java.math.BigDecimal getServerExecutionTimeMs() {
        return serverExecutionTimeMs;
    }

    public void setServerExecutionTimeMs(java.math.BigDecimal serverExecutionTimeMs) {
        this.serverExecutionTimeMs = serverExecutionTimeMs;
    }

    public java.math.BigDecimal getServerOverheadTimeMs() {
        return serverOverheadTimeMs;
    }

    public void setServerOverheadTimeMs(java.math.BigDecimal serverOverheadTimeMs) {
        this.serverOverheadTimeMs = serverOverheadTimeMs;
    }

    public java.math.BigDecimal getServerWaitTimeMs() {
        return serverWaitTimeMs;
    }

    public void setServerWaitTimeMs(java.math.BigDecimal serverWaitTimeMs) {
        this.serverWaitTimeMs = serverWaitTimeMs;
    }

    public java.math.BigDecimal getSessionAgeInMinutes() {
        return sessionAgeInMinutes;
    }

    public void setSessionAgeInMinutes(java.math.BigDecimal sessionAgeInMinutes) {
        this.sessionAgeInMinutes = sessionAgeInMinutes;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public java.math.BigDecimal getSollGleichIstDauerMs() {
        return sollGleichIstDauerMs;
    }

    public void setSollGleichIstDauerMs(java.math.BigDecimal sollGleichIstDauerMs) {
        this.sollGleichIstDauerMs = sollGleichIstDauerMs;
    }

    public java.math.BigDecimal getSollGleichIstTage() {
        return sollGleichIstTage;
    }

    public void setSollGleichIstTage(java.math.BigDecimal sollGleichIstTage) {
        this.sollGleichIstTage = sollGleichIstTage;
    }

    public java.math.BigDecimal getStatementsExecuted() {
        return statementsExecuted;
    }

    public void setStatementsExecuted(java.math.BigDecimal statementsExecuted) {
        this.statementsExecuted = statementsExecuted;
    }

    public java.sql.Timestamp getSysDatetime() {
        return sysDatetime;
    }

    public void setSysDatetime(java.sql.Timestamp sysDatetime) {
        this.sysDatetime = sysDatetime;
    }

    public java.math.BigDecimal getSystemCpuLoad() {
        return systemCpuLoad;
    }

    public void setSystemCpuLoad(java.math.BigDecimal systemCpuLoad) {
        this.systemCpuLoad = systemCpuLoad;
    }

    public java.math.BigDecimal getSystemLoadAverage() {
        return systemLoadAverage;
    }

    public void setSystemLoadAverage(java.math.BigDecimal systemLoadAverage) {
        this.systemLoadAverage = systemLoadAverage;
    }

    public java.math.BigDecimal getTotalDatabaseTimeMs() {
        return totalDatabaseTimeMs;
    }

    public void setTotalDatabaseTimeMs(java.math.BigDecimal totalDatabaseTimeMs) {
        this.totalDatabaseTimeMs = totalDatabaseTimeMs;
    }

    public java.math.BigDecimal getTotalExecutionTimeMs() {
        return totalExecutionTimeMs;
    }

    public void setTotalExecutionTimeMs(java.math.BigDecimal totalExecutionTimeMs) {
        this.totalExecutionTimeMs = totalExecutionTimeMs;
    }

    public java.math.BigDecimal getTotalFetchTimeMs() {
        return totalFetchTimeMs;
    }

    public void setTotalFetchTimeMs(java.math.BigDecimal totalFetchTimeMs) {
        this.totalFetchTimeMs = totalFetchTimeMs;
    }

    public java.math.BigDecimal getTotalTimeDuration() {
        return totalTimeDuration;
    }

    public void setTotalTimeDuration(java.math.BigDecimal totalTimeDuration) {
        this.totalTimeDuration = totalTimeDuration;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public java.math.BigDecimal getUnloadEventDuration() {
        return unloadEventDuration;
    }

    public void setUnloadEventDuration(java.math.BigDecimal unloadEventDuration) {
        this.unloadEventDuration = unloadEventDuration;
    }

    public java.math.BigDecimal getUnloadEventEnd() {
        return unloadEventEnd;
    }

    public void setUnloadEventEnd(java.math.BigDecimal unloadEventEnd) {
        this.unloadEventEnd = unloadEventEnd;
    }

    public java.math.BigDecimal getUnloadEventStart() {
        return unloadEventStart;
    }

    public void setUnloadEventStart(java.math.BigDecimal unloadEventStart) {
        this.unloadEventStart = unloadEventStart;
    }

    public Byte getUsecaseExpectsNavTime() {
        return usecaseExpectsNavTime;
    }

    public void setUsecaseExpectsNavTime(Byte usecaseExpectsNavTime) {
        this.usecaseExpectsNavTime = usecaseExpectsNavTime;
    }

    public String getUsecaseId() {
        return usecaseId;
    }

    public void setUsecaseId(String usecaseId) {
        this.usecaseId = usecaseId;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public java.math.BigDecimal getWocheNeuRechnenAnzahl() {
        return wocheNeuRechnenAnzahl;
    }

    public void setWocheNeuRechnenAnzahl(java.math.BigDecimal wocheNeuRechnenAnzahl) {
        this.wocheNeuRechnenAnzahl = wocheNeuRechnenAnzahl;
    }

    public java.math.BigDecimal getWocheNeuRechnenDauerMs() {
        return wocheNeuRechnenDauerMs;
    }

    public void setWocheNeuRechnenDauerMs(java.math.BigDecimal wocheNeuRechnenDauerMs) {
        this.wocheNeuRechnenDauerMs = wocheNeuRechnenDauerMs;
    }

    public String getZebraVersion() {
        return zebraVersion;
    }

    public void setZebraVersion(String zebraVersion) {
        this.zebraVersion = zebraVersion;
    }

    public String getZeitartNummer() {
        return zeitartNummer;
    }

    public void setZeitartNummer(String zeitartNummer) {
        this.zeitartNummer = zeitartNummer;
    }

    public java.math.BigDecimal getZeitartRegelEnforcmAnzahl() {
        return zeitartRegelEnforcmAnzahl;
    }

    public void setZeitartRegelEnforcmAnzahl(java.math.BigDecimal zeitartRegelEnforcmAnzahl) {
        this.zeitartRegelEnforcmAnzahl = zeitartRegelEnforcmAnzahl;
    }

    public java.math.BigDecimal getZeitartRegelEnforcmDauerMs() {
        return zeitartRegelEnforcmDauerMs;
    }

    public void setZeitartRegelEnforcmDauerMs(java.math.BigDecimal zeitartRegelEnforcmDauerMs) {
        this.zeitartRegelEnforcmDauerMs = zeitartRegelEnforcmDauerMs;
    }

}

