package ch.ergon.zebra.monitoring.codegen

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.mysema.codegen.model.SimpleType;
import com.mysema.query.codegen.BeanSerializer;
import com.mysema.query.sql.codegen.DefaultNamingStrategy;
import com.mysema.query.sql.codegen.MetaDataExporter;
import com.mysema.query.sql.codegen.NamingStrategy;

class QuerydslCodeGenerator {

	def String dbUrl = null;
	def String dbUserName = null;
	def String dbPassword = null;
	def String exportBeans = true;
	def String targetPackage = null;
	def String beanTargetPackage = null;
	def String targetSourceFolder = null;
	def String tableNamePattern = null;
	def List<String> beanInterfaces = null;

	def execute() {
		def Connection connection= null;
		try {
			connection= DriverManager.getConnection(dbUrl, dbUserName, dbPassword);

			NamingStrategy namingStrategy= new DefaultNamingStrategy();
			MetaDataExporter exporter= new MetaDataExporter();
			exporter.setPackageName(targetPackage);
			exporter.setBeanPackageName(beanTargetPackage);
			exporter.setTargetFolder(new File(targetSourceFolder));
			exporter.setNamingStrategy(namingStrategy);
			exporter.setTableNamePattern(tableNamePattern);

			if (exportBeans) {
				BeanSerializer serializer= new BeanSerializer();
				if (beanInterfaces != null) {
					for (String iface: beanInterfaces) {
						int sepIndex= iface.lastIndexOf('.');
						if (sepIndex < 0) {
							serializer.addInterface(new SimpleType(iface));
						} else {
							String packageName= iface.substring(0, sepIndex);
							String simpleName= iface.substring(sepIndex + 1);
							serializer.addInterface(new SimpleType(iface, packageName, simpleName));
						}
					}
				}
				exporter.setBeanSerializer(serializer);
			}

			exporter.export(connection.getMetaData());
		} catch (Exception e) {
			println e.getMessage()
			throw e;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// Nothing we can do at this point
				}
			}
		}
	}
}