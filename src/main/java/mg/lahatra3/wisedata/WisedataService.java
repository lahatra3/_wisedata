package mg.lahatra3.wisedata;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mg.lahatra3.wisedata.beans.DataSinkConfiguration;
import mg.lahatra3.wisedata.beans.DataSourceConfiguration;
import mg.lahatra3.wisedata.beans.SparkConfiguration;
import mg.lahatra3.wisedata.reader.DataReader;
import mg.lahatra3.wisedata.writer.DataWriter;


@Slf4j
@Service
public class WisedataService {

    @Autowired
    private SparkConfiguration sparkConfiguration;


    public void call(DataSourceConfiguration dataSourceConfiguration, DataSinkConfiguration dataSinkConfiguration) {

        SparkConf sparkConf = new SparkConf()
            .setMaster(sparkConfiguration.getMaster())
            .setAppName(sparkConfiguration.getName())
            .set("spark.executor.memory", sparkConfiguration.getMemory())
            .set("spark.driver.memory", sparkConfiguration.getMemory())
            .set("spark.executor.cores", sparkConfiguration.getCores())
            .set("spark.driver.cores", sparkConfiguration.getCores())
            .set("spark.executor.instances", sparkConfiguration.getExecutors())
            .set("spark.executor.extraJavaOptions", sparkConfiguration.getOptions())
            .set("spark.driver.extraJavaOptions", sparkConfiguration.getOptions());

        SparkSession sparkSession = SparkSession.builder().config(sparkConf).getOrCreate();

        log.info("Starting to read data source...");
        DataReader dataReader = DataReader.builder()
            .sparkSession(sparkSession)
            .dataSourceConfiguration(dataSourceConfiguration).build();
        Dataset<Row> dataset = dataReader.get().cache();

        log.info("Starting to write data sink...");
        DataWriter dataWriter = DataWriter.builder()
            .dataSinkConfiguration(dataSinkConfiguration).build();
        dataWriter.accept(dataset);
        log.info("Written successfully...");
    }
}
