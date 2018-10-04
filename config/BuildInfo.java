
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "buildInfo")
@PropertySource({ 
	  "classpath:buildInfo.properties"
	})
@Component
public class BuildInfo {

	public BuildInfo() {
		super();
	}
	
	private String apiVersion = "1.0";
	
	private String version;
	
	private String tag;
	
	private String date;

    private String host;

    private String port;
    
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	@Override
	public String toString() {
		StringBuffer versionStr = new StringBuffer();
		versionStr.append("API Version :" +apiVersion+"\r\n");
		versionStr.append("Release Version :" +version+"\r\n");
		versionStr.append("Release Tag :" +tag+"\r\n");
		versionStr.append("Release Date :" +date+"\r\n");
		
		return versionStr.toString();
	}
	
}
	

