package it.classe.SpringClass.Service;

import it.classe.SpringClass.Dto.AlunnoDto;
import it.classe.SpringClass.Mapper.AlunniMapper;
import it.classe.SpringClass.Mapper.Converter;
import it.classe.SpringClass.Model.Alunno;
import it.classe.SpringClass.Repository.AlunnoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AlunnoService extends AbstractService<Alunno, AlunnoDto> {

    private final AlunniMapper alunnoMapper;
    private final AlunnoRepository alunnoRepository;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    public AlunnoService(JpaRepository<Alunno, Integer> repository,
                         Converter<Alunno, AlunnoDto> converter,
                         AlunniMapper alunnoMapper,
                         AlunnoRepository alunnoRepository) {
        super(repository, converter);

        this.alunnoMapper = alunnoMapper;
        this.alunnoRepository = alunnoRepository;
    }

    public AlunnoDto findByNome(String nome) {
        return alunnoMapper.toDTO(alunnoRepository.findByNome(nome));
    }
    public AlunnoDto findByCognome(String cognome) {
        return alunnoMapper.toDTO(alunnoRepository.findByCognome(cognome));
    }


    public String getDatabaseUrl() {
        return databaseUrl;
    }
}