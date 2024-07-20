package az.bughunteraz.dto.request.company;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyUpdateRequest {

    String companyName;

    String contactInfo;

    String password;

    String confirmPassword;

}
