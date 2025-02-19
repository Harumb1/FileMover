import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyFrame extends JFrame {
    public MyFrame() {
        // Set up the frame
        setTitle("Movable!");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JFileChooser from = new JFileChooser();
        JFileChooser to = new JFileChooser();
        to.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Create text fields
        JTextField textField1 = new JTextField(20);
        JTextField textField2 = new JTextField(20);

        // Create buttons
        JButton from1 = new JButton("From");
        JButton to1 = new JButton("To");
        JButton move = new JButton("Move");

        textField1.setBounds(70, 100, 190, 30);
        from1.setBounds(270, 100, 70, 30);
        textField2.setBounds(70, 200, 190, 30);
        to1.setBounds(270, 200, 70, 30);
        move.setBounds(150, 270, 70,30);

        // Add action listeners
       from1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = from.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(from.getSelectedFile().getAbsolutePath());
                    textField1.setText(String.valueOf(file));

                }
            }
        });

       to1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = to.showSaveDialog(null);
                if(response == JFileChooser.APPROVE_OPTION){
                    File file = new File(to.getSelectedFile().getAbsolutePath());
                    textField2.setText(String.valueOf(file));
                }
            }
        });

        // Add components to the frame
        add(textField1);
        add(from1);
        add(textField2);
        add(to1);
        add(move);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyFrame frame = new MyFrame();
            frame.setVisible(true);
        });
    }
}
