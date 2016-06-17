package Methods;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacpp.opencv_objdetect.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

/**
 * Created by Marcin on 09.06.2016.
 */
public class FaceMethods {
    private static String openCVPath = "C:/OpenCV3/opencv/sources/data/haarcascades_cuda/";

    public static Mat cropToFace(Mat image){
        CascadeClassifier faceDetector = new CascadeClassifier(openCVPath + "haarcascade_frontalface_alt.xml");
        RectVector vector = new RectVector();
        faceDetector.detectMultiScale(image,vector);
        Mat roi = image.apply(vector.get(0));
        rectangle(image,vector.get(0), new Scalar(0, 255, 0, 1));
        return roi;
    }
}
