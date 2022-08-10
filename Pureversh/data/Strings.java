package data;

public final class Strings {
	public final static String getTitle() {
		String language = System.getProperty("user.language");
		return language.equals("zh") ? TITLE_CN : TITLE_EN;
	}
	public final static String getStart() {
		String language = System.getProperty("user.language");
		return language.equals("zh") ? START_CN : START_EN;
	}
	public final static String getExit() {
		String language = System.getProperty("user.language");
		return language.equals("zh") ? EXIT_CN : EXIT_EN;
	}
	public final static String getRestart() {
		String language = System.getProperty("user.language");
		return language.equals("zh") ? RESTART_CN : RESTART_EN;
	}
	public final static String getRoll() {
		String language = System.getProperty("user.language");
		return language.equals("zh") ? ROLL_CN : ROLL_EN;
	}
	public final static String getDifficultyPoint() {
		String language = System.getProperty("user.language");
		return language.equals("zh") ? DIFFICULTY_POINT_CN : DIFFICULTY_POINT_EN;
	}
	public final static String getSetting() {
		String language = System.getProperty("user.language");
		return language.equals("zh") ? SETTING_CN : SETTING_EN;
	}
	public final static String getDone() {
		String language = System.getProperty("user.language");
		return language.equals("zh") ? DONE_CN : DONE_EN;
	}
	public final static String getCancel() {
		String language = System.getProperty("user.language");
		return language.equals("zh") ? CANCEL_CN : CANCEL_EN;
	}
	public final static String getKind() {
		String language = System.getProperty("user.language");
		return language.equals("zh") ? KIND_CN : KIND_EN;
	}
	public final static String getLength() {
		String language = System.getProperty("user.language");
		return language.equals("zh") ? LENGTH_CN : LENGTH_EN;
	}
	public final static String getControl() {
		String language = System.getProperty("user.language");
		return language.equals("zh") ? CONTROL_CN : CONTROL_EN;
	}
	public final static String getDefault() {
		String language = System.getProperty("user.language");
		return language.equals("zh") ? DEFAULT_CN : DEFAULT_EN;
	}
	public final static String TITLE_CN = "序列元素后置";
	public final static String TITLE_EN = "Pureversh";
	public final static String START_CN = "开始";
	public final static String START_EN = "Start";
	public final static String EXIT_CN = "退出";
	public final static String EXIT_EN = "Exit";
	public final static String RESTART_CN = "重新";
	public final static String RESTART_EN = "Restart";
	public final static String ROLL_CN = "再来";
	public final static String ROLL_EN = "Roll";
	public final static String DIFFICULTY_POINT_CN = "难度系数";
	public final static String DIFFICULTY_POINT_EN = "Difficulty";
	public final static String SETTING_CN = "设置";
	public final static String SETTING_EN = "Setting";
	public final static String DONE_CN = "确定";
	public final static String DONE_EN = "Done";
	public final static String CANCEL_CN = "取消";
	public final static String CANCEL_EN = "Cancel";
	public final static String KIND_CN = "随机范围";
	public final static String KIND_EN = "Range";
	public final static String LENGTH_CN = "元素长度";
	public final static String LENGTH_EN = "Length";
	public final static String CONTROL_CN = "控制范围";
	public final static String CONTROL_EN = "Control";
	public final static String DEFAULT_CN = "默认";
	public final static String DEFAULT_EN = "Default";
	
	
}
