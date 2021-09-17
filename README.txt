*************************************
*		READ ME		    *
*************************************

INSTRUCTIONS ON HOW TO RUN THE APPLICATION
*******************************************

- First, run the MongoDB server by installing MongoDB on your device and running mongod.exe through Command Prompt. 

- Then open the 'CloudedMinds_Backend' project in IntelliJ and run the server in 'bin\www'.

- Then, open the 'CloudedMinds' project in Android Studio. Choose an emulator that is at least 6 inches in height,   for example the "Pixel_3a_API_30_x86" and run the app on the emulator.

- Use the app as you wish, you will need to create an account to be able to access its content.

- Visit 'http://localhost:3000/' on Google Chrome while the server is running to view the CloudedMinds website for medical professionals.


HOW TO RUN APP ON A PHYSICAL SMARTPHONE
****************************************
- The app uses Retrofit which only runs the app on the emulator because the baseURL is set as that of the emulator.

- If you wish to run the app on your smartphone, you will require a 6inch minimum length device.

- In the 'CloudedMinds' Android Studio project, visit the 'RetrofitClient.java' and change the baseURL in line 20      to use your PC IP Address as demonstrated in the comment.

- In the 'Diary.java' file, do the same to line 71.

- Connect your device to your PC and run the project on your device.

