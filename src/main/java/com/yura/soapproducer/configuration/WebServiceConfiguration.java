package com.yura.soapproducer.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.xml.xsd.XsdSchemaCollection;
import org.springframework.xml.xsd.commons.CommonsXsdSchemaCollection;

@Configuration
@EnableWs
public class WebServiceConfiguration extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/ws/*");
    }

    @Bean
    public XsdSchemaCollection schemaCollection() {
        CommonsXsdSchemaCollection commonsXsdSchemaCollection = new CommonsXsdSchemaCollection(
                new ClassPathResource("users.xsd"),
                new ClassPathResource("orders.xsd"));

        commonsXsdSchemaCollection.setInline(true);

        return commonsXsdSchemaCollection;
    }

    @Bean(name = "appWsdl")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchemaCollection schemaCollection) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UserService");
        wsdl11Definition.setLocationUri("/ws");
        wsdl11Definition.setTargetNamespace("http://dto.soapproducer.yura.com");
        wsdl11Definition.setSchemaCollection(schemaCollection);
        return wsdl11Definition;
    }
}
