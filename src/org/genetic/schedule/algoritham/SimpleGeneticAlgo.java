package org.genetic.schedule.algoritham;

import java.util.BitSet;

import io.jenetics.BitChromosome;
import io.jenetics.BitGene;
import io.jenetics.Genotype;
import io.jenetics.util.Factory;

public class SimpleGeneticAlgo {

	public static void main(String[] args) {
		
		/*BitChromosome bitChromosome=new BitChromosome(null,13, 0.5);
        Factory<Genotype<BitGene>> gtf = Genotype.of(bitChromosome);
        System.out.println("Before the evolution:\n" + gtf);*/
		
		System.out.println(Math.round(Math.random()));
	}
}

