package ui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

// Steak cooking application with real-time cooking assistance and recipe management
public class SteakMasterUI extends JFrame {
    private static final String JSON_STORE = "./data/recipes.json";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;


    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    private RecipeManager recipeManager;

    private JPanel titlePanel;

    public JPanel getRecipesPanel() {
        return recipesPanel;
    }

    private JPanel recipesPanel;
    private JPanel buttonsPanel;

    private SteakMasterUI frame;



    // EFFECTS: runs the Steak Master UI application
    public SteakMasterUI() {
        super("Steak Master");
        frame = this;
        recipeManager = new RecipeManager();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                printLog(EventLog.getInstance());
                System.exit(0);

            }
        });


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());
        setTitle("Steak Master");

        addTitlePanel();
        addRecipesPanel();
        addButtonPanel();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: adds button panel to the main frame
    private void addButtonPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3,1));
        buttonsPanel.setPreferredSize(new Dimension(200,200));
        buttonsPanel.setBackground(new Color(236, 240, 241));

        JButton buttonCreate = new JButton(new CreateRecipeAction());
        buttonCreate.setFont(new Font("SansSerif", Font.BOLD, 16));
        JButton buttonLoad = new JButton(new LoadAction());
        buttonLoad.setFont(new Font("SansSerif", Font.BOLD, 16));
        JButton buttonSave = new JButton(new SaveAction());
        buttonSave.setFont(new Font("SansSerif", Font.BOLD, 16));

        buttonsPanel.add(buttonCreate);
        buttonsPanel.add(buttonLoad);
        buttonsPanel.add(buttonSave);
        buttonsPanel.setVisible(true);


        add(buttonsPanel,BorderLayout.EAST);
    }


    // MODIFIES: this
    // EFFECTS: adds title panel to the main frame
    private void addTitlePanel() {
        titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(WIDTH,100));
        JLabel title = new JLabel();
        title.setText("Steak Master");
        title.setFont(new Font("Ink Free",Font.BOLD,50));
        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(title);
        add(titlePanel, BorderLayout.NORTH);
    }


    // MODIFIES: this
    // EFFECTS: adds title panel to the main frame
    private void addRecipesPanel() {
        recipesPanel = new JPanel();

        recipesPanel.setLayout(new GridLayout(10,1));
        recipesPanel.setPreferredSize(new Dimension(WIDTH,500));
        recipesPanel.setBackground(new Color(236, 240, 241));

        int i = 0;
        for (Recipe recipe: recipeManager.getRecipes()) {
            JPanel recipePanel = createRecipePanel(recipe, i);

            recipesPanel.add(recipePanel, BorderLayout.WEST, i);
            i++;
        }
        add(recipesPanel, 1);
    }

    // MODIFIES: this
    // EFFECTS: returns a complete recipe panel with title, duration, cook and delete button
    public JPanel createRecipePanel(Recipe recipe, int recipeIndex) {
        JPanel recipePanel = new JPanel(new GridLayout(1, 4));
        JLabel titleLabel = new JLabel(recipe.getName());
        titleLabel.setBorder(new CompoundBorder(titleLabel.getBorder(),
                new EmptyBorder(0,15,0,0)));
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        JLabel durationLabel = new JLabel(recipe.getTimeInMinSec());
        durationLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        durationLabel.setBorder(new CompoundBorder(durationLabel.getBorder(),
                new EmptyBorder(0,15,0,0)));

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        recipePanel.setBorder(border);

        JButton deleteButton = new JButton(new DeleteAction());
        deleteButton.setName(String.valueOf(recipeIndex));
        deleteButton.setFont(new Font("SansSerif", Font.BOLD, 16));

        JButton cookButton = new JButton(new CookAction());
        cookButton.setName(String.valueOf(recipeIndex));
        cookButton.setFont(new Font("SansSerif", Font.BOLD, 16));

        recipePanel.add(titleLabel, BorderLayout.CENTER);
        recipePanel.add(durationLabel, BorderLayout.CENTER);
        recipePanel.add(cookButton);
        recipePanel.add(deleteButton);
        return recipePanel;
    }




    // Represents the action to be taken when the user clicks delete button
    private class DeleteAction extends AbstractAction {

        DeleteAction() {
            super("❌");
        }

        // MODIFIES: this
        // EFFECTS: deletes the recipe of the button clicked from main frame and recipe list
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = 0;

            JButton button = (JButton) e.getSource();
            JPanel recipePanel = (JPanel)button.getParent();
            Component[] recipePanels = recipesPanel.getComponents();

            int recipeIndex = Integer.parseInt(button.getName());

            recipeManager.remove(recipeIndex);
            recipesPanel.remove(recipePanel);

            frame.repaint();
            frame.revalidate();

            for (Component panel: recipePanels) {
                panel.setName(String.valueOf(i));
            }
        }
    }

    public List<Recipe> getRecipeList() {
        return recipeManager.getRecipes();
    }



    // Represents the action to be taken when the user clicks create recipe button
    private class CreateRecipeAction extends AbstractAction {
        CreateRecipeAction() {
            super("Create Recipe");
        }



        // EFFECTS: instantiates CreateRecipeUI
        @Override
        public void actionPerformed(ActionEvent e) {
            CreateRecipeUI createRecipeUI = new CreateRecipeUI(frame);
        }
    }

    //    Represents the action to be taken when the user clicks load button
    private class LoadAction extends AbstractAction {
        LoadAction() {
            super("Load Data");
        }

        // MODIFIES: this
        // EFFECTS: loads the saved recipes data;
        //          updates the main frame with the loaded data
        @Override
        public void actionPerformed(ActionEvent e) {
            loadRecipes();
            recipesPanel.removeAll();
            int i = 0;
            for (Recipe recipe: recipeManager.getRecipes()) {
                JPanel recipePanel = createRecipePanel(recipe, i);
                recipesPanel.add(recipePanel, i);
                i++;
            }
            repaint();
            revalidate();

        }
    }

    //Represents the action to be taken when the user clicks save button
    private class SaveAction extends AbstractAction {
        SaveAction() {
            super("Save Data");
        }

        // EFFECTS: saves the recipes to file
        @Override
        public void actionPerformed(ActionEvent e) {
            saveRecipes();
        }
    }

    //Represents the action to be taken when the user clicks cook button
    private class CookAction extends AbstractAction {
        CookAction() {
            super("Cook");
        }

        // EFFECTS: opens the cook window of the selected recipe
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            System.out.println(button.getName());
            int recipeIndex = Integer.parseInt(button.getName());


            CookRecipeUI cookRecipeUI = new CookRecipeUI(recipeManager.getRecipe(recipeIndex));
        }
    }

    // EFFECTS: saves the recipes to file
    private void saveRecipes() {
        try {
            jsonWriter.open();
            jsonWriter.write(recipeManager.getRecipes());
            jsonWriter.close();
            System.out.println("Saved the current recipes to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: loads recipes from file
    private void loadRecipes() {
        try {
            recipeManager.setRecipes(jsonReader.read());
            System.out.println("Loaded recipes from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS : prints events logged since the application started
    private void printLog(EventLog eventLog) {
        for (Event next : eventLog) {
            System.out.println(next.toString() + "\n\n");
        }

    }
}
