package ui;

import model.Recipe;
import model.Steak;
import model.Step;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.EventListener;

// Represents a window for user to create a new recipe
public class CreateRecipeUI extends JFrame implements EventListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final String PIC_STORE = "./data/pic";

    private ArrayList<Step> steps = new ArrayList<>();
    private ArrayList<Recipe> recipeList;

    private JTextField nameField;
    private JTextField donenessField;
    private JTextField cutField;
    private JTextField thicknessField;
    private JTextField gramField;
    private JTextField stepNameField;
    private JTextField actionField;
    private JTextField timeField;


    private JPanel titlePanel;
    private JPanel recipeBasicPanel;
    private JPanel buttonsPanel;
    private JPanel stepPanel;

    private JButton addStepButton;
    private JButton createButton;
    private JButton addPicButton;

    private JLabel stepStatusLabel;

    private int stepAdded = 1;

    private SteakMasterUI steakMasterUI;
    private CreateRecipeUI frame = this;

    private String picPath;



    // MODIFIES: this, SteakMasterUI
    // EFFECTS: creates a window to create a recipe;
    //          accepts user input and creates a new recipe;
    //          adds a new recipe to existing recipe list in steakMasterUI;
    public CreateRecipeUI(SteakMasterUI steakMasterUI) {
        this.steakMasterUI = steakMasterUI;
        recipeList = (ArrayList<Recipe>) steakMasterUI.getRecipeList();
        addTitlePanel();
        addRecipeBasicPanel();
        addStepPanel();
        addButtonsPanel();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    // MODIFIES: this
    // EFFECTS: adds step input panel to the main frame
    private void addStepPanel() {
        stepPanel = new JPanel();
        stepPanel.setLayout(new GridLayout(5,1));
        JLabel stepPanelTitle = new JLabel("Step Details", SwingConstants.CENTER);
        stepStatusLabel = new JLabel("You've added 0 step(s)", SwingConstants.CENTER);

        stepPanel.add(stepPanelTitle);
        addStepNamePanel();
        addActionPanel();
        addTimePanel();

        stepPanel.add(stepStatusLabel);

        add(stepPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: adds step name input panel to step panel
    private void addStepNamePanel() {
        JLabel stepName = new JLabel("Step Name");
        JPanel stepNamePanel = new JPanel();
        stepNameField = new JTextField();
        stepNameField.setPreferredSize(new Dimension(100, 25));
        stepNamePanel.add(stepName);
        stepNamePanel.add(stepNameField);
        stepPanel.add(stepNamePanel);
    }



    // MODIFIES: this
    // EFFECTS: adds action input panel to step panel
    private void addActionPanel() {
        JLabel stepAction = new JLabel("Step Action");
        JPanel actionPanel = new JPanel();
        actionField = new JTextField();
        actionField.setPreferredSize(new Dimension(300, 25));
        actionPanel.add(stepAction);
        actionPanel.add(actionField);
        stepPanel.add(actionPanel);
    }


    // MODIFIES: this
    // EFFECTS: adds time input panel to step panel
    private void addTimePanel() {
        JLabel stepTime = new JLabel("Time(sec)");
        JPanel timePanel = new JPanel();
        timeField = new JTextField();
        timeField.setPreferredSize(new Dimension(50, 25));
        timePanel.add(stepTime);
        timePanel.add(timeField);
        stepPanel.add(timePanel);
    }


    // MODIFIES: this
    // EFFECTS: adds buttons panel input panel to main frame
    private void addButtonsPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3,1));

        addStepButton = new JButton(new AddStepAction());
        addStepButton.setPreferredSize(new Dimension(100, 10));

        addPicButton = new JButton(new AddPicAction());
        addPicButton.setPreferredSize(new Dimension(100, 10));

        createButton = new JButton(new CreateAction());
        createButton.setPreferredSize(new Dimension(100, 10));

        buttonsPanel.add(addStepButton);
        buttonsPanel.add(addPicButton);
        buttonsPanel.add(createButton);

        add(buttonsPanel, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: adds title panel input panel to main frame
    private void addTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(WIDTH,100));
        JLabel title = new JLabel();
        title.setText("Create Recipe");
        title.setFont(new Font("Ink Free",Font.BOLD,50));
        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: adds recipe basic panel input panel to main frame
    private void addRecipeBasicPanel() {
        recipeBasicPanel = new JPanel();
        recipeBasicPanel.setLayout(new GridLayout(5,1));


        addNamePanel();
        addCutPanel();
        addThicknessPanel();
        addGramsPanel();
        addDonenessPanel();
        add(recipeBasicPanel, BorderLayout.WEST);

    }


    // MODIFIES: this
    // EFFECTS: adds doneness panel input panel to recipe basic panel
    private void addDonenessPanel() {
        JLabel doneness = new JLabel("Doneness");
        JPanel donenessPanel = new JPanel();
        donenessField = new JTextField();
        donenessField.setPreferredSize(new Dimension(100, 25));
        donenessPanel.add(doneness);
        donenessPanel.add(donenessField);
        recipeBasicPanel.add(donenessPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds grams panel input panel to recipe basic panel
    private void addGramsPanel() {
        JLabel grams = new JLabel("Weight(g)");
        JPanel gramPanel = new JPanel();
        gramField = new JTextField();
        gramField.setPreferredSize(new Dimension(100, 25));
        gramPanel.add(grams);
        gramPanel.add(gramField);
        recipeBasicPanel.add(gramPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds thickness panel input panel to recipe basic panel
    private void addThicknessPanel() {
        JLabel thickness = new JLabel("Thickness(mm)");
        JPanel thicknessPanel = new JPanel();
        thicknessField = new JTextField();
        thicknessField.setPreferredSize(new Dimension(100, 25));
        thicknessPanel.add(thickness);
        thicknessPanel.add(thicknessField);
        recipeBasicPanel.add(thicknessPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds cut panel input panel to recipe basic panel
    private void addCutPanel() {
        JLabel cut = new JLabel("Cut of Beef");
        JPanel cutPanel = new JPanel();
        cutField = new JTextField();
        cutField.setPreferredSize(new Dimension(100, 25));
        cutPanel.add(cut);
        cutPanel.add(cutField);
        recipeBasicPanel.add(cutPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds name panel input panel to recipe basic panel
    private void addNamePanel() {
        JPanel namePanel = new JPanel();
        JLabel recipeName = new JLabel("Recipe Name");
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(100, 25));
        namePanel.add(recipeName);
        namePanel.add(nameField);
        recipeBasicPanel.add(namePanel);
    }

    //Represents the action to be taken when the user clicks add pic button
    private class AddPicAction extends AbstractAction {
        AddPicAction() {
            super("Add Pic");
        }

        // MODIFIES: this
        // EFFECTS: lets user select a file and save the directory of the file
        @Override
        public void actionPerformed(ActionEvent e) {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(PIC_STORE));

            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File homeFile = new File(".");
                String homePath = homeFile.getAbsolutePath();
                picPath = "." + fileChooser.getSelectedFile().getPath().substring(+ homePath.length() - 2);
            }

        }
    }

    //    Represents the action to be taken when the user clicks add step button
    private class AddStepAction extends AbstractAction {
        AddStepAction() {
            super("Add Step");
        }


        // MODIFIES: this
        // EFFECTS: creates a new step with user input;
        //          adds a new step to stepList
        @Override
        public void actionPerformed(ActionEvent e) {


            String stepName = stepNameField.getText();
            String stepAction = actionField.getText();
            int stepTime = Integer.parseInt(timeField.getText());
            steps.add(new Step(stepName, stepAction, stepTime));

            stepNameField.setText("");
            actionField.setText("");
            timeField.setText("");

            stepStatusLabel.setText("You've added " + stepAdded + " step(s)");
            stepAdded++;
        }
    }


    //    Represents the action to be taken when the user clicks add create button
    private class CreateAction extends  AbstractAction {
        CreateAction() {
            super("Create");
        }

        // MODIFIES: this, SteakMasterUI
        // EFFECTS: creates a new recipe with user input;
        //          adds a new step to recipe list
        //          revalidate main frame
        @Override
        public void actionPerformed(ActionEvent e) {
            String recipeName = nameField.getText();
            String cut = cutField.getText();
            int thickness = Integer.parseInt(thicknessField.getText());
            int gram = Integer.parseInt(gramField.getText());
            String doneness = donenessField.getText();

            Steak newSteak = new Steak(cut, thickness, gram);
            Recipe newRecipe = new Recipe(recipeName, newSteak, doneness);
            for (Step step: steps) {
                newRecipe.addStep(step);
            }
            if (picPath != null) {
                newRecipe.setPicturePath(picPath);
            }

            recipeList.add(newRecipe);
            int recipeIndex = recipeList.size() - 1;
            JPanel newRecipePanel = steakMasterUI.createRecipePanel(newRecipe, recipeIndex);
            steakMasterUI.getRecipesPanel().add(newRecipePanel, recipeIndex);
            steakMasterUI.revalidate();
            frame.dispose();

        }
    }


}
