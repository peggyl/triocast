package flyer;
import java.io.File;

import javax.imageio.ImageIO;


public class FlyerRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] tags = {"meal", "religious", "", ""};
		FlyerGenerator fg = new FlyerGenerator("600x800", tags, "monday", "03/29", "room");
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