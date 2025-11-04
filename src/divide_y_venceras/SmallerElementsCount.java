package divide_y_venceras;

public class SmallerElementsCount {
    public static int[] conteoElementosMenores(int[] numeros) {
        int n = numeros.length;
        int[] resultado = new int[n];
        if (n == 0) return resultado;

        // índices mantiene las posiciones originales de los elementos
        int[] indices = new int[n];
        int[] temp = new int[n];
        for (int i = 0; i < n; i++) indices[i] = i;

        conteoElementosMenoresRec(numeros, indices, temp, resultado, 0, n - 1);
        return resultado;
    }

    private static void conteoElementosMenoresRec(int[] numeros, int[] indices, int[] temp, int[] resultado, int izquierda, int derecha) {
        if (izquierda >= derecha) {
            return;
        }

        int mid = izquierda + (derecha - izquierda) / 2;
        conteoElementosMenoresRec(numeros, indices, temp, resultado, izquierda, mid);
        conteoElementosMenoresRec(numeros, indices, temp, resultado, mid + 1, derecha);
        mezclarYContar(numeros, indices, temp, resultado, izquierda, mid, derecha);
    }

    private static void mezclarYContar(int[] numeros, int[] indices, int[] temp, int[] resultado, int izquierda, int mid, int derecha) {
        // Copiamos el rango de índices a temp para mezclar sobre índices
        for (int i = izquierda; i <= derecha; i++) {
            temp[i] = indices[i];
        }

        int i = izquierda;       // cursor para la mitad izquierda en temp
        int j = mid + 1;         // cursor para la mitad derecha en temp
        int k = izquierda;       // cursor para escribir de vuelta en indices

        while (i <= mid && j <= derecha) {
            // Comparamos valores reales usando temp (que contiene índices originales)
            if (numeros[temp[i]] <= numeros[temp[j]]) {
                // Todos los elementos de la mitad derecha ya copiados antes que temp[i]
                // son menores que numeros[temp[i]]; la cantidad es j - (mid+1)
                resultado[temp[i]] += (j - (mid + 1));
                indices[k++] = temp[i++];
            } else {
                // temp[j] (derecha) es menor que temp[i], lo colocamos primero
                indices[k++] = temp[j++];
            }
        }

        while (i <= mid) {
            resultado[temp[i]] += (j - (mid + 1));
            indices[k++] = temp[i++];
        }

        while (j <= derecha) {
            indices[k++] = temp[j++];
        }
    }

    public static void main(String[] args) {
        int[] numeritos = {5, 2, 6, 1};
        int[] cuentas = conteoElementosMenores(numeritos);
        System.out.print("El conteo de elementos menores es: ");
        for (int cuenta : cuentas) {
            System.out.print(cuenta + " ");
        }
    }
}
