package com.fluxit.microexa.template.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdditionalTemplateUserInfo {
    private final Long userId;
    private final String userName;
    private final String userEmail;
}
