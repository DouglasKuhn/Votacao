package br.com.compasso.votacao.controller;

import br.com.compasso.votacao.controller.dto.UserDto;
import br.com.compasso.votacao.controller.form.UserForm;
import br.com.compasso.votacao.converter.UserConverter;
import br.com.compasso.votacao.entity.User;
import br.com.compasso.votacao.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final UserConverter userConverter;

    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> list() {

        List<UserDto> usersDto = userConverter.userListToUserDtoList(userService.getAll());
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }


    @PostMapping
    @Transactional
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {

        User user = userConverter.userFormToUser(form);
        userService.save(user);

        URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDto(user));
    }

}
