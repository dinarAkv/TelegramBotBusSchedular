package ru.cheb.intercity.bus;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;





@SpringBootApplication
public class Application {



    public static void main(String[] args) throws Throwable {

        SpringApplication.run(Application.class, args);

//        try (CloseableHttpAsyncClient client = HttpAsyncClients.createDefault()) {
//            client.start();
//            Botan botan = new Botan(client, new ObjectMapper());
//            botan.track("1111", "1", ImmutableMap.of("some_metric": 100, "another_metric": 500), "Search").get();
//        }
    }




}
