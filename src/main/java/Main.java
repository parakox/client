import model.entity.Chat;
import model.entity.Message;
import model.entity.User;
import service.ChatService;
import service.UserService;

import java.io.IOException;
import java.util.*;

public class Main {

    private static UserService userService = UserService.getUserService();

    private static ChatService chatService = ChatService.getChatService();

    public static void main(String... args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        User user = null;
        while(user == null) {
            System.out.print("\ntype 1 if you want to create new user and 2 if you want to log in: ");
            int mode = scanner.nextInt();
            System.out.print("\nyour nickname: ");
            String nickname = scanner.next();
            System.out.print("\nyour password: ");
            String password = scanner.next();
            if(mode==1){
                if(userService.findByNickname(nickname)==null){
                    userService.save(new User(nickname,password,null,null));
                    user = userService.findByNickname(nickname);
                    System.out.print("\nyou registered new account and logged as "+user.getNickname());
                }else{
                    System.out.println("\nuser with this nickname already exists");
                }
            }else{
                User user1 = userService.findByNickname(nickname);
                if(user1!=null && user1.getPassword().equals(password)){
                    user = user1;
                    System.out.println("\nyou logged as "+user.getNickname());
                }else{
                    System.out.print("\nwrong credentials");
                }
            }
        }
        System.out.print("\nyour topic : ");
        String topic = scanner.next();
        user.setTopic(topic);
        userService.save(user);
        System.out.print("\nsearching interlocutor for you...");
        while(user.getChatId()==null){
            User[] usersByTopic = userService.findAllByTopic(user.getTopic());
            boolean flag = false;
            for(User user1:usersByTopic){
                if(!user.equals(user1) && user1.getChatId()==null){
                    Chat chat1 = new Chat(new ArrayList<>(),new HashSet<>());
                    chat1.addUserId(user.getId());
                    chat1.addUserId(user1.getId());
                    chatService.save(chat1);
                    Chat[] chats = chatService.findAll();
                    for(int i=chats.length-1;i>=0;i--){
                        if(chats[i].getUserIdList().contains(user.getId())){
                            user.setChatId(chats[i].getId());
                            user.setTopic(null);
                            user1.setChatId(chats[i].getId());
                            user1.setTopic(null);
                            userService.save(user);
                            userService.save(user1);
                            flag = true;
                            break;
                        }
                    }
                }
                if(flag){
                    break;
                }
            }
        }
        Chat chat;
        final long id = user.getChatId();
        final User userHelper = user;
        new Thread(() -> {
            try {
                update(id,userHelper);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        while(true){
            chat = chatService.findById(user.getChatId());
            System.out.print("\nwrite your message : ");
            String mess = scanner.next();
            Message message = new Message(user.getId(),mess);
            chat.addMessage(message);
            chatService.save(chat);
        }
    }
    public static void update(long id,User user) throws IOException {
        int length = 0;
        while(true){
            Chat chat = chatService.findById(id);
            List<Message> messages = chat.getMessageList();
            for(int i = length;i<messages.size();i++){
                if(!messages.get(i).getUserId().equals(user.getId())){
                    System.out.println("\nuser"+messages.get(i).getUserId()+" : "+messages.get(i).getText());
                }
            }
            length = messages.size();
        }
    }
}

