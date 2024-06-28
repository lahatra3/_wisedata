package mg.lahatra3.wisedata.writer;

import java.util.function.Consumer;

import org.apache.spark.sql.DataFrameWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import mg.lahatra3.wisedata.beans.DataSinkConfiguration;

@Builder
@RequiredArgsConstructor
public class DataWriter implements Consumer<Dataset<Row>> {

    private final DataSinkConfiguration dataSinkConfiguration;

    @Override
    public void accept(Dataset<Row> dataset) {

        DataFrameWriter<Row> dataFrameWriter = dataset.write();

        switch (dataSinkConfiguration.getSinkType()) {
            case DATABASE:
                dataFrameWriter.format("jdbc")
                    .mode("append")
                    .option("url", dataSinkConfiguration.getUrl())
                    .option("dbtable", dataSinkConfiguration.getTable())
                    .option("user", dataSinkConfiguration.getUsername())
                    .option("password", dataSinkConfiguration.getPassword())
                    .option("batchsize", dataSinkConfiguration.getBatchSize())
                    .save();
                break;
            
            case CSV:
                dataFrameWriter
                    .mode("append")
                    .csv(dataSinkConfiguration.getFolderPathString());
                break;

            case JSON:
                dataFrameWriter
                    .mode("append")
                    .json(dataSinkConfiguration.getFolderPathString());
                break;
        }
    }
}
