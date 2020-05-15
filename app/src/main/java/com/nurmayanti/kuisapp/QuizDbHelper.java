package com.nurmayanti.kuisapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                QuizContract.CategoriesTable.TABLE_NAME + "( " +
                QuizContract.CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionTable.TABLE_NAME + " ( " +
                QuizContract.QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuizContract.QuestionTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuizContract.QuestionTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuizContract.QuestionTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                QuizContract.CategoriesTable.TABLE_NAME + "(" + QuizContract.CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("SAINTEK");
        insertCategory(c1);
        Category c2 = new Category("SOSHUM");
        insertCategory(c2);
        Category c3 = new Category("TPA");
        insertCategory(c3);
    }

    public void addCategory(Category category) {
        db = getWritableDatabase();
        insertCategory(category);
    }

    public void addCategories(List<Category> categories) {
        db = getWritableDatabase();

        for (Category category : categories) {
            insertCategory(category);
        }
    }

    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(QuizContract.CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Hasil perkalian semua solusi riil dari persamaan x5−4x4−2x3+39x2 − 54x = 0 adalah...",
                "-54", "-4", "0", 3,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q1);
        Question q2 = new Question("Jika f(x) = |x| + 10, berapakah nilai x yang memenuhi f(x) = f(−x)? ",
                "Hanya 10 dan -10 ", "Semua bilangan real x ", "Semua bilangan real x selain 10 dan -10 ", 2,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q2);
        Question q3 = new Question("Ketika 4x2 + 6x + F dibagi (x + 1) bersisa 2. Maka nilai F adalah...",
                "4", "6", "12", 1,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q3);
        Question q4 = new Question("Manakah pernyataan berikut yang benar? I. sin(−θ) = −sinθ II. cos(−θ) = −cosθ III. tan(−θ) = −tanθ, dimana tanθ terdefinisi. ",
                "Hanya I", "Hanya I dan II", "Hanya I dan III", 3,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q4);
        Question q5 = new Question("Di sebuah segitiga PQR, ∠Q = 90° dan ∠R = 27° serta PQ = 9, maka panjang sisi miring segitiga PQR adalah... ",
                "4,1", "19,8", "10,1", 2,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q5);
        Question q6 = new Question("Ketika 4x2 + 6x + F dibagi (x + 1) bersisa 2. Maka nilai F adalah... ",
                "4", "10", "6", 1,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q6);
        Question q7 = new Question("Jika −9x + 2000 merupakan sisa pembagian polinomial P(x)oleh x2 − x − 2, maka sisa \n" +
                "pembagian P(x)oleh (x + 2) adalah... ",
                "2016", "2017", "2018", 3,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q7);
        Question q8 = new Question("Banyaknya solusi real x dari persamaan \n" +
                "3(12+log3(cosx−sinx)) + 2(log2(cosx+sinx)) = √2 \n" +
                "Adalah... \n ",
                "1", "2", "3", 2,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q8);
        Question q9 = new Question("Jika x mod y memiliki arti sisa dari x saat dibagi y, maka (61mod7) − (5mod5) = ...",
                "4", "2", "5", 3,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q9);
        Question q10 = new Question("Jika, untuk semua bilangan real x, fungsi g(x) didefinisikan sebagai berikut \n" +
                "f(x){2,x ≠ 13 4,x = 13 Maka nilai dari f(15) − f(14) = ⋯ \n",
                "-1", "2", "0", 3,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q10);
        Question q11 = new Question("Manakah yang merupakan besaran vektor? \n I) kecepatan II) kelajuan III) perpindahan IV) jarak V) gaya VI) percepatan ",
                "I,III,V,VI", "I,III,IV,V", "II,III,IV,VI", 1,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q11);
        Question q12 = new Question("Menurut Hukum 1 Newton, jika sebuah balok didorong di atas permukaan licin hingga \n" +
                "mencapai kecepatan 0,2 m/s kemudian dilepaskan, benda tersebut akan... \n",
                "Bergerak dengan jarak yang cukup jauh kemudian berhenti", "Tetap bergerak pada kecepatan yang sama 0,2 m/s", "Bergerak dengan jarak yang dekat kemudian berhenti", 2,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q12);
        Question q13 = new Question("Jika kelajuan dan massa dari sebuah benda besarnya dibuat menjadi dua kalinya, maka \n" +
                "pernyataan manakah yang benar? A) Momentum dari benda menjadi dua kalinya B) Energi kinetik dari benda menjadi dua kalinya C) Momentum dari benda menjadi satu per empat kalinya D) Energi kinetik dari benda menjadi delapan kalinya E) Momentum dan energi kinetik benda menjadi empat kalinya ",
                "A", "D", "B", 2,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q13);
        Question q14 = new Question("Berapa besar usaha yang dilakukan saat menarik sebuah kotak di atas tanah sejauh 20 m \n" +
                "oleh gaya 20 N yang membentuk sudut 10° dengan tanah? ",
                "400 cos(10°) ", "4000 J ", "400 tan(10°) ", 1,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q14);
        Question q15 = new Question("Berapa lama waktu yang dibutuhkan dari sebuah gelombang berfrekuensi 0,2 Hz dengan \n" +
                "panjang gelombang 2 m untuk merambat pada tali sepanjang 4m? ",
                "10", "0,8", "4", 1,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q15);
        Question q16 = new Question("Manakah dari pernyataan dibawah ini yang benar tentang cahaya? \n" +
                "I) merupakan gelombang elektromagnetik II) tidak merambat pada ruang hampa III) Kecepatannya ",
                "I dan II", "II dan III", "I dan III", 3,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q16);
        Question q17 = new Question("Sebuah electric motor mengubah: \n" +
                "A) Energi mekanik menjadi energi listrik B) Energi listrik menjadi energi mekanik C) Energi potensial menjadi energi listrik D) Tegangan tinggi menjadi tegangan rendah E) Tegangan rendah menjadi tegangan tinggi \n",
                "D) Tegangan tinggi menjadi tegangan rendah", "A) Energi mekanik menjadi energi listrik", "B) Energi listrik menjadi energi mekanik", 3,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q17);
        Question q18 = new Question("Sebuah bola dilemparkan vertikal ke atas dengan kecepatan 10m/s. Hitunglah ketinggian \n" +
                "maksimum yang dicapai oleh bola sebelum jatuh kembali ke tanah! (g = 10 m/s2) \n",
                "10m", "5m", "15m", 2,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q18);
        Question q19 = new Question("Molekul mana dibawah ini yang memiliki struktur stabil berupa resonansi transfer \n" +
                "antarelektron ikat pi... \n",
                "Benzena", "Hydrochloric acid", "Hydrogen gas", 1,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q19);
        Question q20 = new Question("Sebuah sampel gas terperangkap pada manometer dan ujung atasnya dibiarkan terbuka seperti \n" +
                "gambar berikut. Cairan merkuri bergerak menuju ketinggian yang berbeda setelah terjadi \n" +
                "kesetimbangan. Jika tekanan dari gas didalam manometer adalah 815 torr, berapa besar dari \n" +
                "tekanan atmosferik... \n",
                "740 torr", "760 torr", "750 torr", 3,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q20);
        Question q21 = new Question("Berikut merupakan data entalpi pada berbagai reaksi : \n" +
                "A +B →2C ∆H = −500 kJ, \n" +
                "D +2B →E ∆H = −700 kJ, \n" +
                "2D +2A →F ∆H = +50 kJ \n" +
                "Berapa entalpi total dari reaksi F +6B →2E +4C ... \n",
                "+2,350 kJ", "−2,450 kJ", "−1,100 kJ", 2,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q21);
        Question q22 = new Question("Tiga jenis gas dicampur dalam suatu wadah tertutup. Wadah tersebut terdiri dari 0,3 mol gas \n" +
                "A, 0,4 gas B, dan 0,3 mol gas C. Tekanan total wadah adalah 660 torr. Berikut merupakan \n" +
                "pertanyaan yang benar adalah... \n",
                "Tekanan parsial gas A dan C adalah masing masing 198 torr.", "Tekanan parsial gas C adalah 220 torr.", "Tekanan parsial gas A adalah 264 torr.", 1,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q22);
        Question q23 = new Question("Perhatikan reaksi berikut : \n" +
                "[CaSO4(s) ←→ Ca2+(aq) +SO42−(aq)] Berikut yang akan terjadi jika sodium sulfate ditambahkan ke dalam larutan jenuh CaSO4 yang \n" +
                "berada pada kesetimbangan... \n",
                "Konsentrasi ion calcium akan meningkat", "Kelarutan calcium sulfate akan menurun", "Nilai Ksp akan berubah", 2,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q23);
        Question q24 = new Question("Kalor pembakaran gas asetilena ini adalah 320 kkal/mol. Jika dalam suatu proses digunakan 160 \n" +
                "gr kalsium karbida dan dengan asumsi bahwa 60% berat CaC2 yang bereaksi, pada pembakaran asetilena akan dihasilkan kalor sebanyak .... \n (Ar C = 12; Ca = 40)",
                "640 kkal", "960 kkal", "480 kkal", 3,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q24);
        Question q25 = new Question("Atom X memiliki tiga isotop umum yang sering dijumpai : X-48, X-49, dan X-51. Jika \n" +
                "Komposisi relatif suatu isotop dibanding total adalah berturut-turut 42%, 38%, dan 20%, maka \n" +
                "massa atom X adalah... \n",
                "48.98", "48.62", "50.67", 1,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q25);
        Question q26 = new Question("Reduksi besi (III) oksida dengan gas CO menghasilkan besi menurut persamaan reaksi: \n" +
                "Fe2O3(s) + 3CO(g) → 2Fe(s) + 3CO2(g). \n" +
                "Untuk menghasilkan 5,6 kg besi dibutuhkan besi(III) oksida sejumlah .... \n" +
                "(Ar C = 12, O = 16, Fe = 56; Mr Fe2O3 = 160) \n",
                "32 kg", "16 kg", "160 kg", 2,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q26);
        Question q27 = new Question("Manakah pernyataan dibawah ini yang benar mengenai geometri molekul suatu molekul... \n" +
                "I. CH4 memiliki bentuk molekul trigonal pyramidal II. BF3 memiliki bentuk molekul trigonal planar III. XeF6 memiliki bentuk molekul tetrahedral \n",
                "I", "II", "III", 2,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q27);
        Question q28 = new Question("Yang manakah dibawah ini yang tidak akan berubah setelah perlakuan penambahan katalis \n" +
                "pada suatu reaksi setimbang... \n",
                "I, II, dan III", "II dan III", "III", 1,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q28);
        Question q29 = new Question("Berikut ini yang merupakan penyebab eutrofikasi di danau adalah...",
                "Kurangnya sinar matahari", "Meningkatnya oksigen", "Meningkatnya nutrien", 3,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q29);
        Question q30 = new Question("Kelas Mamalia memproduksi urea. Fungsi dari urea adalah...",
                "Mencegah amonia menumpuk dalam tubuh", "Meningkatkan pH darah", "Menyediakan energi pada beberapa sel dan jaringan", 1,
                Question.DIFFICULTY_EASY, Category.SAINTEK);
        insertQuestion(q30);
        Question q31 = new Question("Jika −9x + 2000 merupakan sisa pembagian polinomial P(x)oleh x2 − x − 2, maka sisa \\n\" +\n" +
                "                \"pembagian P(x)oleh (x + 2) adalah... ",
                "2018", "2016", "2015", 1,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q31);
        Question q32 = new Question("Jika, untuk semua bilangan real x, fungsi g(x) didefinisikan sebagai berikut \\n\" +\n" +
                "                \"f(x){2,x ≠ 13 4,x = 13 Maka nilai dari f(15) − f(14) = ⋯ \\n",
                "0", "1", "2", 1,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q32);
        Question q33 = new Question("Jika x mod y memiliki arti sisa dari x saat dibagi y, maka (61mod7) − (5mod5) = ...",
                "4", "5", "2", 2,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q33);
        Question q34 = new Question("Kalor pembakaran gas asetilena ini adalah 320 kkal/mol. Jika dalam suatu proses digunakan 160 \\n\" +\n" +
                "                \"gr kalsium karbida dan dengan asumsi bahwa 60% berat CaC2 yang bereaksi, pada pembakaran asetilena akan dihasilkan kalor sebanyak .... \\n (Ar C = 12; Ca = 40)",
                "660 kkal", "520 kkal", "480 kkal", 3,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q34);
        Question q35 = new Question("Jarak dari partikel yang bergerak sepanjang garis lurus dari keadaan diam dapat dinyatakan \n" +
                "dengan persamaan l = 2+ 4t + 5 t2 dengan l dalam meter dan t dalam sekon. Berapakah jarak yang ditempuh partikel diantara detik ke 4 dan 6? \n",
                "108m", "206m", "304m", 1,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q35);
        Question q36 = new Question("Sebuah partikel bergerak sepanjang sumbu x. Setelah bergerak 2 s kedudukan partikel di x₁ = 20 m, dan 4 sekon kemudian kedudukan partikel di x₂ = 5 m. Hitunglah perpindahan dan kecepatan rata-rata partikel pada selang waktu tersebut!  \n",
                "-7,5 m/s", "-5,0 m/s", "-10,5 m/s", 1,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q36);
        Question q37 = new Question("Sebuah bola pejal berputar dengan kecepatan sudut yang dinyatakan dengan persamaan ω = (8t - 4t²), dalam rad/s. Jika massa bola 2 kg dan jari-jarinya 20 cm, maka momen gaya yang bekerja pada bola saat t = 0,5 s adalah?  \n",
                "0,156 Nm", "0,128 Nm", "0,216 Nm", 2,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q37);
        Question q38 = new Question("Sebuah partikel bergerak dengan persamaan vektor posisi r = (2t² - t)i + (t³ + t)j. Besar kecepatan partikel saat t = 1 s adalah?",
                "7,5 m/s", "15 m/s", "5 m/s", 3,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q38);
        Question q39 = new Question("Sebuah sampel gas terperangkap pada manometer dan ujung atasnya dibiarkan terbuka seperti \n" +
                "gambar berikut. Cairan merkuri bergerak menuju ketinggian yang berbeda setelah terjadi \n" +
                "kesetimbangan. Jika tekanan dari gas didalam manometer adalah 815 torr, berapa besar dari \n" +
                "tekanan atmosferik... \n",
                "810 torr", "660 torr", "750 torr", 3,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q39);
        Question q40 = new Question(" Perhatikan reaksi berikut : \n" +
                "[CaSO4(s) ←→ Ca2+(aq) +SO42−(aq)] Berikut yang akan terjadi jika sodium sulfate ditambahkan ke dalam larutan jenuh CaSO4 yang \n" +
                "berada pada kesetimbangan... \n",
                "Kelarutan calcium sulfate akan menurun", "Reaksi akan bergeser ke kanan", "Nilai Ksp akan berubah", 1,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q40);
        Question q41 = new Question("Gas asetilena yang digunakan sebagai bahan bakar las karbid dibuat dari kalsium karbida dan \n" +
                "air menurut reaksi: \n" +
                "CaC2 (s) + 2H2O (l) → Ca(OH)2 (aq) + C2H2 (g). \n" +
                "Kalor pembakaran gas asetilena ini adalah 320 kkal/mol. Jika dalam suatu proses digunakan 160 \n" +
                "gr kalsium karbida dan dengan asumsi bahwa 60% berat CaC2 yang bereaksi, pada pembakaran asetilena akan dihasilkan kalor sebanyak .... \n" +
                "(Ar C = 12; Ca = 40) \n",
                "960 kkal", "480 kkal", "720 kkal", 2,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q41);
        Question q42 = new Question("Atom X memiliki tiga isotop umum yang sering dijumpai : X-48, X-49, dan X-51. Jika \n" +
                "Komposisi relatif suatu isotop dibanding total adalah berturut-turut 42%, 38%, dan 20%, maka \n" +
                "massa atom X adalah... \n",
                "48,98", "46,67", "50,67", 1,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q42);
        Question q43 = new Question("Reduksi besi (III) oksida dengan gas CO menghasilkan besi menurut persamaan reaksi: \n" +
                "Fe2O3(s) + 3CO(g) → 2Fe(s) + 3CO2(g). \n" +
                "Untuk menghasilkan 5,6 kg besi dibutuhkan besi(III) oksida sejumlah .... \n" +
                "(Ar C = 12, O = 16, Fe = 56; Mr Fe2O3 = 160) \n",
                "32 kg", "16 kg", "8kg", 2,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q43);
        Question q44 = new Question("Human immunodeficiency virus (HIV) merupakan retrovirus (virus RNA) yang \n" +
                "mengintegrasikan materi genetiknya ke dalam genom inang menggunakan....",
                "DNA polimerase", "Reverse transkriptase", "Helikase", 2,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q44);
        Question q45 = new Question("(I) Mengkonversi karbondioksida menjadi senyawa berkarbon empat. \n(II) Stomata menutup pada malam hari \n(III) Meningkatkan fotorespirasi Nomor yang sesuai untuk cara adaptasi tumbuhan yang menempati daerah hangat dan kering adalah.....",
                "I saja", "I dan II", "III saja", 1,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q45);
        Question q46 = new Question("Organimse dengan karakteristik simetri radial dan memiliki eksoskeleton diklasfikasikan ke \n" +
                "dalam filum...",
                "Echinodermata", "Mollusca", "Arthropoda", 1,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q46);
        Question q47 = new Question("Proses seluler yang bertanggungjawab terhadap perubahan tersebut adalah....",
                "Penutupan stomata", "Pelambatan fotosintesis", "Penurunan tekanan vakuola", 3,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q47);
        Question q48 = new Question("Struktur berikut yang memungkinkan tumbuhan dikotil dapat memulai fotosintesis pada saat \n" +
                "masa perkecambahan adalah...",
                "Endosperma", "Kotiledon", "Meristem apikal", 2,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q48);
        Question q49 = new Question("Ketika dilakukan percobaan di padang rumput, di daerah yang ditambahkan fosfor menunjukkkan peningkatan pertumbuhan rumput, sedangkan di daerah yang ditambahkan nitrogen tidak menunjukkan pertumbuhan. Nutrient pembatas pada ekosistem padang rumput ini adalah....",
                "Fosfor, karena penambahan nutrien ini menghasilkan peningkatan pertumbuhan rumput", "Nitrogen, karena penambahan nutrien ini tidak mempengaruhi pertumbuhan rumput", "Nitrogen dan fosfor, karean penambahan nutrien ini meningkatkan pertumbuhan rumput", 1,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q49);
        Question q50 = new Question("Berikut ini merupakan karakteristik yang paling dibutuhkan untuk meningkatkan \n" +
                "kemungkinan lolos seleksi alam adalah....",
                "Individu dengan kemampuan bertahan terhadap penyakit", "Individu dengan kemampuan mencari dan menemukan makanan", "Individu dengan kemampuan memproduksi banyak keturunan", 3,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q50);
        Question q51 = new Question("Beberapa tumbuhan memiliki nilai ekonomi yang tinggi karena memiliki kayu yang\n" +
                "harum. Salah satu tumbuhan tersebut adalah cendana. Cendana berbau harum karena ....",
                "batangnya ditumbuhi lumut kerak sehingga menghasilkan senyawa berbau harum", "batangnya mengandung minyak atsiri yang berbau harum", "mempunyai jamur yang menghasilkan senyawa berbau harum", 2,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q51);
        Question q52 = new Question("Pengelompokkan bakteri berdasarkan kelompok bakteri gram-positif dan gram-negatif\n" +
                "didasarkan pada ....",
                "komponen senyawa penyususn organel sel", "permeabilitas membran sel", "komposisi senyawa penyusun dinding sel", 3,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q52);
        Question q53 = new Question("Kekurangan unsur kimia berikut dapat menyebabkan tanaman mengalami klorosis\n" +
                "yang ditandai dengan menguningnya daun, yaitu ....",
                "magnesium", "kalium", "natrium", 1,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q53);
        Question q54 = new Question("Pindah silang pada meiosis yang ditandai dengan terbentuknya kiasmata terjadi pada\n" +
                "tahapan ...",
                "diploten", "diakinesis", "haploten", 1,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q54);
        Question q55 = new Question("Dua buah kawat konduktor yang sejajar dan berjarak L = 1 m dipasang membentk sudut =\n" +
                "30° terhadap bidang horizontal. Ujung bawah kedua kawat terhubung dengan sebuah\n" +
                "resistor R = 3 . Sebuah batang konduktor dengan masa m bergeser turun di sepanjang rel,\n" +
                "tanpa kehilangan kontak dengan rel sehingga rel dan batang membentuk suatu rangkaian\n" +
                "tertutup. Pada daerah tersebut terdapat medan magnetik seragam yang bersarnya B = 2 T\n" +
                "dan berarah horizontal. Jika batang turun dengan laju konstanta v = 3 m/s, maka batang m\n" +
                "adalah ....",
                "0,4 kg", "0,2 kg", "0,8 kg", 2,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q55);
        Question q56 = new Question("Sejumlah atom hidrogen dipapari gelombang elektromagnetik hingga tereksitasi. Atom-\n" +
                "atom ini kemudian memancarkan gelombang elektromagnetik sehingga turun ke keadaan\n" +
                "\n" + "dasar. Panjang gelombang terbesar dua garis spektral yang dihasilkan adalah ....",
                "121,6 nm dan 102,6 nm", "120,4 nm dan 100,4 nm", "114,4 nm dan 94,4 nm", 1,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q56);
        Question q57 = new Question("Dua balok kayu kecil A dan B terapung di permukaan danau. Jarak keduanya adalah 150\n" +
                "cm. Ketika gelombang sinusoida menjalar pada permukaan air teramati bahwa pada saat t =\n" +
                "0 detik, balok A berada di puncak, sedangkan balok B berada di lembah. Keduanya\n" +
                "\n" + "dipisahkan satu puncak gelombang. Pada saat t = 1 detik, balok A berada di titik setimbang\n" +
                "pertama kali dan sedang bergerak turun. Manakah pernyataan yang benar tentang\n" +
                "gelombang pada permukaan air tersebut?",
                "Pada saat t = 1 detik, balok B berada di titik setimbang dan sedang bergerak turun.", "Frekuensi gelombang adalah 0,25 Hz.", "Balok A akan kembali berada di puncak pada saat t = 4,5 detik.", 2,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q57);
        Question q58 = new Question("Komponen pada sitoplasma yang ditemukan pada sel prokariotik maupun eukariotik,\n" +
                "yang tersusun atas serat protein adalah ....",
                "histon", "nukleosom", "sitoskeleton", 3,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q58);
        Question q59 = new Question("Sebuah sistem mekanik diperlihatkan pada gambar. Sudut kemiringan = 30o dan bidang\n" +
                "miring licin. Sistem berada dalam keadaan setimbang serta massa katrol dan massa pegas\n" +
                "diabaikan. Jika setiap massa dijadikan dua kali semula, salah satu cara yang dapat dilakukan\n" +
                "agar sistem tetap setimbang adalah ....",
                "konstanta pegas menjadi dua kali semula dan pertambahan panjang pegas tetap", "konstanta pegas tetap dan pertambahan panjang pegas menjadi 4 kali semula", "konstanta pegas tetap dan pertambahan panjang pegas menjadi 2 kali semula", 3,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q59);
        Question q60 = new Question("Seorang pemain biola menarik senar dengan gaya 4 N sehingga senar bertambah\n" +
                "panjang 4 mm. Besar usaha yang dikerjakan oleh pemain biola tersebut adalah ...",
                "2 mJ", "4 mJ", "8 mJ", 3,
                Question.DIFFICULTY_MEDIUM, Category.SAINTEK);
        insertQuestion(q60);
        Question q61 = new Question("Sisa pembagian p(x) = x3 + ax2 + 3bx + 21 oleh x2 + 9 adalah b. Jika p(x) dibagi x + 1\n" +
                "bersisa 4b + 1, maka a + b = ...",
                "3", "4", "5", 3,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q61);
        Question q62 = new Question("Jika garis singgung kurva y = 9 – x2 di titik P(a, b) dengan b > 0 memotong sumbu x di\n" +
                "titik Q(-5, 0), maka ab adalah...",
                "-4", "-8", "-12", 2,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q62);
        Question q63 = new Question("Diketahui (an) dan (bn) adalah dua barisan aritmetika dengan a1 = 5, a2 = 8, b1 = 3,\n" +
                "dan b2 = 7. Jika A = {a1, a2, ... , a100} dan B = {b1, b2, ... , b100}, maka banyaknya\n" + "anggota A C B adalah ….",
                "25", "22", "20", 1,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q63);
        Question q64 = new Question("Diketahui kubus ABCD.EFGH dengan panjang rusuk 2√2. Jika titik P ditengah-tengah AB dan titik Q ditengah-tengah BC, maka jarak antara H dengan garis PQ adalah ... cm.",
                "√15", "√17", "3√2", 2,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q64);
        Question q65 = new Question("Jika periode fungsi f(X) = 2 cos (ax) + a adalah π/3, maka nilai minimum fungsi f adalah...",
                "2", "6", "4", 3,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q65);
        Question q66 = new Question("Ari dan Ira merupakan anggota dari suatu kelompok yang terdiri dari 9 orang.\n" +
                "Banyaknya cara membuat barisan, dengan syarat Ari dan Ira tidak berdampingan, adalah...",
                "6 × 8!", "7 × 8!", "9 × 8!", 2,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q66);
        Question q67 = new Question("Himpunan semua bilangan real x pada selang (π,2π) yang memenuhi csc x(1-cot x) < 0 berbentuk (a,b). Nilai a+b adalah...",
                "13π/4", "11π/4", "15π/4", 1,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q67);
        Question q68 = new Question("Posisi sebuah benda di sepanjang sumbu x mengikuti x(t)=-4t+2t², dengan satuan posisi (x) adalah meter dan satuan waktu (t) adalah detik. \n Pada selang waktu dari t = 2 detik sampai t = 4 detik, perpindahan dan percepatan rata-rata benda tersebut berturut-turut adalah...",
                "8 m dan 4 m/s²", "16 m dan 8 m/s²", "16 m dan 4 m/s²", 3,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q68);
        Question q69 = new Question("Seorang pemain biola menarik senar dengan gaya 4 N sehingga senar bertambah\n" +
                "panjang 4 mm. Besar usaha yang dikerjakan oleh pemain biola tersebut adalah...",
                "6 mJ", "8 mJ", "2 mJ", 2,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q69);
        Question q70 = new Question("Suatu bejana kokoh yang berisi gas ideal dikocok berulang-ulang. Manakah pernyataan\n" +
                "yang benar tentang keadaan gas tersebut setelah dikocok?",
                "Temperatur gas bertambah sebanding dengan penambahan kelajuan molekul gas.", "Gas melakukan usaha sebesar penambahan energi dalamnya\n", "Temperatur gas bertambah tanpa gas melakukan usaha", 3,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q70);
        Question q71 = new Question("Dua balok kayu kecil A dan B terapung di permukaan danau. Jarak keduanya adalah 150\n" +
                "cm. Ketika gelombang sinusoida menjalar pada permukaan air teramati bahwa pada saat t =\n" +
                "0 detik, balok A berada di puncak, sedangkan balok B berada di lembah. Keduanya\n" +
                "dipisahkan satu puncak gelombang. Pada saat t = 1 detik, balok A berada di titik setimbang\n" +
                "pertama kali dan sedang bergerak turun. Manakah pernyataan yang benar tentang\n" +
                "gelombang pada permukaan air tersebut?",
                "Frekuensi gelombang adalah 0,25 Hz", " Pada saat t = 1 detik, balok B berada di titik setimbang dan sedang bergerak turun.", "Amplitudo gelombang adalah 75 cm", 1,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q71);
        Question q72 = new Question("Dua buah kawat konduktor yang sejajar dan berjarak L = 1 m dipasang membentk sudut =\n" +
                "30° terhadap bidang horizontal. Ujung bawah kedua kawat terhubung dengan sebuah\n" +
                "resistor R = 3 . Sebuah batang konduktor dengan masa m bergeser turun di sepanjang rel,\n" +
                "tanpa kehilangan kontak dengan rel sehingga rel dan batang membentuk suatu rangkaian\n" +
                "tertutup. Pada daerah tersebut terdapat medan magnetik seragam yang bersarnya B = 2 T\n" +
                "dan berarah horizontal. Jika batang turun dengan laju konstanta v = 3 m/s, maka batang m\n" + "adalah ...",
                "0,6 kg", "0,2 kg", "0,4 kg", 2,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q72);
        Question q73 = new Question("Sejumlah atom hidrogen dipapari gelombang elektromagnetik hingga tereksitasi. Atomatom ini kemudian memancarkan gelombang elektromagnetik sehingga turun ke keadaan\n" +
                "dasar. Panjang gelombang terbesar dua garis spektral yang dihasilkan adalah ...",
                " 120,4 nm dan 100,4 nm", " 116,0 nm dan 96,0 nm", " 121,6 nm dan 102,6 nm", 3,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q73);
        Question q74 = new Question("Sebuah bintang memiliki massa 4 kali massa matahari M. Sebuah satelit yang mengorbit\n" +
                "bintang tersebut, pada jarak r yang tetap dari pusat massa bintang, memiliki periode orbit T.\n" +
                "Jika satelit tersebut mengorbit matahari dengan jari-jari orbit yang sama, yang akan terjadi\n" +
                "adalah...",
                "Periode orbitnya menjadi 0,5 T", "Kecepatan linearnya menjadi 0,5 kali semula", "Momentum sudut tetap", 2,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q74);
        Question q75 = new Question("Mobil pemadam kebakaran sedang bergerak dengan laju 20 m/s sambil membunyikan\n" +
                "sirine pada frekuensi 400 Hz (cepat rambat bunyi 00 m/s). Jika mobil pemadam kebakaran\n" +
                "bergerak menjauhi seseorang yang sedang berdiri di tepi jalan, manakah di antara\n" +
                "pernyataan berikut yang benar?",
                "Panjang gelombang bunyi menurut pendengar adalah 90 cm.", "Orang tersebut akan mendengar frekuensi sirine lebih rendah daripada sirine mobil", "Orang tersebut akan mendengar sirine dengan frekuensi 400 Hz.", 2,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q75);
        Question q76 = new Question("Unsur F (nomor atom = 9) dan M (nomor atom = 54) membentuk molekul MF4. Bentuk molekul dari sifat kepolaran MD4 adalah...",
                "tetrahedral dan nonpolar", "piramida dan polar", "planar segiempat dan nonpolar", 3,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q76);
        Question q77 = new Question("Berikut merupakan data entalpi pada berbagai reaksi : \n" +
                "A +B →2C ∆H = −500 kJ, \n" +
                "D +2B →E ∆H = −700 kJ, \n" +
                "2D +2A →F ∆H = +50 kJ \n" +
                "Berapa entalpi total dari reaksi F +6B →2E +4C ...",
                "−2,450 kJ", "+2,350 kJ", "−1,100 kJ", 1,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q77);
        Question q78 = new Question("Manakah pernyataan dibawah ini yang benar mengacu pada gambar sel dibawah... \n" +
                "Al → Al3++3e V0 = +1.66 V \n" +
                "Ni2++2e → Ni V0 = −0.26 V \n",
                "Potensial elektroda sel adalah 2.54 V.", "Elektron akan saling bertukar antarsel melalui jembatan garam", "Potensial elektroda sel adalah 1.40 V.", 3,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q78);
        Question q79 = new Question("Tiga jenis gas dicampur dalam suatu wadah tertutup. Wadah tersebut terdiri dari 0,3 mol gas \n" +
                "A, 0,4 gas B, dan 0,3 mol gas C. Tekanan total wadah adalah 660 torr. Berikut merupakan \n" +
                "pertanyaan yang benar adalah... \n",
                "Tekanan parsial gas A dan C adalah masing masing 198 torr.", "Tekanan parsial gas C adalah 220 torr.", "Tekanan parsial gas A adalah 264 torr.", 1,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q79);
        Question q80 = new Question("Perhatikan reaksi berikut : \n" +
                "[CaSO4(s) ←→ Ca2+(aq) +SO42−(aq)] Berikut yang akan terjadi jika sodium sulfate ditambahkan ke dalam larutan jenuh CaSO4 yang \n" +
                "berada pada kesetimbangan...",
                "Kesetimbangan akan bergeser mengurangi konsentrasi ion sulfate", "Konsentrasi ion calcium akan meningkat ", "Kelarutan calcium sulfate akan menurun", 3,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q80);
        Question q81 = new Question("Atom X memiliki tiga isotop umum yang sering dijumpai : X-48, X-49, dan X-51. Jika \n" +
                "Komposisi relatif suatu isotop dibanding total adalah berturut-turut 42%, 38%, dan 20%, maka \n" +
                "massa atom X adalah... \n",
                "50.67", "48.98", "49.67", 2,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q81);
        Question q82 = new Question("Reduksi besi (III) oksida dengan gas CO menghasilkan besi menurut persamaan reaksi: \n" +
                "Fe2O3(s) + 3CO(g) → 2Fe(s) + 3CO2(g). \n" +
                "Untuk menghasilkan 5,6 kg besi dibutuhkan besi(III) oksida sejumlah .... (Ar C = 12, O = 16, Fe = 56; Mr Fe2O3 = 160) ",
                "24 kg", "36 kg", "16 kg", 3,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q82);
        Question q83 = new Question("Pernyataan yang BENAR tentang teori Darwin dan Lamarck adalah sebagai berikut..",
                "Darwin berpendapat bahwa dulu ada jerapah yang berleher pendek dan ada yang\n" +
                        "berleher panjang. Karena letak makanannya tinggi, leher yang pendek menjadi panjang", "Menurut Lamarck, jerapah yang berleher pendek akan mati karena tidak mendapatkan\n" +
                "makanan yang letaknya lebih tinggi dari tubuhnya", " Lamarck berpendapat bahwa dulu leher jerapah pendek, tetapi karena tumbuhan yang\n" +
                "dimaknnya semakin tinggi, lehernya menjadi panjang dan diwariskan kepada keturunannya.\n", 3,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q83);
        Question q84 = new Question("Komponen pada sitoplasma yang ditemukan pada sel prokariotik maupun eukariotik,\n" +
                "yang tersusun atas serat protein adalah...",
                "kromatin", "nukleosom", "sitoskeleton", 3,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q84);
        Question q85 = new Question("Beberapa tumbuhan memiliki nilai ekonomi yang tinggi karena memiliki kayu yang\n" +
                "harum. Salah satu tumbuhan tersebut adalah cendana. Cendana berbau harum karena...",
                "batangnya ditumbuhi lumut kerak sehingga menghasilkan senyawa berbau harum", "batangnya mengandung minyak atsiri yang berbau harum", "memiliki simbion berupa bakteri yang menghasilkan resin berbau harum", 2,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q85);
        Question q86 = new Question("Pengelompokkan bakteri berdasarkan kelompok bakteri gram-positif dan gram-negatif\n" +
                "didasarkan pada...",
                "komposisi kimiawi sitoplasma", "permeabilitas membran sel", "komposisi senyawa penyusun dinding sel", 3,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q86);
        Question q87 = new Question("Sklera pada bagian anterior mata akan tersambung dengan...",
                "kornea", "lensa", "koroid", 1,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q87);
        Question q88 = new Question("Pindah silang pada meiosis yang ditandai dengan terbentuknya kiasmata terjadi pada\n" +
                "tahapan...",
                "zigoten", "pakiten", " diploten", 3,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q88);
        Question q89 = new Question("Polisakarida, lipid, dan protein memiliki persamaan, yaitu...",
                "disintesis dari monomer oleh reaksi dehidrasi", "didekomposisi menjadi subunit dengan reaksi dehidrasi", "disintesis sebagai hasil dari pembentukan ikatan peptida antara monomer", 2,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q89);
        Question q90 = new Question("Human immunodeficiency virus (HIV) merupakan retrovirus (virus RNA) yang \n" +
                "mengintegrasikan materi genetiknya ke dalam genom inang menggunakan...",
                "DNA polimerase", "Primase", "Reverse transkriptase", 3,
                Question.DIFFICULTY_HARD, Category.SAINTEK);
        insertQuestion(q90);
        Question q91 = new Question("Kemunduran kerajaan Sriwijaya antara lain disebabkan oleh....",
                "kaum Brahmana mengeluarkan orang asing dari kerajaan", "faktor geografi s yang tidak kondusif", "munculnya persaingan dengan kerajaan Majapahit", 3,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q91);
        Question q92 = new Question("Makam tertua sebagai salah satu bentuk wujud seni bangunan Islam di pulau Jawa ialah makam...",
                "Sunan Gunung Jati di Cirebon", "Fatimah Binti Maimun bin Hibatullah di Leran", "Sultan Tirtayasa di Banten", 2,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q92);
        Question q93 = new Question("Alasan utama Sultan Khairun menentang Portugis di Ternate adalah untuk menolak...",
                "monopoli perdagangan Portugis", "penghimpunan kekuatan di Maluku", "monopoli perdagangan Portugis", 1,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q93);
        Question q94 = new Question("Pengaruh revolusi industri di Inggris dalam bidang politik dunia pada abad ke−18 adalah...",
                "berkembangnya paham nasionalis", "munculnya kalangan feodal", "timbulnya kapitalisme modern", 3,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q94);
        Question q95 = new Question("Berbeda dengan organisasi pergerakan sebelumnya. Muhammadiyah bergerak di bidang sosial–keagamaan karena...",
                "bersifat modern Islam", "perkumpulan politik kurang cepat memperbaiki kehidupan sosial masyarakat", "ingin memberantas kemiskinan", 2,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q95);
        Question q96 = new Question("Sebab khusus peristiwa Jerman bersatu adalah ....",
                "pertemuan dua plus empat", "adanya gerakan Jerman bersatu", "ajaran Gorbachev", 1,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q96);
        Question q97 = new Question("Usaha awal Jepang untuk membendung gerakan nasional Indonesia adalah dengan...",
                "menindas gerakan-gerakan yang melawan Jepang", "membentuk organisasi baru untuk menindak Jepang", "melarang adanya rapat dan membubarkan semua perkumpulan", 3,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q97);
        Question q98 = new Question("Pada 12 Juni 1898 Emiliano Equinaldo mengumumkan proklamasi bagi bangsa dan negara Filipina, tetapi oleh Amerika Serikat dibatalkan, karena",
                "dipengaruhi oleh gerakan komunis Cina", "mendapat pengaruh dari India", "Spanyol dikalahkan oleh Amerika Serikat", 3,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q98);
        Question q99 = new Question("Jejak-jejak sejarah yang terjadi pada masa lampau memiliki arti yang sangat penting dalam historiografi. Hal ini karena sejarah berfungsi sebagai...",
                "Sejarah sebagai peristiwa", "Sejarah sebagai kisah", "Sejarah sebagai ilmu", 2,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q99);
        Question q100 = new Question("Neraca yang berisi catatan–catatan perolehan ekspor serta pengeluaran impor barang dan jasa dinamakan...",
                "neraca transaksi berjalan", "neraca perdagangan", "neraca modal", 1,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q100);
        Question q101 = new Question("Salah satu di antara lima BUMN di bawah ini operasional bisnisnya tidak satu bidang dengan empat yang lain. BUMN yang dimaksud adalah",
                "PT Pelni", "PT Garuda Indonesia", "PT Jasa Marga", 3,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q101);
        Question q102 = new Question("Jika sebuah bank umum kesulitan likuiditas, maka sumber dana terakhir yang dapat digali adalah dari",
                "Kredit Likuiditas Bank Indonesia (KLBI)", "interbank call money", " dana pihak ketiga/masyarakat", 1,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q102);
        Question q103 = new Question("Ketika anda kuliah, maka sebenarnya anda sedang memproduksi...",
                "modal manusia", "tenaga kerja", "kewirausahaan", 1,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q103);
        Question q104 = new Question("Persediaan akhir yang ditentukan terlalu besar akan berakibat...",
                "harga pokok penjualan terlalu besar", "harga pokok penjualan terlalu kecil", "laba terlalu kecil", 2,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q104);
        Question q105 = new Question("Skala produksi efi sien adalah kuantitas output yang meminimumkan biaya...",
                "total rata–rata", "tetap rata–rata", "variabel rata–rata", 1,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q105);
        Question q106 = new Question("Saham perusahaan yang memiliki hak suara dan hak memperoleh dividen adalah...",
                "Saham obligasi", "Saham preferen", "Saham bunga", 2,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q106);
        Question q107 = new Question("Pembagian tugas merupakan fungsi manajemen...",
                "organizing", "planning", "actuating", 1,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q107);
        Question q108 = new Question("Biaya yang dikorbankan dalam menentukan pilihan disebut...",
                "opportunity", "implisit", "langsung", 1,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q108);
        Question q109 = new Question("Barang substitusi memiliki elastis silang...",
                "lebih dari 1", "negatif", "positif", 3,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q109);
        Question q110 = new Question("Berikut ini pernyataan yang benar...",
                "Kurva FC berbentuk horizontal", "Kurva MC tidak memotong kurva AC", "AC=TC+FC", 1,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q110);
        Question q111 = new Question("Pada sistem ekonomi pasar, harga ditentukan...",
                "pemerintah", "mekanisme pasar", "konsumen", 2,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q111);
        Question q112 = new Question("Batu pualam yang banyak di tambang di daerah Gunungkidul menurut proses terbentuknya termasuk jenis batuan...",
                "Metamorf kontak", "Metamorf pneumatolitik", "Metamorf dinamo", 1,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q112);
        Question q113 = new Question("Pola sungai yang berkembang di pulau Kali- mantan adalah...",
                "Rektangular", "Dendritik", "Pinnate", 2,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q113);
        Question q114 = new Question("Yang dimaksud sosiologi bersifat empiris sebagai ilmu pengetahuan tentang masyarakat adalah...",
                "Tidak meramalkan kejadian-kejadian sosial yang terjadi di masa depan.", "Disusun berdasarkan teori yang sudah ada.", "Bentuk peristiwa secara menyeluruh.", 1,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q114);
        Question q115 = new Question("Proses sosial asimilasi dapat terjadi dengan disebabkan faktor...",
                "Saling curiga", "Adanya rasa etnosentrisme", "Adanya diskriminasi ras", 1,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q115);
        Question q116 = new Question("Interaksi sosial terjadi karena kontak dan komunikasi. Menurut Horton ada 5 bentuk...",
                "Conflict-assimilation-competition-cooperation-accomodation", "Cooperation-conflict-competition-accomodation-assimilation", "Cooperation-competition-conflict-accomodation-assimilation", 3,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q116);
        Question q117 = new Question("Menurut Max Weber seorang pemimpin memiliki otoritas legal-irasional. Otoritas legal-irasional dapat dicapai dengan cara...",
                "Berdasarkan aturan hukum", "Berdasarkan kewibawaan seseorang", "Berdasarkan otonomi", 1,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q117);
        Question q118 = new Question("Konsentrasi ikan di laut terdapat pada zona...",
                "Neritis", "Abisol", "Ambang laut", 1,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q118);
        Question q119 = new Question("Syarat utama penentuan lokasi industri menurut A.Lush adalah...",
                "biaya transportasi", "Bahan baku", "Jumlah penduduk", 3,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q119);
        Question q120 = new Question("Pola sungai yang berkembang di pulau Kalimantan adalah...",
                "Pinnate", "Dendritik", "Rektangular", 2,
                Question.DIFFICULTY_EASY, Category.SOSHUM);
        insertQuestion(q120);
        Question q121 = new Question("Menurut Codes, bangsa Indonesia telah mengenal unsur peradaban sebelum Hindu-Budha masuk yaitu, kecuali..",
                "Memelihara ternak", "Kepercayaan kepada penguasa gunung", "Memiliki teknologi perundagian dan pelayaran", 3,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q121);
        Question q122 = new Question("Kehidupan pada masa Palaeolitikum adalah...",
                "Nomaden, berburu dan meramu tingkat akhir, food producing", "Nomaden, berburu dan meramu tingkat awal, food gathering", "Sedenter, bercocok tanam, food producing", 2,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q122);
        Question q123 = new Question("Budaya Mesopotamia adalah salah satu budaya tertua di dunia. Di bawah ini adalah bangsa penguasa Mesopotamia, kecuali...",
                "Mesir", "Akkadia", "Persia", 1,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q123);
        Question q124 = new Question("Apabila besarnya koefi sien elastisitas harga permintaan adalah 0,8 maka...",
                "Kenaikan harga sebesar 8% akan menyebabkan jumlah barang yang diminta turun 10%", "Kenaikan permintaan sebesar 10% akan menyebabkan penurunan harga 8%", "Penurunan harga sebesar 10% akan menyebabkan jumlah barang yang diminta naik 8%", 3,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q124);
        Question q125 = new Question("Jika sebuah bank umum kesulitan likuiditas, maka sumber dana terakhir yang dapat digali adalah dari...",
                "Sertifi kat Bank Indonesia (SBI)", "Kredit Likuiditas Bank Indonesia (KLBI)", "dana pihak ketiga/masyarakat", 2,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q125);
        Question q126 = new Question("Ketika anda kuliah, maka sebenarnya anda sedang memproduksi...",
                "jasa pendidikan", "kewirausahaan", "modal manusia", 3,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q126);
        Question q127 = new Question("Dalam proses penawaran umum atau go public dikenal suatu dokumen yang berisi semua informasi yang relevan dengan keadaan perusahaan. Dokumen tersebut dikenal dengan istilah...",
                "prospektus", "informasi penawaran umum", "dokumen penawaran umum", 1,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q127);
        Question q128 = new Question("Skala produksi efi sien adalah kuantitas output yang meminimumkan biaya...",
                "marginal", "tetap rata–rata", "total rata–rata", 3,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q128);
        Question q129 = new Question(" Faktor yang mendorong nelayan tradisional menjelang malam hari berangkat mencari ikan di laut adalah...",
                "tekanan maksimum di daratan", "daratan lambat menerima panas", "laut cepat menjadi panas", 1,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q129);
        Question q130 = new Question("Alat yang digunakan untuk mengukur kelembapan dan suhu udara adalah...",
                "anemometer", "higrometer rambut", "termohigrograf", 3,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q130);
        Question q131 = new Question("Peristiwa pengelupasan batuan induk yang telah mengalami proses pelapukan atau pengaruh aliran sungai disebut...",
                "erosi", "denudasi", "sedimentasi", 2,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q131);
        Question q132 = new Question("Kota dengan tingkat kemacetan lalu lintas tinggi dan banyaknya tingkat kejahatan, termasuk dalam tahapan kota...",
                "Tiranopolis", "Eopolis ", "Megapolis", 1,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q132);
        Question q133 = new Question("Faktor yang tidak mempengaruhi pola persebaran desa dan pemusatan penduduk adalah...",
                "Sumber daya alam", "Tata air", "Jumlah penduduk", 3,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q133);
        Question q134 = new Question("Hal-hal berikut merupakan hakikat sosiologi sebagai ilmu pengetahuan, kecuali...",
                "Sosiologi merupakan ilmu pengetahuan yang khusus", "Sosiologi bukan merupakan disiplin ilmu yang normatif", "Sosiologi merupakan pengetahuan yang abstrak", 2,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q134);
        Question q135 = new Question("Hal-hal berikut yang lebih berpengaruh terhadap terjadinya diferensiasi sosial dalam masyarakat adalah...",
                "Kekuasaan", "Pendidikan ", "Keyakinan", 3,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q135);
        Question q136 = new Question("Pada sistem ekonomi pasar, harga ditentukan...",
                "Konsumen", "Mekanisme pasar", "Pemerintah", 2,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q136);
        Question q137 = new Question("Neraca pembayaran internasional suatu negara dikategorikan surplus apabila...",
                "Nilai ekspor lebih besar dari nilai impor", "Cadangan devisa berkurang", "Surplus neraca transaksi berjalan (current account)", 1,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q137);
        Question q138 = new Question("Salah satu di antara lima BUMN di bawah ini operasional bisnisnya tidak satu bidang dengan empat yang lain. BUMN yang dimaksud adalah...",
                "PT Damri", "PT Jasa Marga", "PT Samudera Indonesia", 2,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q138);
        Question q139 = new Question("Islam berkembang di Indonesia sejak abad ke 7 melalui berbagai saluran penyebaran. Sebagai saluran penyebaran Islam di Indonesia tersebut antara lain...",
                "Mengucapkan kalimat syahadat", "Ibadah haji", "Perdagangan", 3,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q139);
        Question q140 = new Question("Neraca yang berisi catatan–catatan perolehan ekspor serta pengeluaran impor barang dan jasa dinamakan...",
                "Neraca transaksi berjalan", "Neraca pembayaran internasional", "Neraca perdagangan", 1,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q140);
        Question q141 = new Question("Dalam proses penawaran umum atau go public dikenal suatu dokumen yang berisi semua informasi yang relevan dengan keadaan perusahaan. Dokumen tersebut dikenal dengan istilah",
                "Dokumen penawaran umum", "Prospektus", "Pernyataan emiten", 2,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q141);
        Question q142 = new Question("Jika diketahui penduduk yang berumur 0-14 tahun 3000 jiwa dan di atas 65 tahun 500 jiwa dengan jumlah penduduk total 8500 jiwa, maka angka ketergantungan daerah tersebut adalah...",
                "60%", "75%", "70%", 3,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q142);
        Question q143 = new Question("Suatu keadaan di mana angkatan kerja lebih besar dari pada kesempatan kerja dinamakan...",
                "Pengangguran", "Tenaga Kerja", "Lowongan Kerja", 1,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q143);
        Question q144 = new Question("Wilayah laut Indonesia yang menjadi batas landasan kontinen disebut...",
                "Ambang laut", "Shelf", "Palung laut", 2,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q144);
        Question q145 = new Question("Batu pualam yang banyak di tambang di daerah Gunungkidul menurut proses ter- bentuknya termasuk jenis batuan...",
                "Metamorf pneumatolitik", "Sedimen", "Metamorf kontak", 3,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q145);
        Question q146 = new Question("Pola sungai yang berkembang di pulau Kalimantan adalah...",
                "Dendritik", "Pinnate", "Rektangular", 1,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q146);
        Question q147 = new Question("Dalam pengenalan obyek pada citra selalu menggunakan unsur-unsur interpretasi. Jika seorang geograf mengenali obyek lapangan sepakbola berdasarkan kenampakan gawang, maka ia menggunakan unsur interpretasi...",
                "Ukuran", "Asosiasi", "Rona", 2,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q147);
        Question q148 = new Question("Peristiwa pengelupasan batuan induk yang telah mengalami proses pelapukan atau pengaruh aliran sungai disebut...",
                "Erosi", "Sedimentasi", "Denudasi", 3,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q148);
        Question q149 = new Question("Kota dengan tingkat kemacetan lalu lintas tinggi dan banyaknya tingkat kejahatan, termasuk dalam tahapan kota...",
                "Tiranopolis", "Megapolis", "Eopolis", 1,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q149);
        Question q150 = new Question("Faktor yang tidak mempengaruhi pola persebaran desa dan pemusatan penduduk adalah...",
                "Tata air", "Jumlah penduduk", "Jenis tanah", 2,
                Question.DIFFICULTY_MEDIUM, Category.SOSHUM);
        insertQuestion(q150);
        Question q151 = new Question("Pengaruh revolusi industri di Inggris dalam bidang politik dunia pada abad ke−18 adalah...",
                "Munculnya kalangan feodal", "Timbulnya kapitalisme modern", "Berkembangnya paham nasionalis", 2,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q151);
        Question q152 = new Question("Berbeda dengan organisasi pergerakan sebelumnya. Muhammadiyah bergerak di bidang sosial–keagamaan karena...",
                "Perkumpulan politik kurang cepat memperbaiki kehidupan sosial masyarakat", "pimpinan pusat berkedudukan di Yogyakarta", "bersifat modern Islam", 1,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q152);
        Question q153 = new Question("Pada 12 Juni 1898 Emiliano Equinaldo mengumumkan proklamasi bagi bangsa dan negara Filipina, tetapi oleh Amerika Serikat dibatalkan, karena...",
                "Huru-hara di Katipunan dipimpin oleh Jose Rizal", "Pimpinan Gerakan Katipunan ditangkap oleh Spanyol", "Spanyol dikalahkan oleh Amerika Serikat", 3,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q153);
        Question q154 = new Question("Usaha awal Jepang untuk membendung gerakan nasional Indonesia adalah dengan...",
                "Melarang adanya rapat dan membubarkan semua perkumpulan", "Membentuk organisasi baru untuk menindak Jepang", "Menindas gerakan-gerakan yang melawan Jepang", 1,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q154);
        Question q155 = new Question("Sebab khusus peristiwa Jerman bersatu adalah...",
                "Adanya gerakan Jerman bersatu", "Pertemuan dua plus empat", "Ajaran Gorbachev", 2,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q155);
        Question q156 = new Question("Buku yang ditulis oleh Thomas yang membangkitkan nasionalisme rakyat Amerika Serikat adalah...",
                "Du Contract Social", "Trias Politica", "Common Sense", 3,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q156);
        Question q157 = new Question("Dewasa ini harga obat–obatan semakin tinggi bahkan juga harga obat generik. Akibatnya masyarakat terutama yang berpenghasilan menengah ke bawah kesulitan untuk membelinya. Kebijakan\n" +
                "yang bisa dilakukan pemerintah untuk mengatasi tingginya harga obat adalah dengan menetapkan kebijakan...",
                "Equilibrium price", "Harga tertinggi", "Harga terendah", 2,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q157);
        Question q158 = new Question("Dalam proses penawaran umum atau go public dikenal suatu dokumen yang berisi\n" +
                "semua informasi yang relevan dengan keadaan perusahaan. Dokumen tersebut dikenal dengan istilah...",
                "Prospektus", "Informasi penawaran umum", "Pernyataan emiten", 1,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q158);
        Question q159 = new Question("Persediaan akhir yang ditentukan terlalu besar akan berakibat...",
                "Harga pokok penjualan terlalu kecil", "Laba terlalu kecil", "Harga pokok penjualan terlalu besar", 1,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q159);
        Question q160 = new Question("Indonesia memiliki keragaman sumber daya alam, antara lain geotermal. Dibandingkan\n" +
                "dengan migas, geotermal memiliki banyak kelebihan antara lain 40% dari total deposit lingkungan dan energi terbarukan. Prinsip\n" +
                "geografi yang sesuai dalam memahami gejala seperti ini adalah...",
                "", "Prinsip deskripsi", "", 2,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q160);
        Question q161 = new Question("36\tSeorang investor ingin mendirikan pom bensin diantara dua daerah. Jika penduduk A berjumlah 16.000 jiwa dan penduduk B berjumlah 4.000. Tentukan daerah yang ide- al untuk pendirian pom bensin tersebut bila jarak daerah A dan B 100 km!",
                "33,3km dari A", "33,3km dari B", "", 2,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q161);
        Question q162 = new Question("38\tSebuah pesawat terbang dengan keting- gian 15.00 feet, merekam objek yang ketinggianya 1000m. Jika panjang fokus kamera 100mm. Berapa skala udara yang akan di- buat?",
                "1:38.000", "1:35.000", "1:40.000", 2,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q162);
        Question q163 = new Question("Yang dimaksud sosiologi bersifat empiris se- bagai ilmu pengetahuan tentang masyarakat adalah...",
                "Tidak meramalkan kejadian-kejadian sosial yang terjadi di masa depan", "Disusun berdasarkan teori yang sudah ada.", "Menyusun abstraksi dari hasil observasi yang konkret.", 1,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q163);
        Question q164 = new Question("Keteraturan sosial merupakan suatu kondisi dinamis yang menyebabkan kehidupan masyarakat berlangsung tertib dan teratur. Terciptanya keteraturan ini disebabkan oleh adanya 2 faktor yang terdapat dalam masya- rakat tersebut, yaitu...",
                "Saling pengertian dan nilai budaya", "Nilai sosial dan norma sosial", "Nilai tradisional dan norma sosial", 2,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q164);
        Question q165 = new Question("Menurut Max Weber seorang pemimpin memiliki otoritas legal-irasional. Otoritas legal- irasional dapat dicapai dengan cara...",
                "Berdasarkan hak dan kewajiban", "Berdasarkan otonomi", "Berdasarkan aturan hukum", 3,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q165);
        Question q166 = new Question("Interaksi sosial terjadi karena kontak dan komunikasi. Menurut Horton ada 5 bentuk...",
                "Cooperation-competition-conflict-accomodation-assimilation", "Assimilation-competition-cooperation-accomodation-conflict", "Competition-conflict-assimilation-accomodation-assimilation", 1,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q166);
        Question q167 = new Question("Jika diketahui penduduk yang berumur 0-14 tahun 3000 jiwa dan di atas 65 tahun. 500 jiwa dengan jumlah penduduk total\n" +
                "8500 jiwa, maka angka ketergantungan daerah tersebut adalah...",
                "75%", "70%", "60%", 2,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q167);
        Question q168 = new Question("Keteraturan sosial merupakan suatu kondisi dinamis yang menyebabkan kehidupan masyarakat berlangsung tertib dan teratur. Terciptanya keteraturan ini disebabkan oleh adanya 2 faktor yang terdapat dalam masya- rakat tersebut, yaitu...",
                "Saling pengertian dan nilai budaya", "Nilai sosial dan norma sosial", "Nilai tradisional dan norma sosial", 2,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q168);
        Question q169 = new Question("Buku yang ditulis oleh Thomas yang membangkitkan nasionalisme rakyat Amerika Serikat adalah...",
                "Du Contract Social", "Trias Politica", "Common Sense", 3,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q169);
        Question q170 = new Question("Alasan utama Sultan Khairun menentang Portugis di Ternate adalah untuk menolak...",
                "monopoli perdagangan Portugis", "penghimpunan kekuatan di Maluku", "monopoli perdagangan Portugis", 1,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q170);
        Question q171 = new Question("Pada sistem ekonomi pasar, harga ditentukan...",
                "Konsumen", "Mekanisme pasar", "Pemerintah", 2,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q171);
        Question q172 = new Question("Berbeda dengan organisasi pergerakan sebelumnya. Muhammadiyah bergerak di bidang sosial–keagamaan karena...",
                "bersifat modern Islam", "perkumpulan politik kurang cepat memperbaiki kehidupan sosial masyarakat", "ingin memberantas kemiskinan", 2,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q172);
        Question q173 = new Question("Fenomena di masyarakat apabila kita memasuki instansi pemerintah maka kita\n" +
                "wajib berpakaian rapi dan formal. Namun, hal ini tidak berlaku apabila kita berada di\n" +
                "pasar atau pusat-pusat pembelanjaan. Hal ini menunjukkan bahwa nilai dan norma dipengaruhi oleh...",
                "Tempat", "Waktu", "Jabatan", 1,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q173);
        Question q174 = new Question(" Seorang siswa telah beberapa kali melakukan pelanggaran lalulintas di\n" +
                "jalan raya seperti belum mempunyai SIM dan melanggar rambu-rambu lalulintas.\n" +
                "Berdasarkan intensitasnya, penyimpangan yang dilakukan siswa tersebut termasuk dalam kategori penyimpangan...",
                "Individu", "Massa", "Primer", 3,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q174);
        Question q175 = new Question("Hal-hal berikut yang lebih berpengaruh terhadap terjadinya diferensiasi sosial dalam masyarakat adalah...",
                "Kehormatan", "Keyakinan", "Pendidikan", 2,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q175);
        Question q176 = new Question("Hal-hal berikut ini berkaitan dengan faktor pembentuk kepribadian menurut Paul B. Horton, kecuali...",
                "Kepribadian dibangun dengan menyusun peristiwa di atas peristiwa lainnya", "Kepribadian berbeda-beda karena faktor pengalaman yang unik", "Kepribadian berbeda-beda antara satu dengan yang lainya", 1,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q176);
        Question q177 = new Question("Neraca yang berisi catatan–catatan perolehan ekspor serta pengeluaran impor barang dan jasa dinamakan...",
                "Neraca transaksi berjalan", "Neraca pembayaran internasional", "Neraca perdagangan", 1,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q177);
        Question q178 = new Question("Jejak-jejak sejarah yang terjadi pada masa lampau memiliki arti yang sangat penting dalam historiografi. Hal ini karena sejarah berfungsi sebagai...",
                "Sejarah sebagai peristiwa", "Sejarah sebagai kisah", "Sejarah sebagai ilmu", 2,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q178);
        Question q179 = new Question("Konsentrasi ikan di laut terdapat pada zona...",
                "Neritis", "Abisol", "Ambang laut", 1,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q179);
        Question q180 = new Question("Syarat utama penentuan lokasi industri menurut A.Lush adalah...",
                "biaya transportasi", "Bahan baku", "Jumlah penduduk", 3,
                Question.DIFFICULTY_HARD, Category.SOSHUM);
        insertQuestion(q180);
        Question q181 = new Question("(SINONIM) GEOSINKRONIS",
                "Geostasioner", "Kesamaan wilayah", "Kesamaan habitat", 1,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q181);
        Question q182 = new Question("(SINONIM) FLAMBOYAN",
                "Jenis kaktus", "Serba mewah", "Daya tarik", 2,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q182);
        Question q183 = new Question("(SINONIM) HIPOTENUSA",
                "Hipotesis", "Daerah rendah", "Sisi miring", 3,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q183);
        Question q184 = new Question("(SINONIM) HEPTAHEDRON",
                "Berisi tujuh", "Sapla muka", "Tempat lomba", 1,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q184);
        Question q185 = new Question("(SINONIM) INDOLEN",
                "Kepulauan", "Lesu", "Berasal dari Indonesia", 2,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q185);
        Question q186 = new Question("(SINONIM) ESOTERIS",
                "Rahasia", "Tertutup", "Terbuka", 3,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q186);
        Question q187 = new Question("(SINONIM) HEDONISME",
                "Sederhana", "Idealisme", "Pluralisme", 1,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q187);
        Question q188 = new Question("(SINONIM) GRATIFIKASI",
                "Denda", "Potongan", "Gaji", 2,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q188);
        Question q189 = new Question("(SINONIM) VIRTUAL",
                "Nyata", "Kabur", "Tak pasti", 3,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q189);
        Question q190 = new Question("(SINONIM) INSINUASI",
                "Terang-terangan", "Sindiran", "Caci maki", 1,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q190);
        Question q191 = new Question("MERAKIT : .... = .... PENSIUN",
                "Pekerjaan : Usia", "Membangun : Purnabakti", "Keahlian : Pekerjaan", 2,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q191);
        Question q192 = new Question("KANTUK : KEPENATAN = ....",
                "Mimpi : Tidur", "Muka : Ekspresi", "Senyum : Kegembiraan", 3,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q192);
        Question q193 = new Question("BUNGA : .... = .... KIKIR",
                "Bank : Rumah", "Riba : Hemat", "Mawar : Sakit", 2,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q193);
        Question q194 = new Question("BAIT : ..... = ..... BANGUNAN",
                "Bendera : Pondasi", "Merdu : Tiang", "Puisi : Loteng", 3,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q194);
        Question q195 = new Question("HUTAN : POHON = ..... : .....",
                "Armada : Kapal", "Mawar : Duri", "Sendok : Garpu", 1,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q195);
        Question q196 = new Question("Sebagian siswa memakai baju. Semua siswa memakai dasi.",
                "Semua siswa memakai baju dan dasi.", "Semua siswa memakai baju.", "Sebagian siswa memakai baju dan dasi.", 3,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q196);
        Question q197 = new Question("Dhian lebih pintar daripada Dani. Dani lebih pintar daripada Eni.",
                "Dhian paling pintar di antara mereka.", "Eni lebih pintar daripada Dani dan Dhian.", "Mereka bertiga sama pintar.", 1,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q197);
        Question q198 = new Question("Sebagian besi berkarat. Semua yang berkarat tidak tahan panas.",
                "Yang tahan panas yang berkarat.", "Sebagian besi tidak tahan panas.", "Hanya besi yang tidak tahan panas.", 2,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q198);
        Question q199 = new Question("Denny selalu bekerja setiap hari, kecuali jika hari libur. Besok hari libur.",
                "Denny bekerja.", "Besok Denny pergi piknik.", "Besok Denny tidak bekerja.", 3,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q199);
        Question q200 = new Question("Hanya anak-anak berwisata ke pantai. Tofan berwisata ke pantai.",
                "Yang berwisata ke pantai tidak hanya anak-anak.", "Tofan adalah seorang anak.", "Tofan adalah adalah seorang ibu.", 1,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q200);
        Question q201 = new Question("Jika T adalah siswa yang menyelesaikan ujian setelah M, manakah pernyataan berikut ini yang benar?",
                "T adalah siswa yang terakhir kali menyelesaikan ujian.", "T adalah siswa yang pertama kali menyelesaikan ujian.", "W adalah siswa yang menyelesaikan ujian setelah T.", 1,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q201);
        Question q202 = new Question("Jika U adalah siswa yang menyelesaikan ujian sebelum O tapi sesudah W, dan F selesai setelah M, manakah pernyataan berikut yang tidak benar?",
                "M selesai sebelum O.", "F selesai sebelum K.", "U selesai sebelum M.", 2,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q202);
        Question q203 = new Question("Jika p = 3, q = 2, dan r = p2 + 2pq + q2, berapakah pq ?",
                "75", "45", "150", 3,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q203);
        Question q204 = new Question("Sebuah kota mempunyai lebar 12 cm, panjang 16 cm, dan tinggi 16 cm. Berapa cm persegi kertas yang diperlukan untuk menutup semua sisi kotak tersebut...",
                "720", "360", "900", 1,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q204);
        Question q205 = new Question("Luas sebuah lingkaran adalah 49π. Berapa keliling lingkaran tersebut?",
                "28π", "52π", "14π", 3,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q205);
        Question q206 = new Question("8, 3, 8, 6, 9, 7, 9, 11, 8, 12, ..., ....",
                "8,12", "8,17", "7,17", 2,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q206);
        Question q207 = new Question("2, 2, 2, 5, 4, 5, 4, 9, 6, 9, ..., ....",
                "6,14", "7,14", "7,12", 1,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q207);
        Question q208 = new Question("Jika 0,1 m = 1, berapa nilai 1,1 m ?",
                "11", "10,1", "9,1", 1,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q208);
        Question q209 = new Question("100, 18, 105, 21, 110, 24, ...., ....",
                "115,26", "115,27", "116,28", 2,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q209);
        Question q210 = new Question("0,75 : 1,25 = ....",
                "0,60", "0,65", "0,70", 1,
                Question.DIFFICULTY_EASY, Category.TPA);
        insertQuestion(q210);
        Question q211 = new Question("(SINONIM) HEPTAHEDRON",
                "Berisi tujuh", "Sapla muka", "Tempat lomba", 1,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q211);
        Question q212 = new Question("(SINONIM) GEOSINKRONIS",
                "Geostasioner", "Kesamaan wilayah", "Kesamaan habitat", 1,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q212);
        Question q213 = new Question("(SINONIM) FLAMBOYAN",
                "Jenis kaktus", "Serba mewah", "Daya tarik", 2,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q213);
        Question q214 = new Question("KANTUK : KEPENATAN = ....",
                "Mimpi : Tidur", "Muka : Ekspresi", "Senyum : Kegembiraan", 3,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q214);
        Question q215 = new Question("POLISI : ... = TNI : ...",
                "Ajun komisaris besar polisi : Letnan kolonel", "Ajun komisaris polisi : Kolonel", "Komisaris besar polisi : Kapten", 1,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q215);
        Question q216 = new Question("WISUDA : ... = PERTUNANGAN : ...",
                "Gelar : Pelaminan", "Toga : Cincin", "Sarjana : Mempelai", 2,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q216);
        Question q217 = new Question("KARET : ... = AREN : ...",
                "Getah : Nira", "Ban : Manis", "Sadap : Gula", 1,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q217);
        Question q218 = new Question("SAYAP : ... = KAKI : ...",
                "Terbang : Pijak", "Kepak : Hentak", "Udara : Air", 2,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q218);
        Question q219 = new Question("ANALGESIK : ... = PELUMAS : ...",
                "Nyeri : Gesekan", "Sakit : Rusak", "Obat : Oli", 1,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q219);
        Question q220 = new Question("... : MALAM = MATAHARI : ...",
                "Gelap : Panas", "Bulan : Siang", "Tidur : Bekerja", 2,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q220);
        Question q221 = new Question("... : KONGLOMERAT = PANDAI : ...",
                "Kaya : Pintar", "Harta : Buku", "Kaya : Jenius", 3,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q221);
        Question q222 = new Question("... : KATAK = ULAT : ...",
                "", "Nyamuk : Burung", "", 2,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q222);
        Question q223 = new Question("... : PENJAHIT = KUAS : ...",
                "Pakaian : Pelukis", "Jarum : Cat", "Mesin jahit : Tukang cat", 3,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q223);
        Question q224 = new Question("... : ASTRONOM = BUKU : ....",
                "Rasi : Penulis", "Bintang : Penerbit", "Teleskop : Pelajar", 1,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q224);
        Question q225 = new Question("IKAN : ... = ... : KULIT",
                "Asin : Gelap", "Sisik : Manusia", "Laut : Sel", 2,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q225);
        Question q226 = new Question("Setelah lulus S1, jika mahasiswa melanjutkan studi S2 maka ia tidak menikah. Resita menikah setelah lulus S1.",
                "Resita menikah kemudian melanjutkan studi S2", "Resita melanjutkan studi S2", "Resita tidak melanjutkan studi S2", 3,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q226);
        Question q227 = new Question("Sebelum jam istirahat, siswa mengikuti kegiatan di aula. Firman makan di kantin sekolah pada jam istirahat.",
                "Firman mengikuti kegiatan dan makan di kantin sekolah", "Firman mengikuti kegiatan sebelum makan di kantin sekolah.", "Firman tidak mengikuti kegiatan karena makan di kantin sekolah.", 2,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q227);
        Question q228 = new Question("Siswa kursus level 1 baru naik ke level 2 jika sudah lulus ujian geometri. Ardi dan Nolang adalah siswa kursus level 2.",
                "Ardi dan Nolang keduanya lulus ujian geometri.", "Ardi lulus ujian geometri tetapi Ardi tidak lulus ujian geometr", "Ardi tidak lulus ujian geometri tetapi Ardi lulus ujian geometri.", 1,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q228);
        Question q229 = new Question("Semua bunga berwarna cerah penyerbukannya dibantu serangga. Sebagian bunga di taman tidak berwarna cerah.",
                "Semua bunga di taman penyerbukannya dibantu serangga", "Semua bunga di taman penyerbukannya tidak dibantu serangga", "Sebagian bunga di taman penyerbukannya tidak dibantu serangga.", 3,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q229);
        Question q230 = new Question("Jika musim kemarau, maka tumbuhtumbuhan meranggas. Saat tumbuh-tumbuhan meranggas, sampah berserakan.",
                "Saat musim bukan kemarau sampah berserakan", "Saat musim kemarau sampah berserakan", "Sampah berserakan bukan karena tumbuh- tumbuhan yang meranggas", 2,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q230);
        Question q231 = new Question("Pecahan yang nilainya terletak antara 3/5 dan 9/10 adalah...",
                "3/4", "4/7", "5/11", 1,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q231);
        Question q232 = new Question("Jika 4 adalah x% dari 160 maka nilai x adalah...",
                "25", "2,5", "0,25", 2,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q232);
        Question q233 = new Question("Jika 5/9 dari 27/35 sama dengan x dari 1/14 maka nilai x = ...",
                "2", "4", "6", 3,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q233);
        Question q234 = new Question("0, 2, 8, 14, 24, ...",
                "34", "32", "30", 1,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q234);
        Question q235 = new Question("1, 2, 3, 6, 9, 18, ...",
                "18", "27", "36", 2,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q235);
        Question q236 = new Question(" 1, 3, 4, 8, 15, 27, ...",
                "58", "55", "50", 3,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q236);
        Question q237 = new Question("8, 11, 21, 15, 18, 21, 22, ..., ...",
                "25, 21", "25, 18", "24, 21", 1,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q237);
        Question q238 = new Question(" 2, 3, 4, 5, 6, 4, 8, ..., ...",
                "4, 8 ", "9, 4", "10, 4", 2,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q238);
        Question q239 = new Question("17, 17, 34, 20, 20, 31, 23, ..., ...",
                "23, 28", "26, 23", "34, 20", 1,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q239);
        Question q240 = new Question("75, 65, 85, 55, 45, 85, 35, ..., ...",
                "25, 15", "25, 85", "32, 85", 2,
                Question.DIFFICULTY_MEDIUM, Category.TPA);
        insertQuestion(q240);
        Question q241 = new Question("61, 57, 50, 61, 43, 36, 61, ..., ...",
                "29, 22", "27, 20", "22, 15", 1,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q241);
        Question q242 = new Question("4, 8, 22, 12, 16, 22, 20, 24, ..., ...",
                "28, 32 ", "28, 22", "22, 26", 3,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q242);
        Question q243 = new Question("42, 40, 38, 35, 33, 31, 28, ..., ...",
                "26, 24", "25, 22", "25, 23", 1,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q243);
        Question q244 = new Question("0, 2, 8, 14, 24, ...",
                "32", "34", "30", 2,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q244);
        Question q245 = new Question("1, 2, 3, 6, 9, 18, ...",
                "36", "27", "243", 1,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q245);
        Question q246 = new Question("28, 25, 5, 21, 18, 5, 14, ..., ...",
                "5, 10", "10, 7", "11, 5", 3,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q246);
        Question q247 = new Question("... : KONGLOMERAT = PANDAI : ...",
                "Kaya : Pintar", "Harta : Buku", "Kaya : Jenius", 3,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q247);
        Question q248 = new Question("... : KATAK = ULAT : ...",
                "", "Nyamuk : Burung", "", 2,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q248);
        Question q249 = new Question("... : PENJAHIT = KUAS : ...",
                "Pakaian : Pelukis", "Jarum : Cat", "Mesin jahit : Tukang cat", 3,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q249);
        Question q250 = new Question("KARET : ... = AREN : ...",
                "Getah : Nira", "Ban : Manis", "Sadap : Gula", 1,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q250);
        Question q251 = new Question("Memancing adalah aktivitas yang selalu Eko lakukan pada hari Minggu. Minggu ini pekerjaan Eko menumpuk.",
                "Hari Minggu ini Eko memancing setelah menyelesaikan pekerjaan", "Hari Minggu ini Eko memancing.", "Hari Minggu ini Eko tidak memancing", 2,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q251);
        Question q252 = new Question("Semua peserta SBMPTN harus mengerjakan soal TPA dan TKD Umum. Peserta SBMPTN kelompok Soshum harus mengerjakan soal TKD Soshum. Vita adalah peserta SBMPTN kelompok Soshum.",
                "Vita hanya harus mengerjakan soal TPA dan TKD Umum", "Vita tidak harus mengerjakan soal TPA, TKD Umum, dan TKD Soshum", "Vita harus mengerjakan soal TPA, TKD Umum, dan TKD Soshum", 3,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q252);
        Question q253 = new Question("Jika pemasukan pajak berkurang, maka anggaran belanja negara turun. Penurunan anggaran belanja negara menyebabkan pembangunan terhambat",
                "Pemasukan pajak yang berkurang menyebabkan terhambatnya pembangunan", "Pembangunan terhambat selalu disebabkan oleh turunnya pemasukan pajak", "Pemasukan pajak yang berkurang tidak mempengaruhi pembangunan", 1,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q253);
        Question q254 = new Question("Semua atlet berada di pusat pelatihan atau libur di rumah masing-masing. Ruang pusat pelatihan atlet sedang digunakan",
                "Semua atlet, sedang berada di ruang pusat latihan di rumah masing-masing", "Semua atlet sedang berlatih di rumah masing-masing", "Semua atlet tidak berada di rumah masing-masing", 3,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q254);
        Question q255 = new Question("Semua bunga berwarna cerah penyerbukannya dibantu serangga. Sebagian bunga di taman tidak berwarna cerah.",
                "Sebagian bunga di taman penyerbukannya tidak dibantu serangga", "Semua bunga di taman penyerbukannya dibantu serangga", "Semua bunga di taman penyerbukannya tidak dibantu serangga", 1,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q255);
        Question q256 = new Question("Penonton dapat memperoleh informasi pembelian karcis melalui poster atau internet. Hari ini layanan internet tidak dapat diakses.",
                "Penonton dapat memperoleh informasi selain pembelian karcis", "Penonton tidak dapat memperoleh informasi pembelian karcis", "Penonton dapat memperoleh informasi pembelian karcis", 2,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q256);
        Question q257 = new Question("Semua handphone dapat digunakan untuk mengirim SMS. Sebagian handphone dapat digunakan untuk mengakses internet.",
                "Sebagian handphone dapat digunakan untuk mengakses internet dan untuk mengirim SMS", "Sebagian handphone dapat digunakan untuk mengakses internet tetapi tidak bisa untuk mengirim SMS", "Sebagian handphone tidak dapat digunakan untuk mengakses internet tetapi bisa untuk mengirim SMS", 1,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q257);
        Question q258 = new Question("Ibu membeli 4 3/5 kg jeruk di sebuah supermarket. Jika harga satu kg jeruk adalah Rp15.000,00 dan ibu menyerahkan 2 lembar uang Rp50.000,00 ke kasir maka uang kembalian yang diterima ibu adalah...",
                "Rp 33.000,00", "Rp 29.000,00", "Rp 31.000,00", 3,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q258);
        Question q259 = new Question("Harga sebuah barang X adalah Rp 2.500,00, tetapi jika membeli 3 buah maka harganya Rp7.000,00. Berliana mempunyai uang Rp52.000,00. Banyaknya barang X terbanyak yang bisa dia diperoleh adalah...",
                "22", "21", "16", 1,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q259);
        Question q260 = new Question("Billy memiliki kelereng sejumlah 30% dari jumlah kelereng Kristi. Kristi memiliki kelereng sejumlah 125% dari jumlah kelereng Willy. Jika Willy memberikan 70 kelerengnya kepada Billy maka Billy akan memiliki kelereng sejumlah 50% dari jumlah kelereng Kristi. Banyaknya kelereng yang dimiliki 3 anak tersebut adala...",
                "815", "735", "620", 2,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q260);
        Question q261 = new Question("Edi membeli sebuah sepeda motor seharga Rp12.400.000,00 secara kredit. Jika uang muka yang dibayarkan adalah Rp5.800.000,00 dan setiap bulan ia harus mengangsur sebesar Rp1.100.000,00 maka ia akan melunasi hutangnya tersebut dalam ... bulan.",
                "6", "7", "8", 1,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q261);
        Question q262 = new Question("Sebuah perjalanan sejauh 600 km ditempuh dengan menggunakan kereta api dan mobil. Jika 480 km ditempuh dengan kereta api dan sisanya ditempuh dengan mobil maka perjalanan tersebut akan ditempuh dalam 8 jam. Namun jika 400 km ditempuh dengan kereta api dan sisanya ditempuh dengan mobil maka perjalanan tersebut akan ditempuh 20 menit lebih lama. Perbandingan kecepatan kereta api dan mobil adalah...",
                "4 : 4", "4 : 2", "4 : 3", 3,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q262);
        Question q263 = new Question("A dan B dapat melakukan sebuah pekerjaan secara bersama-sama dalam 30 hari. B dan C secara bersama-sama dapat melakukan pekerjaan yang sama dalam 24 hari. Sedangkan C dan A secara bersama-sama dapat melakukan pekerjaan tersebut dalam 20 hari. Pada awalnya mereka bekerja bersama-sama, tetapi mulai hari kesebelas A bekerja sendirian. Untuk menyelesaikan pekerjaan tersebut maka A harus bekerja ... hari lagi",
                "18", "12", "21", 1,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q263);
        Question q264 = new Question("20% siswa kelas XII di sebuah sekolah berusia di bawah 17 tahun. Jumlah siswa yang berusia lebih dari 17 tahun adalah 2 3 dari jumlah siswa yang berusia tepat 17 tahun. Jika jumlah siswa yang berusia tepat 17 tahun adalah 48, maka jumlah siswa kelas XII di sekolah tersebut adalah...",
                "120", "100", "72", 2,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q264);
        Question q265 = new Question("Saat Aisyah lahir, usia ayahnya adalah 36 tahun sedangkan saat kakaknya lahir, usia ibunya adalah 26 tahun. Jika selisih usia Aisyah dan kakaknya adalah 4 tahun maka selisih usia ayah dan ibu Aisyah adalah...",
                "6", "8", "12", 1,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q265);
        Question q266 = new Question("Jika jumlah n suku pertama suatu deret didefiniskan sebagai Sn = 12n - n2 maka suku kelima deret tersebut adalah...",
                "3", "6", "0", 1,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q266);
        Question q267 = new Question("Sebuah kota mempunyai lebar 12 cm, panjang 16 cm, dan tinggi 16 cm. Berapa cm persegi kertas yang diperlukan untuk menutup semua sisi kotak tersebut..",
                "660", "720", "810", 2,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q267);
        Question q268 = new Question("Jika p = 3, q = 2, dan r = p^2 + 2pq + q^2, berapakah pq ?",
                "150", "75", "100", 1,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q268);
        Question q269 = new Question("Harga sebuah barang X adalah Rp 2.500,00, tetapi jika membeli 3 buah maka harganya Rp7.000,00. Berliana mempunyai uang Rp52.000,00. Banyaknya barang X terbanyak yang bisa dia diperoleh adalah...",
                "31", "19", "22", 3,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q269);
        Question q270 = new Question("42, 40, 38, 35, 33, 31, 28, ..., ...",
                "26, 24", "25, 24", "23, 24", 1,
                Question.DIFFICULTY_HARD, Category.TPA);
        insertQuestion(q270);
    }

    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();

        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuizContract.QuestionTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuizContract.QuestionTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuizContract.QuestionTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(QuizContract.CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(QuizContract.CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuizContract.QuestionTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuizContract.QuestionTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuizContract.QuestionTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuizContract.QuestionTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuizContract.QuestionTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuizContract.QuestionTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}