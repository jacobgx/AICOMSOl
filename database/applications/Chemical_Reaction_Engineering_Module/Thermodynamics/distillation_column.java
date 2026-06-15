/*
 * distillation_column.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:28 by COMSOL 6.3.0.290. */
public class distillation_column {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Thermodynamics");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");
    model.component("comp1").physics("re").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re").feature("spec1").set("specName", "E");
    model.component("comp1").physics("re").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re").feature("spec1").set("specName", "W");

    model.thermodynamics().feature().create("pp1", "BuiltinPropertyPackage");
    model.thermodynamics().feature("pp1")
         .set("compoundlist", new String[][]{{"ethanol", "64-17-5", "C2H6O", "COMSOL"}, {"water", "7732-18-5", "H2O", "COMSOL"}});
    model.thermodynamics().feature("pp1")
         .set("phase_list", new String[][]{{"Vapor", "Vapor"}, {"Liquid", "Liquid"}});
    model.thermodynamics().feature("pp1").label("\u6c7d-\u6db2\u7cfb\u7edf 1");
    model.thermodynamics().feature("pp1").set("manager_id", "COMSOL");
    model.thermodynamics().feature("pp1").set("manager_version", "1.0");
    model.thermodynamics().feature("pp1").set("packagename", "pp1");
    model.thermodynamics().feature("pp1").set("package_desc", "\u5185\u7f6e\u5c5e\u6027\u5305");
    model.thermodynamics().feature("pp1").set("managerindex", "0");
    model.thermodynamics().feature("pp1").set("packageid", "COMSOL1");
    model.thermodynamics().feature("pp1").set("ThermodynamicModel", "NRTL");
    model.thermodynamics().feature("pp1").set("EOS", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").set("LiquidPhaseModel", "NRTL");
    model.thermodynamics().feature("pp1").set("LiquidCard", "LiquidPhaseModel");
    model.thermodynamics().feature("pp1").set("EOSModel", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").set("GasPhaseModel", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").set("GasEOSCard", "GasPhaseModel");
    model.thermodynamics().feature("pp1").set("EOS", "SoaveRedlichKwong");
    model.thermodynamics().feature("pp1").set("VapDiffusivity", "Automatic");
    model.thermodynamics().feature("pp1").set("VLSurfaceTension", "Ideal");
    model.thermodynamics().feature("pp1").set("VapThermalConductivity", "KineticTheory");
    model.thermodynamics().feature("pp1").set("VapViscosity", "Brokaw");
    model.thermodynamics().feature("pp1").set("LiqDiffusivity", "WesselinghKrishna");
    model.thermodynamics().feature("pp1").set("LiqDiffusivityAtInfDilution", "Automatic");
    model.thermodynamics().feature("pp1").set("LLSurfaceTension", "None");
    model.thermodynamics().feature("pp1").set("LiqThermalConductivity", "Ideal");
    model.thermodynamics().feature("pp1").set("LiqViscosity", "LogarithmicMassMixing");
    model.thermodynamics().feature("pp1").set("LiqVolume", "EOS");
    model.thermodynamics().feature("pp1").set("PoyntingFactor", "off");
    model.thermodynamics().feature("pp1").set("UseSaturatedVaporFugacity", "off");
    model.thermodynamics().feature("pp1")
         .set("property", new String[]{"Automatic", "Ideal", "KineticTheory", "Brokaw", "WesselinghKrishna", "Automatic", "None", "Ideal", "LogarithmicMassMixing", "EOS", 
         "off", "off"});
    model.thermodynamics().feature("pp1").storePersistenceData();
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});

    model.component("comp1").physics("re").prop("mixture").set("Thermodynamics", true);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "ethanol", 0, 0);
    model.component("comp1").physics("re").prop("SpeciesMatching").setIndex("PackageSpecies", "water", 1, 0);

    model.thermodynamics().feature("pp1").feature().create("flashcalc1", "FlashCalculationProperty");
    model.thermodynamics().feature("pp1").feature().remove("flashcalc1");
    model.thermodynamics().feature("pp1").feature().create("flashcalc1", "FlashCalculationProperty");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("funcname", "flashcalc1");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("property", "FlashCalculationProperty");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("propertydescr", "\u5e73\u8861\u8ba1\u7b97");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("dvars", new String[]{"0", "0", "0", "0"});
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("compounds", new String[]{"ethanol", "water"});
    model.thermodynamics().feature("pp1").feature("flashcalc1")
         .set("functions", new String[][]{{"Flash1_1_PhaseExist_Vapor", "1", "Presence of Vapor phase"}, 
         {"Flash1_1_PhaseExist_Liquid", "1", "Presence of Liquid phase"}, 
         {"Flash1_1_Temperature", "K", "Temperature"}, 
         {"Flash1_1_PhaseAmount_Vapor", "mol", "Amount in Vapor phase"}, 
         {"Flash1_1_PhaseAmount_Liquid", "mol", "Amount in Liquid phase"}, 
         {"Flash1_1_PhaseComposition_Vapor_ethanol", "mol/mol", "Fraction of ethanol in Vapor phase"}, 
         {"Flash1_1_PhaseComposition_Vapor_water", "mol/mol", "Fraction of water in Vapor phase"}, 
         {"Flash1_1_PhaseComposition_Liquid_ethanol", "mol/mol", "Fraction of ethanol in Liquid phase"}, 
         {"Flash1_1_PhaseComposition_Liquid_water", "mol/mol", "Fraction of water in Liquid phase"}});
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("cond1", new String[]{"pressure"});
    model.thermodynamics().feature("pp1").feature("flashcalc1")
         .set("cond2", new String[]{"phasefraction", "mole", "Vapor"});
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("prop_basis", "mole");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("inphase", "Flash1_1_PhaseExist");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("temperature", "Flash1_1_Temperature");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("pressure", "Flash1_1_Pressure");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("amounts", "Flash1_1_PhaseAmount");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("composition", "Flash1_1_PhaseComposition");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("soltype", "undefined");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("unit", "1");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("phases", new String[]{"Vapor", "Liquid"});
    model.thermodynamics().feature("pp1").feature("flashcalc1")
         .set("args", new String[][]{{"pressure", "Pa", "\u538b\u529b"}, 
         {"phasefraction", "mol/mol", "Vapor \u6469\u5c14\u5206\u6570"}, 
         {"ethanol", "mol", "\u6570\u91cf ethanol"}, 
         {"water", "mol", "\u6570\u91cf water"}});
    model.thermodynamics().feature("pp1").feature("flashcalc1")
         .set("plotargs", new String[][]{{"pressure", "101325", "101325"}, 
         {"phasefraction", "0", "0"}, 
         {"ethanol", "0", "0"}, 
         {"water", "0", "0"}});
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("derivatives", "");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("SecondDerivatives", "");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("SecondDerivativeIndices", "");
    model.thermodynamics().feature("pp1").feature("flashcalc1").set("plotfunction", "Flash1_1_PhaseExist_Vapor");
    model.thermodynamics().feature("pp1").set("WarningState", false);
    model.thermodynamics().feature("pp1").set("Warning", new String[]{""});

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "x_y");
    model.func("an1").set("expr", "Flash1_1_PhaseComposition_Vapor_ethanol(p,n,x1,x2)");
    model.func("an1").set("args", "p,n,x1,x2");
    model.func("an1").setIndex("argunit", "Pa", 0);
    model.func("an1").setIndex("argunit", 1, 1);
    model.func("an1").setIndex("argunit", "mol/mol", 2);
    model.func("an1").setIndex("argunit", "mol/mol", 3);
    model.func("an1").set("fununit", "mol/mol");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T", "298.15[K]", "\u6e29\u5ea6\u53c2\u6570");
    model.param().set("P", "101325[Pa]", "\u538b\u529b\u53c2\u6570");
    model.param().set("n", "1", "\u6c7d\u76f8\u5206\u6570");
    model.param().set("x1", "1", "\u7269\u8d28 1 \u7684\u6469\u5c14\u5206\u6570");
    model.param().set("x2", "1-x1", "\u7269\u8d28 2 \u7684\u6469\u5c14\u5206\u6570");
    model.param().set("F", "2500[mol/h]", "\u8fdb\u7ed9\u7387");
    model.param().set("xf", "0.5", "\u8fdb\u6599\u6469\u5c14\u5206\u6570\uff0c\u6613\u6325\u53d1\u7269\u8d28");
    model.param()
         .set("xd", "0.85", "\u998f\u51fa\u6db2\u6469\u5c14\u5206\u6570\uff0c\u6613\u6325\u53d1\u7269\u8d28");
    model.param()
         .set("xb", "0.05", "\u84b8\u998f\u6b8b\u6e23\u6469\u5c14\u5206\u6570\uff0c\u6613\u6325\u53d1\u7269\u8d28");
    model.param().set("Vr", "2.5*F", "\u7cbe\u998f\u84b8\u53d1\u7387");
    model.param().set("Vs", "Vr", "\u6c7d\u63d0\u84b8\u53d1\u7387");
    model.param().set("B", "F*(xf-xd)/(xb-xd)", "\u84b8\u998f\u6b8b\u6e23\u901f\u7387");
    model.param().set("D", "F-B", "\u998f\u51fa\u6db2\u901f\u7387");
    model.param().set("Lr", "Vr-D", "\u7cbe\u998f\u6db2\u4f53\u6d41\u91cf");
    model.param().set("Ls", "Lr+F", "\u6c7d\u63d0\u6db2\u4f53\u6d41\u91cf");
    model.param().set("RR", "Lr/D", "\u56de\u6d41\u6bd4");
    model.param().set("Kya", "75[mol/m^3/s]", "\u8d28\u91cf\u4f20\u9012\u7cfb\u6570");
    model.param().set("R", "0.12[m]", "\u5854\u534a\u5f84");
    model.param().set("A", "pi*(R)^2", "\u5854\u6a2a\u622a\u9762\u79ef");
    model.param().set("H", "12[m]", "\u5854\u603b\u9ad8");
    model.param().set("Hs", "1.2[m]", "\u63d0\u998f\u6bb5\u9ad8\u5ea6");
    model.param().set("Hr", "H-Hs", "\u7cbe\u998f\u6bb5\u9ad8\u5ea6");
    model.param().set("uV", "Vr*0.022414[m^3/mol]/A", "\u84b8\u6c7d\u901f\u5ea6");
    model.param().set("uLr", "Lr*0.022414[m^3/mol]/A", "\u7cbe\u998f\u6db2\u4f53\u6d41\u901f");
    model.param().set("uLs", "Ls*0.022414[m^3/mol]/A", "\u6c7d\u63d0\u6db2\u4f53\u6d41\u901f");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/re", false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "T", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "T", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "K", 0);
    model.study("std1").feature("stat").setIndex("pname", "n", 0);
    model.study("std1").feature("stat").setIndex("pname", "T", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "K", 1);
    model.study("std1").feature("stat").setIndex("pname", "T", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "K", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "0 1", 0);
    model.study("std1").feature("stat").setIndex("pname", "x1", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.01,1)", 1);
    model.study("std1").feature("stat").set("sweeptype", "filled");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").set("expr", new String[]{});
    model.result("pg1").feature("glob1").set("descr", new String[]{});
    model.result("pg1").feature("glob1").setIndex("expr", "x_y(P,n,x1,x2)", 0);
    model.result("pg1").feature("glob1").setIndex("descr", "", 0);
    model.result("pg1").feature("glob1").set("legend", false);
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").run();
    model.result("pg1").label("\u5e73\u8861\u66f2\u7ebf");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "y1");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("legendpos", "middleright");

    model.study("std1").label("\u5e73\u8861\u66f2\u7ebf\u53c2\u6570\u5316");

    model.component("comp1").physics("re").create("sync1", "ReactionToMph", -1);
    model.component("comp1").physics("re").feature("sync1").set("geomToUse", "1D");
    model.component("comp1").physics("re").feature("sync1").set("massbalance", "ConcentratedSpecies");
    model.component("comp1").physics("re").prop("synchronize").set("synchronize", "1");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom1", 1);
    model.component("comp2").geom("geom1").axisymmetric(false);
    model.component("comp2").geom("geom1").label("\u51e0\u4f55 1(1D)");

    model.component("comp2").mesh().create("mesh1");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");

    model.component("comp1").physics("re").feature("sync1").set("genom", new String[]{"comp2:geom1"});
    model.component("comp1").physics("re").feature("sync1").set("studyname", new String[]{"comp2:std2"});
    model.component("comp2").physics().create("chem", "Chemistry", "geom1");
    model.component("comp2").physics().move("chem", 0);
    model.component("comp2").physics().create("tcs", "ConcentratedSpecies", "geom1");
    model.component("comp2").physics("tcs").prop("AdvancedSettings").set("UsePseudoTime", true);
    model.component("comp2").physics("chem").prop("mixture").set("updatechem", "0");
    model.component("comp2").physics("chem").prop("mixture").set("mixture", "gas");
    model.component("comp2").physics("chem").prop("Activity").set("useActivity", "0");
    model.component("comp2").physics("chem").prop("chemkin").set("chemkin", "0");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("VolumetricConcentrationGlobalActivityStandardState", "1[mol/m^3]");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SurfaceSpeciesConcentrationType", "SurfaceConcentration");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SurfaceGlobalActivityStandardState", "1[mol/m^2]");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SpeciesrateUserDefinedList", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty").set("AdditionalSourceFeature", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "2");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "2");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpeciesVariable", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", "0");
    model.component("comp2").physics("chem").prop("solventIsSet").set("solventIsSet", "0");
    model.component("comp2").physics("chem").create("E", "SpeciesChem");
    model.component("comp2").physics("chem").feature("E").set("SpeciesSource", "FreeSpecies");
    model.component("comp2").physics("chem").feature("E").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("E").set("specLabel", "E");
    model.component("comp2").physics("chem").feature("E").set("speciesNameInput", "E");
    model.component("comp2").physics("chem").feature("E").set("specName", "E");
    model.component("comp2").physics("chem").feature("E").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("E").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("E").label("\u7269\u8d28: E");
    model.component("comp2").physics("chem").feature("E").active(true);
    model.component("comp2").physics("chem").feature("E").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("E").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("E").set("z", "0");
    model.component("comp2").physics("chem").feature("E").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("E").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("E").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("E").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("E").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("E").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("E").set("cLock", "0");
    model.component("comp2").physics("chem").feature("E").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("E").set("dependent", "0");
    model.component("comp2").physics("chem").feature("E").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("E").set("AdditionalSource", "1");
    model.component("comp2").physics("chem").feature("E").set("AddR", "0");
    model.component("comp2").physics("chem").create("W", "SpeciesChem");
    model.component("comp2").physics("chem").feature("W").set("SpeciesSource", "FreeSpecies");
    model.component("comp2").physics("chem").feature("W").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("W").set("specLabel", "W");
    model.component("comp2").physics("chem").feature("W").set("speciesNameInput", "W");
    model.component("comp2").physics("chem").feature("W").set("specName", "W");
    model.component("comp2").physics("chem").feature("W").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("W").set("chemicalFormula", "W");
    model.component("comp2").physics("chem").feature("W").label("\u7269\u8d28: W");
    model.component("comp2").physics("chem").feature("W").active(true);
    model.component("comp2").physics("chem").feature("W").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("W").set("M", "183.84[g/mol]");
    model.component("comp2").physics("chem").feature("W").set("z", "0");
    model.component("comp2").physics("chem").feature("W").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("W").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("W").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("W").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("W").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("W").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("W").set("cLock", "0");
    model.component("comp2").physics("chem").feature("W").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("W").set("dependent", "0");
    model.component("comp2").physics("chem").feature("W").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("W").set("AdditionalSource", "1");
    model.component("comp2").physics("chem").feature("W").set("AddR", "0");
    model.component("comp2").physics("chem").prop("simpropChem").set("rSequenceNo", "0");
    model.component("comp2").physics("chem").prop("simpropChem").set("sSequenceNo", "2");
    model.component("comp2").physics("chem").prop("mixture").set("hasPropertyPackage", "1");
    model.component("comp2").physics("chem").prop("mixture").set("PropertyPackage", "pp1");
    model.component("comp2").physics("chem").prop("mixture").set("Thermodynamics", "1");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("PackageSpecies", new String[]{"ethanol", "water"});
    model.component("comp2").physics("chem").prop("mixture").set("FullyCoupled", "1");
    model.component("comp2").physics("chem").prop("mixture").set("gasDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem").prop("mixture").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem").prop("calcTransport").set("heatCapacitySel", "Thermodynamics");
    model.component("comp2").physics("chem").prop("calcTransport").set("thermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").prop("calcTransport").set("dynamicViscositySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("E").set("MolarMassSelection", "Thermodynamics");
    model.component("comp2").physics("chem").feature("E").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("E").set("GasThermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("E").set("GasDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("E").set("LiquidDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("E").set("speciesEnthalpy", "Thermodynamics");
    model.component("comp2").physics("chem").feature("E").set("Thermodynamics", "1");
    model.component("comp2").physics("chem").feature("W").set("MolarMassSelection", "Thermodynamics");
    model.component("comp2").physics("chem").feature("W").set("liquidDensitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("W").set("GasThermalConductivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("W").set("GasDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("W").set("LiquidDiffusivitySel", "Thermodynamics");
    model.component("comp2").physics("chem").feature("W").set("speciesEnthalpy", "Thermodynamics");
    model.component("comp2").physics("chem").feature("W").set("Thermodynamics", "1");
    model.component("comp2").physics("chem").prop("mixture").set("updatechem", "1");

    model.thermodynamics().feature("pp1").set("physicsID", "chem");
    model.thermodynamics().feature("pp1")
         .set("FunctionList", new String[][]{{"M", "sigma", "epsilonkb", "mu", "M", "sigma", "epsilonkb", "mu", "rho", "hF", 
         "h", "sF", "Cp", "gamma", "D", "k", "eta"}, 
         {"ethanol", "ethanol", "ethanol", "ethanol", "water", "water", "water", "water", "ethanol:water", "ethanol:water", 
         "ethanol:water", "ethanol:water", "ethanol:water", "ethanol:water", "ethanol:water:none", "ethanol:water", "ethanol:water"}, 
         {"CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "CONSTANT", "ONEPHASE", "ONEPHASE", 
         "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE", "ONEPHASE"}, 
         {"mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mass", "mole", 
         "mass", "mole", "mole", "mole", "mole", "mole", "mole"}, 
         {"mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", "mole", 
         "mass", "mole", "mole", "mole", "mole", "mole", "mole"}});
    model.thermodynamics().feature("pp1").set("Create", "0");

    model.component("comp2").physics("tcs").field("massfraction").component(new String[]{"wE", "wW"});
    model.component("comp2").physics("tcs").feature("init1").set("w0", new String[]{"0.5", "0.5"});
    model.component("comp2").physics("tcs").feature().create("reac1", "ReactionSources");
    model.component("comp2").physics("tcs").feature("reac1").selection().all();
    model.component("comp2").physics("tcs").feature("reac1").set("MassTransferToOtherPhases", true);
    model.component("comp2").physics("tcs").feature("reac1").set("ReactingVolumeType", "TotalVolume");
    model.component("comp2").physics("chem").prop("mixture").set("ConcentrationType", "MassFraction");
    model.component("comp2").physics("chem").prop("calcTransport").set("calcTransport", "1");
    model.component("comp2").physics("tcs").feature("cdm1").set("rho_src", "root.comp2.chem.rho_chem");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tcs");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"wE", "wW"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"\u6c42\u89e3\u7684", "\u6c42\u89e3\u7684"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"1", "1"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("csurf", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("tcs").feature("sp1").set("M_wE_src", "root.comp2.chem.M_E");
    model.component("comp2").physics("tcs").feature("sp1").set("M_wW_src", "root.comp2.chem.M_W");
    model.component("comp2").physics("tcs").feature("sp1").set("z", new String[]{"0", "0"});
    model.component("comp2").physics("tcs").feature("cdm1")
         .set("Dik", new String[]{"1", "comp2.chem.D_E_W", "comp2.chem.D_E_W", "1"});
    model.component("comp2").physics("tcs").feature("cdm1").setIndex("DiffusivityFrom", "comp2.chem.D_E_W", 0, 0);
    model.component("comp2").physics("tcs").feature("reac1").setIndex("R_wE_src", "root.comp2.chem.Rw_E", 0);
    model.component("comp2").physics("tcs").feature("reac1").setIndex("R_wW_src", "root.comp2.chem.Rw_W", 0);
    model.component("comp1").physics("re").feature("sync1").set("geomToUse", "geom1");
    model.component("comp1").physics("re").feature("sync1").set("chemTag", "chem");
    model.component("comp1").physics("re").feature("sync1").set("massbalance", "tcs");

    model.study("std2").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std2").feature("stat").setSolveFor("/physics/tcs", true);
    model.study("std1").feature("stat").setSolveFor("/physics/chem", false);
    model.study("std1").feature("stat").setSolveFor("/physics/tcs", false);

    model.component("comp2").geom("geom1").create("i1", "Interval");
    model.component("comp2").geom("geom1").feature("i1").setIndex("coord", "Hs", 1);
    model.component("comp2").geom("geom1").run("i1");
    model.component("comp2").geom("geom1").feature().duplicate("i2", "i1");
    model.component("comp2").geom("geom1").feature("i2").setIndex("coord", "Hs", 0);
    model.component("comp2").geom("geom1").feature("i2").setIndex("coord", "H", 1);
    model.component("comp2").geom("geom1").runPre("fin");
    model.component("comp2").geom("geom1").run();

    model.component("comp2").physics("tcs").prop("SpeciesProperties").set("FromMassConstraint", 2);
    model.component("comp2").physics().create("tcs2", "ConcentratedSpecies", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/tcs2", true);
    model.study("std2").feature("stat").setSolveFor("/physics/tcs2", true);

    model.component("comp2").physics("tcs2").field("massfraction").field("wEl");
    model.component("comp2").physics("tcs2").field("massfraction").component(new String[]{"wEl", "wWl"});
    model.component("comp2").physics("tcs2").prop("SpeciesProperties").set("FromMassConstraint", 2);

    model.component("comp2").variable().create("var1");

//    To import content from file, use:
//    model.component("comp2").variable("var1").loadFile("FILENAME");
    model.component("comp2").variable("var1")
         .set("ym1", "tcs.x_wE", "\u84b8\u6c7d\u6469\u5c14\u5206\u6570\uff0c\u6613\u6325\u53d1\u7269\u8d28");
    model.component("comp2").variable("var1")
         .set("xm1", "tcs2.x_wEl", "\u6db2\u4f53\u6469\u5c14\u5206\u6570\uff0c\u6613\u6325\u53d1\u7269\u8d28");
    model.component("comp2").variable("var1")
         .set("xm2", "1-xm1", "\u6db2\u4f53\u6469\u5c14\u5206\u6570\uff0c\u96be\u6325\u53d1\u7269\u8d28");
    model.component("comp2").variable("var1")
         .set("ye1", "x_y(101325,0,xm1,xm2)", "\u5e73\u8861\u6469\u5c14\u5206\u6570\uff0c\u7ed9\u5b9a\u6db2\u4f53\u6761\u4ef6\u4e0b\u84b8\u6c7d\u4e2d\u7684\u6613\u6325\u53d1\u7269\u8d28");
    model.component("comp2").variable("var1")
         .set("wf", "xf*tcs.M_wE/(xf*tcs.M_wE+(1-xf)*tcs.M_wW)", "\u8fdb\u6599\u8d28\u91cf\u5206\u6570\uff0c\u6613\u6325\u53d1\u7269\u8d28");
    model.component("comp2").variable("var1")
         .set("wb", "xb*tcs.M_wE/(xb*tcs.M_wE+(1-xb)*tcs.M_wW)", "\u84b8\u998f\u6b8b\u6e23\u7684\u8d28\u91cf\u5206\u6570\uff0c\u6613\u6325\u53d1\u7269\u8d28");
    model.component("comp2").variable("var1")
         .set("wd", "xd*tcs.M_wE/(xd*tcs.M_wE+(1-xd)*tcs.M_wW)", "\u998f\u51fa\u6db2\u7684\u8d28\u91cf\u5206\u6570\uff0c\u6613\u6325\u53d1\u7269\u8d28");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}, {"pressure", "P"}});

    model.component("comp2").physics("tcs").feature("cdm1").set("rho_src", "IdealGas");
    model.component("comp2").physics("tcs").feature("cdm1").set("u", new String[]{"uV", "0", "0"});
    model.component("comp2").physics("tcs").feature("init1").setIndex("w0", "wf", 0);
    model.component("comp2").physics("tcs").feature("reac1").setIndex("R_wE_src", "userdef", 0);
    model.component("comp2").physics("tcs").feature("reac1").setIndex("R_wE", "-tcs.M_wE*Kya*(ym1-ye1)", 0);
    model.component("comp2").physics("tcs").feature("reac1").setIndex("R_wW_src", "userdef", 0);
    model.component("comp2").physics("tcs").feature("reac1").setIndex("R_wW", "tcs.M_wW*Kya*(ym1-ye1)", 0);
    model.component("comp2").physics("tcs").create("mf1", "MassFraction", 0);
    model.component("comp2").physics("tcs").feature("mf1").selection().set(1);
    model.component("comp2").physics("tcs").feature("mf1").setIndex("species", true, 0);
    model.component("comp2").physics("tcs").feature("mf1").setIndex("wbnd", "wb", 0);
    model.component("comp2").physics("tcs").create("out1", "Outflow", 0);
    model.component("comp2").physics("tcs").feature("out1").selection().set(3);
    model.component("comp2").physics("tcs2").feature("sp1").setIndex("M_wWl_src", "root.comp2.chem.M_W", 0);
    model.component("comp2").physics("tcs2").feature("sp1").setIndex("M_wEl_src", "root.comp2.chem.M_E", 0);
    model.component("comp2").physics("tcs2").feature("cdm1").set("u", new String[]{"-uLr", "0", "0"});
    model.component("comp2").physics("tcs2").feature("cdm1").setIndex("DF", "1e-20", 0, 0);
    model.component("comp2").physics("tcs2").feature("init1").setIndex("w0", "wf", 0);
    model.component("comp2").physics("tcs2").feature().duplicate("cdm2", "cdm1");
    model.component("comp2").physics("tcs2").feature("cdm2").selection().set(1);
    model.component("comp2").physics("tcs2").feature("cdm2").set("u", new String[]{"-uLs", "0", "0"});
    model.component("comp2").physics("tcs2").create("mf1", "MassFraction", 0);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").physics("tcs2").feature("mf1").selection().set(3);
    model.component("comp2").physics("tcs2").feature("mf1").setIndex("species", true, 0);
    model.component("comp2").physics("tcs2").feature("mf1").setIndex("wbnd", "wd", 0);
    model.component("comp2").physics("tcs2").create("mf2", "MassFraction", 0);
    model.component("comp2").physics("tcs2").feature("mf2").selection().set(2);
    model.component("comp2").physics("tcs2").feature("mf2").setIndex("species", true, 0);
    model.component("comp2").physics("tcs2").feature("mf2").setIndex("wbnd", "wf", 0);
    model.component("comp2").physics("tcs2").create("out1", "Outflow", 0);
    model.component("comp2").physics("tcs2").feature("out1").selection().set(1);
    model.component("comp2").physics("tcs2").create("reac1", "ReactionSources", 1);
    model.component("comp2").physics("tcs2").feature("reac1").set("MassTransferToOtherPhases", true);
    model.component("comp2").physics("tcs2").feature("reac1").setIndex("R_wEl", "tcs.M_wE*Kya*(ym1-ye1)", 0);
    model.component("comp2").physics("tcs2").feature("reac1").setIndex("R_wWl", "-tcs.M_wW*Kya*(ym1-ye1)", 0);
    model.component("comp2").physics("tcs2").feature("reac1").selection().set(1, 2);

    model.component("comp2").mesh("mesh1").automatic(false);
    model.component("comp2").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp2").mesh("mesh1").feature("edg1").feature("size1").selection().geom("geom1", 0);
    model.component("comp2").mesh("mesh1").feature("edg1").feature("size1").selection().set(1, 2, 3);
    model.component("comp2").mesh("mesh1").feature("edg1").feature("size1").set("table", "cfd");
    model.component("comp2").mesh("mesh1").feature("edg1").feature("size1").set("hauto", 1);
    model.component("comp2").mesh("mesh1").run("edg1");

    model.study("std2").label("\u5217\u8bbe\u8ba1");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "T", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "K", 0);
    model.study("std2").feature("param").setIndex("pname", "T", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "K", 0);
    model.study("std2").feature("param").setIndex("pname", "Hs", 0);
    model.study("std2").feature("param").setIndex("plistarr", "1.2 1.3 1.4", 0);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevelinput", "manual", 0);
    model.result("pg2").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().all();
    model.result("pg2").feature("lngr1").set("expr", "ym1");
    model.result("pg2").feature("lngr1").set("linewidth", 2);
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").setIndex("legends", "\u6c14\u76f8", 0);
    model.result("pg2").feature().duplicate("lngr2", "lngr1");
    model.result("pg2").run();
    model.result("pg2").feature("lngr2").set("expr", "xm1");
    model.result("pg2").feature("lngr2").setIndex("legends", "\u6db2\u76f8", 0);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").label("\u6469\u5c14\u5206\u6570");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u4e59\u9187\u6469\u5c14\u5206\u6570");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u5854\u9ad8 (m)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "x1\uff0cy1");
    model.result("pg2").set("legendpos", "middleright");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevelinput", "manual", 0);
    model.result("pg3").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().all();
    model.result("pg3").feature("lngr1").set("expr", "ym1");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "xm1");
    model.result("pg3").feature("lngr1").set("linecolor", "black");
    model.result("pg3").feature("lngr1").set("linewidth", 2);
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "manual");
    model.result("pg3").feature("lngr1").setIndex("legends", "\u5854\u64cd\u4f5c", 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{});
    model.result("pg3").feature("glob1").set("descr", new String[]{});
    model.result("pg3").feature("glob1").setIndex("expr", "x_y(P,n,x1,x2)", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "", 0);
    model.result("pg3").feature("glob1").set("data", "dset1");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").feature().move("glob1", 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").label("\u64cd\u4f5c\u7ebf");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "x1");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "y1");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("legendpos", "middleright");
    model.result("pg3").run();

    model.study("std1").feature("stat").setSolveFor("/physics/tcs2", false);

    model.result("pg3").run();

    model
         .title("\u4e3a\u7528\u4e8e\u6c34\u548c\u4e59\u9187\u5206\u79bb\u7684\u84b8\u998f\u5854\u6807\u6ce8\u5c3a\u5bf8");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u7ed3\u5408\u201c\u70ed\u529b\u5b66\u201d\u8282\u70b9\uff08\u9700\u8981\u201c\u5316\u5b66\u53cd\u5e94\u5de5\u7a0b\u6a21\u5757\u201d\u8bb8\u53ef\u8bc1\uff09\u4e0e\u201c\u6d53\u7269\u8d28\u4f20\u9012\u201d\u63a5\u53e3\u7684\u529f\u80fd\uff0c\u4e3a\u4e8c\u5143\u84b8\u998f\u8fc7\u7a0b\u5efa\u7acb\u7b80\u5355\u7684\u6a21\u578b\uff0c\u7528\u4e8e\u7814\u7a76\u4e59\u9187\u548c\u6c34\u7684\u975e\u7406\u60f3\u6df7\u5408\u7269\u7684\u5206\u79bb\uff0c\u5176\u4e2d\u4f7f\u7528\u201c\u70ed\u529b\u5b66\u201d\u8282\u70b9\u63d0\u4f9b\u7684\u201c\u5e73\u8861\u8ba1\u7b97\u201d\u529f\u80fd\u751f\u6210\u6240\u9700\u7684\u5e73\u8861\u5173\u7cfb\u3002\u8be5\u6a21\u578b\u7528\u4e8e\u6839\u636e\u63d0\u998f\u6bb5\u548c\u7cbe\u998f\u6bb5\u7684\u957f\u5ea6\u6765\u786e\u5b9a\u84b8\u998f\u5854\u7684\u6700\u4f73\u8bbe\u8ba1\uff0c\u4ee5\u7b26\u5408\u4e00\u7ec4\u6307\u5b9a\u7684\u998f\u5206\u7ec4\u6210\u548c\u6b8b\u6e23\u6210\u5206\u3002");

    model.label("distillation_column.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
