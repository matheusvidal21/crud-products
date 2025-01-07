package br.ufrn.imd.web.enums;

import java.util.Arrays;

public enum RoleType {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String roleName;

    RoleType(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static RoleType fromString(String roleName) {
        return Arrays.stream(RoleType.values())
                .filter(roleType -> roleType.getRoleName().equalsIgnoreCase(roleName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid role name: " + roleName));
    }

}