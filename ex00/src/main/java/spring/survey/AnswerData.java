package spring.survey;

import java.util.List;

public class AnswerData {	// 설문 조사지
	private List<String> responses;	// 설문 답변
	private Respondent res;			// 답변자 정보
	
	public List<String> getResponses() {
		return responses;
	}
	public void setResponses(List<String> responses) {
		this.responses = responses;
	}
	public Respondent getRes() {
		return res;
	}
	public void setRes(Respondent res) {
		this.res = res;
	}
}
