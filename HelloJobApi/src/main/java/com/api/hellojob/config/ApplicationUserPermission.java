package com.api.hellojob.config;

public enum ApplicationUserPermission {
    COMPANY_READ("company:read"),
    COMPANY_WRITE("company:write"),
    CANDIDATE_READ("candidate:read"),
    CANDIDATE_WRITE("candidate:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
