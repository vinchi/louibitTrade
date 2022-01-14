package kr.nexparan.louibitTrade.controller;

import kr.nexparan.louibitTrade.model.Board;
import kr.nexparan.louibitTrade.model.Faq;
import kr.nexparan.louibitTrade.model.RoleType;
import kr.nexparan.louibitTrade.model.User;
import kr.nexparan.louibitTrade.repository.BoardRepository;
import kr.nexparan.louibitTrade.repository.FaqRepository;
import kr.nexparan.louibitTrade.repository.UserRepository;
import kr.nexparan.louibitTrade.service.UserService;
import kr.nexparan.louibitTrade.validator.BoardValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private FaqRepository faqRepository;
    @Autowired
    private BoardValidator boardValidator;
    @Autowired
    private UserService userService;

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
    public String notice(Model model, @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false, defaultValue="") String searchText) {
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = 1;
        int endPage = boards.getTotalPages() == 0 ? 1 : boards.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "notice";
    }

    @GetMapping("/noticeView")
    public String noticeView(Model model, @RequestParam(required = true) Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        model.addAttribute("board", board);
        return "noticeView";
    }

    @GetMapping("/faq")
    public String faq(Model model, @PageableDefault(size = 5) Pageable pageable, @RequestParam(required = false, defaultValue="") String searchText) {
        Page<Faq> faqs = faqRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = 1;
        int endPage = faqs.getTotalPages() == 0 ? 1 : faqs.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("faqs", faqs);
        return "faq";
    }

    @GetMapping("/faqView")
    public String faqView(Model model, @RequestParam(required = true) Long id) {
        Faq faq = faqRepository.findById(id).orElse(null);
        model.addAttribute("board", faq);
        return "faqView";
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

    @PostMapping("/signup")
    public String signup(User user) {
        userService.save(user);
        return "redirect:/";
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
