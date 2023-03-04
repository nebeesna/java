import javax.swing.*;
import java.awt.*;
import java.util.Random;

/*
Створити застосування для відображення графіка функції.
Графік відображати в центрі вікна, чорним кольором відобразити осі координат (5 балів).
При зміні розміру вікна масштабувати графік, при цьому колір ліній, стиль і товщина не змінюються (2 бали).
У лівому куті вікна вивести прізвище автора і № варіанта(1 бал).
При кліку мишкою випадковим чином змінювати колір, стиль і товщину ліній графіка,
осі координат залишаються чорними (2 бали).
*/
public class Strophoid extends JComponent{
    public int a;
    private static final Random random = new Random();
    private static Color lineColor = Color.blue;
    private static float thickness = 1;
    private static int cap = 1;
    private static final float[] dashes = {random.nextFloat(), random.nextFloat()};
    private static boolean dash = false;
    public Strophoid(int param){
        this.a = param;
    }
    public static void randomChanges(){
        thickness = random.nextFloat() * 20;
        lineColor = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
        cap = random.nextInt(3);
        if (random.nextFloat() > 0.5)
        {
            dashes[0]= random.nextFloat() * 10;
            dashes[1]= random.nextFloat() * 10;
            dash = true;
        }
        else dash = false;

    }
    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2d.setRenderingHints(rh);

        g2d.drawLine(0, this.getSize().height / 2, this.getSize().width, this.getSize().height / 2);
        g2d.drawLine(this.getSize().width/ 2, 0, this.getSize().width / 2, this.getSize().height);

        g2d.translate(this.getSize().width / 2, this.getSize().height / 2);

        g2d.setColor(this.lineColor);

        if(!dash)
            g2d.setStroke(new BasicStroke(thickness, cap, 0));
        else
            g2d.setStroke(new BasicStroke(thickness, cap, 0, 10.0f, dashes, 0));

        int a_scale = a * (this.getSize().width / 100);
        int x1 = -a_scale;
        int y1 = (int)Math.sqrt(Math.pow(x1, 2)*(a_scale+x1)/(a_scale-x1));
        int x2 = -a_scale + 1;
        int y2 = (int)Math.sqrt(Math.pow(x1, 2)*(a_scale+x2)/(a_scale-x2));
        do{
            g2d.drawLine(x1, y1, x2, y2);
            g2d.drawLine(x1, -y1, x2, -y2);
            x1 = x2;
            x2 = x1+1;
            y1 = y2;
            y2 = (int)Math.sqrt(Math.pow(x2, 2)*(a_scale+x2)/(a_scale-x2));

        } while (y1 <= this.getSize().height);
    }
}
