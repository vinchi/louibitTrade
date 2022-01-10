package kr.nexparan.louibitTrade.repository;

import kr.nexparan.louibitTrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
