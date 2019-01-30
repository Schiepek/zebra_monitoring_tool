/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.arff;

import java.util.ArrayList;
import java.util.List;

import ch.ergon.zebra.monitoring.arff.Instance;
import ch.ergon.zebra.monitoring.arff.InstanceHandler;
import ch.ergon.zebra.monitoring.arff.attribute.Attribute;
import ch.ergon.zebra.monitoring.data.measurement.Measurement;
import ch.ergon.zebra.monitoring.data.measurement.MeasurementRepository;
import ch.ergon.zebra.monitoring.service.arff.util.HashUtil;
import ch.ergon.zebra.monitoring.service.mapping.ActionMappings;
import ch.ergon.zebra.monitoring.service.mapping.MappingFunction;
import ch.ergon.zebra.monitoring.service.service.MappingNotFoundException;
import ch.ergon.zebra.monitoring.service.service.ServiceContext;

public abstract class AbstractInstanceHandler<T extends Measurement> implements InstanceHandler<T> {

	private static final long BATCH_SIZE= 1000;

	private final MeasurementRepository<T> repository;

	private final ActionMappings<T> actionMappings;

	private final List<T> entities= new ArrayList<>();

	private final String client;

	private final String environment;

	private long counter= 0;

	public AbstractInstanceHandler (ServiceContext<T> context, ActionMappings<T> actionMappings) {
		this.client= context.getClient();
		this.environment= context.getEnvironment();
		this.repository= context.getRepository();
		this.actionMappings= actionMappings;
	}

	@Override
	public void preHandle () {
	}

	@Override
	public void handle (Instance instance) {
		final T entity= createEntity(instance);
		entity.setClient(getClient());
		entity.setEnvironment(getEnvironment());
		entity.setHash(HashUtil.calculateHash(instance.getSource()));
		convert(entity, instance);

		entities.add(entity);
		if (counter % BATCH_SIZE == 0) {
			saveEntities();
		}
		counter++;
	}

	@Override
	public void postHandle () {
		saveEntities();
	}

	private void convert (T entity, Instance instance) {
		instance.getValues().forEach( (pair) -> {
			final Attribute attribute= pair.getKey();

			final MappingFunction<T, Attribute, Instance> mappingFunction= actionMappings.getMapping(attribute);
			if (mappingFunction == null) {
				throw new MappingNotFoundException("Attribute '" + attribute.getName() + "' not found in action collection");
			}

			mappingFunction.accept(entity, attribute, instance);
		});
	}

	protected abstract T createEntity (Instance instance);

	protected final String getClient () {
		return client;
	}

	protected final String getEnvironment () {
		return environment;
	}

	protected abstract List<T> filterEntities (List<T> entities);

	protected final MeasurementRepository<T> getRepository () {
		return repository;
	}

	private void saveEntities () {
		if (entities.isEmpty()) {
			return;
		}

		final List<T> newEntities= filterEntities(entities);
		if (!newEntities.isEmpty()) {
			repository.save(newEntities);
		}

		entities.clear();
	}

}
