# Server

import java.io.*;
import java.nio.file.Paths;

public class Downloader implements ActionListener{

    public static boolean exists(String path, String filename) {

        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) {
            return false;
        }

        for (File f : list) {
            if (f.isDirectory()) {
                exists(f.getAbsolutePath(), filename);
            } else {

                if (f.getAbsoluteFile().toString().contains(filename)) {
                    return true;
                }
            }
        }
        return false;
    }

    byte[] readBytes(String inputFileName) {

        if(!exists(Paths.get(".").toAbsolutePath().toString(),inputFileName)){
            return "file does not exist".getBytes();
        }

        File file = new File(inputFileName);
        byte[] result = new byte[(int) file.length()];
        try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
            int totalBytesRead = 0;
            while (totalBytesRead < result.length) {
                int bytesRemaining = result.length - totalBytesRead;
                int bytesRead = input.read(result, totalBytesRead, bytesRemaining);
                if (bytesRead > 0) {
                    totalBytesRead = totalBytesRead + bytesRead;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public byte[] executeAction(ActionEvent actionEvent, boolean append, int len) {
        return readBytes(actionEvent.getFilename());
    }
}
