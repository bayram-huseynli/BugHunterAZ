package az.bughunteraz.service.company;

import az.bughunteraz.dto.request.company.CompanyUpdateRequest;
import az.bughunteraz.dto.response.UserUpdateResponse;
import az.bughunteraz.dto.response.company.CompanyResponse;
import az.bughunteraz.entity.Role;
import az.bughunteraz.entity.User;
import az.bughunteraz.exception.CustomException;
import az.bughunteraz.repository.UserRepository;
import az.bughunteraz.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    @Override
    public UserUpdateResponse update(String email, CompanyUpdateRequest companyUpdateRequest) {

        User company = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("User Not Found"));

        if (companyUpdateRequest.getPassword() != null && !companyUpdateRequest.getPassword().isEmpty()) {
            // ConfirmPassword kontrolü
            if (!companyUpdateRequest.getPassword().equals(companyUpdateRequest.getConfirmPassword())) {
                throw new CustomException("Password and Confirm Password do not match");
            }
            String encodedPassword = passwordEncoder.encode(companyUpdateRequest.getPassword());
            companyUpdateRequest.setPassword(encodedPassword);
        }

        modelMapper.map(companyUpdateRequest, company);

        User updateHacker = userRepository.save(company);

        emailService.sendUpdateEmail(company.getEmail());

        return modelMapper.map(updateHacker, UserUpdateResponse.class);
    }

    @Override
    public List<CompanyResponse> getAllCompanies() {
        List<User> companies = userRepository.findByRole(Role.COMPANY);
        return companies.stream()
                .map(company -> modelMapper.map(company, CompanyResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CompanyResponse> searchCompanies(String keyword) {
        List<User> companies = userRepository.findByRoleAndEmailContainingIgnoreCaseOrRoleAndNameContainingIgnoreCase(
                Role.COMPANY, keyword, Role.COMPANY, keyword);
        return companies.stream()
                .map(company -> modelMapper.map(company, CompanyResponse.class))
                .collect(Collectors.toList());
    }
}
