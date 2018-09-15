package com.javafx.stego.model;

// list types of data containers
public enum ContainerType {
	IMAGE ("IMAGE", 0),
	AUDIO ("AUDIO", 1);


	ContainerType(String type, int code){
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
