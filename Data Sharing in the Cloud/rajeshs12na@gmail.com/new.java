import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

 class StampedeMonitor {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame f = new JFrame("MSM");
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel gui = new JPanel(new BorderLayout(5,5));
                gui.setBorder(new EmptyBorder(10,10,10,10));

                JPanel logos = new JPanel(new BorderLayout(5,5));
                gui.add(logos, BorderLayout.NORTH);
                JPanel icon = new JPanel();
                // this should be set by the image
                icon.setPreferredSize(new Dimension(50,50));
                icon.setBackground(Color.RED);
                icon.setOpaque(true);
                logos.add(icon, BorderLayout.WEST);
                logos.add(new JLabel("MNOC Stampede Monitor", JLabel.CENTER));

                JPanel controls = new JPanel(new BorderLayout(5,5));
                gui.add(controls, BorderLayout.CENTER);
                JPanel buttons = new JPanel(new GridLayout(1,0,5,5));
                controls.add(buttons, BorderLayout.NORTH);
                for (int ii=1; ii<8; ii++) {
                    JButton button = new JButton("SPD " + ii);
                    Dimension d = button.getPreferredSize();
                    // make the buttons twice as tall as default
                    Dimension big = new Dimension(
                            (int)d.getWidth(),
                            (int)d.getHeight()*2);
                    button.setPreferredSize(big);
                    buttons.add(button);
                }
                controls.add(new JScrollPane( new JTextArea(5,10) ));

                f.setContentPane(gui);
                f.pack();
                f.setVisible(true);
            }
        });
    }
}
