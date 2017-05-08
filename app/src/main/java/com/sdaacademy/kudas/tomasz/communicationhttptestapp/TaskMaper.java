package com.sdaacademy.kudas.tomasz.communicationhttptestapp;

import com.sdaacademy.kudas.tomasz.communicationhttptestapp.dto.TaskDTO;
import com.sdaacademy.kudas.tomasz.communicationhttptestapp.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RENT on 2017-04-29.
 */

public class TaskMaper {

    public static List<Task> TaskDTOToTaskList (List<TaskDTO> taskDTOList) {
        List<Task> tasks =  new ArrayList<>();
        for (TaskDTO taskDTO:taskDTOList) {
            Long id = taskDTO.getId();
            Boolean completed = taskDTO.isCompleted();
            String value = taskDTO.getValue();
            tasks.add(new Task(id, completed, value));
        }
        return tasks;
    }

    public static List<TaskDTO> TaskToTaskDTOList (List<Task> taskList) {
        List<TaskDTO> tasks = new ArrayList<>();
        for (Task task:taskList){
            Long id = task.getId();
            Boolean completed = task.isCompleted();
            String value = task.getValue();
            tasks.add(new TaskDTO(id, 10, completed, value));
        }
        return tasks;
    }

    public static Task TaskDTOToTask (TaskDTO taskDTO) {
            Long id = taskDTO.getId();
            Boolean completed = taskDTO.isCompleted();
            String value = taskDTO.getValue();
            Task task = new Task(id, completed, value);

        return task;
    }

    public static TaskDTO TaskToTaskDTO (Task task) {
            Long id = task.getId();
            Boolean completed = task.isCompleted();
            String value = task.getValue();
            TaskDTO taskDTO = new TaskDTO(id, 8l, completed, value);
        return taskDTO;
    }

    public static TaskDTO TextToTaskDTO (String message) {
        Long id = null;
        Boolean completed = false;
        String value = message;
        TaskDTO taskDTO = new TaskDTO(id, 8l, completed, value);
        return taskDTO;
    }
}
