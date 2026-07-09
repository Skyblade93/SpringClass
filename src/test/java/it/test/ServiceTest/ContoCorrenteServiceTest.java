package it.test.ServiceTest;

import it.classe.SpringClass.Dto.ContoCorrenteDto;
import it.classe.SpringClass.Mapper.ContoCorrenteMapper;
import it.classe.SpringClass.Mapper.Converter;
import it.classe.SpringClass.Model.ContoCorrente;
import it.classe.SpringClass.Repository.ContoCorrenteRepository;
import it.classe.SpringClass.Service.ContoCorrenteService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

@ExtendWith(MockitoExtension.class)
class ContoCorrenteServiceTest {

    @Mock
    private ContoCorrenteRepository contoCorrenteRepository;

    @Mock
    private JpaRepository<ContoCorrente, Integer> repository;

    @Mock
    private Converter<ContoCorrente, ContoCorrenteDto> converter;

    @Mock
    private ContoCorrenteMapper contoCorrenteMapper;

    @InjectMocks
    private ContoCorrenteService contoCorrenteService;




}