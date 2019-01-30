/*
 * Copyright (c) 2015 Ergon Informatik AG
 * Merkurstrasse 43, 8032 Zuerich, Switzerland
 * All rights reserved.
 */

package ch.ergon.zebra.monitoring.service.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import ch.ergon.zebra.monitoring.data.DatabaseConfig;
import ch.ergon.zebra.monitoring.service.elasticsearch.ElasticsearchConfig;
import ch.ergon.zebra.monitoring.service.service.ImporterConfig;

public class ConfigurationService {

	private final Element root;

	public ConfigurationService (InputStream inputStream) {
		root= XMLUtil.getRoot(inputStream);
	}

	public ElasticsearchConfig getElasticsearchConfig () {
		Element child= root.getChild("es");

		return new ElasticsearchConfig(
			XMLUtil.getText(root, "client"),
			XMLUtil.getText(root, "environment"),
			XMLUtil.getText(child, "cluster"),
			XMLUtil.getText(child, "index"),
			XMLUtil.getText(child, "host"),
			XMLUtil.getNumber(child, "port"));
	}

	public DatabaseConfig getDatabaseConfig () {
		Element child= XMLUtil.getChild(root, "database");

		return new DatabaseConfig(
			XMLUtil.getText(child, "jdbcUrl"),
			XMLUtil.getText(child, "jdbcDriver"),
			XMLUtil.getText(child, "username"),
			XMLUtil.getText(child, "password"));
	}

	public List<ImporterConfig> getImporterConfigs () {
		List<Element> children= XMLUtil.getChildren(root, "importer");
		List<ImporterConfig> importerConfigs= new ArrayList<>();

		children.forEach( (child) -> {
			importerConfigs.add(
				new ImporterConfig(
					XMLUtil.getAttribute(child, "name"),
					XMLUtil.getText(root, "client"),
					XMLUtil.getText(root, "environment"),
					XMLUtil.getAttribute(child, "pending"),
					XMLUtil.getAttribute(child, "done"),
					XMLUtil.getAttribute(child, "failed")));
		});

		return importerConfigs;
	}

}
