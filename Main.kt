//fun FileTestLab2() {
//    //read
//    val studList = Student.readFromTxt("C://Users//User//IdeaProjects//mpjjj1//src//testfile_lab2.txt")
//    studList.forEach { println(it.getInfoSt()) }
//
//    //write to
//    Student.writeToTxt("C://Users//User//IdeaProjects//mpjjj1//src//testfileOUT_lab2.txt", studList)
//    val studList2 = Student.readFromTxt("C://Users//User//IdeaProjects//mpjjj1//src//testfile_lab2.txt")
//    println()
//    studList2.forEach { println(it.getInfoSt()) }
//    require(studList2.toString() == studList.toString())
//}
//
//fun part2_lb2() {//print datatable
//    val studList = Student.readFromTxt("C://Users//User//IdeaProjects//mpjjj1//src//testfile_lab2.txt").map { StudentShort(it) }
//    studList.forEach { it.show() }
//    val dataList = DataListStudentShort(studList)
//    val names = dataList.getNames()
//    println(names)
//    dataList.select(0)
//    dataList.select(2)
//    val dataTable = dataList.getData()
//    for (i in 0..<dataTable.getRowAmount()) {
//        for (j in 0..<dataTable.getColAmount()) {
//            print("${dataTable[i, j]} ")
//        }
//        println()
//    }
//}

fun lab1() {
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
        /*
        Student(mapOf(
            "ID" to 6,
            "name" to "Irina",
            "surname" to "IX",
            "secondname" to "no secondname",
            "email" to "lol2000@mail.com",
            "phone" to "9999lol"
        )),*/
    )
    //students.forEach { it.show() }
    //students.forEach { it.anyGit() }
    //students.forEach { it.anyContact() }

    //students[1].setContacts(mapOf("telegram" to null, "email" to "newemail@com.com"))
    //students[1].show()
    //println("\nLab 2 results:\n")
//    lab2()
}
fun printDataTable(dataTable: DataTable) {
    for (i in 0..<dataTable.getRowAmount()) {
        for (j in 0..<dataTable.getColAmount()) {
            print("${dataTable[i, j]} ")
        }
        println()
    }
}
fun lab3TestGettingStudents() {
    val students = StudentListTXT()
    students.load("C://Users//User//IdeaProjects//mpjjj1//src//testfile_lab2.txt")
    println(students.getStudentShortCount())
    for (id in -1..6) {
        try {
            println(students.getStudentById(id).toStringRow())
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
    println()
    printDataTable(students.getStudentShortList(1, 3).getData())
    println("=========================")
    printDataTable(students.getStudentShortList(1, 0).getData())
    println("=========================")
//    printDataTable(students.getStudentShortList(0, 3).getData())
//    println("=========================")
//    printDataTable(students.getStudentShortList(1, -1).getData())
//    println("=========================")
    printDataTable(students.getStudentShortList(1, 100).getData())
    println("=========================")
    printDataTable(students.getStudentShortList(2, 100).getData())
    println("=========================")
    printDataTable(students.getStudentShortList(3, 1).getData())
    println("=========================")
    printDataTable(students.getStudentShortList(2, 3).getData())
    println("=========================")
    printDataTable(students.getStudentShortList(1, 5).getData())
    println("=========================")
    printDataTable(students.getStudentShortList(1, 6).getData())
    println()

    students.sortByStudentName()
    printDataTable(students.getStudentShortList(1, 5).getData())

    println()

    students.add(Student(0, "Новый", "Студент", "Хе-хе"))
    printDataTable(students.getStudentShortList(1, 100).getData())
    println(students.getStudentById(6).toStringRow())
    students.replace(6, Student(0, "Изменённый", "Студент", "Хе-хе"))
    println(students.getStudentById(6).toStringRow())
    println()
    students.remove(6)
    for (id in 1..7) {
        try {
            println(students.getStudentById(id).toStringRow())
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
    println()
    students.add(Student(0, "Вернувшийся", "Студент", "Хе-хе"))
    students.add(Student(0, "Студент", "Номер", "Восемь"))
    for (id in 1..9) {
        try {
            println(students.getStudentById(id).toStringRow())
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
    println(students.getStudentShortCount())
    students.save("lab3_output.txt")
}
fun lab2() {
    val students = mutableListOf(
        Student("1,Фролова,Анастасия,Александровна,,,,"),
        Student("2,Фамилия,Имя,Отчество,+79528459854,@mail_heck,ex@example.com,https://github.com/git_check"),
        Student("3,Иванов,Иван,Иванович,,@ivan2002,,https://github.com/ivan_200002"),
        Student("4,Surname,Name,SecondName,,,,https://gitlab.com/user"),
        Student("5,Вишня,Олег,Петросович,,,cherry@mail.com,"),
        //Student("5.5,Вишня2,Олег2,Петросович2,,,cherry2@mail.com,")
    )

    //students.forEach { it.show() }
    //students.forEach { println(it.getInfoSt())}
    val StudentsSHORT = mutableListOf(
        StudentShort(students[0]),
        StudentShort(students[1]),
        StudentShort(students[2]),
        StudentShort(students[3]),
        StudentShort(students[4]),
        StudentShort(6, "Студент Дыня О., git не указан, контакты не указаны"),
        StudentShort(7, "Студент Иванов Н. Н., git: https://github.com/ivan, связаться можно по номеру телефона: +79528459855")
    )
   // StudentsSHORT.forEach { it.show() }


    //print("\nEx 7:\n")
    //FileTestLab2()

    //print("\nPart 2:\n")
    //part2_lb2()

}
fun main(){
    lab3TestGettingStudents()
}
