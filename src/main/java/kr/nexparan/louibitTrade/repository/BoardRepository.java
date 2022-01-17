package kr.nexparan.louibitTrade.repository;

import kr.nexparan.louibitTrade.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByTitle(String title);
    List<Board> findByTitleOrContent(String title, String content);
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
