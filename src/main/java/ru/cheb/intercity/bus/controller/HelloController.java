package ru.cheb.intercity.bus.controller;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HelloController {


    final static Logger logger = Logger.getLogger(HelloController.class);

    @RequestMapping("/good")
    public String hello(@RequestParam String relUrl){



        logger.info("GOOD PAGE HAS CALLED. relUrl = " + relUrl);

        return "base";
    }
}
