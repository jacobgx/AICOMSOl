/*
 * protein_adsorption.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:21 by COMSOL 6.3.0.290. */
public class protein_adsorption {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Mixing_and_Separation");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Arsurf", "2e4[m^2]", "\u8868\u9762\u53cd\u5e94\u9762\u79ef");
    model.param().set("vfp", "1[m^3/s]", "\u5165\u53e3\u4f53\u79ef\u8fdb\u7ed9\u7387");
    model.param().set("Keq01", "2", "\u53cd\u5e94 1 \u7684\u5e73\u8861\u5e38\u6570");
    model.param().set("Keq02", "5", "\u53cd\u5e94 2 \u7684\u5e73\u8861\u5e38\u6570");
    model.param().set("CAmax_inlet", "5e-2[mol/m^3]", "\u5165\u53e3\u4e2d A \u7684\u6700\u5927\u6d53\u5ea6");
    model.param().set("CBmax_inlet", "5e-2[mol/m^3]", "\u5165\u53e3\u4e2d B \u7684\u6700\u5927\u6d53\u5ea6");
    model.param().set("CS0surf", "0.99e-5[mol/m^2]", "S \u7684\u521d\u59cb\u8868\u9762\u6d53\u5ea6");
    model.param().set("CH2O", "55600[mol/m^3]", "\u6eb6\u5242\uff08\u6c34\uff09\u6d53\u5ea6");
    model.param().set("G0", "1e-5[mol/m^2]", "\u53cd\u5e94\u8868\u9762\u7684\u521d\u59cb\u4f4d\u5bc6\u5ea6");
    model.param().set("MA", "0.2[kg/mol]", "\u86cb\u767d\u8d28 A \u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("MB", "0.15[kg/mol]", "\u86cb\u767d\u8d28 B \u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("MS", "0.058[kg/mol]", "S \u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("MH2O", "0.018[kg/mol]", "\u6c34\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("rho_H2O", "1000[kg/m^3]", "\u6c34\u7684\u5bc6\u5ea6");
    model.param().set("rho_p", "1400[kg/m^3]", "\u86cb\u767d\u8d28\u5bc6\u5ea6");
    model.param().set("rho_S", "1000[kg/m^3]", "S \u7684\u5bc6\u5ea6");
    model.param().set("muH2O", "1e-3[Pa*s]", "\u6c34\u7684\u52a8\u529b\u9ecf\u5ea6");
    model.param()
         .set("U_column", "1e-4[m/s]", "\u79bb\u5b50\u4ea4\u6362\u67f1\u5185\u7684\u5e73\u5747\u6d41\u4f53\u901f\u5ea6");

    model.func().create("gp1", "GaussianPulse");
    model.func("gp1").set("location", 5);
    model.func("gp1").set("sigma", 1.5);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("pulse11", "3.76*gp1(t/1[s])");
    model.component("comp1").variable("var1").descr("pulse11", "\u5e45\u503c\u4e3a 1 \u7684\u8109\u51b2");

    model.component("comp1").physics("re").prop("reactor").set("reactor", "cstrvol");
    model.component("comp1").physics("re").prop("mixture").set("mixture", "liquid");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "S(ads)+A=S+A(ads)");
    model.component("comp1").physics("re").feature("rch1").set("Keq0", "Keq01");
    model.component("comp1").physics("re").feature("S_surf").set("enableChemicalFormulaCheckbox", false);
    model.component("comp1").physics("re").feature("S_surf").set("M", "MS");
    model.component("comp1").physics("re").feature("A").set("M", "MA");
    model.component("comp1").physics("re").feature("A").set("rho", "rho_p");
    model.component("comp1").physics("re").feature("S").set("enableChemicalFormulaCheckbox", false);
    model.component("comp1").physics("re").feature("S").set("M", "MS");
    model.component("comp1").physics("re").feature("S").set("rho", "rho_S");
    model.component("comp1").physics("re").feature("A_surf").set("M", "MA");
    model.component("comp1").physics("re").create("rch2", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch2").set("formula", "S(ads)+B=S+B(ads)");
    model.component("comp1").physics("re").feature("rch2").set("Keq0", "Keq02");
    model.component("comp1").physics("re").feature("B").set("enableChemicalFormulaCheckbox", false);
    model.component("comp1").physics("re").feature("B").set("M", "MB");
    model.component("comp1").physics("re").feature("B").set("rho", "rho_p");
    model.component("comp1").physics("re").feature("B_surf").set("enableChemicalFormulaCheckbox", false);
    model.component("comp1").physics("re").feature("B_surf").set("M", "MB");
    model.component("comp1").physics("re").prop("EquilibriumReactionProperty")
         .set("PredefinedDependentSpecies", "A(ads):B(ads)");
    model.component("comp1").physics("re").prop("reactor").set("reactorparsource", "UserDefined");
    model.component("comp1").physics("re").prop("reactor").set("vp", 0);
    model.component("comp1").physics("re").prop("reactor").set("Ar", "Arsurf");
    model.component("comp1").physics("re").feature("inits1").set("gamman0", "G0");
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValues", "CS0surf", 2, 0);
    model.component("comp1").physics("re").create("feed1", "FeedStream", -1);
    model.component("comp1").physics("re").feature("feed1").set("vf", "vfp");
    model.component("comp1").physics("re").feature("feed1")
         .setIndex("FeedSpeciesConcentration", "CAmax_inlet*pulse11", 0, 0);
    model.component("comp1").physics("re").feature("feed1")
         .setIndex("FeedSpeciesConcentration", "CBmax_inlet*pulse11", 1, 0);

    model.study("std1").label("\u7814\u7a76 1 - \u7a7a\u95f4\u65e0\u5173\u6a21\u578b");
    model.study("std1").feature("time").set("tlist", "range(0,0.1,14)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{"", "", "", "", "", ""});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.csurf_S_surf", "re.c_A", "re.c_S", "re.c_B", "re.csurf_A_surf", "re.csurf_B_surf"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u8868\u9762\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6"});
    model.result("pg1").label("\u6d53\u5ea6 (re)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg1").run();
    model.result("pg1").label("\u6d53\u5ea6\u96f6\u7ef4\u6a21\u578b");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1")
         .set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>) | \u8868\u9762\u6d53\u5ea6 (mol/m<sup>2</sup>)");
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_A"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"mol/m^3"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_A", "re.c_B"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_A", "re.c_B", "re.c_S"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"re.c_A", "re.c_B", "re.c_S", "re.csurf_A_surf"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6"});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_A", "re.c_B", "re.c_S", "re.csurf_A_surf", "re.csurf_B_surf"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6"});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"re.c_A", "re.c_B", "re.c_S", "re.csurf_A_surf", "re.csurf_B_surf", "re.csurf_S_surf"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6d53\u5ea6", "\u6d53\u5ea6", "\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6", "\u8868\u9762\u6d53\u5ea6"});
    model.result("pg1").feature("glob1").setIndex("expr", "re.csurf_A_surf*1e4", 3);
    model.result("pg1").feature("glob1")
         .setIndex("descr", "\u6bcf\u5e73\u65b9\u5206\u7c73\u7684\u8868\u9762\u6d53\u5ea6", 3);
    model.result("pg1").feature("glob1").setIndex("expr", "re.csurf_B_surf*1e4", 4);
    model.result("pg1").feature("glob1")
         .setIndex("descr", "\u6bcf\u5e73\u65b9\u5206\u7c73\u7684\u8868\u9762\u6d53\u5ea6", 4);
    model.result("pg1").feature("glob1").setIndex("expr", "re.csurf_S_surf*1e4", 5);
    model.result("pg1").feature("glob1")
         .setIndex("descr", "\u6bcf\u5e73\u65b9\u5206\u7c73\u7684\u8868\u9762\u6d53\u5ea6", 5);
    model.result("pg1").feature("glob1").set("linewidth", 2);
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "A", 0);
    model.result("pg1").feature("glob1").setIndex("legends", "B", 1);
    model.result("pg1").feature("glob1").setIndex("legends", "S", 2);
    model.result("pg1").feature("glob1").setIndex("legends", "\u8868\u9762 A", 3);
    model.result("pg1").feature("glob1").setIndex("legends", "\u8868\u9762 B", 4);
    model.result("pg1").feature("glob1").setIndex("legends", "\u8868\u9762 S", 5);
    model.result("pg1").run();

    model.component("comp1").physics("re").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re").feature("spec1").set("specName", "H2O");
    model.component("comp1").physics("re").feature("H2O").set("sType", "solvent");
    model.component("comp1").physics("re").feature("H2O").set("M", "MH2O");
    model.component("comp1").physics("re").feature("H2O").set("rho", "rho_H2O");
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "CH2O", 2, 0);
    model.component("comp1").physics("re").prop("calcTransport").set("calcTransport", true);
    model.component("comp1").physics("re").prop("calcTransport").set("dynamicViscositySel", "UserDefined");
    model.component("comp1").physics("re").prop("calcTransport").set("eta", "muH2O");
    model.component("comp1").physics("re").create("sync1", "ReactionToMph", -1);
    model.component("comp1").physics("re").feature("sync1").set("study", "Transient");
    model.component("comp1").physics("re").feature("sync1").set("momentumbalance", "LaminarFlow");
    model.component("comp1").physics("re").prop("synchronize").set("synchronize", "1");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom1", 3);
    model.component("comp2").geom("geom1").geomRep("comsol");
    model.component("comp2").geom("geom1").axisymmetric(false);
    model.component("comp2").geom("geom1").label("\u51e0\u4f55 1(3D)");

    model.component("comp2").mesh().create("mesh1");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");

    model.component("comp1").physics("re").feature("sync1").set("genom", new String[]{"comp2:geom1"});
    model.component("comp1").physics("re").feature("sync1").set("studyname", new String[]{"comp2:std2"});
    model.component("comp2").physics().create("chem", "Chemistry", "geom1");
    model.component("comp2").physics().move("chem", 0);
    model.component("comp2").physics().create("tds", "DilutedSpecies", "geom1");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T_src", "userdef");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T", "293.15[K]");
    model.component("comp2").physics("chem").prop("mixture").set("mixture", "liquid");
    model.component("comp2").physics("chem").prop("mixture").set("liquidDensitySel", "Automatic");
    model.component("comp2").physics("chem").prop("Activity").set("useActivity", "0");
    model.component("comp2").physics("chem").prop("chemkin").set("chemkin", "0");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("VolumetricConcentrationGlobalActivityStandardState", "1[mol/m^3]");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SurfaceSpeciesConcentrationType", "SurfaceConcentration");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SurfaceGlobalActivityStandardState", "1[mol/m^2]");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SpeciesrateUserDefinedList", new String[]{"H2O"});
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty").set("AdditionalSourceFeature", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "7");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "4");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "3");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpeciesVariable", "3");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", "1");
    model.component("comp2").physics("chem").prop("solventIsSet").set("solventIsSet", "1");
    model.component("comp2").physics("chem").prop("solventIsSet").set("solventTag", "H2O");
    model.component("comp2").physics("chem").prop("calcTransport").set("etaRef", "1.787E-3[Pa*s]");
    model.component("comp2").physics("chem").prop("calcTransport").set("Tref", "273[K]");
    model.component("comp2").physics("chem").prop("calcTransport").set("dynamicViscositySel", "UserDefined");
    model.component("comp2").physics("chem").prop("calcTransport").set("eta", "muH2O");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("massbalance", new String[]{"tds"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("hasEquilMassBalance", "1");
    model.component("comp2").physics("chem").create("rch1", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch1").set("rSequenceNo", "1");
    model.component("comp2").physics("chem").feature("rch1").set("formula", "S(ads) + A = S + A(ads)");
    model.component("comp2").physics("chem").feature("rch1").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch1").set("EquilibriumConstantSettings", "UserDefined");
    model.component("comp2").physics("chem").feature("rch1").set("Keq0", "Keq01");
    model.component("comp2").physics("chem").feature("rch1").label("1: \u8868\u9762: S(ads) + A = S + A(ads)");
    model.component("comp2").physics("chem").feature("rch1").set("rClass", "surface");
    model.component("comp2").physics("chem").create("rch2", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch2").set("rSequenceNo", "2");
    model.component("comp2").physics("chem").feature("rch2").set("formula", "S(ads) + B = S + B(ads)");
    model.component("comp2").physics("chem").feature("rch2").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch2").set("EquilibriumConstantSettings", "UserDefined");
    model.component("comp2").physics("chem").feature("rch2").set("Keq0", "Keq02");
    model.component("comp2").physics("chem").feature("rch2").label("2: \u8868\u9762: S(ads) + B = S + B(ads)");
    model.component("comp2").physics("chem").feature("rch2").set("rClass", "surface");
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("S_surf").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("S_surf").set("specLabel", "S(ads)");
    model.component("comp2").physics("chem").feature("S_surf").set("speciesNameInput", "S(ads)");
    model.component("comp2").physics("chem").feature("S_surf").set("specName", "S(ads)");
    model.component("comp2").physics("chem").feature("S_surf").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("S_surf").set("chemicalFormula", "S(ads)");
    model.component("comp2").physics("chem").feature("S_surf").set("sType", "surface");
    model.component("comp2").physics("chem").feature("S_surf").set("M", "MS");
    model.component("comp2").physics("chem").feature("S_surf").set("z", "0");
    model.component("comp2").physics("chem").feature("S_surf").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("S_surf").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("S_surf").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("S_surf").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("S_surf").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("S_surf").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("S_surf").set("cLock", "0");
    model.component("comp2").physics("chem").feature("S_surf").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("S_surf").set("dependent", "2");
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("S_surf").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("S_surf").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("S_surf").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("S_surf").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S_surf").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("A").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("A").set("specLabel", "A");
    model.component("comp2").physics("chem").feature("A").set("speciesNameInput", "A");
    model.component("comp2").physics("chem").feature("A").set("specName", "A");
    model.component("comp2").physics("chem").feature("A").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("A").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("A").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("A").set("M", "MA");
    model.component("comp2").physics("chem").feature("A").set("z", "0");
    model.component("comp2").physics("chem").feature("A").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("A").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("A").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("A").set("rho", "rho_p");
    model.component("comp2").physics("chem").feature("A").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("A").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("A").set("cLock", "0");
    model.component("comp2").physics("chem").feature("A").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("A").set("dependent", "0");
    model.component("comp2").physics("chem").feature("A").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("A").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("A").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("A").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("A").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("S").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("S").set("specLabel", "S");
    model.component("comp2").physics("chem").feature("S").set("speciesNameInput", "S");
    model.component("comp2").physics("chem").feature("S").set("specName", "S");
    model.component("comp2").physics("chem").feature("S").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("S").set("chemicalFormula", "S");
    model.component("comp2").physics("chem").feature("S").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("S").set("M", "MS");
    model.component("comp2").physics("chem").feature("S").set("z", "0");
    model.component("comp2").physics("chem").feature("S").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("S").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("S").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("S").set("rho", "rho_S");
    model.component("comp2").physics("chem").feature("S").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("S").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("S").set("cLock", "0");
    model.component("comp2").physics("chem").feature("S").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("S").set("dependent", "0");
    model.component("comp2").physics("chem").feature("S").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("S").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("S").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("S").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("S").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("S").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("S").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("A_surf").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("A_surf").set("specLabel", "A(ads)");
    model.component("comp2").physics("chem").feature("A_surf").set("speciesNameInput", "A(ads)");
    model.component("comp2").physics("chem").feature("A_surf").set("specName", "A(ads)");
    model.component("comp2").physics("chem").feature("A_surf").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("A_surf").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("A_surf").set("sType", "surface");
    model.component("comp2").physics("chem").feature("A_surf").set("M", "MA");
    model.component("comp2").physics("chem").feature("A_surf").set("z", "0");
    model.component("comp2").physics("chem").feature("A_surf").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("A_surf").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("A_surf").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("A_surf").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("A_surf").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("A_surf").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("A_surf").set("cLock", "0");
    model.component("comp2").physics("chem").feature("A_surf").set("Dependent", "1");
    model.component("comp2").physics("chem").feature("A_surf").set("dependent", "3");
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("A_surf").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("A_surf").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("A_surf").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("A_surf").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("A_surf").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("B").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("B").set("specLabel", "B");
    model.component("comp2").physics("chem").feature("B").set("speciesNameInput", "B");
    model.component("comp2").physics("chem").feature("B").set("specName", "B");
    model.component("comp2").physics("chem").feature("B").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("B").set("chemicalFormula", "B");
    model.component("comp2").physics("chem").feature("B").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("B").set("M", "MB");
    model.component("comp2").physics("chem").feature("B").set("z", "0");
    model.component("comp2").physics("chem").feature("B").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("B").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("B").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("B").set("rho", "rho_p");
    model.component("comp2").physics("chem").feature("B").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("B").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("B").set("cLock", "0");
    model.component("comp2").physics("chem").feature("B").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("B").set("dependent", "0");
    model.component("comp2").physics("chem").feature("B").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("B").set("speciesEnthalpy", "NASA");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").physics("chem").feature("B").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("B").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("B").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("B_surf").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("B_surf").set("specLabel", "B(ads)");
    model.component("comp2").physics("chem").feature("B_surf").set("speciesNameInput", "B(ads)");
    model.component("comp2").physics("chem").feature("B_surf").set("specName", "B(ads)");
    model.component("comp2").physics("chem").feature("B_surf").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("B_surf").set("chemicalFormula", "B(ads)");
    model.component("comp2").physics("chem").feature("B_surf").set("sType", "surface");
    model.component("comp2").physics("chem").feature("B_surf").set("M", "MB");
    model.component("comp2").physics("chem").feature("B_surf").set("z", "0");
    model.component("comp2").physics("chem").feature("B_surf").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("B_surf").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("B_surf").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("B_surf").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("B_surf").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("B_surf").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("B_surf").set("cLock", "0");
    model.component("comp2").physics("chem").feature("B_surf").set("Dependent", "1");
    model.component("comp2").physics("chem").feature("B_surf").set("dependent", "3");
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("B_surf").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("B_surf").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("B_surf").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("B_surf").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("B_surf").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").create("H2O", "SpeciesChem");
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesSource", "FreeSpecies");
    model.component("comp2").physics("chem").feature("H2O").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("H2O").set("specLabel", "H2O");
    model.component("comp2").physics("chem").feature("H2O").set("speciesNameInput", "H2O");
    model.component("comp2").physics("chem").feature("H2O").set("specName", "H2O");
    model.component("comp2").physics("chem").feature("H2O").set("enableChemicalFormulaCheckbox", "1");
    model.component("comp2").physics("chem").feature("H2O").set("chemicalFormula", "H2O");
    model.component("comp2").physics("chem").feature("H2O").label("\u7269\u8d28: H2O (\u6eb6\u5242)");
    model.component("comp2").physics("chem").feature("H2O").active(true);
    model.component("comp2").physics("chem").feature("H2O").set("sType", "solvent");
    model.component("comp2").physics("chem").feature("H2O").set("M", "MH2O");
    model.component("comp2").physics("chem").feature("H2O").set("z", "0");
    model.component("comp2").physics("chem").feature("H2O").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("H2O").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("H2O").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("H2O").set("rho", "rho_H2O");
    model.component("comp2").physics("chem").feature("H2O").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("H2O").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("H2O").set("cLock", "1");
    model.component("comp2").physics("chem").feature("H2O").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("H2O").set("dependent", "0");
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("H2O").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("H2O").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("H2O").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("H2O").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("H2O").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").prop("simpropChem").set("rSequenceNo", "2");
    model.component("comp2").physics("chem").prop("simpropChem").set("sSequenceNo", "7");
    model.component("comp2").physics("chem").prop("mixture").set("hasPropertyPackage", "0");
    model.component("comp2").physics("chem").prop("mixture").set("PropertyPackage", "");
    model.component("comp2").physics("chem").prop("mixture").set("Thermodynamics", "0");
    model.component("comp2").physics("tds").field("concentration").component(new String[]{"cA", "cB", "cS"});
    model.component("comp2").physics("tds").feature("init1").set("initc", new String[]{"0", "0", "0"});
    model.component("comp2").physics("tds").feature().create("reac1", "Reactions");
    model.component("comp2").physics("tds").feature("reac1").selection().all();
    model.component("comp2").physics("tds").feature("reac1").set("ReactingVolumeType", "TotalVolume");
    model.component("comp2").physics("tds").feature().create("in1", "Inflow");
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "CAmax_inlet*pulse11", 0);
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "CBmax_inlet*pulse11", 1);
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "0", 2);
    model.component("comp2").physics("tds").feature().create("out1", "Outflow");
    model.component("comp2").physics("tds").feature().create("seqreac1", "SurfaceEquilibriumReaction");
    model.component("comp2").physics("tds").feature("seqreac1").set("EquilibriumCondition", "UserDefined");
    model.component("comp2").physics("tds").feature("seqreac1").set("Eqexpr0", "chem.equ_expr_1");
    model.component("comp2").physics("tds").feature("seqreac1").selection().all();
    model.component("comp2").physics("tds").feature("seqreac1").set("nu", new String[]{"-1.0", "0", "1.0"});
    model.component("comp2").physics("tds").feature().create("seqreac2", "SurfaceEquilibriumReaction");
    model.component("comp2").physics("tds").feature("seqreac2").set("EquilibriumCondition", "UserDefined");
    model.component("comp2").physics("tds").feature("seqreac2").set("Eqexpr0", "chem.equ_expr_2");
    model.component("comp2").physics("tds").feature("seqreac2").selection().all();
    model.component("comp2").physics("tds").feature("seqreac2").set("nu", new String[]{"0", "-1.0", "1.0"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("reactionRate", new String[]{"tds.Rseq_seqreac1", "tds.Rseq_seqreac2"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tds");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"cA", "cB", "UserDefined", "cS"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"\u6c42\u89e3\u7684", "\u6c42\u89e3\u7684", "CH2O", "\u6c42\u89e3\u7684"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cA", "cB", "CH2O", "cS"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("csurf", new String[]{"cA_surf", "cB_surf", "cS_surf"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("tds").feature("sp1")
         .set("z", new String[]{"root.comp2.chem.z_A", "root.comp2.chem.z_B", "root.comp2.chem.z_S"});
    model.component("comp2").physics("tds").feature("cdm1").set("DiffusionCoefficientSource", "chem");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cA_src", "root.comp2.chem.DXX_A");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cB_src", "root.comp2.chem.DXX_B");
    model.component("comp2").physics("tds").feature("cdm1").set("Dchem_cS_src", "root.comp2.chem.DXX_S");
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cA_src", "root.comp2.chem.R_A", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cB_src", "root.comp2.chem.R_B", 0);
    model.component("comp2").physics("tds").feature("reac1").setIndex("R_cS_src", "root.comp2.chem.R_S", 0);
    model.component("comp2").physics().create("sr", "SurfaceReactions", "geom1");
    model.component("comp2").physics("sr").field("surfaceconcentration")
         .component(new String[]{"cs_A", "cs_B", "cs_S"});
    model.component("comp2").physics("sr").feature("sp1").set("Gamma", "G0");
    model.component("comp2").physics("sr").feature("sp1").set("Sigma", new String[]{"1", "1", "1"});
    model.component("comp2").physics("sr").feature("init1").set("initcs", new String[]{"0", "0", "CS0surf"});
    model.component("comp2").physics("sr").create("reac1", "Reactions");
    model.component("comp2").physics("sr").feature("reac1").setIndex("Rs_cs_A_src", "root.comp2.chem.R_A_surf", 0);
    model.component("comp2").physics("sr").feature("reac1").setIndex("Rs_cs_B_src", "root.comp2.chem.R_B_surf", 0);
    model.component("comp2").physics("sr").feature("reac1").setIndex("Rs_cs_S_src", "root.comp2.chem.R_S_surf", 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"cA", "cB", "UserDefined", "cS"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"\u6c42\u89e3\u7684", "\u6c42\u89e3\u7684", "CH2O", "\u6c42\u89e3\u7684"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cA", "cB", "CH2O", "cS"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SurfaceSpeciesSource", "SurfaceReactions_sr");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("SurfaceConcentrationInput", "cs_A", 0, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("SurfaceConcentrationInput", "cs_B", 1, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .setIndex("SurfaceConcentrationInput", "cs_S", 2, 0);
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp2").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp2").physics("spf").feature().create("inl1", "InletBoundary");
    model.component("comp2").physics("spf").feature("inl1").set("BoundaryCondition", "Velocity");
    model.component("comp2").physics("spf").feature("inl1").set("ComponentWise", "NormalInflowVelocity");
    model.component("comp2").physics("spf").feature().create("out1", "OutletBoundary");

    model.component("comp2").multiphysics().create("rfd1", "ReactingFlowDS", 3);
    model.component("comp2").multiphysics("rfd1").set("Fluid_physics", "spf");
    model.component("comp2").multiphysics("rfd1").set("Species_physics", "tds");

    model.component("comp1").physics("re").feature("sync1").set("geomToUse", "geom1");
    model.component("comp1").physics("re").feature("sync1").set("chemTag", "chem");
    model.component("comp1").physics("re").feature("sync1").set("massbalance", "tds");
    model.component("comp2").physics("tds").feature("cdm1").set("u_src", "root.comp2.u");
    model.component("comp2").physics("tds").feature("cdm1").set("minput_pressure_src", "root.comp2.spf.pA");
    model.component("comp1").physics("re").feature("sync1").set("momentumbalance", "spf");
    model.component("comp2").physics("spf").feature("fp1").set("rho_mat", "root.comp2.chem.rho");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("p_src", "root.comp2.spf.pA");
    model.component("comp2").physics("spf").feature("fp1").set("mu_mat", "root.comp2.chem.eta");

    model.study("std2").feature("time").setSolveFor("/physics/re", false);
    model.study("std2").feature("time").setSolveFor("/physics/chem", true);
    model.study("std2").feature("time").setSolveFor("/physics/tds", true);
    model.study("std2").feature("time").setSolveFor("/physics/sr", true);
    model.study("std2").feature("time").setSolveFor("/physics/spf", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/rfd1", true);
    model.study("std1").feature("time").setSolveFor("/physics/chem", false);
    model.study("std1").feature("time").setSolveFor("/physics/tds", false);
    model.study("std1").feature("time").setSolveFor("/physics/sr", false);
    model.study("std1").feature("time").setSolveFor("/physics/spf", false);
    model.study("std1").feature("time").setSolveFor("/multiphysics/rfd1", false);

    model.component("comp2").geom("geom1").insertFile("protein_adsorption_geom_sequence.mph", "geom1");
    model.component("comp2").geom("geom1").run("sel4");

    model.component("comp2").physics("tds").feature("init1").setIndex("initc", "CAmax_inlet*0.01", 0);
    model.component("comp2").physics("tds").feature("init1").setIndex("initc", "CBmax_inlet*0.01", 1);
    model.component("comp2").physics("tds").feature("init1").setIndex("initc", "1e-7", 2);
    model.component("comp2").physics("tds").feature("in1").selection().named("geom1_sel2");
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "CAmax_inlet", 0);
    model.component("comp2").physics("tds").feature("in1").setIndex("c0", "CBmax_inlet", 1);
    model.component("comp2").physics("tds").feature("in1").set("BoundaryConditionType", "FluxDanckwerts");
    model.component("comp2").physics("tds").feature("out1").selection().named("geom1_sel3");
    model.component("comp2").physics("tds").feature("seqreac1").selection().named("geom1_arr1_bnd");
    model.component("comp2").physics("tds").feature("seqreac2").selection().named("geom1_arr1_bnd");
    model.component("comp2").physics("sr").selection().named("geom1_arr1_bnd");
    model.component("comp2").physics("sr").feature("reac1").selection().named("geom1_arr1_bnd");
    model.component("comp2").physics("sr").feature("init1").setIndex("initcs", "CS0surf*0.01", 0);
    model.component("comp2").physics("sr").feature("init1").setIndex("initcs", "CS0surf*0.01", 1);
    model.component("comp2").physics("spf").feature("inl1").selection().named("geom1_sel2");
    model.component("comp2").physics("spf").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp2").physics("spf").feature("out1").selection().named("geom1_sel3");
    model.component("comp2").physics("spf").feature("out1").set("BoundaryCondition", "Velocity");
    model.component("comp2").physics("spf").feature("out1").set("U0out", "U_column");
    model.component("comp2").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp2").physics("spf").feature("sym1").selection().named("geom1_sel4");

    model.component("comp2").selection().create("sel1", "Explicit");
    model.component("comp2").selection("sel1").geom(2);
    model.component("comp2").selection("sel1").label("\u58c1");
    model.component("comp2").selection("sel1")
         .set(5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213);

    model.component("comp2").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh1").feature("ftri1").selection().named("geom1_arr1_bnd");
    model.component("comp2").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp2").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "9E-2");
    model.component("comp2").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp2").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp2").mesh("mesh1").feature("ftet1").selection().all();
    model.component("comp2").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp2").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp2").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp2").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "5E-1");

    model.study("std2").label("\u7814\u7a76 2 - \u7a7a\u95f4\u4f9d\u8d56\u6a21\u578b");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").label("\u7a33\u6001 - \u5c42\u6d41");
    model.study("std2").feature("stat").setSolveFor("/physics/chem", false);
    model.study("std2").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std2").feature("stat").setSolveFor("/physics/sr", false);
    model.study("std2").feature("time").label("\u77ac\u6001 - \u754c\u9762\u677e\u5f1b");
    model.study("std2").feature("time").set("tlist", "range(0,1,30)");
    model.study("std2").feature("time").set("usertol", true);
    model.study("std2").feature("time").set("rtol", "2e-4");
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").feature().move("stat", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol2").feature("t1").set("atolglobal", "1e-4");
    model.sol("sol2").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("t1").feature("fc1").set("linsolver", "dDef");

    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("sectors", 4);
    model.result().dataset().duplicate("sec2", "sec1");
    model.result().dataset("sec2").set("include", "manual");
    model.result().dataset("sec2").set("sectorsinclude", 3);
    model.result().dataset("sec2").set("startsector", 3);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u672c\u4f53\u6d53\u5ea6 B\uff0c5 s");
    model.result("pg2").setIndex("looplevel", 6, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("data", "sec1");
    model.result("pg2").feature("surf1").setIndex("looplevel", 6, 0);
    model.result("pg2").feature("surf1").set("expr", "cB");
    model.result("pg2").feature("surf1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0ccB");

    model.component("comp2").view("view1").set("showgrid", false);

    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u672c\u4f53\u6d53\u5ea6 B\uff0c30 s");
    model.result("pg3").setIndex("looplevel", 31, 0);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("data", "sec2");
    model.result("pg3").feature("surf1").setIndex("looplevel", 31, 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u8868\u9762\u6d53\u5ea6 B\uff0c5 s");
    model.result("pg4").setIndex("looplevel", 6, 0);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("data", "sec1");
    model.result("pg4").feature("surf1").setIndex("looplevel", 6, 0);
    model.result("pg4").feature("surf1").set("expr", "cs_B");
    model.result("pg4").feature("surf1").set("descr", "\u8868\u9762\u6d53\u5ea6");

    model.component("comp2").view("view1").set("transparency", true);

    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u8868\u9762\u6d53\u5ea6 B\uff0c30 s");
    model.result("pg5").setIndex("looplevel", 31, 0);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("data", "sec2");
    model.result("pg5").feature("surf1").setIndex("looplevel", 31, 0);

    model.component("comp2").view("view1").set("transparency", false);

    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u901f\u5ea6\u573a");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").set("descriptionintitle", false);
    model.result("pg6").set("unitintitle", false);
    model.result("pg6").set("edges", false);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg6").feature("str1").set("descr", "\u901f\u5ea6\u573a");
    model.result("pg6").feature("str1").set("posmethod", "magnitude");
    model.result("pg6").feature("str1").set("mdist", new double[]{0.03, 0.05});
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("linetype", "tube");
    model.result("pg6").feature("str1").create("col1", "Color");
    model.result("pg6").run();
    model.result("pg6").feature("str1").feature("col1").set("expr", "spf.U");
    model.result("pg6").feature("str1").feature("col1").set("descr", "\u901f\u5ea6\u5927\u5c0f");
    model.result("pg6").run();
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("coloring", "gradient");
    model.result("pg6").feature("surf1").set("topcolor", "custom");
    model.result("pg6").feature("surf1")
         .set("customtopcolor", new double[]{0.6509804129600525, 0.8392156958580017, 0.8156862854957581});
    model.result("pg6").feature("surf1").set("bottomcolor", "custom");
    model.result("pg6").feature("surf1")
         .set("custombottomcolor", new double[]{1, 0.6274510025978088, 0.47843137383461});
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().named("sel1");
    model.result("pg6").run();

    model.title("\u86cb\u767d\u8d28\u5438\u9644");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u4e86\u79bb\u5b50\u4ea4\u6362\u67f1\u4e2d\u4e24\u79cd\u86cb\u767d\u8d28\u7684\u5438\u9644\u3002\u5c06\u5176\u63cf\u8ff0\u4e3a\u5e73\u8861\u53cd\u5e94\u3002\u9996\u5148\uff0c\u4f7f\u7528\u201c\u53cd\u5e94\u5de5\u7a0b\u201d\u63a5\u53e3\u7814\u7a76\u96f6\u7ef4\u6a21\u578b\u4e2d\u7684\u52a8\u529b\u5b66\u3002\u63a5\u7740\uff0c\u4f7f\u7528\u4e09\u7ef4\u6a21\u578b\u7814\u7a76\u79bb\u5b50\u4ea4\u6362\u67f1\u4e0a\u591a\u5b54\u7ed3\u6784\u5185\u7684\u5438\u9644\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("protein_adsorption.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
