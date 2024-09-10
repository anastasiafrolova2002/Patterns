class Student(
    val id: Int,
    var firstName: String,
    var lastName: String,
    var middleName: String? = null,
    var phone: String? = null,
    var telegram: String? = null,
    var email: String? = null,
    var github: String? = null
) {
    // Вторичные конструкторы
    constructor(id: Int, firstName: String, lastName: String) : this(id, firstName, lastName, null, null, null, null, null)
    constructor(id: Int, firstName: String, lastName: String, middleName: String) : this(id, firstName, lastName, middleName, null, null, null, null)
    
}
