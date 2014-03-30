import java.io.File;

import javax.imageio.ImageIO;


public class FlyerRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] tags = {"meal", "religious"};
		FlyerGenerator fg = new FlyerGenerator("800x600", tags, "monday", "3/29", "room");
		fg.setVisible(true);

		try {
            // write the image as a PNG
            ImageIO.write(
            		FlyerGenerator.getScreenShot(fg.getContentPane()),
              "png",
              new File("flyer.png"));
          } catch(Exception e) {
            e.printStackTrace();
          }
	}
}