package kr.nexparan.louibitTrade.admin;

import kr.nexparan.louibitTrade.model.Board;
import kr.nexparan.louibitTrade.model.Faq;
import kr.nexparan.louibitTrade.repository.BoardRepository;
import kr.nexparan.louibitTrade.repository.FaqRepository;
import kr.nexparan.louibitTrade.service.BoardService;
import kr.nexparan.louibitTrade.service.FaqService;
import kr.nexparan.louibitTrade.validator.BoardValidator;
import kr.nexparan.louibitTrade.validator.FaqValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardValidator boardValidator;

    @Autowired
    private FaqRepository faqRepository;
    @Autowired
    private FaqValidator faqValidator;
    @Autowired
    private FaqService faqService;

    @GetMapping("/noticeForm")
    public String noticeForm(Model model, @RequestParam(required = false) Long id) {
        if(id == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "noticeForm";
    }


    @PostMapping("/noticeForm")
    public String postNoticeForm(@Valid Board board, BindingResult bindingResult, Authentication authentication) {
        boardValidator.validate(board, bindingResult);
        if(bindingResult.hasErrors()) {
            return "noticeForm";
        }
        String username = authentication.getName();
        boardService.save(username, board);
        return "redirect:/notice";
    }

    @GetMapping("/faqForm")
    public String faqForm(Model model, @RequestParam(required = false) Long id) {
        if(id == null) {
            model.addAttribute("faq", new Faq());
        } else {
            Faq faq = faqRepository.findById(id).orElse(null);
            model.addAttribute("faq", faq);
        }
        return "faqForm";
    }

    @PostMapping("/faqForm")
    public String faqForm(@Valid Faq faq, BindingResult bindingResult) {
        faqValidator.validate(faq, bindingResult);
        if(bindingResult.hasErrors()) {
            return "faqForm";
        }
        faqService.save(faq);
        return "redirect:/faq";
    }

}
