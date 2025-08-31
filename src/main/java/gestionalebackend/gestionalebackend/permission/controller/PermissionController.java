package gestionalebackend.gestionalebackend.permission.controller;

import gestionalebackend.gestionalebackend.permission.dto.PermissionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/permissions")
public interface PermissionController {

    @PostMapping
    ResponseEntity<PermissionDTO> createPermission(@RequestBody PermissionDTO permissionDTO);

    @PutMapping("/{id}")
    ResponseEntity<PermissionDTO> updatePermission(@PathVariable Long id, @RequestBody PermissionDTO permissionDTO);

    @GetMapping("/{id}")
    ResponseEntity<PermissionDTO> getPermissionById(@PathVariable Long id);

    @GetMapping("/name/{name}")
    ResponseEntity<PermissionDTO> getPermissionByName(@PathVariable String name);

    @GetMapping
    ResponseEntity<List<PermissionDTO>> getAllPermissions();

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePermission(@PathVariable Long id);
}