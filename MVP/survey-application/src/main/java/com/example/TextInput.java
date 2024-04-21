package com.example;

import java.io.Serializable;

public class TextInput implements Serializable {
	private String content;

	public TextInput(){};

	public TextInput(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
