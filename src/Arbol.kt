
class Arbol (var raiz: Nodo?) {

    private fun crearNodo(elemento: Elemento) : Nodo {
        // crea un nuevo nodo y lo devuelve
        return Nodo(null, null, 0, elemento)
    }

    fun imprimir(raiz: Nodo?) {
        if (raiz != null)
            raiz?.enOrden()
    }
    
    private fun enOrden(raiz : Nodo?) {
        if(raiz.izquierdo != null)
            raiz.izquierdo?.enOrden();

        println("Elemento: " + raiz.dato.toString());

        if(raiz.derecho != null)
            raiz.derecho?.enOrden();
    }

    /*fun insertarNodo(raiz: Nodo?, elemento: Elemento) : Nodo {
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
    }*/
    
    fun insert(elemento: Elemento): Boolean {
        if (raiz == null)
            raiz = crearNodo(elemento)
        else {
            var nodo: Nodo? = raiz
            var nuevoNodo: Nodo
            while (true) {
                if (nodo!!.dato.compareTo(elemento) == 0) return false
                nuevoNodo = nodo
                val goLeft = nodo.dato.compareTo(elemento) > 0
                nodo = if (goLeft) 
                            nodo.izquierda 
                        else 
                            nodo.derecha
                if (nodo == null) {
                    if (goLeft)
                        nuevoNodo.izquierda = Nodo(elemento, nuevoNodo)
                    else
                        nuevoNodo.derecha = Nodo(elemento, nuevoNodo)
                    rebalance(nuevoNodo)
                    break
                }
            }
        }
        return true
    }
    
    
     private fun rebalance(nodo: Nodo) {
        setBalance(nodo)
        var otroNodo = nodo
        if (otroNodo.balance == -2)
            if (height(otroNodo.izquierda!!.izquierda) >= height(otroNodo.izquierda!!.derecha))
                otroNodo = rotateRight(otroNodo)
            else
                otroNodo = rotateLeftThenRight(otroNodo)
        else if (otroNodo.balance == 2)
            if (height(otroNodo.derecha!!.derecha) >= height(otroNodo.derecha!!.izquierda))
                otroNodo = rotateLeft(otroNodo)
            else
                otroNodo = rotateRightThenLeft(otroNodo)
        if (otroNodo.padre != null) rebalance(otroNodo.padre!!)
        else raiz = otroNodo
    }
 
    private fun rotateLeft(unNodo: Nodo): Nodo {
        val nodoDerecho: Nodo? = unNodo.derecha
        nodoDerecho!!.padre = unNodo.padre
        unNodo.derecha = nodoDerecho.izquierda
        if (unNodo.derecha != null) unNodo.derecha!!.padre = unNodo
        nodoDerecho.izquierda = unNodo
        unNodo.padre = nodoDerecho
        if (b.padre != null) {
            if (nodoDerecho.padre!!.derecha == unNodo)
                nodoDerecho.padre!!.derecha = nodoDerecho
            else
                nodoDerecho.padre!!.izquierda = nodoDerecho
        }
        setBalance(unNodo, nodoDerecho)
        return nodoDerecho
    }
 
    private fun rotateRight(nuNodo: Nodo): Nodo {
        val nodoIzquierdo: Nodo? = nuNodo.izquierda
        nodoIzquierdo!!.padre = nuNodo.padre
        nuNodo.izquierda = nodoIzquierdo.derecha
        if (nuNodo.izquierda != null) nuNodo.izquierda!!.padre = nuNodo
        nodoIzquierdo.derecha = nuNodo
        nuNodo.padre = nodoIzquierdo
        if (nodoIzquierdo.padre != null) {
            if (nodoIzquierdo.padre!!.derecha == nuNodo)
                nodoIzquierdo.padre!!.derecha = nodoIzquierdo
            else
                nodoIzquierdo.padre!!.izquierda = nodoIzquierdo
        }
        setBalance(nuNodo, nodoIzquierdo)
        return nodoIzquierdo
    }
 
    private fun rotateLeftThenRight(unNodo: Nodo): Nodo {
        unNodo.left = rotateLeft(unNodo.left!!)
        return rotateRight(unNodo)
    }
 
    private fun rotateRightThenLeft(unNodo: Nodo): Nodo {
        unNodo.right = rotateRight(unNodo.right!!)
        return rotateLeft(unNodo)
    }
 
    private fun height(unNodo: Nodo?): Int {
        if (unNodo == null) return -1
        return 1 + Math.max(height(unNodo.izquierda), height(unNodo.derecha))
    }
 
    private fun setBalance(vararg nodos: Nodo) {
        for (nodo in nodes) nodo.balance = height(nodo.derecha) - height(nodo.izquierda)
    }

}
