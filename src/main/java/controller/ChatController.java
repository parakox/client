package controller;

import model.IoCSingleton.IoCSingleton;
import model.entity.Chat;
import model.entity.Message;
import model.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import service.ChatService;
import service.MessageService;

public class ChatController extends JFrame implements ActionListener {
    private final ChatService chatService = ChatService.getChatService();
    private final MessageService messageService = MessageService.getMessageService();
    private final ApplicationContext applicationContext = IoCSingleton.getApplicationContext();

    private final User user;
    private Chat chat;

    private JButton sendMessage;
    private JTextField messageBox;
    private JTextArea chatBox;
    private JPanel southPanel;
    private JFrame frame;

    public ChatController(User user,Chat chat) {
        this.user = user;
        this.chat = chat;

        frame = new JFrame("Chat "+chat.getName());
        southPanel = new JPanel();

        frame.getContentPane().add(BorderLayout.SOUTH, southPanel);
        southPanel.setBackground(Color.BLUE);
        southPanel.setLayout(new GridBagLayout());

        messageBox = new JTextField(30);
        sendMessage = new JButton("Send Message");
        chatBox = new JTextArea();
        chatBox.setEditable(false);
        frame.getContentPane().add(BorderLayout.CENTER, chatBox);


        chatBox.setLineWrap(true);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.WEST;
        GridBagConstraints right = new GridBagConstraints();
        right.anchor = GridBagConstraints.EAST;
        right.weightx = 4.0;

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);

        sendMessage.addActionListener(this);
        frame.add(new JScrollPane(chatBox), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(500, 300);
        final long chat_id = chat.getId();
        new Thread(() -> {
            int last = 0;
            while (true) {
                try {
                    Chat chat1 = chatService.findById(chat_id);
                    List<Message> messageList = chat1.getMessages();
                    for (int i = last; i < messageList.size(); i++) {
                        Message message = messageList.get(i);
                        chatBox.append(message.getUserName() + " : " + message.getText()+"\n");
                    }
                    last = messageList.size();
                } catch (IOException e ) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if (messageBox.getText().length() >= 1) {
            try {
                Message message = (Message) applicationContext.getBean("message");
                message.setUserName(user.getNickname());
                message.setChatId(chat.getId());
                message.setText(messageBox.getText());
                messageService.saveMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
            messageBox.setText("");
        }
    }
}
