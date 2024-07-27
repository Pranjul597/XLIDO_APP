package com.crio.xlido.Services;

import java.util.List;
import com.crio.xlido.Entities.User;
import com.crio.xlido.repositories.UserRepository;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(List<String> tokens){
        String emailId = tokens.get(1);
        String password = tokens.get(2);
        User user = new User(emailId, password);
        return userRepository.save(user);
    }

    

}
