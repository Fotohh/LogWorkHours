package io.github.fotohh.worklogger.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Button {

    JButton button;

    public Button(String title, ActionListener actionListener, Window window){
        button = new JButton(title);
        button.addActionListener(actionListener);
        window.getPanel().add(getButton());
    }

    public JButton getButton() {
        return button;
    }
}
