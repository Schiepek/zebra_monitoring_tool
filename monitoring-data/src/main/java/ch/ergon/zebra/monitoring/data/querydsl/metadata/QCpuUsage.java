package ch.ergon.zebra.monitoring.data.querydsl.metadata;

import static com.mysema.query.types.PathMetadataFactory.*;
import ch.ergon.zebra.monitoring.data.querydsl.domain.CpuUsage;


import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;

import com.mysema.query.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QCpuUsage is a Querydsl query type for CpuUsage
 */
@Generated("com.mysema.query.sql.codegen.MetaDataSerializer")
public class QCpuUsage extends com.mysema.query.sql.RelationalPathBase<CpuUsage> {

    private static final long serialVersionUID = 833823534;

    public static final QCpuUsage cpuUsage1 = new QCpuUsage("CPU_USAGE");

    public final StringPath client = createString("client");

    public final NumberPath<java.math.BigDecimal> cpuUsage = createNumber("cpuUsage", java.math.BigDecimal.class);

    public final StringPath environment = createString("environment");

    public final StringPath hash = createString("hash");

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final DateTimePath<java.sql.Timestamp> sysDatetime = createDateTime("sysDatetime", java.sql.Timestamp.class);

    public final com.mysema.query.sql.PrimaryKey<CpuUsage> sysC00756829 = createPrimaryKey(id);

    public QCpuUsage(String variable) {
        super(CpuUsage.class, forVariable(variable), "ZEBRA_M_1", "CPU_USAGE");
        addMetadata();
    }

    public QCpuUsage(String variable, String schema, String table) {
        super(CpuUsage.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCpuUsage(Path<? extends CpuUsage> path) {
        super(path.getType(), path.getMetadata(), "ZEBRA_M_1", "CPU_USAGE");
        addMetadata();
    }

    public QCpuUsage(PathMetadata<?> metadata) {
        super(CpuUsage.class, metadata, "ZEBRA_M_1", "CPU_USAGE");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(client, ColumnMetadata.named("CLIENT").withIndex(2).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(cpuUsage, ColumnMetadata.named("CPU_USAGE").withIndex(5).ofType(Types.DECIMAL).withSize(38).withDigits(2));
        addMetadata(environment, ColumnMetadata.named("ENVIRONMENT").withIndex(3).ofType(Types.VARCHAR).withSize(255).notNull());
        addMetadata(hash, ColumnMetadata.named("HASH").withIndex(6).ofType(Types.CHAR).withSize(64));
        addMetadata(id, ColumnMetadata.named("ID").withIndex(1).ofType(Types.DECIMAL).withSize(0).notNull());
        addMetadata(sysDatetime, ColumnMetadata.named("SYS_DATETIME").withIndex(4).ofType(Types.TIMESTAMP).withSize(11).withDigits(6));
    }

}

