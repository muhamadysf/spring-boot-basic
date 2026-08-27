package com.book_catalog_web.service.Impl;

import com.book_catalog_web.domain.AppUser;
import com.book_catalog_web.dto.request.UserRequestDTO;
import com.book_catalog_web.dto.response.ResultPageResponseDTO;
import com.book_catalog_web.dto.response.UserListResponseDTO;
import com.book_catalog_web.dto.response.UserResponseDTO;
import com.book_catalog_web.repository.AppUserRepository;
import com.book_catalog_web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AppUserRepository appUserRepository;

    @Override
    public void createUser(UserRequestDTO dto) {

        AppUser user = new AppUser();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setMobileNumber(dto.mobileNumber());

        appUserRepository.save(user);
    }

    @Override
    public UserResponseDTO findUserDetail(Long id) {

        AppUser user = appUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User id not found"));

        return new UserResponseDTO(user.getId(), user.getSecureId(), user.getName(), user.getEmail(), user.getMobileNumber());
    }

    @Override
    public ResultPageResponseDTO<UserListResponseDTO> findAllUser(Integer pages, Integer limit, String sortBy, String direction, String userName, String email, String mobileNumber) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.valueOf(direction.toUpperCase()), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);

        userName = StringUtils.isBlank(userName) ? "%" : "%" + userName + "%";
        email = StringUtils.isBlank(email) ? "%": "%" + email + "%" ;
        mobileNumber = StringUtils.isBlank(mobileNumber) ? "%" : "%" + mobileNumber + "%" ;

        Page<AppUser> userPage = appUserRepository.findAllUser(userName, email, mobileNumber, pageable);

        List<UserListResponseDTO> usersDTO = userPage.stream().map(u -> new UserListResponseDTO(u.getId(), u.getSecureId(), u.getName())).toList();

        return new ResultPageResponseDTO<>(usersDTO, userPage.getTotalPages(), userPage.getTotalElements());
    }

    @Override
    public void updateUser(Long id, UserRequestDTO dto) {
        AppUser user = appUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.name());
        user.setEmail(dto.name());
        user.setMobileNumber(dto.mobileNumber());

        appUserRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        appUserRepository.softDelete(id);
    }
}
