package edu.student.expirement10.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.student.expirement10.models.User;
public interface UserRepository extends JpaRepository<User, Integer> {
}