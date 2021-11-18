package ru.ravel.checkAdminPcHost.Models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Computer {
    public String computerName;
    public String lastLoggedUser;
    public LocalDateTime lastLogTime;
}
