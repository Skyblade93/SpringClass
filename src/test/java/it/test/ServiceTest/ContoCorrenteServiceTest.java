package it.test.ServiceTest;

import it.classe.SpringClass.Dto.ContoCorrenteDto;
import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Mapper.ContoCorrenteMapper;
import it.classe.SpringClass.Model.Cards;
import it.classe.SpringClass.Model.ContoCorrente;
import it.classe.SpringClass.Model.Users;
import it.classe.SpringClass.Repository.ContoCorrenteRepository;
import it.classe.SpringClass.Service.ContoCorrenteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class ContoCorrenteServiceTest {

    private  final Logger log = org.slf4j.LoggerFactory.getLogger(UsersServiceTest.class);

    @Mock
    private ContoCorrenteRepository contoRepository;

    @Mock
    private ContoCorrenteService contoService;

    @InjectMocks
    private ContoCorrenteMapper contoMapper;


    @Test
    void findAll(){

        ContoCorrente contoCorrente=new ContoCorrente(1,
                "2332553253151",
                "Nicola", "Pignatiello",
                "nico@gmail.it", "",
                new ArrayList<Cards>());

        List<ContoCorrente>listConto= List.of(contoCorrente);


        ContoCorrenteDto contoCorrenteDto=new ContoCorrenteDto(1,
                "2332553253151",
                "Nicola", "Pignatiello",
                "nico@gmail.it", "",
                new ArrayList<Cards>());

        List<ContoCorrenteDto>contoDto=List.of(contoCorrenteDto);

        //trova la lista dell'entity Contocorrente
        //comparazione tra la lista dell'entity con quella del Dto

        when(contoRepository.findAll()).thenReturn(listConto);
        log.info("Caricati tutti i conti correnti");
        when(contoMapper.toDTOList(listConto)).thenReturn(contoDto);

        //creiamo una nuova lista di appoggio e ci aggiungo i miei Dto del conto corrente
        List<ContoCorrenteDto> appoggio = new ArrayList<>();
        contoService.getAll().forEach(appoggio::add);
        log.info("aggiungi i conti correnti " + appoggio);

        // Assert
        assertEquals(contoDto, appoggio);

        verify(contoRepository, times(1)).findAll();
        verify(contoMapper,times(1)).toDTOList(listConto);
        verifyNoMoreInteractions(contoRepository, contoMapper);

    }

    @Test
    void findById(){

        ContoCorrente contoCorrente=new ContoCorrente(1,
                "787890123456",
                "Fabio", "Esposito",
                "fabio@gmail.it", "",
                new ArrayList<Cards>());


        when(contoRepository.findById(1)).thenReturn(Optional.of(contoCorrente));

        ContoCorrente actual = when(contoMapper.toDTO(any(ContoCorrente.class))).thenReturn(new ContoCorrenteDto(1,
                "","",
                "","",
                "", new ArrayList<>()));

    }

}
