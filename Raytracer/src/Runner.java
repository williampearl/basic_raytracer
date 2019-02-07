import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

import light.AmbientLight;
import light.DistantLight;
import light.PointLight;
import math.Transformation;
import math.Vec3d;
import objects.Plane;
import objects.Sphere;
import objects.WorldObject;
import scene.Color;
import scene.Scene;
public class Runner extends JFrame{
	public static void main(String[] args) {
		new Runner();
	}
	int WIDTH = 720;
	int HEIGHT = 720;
	View view;
	public Runner() {
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view = new View();
		view.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		//view.setSize(WIDTH, HEIGHT);
		
		add(view);
		view.start();
		new Thread(new Repainter()).start();
		setVisible(true);
	}
	public class Repainter implements Runnable {

		@Override
		public void run() {
			while(true) {
				view.repaint();
				try {
					Thread.sleep(1000/30);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	public class View extends Applet {
		Transformation cameraTransformation = Transformation.identity();
		Scene scene;
		AmbientLight amLight;
		public void init() {
 
		}
		public void start() {
			scene = new Scene();
			double range = 10;
//			WorldObject sphere = new WorldObject(new Sphere(new Vec3d(0,-3,0),5));
//			sphere.setColor(Color.WHITE);
//			scene.addWorldObject(sphere);
			
//			for(int i=0; i<1; i++) {
//				WorldObject wo = new WorldObject(new Sphere(
//						new Vec3d(Math.random()*range-range/2,Math.random()*range-range/2,Math.random()*range-range/2),
//						Math.random()*range*0.3));
//				wo.setColor(Color.random());
//				scene.addWorldObject(wo);
//			}
			WorldObject sphere = new WorldObject(new Sphere(new Vec3d(0,0,0), 4));
			sphere.setColor(Color.WHITE);
			scene.addWorldObject(sphere);
			WorldObject floor = new WorldObject(new Plane(new Vec3d(0,2,0),new Vec3d(0,-1,0).normalize()));
			floor.setColor(Color.WHITE);
			scene.addWorldObject(floor);

			PointLight pLight = new PointLight(new Vec3d(0,-4,0), Color.random(), 400.0);
			scene.addLight(pLight);
//			PointLight pLight2  = new PointLight(new Vec3d(-5, -6, 0), Color.random(), 1);
//			scene.addLight(pLight2);
//			distantLight = new DistantLight(new Vec3d(-1,-1,0).normalize(), Color.GREEN);
//			distantLight.setIntensity(1);
//			scene.addLight(distantLight);
			double radius = 15;
			double steps = 500;
			scene.getCamera().setCameraTransformation(Transformation.identity());
			Transformation camTrans = new Transformation();
			camTrans.translate(0, 0, radius);
			scene.getCamera().applyTransformation(camTrans);
			double leftAm = -2*Math.PI*radius/steps;
			cameraTransformation.translate(leftAm, 0, 0);
			cameraTransformation.rotateAboutYAxis(-2*Math.PI/steps);
		}
//		public void createRoom() {
//			WorldObject floor = new WorldObject(new Plane(new Vec3d(0,20,0),new Vec3d(0,1,0)));
//			floor.setColor(Color.random());
//			scene.addWorldObject(floor);
//			floor = new WorldObject(new Plane(new Vec3d(0,-20,0),new Vec3d(0,1,0)));
//			floor.setColor(Color.random());
//			scene.addWorldObject(floor);
//			floor = new WorldObject(new Plane(new Vec3d(20,0,0),new Vec3d(1,0,0)));
//			floor.setColor(Color.random());
//			scene.addWorldObject(floor);
//			floor = new WorldObject(new Plane(new Vec3d(-20,0,0),new Vec3d(-1,0,0)));
//			floor.setColor(Color.random());
//			scene.addWorldObject(floor);
//			floor = new WorldObject(new Plane(new Vec3d(0,0,20),new Vec3d(0,0,1)));
//			floor.setColor(Color.random());
//			scene.addWorldObject(floor);
//			floor = new WorldObject(new Plane(new Vec3d(0,0,-20),new Vec3d(0,0,-1)));
//			floor.setColor(Color.random());
//			scene.addWorldObject(floor);
//		}
		public void update(Graphics g) {
			Graphics2D gg = (Graphics2D) g;
			Dimension size = getSize();
			//System.out.println(size.getWidth());
			scene.getCamera().applyTransformation(cameraTransformation);
			Color[][] render = scene.render();
			int hTranslate = (int)Math.ceil(size.getHeight()/render.length);
			int wTranslate = (int)Math.ceil(size.getWidth()/render[0].length);
			for(int r=0; r<render.length; r++) {
				for(int c=0; c<render[r].length; c++) {
					Color current = render[r][c];
					gg.setColor(getColor(current));
					gg.fillRect(c*wTranslate, r*hTranslate, wTranslate, hTranslate);
				}
			}
			//gg.setColor(Color.BLACK);
			//gg.fillRect((int)size.getWidth()-10, (int)size.getHeight()-10, 10, 10);
			

		}
		public java.awt.Color getColor(Color from) {
			return new java.awt.Color((int)(from.getRed()*255), (int)(from.getGreen()*255), (int)(from.getBlue()*255));
		}
//		public void draw() {
//			scene.getCamera().applyTransformation(cameraTransformation);
//			render();
//			pushMatrix();
//			float tileHeight = 1f*height/rendered.length;
//			float tileWidth = 1f*width/rendered[0].length;
//			for(int r=0; r<rendered.length; r++) {
//				pushMatrix();
//				for(int c=0; c<rendered[0].length; c++) {
//					noStroke();
//					fill(colorToInt(rendered[r][c]));
//					rect(0,0, tileWidth,tileHeight);
//					translate(tileWidth,0);
//				}
//				popMatrix();
//				translate(0,tileHeight);
//			}
//			popMatrix();
//			//frameRate(0);
//		}
//		public int colorToInt(Color color) {
//			int out = 0xFF;
//			out <<= 8;
//			out += color.getRed()*255;
//			out <<= 8;
//			out += color.getGreen()*255;
//			out <<= 8;
//			out += color.getBlue()*255;
//			return out;
//		}
//		public void render() {
//			rendered = scene.render();
//		}

	}

}
