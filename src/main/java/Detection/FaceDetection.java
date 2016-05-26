package Detection;

import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

/**
 * Created by Marcin on 26.05.2016.
 */
public class FaceDetection {
    public void faceDetection() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        CascadeClassifier faceDetector = new CascadeClassifier();
        faceDetector.load("C:/OpenCV/opencv/sources/data/haarcascades/haarcascade_frontalface_alt.xml");
        CascadeClassifier eyeDetector = new CascadeClassifier();
        eyeDetector.load("C:/OpenCV/opencv/sources/data/haarcascades/haarcascade_eye.xml");
        Mat image = Highgui.imread("combine_images.jpg");
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        System.out.println("Wykryto " + faceDetections.toArray().length + " twarzy");
        MatOfRect eyes = new MatOfRect();
        eyeDetector.detectMultiScale(image, eyes);
        for(Rect rect : faceDetections.toArray()){
            Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
        }
        for (Rect rect : eyes.toArray()){
            Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255));
        }
        System.out.println("Zapis do pliku");
        Highgui.imwrite("output.jpg", image);
    }
}
