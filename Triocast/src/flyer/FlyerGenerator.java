package flyer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
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
		eventPanel.setLayout(new GridLayout(tags.length, 1));
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
		try{
			//assume DAY is only of these values
			timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(new File(day + ".png")))));
		} catch (Exception e) {
			System.out.println("day load lol!");
		}
		try{
			//assume DAY is only of these values
//			timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("date_placeholder.png")))));
			for(int i = 0; i < date.length(); i++)
				if (date.charAt(i) == '0')
				{
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("zero.png")))));
				}
				else if (date.charAt(i) == '1')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("one.png")))));
				else if (date.charAt(i) == '2')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("two.png")))));
				else if (date.charAt(i) == '3')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("three.png")))));
				else if (date.charAt(i) == '4')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("four.png")))));
				else if (date.charAt(i) == '5')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("five.png")))));
				else if (date.charAt(i) == '6')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("six.png")))));
				else if (date.charAt(i) == '7')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("seven.png")))));
				else if (date.charAt(i) == '8')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("eight.png")))));
				else if (date.charAt(i) == '9')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("nine.png")))));
				else if (date.charAt(i) == '/')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(new File("slash.png")))));
		} catch (Exception e) {
			System.out.println("date load lol!");
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
		contentPane.setLayout(new BorderLayout());
		contentPane.add(eventPanel, BorderLayout.EAST);
		contentPane.add(timeDatePanel, BorderLayout.CENTER);
		contentPane.add(locationPanel, BorderLayout.SOUTH);
		contentPane.add(logoPanel, BorderLayout.NORTH);
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
		    component.paint(image.getGraphics());
		    return image;
		  }
}
