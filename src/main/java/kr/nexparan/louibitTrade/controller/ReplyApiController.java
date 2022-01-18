package kr.nexparan.louibitTrade.controller;

import kr.nexparan.louibitTrade.dto.ResponseDto;
import kr.nexparan.louibitTrade.model.Reply;
import kr.nexparan.louibitTrade.repository.ReplyRepository;
import kr.nexparan.louibitTrade.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
class ReplyApiController {

    @Autowired
    private ReplyService replyService;
    @Autowired
    private ReplyRepository replyRepository;


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/replys")
    List<Reply> all() {
        return replyRepository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/reply")//content, boardId, userId
    public ResponseDto<Integer> save(@RequestBody Reply reply) {
        replyService.save(reply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
//    Reply newReply(@RequestBody Reply newReply) {
//        return replyRepository.save(newReply);
//    }

    // Single item

    @GetMapping("/reply/{id}")
    Reply one(@PathVariable Long id) {
        return replyRepository.findById(id).orElse(null);
    }

    @PutMapping("/reply/{id}")
    Reply replaceReply(@RequestBody Reply newReply, @PathVariable Long id) {

        return replyRepository.findById(id)
                .map(reply -> {
                    reply.setContent(newReply.getContent());
                    return replyRepository.save(reply);
                })
                .orElseGet(() -> {
                    newReply.setId(id);
                    return replyRepository.save(newReply);
                });
    }

    @DeleteMapping("/reply/{id}")
    void deleteReply(@PathVariable Long id) {
        replyRepository.deleteById(id);
    }
}
