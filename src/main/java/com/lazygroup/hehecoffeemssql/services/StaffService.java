package com.lazygroup.hehecoffeemssql.services;

import java.util.List;
import java.util.Optional;

import com.lazygroup.hehecoffeemssql.dtos.StaffRegistrationDto;
import com.lazygroup.hehecoffeemssql.models.Staff;

public interface StaffService {
	public void save(StaffRegistrationDto staffDto);

	public void update(StaffRegistrationDto staffDto);

	public List<Staff> findAll();

	public Optional<Staff> findById(Long id);

	public Optional<Staff> findByLoginName(String loginName);

	public Optional<Staff> findByEmail(String email);

	public Optional<Staff> findByPhone(String phone);
}
