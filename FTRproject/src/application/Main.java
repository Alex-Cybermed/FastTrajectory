package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import Controller.appController;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//import javafx.collections.ObservableList;
//import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/Controller/GridWorld.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			appController ftrController = loader.getController();
			ftrController.obsL();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Fast Trajectory Replanning");
			primaryStage.setResizable(false);
//			primaryStage.setOnCloseRequest(event -> {
//				ObservableList<SongNode> songList = libController.getList();
//				try {
//					saveFile(songList);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}