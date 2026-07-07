package it.classe.SpringClass.Mapper;


import it.classe.SpringClass.Dto.TaskDto;

import it.classe.SpringClass.Model.Task;



public class TaskMapper {

    public static TaskDto toDto(Task entity){
        TaskDto dto = new TaskDto();
        dto.setTask_id(entity.getTask_id());
        dto.setTaskName(entity.getTaskName());
        dto.setDescription(entity.getDescription());
        dto.setCompleted(entity.isCompleted());
        return dto;
    }

    public static Task toEntity(TaskDto dto){
        Task entity = new Task();
        entity.setTask_id(dto.getTask_id());
        entity.setTaskName(dto.getTaskName());
        entity.setDescription(dto.getDescription());
        entity.setCompleted(dto.isCompleted());
        return entity;
    }

}
