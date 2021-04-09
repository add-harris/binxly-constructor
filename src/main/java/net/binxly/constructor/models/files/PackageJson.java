package net.binxly.constructor.models.files;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PackageJson extends FileModel {

    @Builder.Default
    String fileName = "package.json";

    String projectName;

    public String getProjectName() {
        return projectName;
    }
}
