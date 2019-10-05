package model.persistence;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.StartAndEndPointMode;
import model.dialogs.DialogProvider;
import model.interfaces.IApplicationState;
import model.interfaces.IDialogProvider;
import view.interfaces.IUiModule;
import eventCommands.copyEventCommand;
import eventCommands.pasteEventCommand;
import eventCommands.deleteEventCommand;
import eventCommands.groupEventCommand;
import eventCommands.ungroupEventCommand;
import mouseCommands.CommandHistory;
import shapeStuff.GroupCommand;


import java.io.Serializable;

public class ApplicationState implements IApplicationState, Serializable {
    private static final long serialVersionUID = -5545483996576839008L;
    private final IUiModule uiModule;
    private final IDialogProvider dialogProvider;

    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private StartAndEndPointMode activeStartAndEndPointMode;

    public ApplicationState(IUiModule uiModule) {
        this.uiModule = uiModule;
        this.dialogProvider = new DialogProvider(this);
        setDefaults();
    }

    @Override
    public void setActiveShape() {
        activeShapeType = uiModule.getDialogResponse(dialogProvider.getChooseShapeDialog());
    }

    @Override
    public void setActivePrimaryColor() {
        activePrimaryColor = uiModule.getDialogResponse(dialogProvider.getChoosePrimaryColorDialog());
    }

    @Override
    public void setActiveSecondaryColor() {
        activeSecondaryColor = uiModule.getDialogResponse(dialogProvider.getChooseSecondaryColorDialog());
    }

    @Override
    public void setActiveShadingType() {
        activeShapeShadingType = uiModule.getDialogResponse(dialogProvider.getChooseShadingTypeDialog());
    }

    @Override
    public void setActiveStartAndEndPointMode() {
        activeStartAndEndPointMode = uiModule.getDialogResponse(dialogProvider.getChooseStartAndEndPointModeDialog());
    }

    @Override
    public ShapeType getActiveShapeType() {
        return activeShapeType;
    }

    @Override
    public ShapeColor getActivePrimaryColor() {
        return activePrimaryColor;
    }

    @Override
    public ShapeColor getActiveSecondaryColor() {
        return activeSecondaryColor;
    }

    @Override
    public ShapeShadingType getActiveShapeShadingType() {
        return activeShapeShadingType;
    }

    @Override
    public StartAndEndPointMode getActiveStartAndEndPointMode() {
        return activeStartAndEndPointMode;
    }

    private void setDefaults() {
        activeShapeType = ShapeType.ELLIPSE;
        activePrimaryColor = ShapeColor.BLUE;
        activeSecondaryColor = ShapeColor.GREEN;
        activeShapeShadingType = ShapeShadingType.FILLED_IN;
        activeStartAndEndPointMode = StartAndEndPointMode.DRAW;
    }
    
    // MIKALA CODE

	@Override
	public void copySelectedToClipboard()
	{
		copyEventCommand.run();
	}

	@Override
	public void pasteClipboard()
	{
		pasteEventCommand pasteEC = new pasteEventCommand();
		pasteEC.run();
		CommandHistory.add(pasteEC);
	}
	
	@Override
	public void deleteSelected()
	{
		deleteEventCommand deleteEC = new deleteEventCommand();
		deleteEC.run();
		CommandHistory.add(deleteEC);
	}
	
	public void undoAction()
	{
		CommandHistory.undo();
	}
	
	public void redoAction()
	{
		CommandHistory.redo();
	}
	
	public void groupAction()
	{
		GroupCommand groupEC = groupEventCommand.createGroup();
		CommandHistory.add(groupEC);
	}
	
	public void ungroupAction()
	{
		ungroupEventCommand ungroupEC = new ungroupEventCommand();
		CommandHistory.add(ungroupEC);
	}
}
