
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.JFrame;

public class Julia {
	
	FractalGui fg;
	private static final int WIDTH = 700;
	private static final int HEIGHT = 700;
	private boolean[][] values = null;
	private double minX = -1.5;
	private double maxX = 1.5;
	private double minY = -1.5;
	private double maxY = 1.5;
	private BufferedImage image = null;
	private double threshold = 5;
	private int iterations = 50;
	double num1;
	double num2;
	Color c1; 
	Color c2; 
	ComplexNumber c; 

	public Julia(double num1, double num2, Color c1, Color c2) {
		this.num1 = num1; 
		this.num2 = num2; 
		this.c1 = c1; 
		this.c2 = c2; 
	    c = new ComplexNumber(num1, num2); 
	}

	
	public BufferedImage JuliaExample() {
		
	    image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		getValues();
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				if (values[i][j])
					image.setRGB(i, j, c1.getRGB());

				if (!values[i][j])
					image.setRGB(i, j, c2.getRGB());
			}
		}
		JFrame f = new JFrame() {
			public void paint(java.awt.Graphics g) {
					
				g.drawImage(image, 0, 0, null);
			}
		};
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(WIDTH, HEIGHT);
		f.setVisible(true);
		return image; 
	}

	private void getValues() {
		
		values = new boolean[WIDTH][HEIGHT];

		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
		
				double a = (double) i * (maxX - minX) / (double) WIDTH + minX;
				double b = (double) j * (maxY - minY) / (double) HEIGHT + minY;

				values[i][j] = isInSet(new ComplexNumber(a, b));
			}
		}
	}

	 
	private boolean isInSet(ComplexNumber cn) {
		
			
		for (int i = 0; i < iterations; i++) {
			
			cn = cn.square().add(c);
		}
		return cn.magnitude() < threshold * threshold;
	}

	

}
