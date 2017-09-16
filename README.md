# Quiz App

This is a simple Android Studio project for the [Android Basics Nanodegree](https://www.udacity.com/course/android-basics-nanodegree-by-google--nd803) given by Udacity and Google. The goal was to create an educational app that quizzed users about a certain topic. I chose **Star Wars** as the main topic of this app.

Besides the requirements for this project (see *Layout*, *Functionality* and *Code Readability* sections below) I tried to do my best with:

* Customised fonts.
* Different layouts for portrait and landscape orientations.
* Enhanced selection of random questions every time you run the quiz.
* No unused resources.
* Memory optimization.
* No warnings at all.

Some additional resources I used for this app:

* Making the intro: https://brorlandi.github.io/StarWarsIntroCreator/
* Screen capture for intro: [ScreenCastify Lite for Chrome](https://chrome.google.com/webstore/detail/screencastify-screen-vide/mmeijimgabbpbgpdklnllpncmdofkcpn).
* Video cropping: http://online-video-cutter.com
* Adding “fade” effect for audio clips: https://audiotrimmer.com


If you want to preview how it works, you can check these two video files (captured from Windows 10 desktop using the keys combination *Windows logo key + G*):

[![Landscape orientation](https://github.com/dburgosp/EducationalApp/blob/master/video_preview_landscape.jpg?raw=true)](https://www.youtube.com/watch?v=IMxO_mxPjrQ)
[![Portrait orientation](https://github.com/dburgosp/EducationalApp/blob/master/video_preview_portrait.jpg?raw=true)](https://www.youtube.com/watch?v=Xj1wRTntAZY)

## Layout

1. **Overall Layout**. App contains 4 - 10 questions, including at least one check box, one radio button, and one text entry.
2. **Question types**. Questions are in a variety of formats such as free text response, checkboxes, and radio buttons. Checkboxes are only used for questions with multiple right answers. Radio buttons are only used for questions with a single right answer.
3. **Submit button**. App includes a button for the user to submit their answers and receive a score.
4. **Layout best practices**. The code adheres to all of the following best practices:
   * Text sizes are defined in sp.
   * Lengths are defined in dp.
   * Padding and margin is used appropriately, such that the views are not crammed up against each other.
5. **View variety**. The app includes at least four of the following Views: TextView, ImageView, Button, Checkbox, EditText, LinearLayout, RelativeLayout, ScrollView, RadioButton, RadioGroup. If applicable, the app uses nested ViewGroups to reduce the complexity of the layout.
6. **Rotation**. The app gracefully handles displaying all the content on screen when rotated. Either by updating the layout, adding a scrollable feature or some other mechanism that adheres to Android development guidelines.

## Functionality

1. **Runtime Errors**. The code runs without errors.
2. **Question Answers**. Each question has a correct answer.
3. **Radio Button Implementation**. Any question which uses radio buttons allows only one to be checked at once.
4. **Control Statements**. The app contains at least one if/else statement.
5. **Grading Button Function**. The grading button displays a toast which accurately displays the results of the quiz.
6. **Grading Logic**. The grading logic checks each answer correctly. The app accurately calculates the number of correct answers and does not include incorrect answers in the count.

Note: When applicable, in the grading logic remember to check that the correct answers are checked AND the incorrect answers are not checked.

## Code Readability

1. **Naming Conventions**. All variables, methods, and resource IDs are descriptively named such that another developer reading the code can easily understand their function.
2. **Format**. The code is properly formatted i.e. there are no unnecessary blank lines; there are no unused variables or methods; there is no commented out code. The code also has proper indentation when defining variables and methods.
