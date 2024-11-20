package com.ecom.proj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.proj.model.Role;
import com.ecom.proj.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    public void addUpdRole(Role role) {
        roleRepository.save(role);
    }

    public void delRole(Integer id) {
        roleRepository.deleteById(id);
    }
}
