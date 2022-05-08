# Steak Master
## A Steak-Cooking Assistant for Everybody
*Steak Master* is application to help people make perfect steaks at home.*Steak Master* helps people successfully cook steaks with real-time cooking assistant that tells you what to do at the perfect time during your cooking. Also, you can create, delete, and track down your recipes using Steack Master. The

## Features
- Create Recipe: Click "Create Recipe" button and enter the information of the recipe and add steps for the recipe. You can also add a picture of the recipe too!
- Delete Recipe: By clicking "X" button of each reacipe, you can delete the recipe.
- Cook Recipe: By clicking "Cook" button of each reacipe, you can see the recipe with the information and steps you added.
- Save Data: "Save Data" button saves the current recipes in JSON format in "./data/recipes.json".
- Load Data: "Load data" button loads the saved recipes by parsing the JSON file into recipe objects.
- Save Log: User logs are automatically printed in console after ending the program. They are tracked using Observer Pattern

## Development
-	Applied OOP; recipes and steps are programmed in Composite Pattern and user log is tracked using Observer Pattern
-	Recipes can be and saved in JSON format and can be loaded to the program by parsing the JSON file
- Functions and features are tested using JUnit

## Installation and Using
1. Download the entire SteakMaster Folder
2. Excute the "SteakMaster/src/main/ui/main"
3. Two sample recipes are added by defaults, you can create and save your own recipes!
