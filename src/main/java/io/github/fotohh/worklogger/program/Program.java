package io.github.fotohh.worklogger.program;

import io.github.fotohh.worklogger.Main;
import io.github.fotohh.worklogger.ui.Button;
import io.github.fotohh.worklogger.ui.Popup;
import io.github.fotohh.worklogger.ui.Window;
import io.github.fotohh.worklogger.user.WorkProfile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Program {

    private final Window window;
    private final WorkProfile workProfile;

    public Program(Window window, WorkProfile workProfile){
        this.window = window;
        this.workProfile = workProfile;

        window.getPanel().removeAll();
        window.setPanel(new JPanel(new GridLayout(6,6)));
        window.getPanel().updateUI();

        load();
    }

    private JTextField textField;

    private void load(){
        try {
            Main.logFile.writeToFile(String.format("""
                            Successfully logged into account!
                            Details:\s
                            - username = %s
                            - password = %s
                            - timestamp = %s
                            """, workProfile.getUsername(), workProfile.getPassword(), workProfile.getWorkHours()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        new Button("Start", e -> start(), window);
        new Button("Stop", e-> stop(), window);
        new Button("Finish", e -> finish(), window);
        textField = new JTextField();
        textField.setToolTipText("Hourly rate");
        textField.setText("8");
        new Button("Set hour rate", this::setHourRate, window);
        window.getPanel().add(textField);
    }

    long hourRate = 8;

    private void setHourRate(ActionEvent event){
        hourRate = Long.parseLong(textField.getText());
    }

    private Timer timer;

    private void start(){
        final int[] i = {0};
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(i[0] == 60)
                    workProfile.addWorkHours(1);
                i[0]++;
                System.out.println(i[0]);
            }
        }, 0, 1000L);
    }

    private void stop(){
        timer.cancel();
    }

    private void finish(){
        long currentWorkHours = workProfile.getWorkHours();
        long money = currentWorkHours * hourRate;
        new Popup("Payload is "+ money);

        workProfile.setWorkHours(0);
    }


}
