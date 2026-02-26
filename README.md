# 🧭 Travelling Salesman Problem – Heuristic Implementation  
### Algorithms and Optimization (AAO)

---

## 📌 About The Project

This project was developed for the **Algorithms and Optimization (AAO)** course and focuses on the implementation and analysis of **heuristic algorithms for the Travelling Salesman Problem (TSP)**.

The objective was to implement constructive heuristics and local search improvement procedures, and evaluate their performance using benchmark instances from **TSPLIB**.

The project includes:

- Constructive heuristics  
- Local search improvements (2-Opt and 3-Opt)  
- Hybrid approaches (construction + local search)  
- Automated testing on 30 TSPLIB instances  
- Comparison with known optimal solutions  

---

## 🚀 Implemented Algorithms

### 🏗️ Constructive Heuristics

- **Nearest Neighbor (Vizinho Mais Próximo)**
- **Cheapest Insertion (Menor Custo de Inserção)**

These algorithms build an initial feasible solution for the TSP.

---

### 🔎 Local Search Heuristics

- **2-Opt**
- **3-Opt**

These procedures improve an existing solution by iteratively replacing edges to reduce total tour distance.

---

### 🔄 Hybrid Approaches

The project combines constructive heuristics with local search:

- Nearest Neighbor + 2-Opt  
- Nearest Neighbor + 3-Opt  
- Cheapest Insertion + 2-Opt  
- Cheapest Insertion + 3-Opt  

---

## 📊 Benchmark Instances

The algorithms were tested using **30 TSPLIB instances**, including:

- `a280`
- `bier127`
- `ch130`
- `d198`
- `eil101`
- `kroA100`, `kroB100`, etc.
- `pr107`, `pr124`, `pr264`
- `rat195`
- `u159`

Optimal reference solutions are provided in:

```
SolucoesOtimas.pdf
```

---

## 🏗️ Project Structure

```
HeuristicasTSP/
│
├── 30problemasTSPLIB/          # Benchmark TSP instances
├── src/
│   ├── main/java/
│   │   ├── TSP/                # Core TSP model and graph representation
│   │   ├── Heuristicas/        # Heuristic algorithms
│   │   │   ├── HeuristicasConstrutivas/
│   │   │   └── HeuristicasPesquisaLocal/
│   │   ├── SolveHeuristicas/   # Hybrid algorithm execution
│   │   ├── Execucao/           # Execution classes per instance
│   │   └── Exceptions/         # Custom exception handling
│   │
│   └── test/java/              # Unit tests
│
├── build.gradle                # Gradle build configuration
└── gradlew                     # Gradle wrapper
```

---

## 🧠 Algorithms & Concepts Applied

- Greedy Algorithms  
- Local Search Optimization  
- Edge Exchange Techniques  
- Combinatorial Optimization  
- Graph Theory  
- Performance Analysis  
- Algorithmic Complexity  

---

## 🛠️ Technologies & Tools

### 💻 Language
- **Java**

### ⚙️ Build System
- **Gradle**

### 🧪 Testing
- JUnit-based unit testing

### 📚 Dataset
- **TSPLIB benchmark instances**

---

## ▶️ How to Run

### Using Gradle Wrapper:

```bash
cd HeuristicasTSP
./gradlew build
./gradlew run
```

(Windows)

```bash
gradlew.bat build
gradlew.bat run
```

---

## 📈 Objective of the Study

The main goal of this project was to:

- Compare constructive heuristics  
- Evaluate the impact of local search improvements  
- Analyze solution quality vs computational cost  
- Compare heuristic solutions with known optimal solutions  

---

# 🇵🇹 Versão em Português

## 📌 Sobre o Projeto

Este projeto foi desenvolvido no âmbito da unidade curricular de **Algoritmos e Análise de Otimização (AAO)** e consiste na implementação de **heurísticas para o Problema do Caixeiro Viajante (TSP)**.

Foram implementadas heurísticas construtivas e procedimentos de melhoria baseados em pesquisa local, avaliados através de instâncias clássicas da **TSPLIB**.

---

## 🚀 Algoritmos Implementados

### 🏗️ Heurísticas Construtivas

- Vizinho Mais Próximo  
- Menor Custo de Inserção  

### 🔎 Pesquisa Local

- 2-Opt  
- 3-Opt  

### 🔄 Métodos Híbridos

- Construção + 2-Opt  
- Construção + 3-Opt  

---

## 🎯 Objetivos

- Comparar qualidade das soluções  
- Avaliar melhoria com pesquisa local  
- Analisar custo computacional  
- Comparar com soluções ótimas conhecidas  

---

## 🧰 Ferramentas

- Java  
- Gradle  
- JUnit  
- Instâncias TSPLIB  

---

## 👨‍💻 Contexto Académico

Unidade Curricular: Algoritmos e Análise de Otimização  
Tema: Heurísticas para o Travelling Salesman Problem  
Tipo: Trabalho prático académico  

---
