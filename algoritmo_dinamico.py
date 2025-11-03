def camino_minimo(matriz):
    if not matriz or not matriz[0]:
        return 0

    filas = len(matriz)
    columnas = len(matriz[0])

    # dp[j] almacenará el costo mínimo para llegar a la columna j en la fila actual
    dp = [0] * columnas

    # Inicializar primera fila
    dp[0] = matriz[0][0]
    for j in range(1, columnas):
        dp[j] = dp[j - 1] + matriz[0][j]

    # Iterar sobre las filas
    for i in range(1, filas):
        # Actualizar la primera columna (solo puede venir de arriba)
        dp[0] += matriz[i][0]

        # Actualizar las demás columnas
        for j in range(1, columnas):
            dp[j] = matriz[i][j] + min(dp[j], dp[j - 1])

    return dp[-1]


if __name__ == "__main__":
    matriz = [
        [1, 3, 1],
        [1, 5, 1],
        [4, 2, 1]
    ]

    resultado = camino_minimo(matriz)
    print("El costo mínimo total es:", resultado)