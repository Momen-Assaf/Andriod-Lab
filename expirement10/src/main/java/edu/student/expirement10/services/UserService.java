package edu.student.expirement10.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.student.expirement10.repository.UserRepository;
import edu.student.expirement10.models.User;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }
    public String addUser(User user) {
        userRepository.save(user);
        return "success";
    }
    public String deleteUser(Integer userId) {
        userRepository.deleteById(userId);
        return "success";
    }
}

