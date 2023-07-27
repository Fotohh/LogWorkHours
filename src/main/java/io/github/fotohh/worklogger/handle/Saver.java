package io.github.fotohh.worklogger.handle;

public class Saver {

    public static void save(String line, String type, String defaultKey, StringBuilder builder){
        String s = removeWhiteSpace(line);
        String username = s.replace(type+"=", "");
        if (!(defaultKey.equalsIgnoreCase(username))) {
            line = type + "=" + defaultKey;
        }
        builder.append(line).append("\n");
    }

    private static String removeWhiteSpace(String value){
        return value.replaceAll(" ", "");
    }

}
