# Server

public class ActionEvent {
    String filename;
    String action;
    byte[] text;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
       if(action!=null){
           this.action = action;
       }
    }

    public byte[] getText() {
        return text;
    }

    public void setText(byte[] text) {
        if(text!=null){
            this.text = text;
        }
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        if (filename != null) {
            this.filename = filename;
        }
    }

    public ActionEvent(String filename){
        setFilename(filename);
    }

    public ActionEvent(Wrapper wrapper){
        setFilename(wrapper.getFilename());
        setText(wrapper.getText());
        setAction(wrapper.getAction());
    }
}
