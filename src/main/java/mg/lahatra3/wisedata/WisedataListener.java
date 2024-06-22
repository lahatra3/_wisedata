package mg.lahatra3.wisedata;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@EnableScheduling
public class WisedataListener {
    
    private static final int ONE_SECOND_IN_MILLIS = 1000;
    private static final int THREE_SECOND_IN_MILLIS = 3 * ONE_SECOND_IN_MILLIS;

    @Autowired
	private WisedataRunner wisedataRunner;
	private final Executor executor = Executors.newSingleThreadExecutor();

    @Scheduled(
        initialDelay = ONE_SECOND_IN_MILLIS
        // fixedDelay = THREE_SECOND_IN_MILLIS
    )
    public void start() {
        executor.execute(wisedataRunner);
    }
}
