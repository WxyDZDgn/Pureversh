package application;

import data.Difficulty;
import data.Strings;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Setting {
	private Integer[] difficulties;	//获取或改变难度
	private Stage root;	//上一个窗口
	private Stage stage;	//当前窗口
	private Text kindText;	//范围文本
	private Text lengthText;	//长度文本
	private Text controlText;	//控制范围文本
	private Text difficultyPointText;	//难度系数文本
	private Slider kindSlider;	//调节范围
	private Slider lengthSlider;	//调节长度
	private Slider controlSlider;	//调节控制范围
	private Text difficultyPointValue;	//获取难度系数
	private VBox details;	//以上信息
	//组合: 文本和滑动条 ( 值 )
	private HBox kindDetails;	//范围组合
	private HBox lengthDetails;	//长度组合
	private HBox controlDetails;	//控制范围组合
	private HBox difficultyPointDetails;	//难度系数组合
	
	private VBox main;
	private Scene scene;
	private HBox buttons;	//各种按钮
	private Button doneButton;	//确定按钮
	private Button defaultButton;	//复位按钮
	private Button cancelButton;	//取消按钮
	public Setting() {
		this(null);
	}
	public Setting(Stage root) {
		this.root = root;
		difficulties = Difficulty.getDifficulties();
	}
	public void start() {
		if(root != null) {	//关闭上一个窗口
			root.close();
		}
		main = new VBox(20);
		
		scene = new Scene(main, 500, 300);
		stage = new Stage();
		
		main.setAlignment(Pos.CENTER);
		
		kindDetails = new HBox(20);
		kindDetails.setAlignment(Pos.CENTER);
		kindText = new Text(Strings.getKind());
		kindText.setTextAlignment(TextAlignment.CENTER);
		kindSlider = new Slider(5, 10, difficulties[0]);
		kindSlider.setShowTickLabels(true);
		kindSlider.setShowTickMarks(true);
		kindSlider.setMajorTickUnit(1);
		kindSlider.setMinorTickCount(0);
		kindSlider.setBlockIncrement(1);
		kindSlider.setSnapToTicks(true);
		//监听, 使难度系数随时变化, 下同
		kindSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			difficulties[0] = Integer.valueOf(new_val.intValue());
			difficultyPointValue.setText(String.format("%.1f", Difficulty.getDifficultyPoint(difficulties[0], difficulties[1], difficulties[2])));
		});
		kindDetails.getChildren().addAll(kindText, kindSlider);
		
		lengthDetails = new HBox(20);
		lengthDetails.setAlignment(Pos.CENTER);
		lengthText = new Text(Strings.getLength());
		lengthText.setTextAlignment(TextAlignment.CENTER);
		lengthSlider = new Slider(5, 10, difficulties[1]);
		lengthSlider.setShowTickLabels(true);
		lengthSlider.setShowTickMarks(true);
		lengthSlider.setMajorTickUnit(1);
		lengthSlider.setMinorTickCount(0);
		lengthSlider.setBlockIncrement(1);
		lengthSlider.setSnapToTicks(true);
		lengthSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			difficulties[1] = Integer.valueOf(new_val.intValue());
			difficultyPointValue.setText(String.format("%.1f", Difficulty.getDifficultyPoint(difficulties[0], difficulties[1], difficulties[2])));
		});
		lengthDetails.getChildren().addAll(lengthText, lengthSlider);
		
		controlDetails = new HBox(20);
		controlDetails.setAlignment(Pos.CENTER);
		controlText = new Text(Strings.getControl());
		controlText.setTextAlignment(TextAlignment.CENTER);
		controlSlider = new Slider(2, 5, difficulties[2]);
		controlSlider.setShowTickLabels(true);
		controlSlider.setShowTickMarks(true);
		controlSlider.setMajorTickUnit(1);
		controlSlider.setMinorTickCount(0);
		controlSlider.setBlockIncrement(1);
		controlSlider.setSnapToTicks(true);
		controlSlider.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
			difficulties[2] = Integer.valueOf(new_val.intValue());
			difficultyPointValue.setText(String.format("%.1f", Difficulty.getDifficultyPoint(difficulties[0], difficulties[1], difficulties[2])));
		});
		controlDetails.getChildren().addAll(controlText, controlSlider);
		
		difficultyPointDetails = new HBox(20);
		difficultyPointDetails.setAlignment(Pos.CENTER);
		difficultyPointText = new Text(Strings.getDifficultyPoint());
		difficultyPointText.setTextAlignment(TextAlignment.CENTER);
		difficultyPointValue = new Text(String.format("%.1f", Difficulty.getDifficultyPoint(difficulties[0], difficulties[1], difficulties[2])));
		difficultyPointDetails.getChildren().addAll(difficultyPointText, difficultyPointValue);
		
		details = new VBox(20);
		details.setAlignment(Pos.CENTER);
		
		details.getChildren().addAll(kindDetails, lengthDetails, controlDetails, difficultyPointDetails);
		
		buttons = new HBox(50);
		buttons.setAlignment(Pos.CENTER);
		doneButton = new Button(Strings.getDone());
		defaultButton = new Button(Strings.getDefault());
		cancelButton = new Button(Strings.getCancel());
		
		doneButton.setOnAction(doneEvent -> {
			//改变难度
			Difficulty.changeDifficulties(difficulties[0], difficulties[1], difficulties[2]);
			Pureversh pureversh = new Pureversh(stage);
			pureversh.start();
		});
		defaultButton.setOnAction(defaultEvent -> {
			//默认难度
			difficulties = new Integer[] {5, 5, 2};
			kindSlider.setValue(difficulties[0]);
			lengthSlider.setValue(difficulties[1]);
			controlSlider.setValue(difficulties[2]);
			difficultyPointValue.setText(String.format("%.1f", Difficulty.getDifficultyPoint(difficulties[0], difficulties[1], difficulties[2])));
		});
		cancelButton.setOnAction(cancelEvent -> {
			//取消设置难度
			Pureversh pureversh = new Pureversh(stage);
			pureversh.start();
		});
		
		buttons.getChildren().addAll(doneButton, defaultButton, cancelButton);
		
		main.getChildren().addAll(details, buttons);
		
		stage.setScene(scene);
		stage.setTitle(Strings.getTitle());
		stage.show();
		
	}
}
