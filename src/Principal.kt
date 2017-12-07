
fun main(args: Array<String>) {

    val arbol = Arbol(null)

    var str : String

    str = "bbbbeeeeeeetthh"

    for (c in str) {
        arbol.insertar(Elemento(c,1))
    }

    println(arbol.raiz?.dato.toString())
    arbol.imprimir(arbol.raiz)
}
