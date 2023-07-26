package io.github.fotohh.worklogger.user;

import io.github.fotohh.worklogger.Main;
import io.github.fotohh.worklogger.program.Program;
import io.github.fotohh.worklogger.ui.Button;
import io.github.fotohh.worklogger.ui.Popup;
import io.github.fotohh.worklogger.ui.Window;

import javax.swing.*;
import java.io.FileNotFoundException;

public class Login {

    private final Window window;
    private final JTextField usernameField;
    private final JTextField passwordField;

    public Login(Window window){
        this.window =window;
        usernameField = new JTextField();
        passwordField = new JTextField();
    }

    public void open(){
        window.getPanel().removeAll();
        window.getPanel().updateUI();
        window.getPanel().add(usernameField);
        window.getPanel().add(passwordField);
        new Button("Login", e -> attemptLogin(), window);
    }

    private void attemptLogin(){
        String username = usernameField.getText();
        System.out.println(username);
        String password = passwordField.getText();
        System.out.println(password);
        try {
            WorkProfile workProfile = Main.saveFile.loadSave(username, password);
            if(workProfile == null || username == null || password == null) {
                new Popup("No account exists!");
                Main.start();
                return;
            }
            Main.setProfile(workProfile);
            new Program(window, workProfile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
