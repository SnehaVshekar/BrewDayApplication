package edu.sergradcapstone.groupseven.brewday.repositories;

import edu.sergradcapstone.groupseven.brewday.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByEmail(String email);
}
