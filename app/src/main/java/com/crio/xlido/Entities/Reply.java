package com.crio.xlido.Entities;

public class Reply {
    
    private String content;
    private Integer userId;

    public Reply(String content, Integer userId){
        this.content = content;
        this.userId = userId;
    }

    public String getContent(){
        return content;
    }

    public Integer getUserId(){
        return userId;
    }
}
