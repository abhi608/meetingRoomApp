package com.visa.prj.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.visa.prj.entity.Client;

public interface ClientDao extends JpaRepository<Client, String> {

}
