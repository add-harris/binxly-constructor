package net.binxly.constructor.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.binxly.constructor.models.NavBar;
import net.binxly.constructor.models.components.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildRequestDTO {

    @NonNull
    NavBar navBar;

    List<Component> components;

}
