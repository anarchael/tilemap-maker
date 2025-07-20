package fr.funecirce.tilemapMaker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.funecirce.tilemapMaker.components.Tile;

public class Tileset {

	private BufferedImage tileset;
	private String name;
	private int tileWidth;
	private int tileHeight;
	private int tilesetRows;
	private int tilesetColumns;
	
	public Tileset(File tileset, String name, int tileWidth, int tileHeight) {
		this.name = name;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
		try {
			this.tileset = ImageIO.read(tileset);
			this.tilesetColumns = this.tileset.getWidth()/tileWidth+1;
			this.tilesetRows = this.tileset.getHeight()/tileHeight+1;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Tile getTileAt(int x, int y) {
		if (x*tileWidth > tileset.getWidth()-tileWidth || y*tileHeight > tileset.getHeight()-tileHeight) {
			return null;
		}
		return new Tile(tileset.getSubimage(x*tileWidth, y*tileHeight, tileWidth, tileHeight), x+y*tilesetColumns);
	}
	
	public String getName() {
		return name;
	}
	
	public int getTileWidth() {
		return tileWidth;
	}
	
	public int getTileHeight() {
		return tileHeight;
	}
	
	public int getTilesetRowCount() {
		return tilesetRows;
	}
	
	public int getTilesetColumnCount() {
		return tilesetColumns;
	}
	
	public BufferedImage getImage() {
		return tileset;
		
	}
}
