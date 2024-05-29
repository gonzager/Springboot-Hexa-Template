package com.fluxit.microexa.template.application.services;


import com.fluxit.microexa.template.domain.ports.in.ICreateTemplate;
import com.fluxit.microexa.template.domain.ports.in.IDeleteTemplate;
import com.fluxit.microexa.template.domain.ports.in.IRetrieveTemplate;

public interface ITemplateService extends IRetrieveTemplate, ICreateTemplate, IDeleteTemplate { }
