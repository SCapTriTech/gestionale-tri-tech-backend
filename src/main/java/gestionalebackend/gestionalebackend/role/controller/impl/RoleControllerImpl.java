package gestionalebackend.gestionalebackend.role.controller.impl;

import gestionalebackend.gestionalebackend.role.controller.RoleController;
import gestionalebackend.gestionalebackend.role.dto.RoleDTO;
import gestionalebackend.gestionalebackend.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class RoleControllerImpl implements RoleController {

    private final RoleService roleService;

    @Override
    public ResponseEntity<RoleDTO> createRole(RoleDTO roleDTO) {
        RoleDTO createdRole = roleService.createRole(roleDTO);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<RoleDTO> updateRole(Long id, RoleDTO roleDTO) {
        RoleDTO updatedRole = roleService.updateRole(id, roleDTO);
        return ResponseEntity.ok(updatedRole);
    }

    @Override
    public ResponseEntity<RoleDTO> getRoleById(Long id) {
        RoleDTO role = roleService.getRoleById(id);
        return ResponseEntity.ok(role);
    }

    @Override
    public ResponseEntity<RoleDTO> getRoleByName(String name) {
        RoleDTO role = roleService.getRoleByName(name);
        return ResponseEntity.ok(role);
    }

    @Override
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @Override
    public ResponseEntity<Void> deleteRole(Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<RoleDTO> addPermissionsToRole(Long roleId, Set<Long> permissionIds) {
        RoleDTO updatedRole = roleService.addPermissionsToRole(roleId, permissionIds);
        return ResponseEntity.ok(updatedRole);
    }

    @Override
    public ResponseEntity<RoleDTO> removePermissionsFromRole(Long roleId, Set<Long> permissionIds) {
        RoleDTO updatedRole = roleService.removePermissionsFromRole(roleId, permissionIds);
        return ResponseEntity.ok(updatedRole);
    }
}