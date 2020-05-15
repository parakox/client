package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.constants.Constants;
import model.entity.Chat;
import model.entity.Connector;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.URL;

public interface ChatService {
    Chat[] findAll() throws IOException;

    Chat findById(Long id) throws IOException;

    Chat findByName(String name) throws IOException;

    void save(Chat chat) throws IOException;
}
