import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MyFrame extends JFrame {
    public MyFrame() {
        // Set up the frame
        setTitle("JFrame Example");
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
        JButton button1 = new JButton("From");
        JButton button2 = new JButton("To");

        textField1.setBounds(80, 100, 150, 30);
        button1.setBounds(240, 100, 63, 30);
        textField2.setBounds(80, 200, 150, 30);
        button2.setBounds(240, 200, 63, 30);

        // Add action listeners
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = from.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(from.getSelectedFile().getAbsolutePath());
                    textField1.setText(String.valueOf(file));

                }
            }
        });

        button2.addActionListener(new ActionListener() {
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
        add(button1);
        add(textField2);
        add(button2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyFrame frame = new MyFrame();
            frame.setVisible(true);
        });
    }
}