package fr.funecirce.tilemapMaker;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import fr.funecirce.tilemapMaker.components.Tile;
import fr.funecirce.tilemapMaker.components.TileSelector;
import fr.funecirce.tilemapMaker.components.TilemapDrawPanel;
import fr.funecirce.tilemapMaker.events.MouseHandler;

public class AppPanel extends JPanel implements Runnable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3044014151396657074L;
	TilemapDrawPanel tilemapDraw ;
	TileSelector tileSelector ;
	public Tile currentTile;
	MouseHandler handler = new MouseHandler();
	public TilemapProject project;

	public AppPanel() {
		
		addMouseListener(handler);
		
		tilemapDraw = new TilemapDrawPanel(handler, this);
		tileSelector = new TileSelector(handler, this);
		
		setLayout(new GridBagLayout());
		
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(10, 10, 0, 0);
		
		add(tilemapDraw, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.insets = new Insets(10, 0, 0, 0);
		
		add(tileSelector, gbc);
		
		startAppLoop();
	}
	
	public void startAppLoop() {
		Thread t = new Thread(this);
		t.start();
	}
	
	public TileSelector getTileSelector() {
		return tileSelector;
	}
	

	@Override
	public void run() {
		try {
			while(true) {
				tileSelector.update();
				tilemapDraw.update();
				repaint();
				Thread.sleep(10);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}

}
