# Server

import java.io.*;
import java.nio.file.Files;

public class Uploader implements ActionListener {

    void write(String outputFileName,byte[] input, boolean append,int len) {
        try (OutputStream output = new BufferedOutputStream(new FileOutputStream(outputFileName,append))) {
            output.write(input,0,len);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public byte[] executeAction(ActionEvent actionEvent, boolean append, int len) {
        write(actionEvent.getFilename(), actionEvent.getText(), append, len);
        return null;
    }
}
