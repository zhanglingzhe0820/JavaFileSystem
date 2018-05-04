package surevil.FileSystem.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PathUtil {
    public final static String TEMP_FILE_NAME = "PROJECT_NAME";

    public static String getTmpPath() {
        java.util.Properties properties = System.getProperties();
        String tempFileName = properties.getProperty("java.io.tmpdir");
        return tempFileName + TEMP_FILE_NAME;
    }

    public static String getDatabasePath() {
        return ResourceUtil.getFilePathUnderRootDirOfJarFileOrClassDir("/data/");
    }

    public static String getSerPath() {
        return ResourceUtil.getFilePathUnderRootDirOfJarFileOrClassDir("/data/ser/");
    }

    public static void initDatabase() {
        String resourcePath = ResourceUtil.getFilePathUnderRootDirOfJarFileOrClassDir("/data");

        File dir = new File(resourcePath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        dir = new File(resourcePath + "/ser");
        if (!dir.exists()) {
            dir.mkdir();
        }

        ArrayList<File> fileArrayList = new ArrayList<>();
        fileArrayList.add(new File(resourcePath + "/user.txt"));
        fileArrayList.add(new File(resourcePath + "/tempUser.txt"));
        fileArrayList.add(new File(resourcePath + "/imageInstance.txt"));
        fileArrayList.add(new File(resourcePath + "/imageMission.txt"));
        fileArrayList.add(new File(resourcePath + "/topic.txt"));

        try (FileWriter writer = new FileWriter(getDatabasePath() + "user.txt")) {
            writer.write("{\"username\":\"999\",\"password\":\"$2a$10$EQezV9FHSbCgagwHb6K8g.o.TmwFjh4wMLSUU.8f7PhSLpBpivhO.\",\"email\":\"456\",\"roles\":[{\"name\":\"ROLE_ADMIN\"}],\"exp\":0,\"credits\":0}\n" +
                    "{\"username\":\"123\",\"password\":\"$2a$10$EQezV9FHSbCgagwHb6K8g.o.TmwFjh4wMLSUU.8f7PhSLpBpivhO.\",\"email\":\"456\",\"roles\":[{\"name\":\"ROLE_WORKER\"}],\"exp\":0,\"credits\":0}\n" +
                    "{\"username\":\"888\",\"password\":\"$2a$10$EQezV9FHSbCgagwHb6K8g.o.TmwFjh4wMLSUU.8f7PhSLpBpivhO.\",\"email\":\"456\",\"roles\":[{\"name\":\"ROLE_REQUESTER\"}],\"exp\":0,\"credits\":0}"
            );
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter(getDatabasePath() + "topic.txt")) {
            writer.write("{\"topicId\":1,\"value\":\"123123\"}\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (File file : fileArrayList) {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
