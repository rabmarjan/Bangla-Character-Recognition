import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageResize {
	public void resize() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat im = Imgcodecs.imread("/home/marjan/Pictures/digits.jpg");
		Mat im2 = new Mat();
		Size sz = im.size();
		Imgproc.resize(im, im2, new Size(sz.width=100, sz.height=100));
		Imgcodecs.imwrite("/home/marjan/Pictures/grayscale.jpg", im2);
	}

}
