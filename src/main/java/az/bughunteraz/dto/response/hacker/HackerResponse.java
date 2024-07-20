package az.bughunteraz.dto.response.hacker;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HackerResponse {

    Long id;

    String name;

    String email;

    String password;

    String portfolio;
}
