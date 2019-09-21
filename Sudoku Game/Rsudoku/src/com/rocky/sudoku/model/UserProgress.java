package com.rocky.sudoku.model;

public class UserProgress {

	private int progressId;
	private String progressName;
	private String progressLevel1;
	private String progressLevel2;
	private String progressLevel3;

	public UserProgress(String progressName, String progressLevel1) {
		this.progressName = progressName;
		this.progressLevel1 = progressLevel1;
	}
	public UserProgress(int progressId, String progressName, String progressLevel1, String progressLevel2,
			String progressLevel3) {
		super();
		this.progressId = progressId;
		this.progressName = progressName;
		this.progressLevel1 = progressLevel1;
		this.progressLevel2 = progressLevel2;
		this.progressLevel3 = progressLevel3;
	}

	public int getProgressId() {
		return progressId;
	}

	public void setProgressId(int progressId) {
		this.progressId = progressId;
	}

	public String getProgressName() {
		return progressName;
	}

	public void setProgressName(String progressName) {
		this.progressName = progressName;
	}

	public String getProgressLevel1() {
		return progressLevel1;
	}

	public void setProgressLevel1(String progressLevel1) {
		this.progressLevel1 = progressLevel1;
	}

	public String getProgressLevel2() {
		return progressLevel2;
	}

	public void setProgressLevel2(String progressLevel2) {
		this.progressLevel2 = progressLevel2;
	}

	public String getProgressLevel3() {
		return progressLevel3;
	}

	public void setProgressLevel3(String progressLevel3) {
		this.progressLevel3 = progressLevel3;
	}

}
