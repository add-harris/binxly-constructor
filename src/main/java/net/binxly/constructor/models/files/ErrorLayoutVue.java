package net.binxly.constructor.models.files;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorLayoutVue extends FileModel {

    @Builder.Default
    String fileName = "error.vue";

}
