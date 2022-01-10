# Android-Clean-Architecture-MVVM-Koin-Room-Rxjava-DataBinding
This project aims to demonstrate the usage of Android-Clean-Architecture with MVVM-Koin-Room-Rxjava-DataBinding in Kotlin.

### Project Structure
![](screenshots/project-structure.png)

### Developed With
* MVVM
* KOIN
* ROOM
* RXJAVA
* DATA-BINDING

## Demo
### Registration
<img src="screenshots/Register.gif" width="200" height="350">

### Login
<img src="screenshots/Login.gif" width="200" height="350">

## Clean Architecture
MVVM with Clean Architecture is pretty good in such cases. It goes one step further in separating the responsibilities of your code base. It clearly abstracts the logic of the actions that can be performed in your app.

![](screenshots/Clean-Archtecture.png)

### Data
For this project, our data layer contains database and DAO classes, repositoriess.

### Domain
For this project, domain layer contains model objects, use cases, and repository interfaces

### Presenters
 This layer contains UI(Activities and ViewModels)
 
 ### di
 This layer contains dependency injection related items. We maintain all the injection module here.
 
## License
```
Copyright 2022 Thirumoorthy Ganesan 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
