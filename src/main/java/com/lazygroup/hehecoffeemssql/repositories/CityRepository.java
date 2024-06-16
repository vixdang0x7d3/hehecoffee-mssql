package com.lazygroup.hehecoffeemssql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lazygroup.hehecoffeemssql.models.City;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
}
