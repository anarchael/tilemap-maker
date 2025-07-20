package fr.funecirce.tilemapMaker.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import fr.funecirce.tilemapMaker.AppPanel;
import fr.funecirce.tilemapMaker.events.MouseHandler;

public class TilemapDrawPanel extends JPanel implements MouseWheelListener {

	
	private static final long serialVersionUID = -6407709460597151320L;
	public static final int TILE_SIZE = 48;
	
	public int x = 0;
	private AppPanel appPanel;
	private MouseHandler handler;
	public double zoom = 1;

	
	public TilemapDrawPanel(MouseHandler handler, AppPanel appPanel) {
		this.handler = handler;
		this.appPanel = appPanel;
		setMinimumSize(new Dimension(720, 480));
		setPreferredSize(new Dimension(1080,720));
		setBackground(new Color(36));
		addMouseListener(handler);
		addMouseWheelListener(this);
	}
	
	public void update() {
		if (appPanel.project != null) {
			if (handler.getPressedButtons().contains(MouseEvent.BUTTON1)) {
				if (appPanel.currentTile != null && getMousePosition() != null) {
					int x = (int) (getMousePosition().x/(appPanel.project.getTileWidth()*zoom));
					int y = (int) (getMousePosition().y/(appPanel.project.getTileHeight()*zoom));
					appPanel.project.updateTile(x, y, new Tile(appPanel.currentTile.getImage(), appPanel.currentTile.getId()));
				}
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.white);
		if (appPanel.project != null ) {
			for (int row = 0; row < appPanel.project.getTilemap().length; row++) {
				Tile[] rows = appPanel.project.getTilemap()[row];
				for (int column = 0; column < rows.length; column++) {
					if (appPanel.project.getTilemap()[row][column] != null) {
						BufferedImage before = appPanel.project.getTilemap()[row][column].getImage();
						double width = before.getWidth()*zoom;
						double height = before.getHeight()*zoom;
						BufferedImage after = new BufferedImage((int) width, (int) height, before.getType());
						
						AffineTransform transform = new AffineTransform();
						transform.scale(zoom, zoom);
						
						AffineTransformOp transformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
						
						transformOp.filter(before, after);
						
						g2d.drawImage(after, 
								(int) (row*appPanel.project.getTileWidth()*zoom),
								(int) (column*appPanel.project.getTileHeight()*zoom),
								null);
					}
				}
			}
		} else {
			Font font = new Font("Serif", Font.PLAIN, 16);
			
			g2d.setFont(font);
			
			FontMetrics fontMetrics = g2d.getFontMetrics();
			
			g2d.drawString("Créez ou importez un projet pour commencer.", 
					getSize().width/2-fontMetrics.stringWidth("Créez ou importez un projet pour commencer.")/2, 
					getSize().height/2);
		}
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if (e.getPreciseWheelRotation() == 1 && zoom > 0.2) zoom-=0.2;
		
		if (e.getPreciseWheelRotation() == -1 && zoom < 4) zoom+=0.2;
		
	}
	
	

}
