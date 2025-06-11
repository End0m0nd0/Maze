import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Block extends JPanel {
    private static ArrayList<Block> allBlocks = new ArrayList<>();
    private static ArrayList<Block> specialBlocks = new ArrayList<>();
    private int x;
    private int y;
    private boolean isVisited=false;
    private Block previousBlock = null;

    boolean isSpecial = false;

    boolean [] walls = new boolean[4];
    public Block(int x, int y){
        this.x=x;
        this.y=y;
        allBlocks.add(this);
        for(int i=0;i<walls.length;i++)
            walls[i]=true;
        setBounds(x * MyFrame.getBlockSize(), y * MyFrame.getBlockSize(), MyFrame.getBlockSize(), MyFrame.getBlockSize());
    }

    public static Block getBlock(int x, int y){
        for(Block b: allBlocks)
            if(b.x==x&&b.y==y)
                return b;
        return null;
    }

    public boolean getIsVisited(){
        return isVisited;
    }

    public void setIsVisited(boolean isVisited){
        this.isVisited=isVisited;
    }

    public static void drawAllBlocks(MyFrame frame){
        for(Block b: allBlocks){
            b.paint(frame.getGraphics());
        }
    }

    public static ArrayList<Block> getSpecialBlocks(){
        return specialBlocks;
    }
    public Block getPreviousBlock(){
        return previousBlock;
    }
    public void setPreviousBlock(Block previousBlock){
        this.previousBlock=previousBlock;
    }

    public List<Block> getNeighbours(){
        List<Block> random = new ArrayList<>();
        Block leftBlock = Block.getBlock(x-1,y);
        Block upBlock = Block.getBlock(x,y-1);
        Block rightBlock = Block.getBlock(x+1,y);
        Block downBlock = Block.getBlock(x,y+1);
        if(leftBlock!=null) random.add(leftBlock);
        if(upBlock!=null) random.add(upBlock);
        if(rightBlock!=null) random.add(rightBlock);
        if(downBlock!=null) random.add(downBlock);
        return random;
    }

    public List<Block> getNeighboursConnected(){
        List<Block> neighbours = new ArrayList<>();
        if(!walls[0]) neighbours.add(Block.getBlock(x-1,y));
        if(!walls[1]) neighbours.add(Block.getBlock(x,y-1));
        if(!walls[2]) neighbours.add(Block.getBlock(x+1,y));
        if(!walls[3]) neighbours.add(Block.getBlock(x,y+1));
        return neighbours;
    }

    public void removeWalls(){
            Block block = previousBlock;
            if (x + 1 == block.x) {//blok po prawej
                walls[2] = false;
                block.walls[0] = false;
            } else if (x - 1 == block.x) {//blok po lewej
                walls[0] = false;
                block.walls[2] = false;
            } else if (y + 1 == block.y) {//blok na dole
                walls[3] = false;
                block.walls[1] = false;
            } else if (y - 1 == block.y) {//blok na gorze
                walls[1] = false;
                block.walls[3] = false;
            }
    }


    public static void makeSomeSpecial(int x){
        ArrayList<Integer> specialList = new ArrayList<>();
        for(int i=0;i<x;i++){
            int num = (int)(Math.random()*allBlocks.size());
            while(specialList.contains(num)||num==0||num==allBlocks.size()-1)
                num = (int)(Math.random()*allBlocks.size());
            specialList.add(num);
        }
        for(int i=0;i<specialList.size();i++) {
            allBlocks.get(specialList.get(i)).setSpecial(true);
            specialBlocks.add(allBlocks.get(specialList.get(i)));
        }
    }
    public void setSpecial(boolean isSpecial){
        this.isSpecial=isSpecial;
    }


    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;
        int size = MyFrame.getBlockSize();


        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.BLACK);

        if (walls[0]) g2d.drawLine(0, 0, 0, size);           // Lewa ściana
        if (walls[1]) g2d.drawLine(0, 0, size, 0);           // Górna ściana
        if (walls[2]) g2d.drawLine(size, 0, size, size);     // Prawa ściana
        if (walls[3]) g2d.drawLine(0, size, size, size);     // Dolna ściana

        if (isSpecial) {
            g2d.setColor(Color.RED);
            int dotSize = size / 2;
            int x = (size - dotSize) / 2;
            int y = (size - dotSize) / 2;
            g2d.fillOval(x, y, dotSize, dotSize);
        }
    }

    public String toString(){
        return "( "+x+", "+y+" )";
    }

    public ArrayList<Block> getPath(Block block){
        for(Block b : allBlocks){
            b.setPreviousBlock(null);
            b.setIsVisited(false);
        }
        List<Block> queue = new ArrayList<>();
        queue.add(this);
        while(!queue.isEmpty()){
            Block b = queue.get(0);
            queue.remove(b);
            if(!b.getIsVisited()) {
                List<Block> neighbors = b.getNeighboursConnected();
                for (Block n : neighbors) {
                    if(!n.getIsVisited()) {
                        n.setPreviousBlock(b);
                        queue.add(n);
                    }
                }
                b.setIsVisited(true);
            }
        }
        ArrayList<Block> blockPath = new ArrayList<>();
        Block previous = block;
        while (previous!=this){
            blockPath.add(previous);
            previous = previous.getPreviousBlock();
        }
        blockPath.add(this);
        return blockPath;
    }


}
