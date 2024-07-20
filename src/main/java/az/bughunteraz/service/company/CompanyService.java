package az.bughunteraz.service.company;

import az.bughunteraz.dto.request.company.CompanyUpdateRequest;
import az.bughunteraz.dto.response.UserUpdateResponse;
import az.bughunteraz.dto.response.company.CompanyResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    UserUpdateResponse update(String email, CompanyUpdateRequest companyUpdateRequest);

    List<CompanyResponse> searchCompanies(String keyword);

    List<CompanyResponse> getAllCompanies();
}
