package serviceImpl;

import model.constants.Constants;
import model.entity.Connector;
import model.entity.Message;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import service.MessageService;

import java.io.IOException;

public class MessageServiceImpl implements MessageService {

    private static MessageService messageServiceImpl = new MessageServiceImpl();

    private MessageServiceImpl(){

    }

    public static MessageService getMessageServiceImpl() {
        return messageServiceImpl;
    }

    public void saveMessage(Message message) throws IOException {
        HttpPost post = new HttpPost(Constants.SAVE_MESSAGE);
        StringEntity postingString = new StringEntity(message.toJson());
        Connector.set(postingString,post);
    }
}
