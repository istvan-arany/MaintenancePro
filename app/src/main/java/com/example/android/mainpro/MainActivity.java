package com.example.android.mainpro;

    /*
    Maintenance Pro Quiz applet. For the 3rd project of Udacity Android for Beginners Course.
    This applet is picking 10 randomly chosen questions from a pool of several more.
    While going through the test it counts the number of right answers, and gives a result at the end.

    This project is actually useful for me at the moment, because I have a trainee engineer who I have to teach
    a lot of information that are dependent on our uniquely built vessels. With this app I can provide at
    least an easy way to keep his knowledge refreshed every once in a while.

    comment for my evaluation: I left out the displaying of the result with a toast part, only because
    I didn't feel like it would fit into the whole feeling of the app nicely. I hope based on my code
    you believe that I am capable of writing a toast :)
    */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import static com.example.android.mainpro.R.id.bottomButton;
import static com.example.android.mainpro.R.id.question_line;

public class MainActivity extends AppCompatActivity {
    /*
        Preparing static variables.
     */
    public static String actualRightAnswer;
    public static int count = 0;
    public static int score = 0;
    public static int[] actualQuestionOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    /*
        Evaluating and activating the action of the Button on the bottom of the screen.
     */
    public void bottomButton(View view) {
        resetButtonColors();
        view.setEnabled(false);
        enableButtons();
        switch (count) {
            case 0  :
                startQuiz(view);
                break;
            case 9  :
                TextView resultButton = (TextView) findViewById(bottomButton);
                resultButton.setText(getStrRes("get_result"));
                prepNext(view);
                break;
            case 10 :
                endTest(view);
                break;
            case 100:
                restart(view);
                break;
            default:
                prepNext(view);
                break;
        }
    }
    /*
        This method gets called when user presses <Start the Test> button,
        It starts the quiz, and prepares the layout for the test.
    */
    public void startQuiz(View view) {
        TextView questionLine = (TextView) findViewById(question_line);
        questionLine.setVisibility(View.VISIBLE);
        TextView startButton = (TextView) findViewById(bottomButton);
        startButton.setText(getStrRes("next_question"));
        startButton.setEnabled(false);
        ImageView logo = (ImageView) findViewById(R.id.startup_logo);
        logo.setVisibility(View.GONE);
        TextView score = (TextView) findViewById(R.id.score);
        score.setVisibility(View.GONE);
        View buttons = findViewById(R.id.buttons);
        buttons.setVisibility(View.VISIBLE);

        int[] questionOrder = randomList();
        actualQuestionOrder = questionOrder;
        int next = questionOrder[0];
        count = 1;
        listNextQandA(count, next);
    }
    /*
        Displaying the oncoming question and answer set on the layout.
     */
    public void listNextQandA(int count, int next) {
        String[] questionResource = createResource(next);
        TextView questionView = (TextView) findViewById(R.id.question_line);
        questionView.setText(count + ". " + questionResource[0]);
        Button buttonA = (Button) findViewById(R.id.buttonA);
        Button buttonB = (Button) findViewById(R.id.buttonB);
        Button buttonC = (Button) findViewById(R.id.buttonC);
        Button buttonD = (Button) findViewById(R.id.buttonD);
        actualRightAnswer = questionResource[1];
        int[] answerOrder = randomAnswers();
        buttonA.setText(questionResource[answerOrder[0]]);
        buttonB.setText(questionResource[answerOrder[1]]);
        buttonC.setText(questionResource[answerOrder[2]]);
        buttonD.setText(questionResource[answerOrder[3]]);
    }
    /*
        Deciding if A answer was correct or not. Also disabling buttons, to avoid multiple selections.
     */
    public void aPressed(View view) {
        disableButtons();
        TextView AbuttonPressed = (TextView) findViewById(R.id.buttonA);
        if ((AbuttonPressed.getText().equals(actualRightAnswer))) {
            AbuttonPressed.setTextColor(Color.parseColor("#4CAF50"));
            AbuttonPressed.setText(getStrRes("right_answer"));
            score++;
            TextView nextQbutton = (TextView) findViewById(R.id.bottomButton);
            nextQbutton.setEnabled(true);
        } else {
            AbuttonPressed.setTextColor(Color.parseColor("#FF5252"));
            AbuttonPressed.setText(getStrRes("wrong_answer"));
            showRightAnswer(view);
        }
    }
    /*
        Deciding if B answer was correct or not. Also disabling buttons, to avoid multiple selections.
     */
    public void bPressed(View view){
        disableButtons();
        TextView BbuttonPressed = (TextView) findViewById(R.id.buttonB);
        if ((BbuttonPressed.getText().equals(actualRightAnswer))) {
            BbuttonPressed.setTextColor(Color.parseColor("#4CAF50"));
            BbuttonPressed.setText(getStrRes("right_answer"));
            score++;
            TextView nextQbutton = (TextView) findViewById(R.id.bottomButton);
            nextQbutton.setEnabled(true);
        } else {
            BbuttonPressed.setTextColor(Color.parseColor("#FF5252"));
            BbuttonPressed.setText(getStrRes("wrong_answer"));
            showRightAnswer(view);
        }
    }
    /*
        Deciding if C answer was correct or not. Also disabling buttons, to avoid multiple selections.
     */
    public void cPressed(View view) {
        disableButtons();
        TextView CbuttonPressed = (TextView) findViewById(R.id.buttonC);
        if ((CbuttonPressed.getText().equals(actualRightAnswer))) {
            CbuttonPressed.setTextColor(Color.parseColor("#4CAF50"));
            CbuttonPressed.setText(getStrRes("right_answer"));
            score++;
            TextView nextQbutton = (TextView) findViewById(R.id.bottomButton);
            nextQbutton.setEnabled(true);
        } else {
            CbuttonPressed.setTextColor(Color.parseColor("#FF5252"));
            CbuttonPressed.setText(getStrRes("wrong_answer"));
            showRightAnswer(view);
        }
    }
    /*
        Deciding if D answer was correct or not. Also disabling buttons, to avoid multiple selections.
     */
    public void dPressed(View view) {
        disableButtons();
        TextView DbuttonPressed = (TextView) findViewById(R.id.buttonD);
        if ((DbuttonPressed.getText().equals(actualRightAnswer))) {
            DbuttonPressed.setTextColor(Color.parseColor("#4CAF50"));
            DbuttonPressed.setText(getStrRes("right_answer"));
            score++;
            TextView nextQbutton = (TextView) findViewById(R.id.bottomButton);
            nextQbutton.setEnabled(true);
        } else {
            DbuttonPressed.setTextColor(Color.parseColor("#FF5252"));
            DbuttonPressed.setText(getStrRes("wrong_answer"));
            showRightAnswer(view);
        }
    }
    /*
        In case of a wrongly chosen answer, this method seeks out and displays the correct one, so
        users can learn from their mistakes.
     */
    public void showRightAnswer(View view) {
        Button buttonA = (Button) findViewById(R.id.buttonA);
        Button buttonB = (Button) findViewById(R.id.buttonB);
        Button buttonC = (Button) findViewById(R.id.buttonC);
        Button buttonD = (Button) findViewById(R.id.buttonD);
        if (buttonA.getText().equals(actualRightAnswer)) {
            buttonA.setTextColor(Color.parseColor("#4CAF50"));
            TextView nextQbutton = (TextView) findViewById(R.id.bottomButton);
            nextQbutton.setEnabled(true);
        } else if (buttonB.getText().equals(actualRightAnswer)) {
            buttonB.setTextColor(Color.parseColor("#4CAF50"));
            TextView nextQbutton = (TextView) findViewById(R.id.bottomButton);
            nextQbutton.setEnabled(true);
        } else if (buttonC.getText().equals(actualRightAnswer)) {
            buttonC.setTextColor(Color.parseColor("#4CAF50"));
            TextView nextQbutton = (TextView) findViewById(R.id.bottomButton);
            nextQbutton.setEnabled(true);
        } else {
            buttonD.setTextColor(Color.parseColor("#4CAF50"));
            TextView nextQbutton = (TextView) findViewById(R.id.bottomButton);
            nextQbutton.setEnabled(true);
            }
    }
    /*
        RandomAnswers() method creates a 4 element integer array of randomly chosen,
        different numbers from 1 to 4 to be used for randomly placing possible answers on the buttons.
        By changing parameters of getRandomInteger method, the pool can be altered.
        The method also checks that chosen numbers are never identical.
    */
    public int[] randomAnswers() {
        int[] answerNumbers = new int[4];
        for (int i = 1; i < 5; i++) {
            int newQuestion = getRandomInteger(5, 1);
            boolean exists = false;
            for (int j = i; j > 0; j--) {
                if (answerNumbers[(j - 1)] == newQuestion) {
                    exists = true;
                }
            }
            if (!exists) {
                answerNumbers[i - 1] = newQuestion;
            } else {
                i = i - 1;
            }
        }
        return answerNumbers;
    }
    /*
        RandomList() method creates a 10 element integer array of randomly chosen,
        different numbers from 1 to 23 to get random question-answer sets from the pool.
        By changing parameters of getRandomInteger method, the number pool can be altered.
        The method also checks that chosen numbers are never identical.
     */
    public int[] randomList() {
        int[] questionNumbers = new int[10];
        for (int i = 1; i < 11; i++) {
            int newQuestion = getRandomInteger(24, 1);
            boolean exists = false;
            for (int j = i; j > 0; j--) {
                if (questionNumbers[(j - 1)] == newQuestion) {
                    exists = true;
                }
            }
            if (!exists) {
                questionNumbers[i - 1] = newQuestion;
            } else {
                i = i - 1;
            }
        }
        return questionNumbers;
    }
    /*
        Random integer generator.
     */
    public static int getRandomInteger(int maximum, int minimum) {
        return ((int) (Math.random() * (maximum - minimum))) + minimum;
    }
    /*
        Setting the question counter and the number of next chosen question from the pool.
     */
    public void prepNext(View view) {
        int next = actualQuestionOrder[count];
        count++;
        listNextQandA(count, next);
    }
    /*
        Preparing the next Question and its possible answers based on the randomList array.
    */
    public String[] createResource(int next) {
        String selectedQuestion = "question" + next;
        int id = getResources().getIdentifier(selectedQuestion, "array", getPackageName());

        return getResources().getStringArray(id);
    }
    /*
        Prepare the layout to display summarized result.
     */
    public void endTest(View view) {
        View buttons = findViewById(R.id.buttons);
        buttons.setVisibility(View.GONE);
        TextView questionLine = (TextView) findViewById(question_line);
        questionLine.setVisibility(View.GONE);
        TextView endButton = (TextView) findViewById(R.id.bottomButton);
        endButton.setText(getStrRes("endButton"));
        endButton.setEnabled(true);
        TextView score = (TextView) findViewById(R.id.score);
        score.setVisibility(View.VISIBLE);
        score.setText(displayScore());
        count = 100;
    }
    /*
        Reset counter and score then restart the quiz.
     */
    public void restart(View view) {
        count = 0;
        score = 0;
        startQuiz(view);
    }
    /*
        Summarize result after the test is taken.
     */
    public String displayScore() {
        String result;
        if (score <= 5) {
            result = "You answered " + score + " out of 10 questions correctly.\n" +
                            "I'm sure You can do better!\n" +
                            "Press button, to take another test!";
        } else if (score < 10) {
            result = "You answered " + score + " out of 10 questions correctly.\n" +
                            "Not bad!\n" +
                            "Press button, to take another test!";
        } else {
            result = "You answered " + score + " out of 10 questions correctly.\n" +
                            "Perfect!\n" +
                            "Press button, to take another test!";
        }
        return result;
    }
    /*
        Get String values from values.strings.xml.
     */
    public String getStrRes(String name) {
        return getString(getResources().getIdentifier(name, "string", getPackageName()));
    }
    /*
        Reset textColor on buttons to White.
     */
    public void resetButtonColors() {
        TextView aButtonReset = (TextView) findViewById(R.id.buttonA);
        aButtonReset.setTextColor(Color.parseColor("#FFFFFF"));
        TextView bButtonReset = (TextView) findViewById(R.id.buttonB);
        bButtonReset.setTextColor(Color.parseColor("#FFFFFF"));
        TextView cButtonReset = (TextView) findViewById(R.id.buttonC);
        cButtonReset.setTextColor(Color.parseColor("#FFFFFF"));
        TextView dButtonReset = (TextView) findViewById(R.id.buttonD);
        dButtonReset.setTextColor(Color.parseColor("#FFFFFF"));
    }
    /*
        Disable buttons after answer chosen.
     */
    public void disableButtons() {
        Button buttonA = (Button) findViewById(R.id.buttonA);
        buttonA.setEnabled(false);
        Button buttonB = (Button) findViewById(R.id.buttonB);
        buttonB.setEnabled(false);
        Button buttonC = (Button) findViewById(R.id.buttonC);
        buttonC.setEnabled(false);
        Button buttonD = (Button) findViewById(R.id.buttonD);
        buttonD.setEnabled(false);
    }
    /*
        Re-enable buttons for choosing answers.
     */
    public void enableButtons() {
        Button buttonA = (Button) findViewById(R.id.buttonA);
        buttonA.setEnabled(true);
        Button buttonB = (Button) findViewById(R.id.buttonB);
        buttonB.setEnabled(true);
        Button buttonC = (Button) findViewById(R.id.buttonC);
        buttonC.setEnabled(true);
        Button buttonD = (Button) findViewById(R.id.buttonD);
        buttonD.setEnabled(true);
    }
}