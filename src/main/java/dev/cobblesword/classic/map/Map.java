package dev.cobblesword.classic.map;

import dev.cobblesword.classic.Assets;
import dev.cobblesword.classic.KingdomGame;
import lombok.Getter;

import java.util.Random;

public class Map
{
    @Getter
    private int width, height;
    private TileMap backgroundLayer;
    private TileMap objectLayer;
    private int[][] metaData;
    private TileMap foregroundLayer;

    public Map(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.metaData = new int[width][height];
        this.backgroundLayer = new TileMap(width, height);
        this.objectLayer = new TileMap(width, height);
        this.foregroundLayer = new TileMap(width, height);

        setTile(5, 5, Tile.TREE_BASE);
        setTile(6, 5, Tile.TREE_BASE);
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

//        for (int i = 0; i < this.width; i++)
//        {
//            for (int j = 0; j < this.height; j++)
//            {
//                Tile tile = this.foregroundLayer.getTile(i, j);
//
//            }
//        }
    }

    public void renderMore(KingdomGame engine)
    {
        Random random = new Random();
        for (int i = 0; i < this.width; i++)
        {
            for (int j = 0; j < this.height; j++)
            {
                Tile tile = this.objectLayer.getTile(i, j);
                int mask = this.metaData[i][j];
                if(tile == null) continue;
                if(tile.equals(Tile.TREE_BASE))
                {
                    int offsetX = 0;
                    int offsetY = 0;
                    if(mask == 1)
                    {
                        offsetX = random.nextInt(10) - 5;
                        offsetY = random.nextInt(10) - 5;
                    }
                    engine.DrawSprite((16 * 4) + offsetX, ((16 * 5) - 48) + offsetY,  Assets.getInstance().tree1);
                }
            }
        }
    }

    public void setData(int x, int y, int meta)
    {
        this.metaData[x][y] = meta;
    }

    public void setTile(int x, int y, Tile tile)
    {
        if(tile == null || Tile.ROCK.equals(tile) || Tile.TREE_BASE.equals(tile))
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
