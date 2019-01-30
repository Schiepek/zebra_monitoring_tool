package ch.ergon.zebra.monitoring.data.querydsl.metadata;

import static com.mysema.query.types.PathMetadataFactory.*;
import ch.ergon.zebra.monitoring.data.querydsl.domain.ImportedFile;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QImportedFile is a Querydsl query type for ImportedFile
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QImportedFile extends com.mysema.query.sql.RelationalPathBase<ImportedFile> {

    private static final long serialVersionUID = 1255181941;

    public static final QImportedFile importedFile = new QImportedFile("IMPORTED_FILE");

    public final StringPath client = createString("client");

    public final StringPath environment = createString("environment");

    public final StringPath filename = createString("filename");

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final DateTimePath<java.sql.Timestamp> importDatetime = createDateTime("importDatetime", java.sql.Timestamp.class);

    public final StringPath stacktrace = createString("stacktrace");

    public final StringPath status = createString("status");

    public final com.mysema.query.sql.PrimaryKey<ImportedFile> sysC00756838 = createPrimaryKey(id);

    public QImportedFile(String variable) {
        super(ImportedFile.class, forVariable(variable), "ZEBRA_M_1", "IMPORTED_FILE");
        addMetadata();
    }

    public QImportedFile(String variable, String schema, String table) {
        super(ImportedFile.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QImportedFile(Path<? extends ImportedFile> path) {
        super(path.getType(), path.getMetadata(), "ZEBRA_M_1", "IMPORTED_FILE");
        addMetadata();
    }

    public QImportedFile(PathMetadata<?> metadata) {
        super(ImportedFile.class, metadata, "ZEBRA_M_1", "IMPORTED_FILE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(client, ColumnMetadata.named("CLIENT").withIndex(2).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(environment, ColumnMetadata.named("ENVIRONMENT").withIndex(3).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(filename, ColumnMetadata.named("FILENAME").withIndex(6).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.DECIMAL).withSize(0).notNull());
        addMetadata(importDatetime, ColumnMetadata.named("IMPORT_DATETIME").withIndex(5).ofType(Types.TIMESTAMP).withSize(11).withDigits(6));
        addMetadata(stacktrace, ColumnMetadata.named("STACKTRACE").withIndex(7).ofType(Types.CLOB).withSize(4000));
        addMetadata(status, ColumnMetadata.named("STATUS").withIndex(4).ofType(Types.VARCHAR).withSize(255));
    }

}

