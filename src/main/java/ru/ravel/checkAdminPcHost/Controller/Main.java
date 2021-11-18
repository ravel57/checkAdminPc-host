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
    private String key = "123";

    @GetMapping("/{computerName}")
    ResponseEntity<Object> getComputerName(
            @PathVariable("computerName") String computerName,
            @RequestParam("key") String key
    ) {
        Computer computer = computers.stream()
                .filter(el -> el.computerName.equals(computerName))
                .findAny()
                .orElse(null);
        if (computer != null && ChronoUnit.SECONDS.between(computer.lastLogTime, LocalDateTime.now()) < 60)
            return ResponseEntity.status(HttpStatus.OK).body(computer.lastLoggedUser);
        else
            return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/{computerName}")
    ResponseEntity<Object> postComputerName(
            @PathVariable("computerName") String computerName,
            @RequestBody RequestComputer requestComputer
    ) {
        if (requestComputer.key.equals(this.key)) {
            Computer computer = Computer.builder()
                    .computerName(computerName)
                    .lastLoggedUser(requestComputer.lastLoggedUser)
                    .lastLogTime(LocalDateTime.now())
                    .build();
            if (key == this.key && computers.stream().anyMatch(el -> computer.computerName.equals(el.computerName)))
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(this.computers.stream()
                                .filter(el -> el.computerName.equals(computer.computerName))
                                .findAny()
                        );
            else {
                computers.add(computer);
                return ResponseEntity.status(HttpStatus.OK).body(computer);
            }
        } else
            return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
