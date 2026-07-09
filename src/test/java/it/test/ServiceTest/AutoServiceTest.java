package it.test.ServiceTest;

import it.classe.SpringClass.Dto.AlunnoDto;
import it.classe.SpringClass.Dto.AutoDto;
import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Mapper.AutoMapper;
import it.classe.SpringClass.Mapper.UsersMapper;
import it.classe.SpringClass.Model.Alunno;
import it.classe.SpringClass.Model.Auto;
import it.classe.SpringClass.Model.Users;
import it.classe.SpringClass.Repository.AutoRepository;
import it.classe.SpringClass.Repository.UsersRepository;
import it.classe.SpringClass.Service.AutoService;
import it.classe.SpringClass.Service.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class AutoServiceTest {
    private  final Logger log = org.slf4j.LoggerFactory.getLogger(AutoServiceTest.class);

    @Mock
    private AutoRepository repository;

    @Mock
    private AutoMapper converter;

    @InjectMocks
    private AutoService autoService;



    @Test
     void findAll() {
        AutoDto autoDto=new AutoDto(1,2001,"fiat","panda","grigio",new AlunnoDto(),new UsersDto());
        // Arrange
        Auto auto = new Auto(1,2001,"fiat","panda","grigio", new Users(),new Alunno());



        List<Auto> autos = List.of(auto);


        List<AutoDto> expected = List.of(autoDto);

        when(repository.findAll()).thenReturn(autos);
        log.info("Finding all autos" + autos);
        when(converter.toDTOList(autos)).thenReturn(expected);

        // Act
        List<AutoDto> actual = new ArrayList<>();
        autoService.getAll().forEach(actual::add);
        log.info("aggiungi tutte le cars" + actual);

        // Assert
        assertEquals(expected, actual);

        verify(repository, times(1)).findAll();
        verify(converter, times(1)).toDTOList(autos);
        verifyNoMoreInteractions(repository, converter);

    }

}
