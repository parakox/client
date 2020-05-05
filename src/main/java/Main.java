
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import Controller.AuthenticationController;
import model.constants.Constants;
import model.entity.User;
import service.UserService;

public class Main {

    public static void main(String[] args) {
        new AuthenticationController();
    }
}