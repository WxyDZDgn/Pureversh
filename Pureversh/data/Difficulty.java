package data;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Difficulty {
	private static Integer kind = null;
	private static Integer length = null;
	private static Integer control = null;
	public static Integer[] getDifficulties() {	//�õ��Ѷ���Ϣ
		if(kind == null || length == null || control == null) {
			freshFile();
		}
		return new Integer[] {kind, length, control};
	}
	public static Double getDifficultyPoint() {	//�õ��Ѷ�����
		Integer[] difficulties = getDifficulties();
		return 1.0 * (difficulties[0] * difficulties[1]) / (5 * difficulties[2]);
	}
	public static Double getDifficultyPoint(Integer k, Integer l, Integer c) {	//�õ��Ѷ�����
		return (k != null && l != null && c != null) ? 1.0 * (k * l) / (5 * c) : null;
	}
	private static void freshFile() {
		try {
			//��ʼǰԤ����
			//D:/pureversh/config.txt
			File config = new File("C:/pureversh/config.txt");	//Ϊ��ĳЩ�˵�����ר�Ÿĳ��� C �̴洢
			//���ʼ�����ļ��򲻿ɿ���������ʧ
			if(!config.exists()) {
				File path = new File("C:/pureversh");
				path.mkdir();
				config.createNewFile();	//���������ļ�
			}
			//��������һ��ǰ��������ʹ���ļ�
			//����ļ���ȷ��
			//���������: �ļ�ֻ��һ���������ֲ��ÿո����
			Scanner checkConfigFile = new Scanner(config);
			boolean configFileIsNormal = false;
			String configString = "";
			while(checkConfigFile.hasNextLine()) {	//��ѭ�������ڼ����ظ�����
				configString = checkConfigFile.nextLine();
				if(checkConfigFile.hasNextLine()) {	//�ж��ǲ����ж���
					break;
				}

				int spaceCount = 0;	//���ո�����
				boolean hasAlp = false;	//�������ַ�
				for(int i = 0; i < configString.length(); ++ i) {	//�����ַ���
					if(configString.charAt(i) != ' ' && configString.charAt(i) < '0' || configString.charAt(i) > '9') {	//���������ַ�
						hasAlp = true;	//��ͨ��
						break;
					}
					if(configString.charAt(i) == ' ') {	//�����ǰ�ǿո�
						++ spaceCount;	//����
					}
				}
				if(hasAlp || spaceCount != 2) {	//����������ַ����ҿո�������Ϊ 2, ������
					break;
				}

				//�����ļ�û����
				configFileIsNormal = true;	//�ļ�����
				break;
			}
			if(!configFileIsNormal) {	//�ļ�������
				//Ĭ��ֵ
				kind = 5;
				length = 5;
				control = 2;
			}
			else {
				//����Ϊ�ļ�����ֵ
				String[] settings = configString.split(" ");
				kind = Integer.valueOf(settings[0]);
				length = Integer.valueOf(settings[1]);
				control = Integer.valueOf(settings[2]);
				//���������Χ����, �ָ�Ĭ��
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
			saveFile(false);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void saveFile(boolean changed) {	//�����ļ�
		//changed Ϊ: �Ƿ�ı����Ѷ�, �Է�ֹ�Ѷ���Ϣ�ָ�Ĭ��
		try {
			if(changed) {	//�޸���, ���ǲ����ļ�����, ���ܴ��ļ����ȡ
				getDifficulties();	//�����ļ�����ѶȲ���
			}
			File config = new File("C:/pureversh/config.txt");
			PrintWriter output = new PrintWriter(config);
			output.print(kind + " " + length + " " + control);
			output.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//�ֶ��ı��Ѷ�
	public static void changeDifficulties(Integer k, Integer l, Integer c) {
		if(kind == null || length == null || control == null) {
			freshFile();
		}
		if(kind.equals(k) && length.equals(l) && control.equals(c)) {
			return;
		}
		kind = k;
		length = l;
		control = c;
		saveFile(true);
	}
}
