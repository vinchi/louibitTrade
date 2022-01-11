package kr.nexparan.louibitTrade.admin;

import kr.nexparan.louibitTrade.model.Board;
import kr.nexparan.louibitTrade.repository.BoardRepository;
import kr.nexparan.louibitTrade.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
//@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardValidator boardValidator;

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
    public String postNoticeForm(@Valid Board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult);
        if(bindingResult.hasErrors()) {
            return "noticeForm";
        }
        boardRepository.save(board);
        return "redirect:/notice";
    }

}
