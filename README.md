# Mobile Application 

**About project app**  
<br>
App name:  
SimpleNotepad: about create note and save it internally.

<br>
Tools used:  
Android Studio IDE version 3.6.1, Git,    
  
  
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
  2. NoteSelectActivity  

RecyclerView to show list of notes  
File writing and saving internally    

<br>

**How To RUN**  


