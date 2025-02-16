“Interesting facts about numbers”. The application should allow the user to enter the number about which he wants to know an interesting fact, as well as - to randomly generate a number and get a fact about it. Application must contain 2 screens.

Main screen It should be divided into two parts, the upper part is a field for entering a number, the button "Get fact" and the button "Get fact about random number", the lower part - display the history of user requests, each history element should show the number the user searched for and fact preview (everything that fits in one line), clicking on the element should open the 2nd screen.

2nd screen Must display to the user the number and full text of the fact about the previously selected number. It should also be possible to return to the previous screen.

Task description To get information about the number - use api http://numbersapi.com For example, for the number "10" - the query http://numbersapi.com/10 To get the fact about the random number - http://numbersapi.com/random/math For “http” queries don’t forget to add android:usesCleartextTraffic="true" to your Manifest file.

<img width="217" alt="Screenshot 2025-02-16 at 17 43 53" src="https://github.com/user-attachments/assets/801b0a68-ca64-44bd-80c1-d9b6ac8fc704" />

<img width="209" alt="Screenshot 2025-02-16 at 17 44 28" src="https://github.com/user-attachments/assets/c66b9680-d21b-43d5-9ce6-f8625f4f5e12" />
