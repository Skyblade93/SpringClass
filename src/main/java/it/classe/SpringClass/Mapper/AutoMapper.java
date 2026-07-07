package it.classe.SpringClass.Mapper;

import it.classe.SpringClass.Dto.AutoDto;
import it.classe.SpringClass.Model.Auto;

public class AutoMapper {
    public static AutoDto toDto(Auto entity) {
        AutoDto dto = new AutoDto();
        dto.setId(entity.getId());
        dto.setAnno(entity.getAnno());
        dto.setMarca(entity.getMarca());
        dto.setModello(entity.getModello());
        dto.setColore(entity.getColore());
        return dto;
    }
        public static Auto toEntity(AutoDto dto) {
            Auto entity = new Auto();
            entity.setId(dto.getId());
            entity.setAnno(dto.getAnno());
            entity.setMarca(dto.getMarca());
            entity.setModello(dto.getModello());
            entity.setColore(dto.getColore());
            return entity;

    }
}
