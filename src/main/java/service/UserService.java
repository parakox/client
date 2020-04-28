package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.constants.Constants;
import model.entity.Connector;
import model.entity.User;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.*;
import java.net.URL;
import java.util.List;

public class UserService {

    private static UserService userService = new UserService();

    public static UserService getUserService() {
        return userService;
    }

    private UserService(){

    }

    public User[] findAllByTopic(String topic) throws IOException {
        URL url = new URL(String.format(Constants.GET_USERS_BY_TOPIC,topic));
        StringBuilder data = Connector.get(url);
        return new ObjectMapper().readValue(String.valueOf(data),User[].class);
    }

    public User findById(Long id) throws IOException {
        URL url = new URL(String.format(Constants.GET_USER_BY_ID,id));
        StringBuilder data = Connector.get(url);
        return User.fromJson(String.valueOf(data));
    }

    public User findByNickname(String nickname) throws IOException {
        URL url = new URL(String.format(Constants.GET_USER_BY_NICKNAME,nickname));
        StringBuilder data = Connector.get(url);
        return User.fromJson(String.valueOf(data));
    }

    public void save(User user) throws IOException {
        HttpPost post = new HttpPost(Constants.UPDATE_USER);
        StringEntity postingString = new StringEntity(user.toJson());
        Connector.set(postingString,post);
    }
}
