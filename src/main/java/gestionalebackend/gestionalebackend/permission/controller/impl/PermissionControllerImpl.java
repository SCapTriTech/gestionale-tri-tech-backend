package gestionalebackend.gestionalebackend.permission.controller.impl;

import gestionalebackend.gestionalebackend.permission.controller.PermissionController;
import gestionalebackend.gestionalebackend.permission.dto.PermissionDTO;
import gestionalebackend.gestionalebackend.permission.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PermissionControllerImpl implements PermissionController {

    private final PermissionService permissionService;

    @Override
    public ResponseEntity<PermissionDTO> createPermission(PermissionDTO permissionDTO) {
        PermissionDTO createdPermission = permissionService.createPermission(permissionDTO);
        return new ResponseEntity<>(createdPermission, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PermissionDTO> updatePermission(Long id, PermissionDTO permissionDTO) {
        PermissionDTO updatedPermission = permissionService.updatePermission(id, permissionDTO);
        return ResponseEntity.ok(updatedPermission);
    }

    @Override
    public ResponseEntity<PermissionDTO> getPermissionById(Long id) {
        PermissionDTO permission = permissionService.getPermissionById(id);
        return ResponseEntity.ok(permission);
    }

    @Override
    public ResponseEntity<PermissionDTO> getPermissionByName(String name) {
        PermissionDTO permission = permissionService.getPermissionByName(name);
        return ResponseEntity.ok(permission);
    }

    @Override
    public ResponseEntity<List<PermissionDTO>> getAllPermissions() {
        List<PermissionDTO> permissions = permissionService.getAllPermissions();
        return ResponseEntity.ok(permissions);
    }

    @Override
    public ResponseEntity<Void> deletePermission(Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }
}