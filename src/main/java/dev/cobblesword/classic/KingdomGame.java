package dev.cobblesword.classic;

import dev.cobblesword.classic.entity.Player;
import dev.cobblesword.classic.map.Map;
import dev.cobblesword.classic.map.TileMap;
import dev.cobblesword.classic.map.Tile;
import dev.cobblesword.classic.pathfind.Node;
import dev.cobblesword.classic.pathfind.PathFind;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

public class KingdomGame extends PixelGameEngine
{
    private Player player = new Player();

    private float speed = 0.1f;

    private int currentNodeIndex = 0;

    private long lastMovement = 0;

    private Map map;

    public KingdomGame(int width, int height) {
        super("Kingdom", width, height, 2, 2);
    }

    @Override
    public boolean OnUserCreate()
    {
        player.setX(8);
        player.setX(5);

        Assets.getInstance().loadAssets(this);

        this.map = new Map(50, 50);
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                this.map.setTile(i, j, Tile.GRASS);
            }
        }

        this.map.setTile(13, 11, Tile.ROCK);
        this.map.setTile(13, 10, Tile.ROCK);
        this.map.setTile(12, 10, Tile.ROCK);
        this.map.setTile(11, 10, Tile.ROCK);
        this.map.setTile(10, 10, Tile.ROCK);
        this.map.setTile(10, 11, Tile.ROCK);
        this.map.setTile(10, 12, Tile.ROCK);
        this.map.setTile(10, 13, Tile.ROCK);
        this.map.setTile(11, 13, Tile.ROCK);
        this.map.setTile(12, 13, Tile.ROCK);
        this.map.setTile(13, 13, Tile.ROCK);

        return true;
    }

    @Override
    public boolean OnUserUpdate(float fElapsedTime)
    {
        if(GetKey(KeyEvent.VK_W))
        {
            player.setY(player.getY() - 0.2);
        }

        if(GetKey(KeyEvent.VK_S))
        {
            player.setY(player.getY() + 0.2);
        }

        if(GetKey(KeyEvent.VK_A))
        {
            player.setX(player.getX() - 0.2);
        }

        if(GetKey(KeyEvent.VK_D))
        {
            player.setX(player.getX() + 0.2);
        }

        if(GetMouse(1))
        {
            PathFind pathFind = new PathFind();
            pathFind.setCurrent((int)player.getX(), (int)player.getY());
            int gridX = MouseX() / 16;
            int gridY = MouseY() / 16;
            pathFind.setGoal(gridX, gridY);
            pathFind.updatePath(map);
            player.setPathFind(pathFind);
            currentNodeIndex = 0;
        }

        Random random = new Random(104);
        FillRect(0, 0, frame.getWidth(), frame.getHeight(), Color.white);
        this.map.render(this);

        DrawSprite((int) (player.getX() * 16) - 8, (int) (player.getY() * 16) - 8, Assets.getInstance().player1);

        this.map.renderMore(this);

        int gridX = MouseX() / 16;
        int gridY = MouseY() / 16;

        DrawSprite(gridX * 16, gridY * 16,  Assets.getInstance().selector1);

        if(player.getPathFind() != null)
        {
            player.getPathFind().setCurrent((int)player.getX(), (int)player.getY());
            List<Node> path = player.getPathFind().getPath();
            if(path != null && path.size() > 0) {
                for (int i = 0; i < path.size(); i++) {
                    Node node = path.get(i);

//                    for (int i1 = 0; i1 < node.neighbors.size(); i1++) {
//                        Node.Edge edge = node.neighbors.get(i1);
////                    DrawLine(node.getX() * 16 + 8, node.getY() * 16 + 8, edge.node.getX() * 16 + 8, edge.node.getY() * 16 + 8, Color.white);
//                    }

                    if (i > 0) {
                        Node lastNode = path.get(i - 1);
//                    DrawLine(node.getX() * 16 + 8, node.getY() * 16 + 8, lastNode.getX() * 16 + 8, lastNode.getY() * 16 + 8, Color.blue);
//                    DrawSprite(lastNode.getX() * 16, lastNode.getY() * 16, Assets.getInstance().selector2);
                    }

//                  DrawSprite(node.getX() * 16, node.getY() * 16, Assets.getInstance().selector2);
                }

                if (currentNodeIndex < path.size())
                {
                    Node target = path.get(currentNodeIndex);

                    float dx = (float) (target.getX() - player.getX()) + 0.5f;
                    float dy = (float) (target.getY() - player.getY()) + 0.5f;

                    float distance = (float) Math.sqrt(dx * dx + dy * dy);

                    if (distance < speed * fElapsedTime) {
                        player.setX(target.getX() + 0.5f);
                        player.setY(target.getY() + 0.5f);
                        currentNodeIndex++;
                    } else {
                        // Normalize direction and move toward the target
                        float step = speed * fElapsedTime / distance;
                        player.setX(player.getX() + dx * step);
                        player.setY(player.getY() + dy * step);
                    }
                }
            }
        }

        player.setX(player.getX() + player.getVelocityX());
        player.setY(player.getY() + player.getVelocityY());

        return true;
    }

    public static void main(String[] args) {
        KingdomGame ex = new KingdomGame(800,600);
        ex.start();
    }
}