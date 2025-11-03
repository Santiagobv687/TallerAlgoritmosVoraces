def knapsack_tab(valor, peso, W):
    n = len(valor)
    dp = [[0] * (W + 1) for _ in range(n + 1)]

    for i in range(1, n + 1):
        for w in range(1, W + 1):
            if peso[i - 1] <= w:
                dp[i][w] = max(valor[i - 1] + dp[i - 1][w - peso[i - 1]], dp[i - 1][w])
            else:
                dp[i][w] = dp[i - 1][w]

    return dp[n][W]

valores = [2, 5, 10, 14, 15]
pesos = [1, 3, 4, 5, 7]
W = 8
print(knapsack_tab(valores, pesos, W))
