package shapeStuff;

import java.awt.Color;

import mouseCommands.DrawnShapesList;
import selectionAndMoveStuff.moveSubject;
import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;

public class NullShapeCommand implements IShapeAll {

	
	public void run() {
		System.out.println("NullShapeCommand cannot be run.");
	}

	public void undo() {
		DrawnShapesList.DrawnShapes.removeFirstOccurrence(this);
		if (moveSubject.selectedShapes.contains(this)) 
			moveSubject.selectedShapes.removeFirstOccurrence(this);
	}

	public void redo() {
		DrawnShapesList.DrawnShapes.add(this);
	}

	public void outline() {
		System.out.println("NullShapeCommand cannot be outlined.");
	}

	public void update(int xMoved, int yMoved) {
		System.out.println("NullShapeCommand cannot be updated.");
	}

	public IShapeAll getParent() {
		return this;
	}

	public int getStartX() {
		return 0;
	}

	public int getStartY() {
		return 0;
	}

	public int getWidth() {
		return 0;
	}

	public int getHeight() {
		return 0;
	}

	public Color getPrimColor() {
		return Color.BLACK;
	}

	public Color getSecColor() {
		return Color.BLACK;
	}

	public String getShadeType() {
		return "filledAndOutline";
	}

	public boolean equals(IShapeAll shape) {
		return false;
	}

	public PaintCanvasBase getBase() {
		return new PaintCanvas();
	}

}
