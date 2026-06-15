/*
 * double_headed_streamer_discharge_chemistry.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:51 by COMSOL 6.3.0.290. */
public class double_headed_streamer_discharge_chemistry {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electric_Discharge_Module\\Streamer_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");
    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1").set("formula", "e + M => p + 2 e");
    model.component("comp1").physics("re").feature("rch1").set("kf", 0);
    model.component("comp1").physics("re").feature("e").set("z", -1);
    model.component("comp1").physics("re").feature("M").set("cLock", true);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cM", 0, 0);
    model.component("comp1").physics("re").feature("p").set("z", 1);
    model.component("comp1").physics("re").create("sync1", "ReactionToMph", -1);
    model.component("comp1").physics("re").feature("sync1").set("geomToUse", "2Daxi");
    model.component("comp1").physics("re").feature("sync1").set("massbalance", "TransportOfChargeCarriers");
    model.component("comp1").physics("re").feature("sync1").set("Electrostatics", true);
    model.component("comp1").physics("re").prop("synchronize").set("synchronize", "1");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom1", 2);
    model.component("comp2").geom("geom1").axisymmetric(true);
    model.component("comp2").geom("geom1").label("\u51e0\u4f55 1(2Daxi)");

    model.component("comp2").mesh().create("mesh1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");

