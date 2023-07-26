package io.github.fotohh.worklogger.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class LogFile {
    private final File file;
    public LogFile(File file){
        this.file = file;
    }
    public void writeToFile(String text) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file, true);
        outputStream.write(text.getBytes());
        outputStream.close();
    }
}
