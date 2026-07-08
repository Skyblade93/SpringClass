package it.classe.SpringClass.Service;

import it.classe.SpringClass.Dto.UsersDto;
import it.classe.SpringClass.Mapper.Converter;
import it.classe.SpringClass.Mapper.UsersMapper;
import it.classe.SpringClass.Model.Users;
import it.classe.SpringClass.Repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class UsersService extends AbstractService<Users, UsersDto> {

    private static final Logger log = LoggerFactory.getLogger(UsersService.class);

    private final UsersMapper userMapper;

    private final UsersRepository usersRepository;

    //@Value("${spring.datasource.url}")
    private String databaseUrl;

    public UsersService(JpaRepository<Users, Integer> repository, Converter<Users, UsersDto> converter,
                       UsersMapper userMapper, UsersRepository userRepository) {
        super(repository, converter);
        this.userMapper = userMapper;
        this.usersRepository = userRepository;
    }


    public UsersDto findByNome(String nome){
        return  userMapper.toDTO(usersRepository.findByUsername(nome));

    }


    public String getDatabaseUrl() {
        log.info(databaseUrl);
        log.warn(databaseUrl);
        log.error(databaseUrl);
        return databaseUrl;
    }
}