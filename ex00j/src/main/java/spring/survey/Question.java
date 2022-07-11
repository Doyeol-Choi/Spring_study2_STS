package spring.survey;

import java.util.List;

public class Question {
	private String title;
	private List<String> option;
	
	public Question(String title) {
		this.title = title;
	}
	public Question(String title, List<String> option) {
		this.title = title;
		this.option = option;
	}
	public boolean isChoice() {
		return option!=null && !option.isEmpty();	// option이 있다면 true, 없다면 false
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getOption() {
		return option;
	}
	public void setOption(List<String> option) {
		this.option = option;
	}
}
