package com.crio.xlido.Services;

import com.crio.xlido.repositories.EventRepository;
import com.crio.xlido.repositories.QuestionRepository;
import com.crio.xlido.repositories.UserRepository;
import java.util.Collections;
import java.util.List;
import javax.lang.model.util.ElementScanner6;
import com.crio.xlido.Entities.*;

public class QuestionService {
    
    private QuestionRepository questionRepository;
    private UserRepository userRepository;
    private EventRepository eventRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository,EventRepository eventRepository){
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        }

    public Question addQuestion(List<String> tokens){
        Integer eventId = Integer.parseInt(tokens.get(3));
        Integer userId = Integer.parseInt(tokens.get(2));
        if(!userRepository.userIdPresent(userId))
            throw new RuntimeException("User with an id "+userId+" does not exist");
        else if(!eventRepository.isEventPresent(eventId))
            throw new RuntimeException("Event with an id "+eventId+" does not exist");
        String questionContent = tokens.get(1);
        Question question = new Question(eventId, userId, questionContent);
        return questionRepository.save(question);
    }

    public void deleteQuestion(List<String> tokens){
        Integer questionId = Integer.parseInt(tokens.get(1));
        Integer userId = Integer.parseInt(tokens.get(2));
        if(!userRepository.userIdPresent(userId))
            throw new RuntimeException("User with an id "+userId+" does not exist");
        else if(questionRepository.getQuestion(questionId)==null)
            throw new RuntimeException("Question with an id "+questionId+" does not exist");
        else if(!userId.equals(questionRepository.getQuestion(questionId).getUserId()))
            throw new RuntimeException("User with an id "+userId+" is not an author of question with an id "+questionId);
        else
            questionRepository.delete(questionId);
    }

    public void upvoteQuestion(List<String> tokens){
        Integer questionId = Integer.parseInt(tokens.get(1));
        Integer userId = Integer.parseInt(tokens.get(2));
        if(!userRepository.userIdPresent(userId))
            throw new RuntimeException("User with an id "+userId+" does not exist");
        else if(questionRepository.getQuestion(questionId)==null)
            throw new RuntimeException("Question with an id "+questionId+" does not exist");
        else if(userVotedQuestion(userId, questionId))
            throw new RuntimeException("User with an id "+userId+" has already upvoted a question with an id "+questionId);
        else
            questionRepository.upvoteQuestion(questionId, userId);
    }

    public void replyQuestion(List<String> tokens){
        Integer questionId = Integer.parseInt(tokens.get(2));
        Integer userId = Integer.parseInt(tokens.get(3));
        String content = tokens.get(1);
        if(!userRepository.userIdPresent(userId))
            throw new RuntimeException("User with an id "+userId+" does not exist");
        else if(questionRepository.getQuestion(questionId)==null)
            throw new RuntimeException("Question with an id "+questionId+" does not exist");
        else{
            Reply reply = new Reply(content, userId);
            questionRepository.saveReply(questionId, reply);
        }
    }

    public List<Question> getQuestions(List<String> tokens){
        Integer eventId = Integer.parseInt(tokens.get(1));
        if(!eventRepository.isEventPresent(eventId))
            throw new RuntimeException("Event with an id "+eventId+" does not exist");
        else{
            List<Question> questions = questionRepository.getAllQuestions();
            if(tokens.get(2).equals("POPULAR"))
                Collections.sort(questions, (a, b)->{
                    return b.getNoOfVotes() - a.getNoOfVotes();
                });
            else
                Collections.sort(questions, (a,b)->{
                    return b.getQuestionId() - a.getQuestionId();
                });
        return questions;
        }
    }

    public boolean userVotedQuestion(Integer id, Integer questionId){
        User user = userRepository.getUser(id);
        List<Question> questions = user.getUpvotedQuestions();
        if(questions==null)
            return false;
        Question questionToCheck = questionRepository.getQuestion(questionId);
        for(Question question:questions){
            if(question.getQuestionId().equals(questionToCheck.getQuestionId()))
                return true;
        }
        return false;
    }

}
