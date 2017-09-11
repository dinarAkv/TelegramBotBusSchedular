package ru.cheb.intercity.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.cheb.intercity.bus.constants.ControllerConstants;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SchedulerController {


    @RequestMapping(method = RequestMethod.GET, value = ControllerConstants.schedularRelationalUrl)
    public String showScheduler(@RequestParam(required = true) String sourceRelationalUrl,
                                HttpServletRequest request)
    {


        return "scheduler";
    }




}
