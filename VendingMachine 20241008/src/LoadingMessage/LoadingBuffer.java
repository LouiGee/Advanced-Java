package LoadingMessage;

public class LoadingBuffer {

    public static void loading(){
        System.out.println("Loading...");
        try {
            // Pause the program for 1 seconds (1000 milliseconds)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // Handle the exception if the thread is interrupted
            e.printStackTrace();
        }
    }

}
