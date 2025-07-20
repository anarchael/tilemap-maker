package fr.funecirce.tilemapMaker.components;

import java.awt.image.BufferedImage;

public class Tile {
	
	private BufferedImage image;
	private int id;

	public Tile(BufferedImage image, int id) {
		this.image = image;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public BufferedImage getImage() {
		return image;
	}
	
}
