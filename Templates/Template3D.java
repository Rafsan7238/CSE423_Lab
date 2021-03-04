import static com.jogamp.opengl.GL.GL_POINTS;

import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class ThirdGLEventListener implements GLEventListener, KeyListener {
    /**
     * Interface to the GLU library.
     */

    private GLU glu;
    private GLUT glut;
    
    
    double cameraHeight;
    double cameraAngle;
    double angle, theta;
    
    

    /**
     * Take care of initialization here.
     */
    @Override
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();
        
        //codes for initialization
	cameraHeight=150.0;
	cameraAngle=1.0;
	
	//clear the screen
	gl.glClearColor(0,0,0,0);
	
	theta=0; 
        
        /************************
	/ set-up projection here
	************************/
	//load the PROJECTION matrix
	gl.glMatrixMode(GL2.GL_PROJECTION);

	//initialize the matrix
	gl.glLoadIdentity();

	//give PERSPECTIVE parameters
	glu.gluPerspective(80,1,1,1000.0);
	//field of view in the Y (vertically)
	//aspect ratio that determines the field of view in the X direction (horizontally)
	//near distance
	//far distance

        //gl.glViewport(-250, -150, 250, 150);
    
        //glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);

    }
    
 

    /**
     * Take care of drawing here.
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        
        //clear the display
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(0,0,0,0);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        /********************
	/ set-up camera here
	********************/
	//load the correct matrix -- MODEL-VIEW matrix
	gl.glMatrixMode(GL2.GL_MODELVIEW);
        
        //initialize the matrix
	gl.glLoadIdentity();
            
        //now give three info
	//1. where is the camera (viewer)?
	//2. where is the camera looking?
	//3. Which direction is the camera's UP direction?

	glu.gluLookAt(200*Math.cos(cameraAngle), 200*Math.sin(cameraAngle), cameraHeight,0,0,0,0,0,1);


	//again select MODEL-VIEW
	gl.glMatrixMode(GL2.GL_MODELVIEW);
        
        /****************************
	/ Add your objects from here
	****************************/
	//add objects
        drawAxes(gl);
       
    }
    

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }


    public void dispose(GLAutoDrawable arg0) {

    }
    
    void drawAxes(GL2 gl)
    {

        gl.glBegin(GL2.GL_LINES);

        gl.glColor3f(1,0,0); gl.glVertex3f(200,0,0);
        gl.glColor3f(1,1,1); gl.glVertex3f(-200,0,0);

        gl.glColor3f(0,1,0);gl.glVertex3f(0,200,0);
        gl.glColor3f(1,1,1);gl.glVertex3f(0,-200,0);

        gl.glColor3f(0,0,1);gl.glVertex3f(0,0,200);
        gl.glColor3f(1,1,1);gl.glVertex3f(0,0,-200);

        gl.glEnd();

    }
    
    
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        
       
        if (e.getKeyCode() == KeyEvent.VK_G) {
            
        } 
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(cameraHeight-3<0)
                cameraHeight=0.5;
            else
                cameraHeight-=3;
            
        } 
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            cameraHeight+=3;
        } 
        
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            cameraAngle+=0.03;
        } 
        else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            cameraAngle-=0.03;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
            // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
            
    }
}

public class Template3D {
    public static void main(String args[]) {
        // getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        ThirdGLEventListener b = new ThirdGLEventListener();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(400, 400);
        // creating frame
        final JFrame frame = new JFrame("Basic frame");
        // adding canvas to frame
        frame.addKeyListener(b);
        frame.add(glcanvas);
        frame.setSize(640, 480);
        frame.setVisible(true);
        FPSAnimator animator = new FPSAnimator(glcanvas, 60);
        animator.start();
    }
}