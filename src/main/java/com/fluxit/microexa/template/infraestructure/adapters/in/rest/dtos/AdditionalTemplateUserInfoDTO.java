package com.fluxit.microexa.template.infraestructure.adapters.in.rest.dtos;

import com.fluxit.microexa.template.domain.models.AdditionalTemplateUserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdditionalTemplateUserInfoDTO {
    private String userName;
    private String userEmail;

    public static AdditionalTemplateUserInfoDTO from(AdditionalTemplateUserInfo userInfo) {
        return AdditionalTemplateUserInfoDTO.builder()
                .userEmail(userInfo.getUserEmail())
                .userName(userInfo.getUserName())
                .build();
    }
}
