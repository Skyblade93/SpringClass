package it.test.ServiceTest;

import it.classe.SpringClass.Dto.CardsDto;
import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Mapper.Converter;
import it.classe.SpringClass.Mapper.UsersMapper;
import it.classe.SpringClass.Model.Cards;
import it.classe.SpringClass.Model.Users;
import it.classe.SpringClass.Repository.UsersRepository;
import it.classe.SpringClass.Service.UsersService;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsersServiceTest {

    private  final Logger log = org.slf4j.LoggerFactory.getLogger(UsersServiceTest.class);

    @Mock
    private UsersRepository repository;

    @Mock
    private UsersMapper converter;

    @InjectMocks
    private UsersService usersService;

    @Test
    void findAll() {
        // Arrange
        Users user = new Users(
                1,
                "paperino",
                "098765",
                "paperino@papero.it",
                new ArrayList<>()
        );

        List<Users> users = List.of(user);

        UsersDto dto = new UsersDto(
                1,
                "paperino",
                "098765",
                "paperino@papero.it",
                new ArrayList<>()
        );

        List<UsersDto> expected = List.of(dto);

        when(repository.findAll()).thenReturn(users);
        log.info("Finding all users" + users);
        when(converter.toDTOList(users)).thenReturn(expected);

        // Act
        List<UsersDto> actual = new ArrayList<>();
        usersService.getAll().forEach(actual::add);
        log.info("aggiungi tutti gli users" + actual);

        // Assert
        assertEquals(expected, actual);

        verify(repository, times(1)).findAll();
        verify(converter, times(1)).toDTOList(users);
        verifyNoMoreInteractions(repository, converter);

    }

    @Test
    void findById() {
        Users user = new Users(1,"topolino","12345","topo@topomail.com",new ArrayList<>());



        // Stub the repository to return Optional with user
        when(repository.findById(1)).thenReturn(Optional.of(user));


        // Act: call the service method
        UsersDto actual = when(converter.toDTO(any(Users.class)))
                .thenReturn(new UsersDto(1,"topolino","12345","topo@topomail.com",new ArrayList<>()));

        log.info("Finding user by ID: " + actual);

        // Assert
        assertEquals(user.getId(), actual.getId());
        // Verify interactions
        verify(repository, times(1)).findById(1);
       // verify(converter, times(1)).toDTO(user);
       // verifyNoMoreInteractions(repository, converter);
    }

}
