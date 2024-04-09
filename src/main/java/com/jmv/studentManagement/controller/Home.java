//package com.jmv.studentManagement.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@Controller
//public class Home {
//	
////	@RequestMapping("/home")
////	public String home() {
////		return "index";
////	}
//	
//	@GetMapping({"/","/index"})
//	public String home(@RequestParam(value = "name", defaultValue = "world", required = true) String name, Model model){
//		model.addAttribute("name", name);
//		return "index";
//	}
//
//}


//	@PostMapping("/signup")
//	public String saveStudent(@ModelAttribute Student st){
//		studentService.saveStudent(st);
//		return "redirect:/index";
//	}

//	@GetMapping("/signup")
//	public String home(){
//		return "signup";
//	}

//	@GetMapping("/index")
//	public String dashboard(){
//		return "index";
//	}


