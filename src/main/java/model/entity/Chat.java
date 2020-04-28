package model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Set;

public class Chat {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("messages")
    private List<Message> messageList;

    @JsonProperty("users")
    private Set<Long> userIdList;

    public static Chat fromJson(String json) throws JsonProcessingException {
        try {
            return new ObjectMapper().readValue(json, Chat.class);
        }catch (JsonProcessingException e){
            return null;
        }
    }

    public Chat(List<Message> messageList,Set<Long> userIdList){
        this.messageList=messageList;
        this.userIdList=userIdList;
    }

    public Chat(){

    }

    public Long getId() {
        return id;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public Set<Long> getUserIdList() {
        return userIdList;
    }

    public void addUserId(Long id){
        userIdList.add(id);
    }

    public void addMessage(Message message){
        messageList.add(message);
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

}
