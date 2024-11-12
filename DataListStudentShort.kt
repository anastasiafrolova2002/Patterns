class DataListStudentShort(studentShortArray: List<StudentShort>) : DataList(studentShortArray) {
    override fun getNames() = listOf("ID", "ФИО", "Git", "Контакт")
    override fun getData() = DataTable(buildList {
        var count = 1
        for (index in getSelected()) {
            val student = array[index] as StudentShort
            add(listOf(count++, student.surnameWithInitials, student.git, student.contact))
        }
    })
}
