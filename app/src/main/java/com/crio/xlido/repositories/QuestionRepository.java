package com.crio.xlido.repositories;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.crio.xlido.Entities.*;

public class QuestionRepository {
    
    private final Map<Integer, Question> storage = new HashMap<>();
    private AtomicInteger questionId = new AtomicInteger(0);
    
    private UserRepository userRepository;

    public QuestionRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Question save(Question question){
        Question newQuestion = new Question(questionId.incrementAndGet(), question);
        storage.putIfAbsent(newQuestion.getQuestionId(), newQuestion);
        return newQuestion;
    }

    public void delete(Integer questionId){
        storage.remove(questionId);
    }

    public Question getQuestion(Integer questionId){
        return storage.get(questionId);
    }

    public void upvoteQuestion(Integer questionId, Integer userId){
        Question question = getQuestion(questionId);
        userRepository.saveUpvotedQuestions(userId, question);
        increaseUpvotes(questionId);
        getQuestion(questionId).setUpvote();
    }

    public void increaseUpvotes(Integer questionId){
        Integer curr_upvotes = getQuestion(questionId).getNoOfVotes();
        getQuestion(questionId).setNoOfUpvotes(curr_upvotes+1);
    }

    public void saveReply(Integer questionId, Reply reply){
        getQuestion(questionId).setReply(reply);
    }

    public List<Question> getAllQuestions(){
        List<Question> questions = new ArrayList<>();
        for(Map.Entry<Integer, Question> entry:storage.entrySet())
            questions.add(entry.getValue());
        return questions;
    }

}
