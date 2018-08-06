package com.visa.prj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.visa.prj.entity.Equipment;

public interface EquipmentDao extends JpaRepository<Equipment, Integer> {

}
