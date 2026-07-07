package it.classe.SpringClass.Mapper;

import it.classe.SpringClass.Dto.AlunnoDto;
import it.classe.SpringClass.Model.Alunno;

public class AlunniMapper {

    public static AlunnoDto toDto (Alunno alunno) {
        AlunnoDto alunnoDto = new AlunnoDto();
        alunnoDto.setId(alunno.getId());
        alunnoDto.setNome(alunno.getNome());
        alunnoDto.setCognome(alunno.getCognome());
        alunnoDto.setVoto(alunno.getVoto());
        alunnoDto.setScuola(alunno.getScuola());

        return alunnoDto;
    }

    public static Alunno toEntity (AlunnoDto alunnoDto) {
        Alunno entity = new  Alunno();
        entity.setId(alunnoDto.getId());
        entity.setNome(alunnoDto.getNome());
        entity.setCognome(alunnoDto.getCognome());
        entity.setVoto(alunnoDto.getVoto());
        entity.setScuola(alunnoDto.getScuola());
        return entity;

    }

}
