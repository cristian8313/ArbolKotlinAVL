
class Arbol (var raiz: Nodo?) {

    private fun crearNodo(elemento: Elemento) : Nodo {
        // crea un nuevo nodo y lo devuelve
        return Nodo(null, null, 0, elemento)
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
    
    fun insert(key: Int): Boolean {
        if (root == null)
            root = Node(key, null)
        else {
            var n: Node? = root
            var parent: Node
            while (true) {
                if (n!!.key == key) return false
                parent = n
                val goLeft = n.key > key
                n = if (goLeft) n.left else n.right
                if (n == null) {
                    if (goLeft)
                        parent.left  = Node(key, parent)
                    else
                        parent.right = Node(key, parent)
                    rebalance(parent)
                    break
                }
            }
        }
        return true
    }
    
    
     private fun rebalance(n: Node) {
        setBalance(n)
        var nn = n
        if (nn.balance == -2)
            if (height(nn.left!!.left) >= height(nn.left!!.right))
                nn = rotateRight(nn)
            else
                nn = rotateLeftThenRight(nn)
        else if (nn.balance == 2)
            if (height(nn.right!!.right) >= height(nn.right!!.left))
                nn = rotateLeft(nn)
            else
                nn = rotateRightThenLeft(nn)
        if (nn.parent != null) rebalance(nn.parent!!)
        else root = nn
    }
 
    private fun rotateLeft(a: Node): Node {
        val b: Node? = a.right
        b!!.parent = a.parent
        a.right = b.left
        if (a.right != null) a.right!!.parent = a
        b.left = a
        a.parent = b
        if (b.parent != null) {
            if (b.parent!!.right == a)
                b.parent!!.right = b
            else
                b.parent!!.left = b
        }
        setBalance(a, b)
        return b
    }
 
    private fun rotateRight(a: Node): Node {
        val b: Node? = a.left
        b!!.parent = a.parent
        a.left = b.right
        if (a.left != null) a.left!!.parent = a
        b.right = a
        a.parent = b
        if (b.parent != null) {
            if (b.parent!!.right == a)
                b.parent!!.right = b
            else
                b.parent!!.left = b
        }
        setBalance(a, b)
        return b
    }
 
    private fun rotateLeftThenRight(n: Node): Node {
        n.left = rotateLeft(n.left!!)
        return rotateRight(n)
    }
 
    private fun rotateRightThenLeft(n: Node): Node {
        n.right = rotateRight(n.right!!)
        return rotateLeft(n)
    }
 
    private fun height(n: Node?): Int {
        if (n == null) return -1
        return 1 + Math.max(height(n.left), height(n.right))
    }
 
    private fun setBalance(vararg nodes: Node) {
        for (n in nodes) n.balance = height(n.right) - height(n.left)
    }

}
