package com.book_catalog_web.controller;

import com.book_catalog_web.domain.AppUser;
import com.book_catalog_web.dto.request.UserRequestDTO;
import com.book_catalog_web.dto.response.ResultPageResponseDTO;
import com.book_catalog_web.dto.response.UserListResponseDTO;
import com.book_catalog_web.dto.response.UserResponseDTO;
import com.book_catalog_web.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/app-user")
public class AppUserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createdAppUser(@RequestBody @Valid UserRequestDTO dto) {
        userService.createUser(dto);
        return ResponseEntity.created(URI.create("/v1/app-user")).build();
    }

    @GetMapping
    public ResponseEntity<ResultPageResponseDTO<UserListResponseDTO>> findUserList(
            @RequestParam(defaultValue = "0") Integer pages,
            @RequestParam(defaultValue = "10") Integer limit,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String mobileNumber
    ){
        return ResponseEntity.ok(userService.findAllUser(pages, limit, sortBy, direction, name, email, mobileNumber));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> findUserDetail(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findUserDetail(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateUser(@PathVariable UUID id, @RequestBody UserRequestDTO dto) {

        return null;
    }

}
