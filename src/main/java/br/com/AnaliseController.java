package br.com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnaliseController {

	@RequestMapping("/")
	public String home() throws Exception {
		MainAnalise.executar();
		System.out.println("fim");
		return "index";
	}

}
