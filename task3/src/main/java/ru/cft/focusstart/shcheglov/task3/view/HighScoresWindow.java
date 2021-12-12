package ru.cft.focusstart.shcheglov.task3.view;

import ru.cft.focusstart.shcheglov.task3.enums.GameDifficult;
import ru.cft.focusstart.shcheglov.task3.utils.Constants;

import javax.swing.*;
import java.awt.*;

public class HighScoresWindow extends JDialog {
    public static final String[] DEFAULT_RECORD_TEXT = {"No record", "00:00:00"};

    private final JLabel noviceRecordLabel;
    private final JLabel mediumRecordLabel;
    private final JLabel expertRecordLabel;

    public HighScoresWindow(JFrame owner) {
        super(owner, Constants.HIGH_SCORES_TITLE, true);

        GridBagLayout layout = new GridBagLayout();
        Container contentPane = getContentPane();
        contentPane.setLayout(layout);

        int gridY = 0;

        contentPane.add(createLabel(GameDifficult.NOVICE.getName() + ":", layout, gridY++, 0));
        contentPane.add(noviceRecordLabel = createLabel(createRecordText(DEFAULT_RECORD_TEXT), layout, gridY++, 0));

        contentPane.add(createLabel(GameDifficult.MEDIUM.getName() + ":", layout, gridY++, 10));
        contentPane.add(mediumRecordLabel = createLabel(createRecordText(DEFAULT_RECORD_TEXT), layout, gridY++, 0));

        contentPane.add(createLabel(GameDifficult.EXPERT.getName() + ":", layout, gridY++, 10));
        contentPane.add(expertRecordLabel = createLabel(createRecordText(DEFAULT_RECORD_TEXT), layout, gridY++, 0));

        contentPane.add(createCloseButton(layout));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(200, 200));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public void setRecord(GameDifficult difficult, String[] data) {
        String[] message = data;

        if (data.length == 0) {
            message = DEFAULT_RECORD_TEXT;
        }

        switch (difficult) {
            case NOVICE -> noviceRecordLabel.setText(createRecordText(message));
            case MEDIUM -> mediumRecordLabel.setText(createRecordText(message));
            case EXPERT -> expertRecordLabel.setText(createRecordText(message));
        }
    }

    public String createRecordText(String[] message) {
        return String.format("%s - %s", message[0], message[1]);
    }

    private JLabel createLabel(String labelText, GridBagLayout layout, int gridY, int margin) {
        JLabel label = new JLabel(labelText);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = gridY;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(margin, 0, 0, 0);
        layout.setConstraints(label, gbc);

        return label;
    }

    private JButton createCloseButton(GridBagLayout layout) {
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dispose());
        okButton.setPreferredSize(new Dimension(60, 25));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        layout.setConstraints(okButton, gbc);

        return okButton;
    }
}
