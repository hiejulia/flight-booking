
import com.tangzq.batcher.BatchCalculator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;


@Getter
@Setter
@ConfigurationProperties("config.scheduler")
public class PropertiesTask {

	private TaskConfig task = new TaskConfig();

	@Getter
	@Setter
	public static class TaskConfig {

		
		private String distLockNamePrefix = "my_task_lock_";

		
		private int concurrentSize = 4;

		private Map<Class<BatchCalculator>, String> taskMap;
	}
}