import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class ImageResizer {
	
	public static void main(String[] args) {
		ImageResize imageResize = new ImageResize();

		try {
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			File input = new File("/home/marjan/Pictures/digits.jpg");
			BufferedImage image = ImageIO.read(input);
			

			byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
			Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
			mat.put(0, 0, data);

			Mat mat1 = new Mat(0, 0, CvType.CV_8UC1);
		
			Imgproc.cvtColor(mat, mat1, Imgproc.COLOR_RGB2GRAY);

			byte[] data1 = new byte[mat1.rows() * mat1.cols() * (int) (mat1.elemSize())];
			mat1.get(0, 0, data1);
			BufferedImage image1 = new BufferedImage(mat1.cols(), mat1.rows(), BufferedImage.TYPE_BYTE_GRAY);
			image1.getRaster().setDataElements(0, 0, mat1.cols(), mat1.rows(), data1);
//			Size size = new Size(100, 100);
//			
//			Imgproc.resize(mat, mat1, size);
//			
			File ouptut = new File("/home/marjan/Pictures/grayscale.jpg");
			ImageIO.write(image1, "jpg", ouptut);
			imageResize.resize();
			System.out.println("Image created");

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
