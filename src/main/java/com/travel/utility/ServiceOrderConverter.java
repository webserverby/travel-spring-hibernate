package com.travel.utility;

import com.travel.model.ServiceOrder;
import com.travel.service.ServiceOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ServiceOrderConverter implements Converter<Object, ServiceOrder>{

        static final Logger logger = LoggerFactory.getLogger(ServiceOrderConverter.class);

    @Autowired
    private ServiceOrderService serviceOrderService;

        public ServiceOrder convert(Object element) {
            Integer idService = Integer.parseInt((String)element);
            ServiceOrder serviceOrder = serviceOrderService.findById(idService);
            return serviceOrder;
        }


}
