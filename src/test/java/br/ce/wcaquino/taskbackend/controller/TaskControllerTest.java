package br.ce.wcaquino.taskbackend.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.ce.wcaquino.taskbackend.model.Task;
import br.ce.wcaquino.taskbackend.repo.TaskRepo;
import br.ce.wcaquino.taskbackend.utils.ValidationException;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;


public class TaskControllerTest {
    
    @Mock
    TaskRepo todoRepo;

    @InjectMocks
    TaskController controller;

    @Before
    public void setup(){
        controller = new TaskController();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void naoDeveAsseitarTasksSemDescricao(){
        Task toDo = new Task();
        toDo.setTask("");
        toDo.setDueDate(LocalDate.now());
        try {
            controller.save(toDo);
            fail();
        } catch (ValidationException e) {
            assertEquals("Fill the task description", e.getMessage());
        }
    }

    @Test
    public void naoDeveAsseitarTasksSemData(){
        Task toDo = new Task();
        toDo.setTask("descricao");
        try {
            controller.save(toDo);
            fail();
        } catch (ValidationException e) {
            assertEquals("Fill the due date", e.getMessage());
        }
    }

    @Test
    public void naoDeveAsseitarTasksComDataPassada(){
        Task toDo = new Task();
        toDo.setTask("descricao");
        toDo.setDueDate(LocalDate.of(2010, Month.APRIL, 1));
        try {
            controller.save(toDo);
            fail();
        } catch (ValidationException e) {
            assertEquals("Due date must not be in past", e.getMessage());
        }
    }

    @Test
    public void deveAsseitarTask() throws ValidationException {
        Task toDo = new Task();
        toDo.setTask("descricao");
        toDo.setDueDate(LocalDate.now());
        controller.save(toDo);
        Mockito.verify(todoRepo).save(toDo);
    }
}