package com.jensbackvall.alicewordcountspring.controllers;

import com.jensbackvall.alicewordcountspring.services.CountersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    private CountersService countersService;

    @RequestMapping(value = {"/index", "/"})
    public ModelAndView start() {
        ModelAndView indexModel = new ModelAndView("index");
        return indexModel;
    }
}
