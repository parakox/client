package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.constants.Constants;
import model.entity.Chat;
import model.entity.Connector;
import model.entity.Message;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

public class MessageService {

    private static MessageService messageService = new MessageService();

    private MessageService(){

    }

    public static MessageService getMessageService() {
        return messageService;
    }

    public void saveMessage(Message message) throws IOException {
        HttpPost post = new HttpPost(Constants.SAVE_MESSAGE);
        StringEntity postingString = new StringEntity(message.toJson());
        Connector.set(postingString,post);
    }
}
