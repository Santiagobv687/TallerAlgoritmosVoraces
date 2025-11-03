def knapsack_memo(valor, peso, W, n, memo):
    if n == 0 or W == 0:
        return 0
    if (n, W) in memo:
        return memo[(n, W)]

    if peso[n - 1] > W:
        memo[(n, W)] = knapsack_memo(valor, peso, W, n - 1, memo)
    else:
        incluir = valor[n - 1] + knapsack_memo(valor, peso, W - peso[n - 1], n - 1, memo)
        excluir = knapsack_memo(valor, peso, W, n - 1, memo)
        memo[(n, W)] = max(incluir, excluir)

    return memo[(n, W)]

valores = [2, 5, 10, 14, 15]
pesos = [1, 3, 4, 5, 7]
W = 8
memo = {}
print(knapsack_memo(valores, pesos, W, len(valores), memo))

