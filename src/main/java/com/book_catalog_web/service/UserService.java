package com.book_catalog_web.service;

import com.book_catalog_web.dto.request.UserRequestDTO;
import com.book_catalog_web.dto.response.ResultPageResponseDTO;
import com.book_catalog_web.dto.response.UserListResponseDTO;
import com.book_catalog_web.dto.response.UserResponseDTO;

public interface UserService {
    public void createUser(UserRequestDTO dto);
    public UserResponseDTO findUserDetail(Long id);
    public ResultPageResponseDTO<UserListResponseDTO> findAllUser(
            Integer pages,
            Integer limit,
            String sortBy,
            String direction,
            String userName,
            String email,
            String mobileNumber
    );
    public void updateUser(Long id, UserRequestDTO dto);
    public void deleteUser(Long id);
}
