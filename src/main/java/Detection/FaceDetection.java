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
        CascadeClassifier faceDetector = new CascadeClassifier(FaceDetection.class.getResource("haarcascade_frontalface_alt.xml").getPath());
        Mat image = Highgui.imread(FaceDetection.class.getResource("combine_images.jpg").getPath());
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        System.out.println("Wykryto " + faceDetections.toArray().length + " twarzy");
        for(Rect rect : faceDetections.toArray()){
            Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
        }
        System.out.println("Zapis do pliku");
        Highgui.imwrite("output.jpg", image);
    }
}
