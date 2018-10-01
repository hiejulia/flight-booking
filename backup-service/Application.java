
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync// enable execute task with async 
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}