package az.bughunteraz.dto.response;

import az.bughunteraz.entity.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserUpdateResponse {

    Long id;

    String name;

    String email;

    Role role;

    String password;
}
