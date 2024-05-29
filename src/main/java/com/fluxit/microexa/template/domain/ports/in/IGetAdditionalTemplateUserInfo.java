package com.fluxit.microexa.template.domain.ports.in;

import com.fluxit.microexa.template.domain.models.AdditionalTemplateUserInfo;

public interface IGetAdditionalTemplateUserInfo {
    AdditionalTemplateUserInfo getAdditionalTemplateUserInfo(Long externalReferenceId);
}
