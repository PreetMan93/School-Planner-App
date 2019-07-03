# Logic

## Login
- The **LoginViewModel** is the class that interacts with the activity and will pawn off requests to other classes
- The **FormStateManager** monitors the user input to make sure everything they are inputting is valid
  giving them real time up to date knowledge.
- Have two Custom exceptions which will be handled by the UI and toast messages to the user upon them triggering
- The **LoginViewModel** is the main controller it sends login request to the singleton class of **Repository**,
  which will be used to dynamically display user events because it stores the logged in user. The logged in user is
  assigned by **Repository** calling the **DataSource** class which handles the interaction with the DB. Upon success or
  failure a Result object is returned either containing a logged in user or Exception and then **Repository** either
  sets the logged in user or throws the exception up to **LoginViewModel** which then will throw it to the Activity page
  to be dealt with appropriately.
- The **LoginViewModelFactory** creates the **LoginViewModel** and injects the correct DB, either stub or real
- Registration is in **LoginViewModel** because I would consider Registration a login. Only difference
  being that the information is saved rather then fetched.
