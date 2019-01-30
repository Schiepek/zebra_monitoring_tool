/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.service;

import ch.ergon.zebra.monitoring.arff.TypeConverter;
import ch.ergon.zebra.monitoring.data.file.ImportedFileRepository;
import ch.ergon.zebra.monitoring.data.measurement.MeasurementRepository;

public class ServiceContext<T> {

	private final String client;

	private final String environment;

	private final TypeConverter typeConverter;

	private final MeasurementRepository<T> repository;

	private final ImportedFileRepository fileRepository;

	public ServiceContext (String client, String environment, TypeConverter typeConverter, MeasurementRepository<T> repository,
		ImportedFileRepository fileRepository) {
		this.client= client;
		this.environment= environment;
		this.typeConverter= typeConverter;
		this.repository= repository;
		this.fileRepository= fileRepository;
	}

	public String getClient () {
		return client;
	}

	public String getEnvironment () {
		return environment;
	}

	public TypeConverter getTypeConverter () {
		return typeConverter;
	}

	public MeasurementRepository<T> getRepository () {
		return repository;
	}

	public ImportedFileRepository getFileRepository () {
		return fileRepository;
	}

}
