package data;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Difficulty {
	private static Integer kind = null;
	private static Integer length = null;
	private static Integer control = null;
	public static Integer[] getDifficulties() {	//得到难度信息
		if(kind == null || length == null || control == null) {
			freshFile();
		}
		return new Integer[] {kind, length, control};
	}
	public static Double getDifficultyPoint() {	//得到难度评分
		Integer[] difficulties = getDifficulties();
		return 1.0 * (difficulties[0] * difficulties[1]) / (5 * difficulties[2]);
	}
	private static void freshFile() {
		try {
			//开始前预处理
			//D:/pureversh/config.txt
			File config = new File("C:/pureversh/config.txt");	//为了某些人的需求专门改成了 C 盘存储
			//真初始化或文件因不可抗力因素消失
			if(!config.exists()) {
				File path = new File("C:/pureversh");
				path.mkdir();
				config.createNewFile();	//创建配置文件
			}
			//理论上下一步前可以正常使用文件
			//检查文件正确性
			//正常情况下: 文件只有一行三个数字并用空格隔开
			Scanner checkConfigFile = new Scanner(config);
			boolean configFileIsNormal = false;
			String configString = "";
			while(checkConfigFile.hasNextLine()) {	//用循环有助于减少重复代码
				configString = checkConfigFile.nextLine();
				if(checkConfigFile.hasNextLine()) {	//判断是不是有多行
					break;
				}

				int spaceCount = 0;	//检测空格数量
				boolean hasAlp = false;	//有其他字符
				for(int i = 0; i < configString.length(); ++ i) {	//遍历字符串
					if(configString.charAt(i) != ' ' && configString.charAt(i) < '0' || configString.charAt(i) > '9') {	//出现其他字符
						hasAlp = true;	//不通过
						break;
					}
					if(configString.charAt(i) == ' ') {	//如果当前是空格
						++ spaceCount;	//计数
					}
				}
				if(hasAlp || spaceCount != 2) {	//如果有其他字符并且空格数量不为 2, 不过关
					break;
				}

				//否则文件没问题
				configFileIsNormal = true;	//文件过关
				break;
			}
			if(!configFileIsNormal) {	//文件有问题
				//默认值
				kind = 5;
				length = 5;
				control = 2;
			}
			else {
				//否则为文件储存值
				String[] settings = configString.split(" ");
				kind = Integer.valueOf(settings[0]);
				length = Integer.valueOf(settings[1]);
				control = Integer.valueOf(settings[2]);
				if(kind.intValue() < 5 || kind.intValue() > 10) {
					kind = 5;
				}
				if(length.intValue() < 5 || length.intValue() > 10) {
					length = 5;
				}
//				if(kind.intValue() > length.intValue()) {
//					kind = 5;
//					length = 5;
//				}
				if(control.intValue() < 2 || control.intValue() > 5) {
					control = 2;
				}
			}
			checkConfigFile.close();
			saveFile();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void saveFile() {
		try {
			getDifficulties();
			File config = new File("C:/pureversh/config.txt");
			PrintWriter output = new PrintWriter(config);
			if(kind != null && length != null && control != null) {
				output.print(kind + " " + length + " " + control);
			}
			output.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
