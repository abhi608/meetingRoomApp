package com.visa.prj.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.visa.prj.entity.Admin;

public interface AdminDao extends JpaRepository<Admin,String>{

}
