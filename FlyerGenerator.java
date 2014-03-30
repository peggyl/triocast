import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FlyerGenerator extends JFrame{
	private static Container contentPane;
	//	private static JPanel tagsPanel;
	private static JPanel eventPanel; //is this the same as tagsPanel?
	/*
	 * consider: what makes an event picture?
	 * consider: make it like Bang! in which events are made up of the tags that are associated with them
	 * a church service would be: a cross?
	 * a church service, with a meal: a cross AND a meal icon
	 */
	private static JPanel timeDatePanel;

	/*
	 * consider: include DAY OF THE WEEK and also CALENDAR DATE
	 */
	private static JPanel locationPanel;

	/*
	 * 
	 */

	private static JPanel logoPanel;	

	/*store all the pictures somewhere, and take from it
	 * HashMap<String, Image> ??
	 * will it have to load the images each time??
	 * nah actually just package all of the images with this thing you don't have to load the whole library each time
	 */

	//private static 

	public FlyerGenerator(String size, String[] tags, String day, String date, String location)
	{
		super();
		/*
		 * assumptions:
		 * size is in format "NUMBERxNUMBER"
		 */
		String[] sizeParse = size.split("x");
		setSize(Integer.parseInt(sizeParse[0]), Integer.parseInt(sizeParse[1]));
		setTitle("");

		//load info

		//put images necessary in each panel

		//**eventPanel!!**
		eventPanel = new JPanel(); //by default, is FlowLayout
		for(int i = 0; i < tags.length; i++)
		{
			if (tags[i].equals(""))
			{
				try{
					eventPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("tag_placeholder.png")))));
				} catch (Exception e) {
					System.out.println("tag load lol!");
				}
			}
			//else if.....
			else
				try{
					eventPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("tag_placeholder.png")))));
				} catch (Exception e) {
					System.out.println("tag load lol!");
				}
		}

		//**locationPanel!!**
		locationPanel = new JPanel();
		if (location.equals(""))
		{

		}
		else
		{
			try{
				locationPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("location_placeholder.png")))));
			} catch (Exception e) {
				System.out.println("location load lol!");
			}
		}

		//**timeDatePanel!!**
		timeDatePanel = new JPanel();
		String[] dateSplit = date.split("/");
		try{
			//		if (day.equals("monday"))
			//		{
			//			locationPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("monday.png")))));
			//		}
			//		else if (day.equals("tuesday"))
			//		{
			//			locationPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("location_placeholder.png")))));
			//		}
			//		else if (day.equals("wednesday"))
			//		{
			//			locationPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("location_placeholder.png")))));
			//		}
			//		else if (day.equals("thursday"))
			//		{
			//			locationPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("location_placeholder.png")))));
			//		}
			//		else if (day.equals("friday"))
			//		{
			//			locationPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("location_placeholder.png")))));
			//		}
			//		else if (day.equals("saturday"))
			//		{
			//			locationPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("location_placeholder.png")))));
			//		}
			//		else if (day.equals("sunday"))
			//		{
			//			locationPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("location_placeholder.png")))));
			//		}

			//assume DAY is only of these values
			timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(new File(day + ".png")))));
		} catch (Exception e) {
			System.out.println("day load lol!");
		}

		//**logoPanel!!**
		logoPanel = new JPanel();
		try {
			logoPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("logo_placeholder.png")))));
		} catch (Exception e) {
			System.out.println("logo load lol!");
		}

		//assemble panels in the window
		contentPane = getContentPane();
		contentPane.add(eventPanel, BorderLayout.CENTER);
		contentPane.add(timeDatePanel, BorderLayout.NORTH);
		contentPane.add(locationPanel, BorderLayout.EAST);
		contentPane.add(logoPanel, BorderLayout.SOUTH);
	}
	
	public static BufferedImage getScreenShot(
		    Component component) {

		    BufferedImage image = new BufferedImage(
		      component.getWidth(),
		      component.getHeight(),
		      BufferedImage.TYPE_INT_RGB
		      );
		    // call the Component's paint method, using
		    // the Graphics object of the image.
		    component.paint( image.getGraphics() );
		    return image;
		  }
}
