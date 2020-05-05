package model.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;


public class User {

    @JsonProperty("user_id")
    private Long id;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("password")
    private String password;

    @JsonProperty("chat_id")
    private Long chatId;


    public User(String nickname,String password,Long chatId){
        this.nickname = nickname;
        this.password = password;
        this.chatId = chatId;
    }

    public User(){

    }

    public static User fromJson(String json) throws JsonProcessingException {
        try {
            return new ObjectMapper().readValue(json, User.class);
        }catch (JsonProcessingException e){
            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
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
