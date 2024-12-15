package dev.wagnus.ehcache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class SpringBootEhcacheDemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootEhcacheDemoApplication.class, args);
  }

}
