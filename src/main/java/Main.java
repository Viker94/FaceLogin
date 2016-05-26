import Detection.FaceDetection;

/**
 * Created by Marcin on 23.05.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.library.path"));
        FaceDetection detection = new FaceDetection();
        detection.faceDetection();
    }
}
