package com.lardi.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable=false)
    private Long id;

    @Size (min =5, max =255)
    @Pattern(regexp = "^[A-Za-z]+$")
    private String fio;

    @Size (min =3, max =20)
    @Pattern(regexp = "^[A-Za-z]+$")
    private String username;

    @Size (min =5, max =20)
    private String password;

    @Transient
    private String confirmPassword;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Set<Record> records =  new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFio() {
        return fio;
    }

    public User setFio(String fio) {
        this.fio = fio;
        return this;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public User setRecords(Set<Record> records) {
        this.records = records;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        if (!getFio().equals(user.getFio())) return false;
        if (!getUsername().equals(user.getUsername())) return false;
        if (!getPassword().equals(user.getPassword())) return false;
        if (getConfirmPassword() != null ? !getConfirmPassword().equals(user.getConfirmPassword()) : user.getConfirmPassword() != null)
            return false;
        return getRecords() != null ? getRecords().equals(user.getRecords()) : user.getRecords() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getFio().hashCode();
        result = 31 * result + getUsername().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + (getConfirmPassword() != null ? getConfirmPassword().hashCode() : 0);
        result = 31 * result + (getRecords() != null ? getRecords().hashCode() : 0);
        return result;
    }
}
