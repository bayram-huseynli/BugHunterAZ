package az.bughunteraz.dto.request.hacker;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HackerUpdateRequest {

    String name;

    String password;

    String confirmPassword;

    String portfolio;
}
