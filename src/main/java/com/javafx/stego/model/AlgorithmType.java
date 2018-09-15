package com.javafx.stego.model;

// list types of data containers
public enum AlgorithmType {
	LSB ("LSB", 0),
	INDEXED_TABLE ("INDEXED_TABLE", 1),
	PERCENT_METHOD ("PERCENT_METHOD", 2);


	AlgorithmType(String type, int code){
		this.setType(type);
		this.setCode(code);

	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	private String type;
	private String viewName;
	private int code;

}
