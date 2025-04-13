### Website Launcher: Sample Kotlin App

This is a simple Android app built in **Kotlin** using **Jetpack Compose**, which fetches a list of popular websites from a provided JSON API and displays them in a scrollable, interactive list. 

Although I had no prior experience with Kotlin or Jetpack Compose, my familiarity with React and Java helped me quickly get comfortable with the development process.

### How I approached the task:
I started with reviewing official docs such as  [developer.android.com/compose](https://developer.android.com/compose). To structure the app properly, I also looked at existing projects to understand how components like ViewModels are typically used and why, and followed my structure in a similar way.

For data fetching, I explored several options and chose Retrofit with Moshi due to their ease of integration and concise JSON parsing. In addition, I used Coil for image loading in Compose, which required minimal setup and worked well with the URLs provided in the dataset.

I am particularly proud of quickly adopting the fundamentals of Kotlin and Jetpack Compose, and applying them to build a functional, interactive app.

Current features include:
- Displaying a scrollable list of websites with icons
- Cards can be clicked to open the website with the default app browser
- A sort button to toggle alphabetical order (ascending/descending) 

If I had more time, I would:
- Add more robust error handling and unit tests
- Implement functionality for the 4 buttons in the bottom bar
  - Implement a search bar to filter the websites
  - Implement a dark mode toggle
  - Implement an add button to add custom websites
  - Implement a refresh button to re-fetch from the API
  - Implement persistable user preference (e.g. sort order, added websites, custom API)
 
<p align="center">
<img src="https://github.com/user-attachments/assets/535de492-6d33-49b4-8a03-310c988dfbbf" width="800"/>
</p>

