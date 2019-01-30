/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.cpu;

import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.ergon.zebra.monitoring.arff.ArffParser;
import ch.ergon.zebra.monitoring.arff.ArffParserException;
import ch.ergon.zebra.monitoring.arff.Environment;
import ch.ergon.zebra.monitoring.arff.InstanceHandler;
import ch.ergon.zebra.monitoring.arff.TypeConverter;
import ch.ergon.zebra.monitoring.arff.source.Source;
import ch.ergon.zebra.monitoring.data.querydsl.domain.CpuUsage;
import ch.ergon.zebra.monitoring.service.arff.util.ArffSourceUtil;
import ch.ergon.zebra.monitoring.service.file.FileService;
import ch.ergon.zebra.monitoring.service.mapping.ActionMappings;
import ch.ergon.zebra.monitoring.service.service.ImportStatus;
import ch.ergon.zebra.monitoring.service.service.MappingNotFoundException;
import ch.ergon.zebra.monitoring.service.service.ServiceContext;

public class CpuUsageService {

	private static final Logger LOG= LoggerFactory.getLogger(CpuUsageService.class);

	private final ServiceContext<CpuUsage> serviceContext;

	private final TypeConverter typeConverter;

	private final FileService<CpuUsage> fileService;

	public CpuUsageService (ServiceContext<CpuUsage> serviceContext) {
		this.serviceContext= serviceContext;
		this.typeConverter= serviceContext.getTypeConverter();
		this.fileService= new FileService<CpuUsage>(serviceContext);
	}

	public ImportStatus importCpuUsageFile (Path path) {
		ImportStatus importStatus= ImportStatus.SUCCESS;
		final List<Source> sources= ArffSourceUtil.getSources(path);

		for (Source source: sources) {

			LOG.info("  Processing source: " + source);

			fileService.fileImportStart(source.getName());

			final ArffParser<CpuUsage> arffParser= new ArffParser<>(source, new Environment(Locale.GERMAN));
			final ActionMappings<CpuUsage> actionMappings= new CpuUsageActionMappings(typeConverter);
			InstanceHandler<CpuUsage> handler= new CpuUsageInstanceHandler(serviceContext, actionMappings);

			try {
				arffParser.forEach(handler);
				fileService.fileImportFinish(source.getName());
			} catch (Exception e) {
				fileService.fileImportAbort(source.getName(), e);
				LOG.error("Import of file " + source.getName() + " failed", e);
				if (e instanceof ArffParserException || e instanceof MappingNotFoundException) {
					importStatus= ImportStatus.DATA_ERROR;
				} else {
					importStatus= ImportStatus.TEMPORARY_ERROR;
				}
			}
		}
		return importStatus;
	}

}
