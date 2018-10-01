
import com.tangzq.util.SystemUtils;
import org.redisson.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.StringUtils;


/**
 * Configuration class 
 * 
 */
@Configuration
@EnableConfigurationProperties(PropertiesTask.class)
public class AutoConfigTask {

	private static final Logger logger = LoggerFactory.getLogger(AutoConfigTask.class);

	@Autowired
	private PropertiesTask taskProperties;

	@Autowired
	private RedisProperties redisProperties;

    // Task scheduler 
	@Bean
	public TaskScheduler batchCalculatorTaskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(taskProperties.getTask().getConcurrentSize());
		return taskScheduler;
    }
    

    // RedissonCLlient 
	@Bean
	public RedissonClient redissonClient() {
		Config config = new Config();
		if (SystemUtils.IS_OS_LINUX_X86_64) {
			config.setUseLinuxNativeEpoll(true);
		} else {
			logger.warn("The linux native epoll not support on {}.", SystemUtils.OS_NAME);
		}
		if (redisProperties.getSentinel() != null) {
			RedisProperties.Sentinel sentinelConfig = redisProperties.getSentinel();
			SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
			sentinelServersConfig.setMasterName(sentinelConfig.getMaster());
			if (sentinelConfig.getNodes() != null) {
				sentinelServersConfig
						.addSentinelAddress(StringUtils.commaDelimitedListToStringArray(sentinelConfig.getNodes()));
			}
			sentinelServersConfig.setDatabase(redisProperties.getDatabase());
			sentinelServersConfig.setPassword(redisProperties.getPassword());
		} else {
			SingleServerConfig singleServerConfig = config.useSingleServer();
			singleServerConfig.setDatabase(redisProperties.getDatabase());
			singleServerConfig.setPassword(redisProperties.getPassword());
			singleServerConfig.setAddress(redisProperties.getHost() + ":" + redisProperties.getPort());
		}
		return Redisson.create(config);
	}

}