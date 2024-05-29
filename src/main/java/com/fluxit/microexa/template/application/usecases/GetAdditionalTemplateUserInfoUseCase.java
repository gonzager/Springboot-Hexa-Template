package com.fluxit.microexa.template.application.usecases;

import com.fluxit.microexa.template.domain.models.AdditionalTemplateUserInfo;
import com.fluxit.microexa.template.domain.ports.in.IGetAdditionalTemplateUserInfo;
import com.fluxit.microexa.template.domain.ports.out.IExternalServicePort;

public class GetAdditionalTemplateUserInfoUseCase implements IGetAdditionalTemplateUserInfo {

    private final IExternalServicePort externalServicePort;

    public GetAdditionalTemplateUserInfoUseCase(IExternalServicePort externalServicePort) {
        this.externalServicePort = externalServicePort;
    }

    @Override
    public AdditionalTemplateUserInfo getAdditionalTemplateUserInfo(Long externalReferenceId) {
        return externalServicePort.getAdditionalTemplateUserInfo(externalReferenceId);
    }
}
