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
	
}
