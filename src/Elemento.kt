data class Elemento(val letra: Char, var prioridad: Int) {

    operator fun compareTo (elemento: Elemento) : Int {
        if (this.prioridad > elemento.prioridad) {
            return 1
        } else {
            if (this.prioridad == elemento.prioridad) {
                return 0
            } else {
                return -1
            }
        }
    }
}