import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TaskProducer {

	@Autowired
	private TaskProducerConfiguration taskProducerConfiguration;

    // Send new task 
	public void sendNewTask(TaskMessage taskMessage) {
		byte[] data = taskMessage.getEmailId().getBytes();
		
		taskProducerConfiguration.rabbitTemplate().convertAndSend(
				taskProducerConfiguration.tasksQueue, data);
		
	}

}