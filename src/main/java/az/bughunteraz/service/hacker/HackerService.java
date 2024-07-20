package az.bughunteraz.service.hacker;

import az.bughunteraz.dto.request.hacker.HackerUpdateRequest;
import az.bughunteraz.dto.response.UserUpdateResponse;
import az.bughunteraz.dto.response.hacker.HackerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HackerService {

    UserUpdateResponse update(String email, HackerUpdateRequest hackerRequestDto);

    List<HackerResponse> getAllHackers();

    List<HackerResponse> searchHackers(String keyword);
}
