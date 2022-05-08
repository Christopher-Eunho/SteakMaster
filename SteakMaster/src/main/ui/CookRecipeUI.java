package ui;

import model.Recipe;
import model.Steak;
import model.Step;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents a window for user showing recipe details
public class CookRecipeUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    private JPanel topPanel;
    private JPanel stepsPanel;


    private Recipe recipe;

    private String path;

    CookRecipeUI(Recipe recipe) {
        this.recipe = recipe;
        this.path = recipe.getPicturePath();

        setLayout(new BorderLayout());
        addTopPanel();
        addStepsPanel();
        add(getIconLabel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS: adds top panel with title and summary to main frame
    private void addTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(WIDTH,100));

        JPanel titleSummaryWrapper = new JPanel();
        titleSummaryWrapper.setSize(new Dimension(WIDTH,100));
        titleSummaryWrapper.setLayout(new GridLayout(1,2));

        JLabel title = new JLabel();


        title.setText(recipe.getName());
        title.setFont(new Font("Ink Free",Font.BOLD,50));
        title.setHorizontalAlignment(JLabel.RIGHT);

        JTextArea summary = new JTextArea(getSummaryString());
        summary.setBackground(null);
        summary.setSize(200, 100);
        summary.setWrapStyleWord(true);
        summary.setLineWrap(true);
        summary.setEditable(false);


        titleSummaryWrapper.add(title);
        titleSummaryWrapper.add(summary);

        topPanel.add(titleSummaryWrapper);
        add(topPanel, BorderLayout.NORTH);
    }


    // EFFECTS: returns a label with image at this.path
    private JLabel getIconLabel() {
        JLabel iconLabel = new JLabel();
        ImageIcon recipeIcon = new ImageIcon(path);
        Image recipeImage = recipeIcon.getImage();
        Image newRecipeImage = recipeImage.getScaledInstance(200, 200,  Image.SCALE_SMOOTH);
        ImageIcon newRecipeIcon = new ImageIcon(newRecipeImage);
        iconLabel.setIcon(newRecipeIcon);
        iconLabel.setHorizontalAlignment(JLabel.CENTER);
        iconLabel.setVerticalAlignment(JLabel.CENTER);
        iconLabel.setBorder(new CompoundBorder(iconLabel.getBorder(),
                new EmptyBorder(0,0,200,0)));
        return iconLabel;
    }

    // MODIFIES: this
    // EFFECTS: adds steps panel with inputs to main frame
    private void addStepsPanel() {
        stepsPanel = new JPanel();
        stepsPanel.setLayout(new GridLayout(10,1));
        stepsPanel.setPreferredSize(new Dimension(WIDTH,700));

        int i = 1;
        for (Step step: recipe.getStepList()) {
            JPanel stepPanel = getStepPanel(step, i);
            stepsPanel.add(stepPanel);
            i++;
        }

        add(stepsPanel, BorderLayout.CENTER);
    }

    // REQUIRES: index > 0
    // EFFECTS: creates a step panel using data in the given step object
    private JPanel getStepPanel(Step step, int index) {
        JPanel stepPanel = new JPanel();
        stepPanel.setLayout(new BorderLayout());
        JLabel stepTitle = new JLabel("Step " + index + ". " + step.getName()
                + " ( " + step.getTimeInMinSec() + " )");
        stepTitle.setFont(new Font("SansSerif", Font.BOLD, 15));

        JLabel actionLabel = new JLabel("\t\t" + step.getAction());
        actionLabel.setBackground(null);
        actionLabel.setSize(WIDTH - 100, 50);
        actionLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        actionLabel.setBorder(new CompoundBorder(actionLabel.getBorder(),
                new EmptyBorder(0,15,0,0)));

        stepPanel.add(stepTitle, BorderLayout.NORTH);
        stepPanel.add(actionLabel,  BorderLayout.CENTER);

        stepPanel.setBorder(new CompoundBorder(stepPanel.getBorder(),
                new EmptyBorder(0, 10, 0, 0)));

        return stepPanel;
    }



    // EFFECTS: creates summary of the recipe in string
    private String getSummaryString() {
        Steak steak = recipe.getSteak();
        String summary = "";
        summary += "\n\t •Cut: " + steak.getCut();
        summary += "\n\t •Thickness: " + steak.getThickness() + "mm";
        summary += "\n\t •Grams: " + steak.getGrams() + "g";
        summary += "\n\t •Doneness: " + recipe.getDoneness();
        summary += "\n\t •Cooking Time: " + recipe.getTimeInMinSec() + "\n";

        return summary;
    }


}


