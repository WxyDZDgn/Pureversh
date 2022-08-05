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
	private Double difficultyPoint;	//难度系数
	private HBox mainGame;	//序列界面
	private VBox main;	//主界面
//	private HBox showNums;
//	private HBox showButtons;
	private Text information;	//上方的难度信息
	private VBox[] numsButtons;	//数字与移动控制按钮一一对应 ( 目的: 对齐 )
	private String[] oriNums;	//原来的序列 ( 针对 Restart )
	private Text[] nums;	//显示的序列
	Button[] buttons;
	public MainGame() {
		Integer[] difficulties = Difficulty.getDifficulties();	//获取难度信息
		this.kind = difficulties[0];
		this.length = difficulties[1];
		this.control = difficulties[2];
		this.difficultyPoint = Difficulty.getDifficultyPoint();	//获取难度系数
		
		//显示难度系数
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
	
	public VBox getVBox() {	//得到显示界面
		return main;
	}
	private void operation(int i) {	//提取与加入
		if(i == length.intValue() - 1) {	//没有变化, 减少运算
			return;
		}
		String temp = nums[i].getText();	//提取与插入
		for(i += 1; i < length.intValue(); ++ i) {
			nums[i - 1].setText(nums[i].getText());
		}
		nums[length.intValue() - 1].setText(temp);
		check();	//检查完成情况
	}
	public void restart() {	//再来一遍
		for(int i = 0; i < length.intValue(); ++ i) {
			nums[i].setText(oriNums[i]);
			buttons[i].setDisable(i >= control.intValue());
		}
	}
	public void roll() {	//再来一把
		while(true) {
			for(int i = 0; i < length.intValue(); ++ i) {
				oriNums[i] = String.valueOf(((int)(Math.random() * kind.intValue())));
				nums[i].setText(oriNums[i]);
				buttons[i].setDisable(i >= control.intValue());
			}
			if(!check()) {	//检验是否已经完成
				break;	//不是已经完成, 不用重新生成数据
			}
			//否则一来就完成了, 重新刷新
		}
	}
	private boolean check() {	//检验是否完成要求
		for(int i = 1; i < length.intValue(); ++ i) {
			if(nums[i - 1].getText().compareTo(nums[i].getText()) > 0) {
				return false;
			}
		}

		for(int i = 0; i < control.intValue(); ++ i) {	//如果完成要求, 将所有操作移动按钮关闭以提示完成
			buttons[i].setDisable(true);
		}
		return true;
	}
}
