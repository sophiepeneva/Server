# Server

public class Command {
    public static byte[] getAction(Wrapper wrapper, boolean append, int len) {
        switch (wrapper.getAction().toUpperCase()) {
            case "UPLOAD" : return new Action(new Uploader()).doWork(wrapper, append, len);
            case "DOWNLOAD" : return new Action(new Downloader()).doWork(wrapper,append, len);
            case "LIST FILES" : return new Action(new FileLister()).doWork(wrapper,append, len);
            default:
                System.out.println("Illegal action");
                break;
        }
        return null;
    }
}
