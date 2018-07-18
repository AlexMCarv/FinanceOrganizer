package datatype;

import javafx.geometry.Pos;
import javafx.scene.control.TableCell;

public class DoubleTableCell extends TableCell<CategorySummary,Double>{

	@Override
	protected void updateItem(Double value, boolean empty) {
		super.updateItem(value, empty);
		if (empty || value < 0.0001) {
			setText(null);
		} else {
			setText(value + "");
		}
		setAlignment(Pos.CENTER_RIGHT); //temporary solution implement in css later
	}

}