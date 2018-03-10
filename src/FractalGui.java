//http://stackoverflow.com/questions/10296685/draw-image-in-jogl2
//http://www3.ntu.edu.sg/home/ehchua/programming/opengl/JOGL2.0.html

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.acl.Group;
import java.util.Random;

import javax.swing.*;
import javax.swing.event.DocumentListener;

import com.jogamp.newt.event.KeyEvent;

public class FractalGui extends JFrame implements ActionListener {

	private JFrame frame;

	private JLabel jl1;
	private JTextField tf1;
	private JButton jb1;
	private JTextField tf2;
	private String text1 = "-.22";
	private String text2 = ".75";
	private JRadioButton jrb1Black;
	private JRadioButton jrb1White;
	private JRadioButton jrb1Yellow;
	private JRadioButton jrb1Orange;
	private JRadioButton jrb1Red;
	private JRadioButton jrb1Pink;
	private JRadioButton jrb1Blue;
	private JRadioButton jrb1Green;
	private JRadioButton jrb1Grey;
	private JRadioButton jrb1Peach;
	private JRadioButton jrb1Purple;
	private JRadioButton jrb1Random;

	private JRadioButton jrb2Black;
	private JRadioButton jrb2White;
	private JRadioButton jrb2Yellow;
	private JRadioButton jrb2Orange;
	private JRadioButton jrb2Red;
	private JRadioButton jrb2Pink;
	private JRadioButton jrb2Blue;
	private JRadioButton jrb2Green;
	private JRadioButton jrb2Grey;
	private JRadioButton jrb2Peach;
	private JRadioButton jrb2Purple;
	private JRadioButton jrb2Random;

	private JPanel radioPanel1 = new JPanel();
	private JPanel radioPanel2 = new JPanel();
	private JPanel insidePanel = new JPanel(new GridBagLayout());
	private JPanel insidePanel1 = new JPanel(new GridBagLayout());

	Random randomGenerator = new Random();

	private Color c1;
	private Color c2;
	private Color random1;
	private Color random2;

	private Color black = new Color(0, 0, 0);
	private Color white = new Color(255, 255, 224);
	Color yellow = new Color(255, 255, 0);
	Color orange = new Color(255, 69, 0);
	Color red = new Color(255, 0, 0);
	Color pink = new Color(255, 20, 147);
	Color blue = new Color(0, 0, 205);
	Color green = new Color(50, 205, 50);
	Color grey = new Color(128, 128, 128);
	Color peach = new Color(240, 230, 140);
	Color purple = new Color(138, 43, 226);

