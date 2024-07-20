package az.bughunteraz.controller.user;

import az.bughunteraz.dto.request.company.CompanyUpdateRequest;
import az.bughunteraz.dto.response.UserUpdateResponse;
import az.bughunteraz.dto.response.company.CompanyResponse;
import az.bughunteraz.service.company.CompanyService;
import az.bughunteraz.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final UserService userService;

    @PutMapping("/update-company/{email}")
    public ResponseEntity<UserUpdateResponse> update(@PathVariable String email,
                                                     @RequestBody CompanyUpdateRequest companyUpdateRequest) {
        return new ResponseEntity<>(companyService.update(email, companyUpdateRequest), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<CompanyResponse>> searchHackers(String keyword) {
        return new ResponseEntity<>(companyService.searchCompanies(keyword), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompanyResponse>> getAllHackers() {
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id, String rawPassword) {
        userService.delete(id, rawPassword);
    }
}
