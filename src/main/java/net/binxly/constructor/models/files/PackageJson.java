package net.binxly.constructor.models.files;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageJson extends FileModel {

    String projectName;

}
