import Methods.FaceMethods;
import sun.reflect.generics.scope.MethodScope;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import static org.bytedeco.javacpp.opencv_face.*;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;

/**
 * Created by Marcin on 08.06.2016.
 */
public class FaceRecognition {
    public static void main(String[] args) {

        String trainingDir = "C:/Users/Marcin/Desktop/FaceLogin/baza";
        Mat testImage = imread("C:/Users/Marcin/Desktop/FaceLogin/foty/2.png", CV_LOAD_IMAGE_GRAYSCALE);
        //testImage = FaceMethods.cropToFace(testImage);
        File root = new File(trainingDir);

        FilenameFilter imgFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                name = name.toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".pgm") || name.endsWith(".png");
            }
        };

        File[] imageFiles = root.listFiles(imgFilter);

        MatVector images = new MatVector(imageFiles.length);

        Mat labels = new Mat(imageFiles.length, 1, CV_32SC1);
        IntBuffer labelsBuf = labels.createBuffer();

        int counter = 0;

        for (File image : imageFiles) {
            Mat img = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
            //img = FaceMethods.cropToFace(img);
            int label = Integer.parseInt(image.getName().split("\\-")[0]);

            images.put(counter, img);

            labelsBuf.put(counter, label);

            counter++;
        }

        //FaceRecognizer faceRecognizer = createFisherFaceRecognizer();
        // FaceRecognizer faceRecognizer = createEigenFaceRecognizer();
         FaceRecognizer faceRecognizer = createLBPHFaceRecognizer();
        double confidence[] = new double[1];
        faceRecognizer.train(images, labels);
        int tab[] = new int[1];
        faceRecognizer.predict(testImage,tab,confidence);
        System.out.println(tab[0] + " " + confidence[0]);
    }


}
