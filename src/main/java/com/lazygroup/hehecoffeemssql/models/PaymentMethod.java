package com.lazygroup.hehecoffeemssql.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lk_payment_method")
public class PaymentMethod {
	@Id
	private String id;
	private String name;
}
