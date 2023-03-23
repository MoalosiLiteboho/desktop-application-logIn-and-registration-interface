## Desktop Application LogIn Interface 
# Desktop Application LogIn Interface

This application helps the use to sign-in, and it has the following features
* It checks if the user filled email and password before trying to sign-in, if not it will notify the user to do so
* It checks if the email entered is in an email format, if not it notify the user to  do so before attempting to sign-in
* It encrypts password before it checks if there is a user with email and password of that kind in the database
* It runs Not found exception that extents from runtime exception if the user email and password are incorrect as the sql statement will come with empty user details
* if the user enter the correct credentials it will print the user id and user full names

All the above functionalities are triggered when the user press logIn button. Bellow it is graphical user interface(GUI) of logIn.

## LogIn GUI
![Screenshot from 2023-03-22 20-18-00](https://user-images.githubusercontent.com/112495633/227000232-a0b1bb49-75d8-4886-bdcd-4dadc1dd7fe1.png)

