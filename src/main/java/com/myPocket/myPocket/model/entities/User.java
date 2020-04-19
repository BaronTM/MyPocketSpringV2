package com.myPocket.myPocket.model.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    @Getter @Setter
    private Long idUser;

    @Column(name = "user_name")
    @Getter @Setter
    private String username;

    @Column(name = "user_password")
    @Getter @Setter
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @Getter @Setter
    private List<Role> roles;

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "id_wallet")
    @Getter @Setter
    private Wallet wallet;

    @Column(name = "enabled")
    @Getter @Setter
    private boolean enabled;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.wallet = new Wallet(this);
        this.roles = new ArrayList<>();
        this.enabled = true;
        roles.add(new Role(this, "USER"));
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map( role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", wallet=" + wallet +
                ", enabled=" + enabled +
                '}';
    }
}
