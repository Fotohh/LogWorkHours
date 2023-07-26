package io.github.fotohh.worklogger.ui;

import javax.swing.*;
import java.awt.*;

public class Window {
    private final JFrame jFrame;
    private JPanel jPanel;
    private final String title;
    private final int height;
    private final int width;

    public JPanel getPanel() {
        return jPanel;
    }

    public void setPanel(JPanel jPanel) {
        this.jPanel = jPanel;
        jFrame.add(jPanel);
        jPanel.updateUI();
    }

    public JFrame getFrame() {
        return jFrame;
    }

    public String getTitle() {
        return title;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Window(String title, int width, int height){
        this.title = title;
        this.height = height;
        this.width = width;
        this.jPanel = new JPanel(new GridLayout(3,3));
        jPanel.setVisible(true);
        jFrame = new JFrame(title);
        jFrame.setSize(width,height);
        jFrame.setAutoRequestFocus(true);
        jFrame.add(jPanel);
        jFrame.setResizable(false);
    }

    public void open(){
        jFrame.setVisible(true);
    }
}
