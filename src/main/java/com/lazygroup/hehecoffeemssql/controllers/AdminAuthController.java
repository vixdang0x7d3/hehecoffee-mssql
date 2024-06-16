package com.lazygroup.hehecoffeemssql.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lazygroup.hehecoffeemssql.configs.SecurityUtil;
import com.lazygroup.hehecoffeemssql.dtos.StaffRegistrationDto;
import com.lazygroup.hehecoffeemssql.models.City;
import com.lazygroup.hehecoffeemssql.models.Province;
import com.lazygroup.hehecoffeemssql.models.Staff;
import com.lazygroup.hehecoffeemssql.repositories.CityRepository;
import com.lazygroup.hehecoffeemssql.repositories.ProvinceRepository;
import com.lazygroup.hehecoffeemssql.services.StaffService;

import jakarta.validation.Valid;

@Controller
public class AdminAuthController {

	StaffService staffService;
	ProvinceRepository provinceRepo;
	CityRepository cityRepo;

	@Autowired
	AdminAuthController(StaffService staffService, ProvinceRepository provinceRepo, CityRepository cityRepo) {
		this.staffService = staffService;
		this.provinceRepo = provinceRepo;
		this.cityRepo = cityRepo;
	}

	@GetMapping("/admin-login")
	public String loginForm() {
		return "/admin/admin-login";
	}

	@GetMapping("/admin/register")
	public String registrationForm(Model modelMap) {

		StaffRegistrationDto staff = new StaffRegistrationDto();
		modelMap.addAttribute("staff", staff);

		return "/admin/admin-registration";
	}

	@PostMapping("/admin/register/save")
	public String saveStaff(@Valid @ModelAttribute("staff") StaffRegistrationDto staff,
			ModelMap modelMap,
			BindingResult result) {

		Optional<Staff> existingStaffLoginName = staffService.findByLoginName(staff.getLoginName());
		existingStaffLoginName.ifPresent((existed) -> {
			if (existed.getLoginName().equals(staff.getLoginName()))
				result.rejectValue("username", "The username is already existed");
		});

		Optional<Staff> existingStaffEmail = staffService.findByEmail(staff.getEmail());
		existingStaffEmail.ifPresent((existed) -> {
			if (existed.getEmail().equals(staff.getEmail()))
				result.rejectValue("email", "The email is already existed");
		});

		Optional<Staff> existingStaffPhone = staffService.findByPhone(staff.getPhone());
		existingStaffPhone.ifPresent((existed) -> {
			if (existed.getPhone().equals(staff.getPhone()))
				result.rejectValue("phone", "The phone number is already existed");
		});

		if (result.hasErrors()) {
			modelMap.addAttribute("staff", staff);
			return "/admin/admin-registration";
		}

		staff.setIsActive(true);
		staffService.save(staff);

		return "redirect:/admin/register?success";
	}

	@GetMapping("/admin/accounts")
	public String updateForm(@RequestParam("id") Optional<Long> id, ModelMap modelMap) {

		List<Staff> allStaffs = staffService.findAll();

		Staff staff = staffService.findByLoginName(SecurityUtil.currentSessionUsername()).get();
		allStaffs.remove(staff);
		if (id.isPresent())
			staff = staffService.findById(id.get()).get();

		modelMap.addAttribute("allStaffs", allStaffs);

		StaffRegistrationDto staffDto = StaffRegistrationDto.builder()
				.id(staff.getId())
				.firstname(staff.getFirstname())
				.lastname(staff.getLastname())
				.phone(staff.getPhone())
				.email(staff.getEmail())
				.loginName(staff.getLoginName())
				.isAdmin(staff.getIsAdmin())
				.isActive(staff.getIsActive())
				.build();

		modelMap.addAttribute("staff", staffDto);

		return "/admin/admin-accounts";
	}

	@PostMapping("/admin/accounts/update")
	public String updateStaff(@Valid @ModelAttribute("staff") StaffRegistrationDto staff,
			@ModelAttribute("allStaffs") List<Staff> allStaffs,
			ModelMap modelMap,
			BindingResult result) {

		Optional<Staff> existingStaffLoginName = staffService.findByLoginName(staff.getLoginName());
		existingStaffLoginName.ifPresent((existed) -> {
			if (!existed.getId().equals(staff.getId())
					&& existed.getLoginName().equals(staff.getLoginName()))
				result.rejectValue("username", "The username is already existed");
		});

		Optional<Staff> existingStaffEmail = staffService.findByEmail(staff.getEmail());
		existingStaffEmail.ifPresent((existed) -> {
			if (!existed.getId().equals(staff.getId()) && existed.getEmail().equals(staff.getEmail()))
				result.rejectValue("email", "The email is already existed");
		});

		Optional<Staff> existingStaffPhone = staffService.findByPhone(staff.getPhone());
		existingStaffPhone.ifPresent((existed) -> {
			if (!existed.getId().equals(staff.getId()) && existed.getPhone().equals(staff.getPhone()))
				result.rejectValue("phone", "The phone number is already existed");
		});

		if (result.hasErrors()) {
			modelMap.addAttribute("staff", staff);
			modelMap.addAttribute("allStaffs", allStaffs);
			return "/admin/admin-accounts";
		}

		staffService.update(staff);

		return "redirect:/admin/accounts?success";
	}
}
