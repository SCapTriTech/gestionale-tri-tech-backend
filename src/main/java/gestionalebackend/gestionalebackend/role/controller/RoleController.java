package gestionalebackend.gestionalebackend.role.controller;

import gestionalebackend.gestionalebackend.permission.dto.PermissionDTO;
import gestionalebackend.gestionalebackend.role.dto.RoleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequestMapping("/api/v1/roles")
public interface RoleController {

    @PostMapping
    ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO);

    @PutMapping("/{id}")
    ResponseEntity<RoleDTO> updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO);

    @GetMapping("/{id}")
    ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id);

    @GetMapping("/name/{name}")
    ResponseEntity<RoleDTO> getRoleByName(@PathVariable String name);

    @GetMapping("/all")
    ResponseEntity<List<RoleDTO>> getAllRoles();

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteRole(@PathVariable Long id);

    @PostMapping("/{roleId}/permissions")
    ResponseEntity<RoleDTO> addPermissionsToRole(@PathVariable Long roleId, @RequestBody Set<Long> permissionIds);

    @DeleteMapping("/{roleId}/permissions")
    ResponseEntity<RoleDTO> removePermissionsFromRole(@PathVariable Long roleId, @RequestBody Set<Long> permissionIds);

    @GetMapping("/{roleId}/permissions")
    ResponseEntity<List<PermissionDTO>> getRolePermissions(@PathVariable Long roleId);
}