package fr.funecirce.tilemapMaker;

import java.awt.Frame;

import javax.swing.JFrame;

import fr.funecirce.tilemapMaker.events.MenuBarHandler;

public class TilemapMaker extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5297066422899848484L;
	
	public TilemapMaker() {
		AppPanel appPanel = new AppPanel();
		
		MenuBarHandler menuBar = new MenuBarHandler(appPanel);
		
		
		setTitle("Tilemap Maker");
		add(appPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setJMenuBar(menuBar.getMenuBar());
		pack();

		setExtendedState(Frame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new TilemapMaker();
	}

}
