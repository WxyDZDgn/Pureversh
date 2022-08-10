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
	private Integer[] difficulties;	//��ȡ��ı��Ѷ�
	private Stage root;	//��һ������
	private Stage stage;	//��ǰ����
	private Text kindText;	//��Χ�ı�
	private Text lengthText;	//�����ı�
	private Text controlText;	//���Ʒ�Χ�ı�
	private Text difficultyPointText;	//�Ѷ�ϵ���ı�
	private Slider kindSlider;	//���ڷ�Χ
	private Slider lengthSlider;	//���ڳ���
	private Slider controlSlider;	//���ڿ��Ʒ�Χ
	private Text difficultyPointValue;	//��ȡ�Ѷ�ϵ��
	private VBox details;	//������Ϣ
	//���: �ı��ͻ����� ( ֵ )
	private HBox kindDetails;	//��Χ���
	private HBox lengthDetails;	//�������
	private HBox controlDetails;	//���Ʒ�Χ���
	private HBox difficultyPointDetails;	//�Ѷ�ϵ�����
	
	private VBox main;
	private Scene scene;
	private HBox buttons;	//���ְ�ť
	private Button doneButton;	//ȷ����ť
	private Button defaultButton;	//��λ��ť
	private Button cancelButton;	//ȡ����ť
	public Setting() {
		this(null);
	}
	public Setting(Stage root) {
		this.root = root;
		difficulties = Difficulty.getDifficulties();
	}
	public void start() {
		if(root != null) {	//�ر���һ������
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
		//����, ʹ�Ѷ�ϵ����ʱ�仯, ��ͬ
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
			//�ı��Ѷ�
			Difficulty.changeDifficulties(difficulties[0], difficulties[1], difficulties[2]);
			Pureversh pureversh = new Pureversh(stage);
			pureversh.start();
		});
		defaultButton.setOnAction(defaultEvent -> {
			//Ĭ���Ѷ�
			difficulties = new Integer[] {5, 5, 2};
			kindSlider.setValue(difficulties[0]);
			lengthSlider.setValue(difficulties[1]);
			controlSlider.setValue(difficulties[2]);
			difficultyPointValue.setText(String.format("%.1f", Difficulty.getDifficultyPoint(difficulties[0], difficulties[1], difficulties[2])));
		});
		cancelButton.setOnAction(cancelEvent -> {
			//ȡ�������Ѷ�
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
