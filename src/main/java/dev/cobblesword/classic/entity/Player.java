package dev.cobblesword.classic.entity;

import dev.cobblesword.classic.pathfind.PathFind;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Player
{
    @Getter @Setter
    private double x, y;
    @Getter @Setter
    private double velocityX, velocityY;
    @Getter @Setter
    private PathFind pathFind;
    private boolean goingToCutTree = false;
    private boolean cuttingTree = false;
    private long startedCutting = -1L;
}
