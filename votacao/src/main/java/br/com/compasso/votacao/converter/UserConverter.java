package br.com.compasso.votacao.converter;

import br.com.compasso.votacao.controller.dto.UserDto;
import br.com.compasso.votacao.controller.form.UserForm;
import br.com.compasso.votacao.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public List<UserDto> userListToUserDtoList(List<User> users) {
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

    public User userFormToUser(UserForm form) {
        return new User(form.getName(), form.getEmail(), form.getPassword());
    }
}
