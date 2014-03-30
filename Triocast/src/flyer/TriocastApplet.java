package flyer;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

import json.JSONObject;

public class TriocastApplet extends JApplet{
	public void init() {
		try {
			//Image image = getImage(getCodeBase(), "imgDir/a.gif");


			SwingUtilities.invokeAndWait (new Runnable() {
				public void run() {

					HashMap<String, Image> imageSet = new HashMap<String, Image>();

					imageSet.put("logo", getImage(getDocumentBase(), "UMDGrayscale_small.png"));
					
					imageSet.put("0", getImage(getCodeBase(), "0.png"));
					imageSet.put("1", getImage(getCodeBase(), "1.png"));
					imageSet.put("2", getImage(getCodeBase(), "2.png"));
					imageSet.put("3", getImage(getCodeBase(), "3.png"));
					imageSet.put("4", getImage(getCodeBase(), "4.png"));
					imageSet.put("5", getImage(getCodeBase(), "5.png"));
					imageSet.put("6", getImage(getCodeBase(), "6.png"));
					imageSet.put("7", getImage(getCodeBase(), "7.png"));
					imageSet.put("8", getImage(getCodeBase(), "8.png"));
					imageSet.put("9", getImage(getCodeBase(), "9.png"));
					imageSet.put("Slash", getImage(getCodeBase(), "Slash.png"));
					imageSet.put("Colon", getImage(getCodeBase(), "Colon.png"));
					imageSet.put("am", getImage(getCodeBase(), "am.png"));
					imageSet.put("pm", getImage(getCodeBase(), "pm.png"));
					imageSet.put("empty", getImage(getCodeBase(), "empty.png"));
					
					imageSet.put("Monday", getImage(getCodeBase(), "01 Monday.png"));
					imageSet.put("Tuesday", getImage(getCodeBase(), "02 Tuesday.png"));
					imageSet.put("Wednesday", getImage(getCodeBase(), "03 Wednesday.png"));
					imageSet.put("Thursday", getImage(getCodeBase(), "04 Thursday.png"));
					imageSet.put("Friday", getImage(getCodeBase(), "05 Friday.png"));
					imageSet.put("Saturday", getImage(getCodeBase(), "06 Saturday.png"));
					imageSet.put("Sunday", getImage(getCodeBase(), "07 Sunday.png"));
					
					imageSet.put("budget", getImage(getCodeBase(), "Budget.png"));
					imageSet.put("budget grey", getImage(getCodeBase(), "Budget Grey.png"));
					imageSet.put("donations", getImage(getCodeBase(), "Donations.png"));
					imageSet.put("donations grey", getImage(getCodeBase(), "Donations Grey.png"));
					imageSet.put("education", getImage(getCodeBase(), "Education.png"));
					imageSet.put("education grey", getImage(getCodeBase(), "Education Grey.png"));
					imageSet.put("meal", getImage(getCodeBase(), "Meal.png"));
					imageSet.put("meal grey", getImage(getCodeBase(), "Meal Grey.png"));
					imageSet.put("religious", getImage(getCodeBase(), "Religious.png"));
					imageSet.put("religious grey", getImage(getCodeBase(), "Religious Grey.png"));
					imageSet.put("shelter", getImage(getCodeBase(), "Shelter.png"));
					imageSet.put("shelter grey", getImage(getCodeBase(), "Shelter Grey.png"));

					boolean[] tags = new boolean[6];
					for(int i = 0; i < tags.length; i++)
						tags[i] = Math.random() < .3;
					tags[(int)(Math.random() * tags.length)] = true;

					String time = "", date = "", dayWeek = "";

					int month = (int)(Math.random() * 12 + 1);
					int day = (int)(Math.random() * 31 + 1);

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

					//					System.out.println(dayWeek + " " + date + " @ " + time);

					JSONObject info = new JSONObject();
					info.put("date", date);
					info.put("time", time);
					info.put("size", "600x800");
					info.put("day", dayWeek);
					info.put("tags", tags);
					
					JSONObject info2 = new JSONObject();
					info2.put("day", getParameter("day"));
					info2.put("date", getParameter("date"));
					info2.put("time", getParameter("time"));
					info2.put("size", getParameter("size"));
					
					tags[0] = getParameter("Meal?").equals("true");
					tags[1] = getParameter("Religious?").equals("true");
					tags[2] = getParameter("Education?").equals("true");
					tags[3] = getParameter("Shelter?").equals("true");
					tags[4] = getParameter("Budget?").equals("true");
					tags[5] = getParameter("Donations?").equals("true");
					
					info2.put("tags", tags);

					FlyerGeneratorApplet fg = new FlyerGeneratorApplet(info, imageSet);
					
					TriocastApplet.this.add(fg);
					fg.setVisible(true);
					
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error!");
		}
	}
}
