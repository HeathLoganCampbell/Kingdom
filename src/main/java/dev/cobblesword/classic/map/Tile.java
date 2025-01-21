package dev.cobblesword.classic.map;

public class Tile
{
    public static Tile GRASS = new Tile(1, 0, "Grass");

    public static Tile ROCK = new Tile(2, 1, "Rock");

    private int id;

    private int layer;

    private String name;

    public Tile(int id, int layer, String name)
    {
        this.id = id;
        this.layer = layer;
        this.name = name;
    }
}
