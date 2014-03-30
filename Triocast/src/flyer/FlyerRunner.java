package flyer;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * 
 * @author Everett
 * A runner that creates a random instance of the FlyerGenerator class and saves its display to an image for printing.
 *
 */

public class FlyerRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JSONObject info = new JSONObject();
		
		boolean[] tags = new boolean[6];
		for(int i = 0; i < tags.length; i++)
			tags[i] = Math.random() < .5;
		
		String time = "", date = "", dayWeek = "";
		
		int month = (int)(Math.random() * 12 + 1);
		int day = (int)(Math.random() * 31);
		
		if (month < 10)
			date += "0";
		date += month + "/";
		if (day < 10)
			date += "0";
		date += day;
		
		int hour = (int)(Math.random() * 12 + 1);
		int minute = (int)(Math.random() * 60);
		if (hour < 10)
			time += "0";
		time += hour + ":";
		if (minute < 10)
			time += "0";
		time += minute;
		if (Math.random() < .5)
			time += "am";
		else
			time += "pm";
		
		switch ((int)(Math.random() * 7))
		{
		case 0: dayWeek = "Sunday"; break;
		case 1: dayWeek = "Monday"; break;
		case 2: dayWeek = "Tuesday"; break;
		case 3: dayWeek = "Wednesday"; break;
		case 4: dayWeek = "Thursday"; break;
		case 5: dayWeek = "Friday"; break;
		case 6: dayWeek = "Saturday"; break;
		}
		
		System.out.println(dayWeek + " " + date + " @ " + time);
		
		
		FlyerGenerator fg = new FlyerGenerator("600x800", tags, dayWeek, date, time);
		
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