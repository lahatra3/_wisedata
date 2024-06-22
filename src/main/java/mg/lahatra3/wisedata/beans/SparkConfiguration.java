package mg.lahatra3.wisedata.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties(prefix = "spark.configuration")
public class SparkConfiguration {
    private String master;
    private String name;
    private String memory = "4g";
    private String cores = "3";
    private String executors = "3";
    private String options;
}
