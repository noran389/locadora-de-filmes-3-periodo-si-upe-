package org.locadora;

import org.locadora.model.Aluguel;
import org.locadora.model.Item;
import org.locadora.observer.Cliente;
import org.locadora.observer.Notificacao;
import org.locadora.strategy.CalculoFilme;
import org.locadora.strategy.CalculoJogo;
import org.locadora.strategy.CalculoLancamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class LocadoraMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Notificacao notificacao = new Notificacao();

        List<Item> catalogo = new ArrayList<>();
        catalogo.add(new Item("Vingadores: Ultimato", new CalculoFilme()));
        catalogo.add(new Item("Zelda: Breath of the Wild", new CalculoJogo()));
        catalogo.add(new Item("Duna 2 (Lançamento)", new CalculoLancamento()));

        List<Aluguel> alugueisAtivos = new ArrayList<>();

        System.out.println("=== SISTEMA DE LOCADORA (versão final) ===");

        boolean rodando = true;
        while (rodando) {
            System.out.println("\nMenu:");
            System.out.println("1 - Listar itens");
            System.out.println("2 - Alugar item");
            System.out.println("3 - Devolver item");
            System.out.println("4 - Mostrar aluguéis ativos");
            System.out.println("5 - Sair");
            System.out.print("Escolha: ");

            String entrada = sc.nextLine();
            if (entrada.isEmpty()) {
                System.out.println("Entrada vazia. Tente novamente.");
                continue;
            }

            int opcao;
            try {
                opcao = Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Catálogo ---");
                    for (int i = 0; i < catalogo.size(); i++) {
                        Item it = catalogo.get(i);
                        String status = it.estaDisponivel() ? "Disponível" : "Indisponível";
                        System.out.println((i + 1) + " - " + it.getNome() + " (" + status + ")");
                    }
                    break;

                case 2:
                    System.out.println("\nEscolha o número do item para alugar:");
                    for (int i = 0; i < catalogo.size(); i++) {
                        Item it = catalogo.get(i);
                        String status = it.estaDisponivel() ? "Disponível" : "Indisponível";
                        System.out.println((i + 1) + " - " + it.getNome() + " (" + status + ")");
                    }
                    System.out.print("Item (número): ");
                    int idxItem;
                    try {
                        idxItem = Integer.parseInt(sc.nextLine().trim()) - 1;
                    } catch (Exception e) {
                        System.out.println("Entrada inválida.");
                        break;
                    }

                    if (idxItem < 0 || idxItem >= catalogo.size()) {
                        System.out.println("Índice inválido.");
                        break;
                    }

                    Item item = catalogo.get(idxItem);
                    if (!item.estaDisponivel()) {
                        System.out.println("Desculpe, este item não está disponível.");
                        break;
                    }

                    System.out.print("Nome do cliente: ");
                    String nomeCliente = sc.nextLine().trim();
                    if (nomeCliente.isEmpty()) {
                        System.out.println("Nome inválido.");
                        break;
                    }
                    Cliente cliente = new Cliente(nomeCliente);

                    System.out.print("Quantos dias de aluguel? ");
                    int dias;
                    try {
                        dias = Integer.parseInt(sc.nextLine().trim());
                        if (dias <= 0) throw new NumberFormatException();
                    } catch (Exception e) {
                        System.out.println("Número de dias inválido.");
                        break;
                    }

                    Aluguel aluguel = new Aluguel(item, cliente, dias, notificacao);
                    aluguel.realizarAluguel();
                    alugueisAtivos.add(aluguel);
                    break;

                case 3:
                    if (alugueisAtivos.isEmpty()) {
                        System.out.println("Não há aluguéis ativos.");
                        break;
                    }
                    System.out.println("\n--- Aluguéis ativos ---");
                    for (int i = 0; i < alugueisAtivos.size(); i++) {
                        System.out.println((i + 1) + " - " + alugueisAtivos.get(i));
                    }
                    System.out.print("Escolha o número do aluguel para devolver: ");
                    int idxAluguel;
                    try {
                        idxAluguel = Integer.parseInt(sc.nextLine().trim()) - 1;
                    } catch (Exception e) {
                        System.out.println("Entrada inválida.");
                        break;
                    }
                    if (idxAluguel < 0 || idxAluguel >= alugueisAtivos.size()) {
                        System.out.println("Índice inválido.");
                        break;
                    }
                    Aluguel aluguelDevolver = alugueisAtivos.get(idxAluguel);
                    aluguelDevolver.devolver();
                    alugueisAtivos.remove(idxAluguel);
                    break;

                case 4:
                    if (alugueisAtivos.isEmpty()) {
                        System.out.println("Nenhum aluguel ativo.");
                    } else {
                        System.out.println("\n--- Aluguéis ativos ---");
                        for (Aluguel a : alugueisAtivos) {
                            System.out.println(a);
                        }
                    }
                    break;

                case 5:
                    rodando = false;
                    System.out.println("Encerrando sistema. Até mais!");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }
}
