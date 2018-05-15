package model;

import utility.Display;

public class ErrorMessage {
	private String errorType;
	private String errorDesc;
	public ErrorMessage(String errorType, String errorDesc) {
		this.errorType = errorType;
		this.errorDesc = errorDesc;
	}

	@Override
	public String toString() {
		return Display.getJSON(this);
	}
}
