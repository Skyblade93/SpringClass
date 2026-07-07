package it.classe.SpringClass.Mapper;

import it.classe.SpringClass.Dto.OrdineDto;
import it.classe.SpringClass.Model.Ordine;

public class OrdineMapper {

    public static OrdineDto toDto(Ordine entity) {
        OrdineDto dto = new OrdineDto();
        dto.setId(entity.getId());
        dto.setProdotti(entity.getProdotti());
        dto.setDataCreazione(entity.getDataCreazione());
        dto.setImporto(entity.getImporto());
        dto.setQuantita(entity.getQuantita());
        dto.setUsers(UsersMapper.toDto(entity.getUsers()));


        return dto;
    }

    public static Ordine toEntity(OrdineDto dto) {
        Ordine entity = new Ordine();
        entity.setId(dto.getId());
        entity.setProdotti(dto.getProdotti());
        entity.setDataCreazione(dto.getDataCreazione());
        entity.setImporto(dto.getImporto());
        entity.setQuantita(dto.getQuantita());
        entity.setUsers(UsersMapper.toEntity(dto.getUsers()));

        return entity;
    }
}