package ru.neoflex.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestController
public class CalcController {

    @GetMapping("/calculate")
    public double calculateVacationPay(@RequestParam double averageSalary, @RequestParam int vacationDays) {
        if (averageSalary <= 0 || vacationDays <= 0) {
            throw new IllegalArgumentException("Средняя зарплата и количество дней отпуска должны быть положительными числами.");
        }
        double vacationPay = averageSalary / 30 * vacationDays;
        return vacationPay;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String Exception(IllegalArgumentException e) {
        return "Ошибка: " + e.getMessage();
    }
}