
13 MAY 2021 (TO)  
how to use ImageButton, there are some images(like icons) available from Android Studio 
  - ic_menu_save for file saving button icon...
<br />  
<br />  

17 May (MA)  
debuggin tip: print something in event log window  
  using d(tag: "TAG", msg: "messsage: " + variableName)    
   - start with d (import android.util.Log.d)  
   - see logcat search with tag
```
   SharedPreferences preferences = getSharedPreferences(key, Context.MODE_PRIVATE)
   String savedName = preferences.getString("savedProductName", "Not exist")
   d(tag: "hello", msg: "saved message is: " + savedName)
```
<br />  
<br />
18 May (TI)  
-QuickNote app-   
using AlertDialog inside on button click event listener  

```  
saveIButton.setOnClickListener(new View.OnClickListener() {
    
    @Override
    public void onClick(View v) {
        AlertDialog ad = new Builder(MainActivity.this).create();
        final EditText saveFileName = new EditText(MainActivity.this);
        
        ad.setView(saveFileName);
        ad.setMessage("Save File");
        ad.setButton(AlertDialog.BUTTON_POSITIVE, "Save", 
                             new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    FileOutputStream fout = openFileOutput(saveFileName.getText().toString() + ".txt", MODE_APPEND);
                    fout.write(text.getText().toString().getBytes());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error occurred: " + e, Toast.LENGTH_LONG).show();
                }
            }
        });

        ad.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", 
                             new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        ad.show();
});
```
       
     
