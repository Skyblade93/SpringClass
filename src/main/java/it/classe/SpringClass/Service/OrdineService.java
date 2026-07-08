package it.classe.SpringClass.Service;


import it.classe.SpringClass.Dto.OrdineDto;
import it.classe.SpringClass.Mapper.Converter;
import it.classe.SpringClass.Mapper.OrdineMapper;
import it.classe.SpringClass.Model.Ordine;
import it.classe.SpringClass.Model.Users;
import it.classe.SpringClass.Repository.OrdineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrdineService extends AbstractService<Ordine, OrdineDto> {

    private static final Logger log = LoggerFactory.getLogger(UsersService.class);

    private final OrdineMapper ordineMapper;

    private final OrdineRepository ordineRepository;

    //@Value("${spring.datasource.url}")
    private String databaseUrl;

    public OrdineService(JpaRepository<Ordine, Integer> repository, Converter<Ordine, OrdineDto> converter,
                        OrdineMapper ordineMapper, OrdineRepository ordineRepository) {
        super(repository, converter);
        this.ordineMapper = ordineMapper;
        this.ordineRepository = ordineRepository;
    }



    public OrdineDto findByDataCreazione(LocalDateTime dataCreazione){
        return  ordineMapper.toDTO(ordineRepository.findByDataCreazione(dataCreazione));

    }

    public List<OrdineDto> findByImporto(double importo){
        return  ordineMapper.toDTOList(ordineRepository.findByImporto(importo));

    }

    public List<OrdineDto> findByQuantita(int quantita){
        return  ordineMapper.toDTOList(ordineRepository.findByQuantita(quantita));

    }

    public List<OrdineDto> findByUsers(Users users){
        return  ordineMapper.toDTOList(ordineRepository.findByUsers(users));

    }


    public String getDatabaseUrl() {
        log.info(databaseUrl);
        log.warn(databaseUrl);
        log.error(databaseUrl);
        return databaseUrl;
    }
}