
fun main(args: Array<String>) {
    /*var e1 = Elemento('a',1)
    var e2 = Elemento('a',1)
    var e3 = Elemento('c',1)
    var e4 = Elemento('a',1)

    var a = Arbol(null)

    a.raiz = a.insertarNodo(a.raiz, e1)
    a.raiz = a.insertarNodo(a.raiz, e2)
    a.raiz = a.insertarNodo(a.raiz, e3)
    a.raiz = a.insertarNodo(a.raiz, e4)

    //a.imprimir(a.raiz)



    var str : String
    str = "hola como estas"

    var a2 = Arbol(Nodo(null,null, Elemento('h',1)))

    for (c in str) {
        //println(c)
        a2.raiz = a2.insertarNodo(a2.raiz, Elemento(c,1))
    }

    a2.imprimir(a2.raiz)
    println("Letra: " + a2.raiz?.dato?.letra + "  - Prioridad: " + a2.raiz?.dato?.prioridad)*/
    
    fun main(args: Array<String>) {
    val tree = AvlTree()
    println("Inserting values 1 to 10")
    for (i in 1..10) tree.insert(i)
    print("Printing key     : ")
    tree.printKey()
    print("Printing balance : ")
    tree.printBalance()
}
