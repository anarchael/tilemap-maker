package fr.funecirce.tilemapMaker.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import fr.funecirce.tilemapMaker.AppPanel;
import fr.funecirce.tilemapMaker.Tileset;
import fr.funecirce.tilemapMaker.events.MouseHandler;

public class TileSelector extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7531541012620653647L;
	
	private List<Tileset> tilesets = new ArrayList<>();
	private AppPanel appPanel;
	private Point mousePos;
	private MouseHandler handler;
	
	int x = 0;
	int y = 0;

	public TileSelector(MouseHandler handler, AppPanel appPanel) {
		this.appPanel = appPanel;
		this.mousePos = new Point(0,0);
		this.handler = handler;
		setMinimumSize(new Dimension(200, 480));
		setPreferredSize(new Dimension(300,720));
		setBackground(Color.MAGENTA);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if (tilesets.size() == 1) {
			
			BufferedImage before = tilesets.getFirst().getImage();
			int width = before.getWidth()*3;
			int height = before.getHeight()*3;
			BufferedImage after = new BufferedImage(width, height, before.getType());
			AffineTransform transform = new AffineTransform();
			transform.scale(3, 3);
			
			AffineTransformOp transformOp = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
			
			transformOp.filter(before, after);
			
			
			g2d.drawImage(after, 0, 0, null);
			
			g2d.setColor(Color.WHITE);
			
			g2d.drawRect(x*16*3, y*16*3, 16*3, 16*3);
		}
	}
	
	public void update() {
		mousePos = getMousePosition();
		
		if(mousePos != null && tilesets.size() > 0) {
			x = mousePos.x/(tilesets.getFirst().getTileWidth()*3);
			y = mousePos.y/(tilesets.getFirst().getTileHeight()*3);
			
		}
		
		if (handler.getPressedButtons().contains(MouseEvent.BUTTON1) && getMousePosition() != null) {
			appPanel.currentTile = tilesets.getFirst().getTileAt(x, y);
			
		}
	}
	
	public void addTileset(Tileset tileset) {
		tilesets.add(tileset);
		
	}
	
}
