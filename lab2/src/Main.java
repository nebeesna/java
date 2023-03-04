import javax.swing.*;
import java.awt.*;
//    //Варіант 9
//    //строфоїди: y2 = x2((a+x)/(a-x)), a = 3
public class Main {
        public static void main(String[] args) {
            MyFrame frame = new MyFrame();

            int width = 500;
            int height = 500;
            frame.setSize(width,height);
            frame.setTitle("Strophoid");
            frame.setVisible(true);
            Label b = new Label("Dovha Anastasiia PMI-32 Variant9");
            b.setBounds(14, 14, 200, 20);
            frame.add(b);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            int a = 30;
            Strophoid s = new Strophoid(a);
            frame.add(s);

        }
    }



