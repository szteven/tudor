package hu.elte.szgy.istvan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TudorApp {
    private static Logger log = LoggerFactory.getLogger(TudorApp.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TudorApp.class, args);
    }

/*        @Bean
        public Module hibernate5Module()
        {
            log.info("Enabling Hibernate5Module");
            return new Hibernate5Module();
        }
*/
}

