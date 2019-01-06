package main;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import obj.Coordinate;
import obj.ThreeDPolygon;

public class Graph extends JFrame {
	JFrame frame = this;
	JPanel jp;
	BufferedImage img;
	public static final int width = 1080;
	public static final int height = 1080;
	
	public static ArrayList<ThreeDPolygon> threedpolygons = new ArrayList<ThreeDPolygon>();
	public static ArrayList<Polygon> polygons = new ArrayList<Polygon>();
	
	public Graph() {
	    frame.setTitle("Simple Drawing");
	    frame.setSize(width, height);
	    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	    jp = new GPanel();
	    frame.add(jp);
	    frame.setVisible(true);
	    
	    Coordinate cameracoordinate = new Coordinate(0.5, 0.5, 0); // x, y, z
	    Coordinate cameraangle = new Coordinate(0, 0, 0); // 1st, pan, 2nd, tilt
	    
	    Coordinate a = new Coordinate(0, 0, 0);
	    Coordinate b = new Coordinate(1, 0, 0);
	    Coordinate c = new Coordinate(1, 0, 1);
	    Coordinate d = new Coordinate(0, 0, 1);
	    Coordinate e = new Coordinate(0, 1, 0);
	    Coordinate f = new Coordinate(1, 1, 0);
	    Coordinate g = new Coordinate(1, 1, 1);
	    Coordinate h = new Coordinate(0, 1, 1);
	    ArrayList<Coordinate> polya = new ArrayList<Coordinate>();
	    polya.add(a);
	    polya.add(b);
	    polya.add(c);
	    polya.add(d);
	    ArrayList<Coordinate> polyb = new ArrayList<Coordinate>();
	    polyb.add(e);
	    polyb.add(f);
	    polyb.add(g);
	    polyb.add(h);
	    ArrayList<Coordinate> polyc = new ArrayList<Coordinate>();
	    polyc.add(a);
	    polyc.add(b);
	    polyc.add(f);
	    polyc.add(e);
	    ArrayList<Coordinate> polyd = new ArrayList<Coordinate>();
	    polyd.add(c);
	    polyd.add(d);
	    polyd.add(h);
	    polyd.add(g);
	    ArrayList<Coordinate> polye = new ArrayList<Coordinate>();
	    polye.add(b);
	    polye.add(c);
	    polye.add(g);
	    polye.add(f);
	    ArrayList<Coordinate> polyf = new ArrayList<Coordinate>();
	    polyf.add(a);
	    polyf.add(d);
	    polyf.add(h);
	    polyf.add(e);
	    threedpolygons.add(new ThreeDPolygon(polya));
	    threedpolygons.add(new ThreeDPolygon(polyb));
	    threedpolygons.add(new ThreeDPolygon(polyc));
	    threedpolygons.add(new ThreeDPolygon(polyd));
	    threedpolygons.add(new ThreeDPolygon(polye));
	    threedpolygons.add(new ThreeDPolygon(polyf));
	    
	    while(1==1) {
	    polygons.clear();
	    cameracoordinate = new Coordinate(Math.cos(System.currentTimeMillis() / 1000.0) + 0.5, Math.sin(System.currentTimeMillis() / 1000.0) + 0.5, Math.sin((System.currentTimeMillis() + 500.0) / 200.0) - 5);
	    cameraangle = new Coordinate(Math.cos(System.currentTimeMillis() / 271.4) * 5, Math.sin(System.currentTimeMillis() / 314.5) * 5, 0);
	    for (ThreeDPolygon tdpolygon: threedpolygons) {
		    Polygon poly = new Polygon();
		    double pan = 0;
		    double tilt = 0;
	    	for (Coordinate coord : tdpolygon.getCoordinates()) {
	    		pan = Math.atan((coord.getX() - cameracoordinate.getX()) / (coord.getZ() - cameracoordinate.getZ()));
	    		tilt = Math.atan((coord.getY() - cameracoordinate.getY()) / (coord.getZ() - cameracoordinate.getZ()));
	    		pan *= 180.0 / Math.PI;
	    		tilt *= 180.0 / Math.PI;
	    		pan -= cameraangle.getX();
	    		tilt -= cameraangle.getY();
	    		pan = pan % 360;
	    		tilt = tilt % 360;
	    		pan = (pan + 360) % 360;
	    		tilt = (tilt + 360) % 360;
	    		if (pan > 180) {
	    			pan -= 360;
	    		}
	    		if (tilt > 180) {
	    			tilt -= 360;
	    		}
	    		pan /= 180.0 / Math.PI;
	    		tilt /= 180.0 / Math.PI;
	    		poly.addPoint((int) ((width / 2.0) + width * Math.tan(pan)), (int) ((height / 2.0) - height * Math.tan(tilt)));
	    		//poly.addPoint((int) ((width / 2.0) + width * pan / camerapov), (int) ((height / 2.0) - height * tilt / camerapov));
	    	}
	    	polygons.add(poly);
	    }
	    frame.repaint();
	    try {
			Thread.sleep(16);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    }
	}
	
	public static void main(String[] args) {
	    Graph g1 = new Graph();
	    g1.setVisible(true);
	}
	
	class GPanel extends JPanel {
	    public GPanel() {
	        frame.setPreferredSize(new Dimension(300, 300));
	    }
	
	    @Override
	    public void paintComponent(Graphics g) {
	    	if (!polygons.isEmpty()) {
		    	for (Polygon polygon : polygons) {
		    		g.drawPolygon(polygon);
		    	}
	    	}
	    }
	}
}