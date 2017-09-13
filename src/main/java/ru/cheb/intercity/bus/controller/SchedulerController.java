package ru.cheb.intercity.bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.cheb.intercity.bus.constants.ControllerConstants;
import ru.cheb.intercity.bus.constants.PropertyConstants;
import ru.cheb.intercity.bus.helper.PropertiesHelper;
import ru.cheb.intercity.bus.parsers.BusStationSchedulerDescription;
import ru.cheb.intercity.bus.parsers.BusStationSchedulerParser;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SchedulerController {

//    http://localhost:8080/scheduler?sourceRelationalUrl=/passengers/raspisanie/tsav/&description=%D0%A7%D0%B5%D0%B1%D0%BE%D0%BA%D1%81%D0%B0%D1%80%D1%8B%20-%20%D0%90%D0%92%20%22%D0%A6%D0%B5%D0%BD%D1%82%D1%80%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9%22
    @RequestMapping(method = RequestMethod.GET, value = ControllerConstants.schedularRelationalUrl)
    public ModelAndView showScheduler(@RequestParam String sourceRelationalUrl,
                                      @RequestParam String description)
    {
        String htmlTable = BusStationSchedulerParser.getScheduler(sourceRelationalUrl);
        String htmlSchedulerDescription = BusStationSchedulerDescription
                                                    .getSchedulerDescription(sourceRelationalUrl);

        ModelAndView mav = new ModelAndView("scheduler");
        mav.addObject("tableDescription", description);
        mav.addObject("schedulerTable", htmlTable);
        mav.addObject("schedulerTableDescription", htmlSchedulerDescription);

        return mav;
    }




}
