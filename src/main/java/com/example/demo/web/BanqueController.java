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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	
	@RequestMapping(value="saveOperation", method = RequestMethod.POST)
	public String saveOperation(Model model, String typeOperation, String codeCompte, double montant, String codeCompte2 ) {
		
		try {
			if(typeOperation.equals("VERS")) {
				banqueMetier.verser(codeCompte, montant);
			}
			else if(typeOperation.equals("RET")) {
				banqueMetier.retirer(codeCompte, montant);
			}
			if(typeOperation.equals("VIR")){
				banqueMetier.virement(codeCompte, codeCompte2, montant);
				
			}
		} catch (Exception e) {
			model.addAttribute("hello", e);
		}
		
		return "redirect:/consultercompte?codeCompte="+codeCompte;
	}
	
	
	
}

