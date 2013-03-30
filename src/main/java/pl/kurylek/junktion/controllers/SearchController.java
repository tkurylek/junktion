package pl.kurylek.junktion.controllers;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

    final Logger logger = getLogger(getClass());

    @RequestMapping(value = { "/", "/search" }, method = RequestMethod.GET)
    public ModelAndView handleSearchPage() {
	ModelAndView model = new ModelAndView("search");
	return model;
    }
}