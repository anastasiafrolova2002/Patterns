import java.io.File
import java.io.FileNotFoundException

class Student (
    override val id: Int,
    name: String,
    surname: String,
    secondname: String,
    phone: String? = null,
    telegram: String? = null,
    email: String? = null,
    git: String? = null
): SudentAbst(){
    
    companion object {
        fun readFromTxt(filePath: String): List<Student> {
            val file = File(filePath)
            if (!file.exists()) throw FileNotFoundException("Файл '$filePath' не найден")
            return buildList {
                var currentLine = 1
                for (line in file.readLines()) {
                    if (line.isNotEmpty()) {
                        try {
                            add(Student(line))
                        }
                        catch (e: Exception) {
                            throw Exception("Reading ERROR '$filePath', line $currentLine: ${e.message}")
                        }
                    }
                    currentLine++
                }
            }
        }
        fun writeToTxt(filePath: String, students: Iterable<Student>) {
            val file = File(filePath)
            file.printWriter().use {
                for (student in students) {
                    it.println(student.toStringRow())
                }
            }
        }
    }
    
        var name = name
        get() = field
        set(value) {
            if (CheckName(value)) field = value
                else throw IllegalArgumentException("Name is incorrect")
        }
        var surname = surname
        get() = field
        set(value) {
            if (CheckName(value)) field = value
            else throw IllegalArgumentException("Surname is incorrect")
        }

        var secondname = secondname
        get() = field
        set(value) {
            if (CheckSecondname(value)) field = value
            else throw IllegalArgumentException("Secondname is incorrect")
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
                if (CheckTelegram(value)) field = value
                else throw IllegalArgumentException("Inavlid name for nick in telegram")
            }
        var email = email
        get() = field
        set(value) {
            if (CheckEmail(value)) field = value
            else throw IllegalArgumentException("Email adress is invalid")
        }

        override var git = git
        get() = field
        set(value) {
            if (CheckGit(value)) field = value
            else throw IllegalArgumentException("Git link is invalid")
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

        constructor(row: String) : this(row.split(',').also {
            if (it.size != 8 || it.any { "\n" in it })
                throw IllegalArgumentException("The format is invalid")
        })

        private constructor(row: List<String>) : this(
            row[0].toIntOrNull().let { it ?: throw IllegalArgumentException("Invalid id. Supposed to be natural") },
            row[1],
            row[2],
            row[3],
            row[4].ifEmpty { null },
            row[5].ifEmpty { null },
            row[6].ifEmpty { null },
            row[7].ifEmpty { null }
        )
        // 3
        fun getInfoSt() : String {
            val contactText = mapOf(
                "phone" to "номер телефона",
                "telegram" to "Telegram",
                "email" to "электронная почта"
            )
            val git = "git${if (this.git != null) ": ${this.git}" else " не указан"}"
            val contact = getContact().let {
                if (it != null) "${contactText[it.first]}: ${it.second}" else "контакты не указаны"
            }
            return "Студент ${getInitials()}, $git, $contact"
        }
        private fun getInitials() = "$name ${surname[0]}.${if (secondname.isNotEmpty()) " ${secondname[0]}." else ""}"
        private fun getContact() =
            if      (phone != null) Pair("phone", phone)
            else if (telegram != null) Pair("telegram", telegram)
            else if (email != null) Pair("email", email)
            else null
        //3

        override fun toString(): String {
            var str = "[ID $id] $surname $name $secondname"
            if (phone != null) str += "\nНомер телефона: $phone"
            if (telegram != null) str += "\nTelegram: $telegram"
            if (email != null) str += "\nEmail: $email"
            if (git != null) str += "\nGit: $git"
            return "$str\n"
        }
        
        private fun toStringRow() = listOf(
            id.toString(), surname, name, secondname, phone ?: "", telegram ?: "", email ?: "", git ?: "").joinToString(",")
        
        fun anyGit(): Boolean {
            val result = git != null
            println("У студента $surname $name $secondname гит ${if (result) "при" else "от"}сутствует!")
            return result
        }
        fun anyContact(): Boolean {
            val result = phone != null || telegram != null || email != null
            println("Студент $surname $name $secondname , контакты:  ${if (result) "ЕСТЬ" else "НЕТ"}")
            return result
        }
        fun setContacts(hashMap: Map<String, String?>) {
            if (hashMap.containsKey("phone"))
                phone = hashMap["phone"]
            if (hashMap.containsKey("telegram"))
                telegram = hashMap["telegram"]
            if (hashMap.containsKey("email"))
                email = hashMap["email"]
        }
    }

fun FileTestLab2() {
    //read
    val studList = Student.readFromTxt("C://Users//User//IdeaProjects//mpjjj1//src//testfile_lab2.txt")
    studList.forEach { println(it.getInfoSt()) }

    //write to
    Student.writeToTxt("C://Users//User//IdeaProjects//mpjjj1//src//testfileOUT_lab2.txt", studList)
    val studList2 = Student.readFromTxt("C://Users//User//IdeaProjects//mpjjj1//src//testfile_lab2.txt")
    println()
    studList2.forEach { println(it.getInfoSt()) }
    require(studList2.toString() == studList.toString())
}    


fun part2_lb2() {
    val studList = Student.readFromTxt("C://Users//User//IdeaProjects//mpjjj1//src//testfile_lab2.txt").map { StudentShort(it) }
    studList.forEach { it.show() }
    val dataList = DataListStudentShort(studList)
    val names = dataList.getNames()
    println(names)
    dataList.select(0)
    dataList.select(2)
    val dataTable = dataList.getData()
    for (i in 0..<dataTable.getRowAmount()) {
        for (j in 0..<dataTable.getColAmount()) {
            print("${dataTable[i, j]} ")
        }
        println()
    }
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
    //check contacts
    students.forEach { it.anyGit() }
    students.forEach { it.anyContact() }

    students[1].setContacts(mapOf("telegram" to null, "email" to "newemail@com.com"))
    students[1].show()
    println("\nLab 2 results:\n")
    lab2()
}
fun lab2() {
    val students = mutableListOf(
        Student("1,Фролова,Анастасия,Александровна,,,,"),
        Student("2,Фамилия,Имя,Отчество,+79528459854,@mail_heck,ex@example.com,https://github.com/git_check"),
        Student("3,Иванов,Иван,Иванович,,@ivan2002,,https://github.com/ivan_200002"),
        Student("4,Surname,Name,SecondName,,,,https://gitlab.com/user"),
        Student("5,Вишня,Олег,Петросович,,,cherry@mail.com,"),
        //Student("5.5,Вишня2,Олег2,Петросович2,,,cherry2@mail.com,"),
    )

    students.forEach { it.show() }
    students.forEach { println(it.getInfoSt())}
    val StudentsSHORT = mutableListOf(
        StudentShort(students[0]),
        StudentShort(students[1]),
        StudentShort(students[2]),
        StudentShort(students[3]),
        StudentShort(students[4]),
        StudentShort(6, "Студент Дыня О., git не указан, контакты не указаны"),
        StudentShort(7, "Студент Иванов Н. Н., git: https://github.com/ivan, связаться можно по номеру телефона: +79528459855")
    )
    StudentsSHORT.forEach { it.show() }
    print("\nEx 7:\n")
    FileTestLab2()

    print("\nPart 2:\n")
    part2_lb2()
}
