package az.bughunteraz.service.hacker;

import az.bughunteraz.dto.request.hacker.HackerUpdateRequest;
import az.bughunteraz.dto.response.UserUpdateResponse;
import az.bughunteraz.dto.response.hacker.HackerResponse;
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
public class HackerServiceImpl implements HackerService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    @Override
    public UserUpdateResponse update(String email, HackerUpdateRequest hackerRequestDto) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("User Not Found"));

        if (hackerRequestDto.getPassword() != null && !hackerRequestDto.getPassword().isEmpty()) {
            // ConfirmPassword kontrolü
            if (!hackerRequestDto.getPassword().equals(hackerRequestDto.getConfirmPassword())) {
                throw new CustomException("Password and Confirm Password do not match");
            }
            String encodedPassword = passwordEncoder.encode(hackerRequestDto.getPassword());
            hackerRequestDto.setPassword(encodedPassword);
        }

        modelMapper.map(hackerRequestDto, user);

        User updateHacker = userRepository.save(user);

        emailService.sendUpdateEmail(user.getEmail());

        return modelMapper.map(updateHacker, UserUpdateResponse.class);
    }

    @Override
    public List<HackerResponse> getAllHackers() {
        List<User> hackers = userRepository.findByRole(Role.HACKER);
        return hackers.stream()
                .map(hacker -> modelMapper.map(hacker, HackerResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<HackerResponse> searchHackers(String keyword) {
        List<User> hackers = userRepository.findByRoleAndEmailContainingIgnoreCaseOrRoleAndNameContainingIgnoreCase(
                Role.HACKER, keyword, Role.HACKER, keyword);
        return hackers.stream()
                .map(hacker -> modelMapper.map(hacker, HackerResponse.class))
                .collect(Collectors.toList());
    }
}
