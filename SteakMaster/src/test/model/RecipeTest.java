package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {
    private Recipe testRecipe;
    private Steak testSteak;

    private int testTime1 = 10;
    private int testTime2 = 20;
    private int testTime3 = 30;


    private String testStepName1 = "testStep1";
    private String testStepName2 = "testStep2";
    private String testStepName3 = "testStep3";

    private String testAction1 = "Tested Well";
    private String testAction2 = "Tested Well2";
    private String testAction3 = "Tested Well3";

    private String testCut = "rib";
    private int testThickness = 20;
    private int testGrams = 200;

    private String testRecipeName = "testing";
    private String testDoneness = "rare";

    private String TestlastStep = "Nope. You are in the final step!";


    Step testStep1 = new Step(testStepName1, testAction1, testTime1);
    Step testStep2 = new Step(testStepName2, testAction2, testTime2);
    Step testStep3 = new Step(testStepName3, testAction3, testTime3);



    @BeforeEach
    void runBefore() {
        testSteak = new Steak(testCut, testThickness, testGrams);
        testRecipe = new Recipe(testRecipeName, testSteak, testDoneness);
    }


    @Test
    void testConstructor() {
        Steak testSteak2 = testRecipe.getSteak();
        assertEquals(testRecipeName, testRecipe.getName());
        assertEquals(testDoneness, testRecipe.getDoneness());
        assertEquals(testCut, testSteak2.getCut());
        assertEquals(testThickness, testSteak2.getThickness());
        assertEquals(testGrams, testSteak2.getGrams());
    }

    @Test
    void testAddStep() {

        assertEquals(0, testRecipe.getStepList().size());
        testRecipe.addStep(testStep1);
        assertEquals(1, testRecipe.getStepList().size());
        assertEquals(testStepName1, testRecipe.getStepList().get(0).getName());
        assertEquals(testAction1, testRecipe.getStepList().get(0).getAction());
        assertEquals(testTime1, testRecipe.getStepList().get(0).getTime());
        assertEquals(testTime1, testRecipe.getTime());

        testRecipe.addStep(testStep2);
        assertEquals(2, testRecipe.getStepList().size());
        assertEquals(testStepName2, testRecipe.getStepList().get(1).getName());
        assertEquals(testAction2, testRecipe.getStepList().get(1).getAction());
        assertEquals(testTime2, testRecipe.getStepList().get(1).getTime());
        assertEquals(testTime1 + testTime2, testRecipe.getTime());


    }

    @Test
    void currentStep() {
        testRecipe.addStep(testStep1);
        testRecipe.addStep(testStep2);
        testRecipe.addStep(testStep3);

        assertEquals(testStepName1, testRecipe.currentStep().getName());
        testRecipe.tickSecond();
        assertEquals(testStepName1, testRecipe.currentStep().getName());

        for (int i = 0; i < 8; i++) {
            testRecipe.tickSecond();
        }
        assertEquals(testStepName1, testRecipe.currentStep().getName());

        testRecipe.tickSecond();
        assertEquals(testStepName2, testRecipe.currentStep().getName());

        for (int i = 0; i < 10; i++) {
            testRecipe.tickSecond();
        }
        assertEquals(testStepName2, testRecipe.currentStep().getName());

        for (int i = 0; i < 9; i++) {
            testRecipe.tickSecond();
        }
        assertEquals(testStepName2, testRecipe.currentStep().getName());

        testRecipe.tickSecond();
        assertEquals(testStepName3, testRecipe.currentStep().getName());

        for (int i = 0; i < 30; i++) {
            testRecipe.tickSecond();
        }
        assertEquals(testStepName3, testRecipe.currentStep().getName());


    }

    @Test
    void nextStepName() {
        testRecipe.addStep(testStep1);
        testRecipe.addStep(testStep2);
        testRecipe.addStep(testStep3);

        assertEquals(testStepName2, testRecipe.nextStepName());
        testRecipe.tickSecond();
        assertEquals(testStepName2, testRecipe.nextStepName());

        for (int i = 0; i < 8; i++) {
            testRecipe.tickSecond();
        }
        assertEquals(testStepName2, testRecipe.nextStepName());

        testRecipe.tickSecond();
        assertEquals(testStepName3, testRecipe.nextStepName());

        for (int i = 0; i < 10; i++) {
            testRecipe.tickSecond();
        }
        assertEquals(testStepName3, testRecipe.nextStepName());

        for (int i = 0; i < 9; i++) {
            testRecipe.tickSecond();
        }
        assertEquals(testStepName3, testRecipe.nextStepName());

        testRecipe.tickSecond();
        assertEquals(TestlastStep, testRecipe.nextStepName());

        for (int i = 0; i < 30; i++) {
            testRecipe.tickSecond();
        }
        assertEquals(TestlastStep, testRecipe.nextStepName());



    }

    @Test
    void resetTimeAll() {
        testRecipe.addStep(testStep1);
        testRecipe.addStep(testStep2);
        testRecipe.addStep(testStep3);

        for(int i = 0; i < 50; i++) {
            testRecipe.tickSecond();
        }
        assertEquals(10, testRecipe.getTimeLeft());
        testRecipe.resetTimeAll();
        assertEquals(60, testRecipe.getTimeLeft());

        assertEquals(testTime1, testStep1.getTimeLeft());
        assertEquals(testTime2, testStep2.getTimeLeft());
        assertEquals(testTime3, testStep3.getTimeLeft());
    }
}
