
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.lease.Releasable;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;


/**
 *  ElasticSearch 数据库访问接口Spring Java Configure 定义
 */
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.tangzq.repository")
public class ElasticsearchAutoConfiguration  implements DisposableBean {

	private static final Log logger = LogFactory.getLog(ElasticsearchAutoConfiguration.class);

	@Resource
	private Environment environment;

	private Releasable releasable;

	public Client createTransportClient() throws Exception {
		TransportClientFactoryBean factory = new TransportClientFactoryBean();
		factory.setClusterNodes(environment.getProperty("elasticsearch.clusterNodes"));
		factory.setClusterName(environment.getProperty("elasticsearch.clusterName"));
		factory.afterPropertiesSet();
		Client client=factory.getObject();
		this.releasable=client;
		return factory.getObject();
	}

	@Bean
	public ElasticsearchOperations elasticsearchTemplate() {
		try {
			return new ElasticsearchTemplate(createTransportClient());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void destroy() throws Exception {
		if (this.releasable != null) {
			try {
				if (logger.isInfoEnabled()) {
					logger.info("Closing Elasticsearch client");
				}
				try {
					this.releasable.close();
				} catch (NoSuchMethodError ex) {
					// Earlier versions of Elasticsearch had a different
					// method name
					ReflectionUtils.invokeMethod(ReflectionUtils.findMethod(Releasable.class, "release"),this.releasable);
				}
			} catch (final Exception ex) {
				if (logger.isErrorEnabled()) {
					logger.error("Error closing Elasticsearch client: ", ex);
				}
			}
		}
	}
}