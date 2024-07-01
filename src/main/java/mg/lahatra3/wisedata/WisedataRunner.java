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
        log.info("START DATE: {}", System.currentTimeMillis());
        
        DataSourceConfiguration dataSourceConfiguration = DataSourceConfiguration.builder()
            .url("")
            .username("")
            .password("")
            .table("")
            .sourceType(DataType.DATABASE).build();

        DataSinkConfiguration dataSinkConfiguration = DataSinkConfiguration.builder()
            .url("")
            .username("")
            .password("")
            .table("")
            .sinkType(DataType.DATABASE).build();

        wisedataService.call(dataSourceConfiguration, dataSinkConfiguration);
        log.info("Finished...");

        log.info("END DATE: {}", System.currentTimeMillis());
    }
}
