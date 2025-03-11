import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int mazeSize = 10;
        int windowSize=640;
        int specialBlocksCount = 4;
        SwingUtilities.invokeLater(()->new MyFrame(mazeSize,windowSize,specialBlocksCount));
    }
}