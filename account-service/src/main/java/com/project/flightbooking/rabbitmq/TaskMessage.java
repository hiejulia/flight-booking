
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskMessage{
	
	private String emailId;

	@JsonProperty("emailId")
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public byte[] toBytes() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
		  out = new ObjectOutputStream(bos);   
		  out.writeObject(this);
		  out.flush();
		  byte[] bytes = bos.toByteArray();
		  return bytes;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		  try {
		    bos.close();
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		}
		return null;
	    }

	
	
}