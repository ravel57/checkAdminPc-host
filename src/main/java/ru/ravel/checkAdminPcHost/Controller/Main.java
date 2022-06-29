package ru.ravel.checkAdminPcHost.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ravel.checkAdminPcHost.Models.Computer;
import ru.ravel.checkAdminPcHost.Models.RequestComputer;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/API/v1")
public class Main {

    List<Computer> computers = new ArrayList<>();
    private String key = System.getenv("key");

    @GetMapping("/{computerName}")
    public ResponseEntity<Object> getComputerName(@PathVariable String computerName,
                                                  @RequestParam String key) {
        if (key.equals(this.key)) {
            Computer computer = computers.stream()
                    .filter(el -> el.getComputerName().equals(computerName))
                    .findFirst()
                    .orElse(null);
            if (computer != null && ChronoUnit.SECONDS.between(computer.getLastLogTime(), LocalDateTime.now()) < 60)
                return ResponseEntity.status(HttpStatus.OK).body(computer.getLastLoggedUser());
            else
                return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/{computerName}")
    public ResponseEntity<Object> postComputerName(@PathVariable String computerName,
                                                   @RequestBody RequestComputer requestComputer) {
        if (requestComputer.getKey().equals(this.key)) {
            Computer savedComputer = this.computers.stream()
                    .filter(el -> el.getComputerName().equals(computerName))
                    .findFirst()
                    .orElse(null);
            if (savedComputer != null) {
                savedComputer.setLastLogTime(LocalDateTime.now());
                savedComputer.setLastLoggedUser(requestComputer.getLastLoggedUser());
            } else {
                Computer computer = Computer.builder()
                        .computerName(computerName)
                        .lastLoggedUser(requestComputer.getLastLoggedUser())
                        .lastLogTime(LocalDateTime.now())
                        .build();
                computers.add(computer);
            }
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
