package kr.nexparan.louibitTrade.repository;

import kr.nexparan.louibitTrade.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph("User.fetchBoard")
    List<User> findAll();

    User findByEmail(String email);

    //select * from user where username=1?;
    Optional<User> findByUsername(String username);

    User getByUsername(String name);
}
