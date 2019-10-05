package shapeStuff;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import mouseCommands.DrawnShapesList;
import selectionAndMoveStuff.canvasRefresh;
import selectionAndMoveStuff.moveSubject;
import view.interfaces.PaintCanvasBase;

public class DrawRectangleCommand implements IShapeAll{
	
	private PaintCanvasBase base;
	private int startX;
	private int startY;
	private int width;
	private int height;
	private String shadeType;
	private Color primColor;
	private Color secColor;
	
	public DrawRectangleCommand (PaintCanvasBase base, Point downPoint, Point upPoint, String shadeType,
							Color primColor, Color secColor) {
		
		this.base = base;
		
		//find upper-leftmost X
		int downX = (int) downPoint.getX();
		int upX = (int) upPoint.getX();
		int inStartX;
		if (downX < upX) inStartX = downX;
		else inStartX = upX;
		
		//find upper-rightmost Y
		int downY = (int) downPoint.getY();
		int upY = (int) upPoint.getY();
		int inStartY;
		if (downY < upY) inStartY = downY;
		else inStartY = upY;
		
		this.startX = inStartX;
		this.startY = inStartY;
		this.width = java.lang.Math.abs(downX-upX);
		this.height = java.lang.Math.abs(downY-upY);
		
		this.shadeType = shadeType;
		this.primColor = primColor;
		this.secColor = secColor;
	}

	@Override
	public void undo() {
		DrawnShapesList.DrawnShapes.removeFirstOccurrence(this);
		if (moveSubject.selectedShapes.contains(this)) 
			moveSubject.selectedShapes.removeFirstOccurrence(this);
		canvasRefresh.refresh(base);
	}

	@Override
	public void redo() {
		DrawnShapesList.DrawnShapes.add(this);
		canvasRefresh.refresh(base);
	}

	@Override
	public void run() {
		Graphics2D graphics2d = base.getGraphics2D();
		if (shadeType.equals("filled")){
			graphics2d.setColor(primColor);
			graphics2d.fillRect(startX, startY, width, height);
		}
		else if (shadeType == "outline") {
			graphics2d.setStroke(new BasicStroke(5));
	        graphics2d.setColor(primColor);
	        graphics2d.drawRect(startX, startY, width, height);
		}
		else if (shadeType == "filledAndOutline") {
			graphics2d.setColor(primColor);
			graphics2d.fillRect(startX, startY, width, height);
			graphics2d.setStroke(new BasicStroke(5));
	        graphics2d.setColor(secColor);
	        graphics2d.drawRect(startX, startY, width, height);
		}
	}

	@Override
	public Color getPrimColor() {
		// TODO Auto-generated method stub
		return primColor;
	}

	@Override
	public int getStartX() {
		// TODO Auto-generated method stub
		return startX;
	}

	@Override
	public int getStartY() {
		// TODO Auto-generated method stub
		return startY;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public void update(int xMoved, int yMoved) {
		startX += xMoved;
		startY += yMoved;
		
	}

	@Override
	public Color getSecColor() {
		// TODO Auto-generated method stub
		return secColor;
	}

	@Override
	public String getShadeType() {
		// TODO Auto-generated method stub
		return shadeType;
	}

	@Override
	public boolean equals(IShapeAll shape) {
		if (!(shape instanceof DrawRectangleCommand)) return false;
		return 	(startX == shape.getStartX() &&
				 startY == shape.getStartY() &&
				 width == shape.getWidth() &&
				 height == shape.getHeight() &&
				 shadeType.equals(shape.getShadeType()) &&
				 primColor.equals(shape.getPrimColor()) &&
				 secColor.equals(shape.getSecColor())
				);
	}

	@Override
	public PaintCanvasBase getBase() {
		// TODO Auto-generated method stub
		return base;
	}

	@Override
	public IShapeAll getParent() {
		return this;
	}
	
	public void outline()
	{
		Point startPoint = new Point	(this.getStartX() - 5,
				 						 this.getStartY() - 5);
		Point endPoint = new Point		(this.getStartX() + this.getWidth() + 5,
				 						 this.getStartY() + this.getHeight() + 5);
		Graphics2D graphics2d = base.getGraphics2D();
		Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect((int) startPoint.getX(),
				 			(int) startPoint.getY(),
				 			(int) Math.abs(startPoint.getX() - endPoint.getX()),
				 			(int) Math.abs(startPoint.getY() - endPoint.getY())
						   );
	}

}
