# SOEN 343 Project (Team 22)

# **Project Name: Smart Home Simulator (SHS)**

## **Objective**
To develop a Smart Home Simulator application. This application will help people in general (especially students and homeowners) to understand and plan smart home systems. The application will allow users to interact with the date, time, lightings, windows, inside and outside temperatures.   

## **Technologies Used**
### **Front-end** ###
* JavaFX
### **Back-end** ###
* Java

## Note
The project runs best with JDK 8 or JDK 9. If JDK 11 or later is used, then the [JavaFX library](https://gluonhq.com/products/javafx/) must be downloaded separately and must be added as an external dependency.

## External Dependencies
* [json-simple 1.1.1](https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/json-simple/json-simple-1.1.1.jar). This library is used to parse and read the house layout which is stored in a JSON file.
* [JFoeniX 8](https://search.maven.org/remotecontent?filepath=com/jfoenix/jfoenix/8.0.10/jfoenix-8.0.10.jar) if you are using JDK 8 or [JFoeniX 9](https://search.maven.org/remotecontent?filepath=com/jfoenix/jfoenix/9.0.10/jfoenix-9.0.10.jar) if you are using JDK 9. This library is used in order to add time and date widget pickers.

## **Team Members**
* Razvan Ivan (40096278): razvan.ivan10@yahoo.com
* Fahim Bhuiyan (40091942) : fahimrbhuiyan@gmail.com
* David Rady (40098177) : David.Rady16@alumni.loyola.ca
* Mark Said (40045772) : marksaid206@gmail.com
* Lauren Lim (40097885) : mnxnhl@gmail.com

## **Getting Started**
1. Clone/download SOEN343 Project.
2. Download the External Dependencies. (json-simple & JFoeniX)
3. Open the SOEN343 folder as a project in IntelliJ IDEA.
4. Mark the src directory as source code.
5. Create a directory called "out" in the SOEN343 folder and mark it as excluded.
6. Configure project settings by going to File>Project Structure. `(CTRL+ALT+SHIFT+S)`
7. Inside the Project Structure window that appeared, go to Project Settings>Project and set the JDK version you're using and set the project compiler output path to the out directory that you just created.
8. Again, inside the Project Structure window that appeared, go to Project Settings>Libraries and add the two external dependencies by clicking on the plus sign and by specifying the path for each dependency.
9. Head over to SOEN343\src\sample\SmartHomeView\MainView, build the project (click on the green hammer icon on the top menu bar), and then run the main method of the class MainView.
10. The application will ask to upload a house file. In the SOEN343 directory, there should a json file called `HouseInfo`. On the text field, type the location of the file. (Ex: D:\Documents\GitHub\SOEN343\HouseInfo.json)

## **System In Operation**
### **Delivery 1**
* [Report](https://docs.google.com/document/d/1CJXxn7XZUxPXvwjpcWOrYFK7pl63NPytqWtMBpBK6ZA/edit#)

Dashboard as soon as the application is running. 
![alt text](https://imgur.com/gHF1wsm.png)

Enter file location on text field.
![alt text](https://imgur.com/TpUimvS.png)

Dashboard after file upload. 
![alt text](https://imgur.com/Tpmhajq.png)

Dashboard when the user is logged in and has set the date, time, and inside and outside temperatures.
![alt text](https://imgur.com/ADMWrdF.png)

Starting the simulation.
![alt text](https://imgur.com/4nnZK3M.png)

## **Special Thanks**
* [JFoenix](http://www.jfoenix.com/)
