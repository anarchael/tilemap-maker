package fr.funecirce.tilemapMaker.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

public class MouseHandler implements MouseListener {

	private Set<Integer> pressedButtons = new HashSet<>();
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		pressedButtons.add(e.getButton());

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (pressedButtons.contains(e.getButton())) pressedButtons.remove(e.getButton());

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	public Set<Integer> getPressedButtons() {
		return pressedButtons;
	}

	


}
