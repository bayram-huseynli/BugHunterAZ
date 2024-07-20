package az.bughunteraz.controller.user;

import az.bughunteraz.dto.request.hacker.HackerUpdateRequest;
import az.bughunteraz.dto.response.UserUpdateResponse;
import az.bughunteraz.dto.response.hacker.HackerResponse;
import az.bughunteraz.service.hacker.HackerService;
import az.bughunteraz.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hacker")
@RequiredArgsConstructor
public class HackerController {

    private final HackerService hackerService;

    private final UserService userService;

    @PutMapping("/update-hacker/{email}")
    public ResponseEntity<UserUpdateResponse> update(@PathVariable String email,
                                                     @RequestBody HackerUpdateRequest hackerRequestDto) {
        return new ResponseEntity<>(hackerService.update(email, hackerRequestDto), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<HackerResponse>> searchHackers(String keyword) {
        return new ResponseEntity<>(hackerService.searchHackers(keyword), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<HackerResponse>> getAllHackers() {
        return new ResponseEntity<>(hackerService.getAllHackers(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id, String rawPassword) {
        userService.delete(id, rawPassword);
    }
}
