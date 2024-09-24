class Student (
    val ID: Int,
    name: String,
    surname: String,
    secondname: String,
    phone: String? = null,
    telegram: String? = null,
    email: String? = null,
    git: String? = null
)
    {
        companion object {
            private val phoneRegex = Regex("""^\+?[0-9]{10,15}$""")
            fun CheckPhone(value: String?) = value == null || phoneRegex.matches(value)
        }
        var name = name
        get() = field
        set(value) {
            field = value
        }
        var surname = surname
        get() = field
        set(value) {
            field = value
        }
        var secondname = secondname
        get() = field
        set(value) {
            field = value
        }
        var phone = phone
            get() = field
            set(value) {
                if (CheckPhone(value)) field = value
                else throw IllegalArgumentException("Number is incorrect")
            }
        var telegram = telegram
            get() = field
            set(value) {
                field = value
            }
        var email = email
        get() = field
        set(value) {
            field = value
        }
        var git = git
        get() = field
        set(value) {
            field = value
        }
    init {
        this.surname = surname
        this.name = name
        this.secondname = secondname
        this.phone = phone
        this.telegram = telegram
        this.email = email
        this.git = git
    }

        constructor(ID: Int,
                    surname: String,
                    name: String,
                    secondname: String,
                    phone: String) : this(ID, surname, name, secondname) {
            this.phone = phone
        }

        constructor(ID: Int,
                    surname: String,
                    name: String,
                    secondname: String,
                    email: String,
                    git: String) : this(ID, surname, name, secondname) {
            this.email = email
            this.git = git
        }

        constructor(hashMap: Map<String, Any>) : this(
            hashMap["ID"]       as  Int,
            hashMap["surname"]  as  String,
            hashMap["name"]     as  String,
            hashMap["secondname"] as  String,
            hashMap["phone"]    as? String,
            hashMap["telegram"] as? String,
            hashMap["email"]    as? String,
            hashMap["git"]      as? String,
        )

        override fun toString(): String {
            var str = "[ID $ID] $surname $name $secondname"
            if (phone != null) str += "\nНомер телефона: $phone"
            if (telegram != null) str += "\nTelegram: $telegram"
            if (email != null) str += "\nEmail: $email"
            if (git != null) str += "\nGit: $git"
            return "$str\n"
        }
        fun show() = println(this.toString())

    }

    
fun main() {
    val students = mutableListOf(

    Student(mapOf(
        "ID" to 1,
        "name" to "Ivan",
        "surname" to "Ivanovich",
        "secondname" to "Ivanov"
    )),
    Student(mapOf(
        "ID" to 2,
        "name" to "Jack",
        "surname" to "Jackov",
        "secondname" to "Jacksov",
        "phone" to "+79528459854",
        "telegram" to "@chips_the_unlimited",
        "email" to "jack999@mail.com",
        "git" to "https://github.com/jack999"
    )),
    Student(mapOf(
        "ID" to 3,
        "name" to "Ann",
        "surname" to "Anny",
        "secondname" to "Annovich",
        "telegram" to "@the_anna",
        "git" to "https://github.com/ann300"
    )),
    Student(mapOf(
        "ID" to 4,
        "name" to "Are",
        "surname" to "Who",
        "secondname" to "You",
        "git" to "https://gitlab.com/unknown"
    )),
    Student(mapOf(
        "ID" to 5,
        "name" to "Anastasia",
        "surname" to "Frolova",
        "secondname" to "Aleksandrovna",
        "email" to "blumwinx2000@mail.com"
    )),
    Student(mapOf(
        "ID" to 6,
        "name" to "Irina",
        "surname" to "IX",
        "secondname" to "no secondname",
        "email" to "lol2000@mail.com",
        "phone" to "9999lol"
    )),
    )
    students.forEach { it.show() }
}
