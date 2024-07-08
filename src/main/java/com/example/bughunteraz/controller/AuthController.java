//package com.example.bughunteraz.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/authenticate")
//public class AuthController {
////
////    private final AuthenticationManager authenticationManager;
////
////    private final CustomUserDetailsServiceImpl userDetailsService;
////
////    private final JwtTokenProvider jwtTokenProvider;
////
////    @PostMapping
////    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
////            throws Exception {
////
////        try {
////            authenticationManager.authenticate(
////                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
////            );
////        } catch (BadCredentialsException e) {
////            throw new Exception("Incorrect email or password", e);
////        }
////
////        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
////        final String jwt = jwtTokenProvider.createToken(userDetails);
////
////        return ResponseEntity.ok(new AuthenticationResponse(jwt));
////    }
//}
