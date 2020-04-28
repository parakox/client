package model.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

public class User {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("password")
    private String password;

    @JsonProperty("chat_id")
    private Long chatId;

    @JsonProperty("topic")
    private String topic;

    public static User fromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, User.class);
        }catch(JsonProcessingException e){
            return null;
        }
    }

    public User(String nickname,String password,Long chatId,String topic){
        this.nickname = nickname;
        this.password = password;
        this.chatId = chatId;
        this.topic = topic;
    }

    public User(){

    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
