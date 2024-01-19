package InxhinieriSofti.projekt;

import InxhinieriSofti.projekt.Services.CourseService;
import InxhinieriSofti.projekt.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//për fshirjen e komenteve që jan shkruar para më shumë se 1 viti
@EnableScheduling
public class ProjektApplication {

	public static void main(String[] args) {


		SpringApplication.run(ProjektApplication.class, args);
	}
		@Bean
		public CommandLineRunner init (CourseService courseService){
			return args -> {
				courseService.registerInitalCourses();
			};
		}

	   @Bean
	   public CommandLineRunner init (UserService userService){
		return args -> {
		userService.registerInitialUsers();
		};
	}





}
