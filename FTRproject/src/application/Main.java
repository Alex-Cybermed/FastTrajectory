package application;
	
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/lib/view/SongLib.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			Scene scene = new Scene(root);
			LibController libController = loader.getController();
			libController.obsL();
			primaryStage.setScene(scene);
			primaryStage.setTitle("Song Library");
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(event -> {
				ObservableList<SongNode> songList = libController.getList();
				try {
					saveFile(songList);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	
	public static void main(String[] args) {
		launch(args);
	}
}