import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class MyFrame extends JFrame {
    private static int mazeSize;
    private static int windowSize;
    public MyFrame(int mazeSize,int windowSize,int specialBlocksCount)
    {
        MyFrame.mazeSize =mazeSize;
        MyFrame.windowSize = windowSize;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Maze generator");
        getContentPane().setBackground(Color.YELLOW);
        getContentPane().setPreferredSize(new Dimension(getBlockSize()*mazeSize, getBlockSize()*mazeSize));
        pack();
        setLayout(null);
        setVisible(true);
        for(int i=0;i<mazeSize;i++)
            for(int j=0;j<mazeSize;j++)
               add(new Block(i, j));
        Block.makeSomeSpecial(specialBlocksCount);
        generateMaze();
        Block.drawAllBlocks(this);

        SolutionDrawer solutionDrawer = new SolutionDrawer();
        add(solutionDrawer);
        solutionDrawer.paint(getGraphics());

    }

    public static int getWindowSize(){
        return windowSize;
    }

    public static int getMazeSize(){
        return mazeSize;
    }


    public void generateMaze(){
        List<Block> stack = new ArrayList<>();
        stack.add(Block.getBlock(0,0));
        while (!stack.isEmpty()){
            Block block = stack.getLast();
            stack.removeLast();
            if(!stack.isEmpty()) block.removeWalls();
            List<Block> random = block.getNeighbours();
            Collections.shuffle(random);
            for (Block rb : random) {
                if (rb != null && !rb.getIsVisited()) {
                    stack.add(rb);
                    rb.setPreviousBlock(block);
                }
            }
            block.setIsVisited(true);
        }
    }

    public static int getBlockSize()
    {
        return windowSize/mazeSize;
    }



}
