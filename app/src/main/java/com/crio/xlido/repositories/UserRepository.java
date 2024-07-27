package com.crio.xlido.repositories;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.crio.xlido.Entities.Question;
import com.crio.xlido.Entities.User;

public class UserRepository {
    
    private final Map<Integer, User> data = new HashMap<>();
    private AtomicInteger idCounter = new AtomicInteger(0);


    public User save(User user){
        User newUser = new User(idCounter.incrementAndGet(), user);
        data.putIfAbsent(newUser.getUserId(), newUser);
        return newUser;
    }

    public boolean userIdPresent(Integer id){
        if(!data.containsKey(id))
            return false;
        return true;
    }

    public void saveUpvotedQuestions(Integer id, Question question){
        List<Question> questions = data.get(id).getUpvotedQuestions();
        if(questions == null)
            questions = new ArrayList<>();
        questions.add(question);
        data.get(id).setUpvotedQuestions(questions);
    }

    public User getUser(Integer id){
        return data.get(id);
    }

}
