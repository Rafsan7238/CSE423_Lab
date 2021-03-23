package Lab2;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;

class FirstGLEventListener implements GLEventListener {
	/**
	 * Interface to the GLU library.
	 */
	private GLU glu;

	/**
	 * Take care of initialization here.
	 */
	public void init(GLAutoDrawable gld) {
		GL2 gl = gld.getGL().getGL2();
		glu = new GLU();

		gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		gl.glViewport(-10, -10, 10, 10);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluOrtho2D(-10.0, 10.0, -10.0, 10.0);
	}

	/**
	 * Take care of drawing here.
	 */
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();

		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

		// for drawing individual pixels
		gl.glPointSize(5); //increase pixel size
		gl.glBegin(GL2.GL_POINTS);
		gl.glColor3f(1, 0.5f, 0); //color orange
		
		//printing number 33; make sure values of x and y are within viewport range in init()
		
		dda(gl, -7, 5, -2, 5);
		dda(gl, -2, 0, -2, 5);
		dda(gl, -7, 0, -2, 0);
		dda(gl, -2, -5, -2, 0);
		dda(gl, -7, -5, -2, -5);
		
		gl.glColor3f(1,1,0); //color yellow
		
		dda(gl, 2, 5, 7, 5);
		dda(gl, 7, 0, 7, 5);
		dda(gl, 2, 0, 7, 0);
		dda(gl, 7, -5, 7, 0);
		dda(gl, 2, -5, 7, -5);
		

		gl.glEnd();

	}
	
	public void dda (GL2 gl, double x1, double y1, double x2, double y2) {
		
		double x = x1;
		double y = y1;
		
		gl.glVertex2d(x, y);
		
		double m = (y2 - y1) / (x2 - x1);
		
		if(m > -1 && m < 1) {
			
			while(x < x2) {
								
				x = x + 0.001;
				y = y + 0.001 * m;
				
				gl.glVertex2d(x, y);
				
			}
			
		}
		
		else {
			
			while (y < y2) {
								
				y = y + 0.001;
				x = x + (0.001 / m);
				
				gl.glVertex2d(x, y);
				
			}
			
		}
		
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
	}

	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
	}

	public void dispose(GLAutoDrawable arg0) {

	}
}

public class Rafsan_18301033_Assign02 {

	public static void main(String args[]) {

		// getting the capabilities object of GL2 profile
		final GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);

		// The canvas
		final GLCanvas glcanvas = new GLCanvas(capabilities);
		FirstGLEventListener b = new FirstGLEventListener();
		glcanvas.addGLEventListener(b);
		glcanvas.setSize(400, 400);

		// creating frame
		final JFrame frame = new JFrame("DDA Frame");

		// adding canvas to frame
		frame.add(glcanvas);
		frame.setSize(640, 480);
		frame.setVisible(true);

	}
}