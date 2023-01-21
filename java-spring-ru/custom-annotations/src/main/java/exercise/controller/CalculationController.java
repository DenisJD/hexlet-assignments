package exercise.controller;

import exercise.calculator.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculationController {

    @Autowired
    Calculator calculator;

    @GetMapping("/sum")
    public String getSum(@RequestParam int a, @RequestParam int b) {
        int result = calculator.sum(a, b);
        return String.valueOf(result);
    }

    @GetMapping("/mult")
    public String getMult(@RequestParam int a, @RequestParam int b) {
        int result = calculator.mult(a, b);
        return String.valueOf(result);
    }
}
