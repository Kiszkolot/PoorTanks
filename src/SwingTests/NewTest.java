package SwingTests;
import java.awt.EventQueue;

public class NewTest {
        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new ActionFrame();
                }
            });
        }
    }
