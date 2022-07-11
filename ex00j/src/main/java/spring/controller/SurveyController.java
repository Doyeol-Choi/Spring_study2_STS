package spring.controller;

import java.util.List;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.survey.AnswerData;
import spring.survey.Question;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	private List<Question> createQuestion() {
		Question q1 = new Question("당신의 역할은 무엇입니까?", Arrays.asList("백엔드","프론트","풀스택"));
		Question q2 = new Question("많이 사용하는 개발 도구는 무엇입니까?", Arrays.asList("이클립스","인텔리제이","넷빈즈"));
		Question q3 = new Question("하고 싶은 말을 적어주세요");
		return Arrays.asList(q1,q2,q3);
	}
	
	@GetMapping
	public String form(Model model) {
		List<Question> qList = createQuestion();
		model.addAttribute("qList", qList);
		return "survey/surveyForm";
	}
	
	@PostMapping
	public String submit(@ModelAttribute("ansData") AnswerData data) {
		return "survey/submitted";
	}
}
