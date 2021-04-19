package net.binxly.constructor.config;

import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.CheckedTemplate;


@CheckedTemplate
public class Templates {

    public static native TemplateInstance packageJsonTemplate();
    public static native TemplateInstance nuxtConfigTemplate();
    public static native TemplateInstance indexTemplate();
    public static native TemplateInstance layoutTemplate();

}