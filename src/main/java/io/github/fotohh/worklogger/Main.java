package io.github.fotohh.worklogger;

import io.github.fotohh.worklogger.handle.SaveFile;
import io.github.fotohh.worklogger.log.LogFile;
import io.github.fotohh.worklogger.ui.Button;
import io.github.fotohh.worklogger.ui.Window;
import io.github.fotohh.worklogger.user.CreateAccount;
import io.github.fotohh.worklogger.user.Login;
import io.github.fotohh.worklogger.user.WorkProfile;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class Main{

    public static SaveFile saveFile = new SaveFile(createSave());
    public static LogFile logFile = new LogFile(createLog());
    private static Window window;

    private static WorkProfile profile;

    public static void setProfile(WorkProfile profile) {
        Main.profile = profile;
    }

    public static void main(String[] args) {
        start();
    }

    public static void start(){

        if(window != null){
            window.getFrame().setVisible(false);
        }

        window = new Window("Work Logger", 500, 500);

        new Button("Login", e -> new Login(window).open(), window);
        new Button("Create Account", e -> new CreateAccount(window).open(), window);

        window.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if(profile != null)
                        saveFile.save(profile);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        window.open();
    }



    public static File createSave(){

        String userDirectory = System.getProperty("user.dir");

        File dir = new File(userDirectory, "data.save");

        if(!dir.exists()) {
            try {
                dir.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return dir;

    }

    public static File createLog(){

        String userDirectory = System.getProperty("user.dir");

        File dir = new File(userDirectory, "log.txt");

        if(!dir.exists()) {
            try {
                dir.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return dir;

    }




}
