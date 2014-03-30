package flyer;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import json.JSONObject;

/**
 * 
 * @author Everett
 * This will take input data from a form/JSON object and craft a flyer for Urban Ministries of Durham
 * to communicate the times and dates of their events, and what kinds of events they are. It is possibly understandable
 * by both literate and illiterate people, by use of symbols and numbers.
 * 
 * using Douglas Crockford's JSONObject class and other classes necessary to make that function
 */

public class FlyerGenerator extends JFrame{
	private Container contentPane;
	private JPanel eventPanel;
	/*
	 * consider: what makes an event picture?
	 * consider: make it like Bang! in which events are made up of the tags that are associated with them
	 */
	private JPanel timeDatePanel;

	private JPanel logoPanel;

	public FlyerGenerator(JSONObject info)
	{
		super();
		/*
		 * assumptions:
		 * size is in format "NUMBERxNUMBER"
		 */
		String[] sizeParse = info.getString("size").split("x");
		setSize(Integer.parseInt(sizeParse[0]), Integer.parseInt(sizeParse[1]));
		setTitle("");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		//		setUndecorated(true);
		eventPanelSetup((boolean[])info.get("tags"));
		timeDatePanelSetup(info.getString("day"), info.getString("date"), info.getString("time"));

		//**logoPanel!!**
		logoPanel = new JPanel();
		try {
			logoPanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/UMDGrayscale_small.png")))));
		} catch (Exception e) {
			System.out.println("logo load error!");
		}

		//assemble panels in the window
		flyerAssembly();
	}

	public FlyerGenerator(String size, boolean[] tags, String day, String date, String time)
	{
		super();
		/*
		 * assumptions:
		 * size is in format "NUMBERxNUMBER"
		 */
		String[] sizeParse = size.split("x");
		setSize(Integer.parseInt(sizeParse[0]), Integer.parseInt(sizeParse[1]));
		setTitle("");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		//	setUndecorated(true);
		eventPanelSetup(tags);
		timeDatePanelSetup(day, date, time);
		//**logoPanel!!**
		logoPanel = new JPanel();
		try {
			logoPanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/UMDGrayscale_small.png")))));
		} catch (Exception e) {
			System.out.println("logo load error!");
		}

		//assemble panels in the window
		flyerAssembly();
	}

	public static BufferedImage getScreenShot(
			Component component) {

		BufferedImage image = new BufferedImage(
				component.getWidth(),
				component.getHeight(),
				BufferedImage.TYPE_INT_RGB
				);
		component.paint(image.getGraphics());
		return image;
	}

	public void eventPanelSetup(boolean[] tags)
	{
		//**eventPanel!!**
		eventPanel = new JPanel(new GridLayout(1, tags.length)); //by default, is FlowLayout. grid it?
		try {
			if (tags[0])
				eventPanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Meal.png")))));
			else
				eventPanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Meal Grey.png")))));

			if (tags[1])
				eventPanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Religious.png")))));
			else
				eventPanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Religious Grey.png")))));

			if (tags[2])
				eventPanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Education.png")))));
			else
				eventPanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Education Grey.png")))));

			if (tags[3])
				eventPanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Shelter.png")))));
			else
				eventPanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Shelter Grey.png")))));

			if (tags[4])
				eventPanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Budget.png")))));
			else
				eventPanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Budget Grey.png")))));

			if (tags[5])
				eventPanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Donations.png")))));
			else
				eventPanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Donations Grey.png")))));
		} catch (Exception e) {

		}
	}

	public void timeDatePanelSetup(String day, String date, String time)
	{
		//**timeDatePanel!!**
		timeDatePanel = new JPanel(new GridLayout(2, 7));

		try{
			//assume DAY is only of these values
			if (day.equals("Monday"))
				timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/01 Monday.png")))));
			else if (day.equals("Tuesday"))
				timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/02 Tuesday.png")))));
			else if (day.equals("Wednesday"))
				timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/03 Wednesday.png")))));
			else if (day.equals("Thursday"))
				timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/04 Thursday.png")))));
			else if (day.equals("Friday"))
				timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/05 Friday.png")))));
			else if (day.equals("Saturday"))
				timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/06 Saturday.png")))));
			else if (day.equals("Sunday"))
				timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/07 Sunday.png")))));
			timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/empty.png")))));
		} catch (Exception e) {
			System.out.println("day load error!");
		}
		try{
			for(int i = 0; i < date.length(); i++)
				if (date.charAt(i) == '0')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/0.png")))));
				else if (date.charAt(i) == '1')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/1.png")))));
				else if (date.charAt(i) == '2')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/2.png")))));
				else if (date.charAt(i) == '3')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/3.png")))));
				else if (date.charAt(i) == '4')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/4.png")))));
				else if (date.charAt(i) == '5')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/5.png")))));
				else if (date.charAt(i) == '6')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/6.png")))));
				else if (date.charAt(i) == '7')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/7.png")))));
				else if (date.charAt(i) == '8')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/8.png")))));
				else if (date.charAt(i) == '9')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/9.png")))));
				else if (date.charAt(i) == '/')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Slash.png")))));
		} catch (Exception e) {
			System.out.println("number load error!");
		}

		try{
			for(int i = 0; i < time.length(); i++)
				if (time.charAt(i) == '0')
				{
					if (i != 0)
						timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/0.png")))));
					else
						timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/empty.png")))));
				}
				else if (time.charAt(i) == '1')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/1.png")))));
				else if (time.charAt(i) == '2')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/2.png")))));
				else if (time.charAt(i) == '3')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/3.png")))));
				else if (time.charAt(i) == '4')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/4.png")))));
				else if (time.charAt(i) == '5')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/5.png")))));
				else if (time.charAt(i) == '6')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/6.png")))));
				else if (time.charAt(i) == '7')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/7.png")))));
				else if (time.charAt(i) == '8')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/8.png")))));
				else if (time.charAt(i) == '9')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/9.png")))));
				else if (time.charAt(i) == ':')
					timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Colon.png")))));

			if (time.substring(time.length() - 2 , time.length()).equals("am"))
				timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/am.png")))));
			else if (time.substring(time.length() - 2 , time.length()).equals("pm"))
				timeDatePanel.add(new JLabel(new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/pm.png")))));
		} catch (Exception e) {
			System.out.println("number load error!");
		}
	}


	public void flyerAssembly()
	{
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(eventPanel, BorderLayout.EAST);
		contentPane.add(timeDatePanel, BorderLayout.CENTER);

		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(logoPanel, BorderLayout.NORTH);
		topPanel.add(timeDatePanel, BorderLayout.CENTER);
		contentPane.add(topPanel, BorderLayout.NORTH);
		contentPane.add(eventPanel, BorderLayout.SOUTH);
	}

}