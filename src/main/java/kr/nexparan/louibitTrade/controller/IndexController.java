package kr.nexparan.louibitTrade.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/")
    public String index() {
        //log.debug(messageSource.getMessage("hello", null, Locale.getDefault()));
        return "index";
    }
}
