package bi.lunch;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class BiLunchOrderingToolsApplication {
	private static final Logger log = Logger.getLogger(BiLunchOrderingToolsApplication.class.getName());

	public static void main(String[] args) {
//		// Init log
//		PropertyConfigurator.configurce("resource/log4j.properties");
		log.setLevel(Level.toLevel("DEBUG"));
		ConsoleAppender a = (ConsoleAppender) Logger.getRootLogger().getAppender("stdout");
		a.setLayout(new PatternLayout("%d{dd/MM/yyyy HH:mm:ss} %5p [%t] %c{1}:%L - %m%n"));
		log.info("Logger initialized");
//		// End init log
		SpringApplication.run(BiLunchOrderingToolsApplication.class, args);
	}

}
