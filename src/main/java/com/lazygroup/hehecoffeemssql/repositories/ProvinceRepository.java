package com.lazygroup.hehecoffeemssql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lazygroup.hehecoffeemssql.models.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String> {

}
