package data;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MainGame {
	//5 <= kind <= length <= 10
	//2 <= control <= 5
	private Integer kind;
	private Integer length;
	private Integer control;
	private Double difficultyPoint;
	private HBox mainGame;
	private VBox main;
//	private HBox showNums;
//	private HBox showButtons;
	private Text information;
	private VBox[] numsButtons;
	private String[] oriNums;
	private Text[] nums;
	Button[] buttons;
	public MainGame() {
		Integer[] difficulties = Difficulty.getDifficulties();
		this.kind = difficulties[0];
		this.length = difficulties[1];
		this.control = difficulties[2];
		this.difficultyPoint = Difficulty.getDifficultyPoint();
		
		information = new Text(Strings.getDifficultyPoint() + " " + String.format("%.2f", difficultyPoint.doubleValue()));
		information.setTextAlignment(TextAlignment.CENTER);
		
		main = new VBox(20);
		main.setAlignment(Pos.CENTER);
		
		mainGame = new HBox(20);
		mainGame.setAlignment(Pos.CENTER);
		
		numsButtons = new VBox[length.intValue()];
		oriNums = new String[length.intValue()];
		nums = new Text[length.intValue()];
		
		buttons = new Button[length.intValue()];

		for(int i = 0; i < length.intValue(); ++ i) {
			numsButtons[i] = new VBox(20);
			numsButtons[i].setAlignment(Pos.CENTER);
			oriNums[i] = "";
			nums[i] = new Text("");
			nums[i].setTextAlignment(TextAlignment.CENTER);
			buttons[i] = new Button(String.valueOf((i)));
			buttons[i].setAlignment(Pos.CENTER);
			final int temp = i;
			buttons[i].setOnAction(buttonEvent -> {
				operation(temp);
			});
			numsButtons[i].getChildren().addAll(nums[i], buttons[i]);
			buttons[i].setDisable(i >= control.intValue());
			mainGame.getChildren().add(numsButtons[i]);
		}
		main.getChildren().addAll(information, mainGame);
		roll();
		
	}
	
	public VBox getVBox() {
		return main;
	}
	private void operation(int i) {
		if(i == length.intValue() - 1) {
			return;
		}
		String temp = nums[i].getText();
		for(i += 1; i < length.intValue(); ++ i) {
			nums[i - 1].setText(nums[i].getText());
		}
		nums[length.intValue() - 1].setText(temp);
		check();
	}
	public void restart() {
		for(int i = 0; i < length.intValue(); ++ i) {
			nums[i].setText(oriNums[i]);
			buttons[i].setDisable(i >= control.intValue());
		}
	}
	public void roll() {
		while(true) {
			for(int i = 0; i < length.intValue(); ++ i) {
				oriNums[i] = String.valueOf(((int)(Math.random() * kind.intValue())));
				nums[i].setText(oriNums[i]);
				buttons[i].setDisable(i >= control.intValue());
			}
			if(!check()) {
				break;
			}
		}
	}
	private boolean check() {
		for(int i = 1; i < length.intValue(); ++ i) {
			if(nums[i - 1].getText().compareTo(nums[i].getText()) > 0) {
				return false;
			}
		}

		for(int i = 0; i < control.intValue(); ++ i) {
			buttons[i].setDisable(true);
		}
		return true;
	}
}
