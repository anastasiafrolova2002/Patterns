//1 создаем класс Student
class Student(
    val id: Int,
    var firstName: String,
    var lastName: String,
    var middleName: String?,
    var phone: String?,
    var telegram: String?,
    var email: String?,
    var github: String?
) {
    // Геттеры и сеттеры
    fun getFullName() = "$lastName $firstName ${middleName ?: ""}"

    override fun toString() = "Student(id=$id, fullName='${getFullName()}', phone=$phone, telegram=$telegram, email=$email, github=$github)"
}

//2 созданием объекты и выводим информацию о них:
fun main() {
    val student1 = Student(1, "Ivan", "Ivanov", "Ivanovich", "123456789", "ivanivan", "ivan.ivan@mail.com", "ivan_2002")
    val student2 = Student(2, "Oleg", "Kitov", null, null, null, "o.kit@mail.com", "o_kit_000")

    println(student1)
    println(student2)
}
