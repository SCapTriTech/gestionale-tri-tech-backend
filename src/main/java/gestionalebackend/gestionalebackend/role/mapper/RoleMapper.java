package gestionalebackend.gestionalebackend.role.mapper;

import gestionalebackend.gestionalebackend.permission.model.Permission;
import gestionalebackend.gestionalebackend.role.dto.RoleDTO;
import gestionalebackend.gestionalebackend.role.model.Role;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public RoleDTO toDTO(Role role) {
        if (role == null) {
            return null;
        }

        return RoleDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .description(role.getDescription())
                .permissionIds(role.getPermissions() != null ?
                        role.getPermissions().stream()
                                .map(Permission::getId)
                                .collect(Collectors.toSet()) : null)
                .permissionNames(role.getPermissions() != null ?
                        role.getPermissions().stream()
                                .map(Permission::getName)
                                .collect(Collectors.toSet()) : null)
                .build();
    }

    public Role toEntity(RoleDTO dto) {
        if (dto == null) {
            return null;
        }

        return Role.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
}