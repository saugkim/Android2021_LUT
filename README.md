## Mobile application

### About project app     

App name:  
SimpleNotepad: about create note and save it internally.


Tools used:  
Android Studio IDE version 3.6.1, Git,    
<br>
build.gradle(app)  
```
android {
    compileSdkVersion 29
    buildToolsVersion "29.0.3"
    defaultConfig {
        applicationId "org.lut.simplenotepad"
        minSdkVersion 18
        targetSdkVersion 28
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}
```

Two basic activities (Views)      
  1. MainActivity      
      - can write save note, create new note, go to List by menu
  2. NoteSelectActivity    
      - can edit modify remove a selected note, create new note

RecyclerView to show list of notes  
File writing and saving internally  


### How To RUN  
1. Download apk file to android device(phone)
2. Install the app 
3. Open and run the app
4. Write something in main screen (first screen)
5. Save button is right bottom corner
6. To create new note, use button in left bottom corner (+ icon)
7. To see the all view, click top right menu in toolbar and click "List of Notes"
8. In Note Select screen, there are all notes saved in the internal storages
9. Each note has date information (firstly saved date, Day_Month_Year format) and contents(full content) right below date
10. Select (click) item to edit 
11. Long click(+2 seconds) opens confirm window to remove item action, can delete or not
12. In Note Select view to create note, use button in right bottom corner  


### Download apk
you can download apk from below link  
[app-release.apk](https://drive.google.com/file/d/1gDkyhOO0ezmU08OeC18uJFCkkxcB1zcw/view?usp=sharing)  
  

### Demo video Youtube
  
