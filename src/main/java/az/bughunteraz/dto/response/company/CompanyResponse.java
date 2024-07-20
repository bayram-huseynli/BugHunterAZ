package az.bughunteraz.dto.response.company;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyResponse {

    Long id;

    String name;

    String email;

    String contactInfo;

    String password;
}
