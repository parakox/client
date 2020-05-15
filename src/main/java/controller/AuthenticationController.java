package controller;

import model.IoCSingleton.IoCSingleton;
import model.entity.User;
import org.springframework.context.ApplicationContext;
import service.UserService;
import serviceImpl.UserServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AuthenticationController extends JFrame implements ActionListener {
    private UserService userService = UserServiceImpl.getUserService();
    private ApplicationContext applicationContext = IoCSingleton.getApplicationContext();

    private Container c;
    private JLabel title;
    private JLabel name;
    private JLabel password;
    private JTextField tname;
    private JTextField tpassword;
    private JButton register;
    private JButton logIn;

    public AuthenticationController() {
        setTitle("Authentication Form");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        c = getContentPane();
        c.setLayout(null);
        title = new JLabel("Authentication Form");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        c.add(title);

        name = new JLabel("Nickname");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 20);
        name.setLocation(100, 100);
        c.add(name);

        tname = new JTextField();
        tname.setFont(new Font("Arial", Font.PLAIN, 15));
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        c.add(tname);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(100, 150);
        c.add(password);

        tpassword = new JTextField();
        tpassword.setFont(new Font("Arial", Font.PLAIN, 15));
        tpassword.setSize(150, 20);
        tpassword.setLocation(200, 150);
        c.add(tpassword);

        register = new JButton("Register");
        register.setFont(new Font("Arial", Font.PLAIN, 15));
        register.setSize(100, 20);
        register.setLocation(150, 450);
        register.addActionListener(this);
        c.add(register);

        logIn = new JButton("Log In");
        logIn.setFont(new Font("Arial", Font.PLAIN, 15));
        logIn.setSize(100, 20);
        logIn.setLocation(270, 450);
        logIn.addActionListener(this);
        c.add(logIn);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == register) {
            String nickname = tname.getText();
            String password = tpassword.getText();
            try {
                if(nickname.length()>0 && password.length()>0 && userService.findByNickname(nickname)==null) {
                    User user = (User) applicationContext.getBean("user");
                    user.setNickname(nickname);
                    user.setPassword(password);
                    user.setChatId(null);
                    userService.save(user);
                    setVisible(false);
                    dispose();
                    new ChooseChatController(userService.findByNickname(nickname));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            String nickname = tname.getText();
            String password = tpassword.getText();
            try {
                User user = userService.findByNickname(nickname);
                if(user!=null && user.getPassword().equals(password)){
                    setVisible(false);
                    dispose();
                    new ChooseChatController(user);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
