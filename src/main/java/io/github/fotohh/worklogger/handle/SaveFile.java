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
                username = %s
                password = %s
                timestamp = %d
                }
                """, username, password, workHours);

        input.append(newData).append("\n");
        FileOutputStream out = new FileOutputStream(file);
        out.write(input.toString().getBytes());

    }

    public void save(WorkProfile workProfile) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder input = new StringBuilder();
        while ((line = fileReader.readLine()) != null) //while line != null
        {
            if (line.contains("{")) {
                input.append(line).append("\n");
                String line1;
                while ((line1 = fileReader.readLine()) != null) {
                    if (line1.contains("username") && line1.contains(workProfile.getUsername())) {
                        String username = line1.replace("username = ", "");
                        if (!(workProfile.getPassword().equalsIgnoreCase(username))) {
                            line1 = "password = " + workProfile.getPassword();
                            input.append(line1).append("\n");
                        }
                    }
                    if (line1.contains("password")) {
                        String password = line1.replace("password = ", "");
                        if (!(workProfile.getPassword().equalsIgnoreCase(password))) {
                            line1 = "username = " + workProfile.getPassword();
                            input.append(line1).append("\n");
                        }
                    } else if (line1.contains("timestamp")) {
                        String timestamp = line1.replace("timestamp =", "");
                        if (!(String.valueOf(workProfile.getWorkHours()).equalsIgnoreCase(timestamp))) {
                            line1 = "timestamp = " + workProfile.getWorkHours();
                            input.append(line1).append("\n");
                        }
                    }else if(line1.contains("}")){
                        input.append(line1).append("\n");
                        break;
                    }

                }
            }
            input.append(line).append('\n'); //write orignal data to line with \n = new line
        }
        //if empty or null line

        FileOutputStream out = new FileOutputStream(file);
        out.write(input.toString().getBytes());
        fileReader.close();
        out.close();
    }

    public WorkProfile loadSave(String username, String password) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            if(line.contains("{")){
                String l1 = scanner.nextLine().replace("username = ", "");
                if(!(l1.equalsIgnoreCase(username))) continue;
                String l2 = scanner.nextLine().replace("password = ", "");
                if(!(l2.equalsIgnoreCase(password))) continue;
                long l3 = Long.parseLong(scanner.nextLine().replace("timestamp = ", ""));
                return new WorkProfile(username,password,l3);
            }
        }
        JPopupMenu popupMenu = new JPopupMenu("No account found!");
        popupMenu.setVisible(true);
        return null;
    }
}
