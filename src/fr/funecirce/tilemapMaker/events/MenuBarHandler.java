package fr.funecirce.tilemapMaker.events;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import fr.funecirce.tilemapMaker.AppPanel;
import fr.funecirce.tilemapMaker.TilemapProject;
import fr.funecirce.tilemapMaker.Tileset;

public class MenuBarHandler implements ActionListener{
	
	private JMenuBar menuBar;
	private AppPanel panel;
	
	
	public MenuBarHandler(AppPanel panel) {
		menuBar = new JMenuBar();
		this.panel = panel;
		
		JMenu fileMenu = new JMenu("Fichiers");
		
		JMenuItem fileCreate = new JMenuItem("Créer une tilemap");
		JMenuItem fileImport = new JMenuItem("Importer");
		JMenuItem fileExport = new JMenuItem("Exporter");
		
		fileMenu.add(fileCreate);
		fileMenu.add(fileImport);
		fileMenu.add(fileExport);
		
		menuBar.add(fileMenu);
		
		JMenu tilesetMenu = new JMenu("Tileset");
		
		JMenuItem tilesetImport = new JMenuItem("Importer un tileset");
		
		tilesetMenu.add(tilesetImport);
		
		menuBar.add(tilesetMenu);
		
		fileCreate.addActionListener(this);
		fileImport.addActionListener(this);
		fileExport.addActionListener(this);
		tilesetImport.addActionListener(this);
		
	}
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		
		JFrame errorFrame = new JFrame("Erreur");
		errorFrame.setLocationRelativeTo(null);
		errorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel errorMessage = new JLabel("Aucun projet n'est chargé");
		
		errorFrame.add(errorMessage);
		errorFrame.pack();
		
		if(cmd.equalsIgnoreCase("Importer un tileset")) {
			if (panel.project != null) {
				JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				fileChooser.setMultiSelectionEnabled(false);
				fileChooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter extensionRestrict = new FileNameExtensionFilter(".png, .jpg", "png", "jpg");
				fileChooser.addChoosableFileFilter(extensionRestrict);
				
				int action = fileChooser.showOpenDialog(null);
				
				if (action == JFileChooser.APPROVE_OPTION) {
					showTilesetDialog(fileChooser.getSelectedFile());
				}
			} else {
				errorFrame.setVisible(true);
			}
		}
		
		if (cmd.equalsIgnoreCase("Créer une tilemap")) {
			showTilemapDialog();
		}
		
