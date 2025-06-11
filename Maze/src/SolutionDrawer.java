import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SolutionDrawer extends JPanel {

    ArrayList<ArrayList<Block>> pathsToDraw=new ArrayList<>();
    public SolutionDrawer(){
        setBounds(0,0,MyFrame.getWindowSize(),MyFrame.getWindowSize());
        setOpaque(false);
        ArrayList<Block> shortestPath = getShortestPathWithSpecialBlocks();
        for(int i=0;i<shortestPath.size()-1;i++)
            pathsToDraw.add(shortestPath.get(i).getPath(shortestPath.get(i+1)));
    }
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.BLUE);
            int move = 0;
            for(ArrayList<Block> path : pathsToDraw){
                for(int i=0;i<path.size()-1;i++){
                    Block bOne = path.get(i);
                    Block bTwo = path.get(i+1);
                    int halfBlockSize = MyFrame.getBlockSize()/2;
                    g2d.drawLine(bOne.getX()+halfBlockSize+move, bOne.getY()+halfBlockSize,
                            bTwo.getX()+halfBlockSize+move, bTwo.getY()+halfBlockSize);
                }
            }
        }
    public ArrayList<ArrayList<Block>> permute(ArrayList<Block> blocks) { //heap iteracyjny
        ArrayList<ArrayList<Block>> result = new ArrayList<>();
        int[] indexes = new int[blocks.size()];
        result.add(new ArrayList<>(blocks));
        int i = 0;
        while (i < blocks.size()) {
            if (indexes[i] < i) {
                if (i % 2 == 0)
                    Collections.swap(blocks, 0, i);
                else
                    Collections.swap(blocks, indexes[i], i);
                result.add(new ArrayList<>(blocks));
                indexes[i]++;
                i = 0;
            } else {
                indexes[i] = 0;
                i++;
            }
        }
        return result;
    }


    public ArrayList<Block> getShortestPathWithSpecialBlocks(){
        ArrayList<ArrayList<Block>> permutationsList = permute(Block.getSpecialBlocks());
        HashMap<ArrayList<Block>,Integer> lengthMap = new HashMap<>();
        for(ArrayList<Block> list : permutationsList){
            list.add(Block.getBlock(MyFrame.getMazeSize()-1,MyFrame.getMazeSize()-1));
            list.add(0,Block.getBlock(0,0));
            int length = 0;
            for(int i=0;i<list.size()-1;i++)
                length+=list.get(i).getPath(list.get(i+1)).size();
            lengthMap.put(list,length);
        }
        ArrayList<Block> resultList = permutationsList.get(0);
        for(int i=0;i<permutationsList.size();i++)
            if(lengthMap.get(permutationsList.get(0))<lengthMap.get(permutationsList.get(i)))
                resultList = permutationsList.get(i);
        return resultList;
    }

}



