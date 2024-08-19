# language_libtray_android_java
for use this 
```
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        load language which store in shared preference when the app start
        LanguageManager languageManager=new LanguageManager(this);
        languageManager.lodeLanguage();
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        /*
         * on click on the change language button
         * and call the show language dialog function
         * */
        binding.changeLanguage.setOnClickListener(v -> {
            languageManager.showLanguageDialog();

        });

    } }
```
