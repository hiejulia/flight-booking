
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// Task Email
public class TaskMessage{
	
    private String emailId;
    
    private String email;

    private String name;

	@JsonCreator
	public TaskMessage(@JsonProperty("emailId") String emailId) {
		this.emailId = emailId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}