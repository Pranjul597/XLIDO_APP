package com.crio.xlido.Entities;

import java.util.List;

public class User {

    private Integer userId;
    private String emailId;
    private String password;
    private List<Question> upvotedQuestions;

    public User(String emailId, String password){
        userId = null;
        this.upvotedQuestions = null;
        this.emailId = emailId;
        this.password = password;
    }

    public User(int userId, User user){
        this.upvotedQuestions = user.upvotedQuestions;
        this.userId = userId;
        this.emailId = user.getEmailId();
        this.password = user.password;
    }

    public Integer getUserId(){
        return userId;
    }
    
    public String getEmailId(){
        return emailId;
    }

    public void setUpvotedQuestions(List<Question> questions){
        this.upvotedQuestions = questions;
    }

    public List<Question> getUpvotedQuestions(){
        return upvotedQuestions;
    }
    
}
