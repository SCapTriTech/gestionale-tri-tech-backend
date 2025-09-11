package gestionalebackend.gestionalebackend.technology.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeTechnologySkillId implements Serializable {
    
    private String employeeEmail;
    private Long technologyId;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeTechnologySkillId that = (EmployeeTechnologySkillId) o;
        return Objects.equals(employeeEmail, that.employeeEmail) && 
               Objects.equals(technologyId, that.technologyId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(employeeEmail, technologyId);
    }
}