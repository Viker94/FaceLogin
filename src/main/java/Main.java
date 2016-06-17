import Detection.FaceDetection;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import static org.opencv.highgui.Highgui.imread;


public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        FaceDetection detection = new FaceDetection();
        Mat image = imread("combine_images.jpg");
        //detection.faceDetection(image);
        detection.openCamera();
    }
}
