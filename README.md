# ElementCronParser
**Description:**
- Test project for Element

- Project created in IntelliJ and written in Kotlin

- Project contains stdin support

There are a set of tasks, that as specified by the config input file.  The purpose of this project is based on the current time passed as a parameter, to ascertain when each task will run next. 

I have approached the development of this test, much in the way I would approach my android development. 

The code is written with a clean architecture in mind.  I have created use cases for each task, all of which are covered with unit tests.   
I have created a sepeartion layer between the use case classes and the main class with a "CronViewModel" class.  
The logic on which use case to call is contained within the view model class.  Taking this approach allows the logic to be covered with unit tests. 
ConfigListItemMapper is added to allowing mapping from each line of the input file to a data class (ConfigItem)

All classes that are testable have 100% code coverage, this can be verified by running tests in intellij with coverage. 

The code is structured in such a way that uses manual dependency injection.  Ordinarily this would be done using koin or some other dependency injection library. 

The code contains zero comments and this is intentional. 

**Instructions to run from terminal:**
- Navigate into projet folder ElementCronParser via terminal/command line
- Using Gradle to run the project, use the following command:


  `./gradlew run --args="<current time> <path to config txt file>"` seperated only by a space
  
  e.g. `./gradlew run --args="16:40 /Users/joebloggs/document/testconfig.txt"`
