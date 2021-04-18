package net.binxly.constructor.models.files;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class NuxtConfig extends FileModel {

    @Builder.Default
    String fileName = "nuxt.config.js";

    public String projectName;

}
