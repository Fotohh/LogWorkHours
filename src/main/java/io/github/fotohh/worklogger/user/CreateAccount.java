package io.github.fotohh.worklogger.user;

import io.github.fotohh.worklogger.Main;
import io.github.fotohh.worklogger.ui.Button;
import io.github.fotohh.worklogger.ui.Popup;
import io.github.fotohh.worklogger.ui.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class CreateAccount {

    private final Window window;
    private final JTextField textField = new JTextField();
    private final JTextField passwordField = new JTextField();

    public CreateAccount(Window window) {
        this.window = window;

        textField.setToolTipText("Set your username!");
        passwordField.setToolTipText("Set your password!");

    }

    public void open(){
        window.getPanel().removeAll();
        window.getPanel().updateUI();
        window.setPanel(new JPanel(new GridLayout(3,3)));
        window.getPanel().add(textField);
        window.getPanel().add(passwordField);
        new Button("Submit", this::create, window);
    }

    private void create(ActionEvent event){
        long time = 0;
        String password = passwordField.getText();
        String username = textField.getText();
        try {
            Main.saveFile.createSave(username, password, time);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Popup("Created Account");
        Main.start();
    }
}
