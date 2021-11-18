package ru.ravel.checkAdminPcHost.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestComputer {
    public String lastLoggedUser;
    public String key;
}
