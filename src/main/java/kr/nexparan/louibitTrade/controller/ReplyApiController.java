package kr.nexparan.louibitTrade.controller;

import kr.nexparan.louibitTrade.model.Reply;
import kr.nexparan.louibitTrade.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
class ReplyApiController {

    @Autowired
    private ReplyRepository repository;


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/replys")
    List<Reply> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/reply")
    Reply newReply(@RequestBody Reply newReply) {
        return repository.save(newReply);
    }

    // Single item

    @GetMapping("/reply/{id}")
    Reply one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/reply/{id}")
    Reply replaceReply(@RequestBody Reply newReply, @PathVariable Long id) {

        return repository.findById(id)
                .map(reply -> {
                    reply.setContent(newReply.getContent());
                    return repository.save(reply);
                })
                .orElseGet(() -> {
                    newReply.setId(id);
                    return repository.save(newReply);
                });
    }

    @DeleteMapping("/reply/{id}")
    void deleteReply(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