	public FractalGui() {

		GridLayout grid = new GridLayout(0, 1);

		this.frame = new JFrame("Fractal Creator");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container content = frame.getContentPane();
		content.setPreferredSize(new Dimension(350, 350));
		// content.setResizeable(false);
		content.setLayout(new FlowLayout());
		content.setBackground(new Color(0, 128, 255));
		this.insidePanel.setLayout(new GridLayout(1, 2));
		content.add(insidePanel);
		this.insidePanel1.setLayout(grid);
		content.add(insidePanel1);
		grid.setHgap(20);
		grid.setVgap(20);

		this.jb1 = new JButton("Create Fractal");

		insidePanel1.add(jb1);

		ButtonGroup group1 = new ButtonGroup();
		ButtonGroup group2 = new ButtonGroup();

		this.jrb1Random = new JRadioButton("Random");
		this.jrb1Black = new JRadioButton("Black");
		this.jrb1White = new JRadioButton("White");
		this.jrb1Blue = new JRadioButton("Blue");
		this.jrb1Green = new JRadioButton("Green");
		this.jrb1Grey = new JRadioButton("Grey");
		this.jrb1Orange = new JRadioButton("Orange");
		this.jrb1Peach = new JRadioButton("Peach");
		this.jrb1Pink = new JRadioButton("Pink");
		this.jrb1Purple = new JRadioButton("Purple");
		this.jrb1Red = new JRadioButton("Red");
		this.jrb1Yellow = new JRadioButton("Yellow");

		this.jrb2Random = new JRadioButton("Random");
		this.jrb2Black = new JRadioButton("Black");
		this.jrb2White = new JRadioButton("White");
		this.jrb2Blue = new JRadioButton("Blue");
		this.jrb2Green = new JRadioButton("Green");
		this.jrb2Grey = new JRadioButton("Grey");
		this.jrb2Orange = new JRadioButton("Orange");
		this.jrb2Peach = new JRadioButton("Peach");
		this.jrb2Pink = new JRadioButton("Pink");
		this.jrb2Purple = new JRadioButton("Purple");
		this.jrb2Red = new JRadioButton("Red");
		this.jrb2Yellow = new JRadioButton("Yellow");

		group1.add(jrb1Random);
		group1.add(jrb1Black);
		group1.add(jrb1White);
		group1.add(jrb1Blue);
		group1.add(jrb1Green);
		group1.add(jrb1Grey);
		group1.add(jrb1Orange);
		group1.add(jrb1Peach);
		group1.add(jrb1Pink);
		group1.add(jrb1Purple);
		group1.add(jrb1Red);
		group1.add(jrb1Yellow);

		group2.add(jrb2Random);
		group2.add(jrb2Black);
		group2.add(jrb2White);
		group2.add(jrb2Blue);
		group2.add(jrb2Green);
		group2.add(jrb2Grey);
		group2.add(jrb2Orange);
		group2.add(jrb2Peach);
		group2.add(jrb2Pink);
		group2.add(jrb2Purple);
		group2.add(jrb2Red);
		group2.add(jrb2Yellow);

		this.radioPanel1.setLayout(new GridLayout(0, 2));
		this.radioPanel1.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Fractal Color"));

		insidePanel.add(radioPanel1);

		this.radioPanel2.setLayout(new GridLayout(0, 2));
		this.radioPanel2.setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Background Color"));

		insidePanel.add(radioPanel2);

		this.radioPanel2.add(jrb2Random);
		this.radioPanel2.add(jrb2Black);
		this.radioPanel2.add(jrb2White);
		this.radioPanel2.add(jrb2Blue);
		this.radioPanel2.add(jrb2Green);
		this.radioPanel2.add(jrb2Grey);
		this.radioPanel2.add(jrb2Orange);
		this.radioPanel2.add(jrb2Peach);
		this.radioPanel2.add(jrb2Pink);
		this.radioPanel2.add(jrb2Purple);
		this.radioPanel2.add(jrb2Red);
		this.radioPanel2.add(jrb2Yellow);

		this.radioPanel1.add(jrb1Random);
		this.radioPanel1.add(jrb1Black);
		this.radioPanel1.add(jrb1White);
		this.radioPanel1.add(jrb1Blue);
		this.radioPanel1.add(jrb1Green);
		this.radioPanel1.add(jrb1Grey);
		this.radioPanel1.add(jrb1Orange);
		this.radioPanel1.add(jrb1Peach);
		this.radioPanel1.add(jrb1Pink);
		this.radioPanel1.add(jrb1Purple);
		this.radioPanel1.add(jrb1Red);
		this.radioPanel1.add(jrb1Yellow);

		this.jl1 = new JLabel("Set Numbers");
		this.jl1.setOpaque(true);
		jl1.setHorizontalTextPosition(JLabel.CENTER);
		insidePanel1.add(jl1);

		this.tf1 = new JTextField(text1);

		this.tf1.setHorizontalAlignment(JTextField.LEFT);
		insidePanel1.add(tf1);

		this.tf2 = new JTextField(text2);

		this.tf2.setHorizontalAlignment(JTextField.LEFT);
		insidePanel1.add(tf2);

		this.jb1.addActionListener(this);
		this.jb1.setSize(new Dimension(30, 30));

		this.frame.pack();
		this.frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				FractalGui fg = new FractalGui();

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		int r1 = randomGenerator.nextInt(255);
		int g1 = randomGenerator.nextInt(255);
		int b1 = randomGenerator.nextInt(255);

		int r2 = randomGenerator.nextInt(255);
		int g2 = randomGenerator.nextInt(255);
		int b2 = randomGenerator.nextInt(255);

		random1 = new Color(r1, g1, b1);
		random2 = new Color(r2, g2, b2);

		if (random1 == random2)
			random2 = black;
		if (random1 == random2)
			random1 = white;

		if (jrb1Random.isSelected())
			c1 = random1;
		else if (jrb1Black.isSelected())
			c1 = black;
		else if (jrb1Blue.isSelected())
			c1 = blue;
		else if (jrb1Green.isSelected())
			c1 = green;
		else if (jrb1Grey.isSelected())
			c1 = grey;
		else if (jrb1Orange.isSelected())
			c1 = orange;
		else if (jrb1Peach.isSelected())
			c1 = peach;
		else if (jrb1Pink.isSelected())
			c1 = pink;
		else if (jrb1Purple.isSelected())
			c1 = purple;
		else if (jrb1Red.isSelected())
			c1 = red;
		else if (jrb1White.isSelected())
			c1 = white;
		else if (jrb1Yellow.isSelected())
			c1 = yellow;

		if (jrb2Random.isSelected())
			c2 = random2;
		else if (jrb2Black.isSelected())
			c2 = black;
		else if (jrb2Blue.isSelected())
			c2 = blue;
		else if (jrb2Green.isSelected())
			c2 = green;
		else if (jrb2Grey.isSelected())
			c2 = grey;
		else if (jrb2Orange.isSelected())
			c2 = orange;
		else if (jrb2Peach.isSelected())
			c2 = peach;
		else if (jrb2Pink.isSelected())
			c2 = pink;
		else if (jrb2Purple.isSelected())
			c2 = purple;
		else if (jrb2Red.isSelected())
			c2 = red;
		else if (jrb2White.isSelected())
			c2 = white;
		else if (jrb2Yellow.isSelected())
			c2 = yellow;

		if (c1 == null || c2 == null)
			JOptionPane.showMessageDialog(null, "Error , Please Pick A Color");

		GpuImaging gpu = new GpuImaging(Double.parseDouble(tf1.getText()),
				Double.parseDouble(tf2.getText()), c1, c2);

	}

}
