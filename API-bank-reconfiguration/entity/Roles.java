package com.enterprise.bank_lies.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String authority;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private BankDataAccountUser user;

    @Override
    public @Nullable String getAuthority() {
        return authority;
    }
}
