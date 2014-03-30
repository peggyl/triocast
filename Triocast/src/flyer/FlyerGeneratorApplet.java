package flyer;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

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

public class FlyerGeneratorApplet extends JPanel{
	private JPanel eventPanel;
	/*
	 * consider: what makes an event picture?
	 * consider: make it like Bang! in which events are made up of the tags that are associated with them
	 */
	private JPanel timeDatePanel;

	private JPanel logoPanel;

	HashMap<String, Image> imageSet = new HashMap<String, Image>();

	public FlyerGeneratorApplet(JSONObject info, HashMap<String, Image> iS)
	{
		super();
		imageSet = new HashMap<String, Image> (iS);
		/*
		 * assumptions:
		 * size is in format "NUMBERxNUMBER"
		 */
		String[] sizeParse = info.getString("size").split("x");
		setSize(Integer.parseInt(sizeParse[0]), Integer.parseInt(sizeParse[1]));
//		setTitle("");
//		setResizable(false);
		try {
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		} catch (Exception e) {
			
		}
		//		setUndecorated(true);
		eventPanelSetup((boolean[])info.get("tags"));
		timeDatePanelSetup(info.getString("day"), info.getString("date"), info.getString("time"));

		//**logoPanel!!**
		logoPanel = new JPanel();
		logoPanel.add(new JLabel(new ImageIcon(imageSet.get("logo"))));

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
		if (tags[0])
			eventPanel.add(new JLabel(new ImageIcon(imageSet.get("meal"))));
		else
			eventPanel.add(new JLabel(new ImageIcon(imageSet.get("meal grey"))));

		if (tags[1])
			eventPanel.add(new JLabel(new ImageIcon(imageSet.get("religious"))));
		else
			eventPanel.add(new JLabel(new ImageIcon(imageSet.get("religious grey"))));

		if (tags[2])
			eventPanel.add(new JLabel(new ImageIcon(imageSet.get("education"))));
		else
			eventPanel.add(new JLabel(new ImageIcon(imageSet.get("education grey"))));

		if (tags[3])
			eventPanel.add(new JLabel(new ImageIcon(imageSet.get("shelter"))));
		else
			eventPanel.add(new JLabel(new ImageIcon(imageSet.get("shelter grey"))));

		if (tags[4])
			eventPanel.add(new JLabel(new ImageIcon(imageSet.get("budget"))));
		else
			eventPanel.add(new JLabel(new ImageIcon(imageSet.get("budget grey"))));

		if (tags[5])
			eventPanel.add(new JLabel(new ImageIcon(imageSet.get("donations"))));
		else
			eventPanel.add(new JLabel(new ImageIcon(imageSet.get("donations grey"))));
	}

	public void timeDatePanelSetup(String day, String date, String time)
	{
		//**timeDatePanel!!**
		timeDatePanel = new JPanel(new GridLayout(2, 7));
		timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get(day))));
		timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("empty"))));
		for(int i = 0; i < date.length(); i++)
			if (date.charAt(i) == '0')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("0"))));
			else if (date.charAt(i) == '1')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("1"))));
			else if (date.charAt(i) == '2')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("2"))));
			else if (date.charAt(i) == '3')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("3"))));
			else if (date.charAt(i) == '4')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("4"))));
			else if (date.charAt(i) == '5')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("5"))));
			else if (date.charAt(i) == '6')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("6"))));
			else if (date.charAt(i) == '7')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("7"))));
			else if (date.charAt(i) == '8')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("8"))));
			else if (date.charAt(i) == '9')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("9"))));
			else if (date.charAt(i) == '/')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("Slash"))));

		for(int i = 0; i < time.length() - 2; i++)
			if (time.charAt(i) == '0')
			{
				if (i != 0)
					timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("0"))));
				else
					timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("empty"))));
			}
			else if (time.charAt(i) == '1')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("1"))));
			else if (time.charAt(i) == '2')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("2"))));
			else if (time.charAt(i) == '3')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("3"))));
			else if (time.charAt(i) == '4')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("4"))));
			else if (time.charAt(i) == '5')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("5"))));
			else if (time.charAt(i) == '6')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("6"))));
			else if (time.charAt(i) == '7')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("7"))));
			else if (time.charAt(i) == '8')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("8"))));
			else if (time.charAt(i) == '9')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("9"))));
			else if (time.charAt(i) == ':')
				timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("Colon"))));

		if (time.substring(time.length() - 2 , time.length()).equals("am"))
			timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("am"))));
		else if (time.substring(time.length() - 2 , time.length()).equals("pm"))
			timeDatePanel.add(new JLabel(new ImageIcon(imageSet.get("pm"))));
	}

	public void flyerAssembly()
	{
		this.setLayout(new BorderLayout());
		this.add(eventPanel, BorderLayout.EAST);
		this.add(timeDatePanel, BorderLayout.CENTER);
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(logoPanel, BorderLayout.NORTH);
		topPanel.add(timeDatePanel, BorderLayout.CENTER);
		this.add(topPanel, BorderLayout.NORTH);
		this.add(eventPanel, BorderLayout.SOUTH);
	}

}