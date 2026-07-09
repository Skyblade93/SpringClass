package it.classe.SpringClass.Service;
import it.classe.SpringClass.Dto.AutoDto;
import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Mapper.Converter;
import it.classe.SpringClass.Mapper.AutoMapper;
import it.classe.SpringClass.Mapper.UsersMapper;
import it.classe.SpringClass.Model.Auto;
import it.classe.SpringClass.Model.Users;
import it.classe.SpringClass.Repository.AutoRepository;
import it.classe.SpringClass.Repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
@Service
public class AutoService extends AbstractService<Auto, AutoDto> {
    private static final Logger log = LoggerFactory.getLogger(AutoService.class);

    private final AutoMapper autoMapper;

    private final AutoRepository autoRepository;

    //@Value("${spring.datasource.url}")
    private String databaseUrl;

    public AutoService(JpaRepository<Auto, Integer> repository, Converter<Auto, AutoDto> converter,
                       AutoMapper autoMapper, AutoRepository autoRepository) {
        super(repository, converter);
        this.autoMapper = autoMapper;
        this.autoRepository = autoRepository;
    }


    public AutoDto findByModello(String modello) {
        return autoMapper.toDTO(autoRepository.findByModello(modello));
    }

    public AutoDto findByColore(String colore) {
        return autoMapper.toDTO(autoRepository.findByColore(colore));
    }

    public AutoDto findByMarca(String marca) {
        return autoMapper.toDTO(autoRepository.findByMarca(marca));
    }

    public AutoDto findByAnno(Integer anno) {
        return autoMapper.toDTO(autoRepository.findByAnno(anno));
    }


    public AutoDto AutoUsersId(Integer id) {
        return autoMapper.toDTO(autoRepository.AutoUsersId(id));
    }
    public AutoDto coloreN(String colore) {
        return autoMapper.toDTO(autoRepository.coloreN(colore));
    }

    public AutoDto marcaN(String marca) {
        return autoMapper.toDTO(autoRepository.marcaN(marca));
    }

    public AutoDto annoN(Integer anno) {
        return autoMapper.toDTO(autoRepository.annoN(anno));
    }


    private String getDatabaseUrl() {
        log.info(databaseUrl);
        log.warn(databaseUrl);
        log.error(databaseUrl);
        return databaseUrl;
    }
}
