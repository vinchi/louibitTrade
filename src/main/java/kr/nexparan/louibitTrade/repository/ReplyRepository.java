package kr.nexparan.louibitTrade.repository;

import kr.nexparan.louibitTrade.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
