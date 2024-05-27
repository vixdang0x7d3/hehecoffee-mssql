package com.lazygroup.hehecoffeemssql.models;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "staffs")
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "is_admin")
	private Boolean isAdmin;

	private String firstname;

	private String lastname;

	private String phone;

	private String email;

	@Column(name = "login_name")
	private String loginName;

	@Column(name = "password_hash")
	private String passwordHash;

	@CreationTimestamp
	@Column(name = "register_on")
	private LocalDateTime registeredOn;

	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	@Column(name = "is_active")
	private Boolean isActive;

	@Builder.Default
	@OneToMany(mappedBy = "staff")
	List<Order> orders = new ArrayList<Order>();

	@Builder.Default
	@OneToMany(mappedBy = "staff", cascade = CascadeType.ALL)
	List<Supply> supplies = new ArrayList<Supply>();

	@Builder.Default
	@OneToMany(mappedBy = "updatedByStaff")
	List<StockRecord> updatedStockRecords = new ArrayList<StockRecord>();

	@Builder.Default
	@OneToMany(mappedBy = "updatedByStaff")
	List<PriceRecord> updatedPriceRecords = new ArrayList<PriceRecord>();
}
