# Server

import java.util.Arrays;

public class Wrapper {
    String action;
    String filename;
    byte[] text;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getText() {
        return text;
    }

    public void setText(byte[] text) {
        if(text==null)return;
        this.text = new byte[text.length];
        for (int i = 0; i < text.length; i++) {
            this.text[i] = text[i];
        }
    }

    public boolean checkIfReady(){
        if(action==null)return false;
        if(action.equals("list files"))return true;
        if(action.equals("upload")&&filename!=null && text!=null)return true;
        if(action.equals("download")&&filename!=null)return true;
        return false;
    }

    public boolean isReadyForByteArray(){
        if(action==null){
            return false;
        }
        if(action.equals("upload")&&filename!=null){
            return true;
        }
        return false;
    }

    private boolean matchesActions(String action){
        return action.toUpperCase().equals("UPLOAD") || action.toUpperCase().equals("DOWNLOAD") || action.toUpperCase().equals("LIST FILES");
    }

    public void add(String string){
        if(!string.equals("null")) {
            if (action == null && matchesActions(string)) {
                setAction(string);
                return;
            }
            if (action!=null&& filename == null) {
                setFilename(string);
                return;
            }
        }
    }

    public void wipeData(){
        text=null;
    }
}
