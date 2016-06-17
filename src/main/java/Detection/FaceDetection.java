package Detection;

import org.opencv.core.*;
import org.opencv.highgui.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

import static org.opencv.core.Core.rectangle;
import static org.opencv.highgui.Highgui.imwrite;

public class FaceDetection {

    private String openCVPath = "C:/OpenCV3/opencv/sources/data/haarcascades_cuda/";
    private String openCVPath2 = "C:/OpenCV3/opencv/sources/data/haarcascades/";

    public void faceDetection(Mat image) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        CascadeClassifier faceDetector = new CascadeClassifier(openCVPath + "haarcascade_frontalface_alt.xml");
        CascadeClassifier eyeDetector = new CascadeClassifier(openCVPath + "haarcascade_eye.xml");
        CascadeClassifier mouthDetector = new CascadeClassifier(openCVPath + "haarcascade_smile.xml");
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections); //wykrycie twarzy
        Mat roi = image.submat(faceDetections.toArray()[0]);
        MatOfRect eyes = new MatOfRect();
        eyeDetector.detectMultiScale(roi, eyes); //wykrycie oczu
        MatOfRect mouths = new MatOfRect();
        System.out.println("Kappa");
        mouthDetector.detectMultiScale(roi,mouths, 1.1, 2, Objdetect.CASCADE_FIND_BIGGEST_OBJECT | Objdetect.CASCADE_SCALE_IMAGE, new Size(30, 30), new Size()); //wykrycie ust
        for (Rect rect : eyes.toArray()){
            rectangle(roi, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0,255, 0));
        }
        for (Rect rect : mouths.toArray()){
            rectangle(roi, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 255));
        }
        System.out.println("Zapis do pliku");
        imwrite("output.jpg", roi);
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
