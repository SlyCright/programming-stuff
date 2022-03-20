package educationalproject.programmingstuff;

import educationalproject.programmingstuff.service.MainService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProgrammingStuffApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ProgrammingStuffApplication.class, args);
        MainService mainService=(MainService) applicationContext.getBean("MainService");
        mainService.prepareTestData();
    }

}
