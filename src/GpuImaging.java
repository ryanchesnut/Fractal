
import static javax.media.opengl.GL.GL_DEPTH_TEST;
import static javax.media.opengl.GL.GL_LEQUAL;
import static javax.media.opengl.GL.GL_NICEST;
import static javax.media.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SMOOTH;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.ImageObserver;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.*;

public class GpuImaging implements GLEventListener {

	Julia j1;
	BufferedImage image;
	private static final int CANVAS_WIDTH = 700;
	private static final int CANVAS_HEIGHT = 700;
	int h = 0;
	int w = 0;
	private double num1, num2;
	private Color c1, c2;

	public Color getC1() {
		return c1;
	}

	public void setC1(Color c1) {
		this.c1 = c1;
	}

	public Color getC2() {
		return c2;
	}

	public void setC2(Color c2) {
		this.c2 = c2;
	}

	public GpuImaging() {

	}

	public GpuImaging(double d, double e, Color c1, Color c2) {

		j1 = new Julia(d, e, c1, c2);
		System.out.println(num1);
		System.out.println(num2);
		image = j1.JuliaExample();

	}

	public double getNum1() {
		return num1;
	}

	public void setNum1(double num1) {
		System.out.println("Setting num1" + num1);
		this.num1 = num1;
	}

	public double getNum2() {
		return num2;
	}

	public void setNum2(double num2) {
		System.out.println("Setting num1" + num1);
		this.num2 = num2;
	}

	public static void main(String[] args) {
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);

		Frame frame = new Frame();
		frame.setSize(CANVAS_HEIGHT, CANVAS_WIDTH);

		frame.add(canvas);
		frame.setVisible(true);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		canvas.addGLEventListener(new GpuImaging());

	}

	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		int w = image.getWidth();
		int h = image.getHeight();

		WritableRaster raster = Raster.createInterleavedRaster(
				DataBuffer.TYPE_BYTE, w, h, 4, null);

		ComponentColorModel colorModel = new ComponentColorModel(
				ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] { 8, 8,
						8, 8 }, true, false, ComponentColorModel.TRANSLUCENT,
				DataBuffer.TYPE_BYTE);

		image = new BufferedImage(colorModel, raster, false, null);

		Graphics2D g = image.createGraphics();
		g.drawImage(image, null, null);
		DataBufferByte dukeBuf = (DataBufferByte) raster.getDataBuffer();

		byte[] dukeRGBA = dukeBuf.getData();
		ByteBuffer bb = ByteBuffer.wrap(dukeRGBA);

		bb.position(0);
		bb.mark();
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER,
				GL2.GL_LINEAR);
		gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER,
				GL2.GL_LINEAR);

		gl.glEnable(GL2.GL_TEXTURE_2D);
		gl.glTexEnvf(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_DECAL);
		gl.glBindTexture(GL2.GL_TEXTURE_2D, 13);
		gl.glPixelStorei(GL2.GL_UNPACK_ALIGNMENT, 1);

		gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGBA, w, h, 0,
				GL2.GL_RGBA, GL2.GL_UNSIGNED_BYTE, bb);

		int left = 100;
		int top = 100;

		gl.glBegin(GL2.GL_QUADS);
		gl.glTexCoord2d(0, 0);
		gl.glVertex3f(left, top, 0);
		gl.glTexCoord2d(1, 0);
		gl.glVertex3f(left + w, top, 0);
		gl.glTexCoord2d(1, 1);
		gl.glVertex3f(left + w, top + h, 0);
		gl.glTexCoord2d(0, 1);
		gl.glVertex3f(left, top + h, 0);
		gl.glEnd();
		gl.glFlush();

	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
		gl.glClearDepth(1.0f); // set clear depth value to farthest
		gl.glEnable(GL_DEPTH_TEST); // enables depth testing
		gl.glDepthFunc(GL_LEQUAL); // the type of depth test to do
		gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST); // best
																// perspective
																// correction
		gl.glShadeModel(GL_SMOOTH); // blends colors nicely, and smoothes out
									// lighting

	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
	}

}