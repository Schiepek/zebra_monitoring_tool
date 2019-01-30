/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.profiling;

import java.util.HashMap;
import java.util.Map;

import ch.ergon.zebra.monitoring.arff.Instance;
import ch.ergon.zebra.monitoring.arff.TypeConverter;
import ch.ergon.zebra.monitoring.arff.attribute.Attribute;
import ch.ergon.zebra.monitoring.data.querydsl.domain.Profiling;
import ch.ergon.zebra.monitoring.service.mapping.ActionMappings;
import ch.ergon.zebra.monitoring.service.mapping.MappingFunction;

import com.google.common.collect.Maps;

public class ProfilingActionMappings implements ActionMappings<Profiling> {

	private final static MappingFunction<Profiling, Attribute, Instance> EMPTY_FUNCTION= (profiling, attribute, instance) -> {
	};

	private final Map<String, MappingFunction<Profiling, Attribute, Instance>> actions= new HashMap<>();

	private final TypeConverter converter;

	public ProfilingActionMappings (TypeConverter converter) {
		this.converter= converter;
		initializeActionMappings();
	}

	@Override
	public MappingFunction<Profiling, Attribute, Instance> getMapping (Attribute attribute) {
		return actions.get(attribute.getName());
	}

	@Override
	public Map<String, MappingFunction<Profiling, Attribute, Instance>> getMappings () {
		return Maps.newHashMap(actions);
	}

