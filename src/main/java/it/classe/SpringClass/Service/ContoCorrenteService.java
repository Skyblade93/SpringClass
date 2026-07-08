package it.classe.SpringClass.Service;

import it.classe.SpringClass.Dto.ContoCorrenteDto;
import it.classe.SpringClass.Mapper.ContoCorrenteMapper;
import it.classe.SpringClass.Mapper.Converter;
import it.classe.SpringClass.Model.ContoCorrente;
import it.classe.SpringClass.Repository.ContoCorrenteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ContoCorrenteService extends AbstractService<ContoCorrente ,ContoCorrenteDto >{

    private static final Logger log = LoggerFactory.getLogger(UsersService.class);

    private  final ContoCorrenteMapper contoCorrenteMapper;

    private  final ContoCorrenteRepository contoCorrenteRepository;

    public ContoCorrenteService(JpaRepository<ContoCorrente, Integer> repository, Converter<ContoCorrente, ContoCorrenteDto> converter,
                                ContoCorrenteMapper contoCorrenteMapper,
                       ContoCorrenteRepository contoCorrenteRepository){

        super(repository,converter);
            this.contoCorrenteMapper=contoCorrenteMapper;
            this.contoCorrenteRepository=contoCorrenteRepository;
    }

        public ContoCorrenteDto findByIdConto(Integer idConto){
            return contoCorrenteMapper.toDTO(contoCorrenteRepository.findById(idConto));
        }

            public ContoCorrenteDto findByName(String nome){
        return contoCorrenteMapper.toDTO(contoCorrenteRepository.findByName(nome));

            }

            public ContoCorrenteDto findByEmail(String email)
            {
                return contoCorrenteMapper.toDTO(contoCorrenteRepository.findByEmail(email));
            }
            public ContoCorrenteDto findByCognome(String cognome){
        return contoCorrenteMapper.toDTO(contoCorrenteRepository.findByEmail(cognome));
            }



}
