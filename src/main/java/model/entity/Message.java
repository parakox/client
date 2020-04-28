package model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("text")
    private String text;

    public Message(Long userId,String text){
        this.userId=userId;
        this.text=text;
    }

    public Message(){

    }

    public String getText() {
        return text;
    }

    public Long getUserId() {
        return userId;
    }
}
