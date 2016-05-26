package Detection;

import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

/**
 * Created by Marcin on 26.05.2016.
 */
public class FaceDetection {

    private String openCVPath = "C:/OpenCV/opencv/sources/data/haarcascades/";

    public void faceDetection(Mat image) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        CascadeClassifier faceDetector = new CascadeClassifier(openCVPath + "haarcascade_frontalface_alt.xml");
        CascadeClassifier eyeDetector = new CascadeClassifier(openCVPath + "haarcascade_eye_tree_eyeglasses.xml");
        CascadeClassifier mouthDetector = new CascadeClassifier(openCVPath + "haarcascade_mcs_mouth.xml");
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections); //wykrycie twarzy
        Mat roi = image.submat(faceDetections.toArray()[0]);
        MatOfRect eyes = new MatOfRect();
        eyeDetector.detectMultiScale(roi, eyes); //wykrycie oczu
        MatOfRect mouths = new MatOfRect();
        System.out.println("Kappa");
        mouthDetector.detectMultiScale(roi,mouths, 1.1, 2, Objdetect.CASCADE_FIND_BIGGEST_OBJECT | Objdetect.CASCADE_SCALE_IMAGE, new Size(30, 30), new Size()); //wykrycie ust
        for (Rect rect : eyes.toArray()){
            Core.rectangle(roi, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0,255, 0));
        }
        for (Rect rect : mouths.toArray()){
            Core.rectangle(roi, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 255));
        }
        System.out.println("Zapis do pliku");
        Highgui.imwrite("output.jpg", roi);
    }

    public void openCamera(){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0);
        if(!camera.isOpened()){
            System.out.println("Nie otwarto kamery");
        } else {
            Mat image = new Mat();
            camera.read(image);
            faceDetection(image);
            camera.release();

        }
    }
}
