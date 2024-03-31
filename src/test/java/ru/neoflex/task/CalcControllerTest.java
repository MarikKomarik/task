package ru.neoflex.task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.neoflex.task.controller.CalcController;

@WebMvcTest(CalcController.class)
public class CalcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCalculate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("averageSalary", "1000")
                        .param("vacationDays", "24"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("800.0"));
    }

    @Test
    public void testCalculateWithNegativeValues() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("averageSalary", "-1000")
                        .param("vacationDays", "20"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Ошибка: Средняя зарплата и количество дней отпуска должны быть положительными числами."));
    }

    @Test
    public void testCalculateWithString() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("averageSalary", "Привет")
                        .param("vacationDays", "20"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Ошибка: For input string: \"Привет\""));
    }
}