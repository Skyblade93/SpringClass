package it.classe.SpringClass.Mapper;


import it.classe.SpringClass.Dto.TaskDto;

import it.classe.SpringClass.Model.Task;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper extends AbstractConverter<Task,TaskDto>{

    final private ModelMapper mapper = new ModelMapper();

    @Override
    public Task toEntity(TaskDto dto){return mapper.map(dto, Task.class);}

    @Override
    public TaskDto toDTO(Task entity) {
        return mapper.map(entity,TaskDto.class);
    }
}
