package gestionalebackend.gestionalebackend.permission.mapper;

import gestionalebackend.gestionalebackend.permission.dto.PermissionDTO;
import gestionalebackend.gestionalebackend.permission.model.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {

    public PermissionDTO toDTO(Permission permission) {
        if (permission == null) {
            return null;
        }

        return PermissionDTO.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .build();
    }

    public Permission toEntity(PermissionDTO dto) {
        if (dto == null) {
            return null;
        }

        return Permission.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
}