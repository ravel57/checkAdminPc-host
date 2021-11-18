package ru.ravel.checkAdminPcHost.Models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Computer {
    private String computerName;
    private String lastLoggedUser;
    private LocalDateTime lastLogTime;
}
