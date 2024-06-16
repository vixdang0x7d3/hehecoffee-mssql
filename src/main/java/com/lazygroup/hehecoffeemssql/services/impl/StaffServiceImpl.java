package com.lazygroup.hehecoffeemssql.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lazygroup.hehecoffeemssql.dtos.StaffRegistrationDto;
import com.lazygroup.hehecoffeemssql.models.Staff;
import com.lazygroup.hehecoffeemssql.repositories.StaffRepository;
import com.lazygroup.hehecoffeemssql.services.StaffService;

@Service
public class StaffServiceImpl implements StaffService {

	StaffRepository staffRepo;
	PasswordEncoder encoder;

	@Autowired
	StaffServiceImpl(StaffRepository staffRepo, PasswordEncoder encoder) {
		this.staffRepo = staffRepo;
		this.encoder = encoder;
	}

	@Override
	public void save(StaffRegistrationDto staffDto) {
		Staff staff = new Staff();

		staff.setId(staffDto.getId());
		staff.setIsAdmin(staffDto.getIsAdmin());
		staff.setFirstname(staffDto.getFirstname());
		staff.setLastname(staffDto.getLastname());
		staff.setPhone(staffDto.getPhone());
		staff.setEmail(staffDto.getEmail());
		staff.setLoginName(staffDto.getLoginName());
		staff.setPasswordHash(encoder.encode(staffDto.getPassword()));
		staff.setLastLogin(LocalDateTime.now());
		staff.setIsActive(true);

		staffRepo.save(staff);
	}

	@Override
	public Optional<Staff> findByLoginName(String loginName) {
		return staffRepo.findByLoginName(loginName);
	}

	@Override
	public Optional<Staff> findByEmail(String email) {
		return staffRepo.findByEmail(email);
	}

	@Override
	public Optional<Staff> findByPhone(String phone) {
		return staffRepo.findByPhone(phone);
	}

	@Override
	public Optional<Staff> findById(Long id) {
		return staffRepo.findById(id);
	}

	@Override
	public List<Staff> findAll() {
		return staffRepo.findAll();
	}

	@Override
	public void update(StaffRegistrationDto staffDto) {

		Staff staff = new Staff();

		staff.setId(staffDto.getId());
		staff.setIsAdmin(staffDto.getIsAdmin());
		staff.setFirstname(staffDto.getFirstname());
		staff.setLastname(staffDto.getLastname());
		staff.setPhone(staffDto.getPhone());
		staff.setEmail(staffDto.getEmail());
		staff.setLoginName(staffDto.getLoginName());
		staff.setPasswordHash(encoder.encode(staffDto.getPassword()));
		staff.setIsActive(staffDto.getIsActive());

		staffRepo.save(staff);
	}
}
