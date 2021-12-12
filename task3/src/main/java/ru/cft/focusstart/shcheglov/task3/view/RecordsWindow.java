package ru.cft.focusstart.shcheglov.task3.view;

import ru.cft.focusstart.shcheglov.task3.utils.Constants;
import ru.cft.focusstart.shcheglov.task3.view.Listeners.RecordNameListener;

import javax.swing.*;
import java.awt.*;

public class RecordsWindow extends JDialog {
    private final RecordNameListener nameListener;

    public RecordsWindow(JFrame frame, RecordNameListener listener) {
        super(frame, Constants.RECORDS_TITLE, true);
        this.nameListener = listener;

        JTextField nameField = new JTextField();

        GridLayout layout = new GridLayout(3, 1);
        Container contentPane = getContentPane();
        contentPane.setLayout(layout);

        contentPane.add(new JLabel("Enter your name:"));
        contentPane.add(nameField);
        contentPane.add(createOkButton(nameField));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(210, 120));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton createOkButton(JTextField nameField) {
        JButton button = new JButton("OK");
        button.addActionListener(e -> {
            dispose();

            if (nameListener != null) {
                nameListener.onRecordNameEntered(nameField.getText());
            }
        });
        return button;
    }
}
