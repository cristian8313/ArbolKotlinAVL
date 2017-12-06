data class Nodo (var izquierdo: Nodo?, var derecho: Nodo?, var dato: Elemento) {

    fun enOrden() {
        if(this.izquierdo != null)
            this.izquierdo?.enOrden();

        println("Elemento: " + this.dato.toString());

        if(this.derecho != null)
            this.derecho?.enOrden();
    }
}