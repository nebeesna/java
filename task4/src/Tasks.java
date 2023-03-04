import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Dimension;
import javax.swing.*;


public class Tasks {
    //1.Перший з потоків виконує анімацію графічного зображення
    public static class Task1 extends Frame implements Runnable {
        private Color lineColor = Color.green;
        private float thickness = 1;
        private Timer timer;
        private int a = 3;
        private int pause = 50;
        private int points = 500;
        private int now = 1;
        private static final Random random = new Random();
        private static int cap = 1;
        private static final float[] dashes = {random.nextFloat(), random.nextFloat()};
        private static boolean dash = false;

        public Task1() {
            Button startButton = new Button("Start");
            startButton.setBounds(100, 35, 50, 30);
            startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    timer.start();
                }
            });
            add(startButton);

            Button stopButton = new Button("Stop");
            stopButton.setBounds(150, 35, 50, 30);
            stopButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    timer.stop();
                }
            });
            add(stopButton);

            Label currentPeriod = new Label(Integer.toString(pause));
            currentPeriod.setBounds(215, 35, 50, 30);
            add(currentPeriod);

            Button fasterButton = new Button("+");
            fasterButton.setBounds(265, 35, 15, 30);
            fasterButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (pause != 300) {
                        pause += 10;
                        timer.setDelay(pause);
                        currentPeriod.setText(Integer.toString(pause));
                    }
                }
            });
            add(fasterButton);

            Button slowerButton = new Button("-");
            slowerButton.setBounds(200, 35, 15, 30);
            slowerButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (pause != 10) {
                        pause -= 10;
                        timer.setDelay(pause);
                        currentPeriod.setText(Integer.toString(pause));
                    }
                }
            });
            add(slowerButton);

            Label currentA = new Label(Integer.toString(a));
            currentA.setBounds(320, 35, 20, 30);
            add(currentA);

            Button increaseA = new Button("a++");
            increaseA.setBounds(350, 35, 20, 30);
            increaseA.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (a != 100) {
                        a += 1;
                        currentA.setText(Integer.toString(a));
                    }
                }
            });
            add(increaseA);

            Button decreaseA = new Button("a--");
            decreaseA.setBounds(300, 35, 20, 30);
            decreaseA.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (a != 3) {
                        a -= 1;
                        currentA.setText(Integer.toString(a));
                    }
                }
            });
            add(decreaseA);

            setSize(500, 500);
            setTitle("Strophoid");
            setLayout(null);


            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    timer.stop();
                    dispose();
                }
            });
            addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent componentEvent) {
                    repaint();
                }
            });

            addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    thickness = random.nextFloat() * 20;
                    lineColor = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
                    cap = random.nextInt(3);
                    if (random.nextFloat() > 0.5) {
                        dashes[0] = random.nextFloat() * 10;
                        dashes[1] = random.nextFloat() * 10;
                        dash = true;
                    } else dash = false;
                    repaint();
                }
            });
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            RenderingHints rh = new RenderingHints(
                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
            );
            g2d.setRenderingHints(rh);

            g2d.drawLine(0, this.getSize().height / 2, this.getSize().width, this.getSize().height / 2);
            g2d.drawLine(this.getSize().width / 2, 0, this.getSize().width / 2, this.getSize().height);

            g2d.translate(this.getSize().width / 2, this.getSize().height / 2);

            g2d.setColor(this.lineColor);

            if (!dash)
                g2d.setStroke(new BasicStroke(thickness, cap, 0));
            else
                g2d.setStroke(new BasicStroke(thickness, cap, 0, 10.0f, dashes, 0));

            int a_scale = a * (this.getSize().width / 100);
            int x1 = -a_scale;
            int y1 = (int) Math.sqrt(Math.pow(x1, 2) * (a_scale + x1) / (a_scale - x1));
            int x2 = -a_scale + 1;
            int y2 = (int) Math.sqrt(Math.pow(x1, 2) * (a_scale + x2) / (a_scale - x2));
            for (int i = 0; i < now; i++) {
                if(y1 <= this.getSize().height){
                    g2d.drawLine(x1, y1, x2, y2);
                    g2d.drawLine(x1, -y1, x2, -y2);
                    x1 = x2;
                    x2 = x1 + 1;
                    y1 = y2;
                    y2 = (int) Math.sqrt(Math.pow(x2, 2) * (a_scale + x2) / (a_scale - x2));
                }
                else {
                    now = 1;
                }

            }
        }

        @Override
        public void run() {
            timer = new Timer(pause, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (points == now) {
                        now = 1;
                    } else {
                        now++;
                    }
                    repaint();
                }
            });
            timer.start();
            setVisible(true);


        }
    }
    //2.Другий потік виконує складні обчислення і виводить покроково результати;
    public static class Task2 extends Frame implements Runnable{
        private boolean isCalc = true;
        private int pause = 2000;
        private String data = new String();
        private Label label = new Label(data);
        private int count = 0;
        public Task2() {
            setTitle("Calcs");
            setLayout(new BorderLayout());
            setMinimumSize(new Dimension(400, 400));

            Button startButton = new Button("Start");
            startButton.setBounds(50, 35, 50, 30);
            startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    isCalc = true;
                }
            });
            add(startButton);

            Button stopButton = new Button("Stop");
            startButton.setBounds(100, 35, 50, 30);
            stopButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    isCalc = false;
                }
            });
            add(stopButton);

            Label currentPause = new Label(Integer.toString(pause));
            currentPause.setBounds(225, 35, 50, 30);
            add(currentPause);

            Button fasterButton = new Button(">>");
            fasterButton.setBounds(275, 35, 25, 30);
            fasterButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (pause != 3000)
                    {
                        pause += 100;
                        currentPause.setText(Integer.toString(pause));
                    }
                }
            });
            add(fasterButton);

            Button slowerButton = new Button("<<");
            slowerButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (pause != 1000)
                    {
                        pause -= 100;
                        currentPause.setText(Integer.toString(pause));
                    }
                }
            });
            slowerButton.setBounds(200, 35, 25, 30);
            add(slowerButton);

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    dispose();
                }
            });

            add(label);
        }

        @Override
        public void run() {

            setVisible(true);
            try {
                while(true)
                {
                    if (isCalc)
                    {
                        count+=1;
                        data += "√" + count + "= " + Math.sqrt(count) + "\n\t";
                        label.setText(data);
                        revalidate();
                    }
                    Thread.sleep(pause);
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //3.Третій потік відображає плаваючий текст (біжучий рядок).
    public static class Task3 extends Frame implements Runnable{
        private Label text;
        private boolean isMoved = true;
        private int x = 0;

        public Task3() {
            Button startButton = new Button("Start");
            startButton.setBounds(100, 35, 50, 30);
            add(startButton);

            startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    isMoved = true;
                }
            });

            Button stopButton = new Button("Stop");
            stopButton.setBounds(160, 35, 50, 30);
            add(stopButton);

            stopButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    isMoved = false;
                }
            });

            text = new Label("3.Третій потік відображає плаваючий текст (біжучий рядок). 3.Третій потік відображає плаваючий текст (біжучий рядок).");
            text.setFont(new Font("Arial", Font.PLAIN, 20));
            text.setSize(555, 50);
            text.setLocation(x, 70);
            add(text);

            setSize(500, 150);
            setResizable(false);
            setTitle("floating text");
            setLayout(null);

            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    dispose();
                }
            });
        }
        @Override
        public void run() {
            setVisible(true);
            try {
                while(true)
                {
                    synchronized (text) {
                        text.wait(10);
                        if (isMoved) {
                            if (x < -500)
                                x = 500;
                            else {
                                x -= 1;
                            }
                            text.setLocation(x, 70);
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
