package fr.funecirce.tilemapMaker;

import java.io.Serializable;

import fr.funecirce.tilemapMaker.components.Tile;

public class TilemapProject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4569387602514006747L;
	String name;
	int rows;
	int columns;
	Tile[][] tilemap;
	int tileWidth;
	int tileHeight;

	public TilemapProject(String name, int rows, int columns, int tileWidth, int tileHeight) {
		this.name = name;
		this.rows = rows;
		this.columns = columns;
		this.tilemap = new Tile[columns][rows];
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}
	
	public void updateTile(int column, int row, Tile tile) {
		if (column < columns && row < rows) {
			tilemap[column][row] = tile;
		}
	}
	
	public Tile[][] getTilemap() {
		return tilemap;
	}
	
	public int[][] getIdTilemap() {
		int[][] tilemapId = new int[rows][columns];
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				if (tilemap[column][row] != null) {
					tilemapId[row][column] = tilemap[column][row].getId();
				} else {
					tilemapId[row][column] = -1;
				}
			}
		}
		return tilemapId;
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
	
	public int getRowNumber() {
		return rows;
	}
	
	public int getColumnNumber() {
		return columns;
	}
	
}
