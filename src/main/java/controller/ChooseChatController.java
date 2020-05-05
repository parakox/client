package controller;

import model.entity.Chat;
import model.entity.User;
import service.ChatService;
import service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ChooseChatController extends JFrame implements ActionListener {
    private ChatService chatService = ChatService.getChatService();
    private UserService userService = UserService.getUserService();

    private User user;

    private Container c;
    private JTextArea chatList;
    private JLabel label;
    private JTextField chatName;
    private JTextField newChatName;
    private JButton moveToChat;
    private JButton createChat;

    public ChooseChatController(User user) throws IOException {
        this.user = user;

        setTitle("List of chats");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        c = getContentPane();
        c.setLayout(null);

        label = new JLabel();
        label.setSize(300,25);
        label.setLocation(300,20);
        c.add(label);

        chatList = new JTextArea();
        chatList.setFont(new Font("Arial", Font.PLAIN, 15));
        chatList.setSize(300, 200);
        chatList.setLocation(300, 50);
        chatList.setEditable(false);
        chatList.setLineWrap(true);
        JPanel panel= new JPanel();
        panel.add(chatList);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(250, 100, 300, 200);
        this.add(scrollPane);

        chatName = new JTextField();
        chatName.setFont(new Font("Arial", Font.PLAIN, 15));
        chatName.setSize(190, 20);
        chatName.setLocation(200, 300);
        c.add(chatName);

        moveToChat = new JButton("Enter chat");
        moveToChat.setFont(new Font("Arial", Font.PLAIN, 15));
        moveToChat.setSize(200, 20);
        moveToChat.setLocation(200, 350);
        moveToChat.addActionListener(this);
        c.add(moveToChat);

        newChatName = new JTextField();
        newChatName.setFont(new Font("Arial", Font.PLAIN, 15));
        newChatName.setSize(190, 20);
        newChatName.setLocation(400, 300);
        c.add(newChatName);

        createChat = new JButton("Create chat");
        createChat.setFont(new Font("Arial", Font.PLAIN, 15));
        createChat.setSize(200, 20);
        createChat.setLocation(400, 350);
        createChat.addActionListener(this);
        c.add(createChat);

        setVisible(true);

        new Thread(() -> {
            int counter = 0;
            while(true) {
                try {
                    Chat[] chats = chatService.findAll();
                    label.setText("Available " + chats.length + " chats:");
                    for(int i=counter;i<chats.length;i++){
                        chatList.append(chats[i].getName()+"\n\n");
                    }
                    counter = chats.length;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==moveToChat){
            try {
                Chat chat = chatService.findByName(chatName.getText());
                if(chat!=null){
                    setVisible(false);
                    dispose();
                    user.setChatId(chat.getId());
                    userService.save(user);
                    new ChatController(user,chat);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else if(e.getSource()==createChat){
            try {
                if(newChatName.getText().length()>=1 && chatService.findByName(newChatName.getText())==null) {
                    chatService.save(new Chat(new ArrayList<>(), newChatName.getText()));
                    chatName.setText("");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
