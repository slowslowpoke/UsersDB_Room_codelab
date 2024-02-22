# Users DB 

- Basic database imitation that uses **Room** library to store data about users in device's SQLite database. <br>
- Requests to the DB are made by **suspend functions** inside **coroutines**. <br>
- Room's DAO methods return **LiveData** which are observed in the UI and updates to the UI are made accordingly. <br>
- Project follows **MVVM architectural pattern** using repository, view model, model classes for better app structure. <br>
  <br><br>
<p align="center">
<img src="screenshots/fulllist.png" width="200" hspace="10"/>
<img src="screenshots/edituser.png" width="200" hspace="10"/>
<img src="screenshots/deleteallusers.png" width="200" hspace="10"/>
</p>