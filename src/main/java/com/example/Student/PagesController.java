package com.example.Student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagesController {
	
	@Autowired
	private StudentJPA stdjpa;
	
	
	@RequestMapping("/")
	public String index(Model model)
	{
		StudentInfo obj = new StudentInfo();
		model.addAttribute("studentobject", obj);
		return "login";
	}
	
	
	@RequestMapping("/save")
	public String save(@ModelAttribute("") StudentInfo stinfo)
	{
		//stinfo.setPassword("");
		stdjpa.save(stinfo);
		return "redirect:/list";
	}
	
	@RequestMapping("/list")
	public String list(Model model)
	{
		List<StudentInfo> stlist = stdjpa.findAll();
		System.out.println("Student Size: "+ stlist.size());
		model.addAttribute("stdlist", stlist);
		return "show";
	}
	
	@RequestMapping("/student/Edit/{id}")
	public ModelAndView edit(@PathVariable(name="id") long id)
	{
		ModelAndView model = new ModelAndView("edit");
		StudentInfo stobj = stdjpa.findById(id).get();
		model.addObject("singleobj",stobj);
		return model;
	}
	
	@RequestMapping("/student/delete/{id}")
	public String depDelete(@PathVariable(name="id") long id)
	{
		stdjpa.deleteById(id);
		return "redirect:/list";
	}

}
