package net.binxly.constructor.models;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.binxly.constructor.models.components.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildRequest {

    @NonNull
    String id;

    @NonNull
    String projectName;

    @NonNull
    NavBar navBar;

    List<Component> components;

}
