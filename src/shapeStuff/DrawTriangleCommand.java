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

public class DrawTriangleCommand implements IShapeAll{
	
	private PaintCanvasBase base;
	private int[] Xpoints = {0,0,0};
	private int[] Ypoints = {0,0,0};
	private String shadeType;
	public Color primColor;
	private Color secColor;

	public DrawTriangleCommand(PaintCanvasBase inbase, Point downPoint, Point upPoint, String shadeType,
							Color primColor, Color secColor){
		
		this.base = inbase;
		
		//set X points
		Xpoints[0] = (int) downPoint.getX();
		Xpoints[1] = (int) downPoint.getX();
		Xpoints[2] = (int) upPoint.getX();
		
		//set Y points
		Ypoints[0] = (int) downPoint.getY();
		Ypoints[1] = (int) upPoint.getY();
		Ypoints[2] = (int) upPoint.getY();
		
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
		if (shadeType == "filled") {
			graphics2d.setColor(primColor);
			graphics2d.fillPolygon(Xpoints, Ypoints, 3);
		}
		else if (shadeType == "outline") {
			graphics2d.setStroke(new BasicStroke(5));
	        graphics2d.setColor(primColor);
	        graphics2d.drawPolygon(Xpoints, Ypoints, 3);
		}
		else if (shadeType == "filledAndOutline") {
			graphics2d.setColor(primColor);
			graphics2d.fillPolygon(Xpoints, Ypoints, 3);
			graphics2d.setStroke(new BasicStroke(5));
	        graphics2d.setColor(secColor);
	        graphics2d.drawPolygon(Xpoints, Ypoints, 3);
		}
	}

	@Override
	public int getStartX() {
		// TODO Auto-generated method stub
		if (Xpoints[0] < Xpoints[2]) return Xpoints[0];		
		else return Xpoints[2];
	}

	@Override
	public int getStartY() {
		// TODO Auto-generated method stub
		if (Ypoints[0] < Ypoints[2]) return Ypoints[0];		
		else return Ypoints[2];
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return java.lang.Math.abs(Xpoints[0] - Xpoints[2]);
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return java.lang.Math.abs(Ypoints[0] - Ypoints[2]);
	}
	
	@Override
	public void update(int xMoved, int yMoved) {
		Xpoints[0] += xMoved;
		Xpoints[1] += xMoved;
		Xpoints[2] += xMoved;
		Ypoints[0] += yMoved;
		Ypoints[1] += yMoved;
		Ypoints[2] += yMoved;
	}

	@Override
	public Color getPrimColor() {
		// TODO Auto-generated method stub
		return primColor;
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
		if (!(shape instanceof DrawTriangleCommand)) return false;
		return 	(this.getStartX() == shape.getStartX() &&
				 this.getStartY() == shape.getStartY() &&
				 this.getWidth() == shape.getWidth() &&
				 this.getHeight() == shape.getHeight() &&
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
	
	public int[] getXarray()
	{
		int[] tempX = new int[3];
		for (int i=0; i<3; i++)
			tempX[i] = Xpoints[i];
		return tempX;
	}
	
	public int[] getYarray()
	{
		int[] tempY = new int[3];
		for (int i=0; i<3; i++)
			tempY[i] = Ypoints[i];
		return tempY;
	}
	
	public void outline()
	{
		Graphics2D graphics2d = base.getGraphics2D();
		Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        int[] tempXpoints = {0, 0, 0};
        int[] tempYpoints = {0, 0, 0};
        
        if (Xpoints[0] < Xpoints[2])
        {
        	tempXpoints[0] = Xpoints[0] - 5;
        	tempXpoints[1] = Xpoints[1] - 5;
        	tempXpoints[2] = Xpoints[2] + 10;
        }
		else
		{
			tempXpoints[2] = Xpoints[2] - 10;
			tempXpoints[1] = Xpoints[1] + 5;
			tempXpoints[0] = Xpoints[0] + 5;
		}
        if (Ypoints[0] < Ypoints[2]) 
		{
        	tempYpoints[0] = Ypoints[0] - 10;
        	tempYpoints[1] = Ypoints[1] + 5;
        	tempYpoints[2] = Ypoints[2] + 5;
		}
		else 
		{
			tempYpoints[2] = Ypoints[2] - 5;
			tempYpoints[1] = Ypoints[1] - 5;
			tempYpoints[0] = Ypoints[0] + 10;
		}
        graphics2d.drawPolygon(tempXpoints, tempYpoints, 3);
	}

}
