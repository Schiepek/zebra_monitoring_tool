package ch.ergon.zebra.monitoring.data.querydsl.metadata;

import static com.mysema.query.types.PathMetadataFactory.*;
import ch.ergon.zebra.monitoring.data.querydsl.domain.MemoryUsage;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QMemoryUsage is a Querydsl query type for MemoryUsage
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QMemoryUsage extends com.mysema.query.sql.RelationalPathBase<MemoryUsage> {

    private static final long serialVersionUID = -855286101;

    public static final QMemoryUsage memoryUsage = new QMemoryUsage("MEMORY_USAGE");

    public final StringPath client = createString("client");

    public final StringPath environment = createString("environment");

    public final NumberPath<java.math.BigDecimal> freeHeap = createNumber("freeHeap", java.math.BigDecimal.class);

    public final StringPath hash = createString("hash");

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final DateTimePath<java.sql.Timestamp> sysDatetime = createDateTime("sysDatetime", java.sql.Timestamp.class);

    public final NumberPath<java.math.BigDecimal> totalHeap = createNumber("totalHeap", java.math.BigDecimal.class);

    public final com.mysema.query.sql.PrimaryKey<MemoryUsage> sysC00756833 = createPrimaryKey(id);

    public QMemoryUsage(String variable) {
        super(MemoryUsage.class, forVariable(variable), "ZEBRA_M_1", "MEMORY_USAGE");
        addMetadata();
    }

    public QMemoryUsage(String variable, String schema, String table) {
        super(MemoryUsage.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QMemoryUsage(Path<? extends MemoryUsage> path) {
        super(path.getType(), path.getMetadata(), "ZEBRA_M_1", "MEMORY_USAGE");
        addMetadata();
    }

    public QMemoryUsage(PathMetadata<?> metadata) {
        super(MemoryUsage.class, metadata, "ZEBRA_M_1", "MEMORY_USAGE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(client, ColumnMetadata.named("CLIENT").withIndex(2).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(environment, ColumnMetadata.named("ENVIRONMENT").withIndex(3).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(freeHeap, ColumnMetadata.named("FREE_HEAP").withIndex(6).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(hash, ColumnMetadata.named("HASH").withIndex(7).ofType(Types.CHAR).withSize(64));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.DECIMAL).withSize(0).notNull());
        addMetadata(sysDatetime, ColumnMetadata.named("SYS_DATETIME").withIndex(4).ofType(Types.TIMESTAMP).withSize(11).withDigits(6));
        addMetadata(totalHeap, ColumnMetadata.named("TOTAL_HEAP").withIndex(5).ofType(Types.DECIMAL).withSize(38).withDigits(2));
    }

}

