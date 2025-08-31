package gestionalebackend.gestionalebackend.role.service;

import gestionalebackend.gestionalebackend.role.dto.RoleDTO;
import java.util.List;
import java.util.Set;

public interface RoleService {
    RoleDTO createRole(RoleDTO roleDTO);
    RoleDTO updateRole(Long id, RoleDTO roleDTO);
    RoleDTO getRoleById(Long id);
    RoleDTO getRoleByName(String name);
    List<RoleDTO> getAllRoles();
    void deleteRole(Long id);
    RoleDTO addPermissionsToRole(Long roleId, Set<Long> permissionIds);
    RoleDTO removePermissionsFromRole(Long roleId, Set<Long> permissionIds);
}