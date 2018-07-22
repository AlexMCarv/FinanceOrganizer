package datatype;

import javafx.scene.control.Button;
import javafx.scene.control.TreeCell;

public class SideMenuCell extends TreeCell<Button>{

	
	@Override
	protected void updateItem(Button item, boolean empty) {
		super.updateItem(item, empty);
		
		if (isEmpty()) {
            setGraphic(null);
            setText(null);
            
		} else {
            
			if (this.getTreeItem().isLeaf()) {

                setGraphic(item);
                setText(null);
                
            } else {
                // If this is the root we just display the text.
                setGraphic(null);
                setText(item.getText());
            }
		}
	
	}

}
