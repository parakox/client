package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.constants.Constants;
import model.entity.Connector;
import model.entity.User;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.URL;

public interface UserService {
    User[] findAllByTopic(String topic);

    User findById(Long id);

    User findByNickname(String nickname);

    void save(User user);
}
