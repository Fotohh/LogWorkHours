package io.github.fotohh.worklogger.handle;

import io.github.fotohh.worklogger.user.WorkProfile;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class SaveFile {

    private final File file;

    public SaveFile(File file){
        this.file = file;
    }

    public void createSave(String username, String password, long workHours) throws IOException {

        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder input = new StringBuilder();
        while ((line = fileReader.readLine()) != null) //while line != null
        {
            input.append(line).append('\n'); //write orignal data to line with \n = new line
        }

        String newData = String.format("""
                {
                username=%s
                password=%s
                timestamp=%d
                }
                """, username, password, workHours);

        input.append(newData).append("\n");
        FileOutputStream out = new FileOutputStream(file);
        out.write(input.toString().getBytes());

    }

    public void saveAndExit(WorkProfile workProfile) throws IOException {
        Scanner scanner = new Scanner(file);
        StringBuilder input = new StringBuilder();
        while (scanner.hasNext()) //while line != null
        {
            String line = scanner.nextLine();
            if (line.contains("{")) {
                input.append(line).append("\n");
                line = scanner.nextLine();
                if (line.contains("username") && line.contains(workProfile.getUsername())) {
                    Saver.save(line, "username", workProfile.getUsername(), input);
                    line = scanner.nextLine();
                } else if (line.contains("password")) {
                    Saver.save(line, "password", workProfile.getPassword(), input);
                    line = scanner.nextLine();
                } else if (line.contains("timestamp")) {
                    Saver.save(line, "timestamp", String.valueOf(workProfile.getWorkHours()), input);
                    line = scanner.nextLine();
                } else if (line.contains("}")) {
                    input.append(line).append('\n');
                    line = scanner.nextLine();
                }
            }
            input.append(line).append('\n'); //write orignal data to line with \n = new line
        }
        //if empty or null line

        FileOutputStream out = new FileOutputStream(file);
        out.write(input.toString().getBytes());
        scanner.close();
        out.close();
    }

    public WorkProfile loadSave(String username, String password) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            if(line.contains("{")){
                String l1 = scanner.nextLine().replace("username=", "");
                if(!(l1.equalsIgnoreCase(username))) continue;
                String l2 = scanner.nextLine().replace("password=", "");
                if(!(l2.equalsIgnoreCase(password))) continue;
                long l3 = Long.parseLong(scanner.nextLine().replace("timestamp=", ""));
                return new WorkProfile(username,password,l3);
            }
        }
        JPopupMenu popupMenu = new JPopupMenu("No account found!");
        popupMenu.setVisible(true);
        return null;
    }
}
