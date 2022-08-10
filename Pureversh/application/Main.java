package application;

//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import data.Difficulty;
import data.Strings;

public class Main extends Application {
//	private static Integer kind;
//	private static Integer length;
//	private static Integer control;
	@Override
	public void start(Stage stage) {
		try {
			
			HBox buttons = new HBox(50);	//ѡ��ť��
			
			Button startButton = new Button(Strings.getStart());	//��ʼ��ť
			Button exitButton = new Button(Strings.getExit());	//�˳���ť
			startButton.setAlignment(Pos.CENTER);	//��������
			exitButton.setAlignment(Pos.CENTER);
			
			//��ʼ��ť�¼�
			startButton.setOnAction(startEvent -> {
				Pureversh pureversh = new Pureversh(stage);
				pureversh.start();
			});
			//�˳���ť�¼�
			exitButton.setOnAction(exitEvent -> {
				//�����ļ�
				Difficulty.saveFile(false);
				stage.close();
			});
			
			buttons.getChildren().addAll(startButton, exitButton);
			buttons.setAlignment(Pos.CENTER);
			
			VBox main = new VBox(20);	//������
			
			VBox titles = new VBox(5);
			Text titleCN = new Text(Strings.TITLE_CN);	//����� - ����
			Text titleEn = new Text(Strings.TITLE_EN);	//����� - Ӣ��
			titleCN.setFont(Font.font(30));	//���������С
			titleEn.setFont(Font.font(30));	//���������С
//			titleCN.setFont(Font.font("����", 30));	//���������С
//			titleEn.setFont(Font.font("Source Code Pro", 30));	//���������С
			titles.setAlignment(Pos.CENTER);
			titles.getChildren().addAll(titleCN, titleEn);
			
			main.getChildren().addAll(titles, buttons);
			main.setAlignment(Pos.CENTER);
			main.setPadding(new Insets(20, 20, 20, 20));
			
			Scene scene = new Scene(main, 500, 300);
			stage.setScene(scene);
			stage.setTitle(Strings.getTitle());	//���ñ���
			
//			Integer[] difficulty = Difficulty.getDifficulty();
//			kind = difficulty[0];
//			length = difficulty[1];
//			control = difficulty[2];
			
			stage.show();	//�������, ����������ʾ����, ��ֹδ�������ʹ�ý���
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		kind = null;
//		length = null;
//		control = null;
		launch(args);
	}
	
}
