package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.employee.employee.MainEmployee;
import com.example.employee.model.Employee;

@Controller
public class EmployeeControll {

	@Autowired
	private MainEmployee mainEmployee;

	@GetMapping
	public String getAllEmployees(Model model) {
		model.addAttribute("employees", mainEmployee.findAll());
		return "Index";
	}

	@GetMapping("/new")
	public String showEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "Save_Empl";
	}

	@PostMapping("/new")
	public String addEmployee(@ModelAttribute("employee") Employee employee) {
		mainEmployee.save(employee);
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") long id, Model model) {
		Employee employee = mainEmployee.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
		model.addAttribute("employee", employee);
		return "Edit_Empl";
	}

	@PostMapping("/edit/{id}")
	public String updateEmployee(@PathVariable("id") long id, @ModelAttribute("employee") Employee employee,
			Model model) {
		Employee existingEmployee = mainEmployee.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
		existingEmployee.setName(employee.getName());
		existingEmployee.setPosition(employee.getPosition());
		existingEmployee.setSalary(employee.getSalary());
		existingEmployee.setDob(employee.getDob());
		existingEmployee.setEmail(employee.getEmail());

		mainEmployee.save(existingEmployee);

		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable("id") long id, Model model) {
		Employee employee = mainEmployee.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
		mainEmployee.delete(employee);
		return "redirect:/";
	}
}
