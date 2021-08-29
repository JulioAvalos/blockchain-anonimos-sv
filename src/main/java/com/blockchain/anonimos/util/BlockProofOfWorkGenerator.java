package com.blockchain.anonimos.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

/**
 * A Proof of Work algorithm (PoW) | (Algoritmo de prueba de trabajo):
 * Es el que detalle como los nuevos bloques son creados o minados en la blockchain.
 * El objetivo del algoritmo es encontrar un numero o un hash que resuelva
 * el problema esperado para poder minar el bloque.
 *
 * El numero debe ser dificil de encontrar humanamente, pero sencillo de
 * forma computacional por cualquiera que se comunique
 *
 * La idea de este algoritmo es evitar que un minero manipule la blockchain
 * con un ataque de 51%, en el qie clone el 51% de la blockchain, ya manupilada
 * para su propio beneficio
 *
 */

public class BlockProofOfWorkGenerator {
    /**
     * Algoritmo sencillo de PoW:
     *
     * Encontrar un numero p tal que su hash(pp') contenga 4 ceros al principio,
     * donde el numero p actual fue anteriormente el numero p'.
     *
     * p es el valor de la prueba anterior (previousProof), y p' es el nuevo valor de prueba (proof)
     *
     * Encontrar un numero p que al combinarlo con el hash de la solucion del bloque anterior,
     * logre producir un nuevo hash con 4 ceros al principio.
     *
     */
    public static String PROOF_OF_WORK = "0000";

    public static Long proofOfWork(Long lastProof) {
        long proof = 0L;
        while (!validProof(lastProof, proof))  proof += 1L;
        return proof;
    }

    public static boolean validProof(Long lastProof, Long proof) {
        String s = "" + lastProof + "" + proof;
        String sha256 = Hashing.sha256().hashString(s, StandardCharsets.UTF_8).toString();
        return sha256.endsWith(PROOF_OF_WORK);
    }
}
