## Software Development Skills: Mobile 2020-21

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
        applicationVariants.all{
            variant ->
                variant.outputs.each{
                    output->
                        def name = "Note_R${variant.versionName}.apk"
                        output.outputFileName = name
                }
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

<br>

### How To RUN  
1. Download apk file to android device(phone)
2. Install the app 
3. Open and run the app
4. Write something in main screen (first screen)
5. Save button is right bottom corner
6. To create new note, use button in left bottom corner (+ icon)
7. To see the all view, click top right menu in toolbar and click "List of Notes"
8. In Note Select screen, there are all notes saved in the internal storages
9. Each note has date information (firstly saved date, Day_Month_Year format) and contents (first few lines only) right below date
10. Select (click) item to see whole content and to modify 
11. Long click(+2 seconds) opens confirm window to remove item action, can delete or not
12. In Note Select view to create note, use button in right bottom corner  

<br>

### Download apk
you can download apk from below link  
[Note_R1.0.apk](https://drive.google.com/file/d/1Y1l_bqSE4Jz-TjE1M8bhRQdgjhDFJnhI/view?usp=sharing)  
  
<br>

### Demo video Youtube  
link1: https://youtu.be/TBwV-C7bRuc  
link2: https://youtu.be/RQopmmbZXLY  
