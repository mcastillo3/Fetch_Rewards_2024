# This is a take-home assessment for Fetch Rewards

Languages Used: Kotlin, Android XML, Groovy, SQL
Tech Stack: MVVM
Dependencies: Koin, Room, Retrofit, Coroutines, JetPack Nav
Purpose: Display a list of items to the user based on the following requirements:
  Display all the items grouped by "listId"
  Sort the results first by "listId" and then by "name" when displaying.
  Filter out any items where "name" is blank or null.

# How it works
The application fetches data in an array of items from a REST API, https://fetch-hiring.s3.amazonaws.com/hiring.json.
Invalid entries where the item name is blank or null are filtered out. The data is then displayed in a sorted list by
list ID and name. I used Retrofit for API calls. Additionally, the data is stored using Room for offline caching. Koin
is used for dependency injection. There is also added functionality to swipe-to-refresh the data.

When the app is opened, the user is presented with a sorted list of items from the REST API. From the menu, the user can
select to view the sorted data from the web, the local storage, or the unsorted data from the web.
