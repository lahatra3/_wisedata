package mg.lahatra3.wisedata.reader;

import java.util.function.Supplier;

import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import mg.lahatra3.wisedata.beans.DataSourceConfiguration;

@Builder
@RequiredArgsConstructor
public class DataReader implements Supplier<Dataset<Row>>{

    private final SparkSession sparkSession;
    private final DataSourceConfiguration dataSourceConfiguration;


    @Override
    public Dataset<Row> get() {

        DataFrameReader dataFrameReader = sparkSession.read();

        return switch(dataSourceConfiguration.getSourceType()) {
            case DATABASE -> dataFrameReader.format("jdbc")
                .option("url", dataSourceConfiguration.getUrl())
                .option("dbtable", dataSourceConfiguration.getTable())
                .option("user", dataSourceConfiguration.getUrl())
                .option("password", dataSourceConfiguration.getPassword())
                .option("numPartitions", dataSourceConfiguration.getNumPartitions())
                .option("fetchsize", dataSourceConfiguration.getFetchSize())
                .load();

            case CSV -> dataFrameReader
                .option("delimiter", ",")
                .option("header", "true")
                .csv(dataSourceConfiguration.getFilenamePathString());

            case JSON -> dataFrameReader
                .json(dataSourceConfiguration.getFilenamePathString());

            default -> sparkSession.emptyDataFrame();
        };
    }
}
