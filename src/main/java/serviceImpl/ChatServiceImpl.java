package serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.constants.Constants;
import model.entity.Chat;
import model.entity.Connector;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import service.ChatService;

import java.io.*;
import java.net.URL;

public class ChatServiceImpl implements ChatService {

    private static ChatService chatService = new ChatServiceImpl();

    public static ChatService getChatService() {
        return chatService;
    }
    private ChatServiceImpl(){

    }

    public Chat[] findAll() throws IOException {
        URL url = new URL(Constants.GET_ALL_CHATS);
        StringBuilder data = Connector.get(url);
        return new ObjectMapper().readValue(String.valueOf(data), Chat[].class);
    }

    public Chat findById(Long id) throws IOException {
        URL url = new URL(String.format(Constants.GET_CHAT_BY_ID,id));
        StringBuilder data = Connector.get(url);
        return Chat.fromJson(String.valueOf(data));
    }

    public Chat findByName(String name) throws IOException {
        URL url = new URL(String.format(Constants.GET_CHAT_BY_NAME,name));
        StringBuilder data = Connector.get(url);
        return Chat.fromJson(String.valueOf(data));
    }

    public void save(Chat chat) throws IOException {
        HttpPost post = new HttpPost(Constants.UPDATE_CHAT);
        StringEntity postingString = new StringEntity(chat.toJson());
        Connector.set(postingString,post);
    }
}
