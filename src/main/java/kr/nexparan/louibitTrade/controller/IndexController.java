package kr.nexparan.louibitTrade.controller;

import kr.nexparan.louibitTrade.model.Board;
import kr.nexparan.louibitTrade.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/")
    public String index() {
        //log.debug(messageSource.getMessage("hello", null, Locale.getDefault()));
        return "index";
    }

    @GetMapping("/spot")
    public String spot() {
        return "spot";
    }

    @GetMapping("/wallet")
    public String wallet() {
        return "wallet";
    }

    @GetMapping("/service")
    public String service() {
        return "service";
    }

    @GetMapping("/history")
    public String history() {
        return "history";
    }

    @GetMapping("/notice")
    public String notice(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "notice";
    }

    @GetMapping("/faq")
    public String faq() {
        return "faq";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/referral")
    public String referral() {
        return "referral";
    }

    @GetMapping("/fee")
    public String fee() {
        return "fee";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/forgotpassword")
    public String forgotpassword() {
        return "forgotpassword";
    }

    @GetMapping("/error404")
    public String error404() {
        return "error404";
    }


    @RequestMapping(value="/setChangeLanguage")
    public String changLanguage(@RequestParam(required=false)String locale, ModelMap map, HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Locale locales = null;

        if(locale.matches("en")) {
            locales = Locale.ENGLISH;
        } else {
            locales = Locale.KOREA;
        }

        session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locales);

        model.addAttribute("locales", locales);

        return "index";
    }
}
