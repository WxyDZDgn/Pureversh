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
	private Stage root;	//上一个窗口, 到这里关闭
	private MainGame mainGame;	//显示界面
	private VBox main;	//整个界面
	private Scene scene;
	private Stage stage;
	private HBox buttons;	//存放控制按钮 ( 下 )
	private Button restartButton;
	private Button rollButton;
	private Button settingButton;	//设置难度
	private Button exitButton;	//退出按钮
	public Pureversh() {
		this(null);
	}
	public Pureversh(Stage root) {
		this.root = root;
		this.mainGame = new MainGame();	//生成新数列
	}
	public void start() {
		if(root != null) {
			root.close();	//关闭上一个窗口
		}
		fresh();	//更新数列
		
	}
	private void fresh() {	//更新数列 ( 再来一组 )
		main = new VBox(20);

		scene = new Scene(main, 500, 300);
		stage = new Stage();

		main.setAlignment(Pos.CENTER);


		buttons = new HBox(50);
		buttons.setAlignment(Pos.CENTER);
		restartButton = new Button(Strings.getRestart());
		rollButton = new Button(Strings.getRoll());
		settingButton = new Button(Strings.getSetting());
		exitButton = new Button(Strings.getExit());

		restartButton.setOnAction(restartEvent -> {
			mainGame.restart();
		});
		rollButton.setOnAction(rollEvent -> {
			mainGame.roll();
		});
		settingButton.setOnAction(settingEvent -> {
			Setting setting = new Setting(stage);
			setting.start();
		});
		exitButton.setOnAction(exitEvent -> {
			Difficulty.saveFile(false);
			stage.close();
		});

		buttons.getChildren().addAll(restartButton, rollButton, settingButton, exitButton);


		main.getChildren().addAll(mainGame.getVBox(), buttons);

		stage.setScene(scene);
		stage.setTitle(Strings.getTitle());
		stage.show();
	}
}
