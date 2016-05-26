import Detection.FaceDetection;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

/**
 * Created by Marcin on 23.05.2016.
 */
public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        FaceDetection detection = new FaceDetection();
        Mat image = Highgui.imread("combine_images.jpg");
        //detection.faceDetection(image);
        detection.openCamera();
    }
}
