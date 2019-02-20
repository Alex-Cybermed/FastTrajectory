package Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class appController {

	@FXML
	GridPane gp;

	public void initialize() {

//		Node node1 = getNodeByRowColumnIndex(3, 4, gp);

		boolean status;
		// if(Math.random() < 0.3) {
		// unvisited = visited
	}

	public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
		Node result = null;
		ObservableList<Node> childrens = gridPane.getChildren();

		for (Node node : childrens) {
			if (gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}

		return result;
	}

}