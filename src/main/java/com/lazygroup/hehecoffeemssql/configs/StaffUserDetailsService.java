package com.lazygroup.hehecoffeemssql.configs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lazygroup.hehecoffeemssql.models.Staff;
import com.lazygroup.hehecoffeemssql.repositories.StaffRepository;

@Service
public class StaffUserDetailsService implements UserDetailsService {

	private StaffRepository staffRepo;

	@Autowired
	StaffUserDetailsService(StaffRepository staffRepo) {
		this.staffRepo = staffRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Staff> optionalStaff = staffRepo.findByLoginName(username);

		if (optionalStaff.isPresent()) {
			Staff staff = optionalStaff.get();
			User authUser = new User(staff.getLoginName(),
					staff.getPasswordHash(),
					List.of(new SimpleGrantedAuthority(staff.getIsAdmin() ? "ADMIN" : "STAFF"),
							new SimpleGrantedAuthority("CUSTOMER")));

			return authUser;
		} else {
			throw new UsernameNotFoundException("Invalid username or password");
		}
	}
}
