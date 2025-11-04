package divide_y_venceras;

public class MajorityElement {
    public static int elementoMayoritario(int[] numeros) {
        if (numeros == null || numeros.length == 0) return -1;
        int candidato = elementoMayoritarioRec(numeros, 0, numeros.length - 1);
        int cuenta = contarEnRango(numeros, candidato, 0, numeros.length - 1);
        return cuenta > numeros.length / 2 ? candidato : -1;
    }

    private static int elementoMayoritarioRec(int[] numeros, int izq, int der) {
        // Caso base: solo un elemento
        if (izq == der) {
            return numeros[izq];
        }

        // Recurre en las mitades izquierda y derecha
        int mid = izq + (der - izq) / 2;
        int mayorIzquierda = elementoMayoritarioRec(numeros, izq, mid);
        int mayorDerecha = elementoMayoritarioRec(numeros, mid + 1, der);

        // Si ambas mitades coinciden en el elemento mayoritario
        if (mayorIzquierda == mayorDerecha) {
            return mayorIzquierda;
        }

        // Cuenta las apariciones de cada candidato en el rango actual
        int cuentaIzquierda = contarEnRango(numeros, mayorIzquierda, izq, der);
        int cuentaDerecha = contarEnRango(numeros, mayorDerecha, izq, der);

        // Devuelve el elemento con mayor cuenta
        return cuentaIzquierda > cuentaDerecha ? mayorIzquierda : mayorDerecha;
    }

    private static int contarEnRango(int[] numeros, int candidato, int izquierda, int derecha) {
        int cuenta = 0;
        for (int i = izquierda; i <= derecha; i++) {
            if (numeros[i] == candidato) {
                cuenta++;
            }
        }
        return cuenta;
    }

    public static void main(String[] args) {
        int[] ejemplo = {2,2,1,1,1,2,2};
        System.out.println("El elemento mayoritario es: " + elementoMayoritario(ejemplo));
    }
}
