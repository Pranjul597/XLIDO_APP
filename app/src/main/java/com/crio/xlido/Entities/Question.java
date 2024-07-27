package com.crio.xlido.Entities;

public class Question {
    
    private Integer questionId;
    private Integer eventId;
    private Integer userId;
    private String questionContent;
    private boolean upvotedStatus;
    private Integer noOfUpvotes;
    private Reply reply;

    public Question(Integer eventId, Integer userId, String questionContent){
        this.questionId = null;
        this.eventId = eventId;
        this.userId = userId; 
        this.questionContent = questionContent;
        this.upvotedStatus = false;
        this.noOfUpvotes = 0;
        this.reply = null;
    }

    public Question(Integer questionId, Question question){
        this.questionId = questionId;
        this.eventId = question.getEventId();
        this.userId = question.getUserId();
        this.questionContent = question.getQuestionContent();
        this.upvotedStatus = question.getUpvotedStatus();
        this.noOfUpvotes = question.getNoOfVotes();
        this.reply = question.getReply();
    }

    public Integer getQuestionId(){
        return questionId;
    }

    public Integer getEventId(){
        return eventId;
    }

    public Integer getUserId(){
        return userId;
    }

    public String getQuestionContent(){
        return questionContent;
    }

    public boolean getUpvotedStatus(){
        return upvotedStatus;
    }

    public void setUpvote(){
        this.upvotedStatus = true;
    }

    public void setNoOfUpvotes(Integer upvotes){
        this.noOfUpvotes = upvotes;
    }

    public void setReply(Reply reply){
        this.reply = reply;
    }

    public Integer getNoOfVotes(){
        return noOfUpvotes;
    }

    public Reply getReply(){
        return reply;
    }

}
