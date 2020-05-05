package model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Message {

    @JsonProperty("message_id")
    private Long id;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("chat_id")
    private Long chatId;

    @JsonProperty("text")
    private String text;


    public Message(String userName,Long chatId,String text){
        this.userName = userName;
        this.chatId = chatId;
        this.text=text;
    }

    public Message(){

    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getUserName() {
        return userName;
    }

    public Long getChatId() {
        return chatId;
    }
}