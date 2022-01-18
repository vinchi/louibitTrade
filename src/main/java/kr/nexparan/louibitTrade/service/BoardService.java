package kr.nexparan.louibitTrade.service;

import kr.nexparan.louibitTrade.model.Board;
import kr.nexparan.louibitTrade.model.Reply;
import kr.nexparan.louibitTrade.model.Role;
import kr.nexparan.louibitTrade.model.User;
import kr.nexparan.louibitTrade.repository.BoardRepository;
import kr.nexparan.louibitTrade.repository.ReplyRepository;
import kr.nexparan.louibitTrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private BoardService boardService;

    @Transactional
    public Board save(String email, Board board) {
        User user = userRepository.findByEmail(email);
        board.setUser(user);
        board.setContent(board.getContent().replace("\r\n", "<br />"));
        return boardRepository.save(board);
    }
}
