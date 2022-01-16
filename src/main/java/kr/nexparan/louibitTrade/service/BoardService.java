package kr.nexparan.louibitTrade.service;

import kr.nexparan.louibitTrade.model.Board;
import kr.nexparan.louibitTrade.model.Role;
import kr.nexparan.louibitTrade.model.User;
import kr.nexparan.louibitTrade.repository.BoardRepository;
import kr.nexparan.louibitTrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;


    public Board save(Board board) {
        board.setContent(board.getContent().replace("\r\n", "<br />"));
        return boardRepository.save(board);
    }
}
