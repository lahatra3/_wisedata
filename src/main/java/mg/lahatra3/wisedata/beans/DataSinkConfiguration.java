package mg.lahatra3.wisedata.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataSinkConfiguration {
    private String url;
    private String username;
    private String password;
    private String table;
    private String folderPathString;
    @Builder.Default
    private String batchSize = "11111";
    private DataType sinkType;
}
