# Server

import java.util.Scanner;

public class Action {
    private ActionListener actionListener;

    public Action(ActionListener actionListener){
        this.actionListener = actionListener;
    }

    public void addActionListener(ActionListener actionListener){
        this.actionListener = actionListener;
    }

    public byte[] doWork (Wrapper wrapper, boolean append, int len) {
        if(actionListener!=null){
            return actionListener.executeAction(new ActionEvent(wrapper), append, len);
        }
        return null;
    }
}
