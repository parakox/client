package service;

import model.constants.Constants;
import model.entity.Connector;
import model.entity.Message;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.IOException;

public interface MessageService {
    void saveMessage(Message message) throws IOException;
}
