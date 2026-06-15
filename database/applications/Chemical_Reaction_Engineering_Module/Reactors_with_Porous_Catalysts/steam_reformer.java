/*
 * steam_reformer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:27 by COMSOL 6.3.0.290. */
public class steam_reformer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Porous_Catalysts");

    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"propane", "74-98-6", "C3H8", "COMSOL"}, 
         {"water", "7732-18-5", "H2O", "COMSOL"}, 
         {"hydrogen", "1333-74-0", "H2", "COMSOL"}, 
         {"carbon dioxide", "124-38-9", "CO2", "COMSOL"}, 
         {"carbon monoxide", "630-08-0", "CO", "COMSOL"}});
    model.thermodynamics().feature("pp1").set("phase_list", new String[][]{{"Gas", "Vapor"}});
    model.thermodynamics().feature("pp1").label("\u6c14\u4f53\u7cfb\u7edf 1");
    model.thermodynamics().feature("pp1").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp1").set("manager_version", "1.0");
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").set("package_desc", "\u5185\u7f6e\u5c5e\u6027\u5305");
    model.thermodynamics().feature("pp1").set("managerindex", "0");
    model.thermodynamics().feature("pp1").set("packageid", "COMSOL1");
    model.thermodynamics().feature("pp1").set("ThermodynamicModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("LiquidPhaseModel", "None");
    model.thermodynamics().feature("pp1").set("LiquidCard", "None");
    model.thermodynamics().feature("pp1").set("EOSModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasPhaseModel", "IdealGas");
    model.thermodynamics().feature("pp1").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp1").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp1").set("VapThermalConductivity", "KineticTheory");
    model.thermodynamics().feature("pp1").set("VapViscosity", "Brokaw");
    model.thermodynamics().feature("pp1").storePersistenceData();
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("chem", "Chemistry", "geom1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 3);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "C3H8");
    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 3);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "H2O");
    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 3);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "H2");
    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 3);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "CO2");
    model.component("comp1").physics("chem").create("spec1", "SpeciesChem", 3);
    model.component("comp1").physics("chem").feature("spec1").set("specName", "CO");
    model.component("comp1").physics("chem").prop("calcTransport").set("calcTransport", true);
    model.component("comp1").physics("chem").prop("mixture").set("ConcentrationType", "MassFraction");
    model.component("comp1").physics("chem").prop("mixture").set("Thermodynamics", true);
    model.component("comp1").physics("chem").prop("mixture").set("PropertyPackage", "pp1");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "propane", 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "0.2", 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "carbon monoxide", 1);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "0.2", 1);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "carbon dioxide", 2);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "0.2", 2);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "hydrogen", 3);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "0.2", 3);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("PackageSpecies", "water", 4);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationValue", "0.2", 4);
    model.component("comp1").physics("chem").prop("mixture").set("mixture", "gas");
    model.component("comp1").physics().move("chem", 0);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("u_in_tubes", "1[m/s]", "\u5165\u53e3\u901f\u5ea6\uff0c\u52a0\u70ed\u7ba1");
    model.param().set("T_in_tubes", "900[K]", "\u5165\u53e3\u6e29\u5ea6\uff0c\u52a0\u70ed\u7ba1");
    model.param().set("p_in_sr", "50[Pa]", "\u538b\u5dee");
    model.param().set("p_ref", "1e5[Pa]", "\u53c2\u8003\u538b\u529b");
    model.param().set("T_in_sr", "700[K]", "\u5165\u53e3\u6e29\u5ea6\uff0c\u91cd\u6574\u5e8a");
    model.param().set("h_tubes", "100[W/m^2/K]", "\u4f20\u70ed\u7cfb\u6570\uff0c\u52a0\u70ed\u7ba1");
    model.param().set("h_j", "1[W/m^2/K]", "\u4f20\u70ed\u7cfb\u6570\uff0c\u5939\u5957");
    model.param().set("w_C3H8_in", "0.2[1]", "\u4e19\u70f7\u7684\u5165\u53e3\u6743\u91cd\u5206\u6570");
    model.param().set("w_H2_in", "0.01[1]", "\u6c22\u7684\u5165\u53e3\u6743\u91cd\u5206\u6570");
    model.param().set("w_CO2_in", "0.01[1]", "\u4e8c\u6c27\u5316\u78b3\u7684\u5165\u53e3\u6743\u91cd\u5206\u6570");
    model.param().set("w_CO_in", "0[1]", "\u4e00\u6c27\u5316\u78b3\u7684\u5165\u53e3\u6743\u91cd\u5206\u6570");
    model.param().set("dens_foam", "24[kg/m^3]", "\u5bc6\u5ea6\uff0c\u7edd\u7f18\u6ce1\u6cab\u5851\u6599");
    model.param().set("Cp_foam", "1.9[J/kg/K]", "\u70ed\u5bb9\uff0c\u7edd\u7f18\u6ce1\u6cab\u5851\u6599");
    model.param()
         .set("k_foam", "0.027[W/m/K]", "\u5bfc\u70ed\u7cfb\u6570\uff0c\u7edd\u7f18\u6ce1\u6cab\u5851\u6599");
    model.param().set("Cp_pm", "2800[J/kg/K]", "\u70ed\u5bb9\uff0c\u591a\u5b54\u57fa\u4f53");
    model.param().set("dens_pm", "2000[kg/m^3]", "\u5bc6\u5ea6\uff0c\u591a\u5b54\u57fa\u4f53");
    model.param().set("k_pm", "0.1[W/m/K]", "\u5bfc\u70ed\u7cfb\u6570\uff0c\u591a\u5b54\u57fa\u4f53");
    model.param()
         .set("A", "7e5[1/s]", "\u53cd\u5e94 1 \u7684\u963f\u7d2f\u5c3c\u4e4c\u65af\u9891\u7387\u56e0\u5b50");
    model.param()
         .set("Ea", "83.14e3[J/mol]", "\u53cd\u5e94 1 \u7684\u963f\u7d2f\u5c3c\u4e4c\u65af\u6d3b\u5316\u80fd");
    model.param()
         .set("A_wgs", "1.6e5[m^3/s/mol]", "\u53cd\u5e94 2 \u7684\u963f\u7d2f\u5c3c\u4e4c\u65af\u9891\u7387\u56e0\u5b50");
    model.param()
         .set("Ea_wgs", "112e3[J/mol]", "\u53cd\u5e94 2 \u7684\u963f\u7d2f\u5c3c\u4e4c\u65af\u6d3b\u5316\u80fd");
    model.param().set("porosity", "0.25[1]", "\u5e8a\u5c42\u7a7a\u9699\u7387");
    model.param().set("kappa_pm", "1e-9[m^2]", "\u591a\u5b54\u57fa\u4f53\u7684\u6e17\u900f\u7387");
    model.param().set("T_amb", "300[K]", "\u73af\u5883\u6e29\u5ea6\uff08\u5916\u5939\u5957\uff09");

    model.component("comp1").geom("geom1").insertFile("steam_reformer_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("sel1");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_wp1_adjsel1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u50ac\u5316\u5e8a");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u7edd\u70ed\u6750\u6599");
    model.component("comp1").material("mat2").selection().named("geom1_ext1_dom");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat3").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").label("Air");
    model.component("comp1").material("mat3").set("family", "air");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat3").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat3").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat3").materialType("nonSolid");
    model.component("comp1").material("mat3").selection().named("geom1_ext3_dom");
    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material("pmat1").selection().named("geom1_ext2_dom");

    model.component("comp1").physics().create("tcs", "ConcentratedSpecies", "geom1");
    model.component("comp1").physics("tcs").prop("TransportMechanism").set("MassTransferInPorousMedia", true);
    model.component("comp1").physics("tcs").field("massfraction")
         .component(new String[]{"w1", "w2", "w3", "w4", "w5"});
    model.component("comp1").physics("tcs").field("massfraction").component(1, "w_H2O");
    model.component("comp1").physics("tcs").field("massfraction").component(2, "w_C3H8");
    model.component("comp1").physics("tcs").field("massfraction").component(3, "w_H2");
    model.component("comp1").physics("tcs").field("massfraction").component(4, "w_CO2");
    model.component("comp1").physics("tcs").field("massfraction").component(5, "w_CO");
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics("dl").prop("PhysicalModelProperty").set("pref", "p_ref");
    model.component("comp1").physics("dl").field("pressure").field("p_sr");
    model.component("comp1").physics().create("ht", "PorousMediaHeatTransfer", "geom1");
    model.component("comp1").physics("ht").field("temperature").field("T_sr");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht2", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht2").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht2");

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("pref", "p_ref");
    model.component("comp1").physics("spf").field("pressure").field("p_tubes");
    model.component("comp1").physics("ht2").field("temperature").field("T_tubes");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tcs");
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "w_C3H8", 0, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "w_CO", 1, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "w_CO2", 2, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "w_H2", 3, 0);
    model.component("comp1").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("ConcentrationInput", "w_H2O", 4, 0);
    model.component("comp1").physics("chem").create("rch1", "ReactionChem", 3);
    model.component("comp1").physics("chem").feature("rch1").set("formula", "C3H8 + 6 H2O => 10 H2 + 3 CO2");
    model.component("comp1").physics("chem").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("chem").feature("rch1").set("r", "chem.kf_1*chem.c_C3H8");
    model.component("comp1").physics("chem").feature("rch1").set("bulkFwdOrder", 1);
    model.component("comp1").physics("chem").feature("rch1").set("useArrhenius", true);
    model.component("comp1").physics("chem").feature("rch1").set("Af", "A");
    model.component("comp1").physics("chem").feature("rch1").set("Ef", "Ea");
    model.component("comp1").physics("chem").create("rch2", "ReactionChem", 3);
    model.component("comp1").physics("chem").feature("rch2").set("formula", "CO+H2O<=>CO2+H2");
    model.component("comp1").physics("chem").feature("rch2").set("setKeq0", true);
    model.component("comp1").physics("chem").feature("rch2").set("useArrhenius", true);
    model.component("comp1").physics("chem").feature("rch2").set("Af", "A_wgs");
    model.component("comp1").physics("chem").feature("rch2").set("Ef", "Ea_wgs");
    model.component("comp1").physics("tcs").label("\u53cd\u5e94\u5e8a\u4e2d\u7684\u6d53\u7269\u8d28\u4f20\u9012");
    model.component("comp1").physics("tcs").selection().named("geom1_ext2_dom");
    model.component("comp1").physics("tcs").prop("TransportMechanism").set("DiffusionModel", "MaxwellStefan");
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", "w_C3H8_in", 1);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", "w_H2_in", 2);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", "w_CO2_in", 3);
    model.component("comp1").physics("tcs").feature("init1").setIndex("w0", "w_CO_in", 4);
    model.component("comp1").physics("tcs").create("porous1", "PorousMedium", 3);
    model.component("comp1").physics("tcs").feature("porous1").selection().named("geom1_ext2_dom");
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1")
         .setIndex("DiffusivityFrom", "comp1.chem.D_C3H8_H2O", 0, 0);
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1")
         .setIndex("DiffusivityFrom", "comp1.chem.D_H2_H2O", 1, 0);
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1")
         .setIndex("DiffusivityFrom", "comp1.chem.D_CO2_H2O", 2, 0);
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1")
         .setIndex("DiffusivityFrom", "comp1.chem.D_CO_H2O", 3, 0);
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1")
         .setIndex("DiffusivityFrom", "comp1.chem.D_C3H8_H2", 4, 0);
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1")
         .setIndex("DiffusivityFrom", "comp1.chem.D_C3H8_CO2", 5, 0);
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1")
         .setIndex("DiffusivityFrom", "comp1.chem.D_C3H8_CO", 6, 0);
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1")
         .setIndex("DiffusivityFrom", "comp1.chem.D_CO2_H2", 7, 0);
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1")
         .setIndex("DiffusivityFrom", "comp1.chem.D_CO_H2", 8, 0);
    model.component("comp1").physics("tcs").feature("porous1").feature("fluid1")
         .setIndex("DiffusivityFrom", "comp1.chem.D_CO_CO2", 9, 0);
    model.component("comp1").physics("tcs").feature("porous1").feature("pm1").set("epsilon_p_mat", "from_mat");
    model.component("comp1").physics("tcs").create("reac1", "ReactionSources", 3);
    model.component("comp1").physics("tcs").feature("reac1").selection().named("geom1_ext2_dom");
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_w_C3H8_src", "root.comp1.chem.Rw_C3H8", 0);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_w_H2_src", "root.comp1.chem.Rw_H2", 0);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_w_CO2_src", "root.comp1.chem.Rw_CO2", 0);
    model.component("comp1").physics("tcs").feature("reac1").setIndex("R_w_CO_src", "root.comp1.chem.Rw_CO", 0);
    model.component("comp1").physics("tcs").feature("reac1").set("ReactingVolumeType", "PoreVolume");
    model.component("comp1").physics("tcs").create("in1", "Inflow", 2);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("J_in_C3H8", "intop1(tcs.rho*dl.U*w_C3H8_in)");
    model.component("comp1").variable("var1").descr("J_in_C3H8", "\u8d28\u91cf\u6d41\u7387\uff0cC3H8");
    model.component("comp1").variable("var1").set("J_in_H2", "intop1(tcs.rho*dl.U*w_H2_in)");
    model.component("comp1").variable("var1").descr("J_in_H2", "\u8d28\u91cf\u6d41\u7387\uff0cH2");
    model.component("comp1").variable("var1").set("J_in_CO2", "intop1(tcs.rho*dl.U*w_CO2_in)");
    model.component("comp1").variable("var1").descr("J_in_CO2", "\u8d28\u91cf\u6d41\u7387\uff0cCO2");
    model.component("comp1").variable("var1").set("J_in_CO", "intop1(tcs.rho*dl.U*w_CO_in)");
    model.component("comp1").variable("var1").descr("J_in_CO", "\u8d28\u91cf\u6d41\u7387\uff0cCO");

    model.component("comp1").physics("tcs").feature("in1").selection().named("geom1_wp1_adjsel1");
    model.component("comp1").physics("tcs").feature("in1").set("MixtureSpecification", "MassFlowRates");
    model.component("comp1").physics("tcs").feature("in1").setIndex("J0", "J_in_C3H8", 1);
    model.component("comp1").physics("tcs").feature("in1").setIndex("J0", "J_in_H2", 2);
    model.component("comp1").physics("tcs").feature("in1").setIndex("J0", "J_in_CO2", 3);
    model.component("comp1").physics("tcs").feature("in1").setIndex("J0", "J_in_CO", 4);
    model.component("comp1").physics("tcs").create("out1", "Outflow", 2);
    model.component("comp1").physics("tcs").feature("out1").selection().named("geom1_difsel3");
    model.component("comp1").physics("dl").label("\u53cd\u5e94\u5e8a\u4e2d\u7684\u8fbe\u897f\u5b9a\u5f8b");
    model.component("comp1").physics("dl").selection().named("geom1_ext2_dom");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("rho_mat", "root.comp1.tcs.rho");
    model.component("comp1").physics("dl").feature("porous1").feature("fluid1").set("mu_mat", "root.comp1.chem.eta");
    model.component("comp1").physics("dl").create("inl1", "Inlet", 2);
    model.component("comp1").physics("dl").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("dl").feature("inl1").set("p0", "p_in_sr");
    model.component("comp1").physics("dl").feature("inl1").selection().named("geom1_wp1_adjsel1");
    model.component("comp1").physics("dl").create("out1", "Outlet", 2);
    model.component("comp1").physics("dl").feature("out1").selection().named("geom1_difsel3");
    model.component("comp1").physics("dl").feature("out1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("dl").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("dl").feature("sym1").selection().named("geom1_intsel5");
    model.component("comp1").physics("ht")
         .label("\u53cd\u5e94\u5e8a\u4e2d\u7684\u591a\u5b54\u4ecb\u8d28\u4f20\u70ed");
    model.component("comp1").physics("ht").selection().set(1, 3);
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("u_src", "root.comp1.dl.u");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("k_mat", "root.comp1.chem.kxx");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1").set("rho_mat", "root.comp1.tcs.rho");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1")
         .set("Cp_mat", "root.comp1.chem.Cptot");
    model.component("comp1").physics("ht").feature("porous1").feature("fluid1")
         .set("gamma_not_IG_mat", "root.comp1.chem.gamma");
    model.component("comp1").physics("ht").feature("porous1").feature("pm1")
         .set("porousMatrixPropertiesType", "solidPhaseProperties");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_in_sr");
    model.component("comp1").physics("ht").create("solid1", "SolidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("solid1").selection().named("geom1_ext1_dom");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().named("geom1_wp1_adjsel1");
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_in_sr");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().named("geom1_difsel3");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("geom1_intsel2");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("ht").feature("hf1").set("h", "h_tubes");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T_tubes");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf2").selection().named("geom1_sel1");
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "h_j");
    model.component("comp1").physics("ht").feature("hf2").set("Text", "T_amb");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").set("Q0", "porosity*chem.Qtot");
    model.component("comp1").physics("ht").feature("hs1").selection().named("geom1_ext2_dom");
    model.component("comp1").physics("spf").label("\u52a0\u70ed\u7ba1\u4e2d\u7684\u5c42\u6d41");
    model.component("comp1").physics("spf").selection().named("geom1_ext3_dom");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_difsel1");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "u_in_tubes");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").label("\u51fa\u53e3\u9006\u6d41");
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_wp1_c2_bnd");
    model.component("comp1").physics("spf").feature("out1").set("NormalFlow", true);
    model.component("comp1").physics("spf").create("out2", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out2").label("\u51fa\u53e3\u5e76\u6d41");
    model.component("comp1").physics("spf").feature("out2").selection().named("geom1_difsel2");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("geom1_intsel4");
    model.component("comp1").physics("ht2").label("\u52a0\u70ed\u7ba1\u4e2d\u7684\u4f20\u70ed");
    model.component("comp1").physics("ht2").selection().named("geom1_ext3_dom");
    model.component("comp1").physics("ht2").feature("init1").set("Tinit", "T_in_tubes");
    model.component("comp1").physics("ht2").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht2").feature("temp1").label("\u5165\u53e3\u6e29\u5ea6");
    model.component("comp1").physics("ht2").feature("temp1").selection().named("geom1_difsel1");
    model.component("comp1").physics("ht2").feature("temp1").set("T0", "T_in_tubes");
    model.component("comp1").physics("ht2").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht2").feature("ofl1").label("\u6d41\u51fa\u9006\u6d41");
    model.component("comp1").physics("ht2").feature("ofl1").selection().named("geom1_wp1_c2_bnd");
    model.component("comp1").physics("ht2").feature().duplicate("ofl2", "ofl1");
    model.component("comp1").physics("ht2").feature("ofl2").label("\u6d41\u51fa\u5e76\u6d41");
    model.component("comp1").physics("ht2").feature("ofl2").selection().named("geom1_difsel2");
    model.component("comp1").physics("ht2").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht2").feature("hf1").label("\u53cd\u5e94\u5e8a\u7684\u70ed\u901a\u91cf");
    model.component("comp1").physics("ht2").feature("hf1").selection().named("geom1_intsel2");
    model.component("comp1").physics("ht2").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht2").feature("hf1").set("h", "h_tubes");
    model.component("comp1").physics("ht2").feature("hf1").set("Text", "T_sr");

    model.component("comp1").material("pmat1").propertyGroup("def")
         .set("hydraulicpermeability", new String[]{"kappa_pm"});
    model.component("comp1").material("pmat1").feature().create("solid1", "Solid", "comp1");
    model.component("comp1").material("pmat1").feature("solid1").set("link", "mat1");
    model.component("comp1").material("pmat1").feature("solid1").set("vfrac", "1-porosity");
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"k_pm"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"dens_pm"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"Cp_pm"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k_foam"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"dens_foam"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"Cp_foam"});

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().named("geom1_wp1_adjsel1");
    model.component("comp1").mesh("mesh1").feature("fq1").feature().create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 5);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmax", "2e-3/1.08");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "2e-3");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "1e-3");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_wp1_c2_bnd");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(4, 9, 13, 17);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().named("geom1_wp1_c2_bnd");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(1, 4, 13, 17);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection()
         .set(5, 8, 16, 17, 19, 21, 24, 27);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "3e-4");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 50);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("bl2", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl2").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl2").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl2").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl2").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl2").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").selection().named("geom1_difsel1");
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blnlayers", 6);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blhmin", "0.0003");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").stat().setQualityMeasure("skewness");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tcs", true);
    model.study("std1").feature("stat").setSolveFor("/physics/dl", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht2", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").label("\u9006\u6d41 T_in_tubes = 900K");
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");
    model.study("std1").feature("stat").setSolveFor("/physics/chem", false);
    model.study("std1").feature("stat").setSolveFor("/physics/tcs", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht2", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").feature().duplicate("stat1", "stat");
    model.study("std1").feature("stat1").setSolveFor("/physics/dl", false);
    model.study("std1").feature("stat1").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat1").set("useadvanceddisable", true);
    model.study("std1").feature("stat1").set("disabledphysics", new String[]{"spf/out2", "ht2/ofl2"});
    model.study("std1").feature().duplicate("stat2", "stat1");
    model.study("std1").feature("stat2").set("useadvanceddisable", false);
    model.study("std1").feature("stat2").setSolveFor("/physics/chem", true);
    model.study("std1").feature("stat2").setSolveFor("/physics/tcs", true);
    model.study("std1").feature("stat2").setSolveFor("/physics/dl", true);
    model.study("std1").feature("stat2").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat2").setSolveFor("/physics/ht2", true);
    model.study("std1").feature("stat2").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("stat2").set("useadvanceddisable", true);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s3").create("fc1", "FullyCoupled");

    model.study().create("std2");
    model.study("std2").label("\u5e76\u6d41 T_in_tubes = 900\uff0c1000 K");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature().copy("stat", "std1/stat");
    model.study("std2").feature().copy("stat1", "std1/stat1");
    model.study("std2").feature().copy("stat2", "std1/stat2");
    model.study("std2").feature("stat1").set("disabledphysics", new String[]{"spf/out1", "ht2/ofl1"});
    model.study("std2").feature("stat2").set("disabledphysics", new String[]{"spf/out1", "ht2/ofl1"});
    model.study("std2").showAutoSequences("all");

    model.sol("sol4").feature("s3").create("fc1", "FullyCoupled");

    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "u_in_tubes", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m/s", 0);
    model.study("std2").feature("param").setIndex("pname", "u_in_tubes", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m/s", 0);
    model.study("std2").feature("param").setIndex("pname", "T_in_tubes", 0);
    model.study("std2").feature("param").setIndex("plistarr", "900 1000", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickx", "L/2");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("L/2 \u5904\u7684 w_C3H8 \u548c T");
    model.result("pg1").set("data", "cpl1");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "w_C3H8");
    model.result("pg1").run();
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("expr", "chem.T");
    model.result("pg1").feature("con1").set("contourtype", "tubes");
    model.result("pg1").run();
    model.result("pg1").feature("con1").set("colortable", "Wave");

    model.component("comp1").view("view1").set("showgrid", false);

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u9006\u6d41\u6a21\u5f0f\u7684\u8d28\u91cf\u5206\u6570");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(3);
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("autoexpr", true);
    model.result("pg2").feature("lngr1").set("autosolution", false);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("lngr2", "lngr1");
    model.result("pg2").run();
    model.result("pg2").feature("lngr2").set("expr", "w_C3H8");
    model.result("pg2").feature().duplicate("lngr3", "lngr2");
    model.result("pg2").run();
    model.result("pg2").feature("lngr3").set("expr", "w_CO");
    model.result("pg2").feature().duplicate("lngr4", "lngr3");
    model.result("pg2").run();
    model.result("pg2").feature("lngr4").set("expr", "w_CO2");
    model.result("pg2").feature().duplicate("lngr5", "lngr4");
    model.result("pg2").run();
    model.result("pg2").feature("lngr5").set("expr", "w_H2");
    model.result("pg2").run();
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u8d28\u91cf\u5206\u6570");
    model.result("pg2").set("legendpos", "uppermiddle");
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").label("\u9006\u6d41\u955c\u50cf");
    model.result().dataset("mir1").set("quickplane", "xy");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u8d28\u91cf\u5206\u6570\uff0cC3H8 \u9006\u6d41");
    model.result("pg3").set("data", "mir1");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("edges", false);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "w_C3H8");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().named("geom1_intsel2");
    model.result("pg3").feature("surf1").feature("sel1").selection().set(6, 8, 11, 14, 15, 16, 18, 20, 21);
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("expr", "1");
    model.result("pg3").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg3").feature("surf2").feature("mtrl1").set("family", "steelanodized");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").create("sel1", "Selection");
    model.result("pg3").feature("surf2").feature("sel1").selection().set(9, 10, 12, 26);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u6d53\u5ea6\uff0cH2 \u9006\u6d41");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "tcs.c_w_H2");
    model.result("pg4").feature("surf1").set("descr", "\u6469\u5c14\u6d53\u5ea6");
    model.result("pg4").run();
    model.result("pg3").run();
    model.result().duplicate("pg5", "pg3");
    model.result("pg5").run();
    model.result("pg5").label("\u6c14\u4f53\u5bc6\u5ea6\uff0c\u9006\u6d41 900 K");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "dl.rho");
    model.result("pg5").feature("surf1").set("descr", "\u6d41\u4f53\u5bc6\u5ea6");
    model.result("pg5").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u6e29\u5ea6\u9006\u6d41");
    model.result("pg6").set("data", "mir1");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("edges", false);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "T_tubes");
    model.result("pg6").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg6").create("slc1", "Slice");
    model.result("pg6").feature("slc1").set("expr", "T_sr");
    model.result("pg6").feature("slc1").set("inheritplot", "surf1");
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").feature("surf2").set("expr", "1");
    model.result("pg6").feature("surf2").create("sel1", "Selection");
    model.result("pg6").feature("surf2").feature("sel1").selection().set(9, 10, 12, 26);
    model.result("pg6").run();
    model.result("pg6").feature("surf2").create("mtrl1", "MaterialAppearance");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").feature("mtrl1").set("appearance", "custom");
    model.result("pg6").feature("surf2").feature("mtrl1").set("family", "steelanodized");
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf3", "surf2");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf3").feature("sel1").selection().set(11);
    model.result("pg6").run();
    model.result("pg6").feature("surf3").create("tran1", "Transparency");
    model.result("pg6").run();
    model.result("pg6").feature("surf3").feature("tran1").set("transparency", 0.75);
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"ht.tfluxx", "ht.tfluxy", "ht.tfluxz"});
    model.result("pg6").feature("arws1").set("descr", "\u603b\u70ed\u901a\u91cf");
    model.result("pg6").feature("arws1").set("placement", "uniformani");
    model.result("pg6").feature("arws1").set("arrowcount", 150);
    model.result("pg6").feature("arws1").set("weight", new double[]{1, 1, 1.5});
    model.result("pg6").feature("arws1").set("arrowbase", "center");
    model.result("pg6").feature("arws1").set("scaleactive", true);
    model.result("pg6").feature("arws1").set("scale", 3.6E-6);
    model.result("pg6").feature("arws1").set("color", "gray");
    model.result("pg6").feature("arws1").create("sel1", "Selection");
    model.result("pg6").feature("arws1").feature("sel1").selection().set(2, 7);
    model.result("pg6").create("arws2", "ArrowSurface");
    model.result("pg6").feature("arws2").set("expr", new String[]{"ht2.tfluxx", "ht2.tfluxy", "ht2.tfluxz"});
    model.result("pg6").feature("arws2").set("descr", "\u603b\u70ed\u901a\u91cf");
    model.result("pg6").feature("arws2").create("sel1", "Selection");
    model.result("pg6").feature("arws2").feature("sel1").selection().set(5);
    model.result("pg6").run();
    model.result("pg6").feature("arws2").set("expr", new String[]{"ht2.tfluxx", "ht2.tfluxy", "ht2.tfluxz"});
    model.result("pg6").feature("arws2").set("descr", "\u603b\u70ed\u901a\u91cf");
    model.result("pg6").feature("arws2").set("placement", "uniformani");
    model.result("pg6").feature("arws2").set("arrowcount", 40);
    model.result("pg6").feature("arws2").set("weight", new double[]{0.3, 1, 3});
    model.result("pg6").feature("arws2").set("scaleactive", true);
    model.result("pg6").feature("arws2").set("scale", "3E-8");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u901f\u5ea6\u9006\u6d41");
    model.result("pg7").set("data", "mir1");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("edges", false);
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").create("vol1", "Volume");
    model.result("pg7").feature("vol1").set("expr", "spf.U");
    model.result("pg7").feature("vol1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg7").feature("vol1").set("colortable", "Cividis");
    model.result("pg7").feature("vol1").create("sel1", "Selection");
    model.result("pg7").feature("vol1").feature("sel1").selection().named("geom1_ext3_dom");
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg7").feature("arws1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg7").feature("arws1").set("placement", "uniformani");
    model.result("pg7").feature("arws1").set("arrowcount", 40);
    model.result("pg7").feature("arws1").set("weight", new double[]{0.4, 1, 4});
    model.result("pg7").feature("arws1").set("arrowtype", "arrowhead");
    model.result("pg7").feature("arws1").set("color", "white");
    model.result("pg7").feature("arws1").create("sel1", "Selection");
    model.result("pg7").feature("arws1").feature("sel1").selection().named("geom1_intsel4");
    model.result("pg7").create("arwv1", "ArrowVolume");
    model.result("pg7").feature("arwv1").set("expr", new String[]{"dl.u", "dl.v", "dl.w"});
    model.result("pg7").feature("arwv1").set("descr", "\u603b\u8fbe\u897f\u901f\u5ea6\u573a");
    model.result("pg7").feature("arwv1").set("arrowlength", "normalized");
    model.result("pg7").feature("arwv1").create("col1", "Color");
    model.result("pg7").run();
    model.result("pg7").feature("arwv1").feature("col1").set("expr", "dl.U");
    model.result("pg7").feature("arwv1").feature("col1").set("descr", "\u603b\u8fbe\u897f\u901f\u5ea6\u5927\u5c0f");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "1");
    model.result("pg7").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg7").feature("surf1").feature("mtrl1").set("family", "steelanodized");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().set(9, 10, 11, 12);
    model.result("pg7").run();

    model.study("std2").createAutoSequences("all");

    model.sol().create("sol7");
    model.sol("sol7").study("std2");
    model.sol("sol7").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol7");
    model.batch("p1").run("compute");

    model.result().dataset().duplicate("mir2", "mir1");
    model.result().dataset("mir2").label("\u5e76\u6d41\u955c\u50cf");
    model.result().dataset("mir2").set("data", "dset7");
    model.result("pg2").run();
    model.result().duplicate("pg8", "pg2");
    model.result().duplicate("pg9", "pg3");
    model.result().duplicate("pg10", "pg4");
    model.result().duplicate("pg11", "pg5");
    model.result().duplicate("pg12", "pg6");
    model.result().duplicate("pg13", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u5e76\u6d41\u8d28\u91cf\u5206\u6570\uff0c1000 K");
    model.result("pg8").set("data", "dset7");
    model.result("pg8").setIndex("looplevelinput", "last", 0);
    model.result("pg8").set("legendpos", "upperright");
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").label("\u8d28\u91cf\u5206\u6570\uff0cC3H8 \u5e76\u6d41 1000 K");
    model.result("pg9").set("data", "mir2");
    model.result("pg9").run();
    model.result("pg10").run();
    model.result("pg10").label("\u6d53\u5ea6\uff0cH2 \u5e76\u6d41 1000 K");
    model.result("pg10").set("data", "mir2");
    model.result("pg10").run();
    model.result("pg11").run();
    model.result("pg11").label("\u6c14\u4f53\u5bc6\u5ea6\uff0c\u5e76\u6d41 1000 K");
    model.result("pg11").set("data", "mir2");
    model.result("pg11").run();
    model.result("pg12").run();
    model.result("pg12").label("\u6e29\u5ea6\uff0c\u5e76\u6d41 1000 K");
    model.result("pg12").set("data", "mir2");
    model.result("pg12").set("legendactive", true);
    model.result("pg12").set("legendprecision", 4);
    model.result("pg12").run();
    model.result("pg13").run();
    model.result("pg13").label("\u901f\u5ea6\u5e76\u6d41\uff0c1000 K");
    model.result("pg13").set("data", "mir2");
    model.result("pg13").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").add("plotgroup", "pg5");
    model.nodeGroup("grp1").add("plotgroup", "pg6");
    model.nodeGroup("grp1").add("plotgroup", "pg7");
    model.nodeGroup("grp1").label("\u9006\u6d41\uff0c900 K");

    model.result("pg8").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg8");
    model.nodeGroup("grp2").add("plotgroup", "pg9");
    model.nodeGroup("grp2").add("plotgroup", "pg10");
    model.nodeGroup("grp2").add("plotgroup", "pg11");
    model.nodeGroup("grp2").add("plotgroup", "pg12");
    model.nodeGroup("grp2").add("plotgroup", "pg13");
    model.nodeGroup("grp2").label("\u5e76\u6d41\uff0c1000 K");

    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").run();
    model.result("pg14").label("\u6cbf\u53cd\u5e94\u5e8a\u4e2d\u5fc3\u7ebf\u7684 w_C3H8 \u548c w_CO");
    model.result("pg14").set("titletype", "none");
    model.result("pg14").create("lngr1", "LineGraph");
    model.result("pg14").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg14").feature("lngr1").set("linewidth", "preference");
    model.result("pg14").feature("lngr1").label("C3H8 \u9006\u6d41 900 K");
    model.result("pg14").feature("lngr1").set("data", "dset1");
    model.result("pg14").feature("lngr1").selection().set(3);
    model.result("pg14").feature("lngr1").set("expr", "w_C3H8");
    model.result("pg14").feature("lngr1").set("xdata", "expr");
    model.result("pg14").feature("lngr1").set("xdataexpr", "x");
    model.result("pg14").feature("lngr1").set("legend", true);
    model.result("pg14").feature("lngr1").set("autoplotlabel", true);
    model.result("pg14").feature("lngr1").set("autosolution", false);
    model.result("pg14").feature().duplicate("lngr2", "lngr1");
    model.result("pg14").run();
    model.result("pg14").feature("lngr2").label("C3H8 \u5e76\u6d41 900 K");
    model.result("pg14").feature("lngr2").set("data", "dset7");
    model.result("pg14").feature("lngr2").setIndex("looplevelinput", "first", 0);
    model.result("pg14").feature().duplicate("lngr3", "lngr2");
    model.result("pg14").run();
    model.result("pg14").feature("lngr3").label("C3H8 \u5e76\u6d41 1000 K");
    model.result("pg14").feature("lngr3").setIndex("looplevelinput", "last", 0);
    model.result("pg14").run();
    model.result("pg14").run();
    model.result("pg14").feature().duplicate("lngr4", "lngr1");
    model.result("pg14").feature().duplicate("lngr5", "lngr2");
    model.result("pg14").feature().duplicate("lngr6", "lngr3");
    model.result("pg14").run();
    model.result("pg14").feature("lngr4").label("CO \u9006\u6d41 900 K");
    model.result("pg14").feature("lngr4").set("expr", "w_CO");
    model.result("pg14").feature("lngr4").set("linestyle", "dashed");
    model.result("pg14").feature("lngr4").set("linecolor", "cyclereset");
    model.result("pg14").run();
    model.result("pg14").feature("lngr5").label("CO \u5e76\u6d41 900 K");
    model.result("pg14").feature("lngr5").set("expr", "w_CO");
    model.result("pg14").feature("lngr5").set("linestyle", "dashed");
    model.result("pg14").run();
    model.result("pg14").feature("lngr6").label("CO \u5e76\u6d41 1000 K");
    model.result("pg14").feature("lngr6").set("expr", "w_CO");
    model.result("pg14").feature("lngr6").set("linestyle", "dashed");
    model.result("pg14").run();
    model.result("pg14").set("xlabelactive", true);
    model.result("pg14").set("ylabelactive", true);
    model.result("pg14").set("ylabel", "\u8d28\u91cf\u5206\u6570 C3H8");
    model.result("pg14").set("twoyaxes", true);
    model.result("pg14").set("yseclabelactive", true);
    model.result("pg14").set("yseclabel", "\u8d28\u91cf\u5206\u6570 CO");
    model.result("pg14").setIndex("plotonsecyaxis", true, 3, 1);
    model.result("pg14").setIndex("plotonsecyaxis", true, 4, 1);
    model.result("pg14").setIndex("plotonsecyaxis", true, 5, 1);
    model.result("pg14").run();
    model.result("pg14").set("legendpos", "middleright");
    model.result("pg14").run();
    model.result().duplicate("pg15", "pg14");
    model.result("pg15").run();
    model.result("pg15").label("\u6cbf\u53cd\u5e94\u5668\u7684\u6e29\u5ea6\u66f2\u7ebf");
    model.result("pg15").run();
    model.result("pg15").feature("lngr1").label("T_sr \u9006\u6d41 900 K");
    model.result("pg15").feature("lngr1").set("expr", "T_sr");
    model.result("pg15").run();
    model.result("pg15").feature("lngr2").label("T_sr \u5e76\u6d41 900 K");
    model.result("pg15").feature("lngr2").set("expr", "T_sr");
    model.result("pg15").run();
    model.result("pg15").feature("lngr3").label("T_sr \u5e76\u6d41 1000 K");
    model.result("pg15").feature("lngr3").set("expr", "T_sr");
    model.result("pg15").run();
    model.result("pg15").feature("lngr4").label("T_tubes \u9006\u6d41");
    model.result("pg15").feature("lngr4").selection().set(6);
    model.result("pg15").feature("lngr4").set("expr", "T_tubes");
    model.result("pg15").run();
    model.result("pg15").feature("lngr5").label("T_tubes \u5e76\u6d41 900 K");
    model.result("pg15").feature("lngr5").selection().set(6);
    model.result("pg15").feature("lngr5").set("expr", "T_tubes");
    model.result("pg15").run();
    model.result("pg15").feature("lngr6").label("T_tubes \u5e76\u6d41 1000 K");
    model.result("pg15").feature("lngr6").selection().set(6);
    model.result("pg15").feature("lngr6").set("expr", "T_tubes");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg15").run();
    model.result("pg15").run();
    model.result("pg15").set("twoyaxes", false);
    model.result("pg15").set("ylabel", "\u6e29\u5ea6 (K)");
    model.result("pg15").run();
    model.result("pg15").set("legendpos", "uppermiddle");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u53cd\u5e94\u5e8a\u6d41\u51fa\u7684\u5e73\u5747\u6e29\u5ea6");
    model.result().numerical("gev1").set("data", "dset7");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev1").setIndex("expr", "ht.ofl1.Tave", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u53cd\u5e94\u5e8a\u6d41\u51fa\u7684\u5e73\u5747\u6e29\u5ea6");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("\u52a0\u70ed\u7ba1\u6d41\u51fa\u7684\u5e73\u5747\u6e29\u5ea6");
    model.result().numerical("gev2").set("data", "dset7");
    model.result().numerical("gev2").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev2").setIndex("expr", "ht2.ofl2.Tave", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u52a0\u70ed\u7ba1\u6d41\u51fa\u7684\u5e73\u5747\u6e29\u5ea6");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result("pg6").run();

    model.title("\u84b8\u6c7d\u91cd\u6574\u5668");

    model
         .description("\u672c\u4f8b\u662f\u84b8\u6c7d\u91cd\u6574\u5668\u7684\u4e09\u7ef4\u6a21\u578b\uff0c\u7528\u4e8e\u56fa\u5b9a\u5f0f\u6c22\u71c3\u6599\u7535\u6c60\u3002\u5728\u63cf\u8ff0\u8fd9\u4e2a\u91cd\u6574\u5668\u65f6\uff0c\u7d27\u5bc6\u8026\u5408\u4e86\u8d28\u91cf\u65b9\u7a0b\u3001\u80fd\u91cf\u65b9\u7a0b\u548c\u52a8\u91cf\u65b9\u7a0b\uff0c\u8fd9\u662f\u5728\u201c\u5316\u5b66\u53cd\u5e94\u5de5\u7a0b\u6a21\u5757\u201d\u7684\u9884\u5b9a\u4e49\u7269\u7406\u573a\u63a5\u53e3\u4e2d\u8bbe\u7f6e\u7684\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();

    model.label("steam_reformer.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
