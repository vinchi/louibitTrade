package kr.nexparan.louibitTrade.service;

import kr.nexparan.louibitTrade.model.Board;
import kr.nexparan.louibitTrade.model.Reply;
import kr.nexparan.louibitTrade.model.User;
import kr.nexparan.louibitTrade.repository.BoardRepository;
import kr.nexparan.louibitTrade.repository.ReplyRepository;
import kr.nexparan.louibitTrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public int save(Reply reply) {
        try {
            replyRepository.save(reply);
            return 1;
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("UserService : save() : " + e.getMessage());
        }

        return -1;
    }
}
