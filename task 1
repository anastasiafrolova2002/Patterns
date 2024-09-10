// создаем класс Student
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
