package com.fluxit.microexa.template.domain.ports.out;

import com.fluxit.microexa.template.domain.ports.in.ICreateTemplate;
import com.fluxit.microexa.template.domain.ports.in.IDeleteTemplate;
import com.fluxit.microexa.template.domain.ports.in.IRetrieveTemplate;

public interface ITemplateRepositoryPort extends ICreateTemplate, IRetrieveTemplate, IDeleteTemplate {}
