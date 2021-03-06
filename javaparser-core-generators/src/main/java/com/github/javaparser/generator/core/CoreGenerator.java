package com.github.javaparser.generator.core;

import com.github.javaparser.JavaParser;
import com.github.javaparser.generator.core.node.*;
import com.github.javaparser.generator.core.visitor.*;
import com.github.javaparser.utils.SourceRoot;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Generates all generated visitors in the javaparser-core module.
 */
public class CoreGenerator {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new RuntimeException("Need 1 parameter: the JavaParser source checkout root directory.");
        }
        final Path root = Paths.get(args[0], "..", "javaparser-core", "src", "main", "java");
        final SourceRoot sourceRoot = new SourceRoot(root);

        new CoreGenerator().run(sourceRoot);

        sourceRoot.saveAll();
    }

    private void run(SourceRoot sourceRoot) throws Exception {
        final JavaParser javaParser = new JavaParser();

        new GenericListVisitorAdapterGenerator(javaParser, sourceRoot).generate();
        new GenericVisitorAdapterGenerator(javaParser, sourceRoot).generate();
        new EqualsVisitorGenerator(javaParser, sourceRoot).generate();
        new VoidVisitorAdapterGenerator(javaParser, sourceRoot).generate();
        new VoidVisitorGenerator(javaParser, sourceRoot).generate();
        new GenericVisitorGenerator(javaParser, sourceRoot).generate();
        new HashCodeVisitorGenerator(javaParser, sourceRoot).generate();
        new CloneVisitorGenerator(javaParser, sourceRoot).generate();
        new TreeStructureVisitorGenerator(javaParser, sourceRoot).generate();
        new ModifierVisitorGenerator(javaParser, sourceRoot).generate();

        new GetNodeListsGenerator(javaParser, sourceRoot).generate();
        new PropertyGenerator(javaParser, sourceRoot).generate();
        new RemoveMethodGenerator(javaParser, sourceRoot).generate();
        new CloneGenerator(javaParser, sourceRoot).generate();
        new GetMetaModelGenerator(javaParser, sourceRoot).generate();
    }
}
