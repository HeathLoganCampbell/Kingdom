package dev.cobblesword.classic;

public class Assets
{
    private static Assets instance;
    public static Assets getInstance()
    {
        if(instance == null)
        {
            instance = new Assets();
        }

        return instance;
    }

    private PixelGameEngine.Sprite tileSet;
    private PixelGameEngine.Sprite treeTileSet;
    private PixelGameEngine.Sprite playerTileSet;
    private PixelGameEngine.Sprite SelectorTileSet;

    public PixelGameEngine.Sprite tileGrass1;
    public PixelGameEngine.Sprite tileGrass2;
    public PixelGameEngine.Sprite tileRock;
    public PixelGameEngine.Sprite tree1;
    public PixelGameEngine.Sprite player1;
    public PixelGameEngine.Sprite selector1;
    public PixelGameEngine.Sprite selector2;

    public void loadAssets(PixelGameEngine engine)
    {
        this.tileSet = PixelGameEngine.Sprite.load("LandTileSet.png");
        this.treeTileSet = PixelGameEngine.Sprite.load("Trees.png");
        this.playerTileSet = PixelGameEngine.Sprite.load("PlayerTileSet.png");
        this.SelectorTileSet = PixelGameEngine.Sprite.load("SelectorTileSet.png");

        this.tileGrass1 = engine.GetPartialSprite(0, 0, 16, 16, this.tileSet);
        this.tileGrass2 = engine.GetPartialSprite(16, 0, 16, 16, this.tileSet);
        this.tileRock = engine.GetPartialSprite(32, 0, 16, 16, this.tileSet);
        this.tree1 = engine.GetPartialSprite(0, 0, 64, 64, this.treeTileSet);
        this.player1 = engine.GetPartialSprite(0, 0, 16, 16, this.playerTileSet);
        this.selector1 = engine.GetPartialSprite(0, 0, 16, 16, this.SelectorTileSet);
        this.selector2 = engine.GetPartialSprite(16, 0, 16, 16, this.SelectorTileSet);
    }
}
