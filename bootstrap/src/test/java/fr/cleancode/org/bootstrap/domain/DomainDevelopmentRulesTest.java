package fr.cleancode.org.bootstrap.domain;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import fr.cleancode.org.bootstrap.PackagesAndLayers;

import static com.tngtech.archunit.junit.CacheMode.FOREVER;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
        packages = "fr.cleancode.org.domain",
        cacheMode = FOREVER,
        importOptions = {DoNotIncludeTests.class})
public class DomainDevelopmentRulesTest {

    private static final String[] allowedPackages = {
            PackagesAndLayers.JAVA_PACKAGE, PackagesAndLayers.DOMAIN_PACKAGE, PackagesAndLayers.LOMBOK_PACKAGE, PackagesAndLayers.VAVR_PACKAGE, PackagesAndLayers.SLF4J
    };

    private static final String[] allowedAccessors = {
            PackagesAndLayers.DOMAIN_PACKAGE, PackagesAndLayers.BOOTSTRAP_PACKAGE, PackagesAndLayers.CLIENT_PACKAGE, PackagesAndLayers.SERVER_PACKAGE
    };

    @ArchTest
    public static final ArchRule DOMAIN_DEVELOPMENT_RULE =
            classes()
                    .that()
                    .resideInAPackage(PackagesAndLayers.DOMAIN_PACKAGE)
                    .should()
                    .onlyDependOnClassesThat()
                    .resideInAnyPackage(allowedPackages)
                    .andShould()
                    .onlyAccessClassesThat()
                    .resideInAnyPackage(allowedPackages)
                    .andShould()
                    .onlyBeAccessed()
                    .byClassesThat()
                    .resideInAnyPackage(allowedAccessors)
                    .andShould()
                    .onlyHaveDependentClassesThat()
                    .resideInAnyPackage(allowedAccessors);
}
