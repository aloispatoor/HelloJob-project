package com.api.hellojob.config;

import java.util.Set;

import static com.api.hellojob.config.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    COMPANY(Set.of(COMPANY_READ, COMPANY_WRITE)),
    JOBSEEKER(Set.of(CANDIDATE_READ, CANDIDATE_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
