package gestionalebackend.gestionalebackend.permission.service;

import gestionalebackend.gestionalebackend.permission.dto.PermissionDTO;
import java.util.List;

public interface PermissionService {
    PermissionDTO createPermission(PermissionDTO permissionDTO);
    PermissionDTO updatePermission(Long id, PermissionDTO permissionDTO);
    PermissionDTO getPermissionById(Long id);
    PermissionDTO getPermissionByName(String name);
    List<PermissionDTO> getAllPermissions();
    void deletePermission(Long id);
}