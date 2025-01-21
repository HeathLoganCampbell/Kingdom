package dev.cobblesword.classic.entity;

import dev.cobblesword.classic.pathfind.PathFind;
import lombok.Getter;
import lombok.Setter;

public class Player
{
    @Getter @Setter
    private double x, y;
    @Getter @Setter
    private double velocityX, velocityY;
    @Getter @Setter
    private PathFind pathFind;
}