		if (cmd.equalsIgnoreCase("Exporter")) {
			if (panel.project != null) {
				Exporter.exportTilemapAsJson(panel.project);
			}
		}
		
	}
	public void showTilemapDialog() {
		JFrame tilemapSettings = new JFrame();
		tilemapSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tilemapSettings.setAutoRequestFocus(true);
		tilemapSettings.setAlwaysOnTop(true);		
		
		JLabel nameLabel = new JLabel("Nom de la map : ");
		JTextArea nameField = new JTextArea();
		
		JLabel dimensionsLabel = new JLabel("Dimensions de la map : ");
		JTextArea rowField = new JTextArea();
		rowField.setPreferredSize(new Dimension(20, 20));
		JLabel junctionLabel = new JLabel("X");
		JTextArea columnField = new JTextArea();
		columnField.setPreferredSize(new Dimension(20, 20));
		
		JLabel tileWidthLabel = new JLabel("Largeur de tuile : ");
		JTextArea tileWidthField = new JTextArea();
		
		JLabel tileHeightLabel = new JLabel("Hauteur de tuile : ");
		JTextArea tileHeightField = new JTextArea();
		
		JButton validate = new JButton("Valider");
		
		tilemapSettings.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Name gbc
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		tilemapSettings.add(nameLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		nameField.setPreferredSize(new Dimension(100,20));
		tilemapSettings.add(nameField, gbc);
		
		// Dimensions gbc
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		tilemapSettings.add(dimensionsLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		tilemapSettings.add(columnField, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		tilemapSettings.add(junctionLabel, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		tilemapSettings.add(rowField, gbc);
		
		// Width gbc
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
				
		tilemapSettings.add(tileWidthLabel, gbc);
				
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(5, 5, 5, 5);
				
		tileWidthField.setPreferredSize(new Dimension(100,20));
		tilemapSettings.add(tileWidthField, gbc);
		
		// Height gbc
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
						
		
		tilemapSettings.add(tileHeightLabel, gbc);
						
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		tileHeightField.setPreferredSize(new Dimension(100,20));
		tilemapSettings.add(tileHeightField, gbc);
		
		// Bouton de validation
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.insets = new Insets(5, 5, 5, 5);
						
		tilemapSettings.add(validate, gbc);
		
		tilemapSettings.pack();
		tilemapSettings.setLocation(panel.getSize().width/2, panel.getSize().height/2);
		tilemapSettings.setVisible(true);
		
		validate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String tileWidthStr = tileWidthField.getText(); 
				String tileHeightStr = tileHeightField.getText();
				String rowStr = rowField.getText();
				String columnStr = columnField.getText();
				
				if (isNumeric(tileWidthStr) && isNumeric(tileHeightStr) 
						&& isNumeric(rowStr) && isNumeric(columnStr)) {
					int tileWidth = Integer.parseInt(tileWidthStr);
					int tileHeight = Integer.parseInt(tileHeightStr);
					int rowCount = Integer.parseInt(rowStr);
					System.out.println(rowCount);
					int columnCount = Integer.parseInt(columnStr);
					System.out.println(columnCount);
					
					panel.project = new TilemapProject(name, rowCount, columnCount, tileWidth, tileHeight);
					tilemapSettings.setVisible(false);
				}
				
				
			}
		});
	
	}
	
	public void showTilesetDialog(File chosenFile) {
		JFrame tilesetSettings = new JFrame();
		tilesetSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tilesetSettings.setAutoRequestFocus(true);
		tilesetSettings.setAlwaysOnTop(true);		
		
		JLabel nameLabel = new JLabel("Nom du tileset : ");
		JTextArea nameField = new JTextArea();
		
		JLabel tileWidthLabel = new JLabel("Largeur de tuile : ");
		JTextArea tileWidthField = new JTextArea();
		
		JLabel tileHeightLabel = new JLabel("Hauteur de tuile : ");
		JTextArea tileHeightField = new JTextArea();
		
		JButton validate = new JButton("Valider");
		
		tilesetSettings.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Name gbc
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		tilesetSettings.add(nameLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		nameField.setPreferredSize(new Dimension(100,20));
		tilesetSettings.add(nameField, gbc);
		
		// Width gbc
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
				
		tilesetSettings.add(tileWidthLabel, gbc);
				
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(5, 5, 5, 5);
				
		tileWidthField.setPreferredSize(new Dimension(100,20));
		tilesetSettings.add(tileWidthField, gbc);
		
		// Width gbc
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(5, 5, 5, 5);
						
		
		tilesetSettings.add(tileHeightLabel, gbc);
						
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc.insets = new Insets(5, 5, 5, 5);
		
		tileHeightField.setPreferredSize(new Dimension(100,20));
		tilesetSettings.add(tileHeightField, gbc);
		
		// Bouton de validation
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.insets = new Insets(5, 5, 5, 5);
						
		tilesetSettings.add(validate, gbc);
		
		tilesetSettings.pack();
		tilesetSettings.setLocation(panel.getSize().width/2, panel.getSize().height/2);
		tilesetSettings.setVisible(true);
		
		validate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				String name = nameField.getText();
				String tileWidthStr = tileWidthField.getText(); 
				String tileHeightStr = tileHeightField.getText();
				
				
				if (cmd.equalsIgnoreCase("Valider")){
					if (tileWidthStr.isBlank() 
							|| tileHeightStr.isBlank()
							|| name.isBlank()) {
						return;
					} else {
						if (isNumeric(tileWidthStr) && isNumeric(tileHeightStr)) {
							int tileWidth = Integer.parseInt(tileWidthStr);
							int tileHeight = Integer.parseInt(tileHeightStr);
							panel.getTileSelector().addTileset(new Tileset(chosenFile, name, tileWidth, tileHeight));
							tilesetSettings.setVisible(false);
						}
					}
				}
				
			}
		});
	
	}
	
	public boolean isNumeric(String str) {
		if (str == null) {
			return false;
		} try {
			Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
