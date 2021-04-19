package net.binxly.constructor.models.files;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import net.binxly.constructor.models.components.NavBar;

@Data
@Builder
@AllArgsConstructor
public class LayoutVue extends FileModel {

    @Builder.Default
    String fileName = "default.vue";

    NavBar navBar;

}
