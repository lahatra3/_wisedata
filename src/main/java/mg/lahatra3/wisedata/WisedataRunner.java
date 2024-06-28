package mg.lahatra3.wisedata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mg.lahatra3.wisedata.beans.DataSinkConfiguration;
import mg.lahatra3.wisedata.beans.DataSourceConfiguration;
import mg.lahatra3.wisedata.beans.DataType;


@Slf4j
@Service
public class WisedataRunner implements Runnable {

    @Autowired
    private WisedataService wisedataService;

    
    @Override
    public void run() {

        log.info("Starting...");

        // List<Wisedata> pending = wisedataService
        log.info("START DATE: {}", System.currentTimeMillis());
        
        DataSourceConfiguration dataSourceConfiguration = DataSourceConfiguration.builder()
            .url("jdbc:postgresql://localhost:5432/wisedata?currentSchema=public")
            .username("wisedata")
            .password("w1s3d_ta")
            .table("mock_data_source")
            .sourceType(DataType.DATABASE).build();

        DataSinkConfiguration dataSinkConfiguration = DataSinkConfiguration.builder()
            .url("jdbc:postgresql://localhost:5432/wisedata?currentSchema=public")
            .username("wisedata")
            .password("w1s3d_ta")
            .table("mock_data_sink")
            .sinkType(DataType.DATABASE).build();

        wisedataService.call(dataSourceConfiguration, dataSinkConfiguration);
        log.info("Finished...");

        log.info("END DATE: {}", System.currentTimeMillis());
    }
}
