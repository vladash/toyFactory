package ua.kpi.tef.toys.pojo.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleType implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
