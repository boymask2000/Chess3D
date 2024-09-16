package com.posbeu.littletown.terrain.vicinato;

import com.posbeu.littletown.terrain.Zolla;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vicino {
    private Zolla zolla;
    private VicinatoDir dir;
}
