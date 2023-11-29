import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main extends JFrame {

    private List<Point> points;

    public Main(List<Point> points) {
        this.points = points;
        setTitle("Dataset Visualization");
        setSize(960, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Point point : points) {
            g.setColor(Color.BLUE);
            g.fillOval(point.x - 3, point.y - 3, 6, 6);
        }
    }

    public static List<Point> readDataset(String filePath) {
        List<Point> points = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] coordinates = line.trim().split("\\s+");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                points.add(new Point(x, y));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }

    public static void main(String[] args) {
        String filePath = "DS2.txt"; // шлях до файлу даних
        List<Point> points = readDataset(filePath);

        SwingUtilities.invokeLater(() -> {
            new Main(points);
        });
    }
}