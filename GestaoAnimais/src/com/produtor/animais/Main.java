package com.produtor.animais;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Animal> animais = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean executar = true;

        while (executar) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Cadastrar um novo animal");
            System.out.println("2. Remover um animal");
            System.out.println("3. Exibir resumo");
            System.out.println("4. Sair");
            System.out.println("Digite a opção desejada:");

            int opcao;
            try {
                opcao = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Erro: Por favor, insira um número válido.");
                scanner.nextLine(); // Limpar o buffer
                continue;
            }

            switch (opcao) {
                case 1:
                    cadastrarAnimal(scanner);
                    break;
                case 2:
                    removerAnimal(scanner);
                    break;
                case 3:
                    exibirResumo();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    executar = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void cadastrarAnimal(Scanner scanner) {
        System.out.println("Digite o código do animal:");
        String codigo = scanner.next();

        // Verificar se o código já existe
        boolean codigoExiste = animais.stream()
                .anyMatch(animal -> animal.getCodigo().equals(codigo));
        if (codigoExiste) {
            System.out.println("Erro: Já existe um animal cadastrado com este código.");
            return;
        }

        try {
            System.out.println("Digite o peso do animal (em quilogramas):");
            double peso = scanner.nextDouble();

            System.out.println("Digite o sexo do animal (macho/fêmea):");
            String sexo = scanner.next().toLowerCase();

            if (!sexo.equals("macho") && !sexo.equals("fêmea")) {
                throw new IllegalArgumentException("Sexo inválido. Use 'macho' ou 'fêmea'.");
            }

            // Criar e adicionar o novo animal
            Animal novoAnimal = new Animal(codigo, peso, sexo);
            animais.add(novoAnimal);
            System.out.println("Animal cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: Entrada inválida. Certifique-se de digitar os valores corretamente.");
            scanner.nextLine(); // Limpar o buffer
        }
    }

    private static void removerAnimal(Scanner scanner) {
        System.out.println("Digite o código do animal a ser removido:");
        String codigo = scanner.next();

        // Buscar o animal com o código informado
        Animal animalRemover = animais.stream()
                .filter(animal -> animal.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);

        if (animalRemover != null) {
            animais.remove(animalRemover);
            System.out.println("Animal removido com sucesso.");
        } else {
            System.out.println("Erro: Nenhum animal encontrado com o código informado.");
        }
    }

    private static void exibirResumo() {
        System.out.println("\n=== Resumo ===");
        int totalAnimais = animais.size();
        long totalMachos = animais.stream().filter(animal -> animal.getSexo().equals("macho")).count();
        long totalFemeas = animais.stream().filter(animal -> animal.getSexo().equals("fêmea")).count();
        double pesoTotal = animais.stream().mapToDouble(Animal::getPeso).sum();
        double pesoMachos = animais.stream().filter(animal -> animal.getSexo().equals("macho"))
                .mapToDouble(Animal::getPeso).sum();
        double pesoFemeas = animais.stream().filter(animal -> animal.getSexo().equals("fêmea"))
                .mapToDouble(Animal::getPeso).sum();
        double pesoMaximo = animais.stream().mapToDouble(Animal::getPeso).max().orElse(0.0);
        double pesoMinimo = animais.stream().mapToDouble(Animal::getPeso).min().orElse(0.0);
        double mediaPeso = totalAnimais > 0 ? pesoTotal / totalAnimais : 0.0;

        // Exibir o resumo
        System.out.println("Quantidade total de animais: " + totalAnimais);
        System.out.println("Quantidade de machos: " + totalMachos);
        System.out.println("Quantidade de fêmeas: " + totalFemeas);
        System.out.printf("Percentual de machos: %.2f%%\n", (totalMachos * 100.0) / totalAnimais);
        System.out.printf("Percentual de fêmeas: %.2f%%\n", (totalFemeas * 100.0) / totalAnimais);
        System.out.println("Peso total dos animais: " + pesoTotal + " kg");
        System.out.printf("Peso total dos machos: %.2f kg (%.2f%%)\n", pesoMachos, (pesoMachos * 100.0) / pesoTotal);
        System.out.printf("Peso total das fêmeas: %.2f kg (%.2f%%)\n", pesoFemeas, (pesoFemeas * 100.0) / pesoTotal);
        System.out.println("Peso médio dos animais: " + mediaPeso + " kg");
        System.out.println("Peso do animal mais pesado: " + pesoMaximo + " kg");
        System.out.println("Peso do animal mais leve: " + pesoMinimo + " kg");
    }
}

