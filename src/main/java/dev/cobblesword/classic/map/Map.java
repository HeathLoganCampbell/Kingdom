package dev.cobblesword.classic.map;

import dev.cobblesword.classic.Assets;
import dev.cobblesword.classic.KingdomGame;
import lombok.Getter;

public class Map
{
    @Getter
    private int width, height;
    private TileMap backgroundLayer;
    private TileMap objectLayer;
    private TileMap foregroundLayer;

    public Map(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.backgroundLayer = new TileMap(width, height);
        this.objectLayer = new TileMap(width, height);
        this.foregroundLayer = new TileMap(width, height);
    }

    public void render(KingdomGame engine)
    {
        for (int i = 0; i < this.width; i++)
        {
            for (int j = 0; j < this.height; j++)
            {
                Tile tile = this.backgroundLayer.getTile(i, j);
                if(tile.equals(Tile.GRASS))
                {
                    engine.DrawSprite(i * 16, j * 16, Assets.getInstance().tileGrass1);
                }
            }
        }

        for (int i = 0; i < this.width; i++)
        {
            for (int j = 0; j < this.height; j++)
            {
                Tile tile = this.objectLayer.getTile(i, j);
                if(tile == null) continue;

                if(tile.equals(Tile.ROCK))
                {
                    engine.DrawSprite(i * 16, j * 16, Assets.getInstance().tileRock);
                }
//                engine.DrawSprite(i * 32, j * 32, Assets.getInstance().tree1);
            }
        }

        for (int i = 0; i < this.width; i++)
        {
            for (int j = 0; j < this.height; j++)
            {
                Tile tile = this.foregroundLayer.getTile(i, j);

            }
        }

        engine.DrawSprite(100, 100,  Assets.getInstance().tree1);
    }

    public void setTile(int x, int y, Tile tile)
    {
        if(Tile.ROCK.equals(tile))
        {
            this.objectLayer.setTile(x, y, tile);
        }
        else
        {
            this.backgroundLayer.setTile(x, y, tile);
        }
    }

    public Tile getTile(int x, int y)
    {
        return this.objectLayer.getTile(x, y);
    }
}
