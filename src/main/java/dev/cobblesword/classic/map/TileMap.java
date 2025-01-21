package dev.cobblesword.classic.map;

public class TileMap
{
    private int width, height;
    public Tile[][] tiles;

    public TileMap(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    public void setTile(int x, int y, Tile tile)
    {
        this.tiles[x][y] = tile;
    }

    public Tile getTile(int x, int y)
    {
        return this.tiles[x][y];
    }
}