    model.component("comp1").physics("re").feature("sync1").set("genom", new String[]{"comp2:geom1"});
    model.component("comp1").physics("re").feature("sync1").set("studyname", new String[]{"comp2:std1"});
    model.component("comp2").physics().create("chem", "Chemistry", "geom1");
    model.component("comp2").physics().move("chem", 0);
    model.component("comp2").physics().create("tcc", "TransportOfChargeCarriers", "geom1");
    model.component("comp2").physics().create("es", "Electrostatics", "geom1");
    model.component("comp2").physics("es").prop("ShapeProperty").set("order_electricpotential", 1);
    model.component("comp2").physics("tcc").feature("tp1").set("E_src", "root.comp2.es.Er");
    model.component("comp2").physics("es").feature().create("scd1", "SpaceChargeDensity");
    model.component("comp2").physics("es").feature("scd1").set("rhoq_src", "root.comp2.tcc.rho");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T_src", "userdef");
    model.component("comp2").physics("chem").prop("TPFeatureInput").set("T", "293.15[K]");
    model.component("comp2").physics("chem").prop("mixture").set("mixture", "gas");
    model.component("comp2").physics("chem").prop("mixture").set("gasDensitySel", "Automatic");
    model.component("comp2").physics("chem").prop("Activity").set("useActivity", "0");
    model.component("comp2").physics("chem").prop("chemkin").set("chemkin", "0");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("VolumetricConcentrationGlobalActivityStandardState", "1[mol/m^3]");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SurfaceSpeciesConcentrationType", "SurfaceConcentration");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SurfaceGlobalActivityStandardState", "1[mol/m^2]");
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty")
         .set("SpeciesrateUserDefinedList", new String[]{"M"});
    model.component("comp2").physics("chem").prop("ChemistryCommonProperty").set("AdditionalSourceFeature", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("SumActiveSpecies", "3");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveVolumeSpecies", "3");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpecies", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("NumActiveSurfaceSpeciesVariable", "0");
    model.component("comp2").physics("chem").prop("ActiveSpecies").set("surface", "0");
    model.component("comp2").physics("chem").prop("solventIsSet").set("solventIsSet", "0");
    model.component("comp2").physics("chem").create("rch1", "ReactionChem");
    model.component("comp2").physics("chem").feature("rch1").set("rSequenceNo", "1");
    model.component("comp2").physics("chem").feature("rch1").set("formula", "e + M => p + 2 e");
    model.component("comp2").physics("chem").feature("rch1").set("updatechem", "0");
    model.component("comp2").physics("chem").feature("rch1").set("useArrhenius", "0");
    model.component("comp2").physics("chem").feature("rch1").set("kf", "0");
    model.component("comp2").physics("chem").feature("rch1").set("bulkFwdOrder", "2");
    model.component("comp2").physics("chem").feature("rch1").set("surfFwdOrder", "0");
    model.component("comp2").physics("chem").feature("rch1").label("1: e + M => p + 2 e");
    model.component("comp2").physics("chem").feature("rch1").set("rClass", "volumetric");
    model.component("comp2").physics("chem").feature("e").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("e").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("e").set("specLabel", "e");
    model.component("comp2").physics("chem").feature("e").set("speciesNameInput", "e");
    model.component("comp2").physics("chem").feature("e").set("specName", "e");
    model.component("comp2").physics("chem").feature("e").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("e").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("e").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("e").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("e").set("z", "-1");
    model.component("comp2").physics("chem").feature("e").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("e").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("e").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("e").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("e").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("e").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("e").set("cLock", "0");
    model.component("comp2").physics("chem").feature("e").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("e").set("dependent", "0");
    model.component("comp2").physics("chem").feature("e").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("e").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("e").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("e").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("e").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("e").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("M").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("M").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("M").set("specLabel", "M");
    model.component("comp2").physics("chem").feature("M").set("speciesNameInput", "M");
    model.component("comp2").physics("chem").feature("M").set("specName", "M");
    model.component("comp2").physics("chem").feature("M").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("M").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("M").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("M").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("M").set("z", "0");
    model.component("comp2").physics("chem").feature("M").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("M").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("M").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("M").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("M").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("M").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("M").set("cLock", "1");
    model.component("comp2").physics("chem").feature("M").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("M").set("dependent", "0");
    model.component("comp2").physics("chem").feature("M").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("M").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("M").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("M").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("M").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("M").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("M").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("M").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("M").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("M").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("M").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("M").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("M").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("M").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("M").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("M").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("M").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("M").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("M").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesSource", "Reaction");
    model.component("comp2").physics("chem").feature("p").set("sisDef", "1");
    model.component("comp2").physics("chem").feature("p").set("specLabel", "p");
    model.component("comp2").physics("chem").feature("p").set("speciesNameInput", "p");
    model.component("comp2").physics("chem").feature("p").set("specName", "p");
    model.component("comp2").physics("chem").feature("p").set("enableChemicalFormulaCheckbox", "0");
    model.component("comp2").physics("chem").feature("p").set("chemicalFormula", "");
    model.component("comp2").physics("chem").feature("p").set("sType", "volumetric");
    model.component("comp2").physics("chem").feature("p").set("M", "0.0[kg/mol]");
    model.component("comp2").physics("chem").feature("p").set("z", "1");
    model.component("comp2").physics("chem").feature("p").set("sigma", "3.458[angstrom]");
    model.component("comp2").physics("chem").feature("p").set("epsilonkb", "107.4[K]");
    model.component("comp2").physics("chem").feature("p").set("mu", "0[C*m]");
    model.component("comp2").physics("chem").feature("p").set("rho", "1000[kg/m^3]");
    model.component("comp2").physics("chem").feature("p").set("k", "0.02[W/(m*K)]");
    model.component("comp2").physics("chem").feature("p").set("ActivityCoefficient", "1");
    model.component("comp2").physics("chem").feature("p").set("cLock", "0");
    model.component("comp2").physics("chem").feature("p").set("Dependent", "0");
    model.component("comp2").physics("chem").feature("p").set("dependent", "0");
    model.component("comp2").physics("chem").feature("p").set("SpeciesrateSelection", "Automatic");
    model.component("comp2").physics("chem").feature("p").set("speciesEnthalpy", "NASA");
    model.component("comp2").physics("chem").feature("p").set("Tlo", "300[K]");
    model.component("comp2").physics("chem").feature("p").set("Tmid", "1000[K]");
    model.component("comp2").physics("chem").feature("p").set("Thi", "5000[K]");
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaLo1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaLo2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaLo3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaLo4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaLo5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaLo6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaLo7", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaHi1", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaHi2", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaHi3", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaHi4", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaHi5", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaHi6", new String[]{"0"});
    model.component("comp2").physics("chem").feature("p").set("SpeciesThermoaHi7", new String[]{"0"});
    model.component("comp2").physics("chem").prop("simpropChem").set("rSequenceNo", "1");
    model.component("comp2").physics("chem").prop("simpropChem").set("sSequenceNo", "3");
    model.component("comp2").physics("chem").prop("mixture").set("hasPropertyPackage", "0");
    model.component("comp2").physics("chem").prop("mixture").set("PropertyPackage", "");
    model.component("comp2").physics("chem").prop("mixture").set("Thermodynamics", "0");
    model.component("comp2").physics("tcc").field("numberdensity").component(new String[]{"ne", "np"});
    model.component("comp2").physics("tcc").feature("init1").set("n0", new String[]{"0", "0"});
    model.component("comp2").physics("tcc").feature().create("reac1", "Reactions");
    model.component("comp2").physics("tcc").feature("reac1").selection().all();
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("MassTransfer", "tcc");
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationInput", new String[]{"UserDefined", "tcc.c_ne", "tcc.c_np"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("ConcentrationValue", new String[]{"cM", "\u6c42\u89e3\u7684", "\u6c42\u89e3\u7684"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("uselog", new String[]{"cM", "ce", "cp"});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("SolidConcentration", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter").set("csurf", new String[]{});
    model.component("comp2").physics("chem").prop("ChemistryModelInputParameter")
         .set("AqueousSpeciesConcentration", new String[]{});
    model.component("comp2").physics("tcc").feature("tp1").setIndex("z_ne_src", "root.comp2.chem.z_e", 0);
    model.component("comp2").physics("tcc").feature("tp1").setIndex("z_np_src", "root.comp2.chem.z_p", 0);
    model.component("comp2").physics("tcc").feature("reac1").setIndex("Rmol_ne_src", "root.comp2.chem.R_e", 0);
    model.component("comp2").physics("tcc").feature("reac1").setIndex("Rmol_np_src", "root.comp2.chem.R_p", 0);
    model.component("comp1").physics("re").feature("sync1").set("geomToUse", "geom1");
    model.component("comp1").physics("re").feature("sync1").set("chemTag", "chem");
    model.component("comp1").physics("re").feature("sync1").set("massbalance", "tcc");

    model.study("std1").feature("stat").setSolveFor("/physics/chem", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tcc", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);

    model.component("comp2").geom("geom1").lengthUnit("cm");
    model.component("comp2").geom("geom1").create("r1", "Rectangle");
    model.component("comp2").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp2").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp2").geom("geom1").feature("r1").setIndex("layer", 0.06, 0);
    model.component("comp2").geom("geom1").runPre("fin");
    model.component("comp2").geom("geom1").run();

    model.component("comp2").physics("es").feature("scd1").selection().set(1, 2);

    model.param().set("P", "1[atm]");
    model.param().descr("P", "Gas pressure");
    model.param().set("mu", "2.9e5/(P/1[Torr])*1[cm^2/V/s]");
    model.param().descr("mu", "Electron mobility");
    model.param().set("cM", "P/R_const/300[K]");
    model.param().descr("cM", "Gas molar concentration");
    model.param().set("V0", "52[kV]");
    model.param().descr("V0", "Applied voltage");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "alphaFun");
    model.func("an1").set("expr", "5.7*P/1[Torr]*exp(-260*P/1[Torr]/x)");
    model.func("an1").set("fununit", "cm^-1");
    model.func("an1").setIndex("argunit", "V/cm", 0);

    model.component("comp2").physics("chem").feature("rch1").set("kf", "alphaFun(es.normE)*mu*es.normE/cM");
    model.component("comp2").physics("chem").feature("e").set("M", "5.5e-7[kg/mol]");
    model.component("comp2").physics("chem").feature("M").set("M", "0.029[kg/mol]");
    model.component("comp2").physics("chem").feature("p").set("M", "0.029[kg/mol]");
    model.component("comp2").physics("es").create("gnd1", "Ground", 1);
    model.component("comp2").physics("es").feature("gnd1").selection().set(2, 5);
    model.component("comp2").physics("es").create("pot1", "ElectricPotential", 1);
    model.component("comp2").physics("es").feature("pot1").selection().set(3, 6);
    model.component("comp2").physics("es").feature("pot1").set("V0", "V0");

    model.component("comp2").variable().create("var1");
    model.component("comp2").variable("var1")
         .set("n0", "(1e8+1e14*exp(-((z/(1[cm])-0.5)/0.027)^2-(r/(1[cm])/0.021)^2))[cm^-3]");
    model.component("comp2").variable("var1").descr("n0", "Initial condition");

    model.component("comp2").physics("tcc").feature("init1").setIndex("n0", "n0", 0);
    model.component("comp2").physics("tcc").feature("init1").setIndex("n0", "n0", 1);
    model.component("comp2").physics("tcc").feature("tp1")
         .setIndex("mu", new String[]{"mu", "0", "0", "0", "mu", "0", "0", "0", "mu"}, 0);
    model.component("comp2").physics("tcc").feature("tp1").setIndex("mu", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, 1);
    model.component("comp2").physics("tcc").feature("tp1").setIndex("D", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, 1);
    model.component("comp2").physics("tcc").feature("tp1")
         .setIndex("D", new String[]{"1800[cm^2/s]", "0", "0", "0", "1800[cm^2/s]", "0", "0", "0", "1E-5[m^2/s]"}, 0);
    model.component("comp2").physics("tcc").feature("tp1")
         .setIndex("D", new String[]{"1800[cm^2/s]", "0", "0", "0", "1800[cm^2/s]", "0", "0", "0", "2190[cm^2/s]"}, 0);

    model.component("comp2").mesh("mesh1").create("map1", "Map");
    model.component("comp2").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp2").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp2").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 3);
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 20);
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 10);
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp2").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis2").selection().set(1, 4);
    model.component("comp2").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 800);
    model.component("comp2").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh1").run();

    model.study("std1").create("time", "Transient");
    model.study("std1").feature("stat").setSolveFor("/physics/chem", false);
    model.study("std1").feature("stat").setSolveFor("/physics/tcc", false);
    model.study("std1").feature("time").setSolveFor("/physics/re", false);
    model.study("std1").feature("time").set("tunit", "ns");
    model.study("std1").feature("time").set("tlist", "range(0,0.5,2.5)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp2_V").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp2_V").set("scaleval", "1e3");
    model.sol("sol1").feature("v2").feature("comp2_V").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp2_V").set("scaleval", "1e3");
    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", "0.01[ns]");
    model.sol("sol1").feature("t1").set("maxorder", 3);
    model.sol("sol1").feature("t1").set("minorder", 2);
    model.sol("sol1").feature("t1").set("estrat", "exclude");
    model.sol("sol1").feature("t1").feature("d1").set("linsolver", "pardiso");

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", "es.Ez");
    model.result("pg1").feature("lngr1").set("unit", "kV/cm");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result("pg2").run();
    model.result("pg2").set("data", "mir1");
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("unit", "1/cm^3");
    model.result("pg2").feature("con1").set("levelmethod", "levels");
    model.result("pg2").feature("con1").set("levels", "range(1.0e13,1.0e13,1.5e14)");
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").set("view", "view1");

    model.component("comp2").view("view1").axis().set("xmin", 0);
    model.component("comp2").view("view1").axis().set("xmax", 0.06);
    model.component("comp2").view("view1").axis().set("ymin", 0);
    model.component("comp2").view("view1").axis().set("ymax", 1);
    model.component("comp2").view("view1").axis().set("viewscaletype", "automatic");

    model.result("pg2").run();
    model.result("pg2").feature("con1").create("trn1", "Transformation");
    model.result("pg2").run();
    model.result("pg2").feature("con1").feature("trn1").set("enablerot", true);
    model.result("pg2").feature("con1").feature("trn1").set("rotangle", "-90");
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u5177\u6709\u81ea\u5b9a\u4e49\u653e\u7535\u5316\u5b66\u7684\u653e\u7535");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u901a\u8fc7\u201c\u53cd\u5e94\u5de5\u7a0b\u201d\u63a5\u53e3\u751f\u6210\u5177\u6709\u81ea\u5b9a\u4e49\u653e\u7535\u5316\u5b66\u7684\u653e\u7535\u6a21\u578b\uff0c\u5e76\u91cd\u73b0\u4e86\u5e93\u4e2d\u7684\u6a21\u578b 127181\uff08\u5e73\u884c\u677f\u7535\u6781\u4e2d\u7684\u53cc\u5934\u6d41\u6ce8\uff09\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("double_headed_streamer_discharge_chemistry.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
