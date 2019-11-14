package com.example.demo.web;

import com.example.demo.entities.Compte;
import com.example.demo.entities.Operation;
import com.example.demo.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.metier.IBanqueMetier;

@Controller
public class BanqueController {
	@Autowired
	private IBanqueMetier banqueMetier;
	
	@GetMapping("/operation")
	public String index() {
		return "comptes";
		
	}
	
	
	@GetMapping("/consultercompte")
	public String consulter(Model model,String codeCompte) {
		model.addAttribute("codeCompte", codeCompte);
		try {
			Compte cp = banqueMetier.consulterCompte(codeCompte);
			Page<Operation> pageOperations = banqueMetier.listOperation(codeCompte, 0, 4);
			model.addAttribute("listOperations", pageOperations.getContent());
			model.addAttribute("compte", cp);
		} catch (Exception e) {
			model.addAttribute("exeption", e);
		}
		
		return "comptes";
		
	}
	
}

