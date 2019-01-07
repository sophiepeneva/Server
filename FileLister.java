# Server

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileLister implements ActionListener {

   String files = "";

    public void getFileDirectory( String path) {

        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null){
            return;
        }

        for (File f : list) {
            if (f.isDirectory()) {
                getFileDirectory(f.getAbsolutePath());
            } else {
                files+=path;
                files+=" ";
            }
        }
    }


    @Override
    public byte[] executeAction(ActionEvent actionEvent, boolean append, int len) {
        getFileDirectory(Paths.get(".").toAbsolutePath().toString());
        return files.getBytes();
    }
}
