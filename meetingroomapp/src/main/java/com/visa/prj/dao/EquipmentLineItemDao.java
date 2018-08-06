package com.visa.prj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.visa.prj.entity.EquipmentLineItem;

public interface EquipmentLineItemDao extends JpaRepository<EquipmentLineItem, Integer> {

}
