
class Arbol (var raiz: Nodo?) {

    private fun crearNodo(elemento: Elemento) : Nodo {
        // crea un nuevo nodo y lo devuelve
        return Nodo(null, null, null, 0, elemento)
    }

    fun imprimir(raiz: Nodo?) {
        if (raiz != null)
            postOrden(raiz)
    }
    
    private fun postOrden(raiz : Nodo?) {
        if(raiz?.derecho != null)
            postOrden(raiz?.derecho)

        if(raiz?.izquierdo != null)
            postOrden(raiz?.izquierdo)

        println("Elemento: " + raiz?.dato.toString())

    }

    fun insert(elemento: Elemento): Boolean {
        if (raiz == null)
            raiz = crearNodo(elemento)
        else {
            var nodo: Nodo? = raiz
            var nuevoNodo: Nodo
            while (true) {
                if (nodo!!.dato.letra == elemento.letra) {
                    nodo!!.dato.prioridad += elemento.prioridad
                    return false
                }
                nuevoNodo = nodo
                val goLeft = nodo.dato.compareTo(elemento) > 0
                nodo = if (goLeft) 
                            nodo.izquierdo 
                        else 
                            nodo.derecho
                if (nodo == null) {
                    if (goLeft)
                        nuevoNodo.izquierdo = Nodo(null,null, nuevoNodo, 0, elemento)
                    else
                        nuevoNodo.derecho = Nodo(null,null, nuevoNodo, 0, elemento)
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
            if (height(otroNodo.izquierdo!!.izquierdo) >= height(otroNodo.izquierdo!!.derecho))
                otroNodo = rotateRight(otroNodo)
            else
                otroNodo = rotateLeftThenRight(otroNodo)
        else if (otroNodo.balance == 2)
            if (height(otroNodo.derecho!!.derecho) >= height(otroNodo.derecho!!.izquierdo))
                otroNodo = rotateLeft(otroNodo)
            else
                otroNodo = rotateRightThenLeft(otroNodo)
        if (otroNodo.padre != null) rebalance(otroNodo.padre!!)
        else raiz = otroNodo
    }
 
    private fun rotateLeft(unNodo: Nodo): Nodo {
        val nodoDerecho: Nodo? = unNodo.derecho
        nodoDerecho!!.padre = unNodo.padre
        unNodo.derecho = nodoDerecho.izquierdo
        if (unNodo.derecho != null) unNodo.derecho!!.padre = unNodo
        nodoDerecho.izquierdo = unNodo
        unNodo.padre = nodoDerecho
        if (nodoDerecho.padre != null) {
            if (nodoDerecho.padre!!.derecho == unNodo)
                nodoDerecho.padre!!.derecho = nodoDerecho
            else
                nodoDerecho.padre!!.izquierdo = nodoDerecho
        }
        setBalance(unNodo, nodoDerecho)
        return nodoDerecho
    }
 
    private fun rotateRight(nuNodo: Nodo): Nodo {
        val nodoIzquierdo: Nodo? = nuNodo.izquierdo
        nodoIzquierdo!!.padre = nuNodo.padre
        nuNodo.izquierdo = nodoIzquierdo.derecho
        if (nuNodo.izquierdo != null) nuNodo.izquierdo!!.padre = nuNodo
        nodoIzquierdo.derecho = nuNodo
        nuNodo.padre = nodoIzquierdo
        if (nodoIzquierdo.padre != null) {
            if (nodoIzquierdo.padre!!.derecho == nuNodo)
                nodoIzquierdo.padre!!.derecho = nodoIzquierdo
            else
                nodoIzquierdo.padre!!.izquierdo = nodoIzquierdo
        }
        setBalance(nuNodo, nodoIzquierdo)
        return nodoIzquierdo
    }
 
    private fun rotateLeftThenRight(unNodo: Nodo): Nodo {
        unNodo.izquierdo = rotateLeft(unNodo.izquierdo!!)
        return rotateRight(unNodo)
    }
 
    private fun rotateRightThenLeft(unNodo: Nodo): Nodo {
        unNodo.derecho = rotateRight(unNodo.derecho!!)
        return rotateLeft(unNodo)
    }
 
    private fun height(unNodo: Nodo?): Int {
        if (unNodo == null) return -1
        return 1 + Math.max(height(unNodo.izquierdo), height(unNodo.derecho))
    }
 
    private fun setBalance(vararg nodos: Nodo) {
        for (nodo in nodos) nodo.balance = height(nodo.derecho) - height(nodo.izquierdo)
    }

}
