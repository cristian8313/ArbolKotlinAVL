
class Arbol (var raiz: Nodo?) {

    private fun crearNodo(elemento: Elemento) : Nodo {
        // crea un nuevo nodo y lo devuelve
        return Nodo(null, null, elemento)
    }

    fun imprimir(raiz: Nodo?) {
        if (raiz != null)
            raiz?.enOrden()
    }

    fun insertarNodo(raiz: Nodo?, elemento: Elemento) : Nodo {
        // inserta un dato nuevo en el árbol
        if (raiz == null) {
            // si no hay nodos en el árbol lo agrega
            return crearNodo(elemento)
        } else {
            // si hay nodos en el árbol lo recorre
            if (elemento.letra == raiz.dato?.letra) {
                raiz.dato.prioridad++
            } else {
                //if (elemento.prioridad <= raiz.dato.prioridad) {
                if (elemento.compareTo(raiz.dato) <= 0) {
                    // si el dato ingresado es  menor que el dato
                    // guardado va al subárbol izquierdo
                    raiz.izquierdo = insertarNodo(raiz.izquierdo, elemento)
                } else {
                    // si no, procesa el subárbol derecho
                    raiz.derecho = insertarNodo(raiz.derecho, elemento)
                }
            }
            return raiz
        }

    }

}