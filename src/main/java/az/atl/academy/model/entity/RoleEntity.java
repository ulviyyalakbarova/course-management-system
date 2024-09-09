package az.atl.academy.model.entity;

import az.atl.academy.model.enumeration.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "roles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq")
    @SequenceGenerator(name = "roles_id_seq", sequenceName = "roles_id_seq", allocationSize = 1)
    Long id;
    @Enumerated(EnumType.STRING)
    Role role;
}
