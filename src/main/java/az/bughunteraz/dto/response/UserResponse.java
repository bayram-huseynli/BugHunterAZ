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
public class UserResponse {

    Long id;

    String email;

    Role role;

    int code;

    String qrCodeUrl;
}
