package generalClasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExceptionMessage extends JFrame implements ActionListener{
    JButton back;
    JLabel message;

    public ExceptionMessage(String str) {
        setTitle("Сообщение");
        setSize(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel container = new JPanel();
        //container.setLayout(new GridBagLayout());
        back = new JButton("Назад");
        back.addActionListener(this);
        message = new JLabel(str);
        container.add(message, BorderLayout.CENTER);
        container.add(back, BorderLayout.SOUTH);

        this.add(container, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
