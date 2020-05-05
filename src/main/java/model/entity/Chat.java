package model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Set;

public class Chat {

    @JsonProperty("chat_id")
    private Long id;

    @JsonProperty("messages")
    private List<Message> messages;

    @JsonProperty("name")
    private String name;

    public static Chat fromJson(String json) throws JsonProcessingException {
        try {
            return new ObjectMapper().readValue(json, Chat.class);
        }catch (JsonProcessingException e){
            return null;
        }
    }

    public Chat(List<Message> messages,String name){
        this.messages=messages;
        this.name = name;
    }

    public Chat(){

    }

    public Long getId() {
        return id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

}
