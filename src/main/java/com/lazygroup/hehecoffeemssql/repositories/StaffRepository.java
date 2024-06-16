package com.lazygroup.hehecoffeemssql.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lazygroup.hehecoffeemssql.models.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
	Optional<Staff> findByLoginName(String loginName);

	Optional<Staff> findByEmail(String email);

	Optional<Staff> findByPhone(String phone);
}
