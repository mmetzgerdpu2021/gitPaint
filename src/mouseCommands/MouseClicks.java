package mouseCommands;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import view.interfaces.PaintCanvasBase;
import model.interfaces.IApplicationState;

public class MouseClicks extends MouseAdapter {
	
	private PaintCanvasBase base;
	private IApplicationState appState;
	private Point downPoint;
	private Point upPoint;
	
	public MouseClicks 	(PaintCanvasBase inbase,
						 IApplicationState inAppState
					    )
	{
		super ();
		this.base = inbase;
		this.appState = inAppState;
	}
	
	public void mousePressed (MouseEvent e) {
		downPoint = e.getPoint();
	}
	
	public void mouseReleased (MouseEvent e) {
		upPoint = e.getPoint();

		// doSomething
		SelectionTypeFactory.doSomething(downPoint, upPoint, base, appState);
		
	}
}
