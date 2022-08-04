package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import data.Difficulty;
import data.Strings;
import data.MainGame;

public class Pureversh {
	private Stage root;
	private MainGame mainGame;
	private VBox main;
	private Scene scene;
	private Stage stage;
	private HBox buttons;
	private Button restartButton;
	private Button rollButton;
	private Button exitButton;
	public Pureversh() {
		this(null);
	}
	public Pureversh(Stage root) {
		this.root = root;
		this.mainGame = new MainGame();
	}
	public void start() {
		if(root != null) {
			root.close();
		}
		fresh();
		
	}
	private void fresh() {
		main = new VBox(20);

		scene = new Scene(main, 400, 200);
		stage = new Stage();

		main.setAlignment(Pos.CENTER);


		buttons = new HBox(50);
		buttons.setAlignment(Pos.CENTER);
		restartButton = new Button(Strings.getRestart());
		rollButton = new Button(Strings.getRoll());
		exitButton = new Button(Strings.getExit());

		restartButton.setOnAction(restartEvent -> {
			mainGame.restart();
		});
		rollButton.setOnAction(rollEvent -> {
			mainGame.roll();
		});
		exitButton.setOnAction(exitEvent -> {
			Difficulty.saveFile();
			stage.close();
		});

		buttons.getChildren().addAll(restartButton, rollButton, exitButton);


		main.getChildren().addAll(mainGame.getVBox(), buttons);

		stage.setScene(scene);
		stage.setTitle(Strings.getTitle());
		stage.show();
	}
}
