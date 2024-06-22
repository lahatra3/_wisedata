package mg.lahatra3.wisedata.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataSourceConfiguration {
    private String url;
    private String username;
    private String password;
    private String table;
    private String filenamePathString;
    @Builder.Default
    private String numPartitions = "311";
    @Builder.Default
    private String fetchSize = "11111";
    private DataType sourceType;
}
