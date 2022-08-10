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
	private Stage root;	//��һ������, ������ر�
	private MainGame mainGame;	//��ʾ����
	private VBox main;	//��������
	private Scene scene;
	private Stage stage;
	private HBox buttons;	//��ſ��ư�ť ( �� )
	private Button restartButton;
	private Button rollButton;
	private Button settingButton;	//�����Ѷ�
	private Button exitButton;	//�˳���ť
	public Pureversh() {
		this(null);
	}
	public Pureversh(Stage root) {
		this.root = root;
		this.mainGame = new MainGame();	//����������
	}
	public void start() {
		if(root != null) {
			root.close();	//�ر���һ������
		}
		fresh();	//��������
		
	}
	private void fresh() {	//�������� ( ����һ�� )
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
