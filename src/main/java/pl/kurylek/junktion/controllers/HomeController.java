package pl.kurylek.junktion.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.kurylek.junktion.repositories.DocumentRepository;

@Controller
public class HomeController {

    final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private DocumentRepository documentRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView handleHomePage() {
	ModelAndView model = new ModelAndView("index");
	return model;
    }
}
