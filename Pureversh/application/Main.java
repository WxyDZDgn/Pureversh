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
			
			HBox buttons = new HBox(50);	//选择按钮们
			
			Button startButton = new Button(Strings.getStart());	//开始按钮
			Button exitButton = new Button(Strings.getExit());	//退出按钮
			startButton.setAlignment(Pos.CENTER);	//放置中心
			exitButton.setAlignment(Pos.CENTER);
			
			//开始按钮事件
			startButton.setOnAction(startEvent -> {
				Pureversh pureversh = new Pureversh(stage);
				pureversh.start();
			});
			//退出按钮事件
			exitButton.setOnAction(exitEvent -> {
				//保存文件
				Difficulty.saveFile(false);
				stage.close();
			});
			
			buttons.getChildren().addAll(startButton, exitButton);
			buttons.setAlignment(Pos.CENTER);
			
			VBox main = new VBox(20);	//主界面
			
			VBox titles = new VBox(5);
			Text titleCN = new Text(Strings.TITLE_CN);	//大标题 - 中文
			Text titleEn = new Text(Strings.TITLE_EN);	//大标题 - 英文
			titleCN.setFont(Font.font(30));	//设置字体大小
			titleEn.setFont(Font.font(30));	//设置字体大小
//			titleCN.setFont(Font.font("楷体", 30));	//设置字体大小
//			titleEn.setFont(Font.font("Source Code Pro", 30));	//设置字体大小
			titles.setAlignment(Pos.CENTER);
			titles.getChildren().addAll(titleCN, titleEn);
			
			main.getChildren().addAll(titles, buttons);
			main.setAlignment(Pos.CENTER);
			main.setPadding(new Insets(20, 20, 20, 20));
			
			Scene scene = new Scene(main, 500, 300);
			stage.setScene(scene);
			stage.setTitle(Strings.getTitle());	//设置标题
			
//			Integer[] difficulty = Difficulty.getDifficulty();
//			kind = difficulty[0];
//			length = difficulty[1];
//			control = difficulty[2];
			
			stage.show();	//保险起见, 处理完再显示窗口, 防止未处理完就使用界面
			
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
