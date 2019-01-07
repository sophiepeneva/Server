# Server

import java.util.ArrayList;
import java.util.List;

public class Protocol {
    public static List<String> operable = new ArrayList<>();

    Wrapper wrapper;

    public Protocol() {
        wrapper = new Wrapper();
    }

    public void addOperable(String operable) {
        wrapper.add(operable);
    }

    public void addContent(byte[] text){
        wrapper.setText(text);
    }

    public boolean checkIfReady() {
        return wrapper.checkIfReady();
    }

    public boolean isReadyForBytesArray(){
        return wrapper.isReadyForByteArray();
    }

    public byte[] act(boolean append, int len) {
        return Command.getAction(wrapper,append, len);
    }

    public void wipeData(){
        wrapper.wipeData();
    }

}
