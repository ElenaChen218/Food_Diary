# My Personal Project

## Food Diary Application

**Food Diary** is an application that keeps track fo all the food that the user has eaten in a day. It has the following
fuctions:

- Records the user's personal health information, including the person's height,weight, gender and age. 
- Calculates the person's BMI base on user info collected.
- Records the food that the user has eaten in a day.
- Calculate the total calorie intake of the user base on the food record.
- Presents a report of all food eaten in a day to the user
 
  
  
  The target users of the application are people who would like to monitor their daily diet for weight control purposes
   or would like to develop healthy eating habits. I am very interested in this project since personally I really pay 
   attention to keeping fit, and it is so much more convenient to have an application to record my diet and allow me to 
   aware what I have eaten in a day.
## User Stories

- As a user, I want to be able to get my BMI (Body Mass Index).
- As a user, I want to be able to add food to my list.
- As a user, I want to be able to remove food from my list.
- As a user, I want to be able to know my total calorie intake in a day.
- As a user, I want to be able to view all food I ate in a day.
- As a user, I want to be able to save my user info to the file.
- As a user, I want to be able to load my user info from the file.
- As a user, I want to be able to save my food list to the file.
- As a user, I want to be able to load my food list from the file.

## Phase 4: Task 2
Test and design a class in your model package that is robust.  You must have at least one method that throws a checked 
exception.  You must have one test for the case where the exception is expected and another where the exception is not
expected.

- addFood method in FoodList class have a robust design.
- Two checked exceptions can be thrown by this method: 1. InvalidAmountException 2. InvalidCalorieException

## Phase 4: Task 3
AddFood and RemoveFood are two classes that open up a new window to add a food to the foodList and remove a food from 
the foodList, respectively. If I had more time to work on the project, I would improve my design by creating an abstract
class for the two classes. They have some similar methods, so by having both classes extend from an abstract class can 
reduce duplication of codes while in the meantime preserve their unique functionality.

In the new abstract class called ModifyFoodList that I am about to create:
- **Constructor**: exactly the same as the constructor in AddFood and RemoveFood, so the two subclasses will just have 
super(userInfo); in the constructor body.
- **audioSetUp();** : provides the behaviour in abstract class
- **setUpWindow();** : make abstract and implement in subclasses
- **setUpComponents();** : make abstract and implement in subclasses
- **actionPerformed(ActionEvent e);** : make abstract and implement in subclasses
- **storeFood();** and **removeFood;** : don't appear in abstract class and only implement in subclasses
