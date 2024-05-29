package com.fluxit.microexa.template.application.services.impl;

import com.fluxit.microexa.template.application.services.IExternalService;
import com.fluxit.microexa.template.application.services.ILoggingService;
import com.fluxit.microexa.template.domain.models.AdditionalTemplateUserInfo;
import com.fluxit.microexa.template.domain.ports.in.IGetAdditionalTemplateUserInfo;

public class ExternalService implements IExternalService {

    private final IGetAdditionalTemplateUserInfo getAdditionalTemplateUserInfo;
    private final ILoggingService loggingService;
    public ExternalService(IGetAdditionalTemplateUserInfo getAdditionalTemplateUserInfo, ILoggingService loggingService) {
        this.getAdditionalTemplateUserInfo = getAdditionalTemplateUserInfo;
        this.loggingService = loggingService;
    }

    @Override
    public AdditionalTemplateUserInfo getAdditionalTemplateUserInfo(Long externalReferenceId) {
        loggingService.logDebug("Service Layer: AdditionalTemplateUserInfo getAdditionalTemplateUserInfo({})", externalReferenceId);
        return getAdditionalTemplateUserInfo.getAdditionalTemplateUserInfo(externalReferenceId);
    }
}