	private void initializeActionMappings () {
		actions.put("useCaseId", (profiling, attribute, instance) -> profiling.setUsecaseId(instance.getValue(attribute, converter)));
		actions.put("serverExecutionTimeMs",
			(profiling, attribute, instance) -> profiling.setServerExecutionTimeMs(instance.getValue(attribute, converter)));
		actions.put("serverOverheadTimeMs",
			(profiling, attribute, instance) -> profiling.setServerOverheadTimeMs(instance.getValue(attribute, converter)));
		actions.put("requestId", (profiling, attribute, instance) -> profiling.setRequestId(instance.getValue(attribute, converter)));
		actions.put("sessionId", (profiling, attribute, instance) -> profiling.setSessionId(instance.getValue(attribute, converter)));
		actions.put("sessionAgeInMinutes",
			(profiling, attribute, instance) -> profiling.setSessionAgeInMinutes(instance.getValue(attribute, converter)));
		actions.put("userAgent", (profiling, attribute, instance) -> profiling.setUserAgent(instance.getValue(attribute, converter)));
		actions.put("host", (profiling, attribute, instance) -> profiling.setHost(instance.getValue(attribute, converter)));
		actions.put("clientIpAddress", (profiling, attribute, instance) -> profiling.setClientIpAddress(instance.getValue(attribute, converter)));
		actions.put("useCaseExpectsNavigationTime",
			(profiling, attribute, instance) -> profiling.setUsecaseExpectsNavTime(instance.getValue(attribute, converter)));
		actions.put("mandant", (profiling, attribute, instance) -> profiling.setMandant(instance.getValue(attribute, converter)));
		actions.put("loginName", (profiling, attribute, instance) -> profiling.setLoginName(instance.getValue(attribute, converter)));
		actions.put("focusDate",
			(profiling, attribute, instance) -> profiling.setFocusDate(instance.getValue(attribute, converter)));
		actions.put("focusDateMinusSystemDate",
			(profiling, attribute, instance) -> profiling.setFocusDateMinusSystemdate(instance.getValue(attribute, converter)));
		actions.put("focusKstNummer",
			(profiling, attribute, instance) -> profiling.setFocusKstNummer(instance.getValue(attribute, converter)));
		actions.put("focusKstNrOfMitarbeiter",
			(profiling, attribute, instance) -> profiling.setFocusKstNrOfMitarbeiter(instance.getValue(attribute, converter)));
		actions.put("nrOfAccessibleKostenstelle",
			(profiling, attribute, instance) -> profiling.setNrOfAccKostenstelleToday(instance.getValue(attribute, converter)));
		actions.put("nrOfAccessibleKostenstelleToday",
			(profiling, attribute, instance) -> profiling.setNrOfAccKostenstelleToday(instance.getValue(attribute, converter)));
		actions.put("limitPeeNummer",
			(profiling, attribute, instance) -> profiling.setLimitPeeNummer(instance.getValue(attribute, converter)));
		actions.put("sysdatetime",
			(profiling, attribute, instance) -> profiling.setSysDatetime(instance.getValue(attribute, converter)));
		actions.put("sysdate", EMPTY_FUNCTION);
		actions.put("systime", EMPTY_FUNCTION);
		actions.put("weekday",
			(profiling, attribute, instance) -> profiling.setWeekday(instance.getValue(attribute, converter)));
		actions.put("zeitartNummer",
			(profiling, attribute, instance) -> profiling.setZeitartNummer(instance.getValue(attribute, converter)));
		actions.put("minActionDate",
			(profiling, attribute, instance) -> profiling.setMinActionDate(instance.getValue(attribute, converter)));
		actions.put("maxActionDate",
			(profiling, attribute, instance) -> profiling.setMaxActionDate(instance.getValue(attribute, converter)));
		actions.put("minActionDateMinusSystemDays",
			(profiling, attribute, instance) -> profiling.setMinActionDateMinusSysdays(instance.getValue(attribute, converter)));
		actions.put("maxActionDateMinusSystemDays",
			(profiling, attribute, instance) -> profiling.setMaxActionDateMinusSysdays(instance.getValue(attribute, converter)));
		actions.put("actionDateIntevalDays",
			(profiling, attribute, instance) -> profiling.setActionDateIntevalDays(instance.getValue(attribute, converter)));
		actions.put("bwDauerMs", (profiling, attribute, instance) -> profiling.setBwDauerMs(instance.getValue(attribute, converter)));
		actions.put("bwMode", (profiling, attribute, instance) -> profiling.setBwMode(instance.getValue(attribute, converter)));
		actions.put("planungenGenerierenTage",
			(profiling, attribute, instance) -> profiling.setPlanungenGenerierenTage(instance.getValue(attribute, converter)));
		actions.put("planungenGenerierenDauerMs",
			(profiling, attribute, instance) -> profiling.setPlanungenGenerierenDauerMs(instance.getValue(attribute, converter)));
		actions.put("sollGleichIstTage",
			(profiling, attribute, instance) -> profiling.setSollGleichIstTage(instance.getValue(attribute, converter)));
		actions.put("sollGleichIstDauerMs",
			(profiling, attribute, instance) -> profiling.setSollGleichIstDauerMs(instance.getValue(attribute, converter)));
		actions.put("buchungslaufTage",
			(profiling, attribute, instance) -> profiling.setBuchungslaufTage(instance.getValue(attribute, converter)));
		actions.put("buchungslaufDauerMs",
			(profiling, attribute, instance) -> profiling.setBuchungslaufDauerMs(instance.getValue(attribute, converter)));
		actions.put("kontolaufTage",
			(profiling, attribute, instance) -> profiling.setKontolaufTage(instance.getValue(attribute, converter)));
		actions.put("kontolaufDauerMs",
			(profiling, attribute, instance) -> profiling.setKontolaufDauerMs(instance.getValue(attribute, converter)));
		actions.put("kontolaufIterationen",
			(profiling, attribute, instance) -> profiling.setKontolaufIterationen(instance.getValue(attribute, converter)));
		actions.put("planungenNeurechnenTage",
			(profiling, attribute, instance) -> profiling.setPlanungenNeurechnenTage(instance.getValue(attribute, converter)));
		actions.put("planungenNeurechnenDauerMs",
			(profiling, attribute, instance) -> profiling.setPlanungenNeurechnenDauerMs(instance.getValue(attribute, converter)));
		actions.put("forcePlanungNeurechnen",
			(profiling, attribute, instance) -> profiling.setForcePlanungNeurechnen(instance.getValue(attribute, converter)));
		actions.put("zeitartRegelEnforcementAnzahl",
			(profiling, attribute, instance) -> profiling.setZeitartRegelEnforcmAnzahl(instance.getValue(attribute, converter)));
		actions.put("zeitartRegelEnforcementDauerMs",
			(profiling, attribute, instance) -> profiling.setZeitartRegelEnforcmDauerMs(instance.getValue(attribute, converter)));
		actions.put("wocheNeuRechnenAnzahl",
			(profiling, attribute, instance) -> profiling.setWocheNeuRechnenAnzahl(instance.getValue(attribute, converter)));
		actions.put("wocheNeuRechnenDauerMs",
			(profiling, attribute, instance) -> profiling.setWocheNeuRechnenDauerMs(instance.getValue(attribute, converter)));
		actions.put("systemCPULoad",
			(profiling, attribute, instance) -> profiling.setSystemCpuLoad(instance.getValue(attribute, converter)));
		actions.put("processCPULoad",
			(profiling, attribute, instance) -> profiling.setProcessCpuLoad(instance.getValue(attribute, converter)));
		actions.put("systemLoadAverage",
			(profiling, attribute, instance) -> profiling.setSystemLoadAverage(instance.getValue(attribute, converter)));
		actions.put("zebraVersion",
			(profiling, attribute, instance) -> profiling.setZebraVersion(instance.getValue(attribute, converter)));
		actions
		.put("javaVersion", (profiling, attribute, instance) -> profiling.setJavaVersion(instance.getValue(attribute, converter)));
		actions.put("pageLoadsPerSecond",
			(profiling, attribute, instance) -> profiling.setPageLoadsPerSecond(instance.getValue(attribute, converter)));
		actions.put("activeSessions1",
			(profiling, attribute, instance) -> profiling.setActiveSessions1(instance.getValue(attribute, converter)));
		actions.put("activeSessions5",
			(profiling, attribute, instance) -> profiling.setActiveSessions5(instance.getValue(attribute, converter)));
		actions.put("realServerTime",
			(profiling, attribute, instance) -> profiling.setRealServerTime(instance.getValue(attribute, converter)));
		actions.put("navigationTimeReceived",
			(profiling, attribute, instance) -> profiling.setNavigationTimeReceived(instance.getValue(attribute, converter)));
		actions.put("navigationStart",
			(profiling, attribute, instance) -> profiling.setNavigationStart(instance.getValue(attribute, converter)));
		actions.put("unloadEventStart",
			(profiling, attribute, instance) -> profiling.setUnloadEventStart(instance.getValue(attribute, converter)));
		actions.put("unloadEventEnd",
			(profiling, attribute, instance) -> profiling.setUnloadEventEnd(instance.getValue(attribute, converter)));
		actions.put("unloadEventDuration",
			(profiling, attribute, instance) -> profiling.setUnloadEventDuration(instance.getValue(attribute, converter)));
		actions.put("redirectStart",
			(profiling, attribute, instance) -> profiling.setRedirectStart(instance.getValue(attribute, converter)));
		actions.put("redirectEnd", (profiling, attribute, instance) -> profiling.setRedirectEnd(instance.getValue(attribute, converter)));
		actions.put("redirectDuration",
			(profiling, attribute, instance) -> profiling.setRedirectDuration(instance.getValue(attribute, converter)));
		actions.put("fetchStart", (profiling, attribute, instance) -> profiling.setFetchStart(instance.getValue(attribute, converter)));
		actions.put("domainLookupStart",
			(profiling, attribute, instance) -> profiling.setDomainLookupStart(instance.getValue(attribute, converter)));
		actions.put("domainLookupEnd",
			(profiling, attribute, instance) -> profiling.setDomainLookupEnd(instance.getValue(attribute, converter)));
		actions.put("domainLookupDuration",
			(profiling, attribute, instance) -> profiling.setDomainLookupDuration(instance.getValue(attribute, converter)));
		actions.put("connectStart", (profiling, attribute, instance) -> profiling.setConnectStart(instance.getValue(attribute, converter)));
		actions.put("connectEnd", (profiling, attribute, instance) -> profiling.setConnectEnd(instance.getValue(attribute, converter)));
		actions.put("connectDuration",
			(profiling, attribute, instance) -> profiling.setConnectDuration(instance.getValue(attribute, converter)));
		actions.put("requestStart", (profiling, attribute, instance) -> profiling.setRequestStart(instance.getValue(attribute, converter)));
		actions.put("responseStart",
			(profiling, attribute, instance) -> profiling.setResponseStart(instance.getValue(attribute, converter)));
		actions.put("responseEnd", (profiling, attribute, instance) -> profiling.setResponseEnd(instance.getValue(attribute, converter)));
		actions.put("responseDuration",
			(profiling, attribute, instance) -> profiling.setResponseDuration(instance.getValue(attribute, converter)));
		actions.put("requestToResonseDuration",
			(profiling, attribute, instance) -> profiling.setRequestToResponseDuration(instance.getValue(attribute, converter)));
		actions.put("domLoading", (profiling, attribute, instance) -> profiling.setDomLoading(instance.getValue(attribute, converter)));
		actions.put("domInteractive",
			(profiling, attribute, instance) -> profiling.setDomInteractive(instance.getValue(attribute, converter)));
		actions.put("domContentLoadedEventStart",
			(profiling, attribute, instance) -> profiling.setDomContentLoadedEventStart(instance.getValue(attribute, converter)));
		actions.put("domContentLoadedEventEnd",
			(profiling, attribute, instance) -> profiling.setDomContentLoadedEventEnd(instance.getValue(attribute, converter)));
		actions.put("domContentLoadedEvent",
			(profiling, attribute, instance) -> profiling.setDomContentLoadedEvent(instance.getValue(attribute, converter)));
		actions.put("domCompleteDuration",
			(profiling, attribute, instance) -> profiling.setDomCompleteDuration(instance.getValue(attribute, converter)));
		actions.put("loadEventStart",
			(profiling, attribute, instance) -> profiling.setLoadEventStart(instance.getValue(attribute, converter)));
		actions.put("loadEventEnd", (profiling, attribute, instance) -> profiling.setLoadEventEnd(instance.getValue(attribute, converter)));
		actions.put("loadEventDuration",
			(profiling, attribute, instance) -> profiling.setLoadEventDuration(instance.getValue(attribute, converter)));
		actions.put("latencyDuration",
			(profiling, attribute, instance) -> profiling.setLatencyDuration(instance.getValue(attribute, converter)));
		actions.put("pageLoadDuration",
			(profiling, attribute, instance) -> profiling.setPageLoadDuration(instance.getValue(attribute, converter)));
		actions.put("totalTimeDuration",
			(profiling, attribute, instance) -> profiling.setTotalTimeDuration(instance.getValue(attribute, converter)));
		actions.put("msFirstPaint", (profiling, attribute, instance) -> profiling.setMsFirstPaint(instance.getValue(attribute, converter)));
		actions.put("msFirstPaintFromNavigationStartDuationMs",
			(profiling, attribute, instance) -> profiling.setMsFirstPaintNavstartdurMs(instance.getValue(attribute, converter)));
		actions.put("msFirstPaintUntilLoadEventEndDurationMs",
			(profiling, attribute, instance) -> profiling.setMsFirstPaintLoadEnddurMs(instance.getValue(attribute, converter)));
		actions.put("type", (profiling, attribute, instance) -> profiling.setTypeName(instance.getValue(attribute, converter)));
		actions.put("redirectCount",
			(profiling, attribute, instance) -> profiling.setRedirectCount(instance.getValue(attribute, converter)));
		actions.put("serverWaitTimeMs", (profiling, attribute, instance) -> profiling.setServerWaitTimeMs(instance.getValue(attribute, converter)));
		actions.put("serverExecutionNoWaitTimeMs",
			(profiling, attribute, instance) -> profiling.setServerExecNoWaitTimeMs(instance.getValue(attribute, converter)));
		actions.put("statementsExecuted",
			(profiling, attribute, instance) -> profiling.setStatementsExecuted(instance.getValue(attribute, converter)));
		actions.put("totalExecutionTimeMs",
			(profiling, attribute, instance) -> profiling.setTotalExecutionTimeMs(instance.getValue(attribute, converter)));
		actions.put("totalFetchTimeMs", (profiling, attribute, instance) -> profiling.setTotalFetchTimeMs(instance.getValue(attribute, converter)));
		actions.put("totalDatabaseTimeMs",
			(profiling, attribute, instance) -> profiling.setTotalDatabaseTimeMs(instance.getValue(attribute, converter)));
	}

}
