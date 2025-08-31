package gestionalebackend.gestionalebackend.role.dto;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    private Long id;
    private String name;
    private String description;
    private Set<Long> permissionIds;
    private Set<String> permissionNames;
}