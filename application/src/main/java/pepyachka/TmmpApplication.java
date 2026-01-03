package pepyachka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
				basePackages = "com.pepyachka"
)
public class TmmpApplication
{
	public static void main(String[] args) {
		SpringApplication.run(TmmpApplication.class, args);
	}
}
